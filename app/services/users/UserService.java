package services.users;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import Com.ServiceUrl;
import Com.StringValue;
import models.ClinicMessage;
import models.User;
import models.UserView;
import play.libs.Json;
import play.libs.ws.*;

public class UserService {
	public static List<User> sLstUser = new ArrayList<User>();
	@Inject
	WSClient ws;

	public boolean isExistUser(String code) {
		// service check exist user
		return false;
	}

	public boolean isMatchPassword(Map<String, String[]> dataMap) {
		String password = dataMap.get("password")[0];
		String confirmPassword = dataMap.get("retype_password")[0];
		return password.equals(confirmPassword);
	}

	public List<ClinicMessage> save(Map<String, String[]> dataMap, String token) {
		List<ClinicMessage> lstError = new ArrayList<ClinicMessage>();
		try {
			User user = new User();
			user.setCode(dataMap.get("username")[0]);
			user.setPassword(dataMap.get("password")[0]);
			user.setName(dataMap.get("fullname")[0]);
			user.setAddress(dataMap.get("address")[0]);
			user.setEmail(dataMap.get("email")[0]);
			user.setPhoneNumber(dataMap.get("phone")[0]);
			user.setType(dataMap.get("type")[0]);
			// save user
			WSRequest req = ws.url(ServiceUrl.SAVE_USER + "?" + "code=" + user.getCode() + "&email=" + user.getEmail()
					+ "&name=" + user.getName() + "&password=" + user.getPassword() + "&phoneNumber="
					+ user.getPhoneNumber() + "&type=" + user.getType());
			req.setHeader("X-AUTH-TOKEN", token);
		} catch (Exception ex) {
			lstError.add(new ClinicMessage("Exception", StringValue.ERR00000));
		}
		return lstError;
	}

	public List<ClinicMessage> validInputUserInfo(Map<String, String[]> dataMap, String token) {
		List<ClinicMessage> lstError = new ArrayList<ClinicMessage>();
		try {
			String account = dataMap.get("username")[0];
			String password = dataMap.get("password")[0];
			String re_password = dataMap.get("retype_password")[0];
			String name = dataMap.get("fullname")[0];
			String phone = dataMap.get("phone")[0];
			String type = dataMap.get("type")[0];
			// account check
			if (account.trim() == "")
				lstError.add(new ClinicMessage("username", StringValue.ERR00005));
			else if (account.trim().length() > 30)
				lstError.add(new ClinicMessage("username", StringValue.ERR00006));
			else if (account.trim().length() < 6)
				lstError.add(new ClinicMessage("username", StringValue.ERR00002));
			// password check
			if (password.trim() == "")
				lstError.add(new ClinicMessage("password", StringValue.ERR00005));
			else if (password.trim().length() < 6)
				lstError.add(new ClinicMessage("password", StringValue.ERR00003));
			else if (!password.equals(re_password))
				lstError.add(new ClinicMessage("retype-password", StringValue.ERR00004));
			// name check
			if (name.trim() == "")
				lstError.add(new ClinicMessage("fullname", StringValue.ERR00005));
			// type check
			if (type.trim() == "")
				lstError.add(new ClinicMessage("type", StringValue.ERR00005));
		} catch (Exception ex) {
			lstError.add(new ClinicMessage("Exception", StringValue.ERR00000));
		}
		return lstError;
	}

	public List<ObjectNode> getListUser(String token) {
		WSRequest req = ws.url(ServiceUrl.GET_LIST_USER);
		req.setHeader("X-AUTH-TOKEN", token);
		CompletionStage<JsonNode> jsonPromise = req.get().thenApply(WSResponse::asJson);
		CompletableFuture<JsonNode> nodeFuture = jsonPromise.toCompletableFuture();
		JsonNode jData = null;
		List<ObjectNode> lstView = new ArrayList<ObjectNode>();
			try {
				jData = nodeFuture.get();
				ArrayNode lstUser = (ArrayNode) jData.get("data");
				ObjectMapper mapper = new ObjectMapper();
				for (JsonNode jNode : lstUser) {
					User user = mapper.readValue(jNode.toString(), User.class);
					String x = mapper.writeValueAsString(user);
					lstView.add(user.getView());
				}
			} catch (InterruptedException | ExecutionException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		return lstView;
	}
}
