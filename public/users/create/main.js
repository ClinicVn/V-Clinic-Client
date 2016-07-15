function UserInfoScreen(user){
	var self = this;
	if($('#info-mode').text() === "Create")
		self.mode = InfoMode.Create;
	else
		self.mode = Info.Edit;
	self.insUser = ko.observable(new UserItem(user));
}
UserInfoScreen.prototype.save = function(){
	var self = this;
	var saveData = {};
	saveData.code = self.code();
	if(self.mode === InfoMode.Edit)
	{
		saveData.oldPassword = self.oldPassword();
	}
	saveData.password = self.password();
	saveData.rePassword = self.rePassword();
	saveData.name = self.name();
	saveData.email = self.mail();
	saveData.phone = self.phone();
	saveData.type = self.type();
	saveData.address = self.address();
	UserInfoService.save({
		user: saveData,
		mode: self.mode
	});
}
var InfoMode = {
	Create: 1,
	Edit: 2
};
$(function() {
	
	/*// event
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
		rules : 'required|min_length[5]'
	}, {
		name : 'email',
		rules : 'required|valid_email'
	}, {
		name : 'phone_number',
		rules : 'required|integer'
	} ], function(errors, event) {
		$('.error').remove();
		$.each(errors, function(index, item) {
			var $LblError = $('<label></label>');
			$LblError.addClass('error');
			$LblError.text(item.message);
			$(item.element).after($LblError);
		});
		return false;
	});*/
})