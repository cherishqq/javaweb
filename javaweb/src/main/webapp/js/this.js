
/**
 * one
 */
var name = "Kevin Yang";
function sayHi(){
    alert("你好，我的名字叫" + name);
 }
    sayHi();
    
/**
  *
  */
    var name = "Kevin Yang";
    function sayHi(){
       alert("你好，我的名字叫" + this.name);
    }
    sayHi();
    