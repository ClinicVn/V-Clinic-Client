package services.home;

import java.util.ArrayList;
import java.util.List;

import models.Menu;

public class HomeServices {
	public List<Menu> getListMenu(){
		List<Menu> lstMenu = new ArrayList<Menu>();
    	lstMenu.add(new Menu("01","User setting","/users/list","/assets/images/menu_user_setting.ico"));
    	lstMenu.add(new Menu("01","User setting","#","/assets/images/menu_support.jpg"));
    	lstMenu.add(new Menu("01","User setting","#",""));
    	lstMenu.add(new Menu("01","User setting","#",""));
    	lstMenu.add(new Menu("01","User setting","#",""));
		return lstMenu;
	}
}
