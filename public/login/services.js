var LoginService = {};
LoginService.SubmitLogin = function(input){
	$.post("/users/login/",{
		username: input.username,
		password: input.password
	},function(res, status){
		if(res === true)
			window.location.reload();
		else
			{
				$('#msgError').text("Invalid account");
			}
		//window.location.href = "/home";
	});
}