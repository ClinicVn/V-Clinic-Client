var UserInfoService = {};
UserInfoService.save = function(data){
	var x = JSON.stringify(data);
	$.post(window.location.origin + "/users/save/",JSON.stringify({
		userCode: data.code,
		userPwd: data.password,
		status: "1",
		type : data.type,
		userPhone: data.phone,
		userEmail: data.email,
		fullname: data.name,
		address:data.address,
		rePassword: data.rePassword
	})).done(function(res){
		var x = res;
	});
}