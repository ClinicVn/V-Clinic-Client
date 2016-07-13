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
		if(res === false)
			window.location.href = window.origin;
		else{
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
		}
	});
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
	$('#btnNewUser').click(function(){
		window.location.href = "/users/create";
	});
})