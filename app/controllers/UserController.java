package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.*;

import javax.inject.Inject;

import models.Product;
import models.User;
import models.UserLabel;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.users.UserService;
import views.html.*;

public class UserController  extends Controller{
	public static UserLabel ul  = new UserLabel(1);
	
	@Inject
	UserService userService;
	
	public Result list(){
		return ok(Json.toJson(userService.getListUser()));
	}
	
	public Result index(){
		return ok(views.html.users.list.render());
	}
	
	public Result create(){
		return ok(views.html.users.create.render(ul));
	}
	
	public Result save(){
		final Map<String, String[]> values = request().body().asFormUrlEncoded();
		String saveResult = "";
		User user = userService.mapUser(values);
		if(user == null)
			saveResult = "USER_ERR001";
		if(saveResult != "" && userService.isMatchPassword(values))
			saveResult = "USER_ERR002";
		if(saveResult != "" && userService.isExistUser(user.getCode()))
			saveResult = "USER_ERR003";
		if(saveResult == "")
		{
			UserService.sLstUser.add(user);
		}
		values.
		return ok(Json.toJson(saveResult));
//		if(userService.isMatchPassword(values) && !userService.isExistUser("xxx")){
//			// call service here
//			lstUser.add(userService.mapUser(values));
//			return redirect(routes.UserController.index());
//		}
//		else{
//			return ok(top.render("User login"));
//		}
	}
}