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
		ArrayNode lstView = userService.getListUser(session(StringValue.V00001));
		if (lstView == null)
			return ok(Json.toJson(false));
		return ok(lstView);
	}

	public Result index() {
		if (this.CheckLogin())
			return ok(views.html.users.list.render());
		else
			return redirect(routes.HomeController.index());
	}

	public Result create() {
		if (this.CheckLogin())
			return ok(views.html.users.info.render("Create"));
		else
			return redirect(routes.HomeController.index());
	}

	public Result edit(String code) {
		if (!this.CheckLogin())
			return redirect(routes.HomeController.index());
		JsonNode userNode = userService.getUserByCode(code, session(StringValue.V00001));
		if (userNode == null)
			return ok(Json.toJson(false));
		return ok(views.html.users.info.render(userNode.toString()));
	}

	public Result delete(String code) {
		if (!this.CheckLogin())
			return redirect(routes.HomeController.index());
		boolean result = userService.deleteUser(code, session(StringValue.V00001));
		return ok(Json.toJson(result));

	}

	public Result getUserByCode(String code) {
		if (!this.CheckLogin())
			return ok(Json.toJson(false));
		JsonNode userNode = userService.getUserByCode(code, session(StringValue.V00001));
		if (userNode == null)
			return ok(Json.toJson(false));
		return ok(userNode);
	}

	public Result save() {
		DynamicForm form = Form.form().bindFromRequest();
		JsonNode userNode = Json.parse(form.get("data"));
		int mode = Integer.parseInt(form.get("mode"));
		List<ClinicMessage> lstMsg = new ArrayList<ClinicMessage>();
		if(mode == 1)
			lstMsg = userService.validCreateInfo(mode, userNode, session(StringValue.V00001));
		if(lstMsg.size() == 0 ){
			lstMsg = userService.save(userNode, session(StringValue.V00001));
		}
		return ok(Json.toJson(lstMsg));
	}
}