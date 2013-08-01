(function(window) {
	
/*	if (typeof console === 'undefined') {
		window.console = {
				
		}
		
	}*/
	
	 function $$(id) {
		 	return document.getElementById(id);
	 }
	
	 function createEl(elem, attrs) {
		  var el = document.createElement(elem);
		   attrs = attrs || {};
		   for( var attr in attrs){
			   if (attrs.hasOwnProperty(attr)){ // work only with direct (non-inherited) properties
				   el.setAttribute(attr,attrs[attr]);
			   }
		   }
		   return el;
	 }
	
	var ConsoleUI = function() {
		this.init();
		this.bind();
	}
	
	ConsoleUI.prototype = {
			
			init : function() {
				
				var consolePanel = createEl('div',{'class':'console-panel'});
				var consoleArea = createEl('div',{'style':'position:relative;width:36px;'});
				this.consoleButton = createEl('a',{'href':'#', 'class':'item',title:'console'});
				this.consoleShowArea = createEl('div',{id:'result','class':'console-border',style:'top:20px;width:680px;height:230px;opacity:1;display:none;'});
				var consoleBox = createEl('div',{'class':'console-panel-box'});
				var consoleResultArea = createEl('div',{id:'console-result-area','class':'console-log'});
				consoleBox.appendChild(consoleResultArea);
				this.consoleShowArea.appendChild(consoleBox);
				consoleArea.appendChild(this.consoleButton);
				consoleArea.appendChild(this.consoleShowArea);
				consolePanel.appendChild(consoleArea);
				document.getElementsByTagName('body')[0].appendChild(consolePanel);
				
			},
			bind : function(){
				this.consoleButton.onclick = this.showConsole;
			},
			render : function() {
				
			},
			createResultRow : function( val ) {
				var e = createEl("div", {'class':'console-log-line console-log-line-log'});
				e.innerText = val;
				$$('console-result-area').appendChild(e);
			},
			onInput : function(){
				$$('console-input-area').onkeydown = function(){
					
					if( event.keyCode == 13){
						alert('Enter');
					}
				 }
			},
			showConsole : function(){
				if( this.；.style.display == 'none'){
					consoleShowArea.style.display = 'block';
				}
			}
			
			
	}
	
	
	window.consoleUI= new ConsoleUI();
	
	var Console = function() {
		this.debug = true;
	//	this._init ();
	}
	
	Console.prototype = {
			
			render : function(obj,within) {
				
				 if (Object(obj) !== obj) {
				      if (within && typeof obj == 'string') {
				        return '"' + obj + '"';
				      }
				      return obj;
				  }
				 
				 if( obj && obj.nodeType == 1) {
					 result = '<' + obj.tagName;
					 for( var i=0, ii= obj.attributes.length; i<ii; i++){
						//  if( obj.attributes[i].specified )
						 result += ' ' + obj.attributes[i].name  + ' =" ' + obj.attributes[i].value + ' "';
					 }
					 
					 if (obj.childNodes && obj.childNodes.length === 0 ) {
						 result += '/';
					 } 
					 return result + '>';
				 }
				
				var objType = Object.prototype.toString.call(obj).slice(8, -1);
			    switch (objType) {
			      case 'String':
			        return 'String "' + obj +'"';

			      case 'Number':
			      case 'Boolean':
			        return objType + ' ' + obj;

			      case 'Array':
			      case 'HTMLCollection':
			      case 'NodeList':
			        // Is array-like object?
			        result = objType == 'Array' ? '[' : objType + ' [';
			        var arr_list = [];
			        for (var j=0, jj=obj.length; j<jj; j++) {
			          arr_list[j] = render(obj[j], true);
			        }
			        return result + arr_list.join(', ') +']';

			      case 'Function':
			      case 'Date':
			        return obj;

			      case 'RegExp':
			        return "/"+ obj.source +"/";

			      default:
			        if (typeof obj === 'object') {
			          var prefix;
			          if (objType == 'Object') {
			            prefix = '';
			          } else {
			            prefix = objType + ' ';
			          }
			          if (within) {
			            return prefix + '{?}';
			          }
			          if (Object.getOwnPropertyNames) {
			            var keys = Object.getOwnPropertyNames(obj);
			          } else {
			            keys = [];
			            for (var key in obj) {
			              if (obj.hasOwnProperty(key)) {
			                keys.push(key);
			              }
			            }
			          }
			          result = prefix + '{';
			          if (!keys.length) {
			            return result + "}";
			          }
			          keys = keys.sort();
			          var properties = [];
			          for (var n=0, nn=keys.length; n<nn; n++) {
			            key = keys[n];
			            try {
			              var value = this.render(obj[key], true);
			              properties.push(key +': '+ value);
			            } catch (e) {}
			          }
			          return result + properties.join(', ') +'}';
			        } else {
			          return obj;
			        }
			    }
			},
			log : function() {
				
				try {
				//	alert( this.render( arguments[0]));//
					consoleUI.createResultRow( this.render( arguments[0]) );
				} catch (e) {
					
				}
				
			}
	}
	
	window.consoleIE = new Console();
})(this);