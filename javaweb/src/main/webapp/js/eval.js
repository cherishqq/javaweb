

function test1(){
	eval("alert(1+1)");
}

function test2(){
    // 说明在一个作用域下
	eval("var i=3");
	alert(i);
}