$(function() {
	// event
	$('#lstLanguage').change(function() {

	});
	// validator
	var userValidator = new FormValidator('create-form', [ {
		name : 'code',
		rules : 'required|min_length[5]|max_length[30]'
	}, {
		name : 'password',
		rules : 'required|min_length[9]|max_length[30]'
	}, {
		name : 'retype-password',
		rules : 'required|matches[password]'
	}, {
		name : 'name',
		rules : 'min_length[5]'
	}, {
		name : 'email',
		rules : 'valid_email'
	}, {
		name : 'phone_number',
		rules : 'integer'
	} ], function(errors, event) {
		$('.error').remove();
		$.each(errors, function(index, item) {
			var $LblError = $('<label></label>');
			$LblError.addClass('error');
			$LblError.text(item.message);
			$(item.element).after($LblError);
		});
		return false;
	});
})