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
		window.location.href = window.location.origin + "/logout";
	});
	$('#btnHome').click(function(){
		window.location.href = window.location.origin;
	});
})