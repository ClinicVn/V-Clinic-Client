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
		$.each(errors, function(index, item){
			var $LblError = $('<label></label>');
			$LblError.addClass('error');
			$LblError.text(item.message);
			$(item.element).after($LblError);
		});
	});
})