package services.users;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import models.User;
import models.UserView;

public class UserService {
	public boolean isExistUser(String code){
		return false;
	}
	public boolean isMatchPassword(Map<String, String[]> dataMap){
		String password = dataMap.get("password")[0];
		String confirmPassword = dataMap.get("retype-password")[0];
		return password.equals(confirmPassword);
	}
	public User mapUser(Map<String, String[]> dataMap){
		User user = new User();
		user.setCode(dataMap.get("username")[0]);
		user.setPassword(dataMap.get("password")[0]);
		user.setName(dataMap.get("fullname")[0]);
		user.setAddress(dataMap.get("address")[0]);
		user.setEmail(dataMap.get("email")[0]);
		user.setPhoneNumber(dataMap.get("phone")[0]);
		user.setType(dataMap.get("type")[0]);
		return user;
	}
	
	public List<UserView> getListUser(){
		List<UserView> lstUser = new ArrayList<UserView>();
		lstUser.add(new UserView(1,"phuongdv","Do Van Phuong","offline","01695563080","Director"));
		lstUser.add(new UserView(2,"dungvst","Nguyen Van Dung","online","0000001111","Staff"));
		return lstUser;
	}
}
