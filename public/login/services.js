var LoginService = {};
LoginService.SubmitLogin = function(input){
	$.post("/users/login/",{
		username: input.username,
		password: input.password,
		token: input.token
	},function(res, status){
		window.location.href = "/home";
	});
}