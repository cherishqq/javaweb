

function test1(){
	eval("alert(1+1)");
}

function test2(){
    // ˵����һ����������
	eval("var i=3");
	alert(i);
}

/**
 ���������򡣡�
 ���Է����ⲿ����
**/

var oo = {};
var cc = "4";
oo.some = function(){
	alert(cc);
}



function test3(){
	
	eval("oo.some()");
}