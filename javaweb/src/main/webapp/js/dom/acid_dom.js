(function(window,document,undefined){
	' use strict';
	
	 var delegatedEvents = [];
	 
	 function consoleShim(){
		 if( typeof window.console === 'undefined' ){
			 var console = {};
			 console.log = console.error = console.warn = console.dir = function() {};
		 }
		 
	 }
	 
	 
	 function nodeTypesShim() {
		 if( !window.Node) {
			  return {
				     ELEMENT_NODE : 1,
				     ATTRIBUTE_NODE : 2,
				     CDATA_SECTION_NODE : 4,
				     ENTITY_REFERENCE_NODE : 5,
				     ENTITY_NODE : 6,
				     PROCESSING_INSTRUCTION_NODE :  7,
					COMMENT_NODE                :  8,
					DOCUMENT_NODE               :  9,
					DOCUMENT_TYPE_NODE          : 10,
					DOCUMENT_FRAGMENT_NODE      : 11,
					NOTATION_NODE               : 12
			  };
		 }
	 }
	 
	 function addEvent(elem, evt ,fn , capture) {
		  if ( typeof elem !== 'object') {
			   throw " addEvent : expected argument elem of type object," + typeof elem + "given."; 
		  }
		  
		  if( window.addEventListener) {
			   if( !capture) {
				     capture = false;
			   }
			   elem.addEventListener(evt, fn,capture);
		  } else {
			  elem.attachEvent('on' + event, fn);
		  }
	 }
	 
	 // attrs maybe id ..class 
	 // create element wrapper---allows to set attributes using the config object
	 function newElement(elem, attrs) {
		  var el = document.createElement(elem);
		   attrs = attrs || {};
		   for( var attr in attrs){
			   if (attrs.hasOwnProperty(attr)){ // work only with direct (non-inherited) properties
				   el.setAttribute(attr,attrs[attr]);
			   }
		   }
		   return el;
	 }
	 
	 function addClass(elem,cls) {
		 if (typeof elem !== 'object') {
			 throw " addClass : expected argument elem of type object," + typeof elem + "given."; 
		 }
		 
		 // normalize to array 
		 if ( typeof cls === 'string') {
			  cls = [cls];
		 }
		 
	 }
	
	
	
	
})();