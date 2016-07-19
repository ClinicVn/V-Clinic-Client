var UserInfoService = {};
UserInfoService.create = function(data) {
	return $.post(window.location.origin + "/users/save/", {
		data : JSON.stringify({
			userCode : data.code(),
			userPwd : data.password(),
			status : "1",
			type : data.type(),
			userPhone : data.phone(),
			userEmail : data.email(),
			fullname : data.name(),
			address : data.address(),
			rePassword : data.rePassword()
		}),
		mode : 1
	});
}