function object_type() {

var kind = Object.prototype.toString.call(arg).slice(8, -1);
    switch (kind) {
      case 'String':
        return 'String "' + arg +'"';

      case 'Number':
      case 'Boolean':
        return kind + ' ' + arg;

      case 'Array':
      case 'HTMLCollection':
      case 'NodeList':
        // Is array-like object?
        result = kind == 'Array' ? '[' : kind + ' [';
        var arr_list = [];
        for (var j=0, jj=arg.length; j<jj; j++) {
          arr_list[j] = inspect(arg[j], true);
        }
        return result + arr_list.join(', ') +']';

      case 'Function':
      case 'Date':
        return arg;

      case 'RegExp':
        return "/"+ arg.source +"/";

      default:
        if (typeof arg === 'object') {
          var prefix;
          if (kind == 'Object') {
            prefix = '';
          } else {
            prefix = kind + ' ';
          }
          if (within) {
            return prefix + '{?}';
          }
          if (Object.getOwnPropertyNames) {
            var keys = Object.getOwnPropertyNames(arg);
          } else {
            keys = [];
            for (var key in arg) {
              if (arg.hasOwnProperty(key)) {
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
              var value = inspect(arg[key], true);
              properties.push(key +': '+ value);
            } catch (e) {}
          }
          return result + properties.join(', ') +'}';
        } else {
          return arg;
        }
    }
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