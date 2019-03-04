$(document).ready(function() {
	delay();
});

function delay() {
	var secs = 1000;
	setTimeout('initFadeIn()', secs);
}

function initFadeIn() {
	$("body").css("visibility", "visible");
	$("body").css("display", "node");
	$("body").fadeIn(5000);
}