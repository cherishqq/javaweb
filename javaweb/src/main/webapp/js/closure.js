/**
 * desc:  js closure  research  闭包
 * @author derek
 * @createtime: 2013-7-4 上午9:39:52 
 * @version  
 */

/**
 *  one 
 */

var scope = "global scope";
function checkscope(){
	var scope = "local scope";
	function f() { return scope;}
	return f();
}

alert(checkscope()());   // local scope




/**
 *  second
 */


function closuretest(){
	var a = 10;
	function f() { a= a*2; return a;}
	return f;
}

var d = new closuretest();
alert(d());   // 20
alert(d());  //  40

/**
* third  这里有形成闭包吗？？ no closure..
*/

var name = "The Window";   
var object = {   
		name : "My Object", 
		getNameFunc : function(){
			return function(){
				return this.name;
			}
			}
};   
alert(object.getNameFunc()());   // why the window


/**
*   four
*/

function outer(){
	var x = 5;
	function inner(){
		alert(x);
	}
	
	setTimeout(inner,2000);
}


/*
 *  five
 */

var foo = ( function() {
    var secret = 'secret';
    // “闭包”内的函数可以访问 secret 变量，而 secret 变量对于外部却是隐藏的
    return {
        get_secret: function () {
            // 通过定义的接口来访问 secret
            return secret;
        },
        new_secret: function ( new_secret ) {
            // 通过定义的接口来修改 secret
            secret = new_secret;
        }
    };
} () );

foo.get_secret (); // 得到 'secret'
foo.secret; // Type error，访问不能
foo.new_secret ('a new secret'); // 通过函数接口，我们访问并修改了 secret 变量
foo.get_secret (); // 得到 'a new secret'