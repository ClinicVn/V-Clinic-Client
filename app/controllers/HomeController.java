package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import play.*;
import play.api.mvc.Session;
import play.libs.Json;
import play.mvc.*;
import services.home.HomeServices;
import views.html.*;
import models.*;
/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
	@Inject
	HomeServices homeServices;
	
    public Result index() {
    	if(session("authen_token") != null){
    		return ok(views.html.home.index.render());
    	}
    	else{
    		return ok(views.html.home.login.render(""));
    	}
    }
    public Result login(){
    	final Map<String, String[]> values = request().body().asFormUrlEncoded();
		String username = values.get("username")[0];
		String password = values.get("password")[0];
		boolean result = true;
		String sResult = homeServices.isValidLogin(username, password);
		if(sResult == ""){
			result = false;
		}
		else{
			session("authen_token",sResult);
		}
		return ok(Json.toJson(result));
    }
    public Result listMenu(){
    	List<Menu> lstMenu = homeServices.getListMenu();
    	return ok(Json.toJson(lstMenu));
    }
}
