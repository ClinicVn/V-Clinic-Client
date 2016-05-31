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
	
	public Result index(){
		return ok(views.html.users.login.render(""));
	}
	
	public Result list(){
		return ok(Json.toJson(userService.getListUser()));
	}
	
	public Result listIndex(){
		return ok(views.html.users.list.render());
	}
	
	public Result login(){
		final Map<String, String[]> values = request().body().asFormUrlEncoded();
		String username = values.get("username")[0];
		String password = values.get("password")[0];
		// call service check user here
		boolean isValid = true;
		if(!isValid){
			return ok(views.html.users.login.render("Invalid user !")); 
		}
		else
			return redirect(routes.HomeController.index());
	}
	
	public Result create(){
		return ok(views.html.users.create.render(ul));
	}
	
	public Result save(){
//		final Map<String, String[]> values = request().body().asFormUrlEncoded();
//		if(userService.isMatchPassword(values) && !userService.isExistUser("xxx")){
//			// call service here
//			lstUser.add(userService.mapUser(values));
//			return redirect(routes.UserController.index());
//		}
//		else{
//			return ok(top.render("User login"));
//		}
		return ok(top.render("User login"));
	}
}