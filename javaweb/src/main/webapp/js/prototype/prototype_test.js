
/**
  $ ʹ��
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
 $F ʹ��
**/


function test3()
{
    alert(  $F('userName')  );
}


/**
  $A ʹ�� ���յ��Ĳ���ת����һ��Array����
*/

   function showOptions(){
        var someNodeList = $('lstEmployees').getElementsByTagName('option');
        var nodes = $A(someNodeList);

        nodes.each(function(node){
                alert(node.nodeName + ': ' + node.innerHTML);
            });
    }

/**
$H() �����Ѷ���ת���ɿ�ö�ٵ�ò����������Hash����
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
  ���� class
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
  ����һ��gpms �ϵ�һ���������÷�
  Ϊʲôcreate ��ʱ���Ҳ��� initialize ���ᱨ����
  ����ֻ��new ��ʱ��,�Żᱨ���� new ��û��new �Ĳ�𡣡�
  �����ȿ��� new ��������ʲô����
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
	 // ͨ�����ʾ��,���� �ᵯ�� alert("2") ����create ��������ݻᱻִ��
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




	
	
