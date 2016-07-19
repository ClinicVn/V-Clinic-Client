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

	public List<ClinicMessage> save(JsonNode jUser,String token) {
		List<ClinicMessage> lstError = new ArrayList<ClinicMessage>();
		try {
			// save user
			WSRequest req = ws.url(ServiceUrl.SAVE_USER);
			req.setContentType("application/form-data");
			req.setHeader("X-AUTH-TOKEN", token);
			req.post(jUser);
		} catch (Exception ex) {
			lstError.add(new ClinicMessage("Exception", StringValue.ERR00000));
		}
		return lstError;
	}

	public List<ClinicMessage> validInputUserInfo(
			int mode,JsonNode dataNode, String token) {
		List<ClinicMessage> lstError = new ArrayList<ClinicMessage>();
		JsonNode jUser = null;
		try {
			String account = dataNode.get("code").asText();
			String password = dataNode.get("password").asText();
			String re_password = dataNode.get("rePassword").asText();
			String name = dataNode.get("name").asText();
			String phone = dataNode.get("phone").asText();
			String type = dataNode.get("type").asText();
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
				lstError.add(new ClinicMessage("retype-password",
						StringValue.ERR00004));
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

	public ArrayNode getListUser(String token) {
		WSRequest req = ws.url(ServiceUrl.GET_LIST_USER + "?page=1&size=50");
		req.setHeader("X-AUTH-TOKEN", token);
		JsonNode jData = ServiceUrl.get(req);
		if(jData == null)
			return null;
		return (ArrayNode) jData.get("data");
	}
	
	public JsonNode getUserByCode(String code, String token) {
		WSRequest req = ws.url(ServiceUrl.GET_USER_BY_CODE + code);
		req.setHeader("X-AUTH-TOKEN", token);
		CompletionStage<JsonNode> jsonPromise = req.get().thenApply(
				WSResponse::asJson);
		CompletableFuture<JsonNode> nodeFuture = jsonPromise
				.toCompletableFuture();
		JsonNode jData = null;
		try {
			jData = nodeFuture.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return jData;
	}
	
	public boolean deleteUser(String code, String token){
		WSRequest req = ws.url(ServiceUrl.GET_USER_BY_CODE + code);
		req.setHeader("X-AUTH-TOKEN", token);
		CompletionStage<JsonNode> jsonPromise = req.delete().thenApply(
				WSResponse::asJson);
		CompletableFuture<JsonNode> nodeFuture = jsonPromise
				.toCompletableFuture();
		try {
			JsonNode jData = nodeFuture.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
