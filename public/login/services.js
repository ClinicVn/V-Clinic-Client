var LoginService = {};
LoginService.SubmitLogin = function(input){
	$.post("/users/login/",{
		userCode: input.username,
		userPwd: input.password
	},function(res, status){
		if(res === true)
			window.location.reload();
		else
			{
				$('#msgError').text("Invalid account");
			}
	});
}