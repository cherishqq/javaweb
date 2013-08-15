
// use text().. no html..
function p( value){
	
	var cc= $("span").text(value);
	
	//alert(cc.html());
	//$("#result").append("<span>").text(value).append("</span>");
	
	$("<div>").text(value).appendTo($("#result"));
}
function StringBuffer() {
	this._Strings = [];
}

StringBuffer.prototype.append = function(str){
	this._Strings.push(str);
}
StringBuffer.prototype.toString() = function() {
	return this._Strings.join("");
}


