var UserInfoService = {};
UserInfoService.save = function(data){
	var x = JSON.stringify(data);
	$.post(window.location.origin + "/users/save/",data).done(function(res){
		var x = res;
	});
}