/**
 *  the different  one
*/

function Person(name){
	this.name = name;
	this.getName = function(){
		return this.name;
	}
}

function Person(name){
	this.name = name;
}

/** it's wrong ..  function can't be .... only the object..  change to Person.prototype.
Person.getName = function(){
	return this.name;
}

**/ 
Person.prototype.getName = function(){
	return this.name;
}


var p = new Person('derek');
alert( p.getName());

// ***********************************

var p = function(name){
	this.name = name;
}

// *****************  two 

( function() {
	var foo = 10;
	var bar =2;
	alert( foo * bar);
})();


( function(foo, bar) {
	alert( foo * bar);
})(10,2);


var baz = ( function(foo, bar) {
	alert( foo * bar);
})(10,2);

// *************** third **************************
/**
 * Execution is not the sentence by sentence 
 * is section by section
 */

function myfunc(){
	alert("my name is nick")
}

myfunc();  // result my name is derek

function myfunc(){
	alert("my name is derek");
}

myfunc(); // result my name is derek