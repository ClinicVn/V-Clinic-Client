
function EditControl(data){
	var self = this;
	var x = eval("(" + data + ')');
	self.user = ko.observable(new UserItem(data));
}

$(function(){
	var x = $('#user-info').text();
	ko.applyBindings(new EditControl($('#user-info').text()));
})