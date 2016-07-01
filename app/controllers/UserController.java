package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.*;

import javax.inject.Inject;

import com.fasterxml.jackson.databind.JsonNode;

import Com.StringValue;
import models.ClinicMessage;
import models.Product;
import models.User;
import models.UserLabel;
import models.UserView;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.users.UserService;
import views.html.*;

public class UserController extends MasterPage {
	public static UserLabel ul = new UserLabel(1);

	@Inject
	UserService userService;

	public Result list() {
		List<JsonNode> lstView = userService.getListUser(session(StringValue.V00001));
		if(lstView == null)
			return ok(Json.toJson(false));
		return ok(Json.toJson(lstView));
	}
	public Result index() {
		if(this.CheckLogin())
			return ok(views.html.users.list.render());
		else
			return redirect(routes.HomeController.index());
	}

	public Result create() {
		return ok(views.html.users.create.render(ul));
	}

	public Result save() {
		final Map<String, String[]> values = request().body()
				.asFormUrlEncoded();
		List<ClinicMessage> lstMsg = userService.validInputUserInfo(values, session(StringValue.V00001));
		if(lstMsg.size() > 0 ){
			return ok(Json.toJson(lstMsg));
		}
		//User user = userService.mapUser(values);
		return ok(Json.toJson(false));
	}
}