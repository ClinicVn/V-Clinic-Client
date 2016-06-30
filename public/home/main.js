function HomeView(){
	var self = this;
	this.services = new Service('');
	this.masterPage = new master();
	self.searchValue = ko.observable('');
	self.searchValue.subscribe(function(){
		self.search();
	});
	self.isReady = ko.observable(false);
	self.lstMenu = ko.observableArray([]);
	self.lstSearchMenu = ko.observableArray([]);
	self.init();
}
HomeView.prototype.search = function(){
	var self = this;
	$.each(self.lstMenu(),function(i, menu){
		if(menu.title().toLowerCase().indexOf(self.searchValue().toLowerCase()) >= 0)
			menu.visiable(true);
		else
			menu.visiable(false);
	})
}

HomeView.prototype.clearSearch = function(){
	var self= this;
	self.searchValue('');
}

HomeView.prototype.init = function(){
	var self = this;
	this.services.getListMenu().done(function(res){
		$.each(res, function(index, item){
			var isDisplay = false;
			if(item.title.indexOf(self.searchValue()) >= 0 )
				isDisplay = true;
			self.lstMenu.push(new MenuItem({
				img: item.img,
				url: item.url,
				title:item.title,
				id: item.id,
				display: isDisplay
			}));
		})
		self.isReady(true);
	});
	// 
	//self.masterPage.visiableTop(true);
}
function MenuItem(data){
	var self = this;
	this.id = data.id;
	this.img = ko.observable('url("' + data.img + '")');
	this.url = ko.observable(data.url);
	this.title = ko.observable(data.title);
	this.visiable = ko.observable(data.display);
}
MenuItem.prototype.onClick = function(){
	var self = this;
	window.location.href = self.url(); 
}
$(function(){
	$(window).resize(function(){
		absoluteHeight();
	});
	absoluteHeight();
	function absoluteHeight(){
		var item = $('.menu-item');
		item.height(item.width());
	}
	//Activates knockout.js
	ko.applyBindings(new HomeView());
});