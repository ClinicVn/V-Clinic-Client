function UserInfoScreen(user) {
	var self = this;
	self.isChangePassword = ko.observable(false);
	self.initData = eval('(' + $('#info-init-data').text() + ')');
	self.mode = ko.observable(self.initData.mode);
	self.isEdit = ko.computed(function() {
		return self.mode() === 2;
	}, self);
	self.isCreate = ko.computed(function() {
		return self.mode() === 1;
	}, self);
	if (self.mode() === 2) {
		var data = JSON.parse(self.initData.user);
		user = {
			id: data.gid,
			code : data.userCode,
			oldPassword : "",
			password : "",
			rePassword : "",
			name : data.fullname,
			status : data.status,
			phone : data.userPhone,
			type : data.type,
			email : data.userEmail,
			address : data.address,
			isEdit : true
		}
	}
	self.insUser = ko.observable(new UserItem(user));
}
UserInfoScreen.prototype.save = function() {
	$('.user-info-input').removeClass('error-field');
	var self = this;
	UserInfoService.save(self.mode(),self.insUser()).done(function(res) {
		if (res.length == 0) {
			window.location.href = window.location.origin + "/users/list";
		} else {
			$.each(res, function(index, errItem) {
				$('#' + errItem.clientId).addClass('error-field');
			});
		}
	});
}
var InfoMode = {
	Create : 1,
	Edit : 2
};
$(function() {
	ko.applyBindings(new UserInfoScreen({
		code : "",
		oldPassword : "",
		password : "",
		rePassword : "",
		name : "",
		status : "",
		phone : "",
		type : "",
		email : "",
		address : "",
		isEdit : false
	}));
})