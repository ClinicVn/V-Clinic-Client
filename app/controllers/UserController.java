package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.*;

import javax.inject.Inject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

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
		List<ObjectNode> lstView = userService.getListUser(session(StringValue.V00001));
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
		if(this.CheckLogin())
			return ok(views.html.users.create.render(ul));
		else
			return redirect(routes.HomeController.index());
	}

	public Result save() {
		final Map<String, String[]> values = request().body()
				.asFormUrlEncoded();
		List<ClinicMessage> lstMsg = userService.validInputUserInfo(values, session(StringValue.V00001));
		if(lstMsg.size() == 0 ){
			lstMsg = userService.save(values, session(StringValue.V00001));
		}
		if(lstMsg.size() == 0){
			return redirect(routes.UserController.index());
		}
		else
		{
			return ok(Json.toJson(false));
		}
	}
}