function ListUserService(domain){
	var self = this;
	this.domain = domain;
}

ListUserService.prototype.getListUser = function(){
	var self = this;
	return $.get(self.domain + '/users/list/data').then(function(res){
		if(res === false)
			return false;
		var lstMenu = new Array();
		$.each(res,function(index, item){
			lstMenu.push({
				id: item.gid,
				code: item.userCode,
				name: item.fullname,
				status: item.status,
				phone: item.userPhone,
				type: item.type,
				email: item.userEmail,
				address: item.address
			});
		});
		return lstMenu;
	});
}