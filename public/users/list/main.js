function listView(){
	var self = this;
	self.services = new ListUserService('');
	self.isReady = ko.observable(false);
	
	self.lstUser = ko.observableArray([]);
	self.init();
}

listView.prototype.init = function(){
	var self = this;
	self.services.getListUser().done(function(res){
		$.each(res, function(i, user){
			self.lstUser.push(new UserItem(user));
		});
		self.isReady(true);
		$.each(self.lstUser(),function(i, user){
			$('#'+user.code()+'-options-button').toolbar({
				content: '#' +user.code()+'-options',
				position: 'left',
				event: 'click',
				hideOnClick: true
			});
		});
	});
}

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
	
}
UserItem.prototype.Info = function(){
	
}
UserItem.prototype.Delete = function(){
	
}

$(function(){
	$('#testTool').toolbar({
		content: '#tool-bar-options',
		position: 'bottom',
		style: 'primary',
		event: 'click',
		hideOnClick: true
	});
	//Activates knockout.js
	ko.applyBindings(new listView());
})