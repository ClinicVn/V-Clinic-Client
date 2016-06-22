$(function(){
	// event
	$('#lstLanguage').change(function(){
		
	});
	// validator
	var userValidator = new FormValidator('create-form',
	[
	 	{
	 		name: 'username',
	 		rules: 'required|min_length[5]|max_length[30]'
	 	},
	 	{
	 		name: 'password',
	 		rules: 'required|min_length[10]|max_length[30]'
	 	},
	 	{
	 		name: 'retype-password',
	 		rules: 'required|matches[password]'
	 	},
	 	{
	 		name: 'fullname',
	 		rules: 'required|min_length[5]'
	 	},
	 	{
	 		name: 'email',
	 		rules: 'required|valid_email'
	 	},
	 	{
	 		name:'phone',
	 		rules: 'required|integer'
	 	}
	],function(errors, event){
		$('.error').remove();
		if(errors.length === 0){
			var info = {
				username: $('#username').val(),
			    password: $('#password').val(),
			    retype_password: $('#retype-password').val(),
			    fullname: $('#fullname').val(),
			    address:  $('#address').val(),
			    email: $('#email').val(),
			    phone: $('#phone').val(),
			    type: $('#type').val()
			}
			$.post('/users/save/',info,function(res){
				if(res === ""){
					window.location.href = "localhost:9000/users/list";
				}
				else{
					alert(res);
					window.location.href = "localhost:9000/users/list";
				}
			})
		}
		else{
			$.each(errors, function(index, item){
				var $LblError = $('<label></label>');
				$LblError.addClass('error');
				$LblError.text(item.message);
				$(item.element).after($LblError);
			});
		}
		return false;
	});
})