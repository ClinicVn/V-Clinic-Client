function ListUserService(domain){
	var self = this;
	this.domain = domain;
}

ListUserService.prototype.getListUser = function(){
	var self = this;
	return $.get(self.domain + '/users/list/data').then(function(res){
		var lstMenu = new Array();
		$.each(res,function(index, item){
			lstMenu.push({
				id: item.id,
				code: item.code,
				name: item.name,
				status: item.status,
				phone: item.phone,
				type: item.type
			});
		});
		return lstMenu;
	});
}