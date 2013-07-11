
/**
 *   one
 */

var Class = {
  create: function() {
    return function() {
      this.initialize.apply(this, arguments);
    }
  }
}
// is　class.create. return function .. no new Class().. 
var bb = Class.create();
bb.prototype = {
		init : function(){
			alert("dd");
		},
	 // must have initialize , otherwise throwss
	 // TypeError: Cannot call method 'apply' of undefined
		initialize : function(){
			alert("init");
		}
}

// bb is a function .. so need use 'new'
var cc = new bb();
cc.init();


// *********************************************************
/*
 * two  
 *  alert derek and undefined
 *  so need pay attention to the order of the method
 */

function Person(name,age){
	this.name= name;
	this.age = age;
}
Person.prototype.getName = function(){
	alert(this.name);
}

var p = new Person('derek',28);
alert(p.getName());
alert(p.getAge());

Person.prototype.getAge = function(){
	alert(this.age);
}

//*********************************************************
/*
 *  third  
 *  the means?
 */

var $A = Array.from = function(iterable) {
	  if (!iterable) return [];
	  if (iterable.toArray) {
	    return iterable.toArray();
	  } else {
	    var results = [];
	    for (var i = 0, length = iterable.length; i < length; i++)
	      results.push(iterable[i]);
	    return results;
	  }
	}

Function.prototype.bind = function() {
	  var __method = this, args = $A(arguments), object = args.shift();
	  return function() {
	    return __method.apply(object, args.concat($A(arguments)));
	  }	
	}
var obj = {
		name : 'A nice demo',
		fx : function() {
			alert(this.name);
		}
};



window.name = 'I am such a beautiful window';

obj.fx();  // alert A nice demo
function runFx(f){
	f();
}

var fx2 = obj.fx.bind(obj);

runFx(obj.fx);  //  I am such a beautiful window 
runFx(fx2);     //  A nice demo


/**
 * four
 */

function Person(name) {
	this.name = name;
}
var p = new Person();
console.log(p.__proto__ === Person.prototype); // true

console.log(Person.prototype.__proto__ === Object.prototype); // true

console.log( Person.__proto__ == Function.prototype);


