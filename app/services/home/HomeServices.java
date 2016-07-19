package services.home;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import Com.ServiceUrl;
import models.Menu;
import play.data.DynamicForm;
import play.libs.Json;
import play.libs.ws.*;
import com.fasterxml.jackson.databind.*;

public class HomeServices {
	@Inject
	public static String AUTHEN_TOKEN = "";
	@Inject
	WSClient ws;

	public List<Menu> getListMenu() {
		List<Menu> lstMenu = new ArrayList<Menu>();
		lstMenu.add(new Menu("01", "User setting", "/users/list", "/assets/images/menu_support.jpg"));
		lstMenu.add(new Menu("01", "User setting", "#", "/assets/images/menu_support.jpg"));
		lstMenu.add(new Menu("01", "User setting", "#", ""));
		lstMenu.add(new Menu("01", "User setting", "#", ""));
		lstMenu.add(new Menu("01", "User setting", "#", ""));
		return lstMenu;
	}

	public boolean isLogin() {
		if (HomeServices.AUTHEN_TOKEN == "")
			return false;
		return true;
	}

	public String isValidLogin(DynamicForm form) {
		WSRequest rq = ws.url(ServiceUrl.LOGIN_SUBMIT).setContentType("application/x-www-form-urlencoded");
		JsonNode returnNode = ServiceUrl.post(rq, Json.toJson(form.data()));
		if(returnNode != null)
			return returnNode.get("authToken").asText();
		else
			return "";
	}
}
