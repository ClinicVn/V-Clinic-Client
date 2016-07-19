package Com;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.databind.JsonNode;

import play.data.DynamicForm;
import play.libs.Json;
import play.libs.ws.WSRequest;
import play.libs.ws.WSResponse;

public class ServiceUrl {
	private static String SERVICE_HOST = "http://45.117.160.37";
	// User 
	public static String GET_LIST_USER = SERVICE_HOST +   "/users";
	public static String SAVE_USER = SERVICE_HOST + "/users";
	public static String GET_USER_BY_CODE = SERVICE_HOST + "/users/";
	public static String DELETE_USER = SERVICE_HOST + "/users/";
	// login
	public static String LOGIN_SUBMIT = SERVICE_HOST + "/login";
	
	public static JsonNode post(WSRequest req, JsonNode input){
		return ServiceUrl.getNode(req.post(input).thenApply(WSResponse::asJson).toCompletableFuture());
	}
	public static JsonNode get(WSRequest req){
		return ServiceUrl.getNode(req.get().thenApply(WSResponse::asJson).toCompletableFuture());
	}
	public static JsonNode delete(WSRequest req){
		return ServiceUrl.getNode(req.delete().thenApply(WSResponse::asJson).toCompletableFuture());
	}
	private static JsonNode getNode(CompletableFuture<JsonNode> nodeFuture){
		
		JsonNode jNode = null;
		try {
			jNode = nodeFuture.get();
		} catch (InterruptedException | ExecutionException e) {
			return null;
		}
		return jNode;
	}
}