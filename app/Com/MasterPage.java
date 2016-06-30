package Com;

import play.mvc.Controller;

public class MasterPage extends Controller {
	public boolean CheckLogin(){
		if(session(StringValue.V00001) == null)
			return false;
		return true;
	}
}