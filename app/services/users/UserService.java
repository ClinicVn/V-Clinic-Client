package services.users;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import Com.ServiceUrl;
import models.User;
import models.UserView;
import play.libs.ws.*;

public class UserService {
	public static List<User> sLstUser = new ArrayList<User>();
	@Inject 
	WSClient ws;
	public boolean isExistUser(String code){
		// service check exist user
		return false;
	}
	public boolean isMatchPassword(Map<String, String[]> dataMap){
		String password = dataMap.get("password")[0];
		String confirmPassword = dataMap.get("retype_password")[0];
		return password.equals(confirmPassword);
	}
	public User mapUser(Map<String, String[]> dataMap){
		try{
			User user = new User();
			user.setCode(dataMap.get("username")[0]);
			user.setPassword(dataMap.get("password")[0]);
			user.setName(dataMap.get("fullname")[0]);
			user.setAddress(dataMap.get("address")[0]);
			user.setEmail(dataMap.get("email")[0]);
			user.setPhoneNumber(dataMap.get("phone")[0]);
			user.setType(dataMap.get("type")[0]);
			return user;
		}catch(Exception ex)
		{
			return null;
		}
		
	}
	
	public List<UserView> getListUser(String token){
		WSRequest req = ws.url(ServiceUrl.GET_LIST_USER);
		req.setHeader("X-AUTH-TOKEN", token);
		CompletionStage<JsonNode> jsonPromise = req.get()
		        .thenApply(WSResponse::asJson);
		CompletableFuture<JsonNode> nodeFuture =  jsonPromise.toCompletableFuture();
		JsonNode jData = null;
		try {
			 jData = nodeFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		ArrayNode lstUser = (ArrayNode)jData.get("data");
		List<UserView> lstView = new ArrayList<UserView>();
		UserView curUser = null;
		for(JsonNode jNode: lstUser){
			curUser = new UserView(Integer.parseInt(jNode.get("id").asText())
							,jNode.get("code").asText()
							,jNode.get("name").asText()
							,jNode.get("status").asText()
							,jNode.get("phoneNumber").asText()
							,jNode.get("type").asText());
			lstView.add(curUser);
		}
		return lstView;
	}
}
