package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

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
        return ok(views.html.home.index.render());
    }
    
    public Result listMenu(){
    	List<Menu> lstMenu = homeServices.getListMenu();
    	return ok(Json.toJson(lstMenu));
    }
}
