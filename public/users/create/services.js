var UserInfoService = {};
UserInfoService.save = function(data){
	$.post(window.location.origin + "/users/save/",data).done(function(){
		
	});
}