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
	$('.user-info-input').removeClass('error-field');
	var self = this;
	UserInfoService.create(self.insUser()).done(function(res){
		if(res.length == 0){
			window.location.href = window.location.origin + "/users/list";
		}else{
			$.each(res, function(index, errItem){
				$('#'+ errItem.clientId).addClass('error-field');
			});
		}
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