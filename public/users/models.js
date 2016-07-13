function UserItem(data){
	var self = this;
	self.id = ko.observable(data.id);
	self.code = ko.observable(data.code);
	self.name = ko.observable(data.name);
	self.status = ko.observable(data.status);
	self.phone = ko.observable(data.phone);
	self.type = ko.observable(data.type);
}
UserItem.prototype.Edit = function(){
	var x = window.location.origin + this.id();
	window.location.href = window.location.origin + "/users/edit/" + this.id();
}
UserItem.prototype.Info = function(){
	
}
UserItem.prototype.Delete = function(){
	
}