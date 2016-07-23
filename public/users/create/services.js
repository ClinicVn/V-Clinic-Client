var UserInfoService = {};
UserInfoService.save = function(mode, data) {
	return $.post(window.location.origin + "/users/save/", {
		data : JSON.stringify({
			gid: data.id(),
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
		mode : mode
	});
}
