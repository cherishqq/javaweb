(function(window) {
	
/*	if (typeof console === 'undefined') {
		window.console = {
				
		}
		
	}*/
	
	var console = function() {
		this.debug = true;
		this._init ();
	}
	
	function isString( val) {
		return typeof val === 'string';
	}
	
	function isBool(val) {
		return typeof val === 'boolean';
	}
	
	function isObj( obj) {
		 if( obj === null || typeof obj === 'undefined'){
			 return false;
		 }
		 return typeof obj === 'object';
	}
	
	function isArray( array) {
		return Object.prototype.toString.apply(array) === '[object Array]';
	}
	
	function isFunction( f ) {
		return Object.prototype.toString.apply(f) === '[object Function]';
	}
	
	
	console.prototype = {
			
			render : function(obj) {
				
				var result = '';
				var objType = this.argType(obj);
				
				switch (objType) {
					case 'boolean' :
						result += this.renderBoolean(obj);
				}
				
			},
	
			renderBoolean : function(bool) {
				 return bool ? 'true' : 'false';
			}
			
	}
	
})(this);