/*!
 * Jp javascript Library 1.0.0
 * Copyright(c) 2010 Jp ja, Inc.
 * licensing@ganjp.org
 * http://www.ganjp.org/license
 */

/**
 * @class Jp 具有核心工具和功能
 * @test html: pages/ganjp/jpframework/html/jpjs.html
 * @suffix naming rules: object(Obj), {}(Config), [](Arr), String(Str), function(Fn), boolean(Bool), 混合型(Mix) 
 * @prefix naming rules: Parameter(a)
 * @singleton
 * @since 1.0.0
 * @author gan jianping
 */
Jp = {
  version: '1.0.0',
  author: 'ganjianping',
  versionDetail: {
    major: 1,
    minor: 0,
    patch: 0
  }
};

/**
 * Copies all the properties of config to obj.
 * @param {Object} aReceiverObj The receiver of the properties
 * @param {Object} aSourceConfig The source of the properties
 * @param {Object} aDefaultConfig defaults A different object that will also be applied for default values
 * @return {Object} returns obj
 * @since 1.0.0
 * @author 甘剑平
 */
Jp.apply = function(aReceiverObj, aSourceConfig, aDefaultConfig){
  if (aDefaultConfig) {
    Jp.apply(aReceiverObj, aDefaultConfig);
  }
  if (aReceiverObj && aSourceConfig && typeof aSourceConfig == 'object') {
    for (var key in aSourceConfig) {
      aReceiverObj[key] = aSourceConfig[key];
    }
  }
  return aReceiverObj;
};

/**
 * Jp的方法扩展
 * 
 * @since 1.0.0
 * @author 甘剑平
 */
