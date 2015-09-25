function lock() {
	if($('.overlay').length==0)
	{
		$(document.body).append('<div class="overlay" ></div>');
	}
	
	if($('.clover').length==0)
	{
		$(document.body).append('<div class="clover"></div>');
	}
	
	$(".overlay").fadeIn("fast");
	$(".clover").fadeIn("fast");
}

function unlock() 
{
	$(".overlay").fadeOut("slow");
	$(".clover").fadeOut("slow");
}