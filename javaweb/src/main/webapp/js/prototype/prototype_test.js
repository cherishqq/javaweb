
/**
  $ 使用
**/


function test1()
    {
        var d = $('myDiv');
        alert(d.innerHTML);
    }

function test2()
    {
        var divs = $('myDiv','myOtherDiv');
        for(i=0; i<divs.length; i++)
        {
            alert(divs[i].innerHTML);
        }
    }
	
/**
 $F 使用
**/


function test3()
{
    alert(  $F('userName')  );
}


/**
  $A 使用 接收到的参数转换成一个Array对象
*/

   function showOptions(){
        var someNodeList = $('lstEmployees').getElementsByTagName('option');
        var nodes = $A(someNodeList);

        nodes.each(function(node){
                alert(node.nodeName + ': ' + node.innerHTML);
            });
    }

/**
$H() 方法把对象转化成可枚举的貌似联合数组Hash对象
**/	

function testa(){
var a = {
            first: 10,
            second: 20,
            third: 30
            };

        //now transform it into a hash
        var h = $H(a);
        alert(h.toQueryString()); //displays: 

}

/**
  测试 class
**/

function test_class(){
   //declaring the class
var MySampleClass = Class.create();
//defining the rest of the class implmentation
MySampleClass.prototype = {

   initialize: function(message) {
        this.message = message;
   },

   showMessage: function(ajaxResponse) {
      alert(this.message);
   }
};

//now, let's instantiate and use one object
var myTalker = new MySampleClass('hi there.');
myTalker.showMessage(); //displays alert

}

/**
  测试一下gpms 上的一个大量的用法
  为什么create 的时候找不到 initialize 不会报错。。
  还是只有new 的时候,才会报错。。 new 和没有new 的差别。。
  可以先看下 new 对象都做了什么。。
**/

var obj = {
	create : function(){
	 alert("2");
	 return this.initialize.apply(this,arguments);
	}
}

var sb = Class.create();
sb.onTabPage = function(o,oo,ooo){
	 alert("hehe");
	 // **
	 // 通过这个示例,发现 会弹出 alert("2") 就是create 里面的内容会被执行
	 var cc = obj.create();
	 cc.prototype = {
	    initialize: function(message) {
		alert("initialize");
        this.message = message;
		}
	 }
  }

function test_class_gpms(){
  // var sb1 = new sb();
  sb.onTabPage();
}




	
	
