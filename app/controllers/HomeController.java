package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import Com.StringValue;
import play.*;
import play.api.mvc.Session;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.*;
import services.home.HomeServices;
import views.html.*;
import models.*;
/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends MasterPage {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
	@Inject
	HomeServices homeServices;
	
    public Result index() {
    	if(session(StringValue.V00001) != null){
    		return ok(views.html.home.index.render());
    	}
    	else{
    		return ok(views.html.home.login.render(""));
    	}
    }
    public Result login(){
    	DynamicForm form = Form.form().bindFromRequest();
		boolean result = true;
		String sResult = homeServices.isValidLogin(form);
		if(sResult == ""){
			result = false;
		}
		else{
			session(StringValue.V00001,sResult);
		}
		return ok(Json.toJson(result));
    }
    public Result logout(){
    	if(session(StringValue.V00001) != null)
    		session().remove(StringValue.V00001);
    	return redirect(routes.HomeController.index());
    }
    public Result listMenu(){
    	List<Menu> lstMenu = homeServices.getListMenu();
    	return ok(Json.toJson(lstMenu));
    }
}
