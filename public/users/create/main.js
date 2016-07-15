function UserInfoScreen(user){
	var self = this;
	self.isChangePassword = ko.observable(false);
	if($('#info-mode').text() === "Create")
		self.mode = InfoMode.Create;
	else
		self.mode = Info.Edit;
	self.insUser = ko.observable(new UserItem(user));
}
UserInfoScreen.prototype.save = function(){
	var self = this;
	var saveData = {};
	saveData.code = self.insUser().code();
	if(self.mode === InfoMode.Edit)
	{
		saveData.oldPassword = self.insUser().oldPassword();
	}
	saveData.password = self.insUser().password();
	saveData.rePassword = self.insUser().rePassword();
	saveData.name = self.insUser().name();
	saveData.email = self.insUser().email();
	saveData.phone = self.insUser().phone();
	saveData.type = self.insUser().type();
	saveData.address = self.insUser().address();
	UserInfoService.save({
		data: JSON.stringify(saveData),
		mode: self.mode
	});
}
var InfoMode = {
	Create: 1,
	Edit: 2
};
$(function() {
	ko.applyBindings(new UserInfoScreen({
		code: "",
		oldPassword: "",
		password: "",
		rePassword: "",
		name: "",
		status: "",
		phone: "",
		type: "",
		email: "",
		address: "",
		isEdit : false
	}));
})