
// use text().. no html..
function p( value){
	
	var cc= $("span").text(value);
	
	//alert(cc.html());
	//$("#result").append("<span>").text(value).append("</span>");
	
	$("<div>").text(value).appendTo($("#result"));
}
