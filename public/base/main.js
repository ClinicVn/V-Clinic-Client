function master(){	
}
master.prototype.visiableTop = function(isVisiable){
	if(isVisiable === true)
		$('#panel-top').addClass('hideTop');
	else if(isVisiable === true)
		$('#panel-top').removeClass('hideTop');
}