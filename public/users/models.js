function UserItem(data){
	var self = this;
	self.id = ko.observable(data.id);
	self.code = ko.observable(data.code);
	self.oldPassword = ko.observable(data.oldPassword);
	self.password = ko.observable(data.password);
	self.rePassword = ko.observable(data.rePassword);
	self.name = ko.observable(data.name);
	self.status = ko.observable(data.status);
	self.phone = ko.observable(data.phone);
	self.type = ko.observable(data.type);
	self.email = ko.observable(data.email);
	self.address = ko.observable(data.address);
	self.isEdit = ko.observable(data.isEdit);
}
UserItem.prototype.Edit = function(){
	
}
UserItem.prototype.Info = function(){
	
}
UserItem.prototype.Delete = function(){
	var result = confirm("Are you sure ?");
	if(result === false)
		return;
	var self = this;
	$.ajax({
		url: window.location.origin + "/users/delete/" + self.id(),
		type: 'DELETE',
		success: function(res){
			if(res === true){
				alert("Deleted !");
				window.location.href = window.location.origin + "/users/list";
			}
			else{
				alert("Has an error !");
				window.location.href = window.location.origin + "/users/list";
			}
				
		}
	});
}