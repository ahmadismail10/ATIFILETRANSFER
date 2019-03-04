$( document ).ready(function() {
	if($("#toggle-trigger").prop('checked') == true){
		$('#toggle-trigger').bootstrapToggle('on')  
    } else {
    	$('#toggle-trigger').bootstrapToggle('off')  
    }
});

function getKey(e){
	if(window.event)
		return window.event.keyCode;
	else if(e)
		return e.which;
	else 
		return null;
}
		
function restrictChars(e, validList){
	key=getKey(e);
	if(key==null) return true;
	keyChar=String.fromCharCode(key).toLowerCase();
	if(validList.toLowerCase().indexOf(keyChar)!=-1) return true;
	if(key==0 || key==8 || key==9 || key==13 || key==27) return true; return false;
}
		
function numericOnly(e){
	return restrictChars(e,"0123456789");	
}
		
function toggleOff() {
	$('#toggle-trigger').bootstrapToggle('off')  
}

