// Horse -> Mammal -> Animal  one

function Animal(){
	
}

Animal.prototype = {
		name : "anmimal",
		weight : 0,
		eat : function() {
			alert(" Animal is eating！");
		}
}

function Mammal() {
	this.name = "mamml";
}

Mammal.prototype = new Animal();

function Horse( height ,weight ){
	this.name = "horse";
	this.height = height;
	this.weight = weight;
}

/**
 *  if Horse.prototype = new Mammal(); 
 *  how to set Horse.prototype = {} ？？ so ... Horse.prototype.xxx = function(){}
 */
Horse.prototype = new Mammal();


// overvide
// if not prototype.. 
/**
 * Horse.eat = function() { alert(" Horse is eating grass"); }
 * then : alert(horse.eat() ); ?? 
 */
Horse.prototype.eat = function() {
	alert(" Horse is eating grass");
}

var horse = new Horse(100,300);

// __proto__ is belong to Object and prototype is belong to Object.. and prototype is a object..
// so prototype chain is ??  horse.__proto__ -> Horse.prototype..--> Horse.prototype.__proto__  --> Mammal.prototype -->
// Mammal.prototype.__proto__ === Animal.prototype

/**
alert( horse.__proto__ === Horse.prototype);  // true
alert( Horse.prototype.__proto__ === Mammal.prototype);  // true
alert( Mammal.prototype.__proto__ === Animal.prototype);  // true
**/

horse.eat();  //  Horse is eating grass
//  
alert( horse.name);  // horse

//******************************************************************
/**
 *  second  use call
 */

function Parent( username) {
	 this.username = username;
	
	  this.hello = function(){
		  alert(this.username);
	  }
}

function Child(username, password){
	
	Parent.call(this, username);
	this.password = password;
	this.world = function() {
		alert(this.password);
	}
}

var parent = new Parent("zhangsan");
var child = new Child("lisi","123456");
parent.hello();
child.hello();
child.world();

// ***********************************************************************
/**
 *   three call + prototype
 */

function Parent(hello) {
	this.hello = hello;
}

Parent.prototype.sayHello = function(){
	alert(this.hello);
}

function Child( hello, world) {
	Parent.call(this, hello); // extend attribute
	this.world = world;
}

Child.prototype = new Parent(); // extend method.

Child.prototype.sayWorld = function() {
	alert(this.world);
}

var c = new Child("zhangsan","lisi");
c.sayHello();
c.sayWorld();

