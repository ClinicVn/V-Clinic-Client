function Service(domain){
	var self = this;
	this.domain = domain;
}

Service.prototype.getListMenu = function(){
	var self = this;
	var url = self.domain + '/home/menu';
	return $.get(self.domain + '/home/menu').done(function(res){
		var lstMenu = new Array();
		$.each(res,function(index, item){
			lstMenu.push({
				id: item.id,
				title: item.title,
				url: item.url,
				img: item.img
			});
		});
		return lstMenu;
	});
}