function master(){	
}
master.prototype.visiableTop = function(isVisiable){
	if(isVisiable === true)
		$('#panel-top').addClass('hideTop');
	else if(isVisiable === true)
		$('#panel-top').removeClass('hideTop');
}
$(function(){
	$('#btnLogout').click(function(){
		$.get('http://localhost:9000/logout').done(function(res){
			window.location.href = "http://localhost:9000/";
		});
	});
})