

function test1(){
	eval("alert(1+1)");
}

function test2(){
    // 说明在一个作用域下
	eval("var i=3");
	alert(i);
}

/**
 测试作用域。。
 可以访问外部变量
**/

var oo = {};
var cc = "4";
oo.some = function(){
	alert(cc);
}



function test3(){
	
	eval("oo.some()");
}