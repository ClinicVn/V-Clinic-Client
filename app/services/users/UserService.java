package services.users;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import models.User;
import models.UserView;

public class UserService {
	public static List<User> sLstUser = new ArrayList<User>();
	public boolean isExistUser(String code){
		// service check exist user
		return false;
	}
	public boolean isMatchPassword(Map<String, String[]> dataMap){
		String password = dataMap.get("password")[0];
		String confirmPassword = dataMap.get("retype_password")[0];
		return password.equals(confirmPassword);
	}
	public User mapUser(Map<String, String[]> dataMap){
		try{
			User user = new User();
			user.setCode(dataMap.get("username")[0]);
			user.setPassword(dataMap.get("password")[0]);
			user.setName(dataMap.get("fullname")[0]);
			user.setAddress(dataMap.get("address")[0]);
			user.setEmail(dataMap.get("email")[0]);
			user.setPhoneNumber(dataMap.get("phone")[0]);
			user.setType(dataMap.get("type")[0]);
			return user;
		}catch(Exception ex)
		{
			return null;
		}
		
	}
	
	public List<UserView> getListUser(){
		if(UserService.sLstUser.size() == 0)
		{
			UserService.sLstUser.add(new User(1,"phuongdv","Do Van Phuong","phuongdv2906@gmail.com","Hoang Mai - Ha Noi","123654789","01695563080","online","Director"));
			UserService.sLstUser.add(new User(1,"phuongdv","Do Van Phuong","phuongdv2906@gmail.com","Hoang Mai - Ha Noi","123654789","01695563080","online","Director"));
		}
		List<UserView> lstView = new ArrayList<UserView>();
		for(User curUser: UserService.sLstUser){
			lstView.add(new UserView(curUser.getId()
					,curUser.getCode()
					,curUser.getName()
					,curUser.getStatus()
					,curUser.getPhoneNumber()
					,curUser.getType()));
		}
		return lstView;
	}
}
