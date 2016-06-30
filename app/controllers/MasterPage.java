package controllers;

import Com.StringValue;
import play.mvc.Controller;

public class MasterPage extends Controller {
	/**
	 * Check authenticate
	 * @return
	 */
	public boolean CheckLogin(){
		if(session(StringValue.V00001) == null)
			return false;
		return true;
	}
}