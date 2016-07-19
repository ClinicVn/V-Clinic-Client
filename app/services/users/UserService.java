package services.users;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;
import javax.validation.Validation;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import Com.ServiceUrl;
import Com.StringValue;
import models.ClinicMessage;
import models.User;
import models.UserView;
import play.libs.ws.*;
import play.api.data.validation.Valid;
import play.api.data.validation.ValidationResult;
import play.data.validation.*;

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
		// save user
		WSRequest req = ws.url(ServiceUrl.SAVE_USER);
		req.setContentType("application/form-data");
		req.setHeader("X-AUTH-TOKEN", token);
		JsonNode jReturn = ServiceUrl.post(req, jUser);
		if(jReturn == null){
			lstError.add(new ClinicMessage("Exception", StringValue.ERR00000));
		}
		return lstError;
	}

	public List<ClinicMessage> validCreateInfo(
			int mode,JsonNode dataNode, String token) {
		List<ClinicMessage> lstError = new ArrayList<ClinicMessage>();
		// user validate
		String userCode = dataNode.get("userCode").asText();
		if(userCode.trim().length() < 5 || userCode.trim().length() > 30){
			lstError.add(new ClinicMessage("userCode", "Invalid Account"));
		}
		else{
			for(JsonNode user: this.getListUser(token)){
				if(user.get("userCode").asText().equals(userCode)){
					lstError.add(new ClinicMessage("userCode", "Exist Account"));
					break;
				}
			}
		}
		// password valid
		String password = dataNode.get("userPwd").asText().trim();
		String rePassword = dataNode.get("rePassword").asText().trim();
		if(password.length() <5 || password.length() >50){
			lstError.add(new ClinicMessage("userPwd", "Password's length must be between 5 and 50 !"));
		}else if(!password.equals(rePassword)){
			lstError.add(new ClinicMessage("rePassword", "retype password not match !"));
		}
		// type valid
		String type = dataNode.get("type").asText();
		if(type.trim().length() == 0){
			lstError.add(new ClinicMessage("type", "Type must be input !"));
		}
		// valid name
		String name = dataNode.get("fullname").asText();
		if(name.trim().length() == 0){
			lstError.add(new ClinicMessage("fullname", "Fullname must be input !"));
		}
		// valid email
		String email = dataNode.get("userEmail").asText();
		if(email.trim().length() == 0){
			lstError.add(new ClinicMessage("userEmail", "Email must be input !"));
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
		return ServiceUrl.get(req);
	}
	
	public boolean deleteUser(String code, String token){
		WSRequest req = ws.url(ServiceUrl.GET_USER_BY_CODE + code);
		req.setHeader("X-AUTH-TOKEN", token);
		JsonNode jData = ServiceUrl.delete(req);
		if(jData == null)
			return false;
		return true;
	}
}
