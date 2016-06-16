$(function(){
	var $BtnLogin = $('#btnLogin');
	var $Username = $('#username');
	var $Password = $('#password');
	$BtnLogin.click(function(){
		var data = {};
		if(window.authToken == undefined)
			data.token = "none";
		else
			data.token = window.authToken;
		data.username = $Username.val();
		data.password = $Password.val();
		LoginService.SubmitLogin(data);
		return false;
	});
})