(function(){
  var toString = Object.prototype.toString,
    userAgent = navigator.userAgent.toLowerCase(),
    checkBrower = function(aStr){
      return aStr.test(userAgent);
    },
    DOC = document,
    //只有IE支持创建ActiveX控件(ActiveXObject函数)
    isIE = !isOpera && checkBrower(/msie/),
    isIE5 = isIE && checkBrower(/msie 5/), //Mozilla/4.0 (compatible; MSIE 5.0; Windows NT)
    isIE6 = isIE && checkBrower(/msie 6/), //Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1) 
    isIE7 = isIE && checkBrower(/msie 7/), //Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.2)
    isIE8 = isIE && checkBrower(/msie 8/), //Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0)
    isIE9 = isIE && checkBrower(/msie 9/),
    //Firefox中的DOM元素都有一个getBoxObjectFor函数,用来获取DOM元素位置和大小
    isFirefox = !checkBrower(/webkit/) && checkBrower(/gecko/), 
    isFirefox1 = isFirefox && checkBrower(/firefox\/1/),//  Mozilla/5.0 (Windows; U; Windows NT 5.1) Gecko/20070803 Firefox/1.5.0.12 
    isFirefox2 = isFirefox && checkBrower(/rv:1\.8/),//Mozilla/5.0 (Windows; U; Windows NT 5.1) Gecko/20070309 Firefox/2.0.0.3 
    isFirefox3 = isFirefox && checkBrower(/rv:1\.9/),//Mozilla/5.0 (Windows; U; Windows NT 5.2) Gecko/2008070208 Firefox/3.0.1
    isFirefox4 = isFirefox && checkBrower(/firefox\/4/),
    //Mozilla/5.0 (Windows; U; Windows NT 5.2) AppleWebKit/525.13 (KHTML, like Gecko) Chrome/0.2.149.27 Safari/525.13
    isChrome = checkBrower(/chrome\/([\d.]+)/),
    isChrome1 = checkBrower(/chrome\/1/),
    isChrome2 = checkBrower(/chrome\/2/),
    isChrome3 = checkBrower(/chrome\/3/),
    isChrome4 = checkBrower(/chrome\/4/),
    isChrome5 = checkBrower(/chrome\/5/),
    isChrome6 = checkBrower(/chrome\/6/),
    isChrome7 = checkBrower(/chrome\/7/),
    isChrome8 = checkBrower(/chrome\/8/),
    //Opera提供了专门的浏览器标志，就是window.opera属性
    isOpera = checkBrower(/opera/),
    isOpera8 = checkBrower(/opera\/8/), //Opera/8.0 (Macintosh; PPC Mac OS X; U; en)
    isOpera9 = checkBrower(/opera\/9/), //Opera/9.27 (Windows NT 5.2; U; zh-cn)
    //Mozilla/5.0 (Windows; U; Windows NT 5.2) AppleWebKit/525.13 (KHTML, like Gecko) Version/3.1 Safari/525.13
    //Mozilla/5.0 (iPhone; U; CPU like Mac OS X) AppleWebKit/420.1 (KHTML, like Gecko) Version/3.0 Mobile/4A93 Safari/419.3 
    isSafari = !isChrome && checkBrower(/safari/),
    isSafari2 = isSafari && checkBrower(/applewebkit\/4/), 
    isSafari3 = isSafari && checkBrower(/version\/3/),
    isSafari4 = isSafari && checkBrower(/version\/4/),
    isWindows = checkBrower(/windows|win32/),
    isLinux = checkBrower(/linux/),
    isMac = checkBrower(/macintosh|mac os x/),
    isAir = checkBrower(/adobeair/),
    isWebKit = checkBrower(/webkit/),
    isSecure = /^https/i.test(window.location.protocol);
  //IE对背景图进行缓存,IE(不包括IE7)会经常从服务器端重新载入背景图片，remove css image flicker
  if(isIE6){
    try{
      DOC.execCommand("BackgroundImageCache", false, true);
    }catch(e){}
  }
  function f(n) {
    return n < 10 ? '0' + n : n;// Format integers to have at least two digits.
  }
  if (typeof Date.prototype.toJSON !== 'function') {
    Date.prototype.toJSON = function (key) {
      return isFinite(this.valueOf()) ?
        this.getUTCFullYear()     + '-' +
        f(this.getUTCMonth() + 1) + '-' +
        f(this.getUTCDate())      + 'T' +
        f(this.getUTCHours())     + ':' +
        f(this.getUTCMinutes())   + ':' +
        f(this.getUTCSeconds())   + 'Z' : null;
    };
    String.prototype.toJSON = Number.prototype.toJSON = Boolean.prototype.toJSON = 
      function (key) {return this.valueOf();};
  }
  Jp.apply(Jp, {
    isIE : isIE,
    isIE6 : isIE6,
    isIE7 : isIE7,
    isIE8 : isIE8,
    isIE9 : isIE9,
    isFirefox : isFirefox,
    isFirefox1 : isFirefox1,
    isFirefox2 : isFirefox2,
    isFirefox3 : isFirefox3,
    isFirefox4 : isFirefox4,
    isChrome : isChrome,
    isChrome1 : isChrome1,
    isChrome2 : isChrome2,
    isChrome3 : isChrome3,
    isChrome4 : isChrome4,
    isChrome5 : isChrome5,
    isChrome6 : isChrome6,
    isChrome7 : isChrome7,
    isChrome8 : isChrome8,
    isOpera : isOpera,
    isOpera8 : isOpera8,
    isOpera9 : isOpera9,
    isSafari : isSafari,
    isSafari2 : isSafari2,
    isSafari3 : isSafari3,
    isSafari4 : isSafari4,
    isWindows : isWindows,
    isLinux : isLinux,
    isMac : isMac,
    isAir : isAir,
    isSecure : isSecure,
    //clientWidth: document.body.clientWidth,
    //clientHeight: document.body.clientHeight,
    //--------------------------------------------- 基本方法  ---------------------------------------------        
    /**
     * <p>如果是已定义返回true,否则返回false</p>
     * @param {Mixed} value 需要判断的值
     * @return {Boolean}
     * @since 1.0.0
     * @author 甘剑平
     */
    isDefined : function(aValueMix){
      return typeof aValueMix !== 'undefined';
    },
    /**
     * <p>如果是字符串返回true,否则返回false</p>
     * @param {Mixed} value 需要判断的值
     * @return {Boolean}
     * @since 1.0.0
     * @author 甘剑平
     */
    isString : function(aValueMix){
      return typeof aValueMix === 'string';
    },
    /**
     * <p>如果是布尔值返回true,否则返回false</p>
     * @param {Mixed} value 需要判断的值
     * @return {Boolean}
     * @since 1.0.0
     * @author 甘剑平
     */
    isBoolean : function(aValueMix){
      return typeof aValueMix === 'boolean';
  	},
    /**
     * <p>如果是数字并且是有穷大的,那么就返回true</p>
     * @param {Mixed} value 需要判断的值
     * @return {Boolean}
     * @since 1.0.0
     * @author 甘剑平
     */
    isNumber : function(aValueMix){
        return typeof aValueMix === 'number' && isFinite(aValueMix);
    },
    /**
     * <p>如果是基础类型(string, number or boolean)则返回true</p>
     * @param {Mixed} value 需要判断的值
     * @return {Boolean}
     * @since 1.0.0
     * @author 甘剑平
     */
    isPrimitive : function(aValueMix){
       return Jp.isString(aValueMix) || Jp.isNumber(aValueMix) || Jp.isBoolean(aValueMix);
    },
    /**
     * <p>如果是数组返回true,否则返回false</p>
     * @param {Mixed} value 需要判断的值
     * @return {Boolean}
     * @since 1.0.0
     * @author 甘剑平
     */
    isArray : function(aValueMix) {
      return toString.apply(aValueMix) === '[object Array]';
    },
    /**
     * <p>如果是空返回true,否则返回false</p>
     * @param {Mixed} value 需要判断的值
     * @param {Boolean} 是否允许''为真 (可选),默认为false
     * @return {Boolean}
     * @since 1.0.0
     * @author 甘剑平
     */
    isEmpty : function(aValueMix, aAllowBlank){
      return aValueMix === null || aValueMix === undefined || ((Jp.isArray(aValueMix) && !aValueMix.length)) || (!aAllowBlank ? aValueMix === '' : false);
    },
    /**
     * <p>如果是可迭代型的返回true,否则返回false</p>
     * @param {Mixed} value 需要判断的值
     * @return {Boolean}
     * @since 1.0.0
     * @author 甘剑平
     */
    isIterable : function(aValueMix){
      //检查是否是数组或arguments
      if (Jp.isArray(aValueMix) || aValueMix.callee) {
        return true;
      }
      //检查是否是node集合类型
      if(/NodeList|HTMLCollection/.test(toString.call(aValueMix))){
        return true;
      }
      //IXMLDOMNodeList有nextNode方法,需要被第一个检查,NodeList有一个item且长度是个数值
      return ((typeof aValueMix.nextNode != 'undefined' || aValueMix.item) && Jp.isNumber(aValueMix.length));
    },
    /**
     * 删除左空格
     * eg: Jp.ltrim(" ganjp") 
     * @param {String}
     * @return (String|mix)
     */
    ltrim : function(aStr){
      if (!Jp.isString(aStr)) {
        return aStr;
      }
      return aStr.replace(/^\s*/, "");
    },
    /**
     * 删除右空格
     * eg: Jp.rtrim(" ganjp") 
     * @param {String}
     * @return (String|mix)
     */
   rtrim : function(aStr){
      if (!Jp.isString(aStr)) {
        return aStr;
      }
      return aStr.replace(/\s*$/, "");
    },
    /**
     * 删除左右空格
     * eg: Jp.trim(" ganjp") 
     * @param {String}
     * @return (String|mix)
     */
    trim : function(aStr){
      if (!Jp.isString(aStr)) {
        return aStr;
      }
      return Jp.rtrim(Jp.ltrim(aStr));
    },
    /**
     * Converts any iterable into a true array,
     * eg: Jp.toArray("23,24,26",1,2), 
     * @param {Iterable or String}
     * @return (Array) array
     */
    toArray : function(){
      return isIE ?
        function(a, i, j, res) {
          if (typeof a === 'string') {
            res = a.split(",");
          } else {
            res = [];
            for (var x = 0, len = a.length; x < len; x++) {
              res.push(a[x]);
            }
          }
          return res.slice(i || 0, j || res.length);
        } :
        function(a, i, j) {
          var arr;  
          if (typeof a === 'string') {
            arr = a.split(",");
          } else {
            arr = a;
          }
          return Array.prototype.slice.call(arr, i || 0, j || arr.length);
        }
    }(),
    toJson : function(text, reviver){
      var j,cx = /[\u0000\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g;
      function walk(holder, key) {
        var k, v, value = holder[key];
        if (value && typeof value === 'object') {
          for (k in value) {
            if (Object.prototype.hasOwnProperty.call(value, k)) {
              v = walk(value, k);
              if (v !== undefined) {
                value[k] = v;
              } else {
                delete value[k];
              }
            }
          }
        }
        return reviver.call(holder, key, value);
      }
      text = String(text);
      cx.lastIndex = 0;
      if (cx.test(text)) {
          text = text.replace(cx, function (a) {
              return '\\u' +
                  ('0000' + a.charCodeAt(0).toString(16)).slice(-4);
          });
      }
      if (/^[\],:{}\s]*$/
              .test(text.replace(/\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g, '@')
                  .replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g, ']')
                  .replace(/(?:^|:|,)(?:\s*\[)+/g, ''))) {
          j = eval('(' + text + ')');
          return typeof reviver === 'function' ? walk({'': j}, '') : j;
      }
      throw new SyntaxError('JSON.parse');
    },
    quote: function(string){
      var escapable = /[\\\"\x00-\x1f\x7f-\x9f\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,
          meta = {'\b': '\\b', '\t': '\\t', '\n': '\\n', '\f': '\\f', '\r': '\\r', '"' : '\\"', '\\': '\\\\'};
          escapable.lastIndex = 0;
      return escapable.test(string) ? '"' + string.replace(escapable, function (a) {var c = meta[a];
               return typeof c === 'string' ? c : '\\u' + ('0000' + a.charCodeAt(0).toString(16)).slice(-4);
           }) + '"' : '"' + string + '"';
    },
    //Jp.toStr(newNodeArr)
    toStr: function(value, replacer, space){
      var gap='', indent='', rep;
      function str(key, holder) {
        var i,          // The loop counter.
            k,          // The member key.
            v,          // The member value.
            length,
            mind = gap,
            partial,
            value = holder[key];
        if (typeof value.toJSON === 'function') {
            value = value.toJSON(key);
        }
        if (typeof rep === 'function') {
            value = rep.call(holder, key, value);
        }
        switch (typeof value) {
        case 'string':
            return Jp.quote(value);
        case 'number':
            return isFinite(value) ? String(value) : 'null';
        case 'boolean':
        case 'null':
            return String(value);
        case 'object':
            if (!value) {
                return 'null';
            }
            gap += indent;
            partial = [];
            if (Object.prototype.toString.apply(value) === '[object Array]') {
                length = value.length;
                for (i = 0; i < length; i += 1) {
                    partial[i] = str(i, value) || 'null';
                }
                v = partial.length === 0 ? '[]' : gap ?
                    '[\n' + gap + partial.join(',\n' + gap) + '\n' + mind + ']' :
                    '[' + partial.join(',') + ']';
                gap = mind;
                return v;
            }
            if (rep && typeof rep === 'object') {
                length = rep.length;
                for (i = 0; i < length; i += 1) {
                    if (typeof rep[i] === 'string') {
                        k = rep[i];
                        v = str(k, value);
                        if (v) {
                            partial.push(Jp.quote(k) + (gap ? ': ' : ':') + v);
                        }
                    }
                }
            } else {
                for (k in value) {
                    if (Object.prototype.hasOwnProperty.call(value, k)) {
                        v = str(k, value);
                        if (v) {
                            partial.push(Jp.quote(k) + (gap ? ': ' : ':') + v);
                        }
                    }
                }
            }
            v = partial.length === 0 ? '{}' : gap ?
                '{\n' + gap + partial.join(',\n' + gap) + '\n' + mind + '}' :
                '{' + partial.join(',') + '}';
            gap = mind;
            return v;
        }
      }
      if (typeof space === 'number') {
        for (var i = 0; i < space; i += 1) {
          indent += ' ';
        }
      } else if (typeof space === 'string') {
        indent = space;
      }
      rep = replacer;
      if (replacer && typeof replacer !== 'function' &&
              (typeof replacer !== 'object' ||
              typeof replacer.length !== 'number')) {
          throw new Error('Jp.toStr');
      }
      return str('', {'': value});
    },
    /**
     * <p>循环执行一个数组</p>
     * @param {Array/NodeList/Mixed} array The array to be iterated. 
     * @param {Function} fn The function to be called with each item. 
     * @param {Object} scope this
     * @return See description for the fn parameter.
     * @since 1.0.0
     * @author 甘剑平
     */
    each: function(pArr, pFn, pScope) {
      if (Jp.isEmpty(pArr, true)) {
        return;
      }
      if (!Jp.isIterable(pArr) || Jp.isPrimitive(pArr)) {
        pArr = [pArr];
      }
      for(var i = 0, len = pArr.length; i < len; i++){
        if (pFn.call(pScope || pArr[i], pArr[i], i, pArr) === false){
          return i;
        };
      }
    },
    /**
     * Iterates either the elements in an array, or each of the properties in an object.
     * <b>Note</b>: If you are only iterating arrays, it is better to call {@link #each}.
     * @param {Object/Array} object The object or array to be iterated
     * @param {Function} fn The function to be called for each iteration.
     * The iteration will stop if the supplied function returns false, or
     * all array elements / object properties have been covered. The signature
     * varies depending on the type of object being interated:
     * <div class="mdetail-params"><ul>
     * <li>Arrays : <tt>(Object item, Number index, Array allItems)</tt>
     * <div class="sub-desc">
     * When iterating an array, the supplied function is called with each item.</div></li>
     * <li>Objects : <tt>(String key, Object value, Object)</tt>
     * <div class="sub-desc">
     * When iterating an object, the supplied function is called with each key-value pair in
     * the object, and the iterated object</div></li>
     * </ul></div>
     * @param {Object} scope The scope (<code>this</code> reference) in which the specified function is executed. Defaults to
     * the <code>object</code> being iterated.
     */
    iterate : function(obj, fn, scope){
      if (Jp.isEmpty(obj)) {
        return;
      }
      if (Jp.isIterable(obj)) {
        Jp.each(obj, fn, scope);
        return;
      } else if (Jp.isObject(obj)) {
	    for (var prop in obj){
	      if (obj.hasOwnProperty(prop)) {
	        if (fn.call(scope || obj, prop, obj[prop], obj) === false) {
	          return;
	        };
	      }
	    }
      }
    },
  
    /**
     * 对日期进行格式化
     * @param {String} 日期字符串
     * @return {String} 格式后的字符串 
     * @since 1.0
     * @author 甘剑平
     */
    formatDate : function(aDateStr){
      var dateObject = new Date(aDateStr);
      if (isNaN(dateObject)) {
        if (aDateStr.length>10) {
          return aDateStr.substr(0,10);
        } else {
          return aDateStr;
        }
      } else {
        var month = dateObject.getMonth() - 0 + 1;
        var day = dateObject.getDate() - 0;
        if (month < 10) {
          month = "0" + month; 
        }
        if (day < 10) {
          day = "0" + day; 
        }
        return dateObject.getFullYear() + "-" + month + "-" + day;  
      }
    },
    /**
     * 在某个DOM元素内输出需要的文本信息
     * @param {String} aText 需要输出的文本
     * @param {String} aElementId Dom元素的ID
     * @since 1.0
     * @author 甘剑平
     */
    show: function(aText, aElementId){
      //DOC.writeln(aText + "</br>");
      var pEl = DOC.createElement("p");
      var newText = DOC.createTextNode(aText);
      pEl.appendChild(newText);
      if (aElementId) {
        DOC.getElementById(aElementId).appendChild(pEl);
      } else {
        DOC.body.appendChild(pEl);
      }
    },
    /**
     * aSourceConfig属性值不能覆盖aReceiverObj的属性值,只能增加
     * @param {Object} aReceiverObj 接受应用属性的对象
     * @param {Object} aSourceConfig 应用对象
     * @return {Object} returns obj
     */
    applyIf : function(aReceiverObj, aSourceConfig){
      if (aReceiverObj) {
          for (var p in aSourceConfig) {
              if (!Jp.isDefined(aReceiverObj[p])) {
                aReceiverObj[p] = aSourceConfig[p];
              }
          }
      }
      return aReceiverObj;
    },
    /**
     * Adds a list of functions to the prototype of an existing class, 
     overwriting any existing methods with the same name.
     * Usage:<pre><code>
Jp.override(MyClass, {
    newMethod: function(){
        // etc.
    }
});
</code></pre>
     * @param {Object} origclass The class to override
     * @param {Object} overrides The list of functions to add to origClass.  This should be specified as an object literal
     * containing one or more methods.
     * @method override
     */
    override : function(aOrigclass, aOverrides){
      if(overrides){
        var p = aOrigclass.prototype;
        Jp.apply(p, aOverrides);
        if(Jp.isIE && aOverrides.hasOwnProperty('toString')){
          p.toString = aOverrides.toString;
        }
      }
    },
    /**
     * Takes an object and converts it to an encoded URL. e.g. Jp.urlEncode({foo: 1, bar: 2}); 
     * would return "foo=1&bar=2".  Optionally, property values can be arrays, instead of keys and 
     * the resulting string that's returned will contain a name/value pair for each array value.
     * @param {Object} o
     * @param {String} pre (optional) A prefix to add to the url encoded string
     * @return {String}
     */
    urlEncode : function(aObj, aPreStr){
      var empty,
          buf = [],
          e = encodeURIComponent;

      Jp.iterate(aObj, function(key, item){
          empty = Jp.isEmpty(item);
          Jp.each(empty ? key : item, function(val){
              buf.push('&', e(key), '=', (!Jp.isEmpty(val) && (val != key || !empty)) 
              ? (Jp.isDate(val) ? Jp.encode(val).replace(/"/g, '') : e(val)) : '');
          });
      });
      if(!aPreStr){
          buf.shift();
          aPreStr = '';
      }
      return aPreStr + buf.join('');
    },
    /**
     * Takes an encoded URL and and converts it to an object. Example: <pre><code>
Jp.urlDecode("foo=1&bar=2"); // returns {foo: "1", bar: "2"}
Jp.urlDecode("foo=1&bar=2&bar=3&bar=4", false); // returns {foo: "1", bar: ["2", "3", "4"]}
</code></pre>
     * @param {String} string
     * @param {Boolean} overwrite (optional) Items of the same name will overwrite previous values instead of creating an an array (Defaults to false).
     * @return {Object} A literal with members
     */
    urlDecode : function(aStr, overwrite){
      if(Jp.isEmpty(aStr)){
        return {};
      }
      var obj = {},
          pairs = aStr.split('&'),
          d = decodeURIComponent,
          name,
          value;
      Jp.each(pairs, function(pair) {
        pair = pair.split('=');
        name = d(pair[0]);
        value = d(pair[1]);
        obj[name] = overwrite || !obj[name] ? value :
                    [].concat(obj[name]).concat(value);
      });
      return obj;
    },

    /**
     * Appends content to the query string of a URL, handling logic for whether to place
     * a question mark or ampersand.
     * @param {String} url The URL to append to.
     * @param {String} s The content to append to the URL.
     * @return (String) The resulting URL
     */
    urlAppend : function(aUrl, aStr){
      if(!Jp.isEmpty(aStr)){
        return aUrl + (aUrl.indexOf('?') === -1 ? '?' : '&') + aStr;
      }
      return aUrl;
    },
    //----------------------------------------------对Dom元素的操作--------------------------------
    /**
     * Return the dom node for the passed String (id), dom node, or Jp.Element.
     * @param {Mixed} el
     * @return HTMLElement
     */
    getDom : function(el){
      if(!el || !DOC){
          return null;
      }
      return el.dom ? el.dom : (Jp.isString(el) ? DOC.getElementById(el) : el);
    },
      
    //--------------------------------------------- 对表单或表单元素的操作  ---------------------------------------------
    /**
     * 创建表单Form
     * eg: Jp.createForm({name:'form',action:'test.jsp',method:'post'});
     *
     * @param {json} aFormJson, action是必填项
     * @return {element} form 使用方法:form.appendChild( createHidden("method", "delete") );
     * @since 1.0
     * @author 甘剑平
     */
    createForm : function(aFormJson){
      var formEl = Jp.isDefined(aFormJson.name)?DOC.createElement(aFormJson.name):DOC.createElement("form");
      if (aFormJson.action) {
        formEl.action = action;
      } else {
        alert("必须设置action属性");
        return;
      }
      formEl.method = Jp.isDefined(aFormJson.method)?aFormJson.method:"post";
      //form.style.display = Jp.isDefined(aFormJson.display)?aFormJson.display:"none";
        return formEl;
    },
    /**
     * 创建隐藏域
     * @param {string} aElementName 隐藏域的名称
     * @param {string} aValueStr 隐藏域的值
     * @return {element} 隐藏域
     * @since 1.0
     * @author 甘剑平
     */
     createHidden : function(aElementName, aValueStr){
        var inputEl = DOC.createElement("input");
        inputEl.type = "hidden";
        inputEl.id = aElementName;
        inputEl.name = aElementName;
        inputEl.value = aValueStr;
        return inputEl;
    },
    /**
     * 设置文本框为只读
     *
     * @param formId 表单ID
     * @param inputName 输入框名称
     * @since 1.0
     * @author 甘剑平
     */
    setReadOnly : function(aFormId,aInputName){
      DOC.getElementById(aFormId).elements[aInputName].readOnly = true;
    },
    /**
     * 选择所有的checkbox
     * eg: <input id="checkHead" onclick="checkAll(this,'key')" type="checkbox"/>
     * @param {element} aTriggerElement 触发的DOM元素
     * @param {string} aTargetCheckBoxName 需要被关联的checkBox名称
     * @since 1.0
     * @author 甘剑平
     */
    checkAll : function(aTriggerElement, aTargetCheckBoxName){
      for (var i = 0; i < DOC.getElementsByName(aTargetCheckBoxName).length; i++) {
        DOC.getElementsByName(aTargetCheckBoxName)[i].checked = aTriggerElement.checked;
      }
    },
    /**
     * 通过多选框名称获得被选中的值(用","分隔)
     * @param {String} aCheckboxName checkBox的名称
     * @return string
     * @since 1.0
     * @author 甘剑平
     */
    getCheckValues : function(aCheckboxName){
      var checkValues = "";
      var elementArr = DOC.getElementsByName(aCheckboxName);
      var size = elementArr.length;
      for (var i = 0; i < size; i++) {
        var element = elementArr[i];
        if (element.checked) {
          checkValues += element.value + ",";
        }
      }
      if (checkValues != "") {
        checkValues = checkValues.substr(0, checkValues.length - 1);
      }
      return checkValues;
    },
    /**
     * 如果没选或多选记录都返回false,否则返回true
     * @param checkValues 多选框被选择的值
     * @return boolean
     * @since 1.0
     * @author ganjp
     */
    isEdit : function(checkValues) {
      if (Jp.isEmpty(checkValues)) {
        alert(I18N.msg_no_sel_edit_record);
        return false;
      } else if (checkValues.indexOf(',') != -1) {
    	alert(I18N.msg_single_edit_record);
        return false;
      } else {
        return true;
      }
    },
    
    /**      
     * 设置多选框的状态.
     *     
     * @param {string} aCheckBoxName 多选框的名称
     * @param {boolean} aStateBool 多选框是否选中ture或false     
     * @since 1.0
     * @author 甘剑平     
     */   
    setCheckBoxState : function(aCheckBoxName, aStateBool){        
      var elArr = DOC.getElementsByName(name);        
      for (var i = 0; i < elArr.length; i++) {               
        elArr[i].checked = aStateBool;        
      }        
    },
    /**      
     * 选中指定值的checkbox
     * eg: Jp.selectCheckbox("ids","1,2")     
     *     
     * @param {string} aElementName 要选中的checkbox数组的名称     
     * @param {string} aValueStr 判断时候选中的值(用","分割)     
     * @since 1.0
     * @author 甘剑平    
     */
    selectCheckbox : function(aElementName,aValueStr){        
      if ( !Jp.isEmpty(aValueStr) ) {
        var checkEl = document.getElementsByName(aElementName);
        var valueArr = aValueStr.split(",");        
        for (var j = 0; j < valueArr.length; j++) {        
          for (var i = 0; i < checkEl.length; i++) {        
            if (checkEl[i].value == valueArr[j]) {        
              checkEl[i].checked = true;    
              break;
            }     
          }    
        }    
      }
    },
    /**      
     * 选中指定值的Radio     
     *
     * @param {string} aElementName 单选按钮的名称
     * @param {string} aValueStr 值     
     * @since 1.0
     * @author 甘剑平     
     */   
    selectRadio : function(aElementName, aValueStr){
      if(!Jp.isEmpty(aValueStr)){ 
        var radioEl = DOC.getElementsByName(aElementName);
        for (var i = 0; i < radioEl.length; i++) {
          if(radioEl[i].value == value) {        
            radioEl[i].checked = true;
            break;
          }
        }
      }
    },    
    /**      
     * 选中指定值的select     
     *      
     * @param {string} aElementName 下拉框名称
     * @param {string} aValueStr 值     
     * @since 1.0
     * @author 甘剑平     
     */   
    selectOption : function(aElementName, aValueStr){   
      if (!Jp.isEmpty(aValueStr)) {  
        var optionArr = document.getElementsByName(aElementName)[0].options;
        for (var i = 0; i < optionArr.length; i++) {
          if (optionArr[i].value === aValueStr) {
            optionArr[i].selected = true;
            break;
          }        
        }
      }
    },
    //--------------------------------------------- 系统级方法  ---------------------------------------------
    /**
     * 创建命名空间来约束变量和class不是全局变量,指派是最后一个节点
     * <pre><code>
       Jp.namespace('Company.data');
       Jp.namespace('Company', 'Company.data');
       Company.Widget = function() { ... }
       Company.data.CustomStore = function(config) { ... }
       </code></pre>
     * @param {String} namespace1
     * @param {String} namespace2
     * @param {String} etc
     * @return {Object} The namespace object.
     * @method namespace
     * @since 1.0.0
     * @author 甘剑平
     */
    namespace : function(){
      var o, d;
      Jp.each(arguments, function(v) {
           d = v.split(".");
           o = window[d[0]] = window[d[0]] || {};
           Jp.each(d.slice(1), function(v2){
               o = o[v2] = o[v2] || {};
           });
      });
      return o;
    },
    clone : function(para){
        var rePara = null;
        var type = Object.prototype.toString.call(para);
        if (type.indexOf("Object") > -1) {
            rePara = {};
            for (var key in para) {
                if (para.hasOwnProperty(key)) {
                    rePara[key] = Jp.clone(para[key]);
                }
            }
        } else if (type.indexOf("Array") > 0) {
            rePara = [];
            for (var i=0;i<para.length;i++) {
               rePara[rePara.length] =  clone(para[i]);
            }
        } else {
            rePara = para;
        }
        return rePara;
    },
    /**
     * 设置
     * <link rel="alternate stylesheet" href="red.css" type="text/css" title="red" media="screen, projection"/> 
     * <link rel="stylesheet" href="green.css" type="text/css" title="green" media="screen, projection"/> 
     * <link rel="alternate stylesheet" href="yellow.css" type="text/css" title="yellow" media="screen, projection"/>
     */
    setActiveStyleSheet : function(title) {
      var i, linkEl;
	  if (title) {
	    for(i=0; (linkEl = document.getElementsByTagName('link')[i]); i++) {
	      if(linkEl.getAttribute('rel').indexOf('style') != -1 && linkEl.getAttribute('title')) {
	        linkEl.disabled = true;
	        if(linkEl.getAttribute('title') == title) linkEl.disabled = false;
	      }
	    }
	  }
	},
    getActiveStyleSheet : function() {
      var i, linkEl;
      for(i=0; (linkEl = document.getElementsByTagName('link')[i]); i++) {
        if(linkEl.getAttribute('rel').indexOf('style') != -1 && linkEl.getAttribute('title') && !linkEl.disabled) 
          return linkEl.getAttribute('title');
      }
      return null;  
    },
    loadCss : function(url, callback) {
      var link = document.createElement('link');
      link.rel = 'stylesheet';
      link.type = 'text/css';
      link.media = 'screen';
      link.href = url;
      document.getElementsByTagName('head')[0].appendChild(link);
      if (callback){
        callback.call(link);
      }
    },
    loadJs : function(url, callback) {
      var done = false;
      var script = document.createElement('script');
      script.type = 'text/javascript';
      script.language = 'javascript';
      script.src = url;
      script.onload = script.onreadystatechange = function(){
        if (!done && (!script.readyState || script.readyState == 'loaded' || script.readyState == 'complete')){
          done = true;
          script.onload = script.onreadystatechange = null;
          if (callback){
            callback.call(script);
          }
        }
      }
      document.getElementsByTagName("head")[0].appendChild(script);
    },
    fixgeometry : function() {
	   /* Some orientation changes leave the scroll position at something
	    * that isn't 0,0. This is annoying for user experience. */
	   scroll(0, 0);

	   /* Calculate the geometry that our content area should take */
	   var header = $("#header");
	   var footer = $("#footer");
	   var content = $("#content");
	   var viewport_height = $(window).height();
	   var content_height = viewport_height - header.outerHeight();
	   if (footer.outerHeight()!=null) {
		   content_height = content_height-footer.outerHeight();
	   }
	   /* Trim margin/border/padding height */
	   content_height -= (content.outerHeight() - content.height());
	   content.height(content_height);
	}
  });
  Jp.ns = Jp.namespace;
})();

Jp.ns("Jp.util", "Jp.lib", "Jp.data");

/**
 * 对String的扩展
 * 
 * @since 1.0.0
 * @author 甘剑平
 */
Jp.applyIf(String, {
    /**
     * 格式化字符串
     * <pre><code>
       var cls = 'my-class', text = 'my-text';
       var s = String.format('&lt;div class="{0}">{1}&lt;/div>', cls, text); //'<div class="my-class">my-text</div>'
     * </code></pre>
     * @param {String} aFormatStr 需要格式化的字符串 
     * @param {String} value1 The value to replace token {0}
     * @param {String} value2 Etc...
     * @return {String} The formatted string
     * @static
     */
    format: function(aFormatStr) {
        var args = Jp.toArray(arguments, 1);
        return aFormatStr.replace(/\{(\d+)\}/g, function(m, i){
            return args[i];
        });
    }
});
/**
 * @class Array
 */
Jp.applyIf(Array.prototype, {
  /**
   * Checks whether or not the specified object exists in the array.
   * @param {Object} o The object to check for
   * @param {Number} from (Optional) The index at which to begin the search
   * @return {Number} The index of o in the array (or -1 if it is not found)
   */
  indexOf : function(o, from){
    var len = this.length;
    from = from || 0;
    from += (from < 0) ? len : 0;
    for (; from < len; ++from){
      if(this[from] === o){
          return from;
      }
    }
    return -1;
  },

  /**
   * Removes the specified object from the array.  If the object is not found nothing happens.
   * @param {Object} o The object to remove
   * @return {Array} this array
   */
  remove : function(o){
    var index = this.indexOf(o);
    if(index != -1){
        this.splice(index, 1);
    }
    return this;
  }
});
/**
 * @class Function
 * These functions are available on every Function object (any JavaScript function).
 */
Jp.apply(Function.prototype, {
  /**
   * Creates an interceptor function. The passed function is called before the original one. If it returns false,
   * the original one is not called. The resulting function returns the results of the original function.
   * The passed function is called with the parameters of the original function. Example usage:
   * <pre><code>
	 var sayHi = function(name){
	   alert('Hi, ' + name);
	 }
     sayHi('Fred'); // alerts "Hi, Fred"
     var sayHiToFriend = sayHi.createInterceptor(function(name){
       return name == 'Brian';
     });
     sayHiToFriend('Fred');  // no alert
     sayHiToFriend('Brian'); // alerts "Hi, Brian"
     </code></pre>
   * @param {Function} fcn The function to call before the original
   * @param {Object} scope (optional) The scope (<code><b>this</b></code> reference) in which the passed function is executed.
   * <b>If omitted, defaults to the scope in which the original function is called or the browser window.</b>
   * @return {Function} The new function
   */
   createInterceptor : function(fcn, scope){
     var method = this;
     return !Jp.isFunction(fcn) ?
	     this :
	     function() {
	         var me = this, args = arguments;
	         fcn.target = me;
	         fcn.method = method;
	         return (fcn.apply(scope || me || window, args) !== false) ?
	                 method.apply(me || window, args) : null;
	     };
  },
  /**
   * Creates a callback that passes arguments[0], arguments[1], arguments[2], ...
   * Call directly on any function. Example: <code>myFunction.createCallback(arg1, arg2)</code>
   * Will create a function that is bound to those 2 args. <b>If a specific scope is required in the
   * callback, use {@link #createDelegate} instead.</b> The function returned by createCallback always
   * executes in the window scope.
   * <p>This method is required when you want to pass arguments to a callback function.  If no arguments
   * are needed, you can simply pass a reference to the function as a callback (e.g., callback: myFn).
   * However, if you tried to pass a function with arguments (e.g., callback: myFn(arg1, arg2)) the function
   * would simply execute immediately when the code is parsed. Example usage:
   * <pre><code>
	var sayHi = function(name){
	    alert('Hi, ' + name);
	}
	new Jp.Button({
	    text: 'Say Hi',
	    renderTo: Jp.getBody(),
	    handler: sayHi.createCallback('Fred')
	});
	</code></pre>
  * @return {Function} The new function
  */
  createCallback : function(/*args...*/){
    // make args available, in function below
    var args = arguments,
        method = this;
    return function() {
        return method.apply(window, args);
    };
  },
  /**
   * Creates a delegate (callback) that sets the scope to obj.
   * Call directly on any function. Example: <code>this.myFunction.createDelegate(this, [arg1, arg2])</code>
   * Will create a function that is automatically scoped to obj so that the <tt>this</tt> variable inside the
   * callback points to obj. Example usage:
   * <pre><code>
	 var sayHi = function(name){
	    alert('Hi, ' + name + '. You clicked the "' + this.text + '" button.');
	 }
	 var btn = new Jp.Button({
       text: 'Say Hi',
       renderTo: Jp.getBody()
     });
	 btn.on('click', sayHi.createDelegate(btn, ['Fred']));
     </code></pre>
   * @param {Object} scope (optional) The scope (<code><b>this</b></code> reference) in which the function is executed.
   * <b>If omitted, defaults to the browser window.</b>
   * @param {Array} args (optional) Overrides arguments for the call. (Defaults to the arguments passed by the caller)
   * @param {Boolean/Number} appendArgs (optional) if True args are appended to call args instead of overriding,
   * if a number the args are inserted at the specified position
   * @return {Function} The new function
   */
  createDelegate : function(obj, args, appendArgs){
    var method = this;
    return function() {
      var callArgs = args || arguments;
      if (appendArgs === true){
        callArgs = Array.prototype.slice.call(arguments, 0);
        callArgs = callArgs.concat(args);
      }else if (Jp.isNumber(appendArgs)){
        callArgs = Array.prototype.slice.call(arguments, 0); // copy arguments first
        var applyArgs = [appendArgs, 0].concat(args); // create method call params
        Array.prototype.splice.apply(callArgs, applyArgs); // splice them in
      }
      return method.apply(obj || window, callArgs);
    };
  },
  /**
   * Calls this function after the number of millseconds specified, optionally in a specific scope. Example usage:
   * <pre><code>
	 var sayHi = function(name){ alert('Hi, ' + name); }
	 sayHi('Fred');// executes immediately
	 sayHi.defer(2000, this, ['Fred']);// executes after 2 seconds
     </code></pre>
   * @param {Number} millis The number of milliseconds for the setTimeout call (if less than or equal to 0 the function is executed immediately)
   * @param {Object} scope (optional) The scope (<code><b>this</b></code> reference) in which the function is executed.
   * <b>If omitted, defaults to the browser window.</b>
   * @param {Array} args (optional) Overrides arguments for the call. (Defaults to the arguments passed by the caller)
   * @param {Boolean/Number} appendArgs (optional) if True args are appended to call args instead of overriding,
   * if a number the args are inserted at the specified position
   * @return {Number} The timeout id that can be used with clearTimeout
   */
  defer : function(millis, obj, args, appendArgs){
      var fn = this.createDelegate(obj, args, appendArgs);
      if(millis > 0){
          return setTimeout(fn, millis);
      }
      fn();
      return 0;
  }
});
