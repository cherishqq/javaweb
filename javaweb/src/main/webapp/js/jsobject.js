/**
 * 
 * desc:  js object research
 * @author derek
 * @createtime: 2013-7-4 上午9:39:52 
 * @version  
 *
 * @param <K>
 * @param <V>
 */

/**
 *  one 
 */


function Circle(r) {	
	this.r = r;
}

Circle.PI = 3.14159;
/*Circle.prototype.area = function() {
	return Circle.PI * this.r * this.r;
}*/


 
 // after define prototype.are , if write prototype = {} .. the method area will not define
Circle.prototype = {
		
		getValue : function(){
			return this._bb;
		},
		setValue : function(bb) {
			this._bb = bb;
		}
}
var c = new Circle(1.0);
/*
alert( " first value:" + c.area());

Circle.PI = 4;
alert( " second value: " + c.area());*/



c.setValue('dd');

alert( c.getValue());
alert( c._bb);



/**
 * two 
 * @param newIsbn
 * @param newTitle
 * @param newAuthor
 * @returns
 */

var Book = function( newIsbn, newTitle, newAuthor){
    // Private attributes
    var isbn,title,author;
    // private method
    function checkIsbn( isbn) {
    	alert(isbn);
    };
    
    isbn = newIsbn;
    
    // public method, privileged methods..
    this.getIsbn = function(){
    	// must write isbn, no this.isbn .. this.isbn == undefined
    	return isbn;
    };
    
/*   
 *   will return undefined  
     this.getIsbn = function() {
    	return this.isbn;
    }*/
    
/*    this.prototype.display = function(){
    	alert( this.isbn);
    }*/
}

// Public , non-privileged methods..
Book.prototype = {
		display2 : function() {
			
		}
}

var book = new Book('1111','Android','derek');
 alert(book.getIsbn());
 //book.display();
 
 var Person = {
		 name : 'derek',
		 show : function(){
			 alert(this.name);  // must use this.name// no name..
		 }
 }
 
Person.show();
 
 
 
