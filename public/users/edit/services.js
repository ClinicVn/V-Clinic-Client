var EditService = {};
EditService.getUserByCode = function(code){
	$.get(window.origin + "/user/" + code).then(function(res){
		return {
			id: res.id,
			code: code,
			name: res.name,
			type: res.type,
			address: res.adress,
			email: res.email,
			phone: res.phone
		}
	});
}