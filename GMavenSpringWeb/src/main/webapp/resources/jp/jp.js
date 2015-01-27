/**
 * jp javascript Library 1.0.0
 * 
 * localStorage.setItem('name', 'value');
 * localStorage.getItem('name');
 * localStorage.removeItem('name');
 * localStorage.clear();
 * sessionStorage.length
 * 
 * Copyright (c) 2014 Gan Jianping. All rights reserved
 */
jp = {
  version: "1.0.0",
  author: "Gan Jianping",
  versionDetail: {
    major: 1,
    minor: 0,
    patch: 0
  },
  keyLang : "jp-lang"
};

/**
 * Copies all the properties of config to obj.
 * @param {Object} aReceiverObj The receiver of the properties
 * @param {Object} aSourceConfig The source of the properties
 * @param {Object} aDefaultConfig defaults A different object that will also be applied for default values
 * @return {Object} returns obj
 * @author GanJianping
 * @since 1.0.0
 */
jp.apply = function(aReceiverObj, aSourceConfig, aDefaultConfig){
  if (aDefaultConfig) {
    jp.apply(aReceiverObj, aDefaultConfig);
  }
  if (aReceiverObj && aSourceConfig && typeof aSourceConfig == 'object') {
    for (var key in aSourceConfig) {
      aReceiverObj[key] = aSourceConfig[key];
    }
  }
  return aReceiverObj;
};
(function(){
  var toString = Object.prototype.toString,
    userAgent = navigator.userAgent.toLowerCase(),
    checkBrower = function(aStr){
      return aStr.test(userAgent);
    },
    DOC = document,
    //IE supoort ActiveX
    isIE = !isOpera && checkBrower(/msie/),
    isFirefox = !checkBrower(/webkit/) && checkBrower(/gecko/), 
    isChrome = checkBrower(/chrome\/([\d.]+)/),
    isOpera = checkBrower(/opera/),
    isSafari = !isChrome && checkBrower(/safari/),
    isWindows = checkBrower(/windows|win32/),
    isLinux = checkBrower(/linux/),
    isMac = checkBrower(/macintosh|mac os x/),
    isAir = checkBrower(/adobeair/),
    isWebKit = checkBrower(/webkit/),
    isSecure = /^https/i.test(window.location.protocol);
  jp.apply(jp, {
    isIE : isIE,
    isFirefox : isFirefox,
    isChrome : isChrome,
    isOpera : isOpera,
    isSafari : isSafari,
    isWindows : isWindows,
    isLinux : isLinux,
    isMac : isMac,
    isAir : isAir,
    isSecure : isSecure,
  //--------------------------------------------- base method  ---------------------------------------------
    /**
     * <p>return true if aValueMix has been defined</p>
     * 
     * @param {Mixed} value 
     * @return {Boolean}
     */
    isDefined : function(aValueMix){
      return typeof aValueMix !== 'undefined';
    },
    /**
     * <p>return true if aValueMix is String</p>
     * 
     * @param {Mixed} value
     * @return {Boolean}
     */
    isString : function(aValueMix){
      return typeof aValueMix === 'string';
    },
    /**
     * <p>return true if aValueMix is boolean</p>
     * 
     * @param {Mixed} value
     * @return {Boolean}
     */
    isBoolean : function(aValueMix){
      return typeof aValueMix === 'boolean';
  	},
    /**
     * <p>return true if aValueMix is number or finite</p>
     * 
     * @param {Mixed} value 
     * @return {Boolean}
     */
    isNumber : function(aValueMix){
        return typeof aValueMix === 'number' && isFinite(aValueMix);
    },
    /**
     * <p>return true if aValueMix is string, number or boolean</p>
     * 
     * @param {Mixed} value
     * @return {Boolean}
     */
    isPrimitive : function(aValueMix){
       return jp.isString(aValueMix) || jp.isNumber(aValueMix) || jp.isBoolean(aValueMix);
    },
    /**
     * <p>return true if aValueMix is array</p>
     * @param {Mixed} value 需要判断的值
     * @return {Boolean}
     */
    isArray : function(aValueMix) {
      return toString.apply(aValueMix) === '[object Array]';
    },
    /**
     * <p>return true if aValueMix is empty</p>
     * 
     * @param {Mixed} value 
     * @param {Boolean} 
     * @return {Boolean}
     */
    isEmpty : function(aValueMix, aAllowBlank){
      return aValueMix === null || aValueMix === undefined || ((jp.isArray(aValueMix) && !aValueMix.length)) || (!aAllowBlank ? aValueMix === '' : false);
    },
    /**
     * <p>delete left whitespace</p>
     * eg: jp.ltrim(" ganjp")
     *  
     * @param {String}
     * @return (String|mix)
     */
    ltrim : function(aStr){
      if (!jp.isString(aStr)) {
        return aStr;
      }
      return aStr.replace(/^\s*/, "");
    },
    /**
     * <p>delete right whitespace</p>
     * eg: jp.rtrim(" ganjp ") 
     * 
     * @param {String}
     * @return (String|mix)
     */
    rtrim : function(aStr){
      if (!jp.isString(aStr)) {
        return aStr;
      }
      return aStr.replace(/\s*$/, "");
    },
    /**
     * <p>delete both whitespace</p>
     * eg: jp.trim(" ganjp")
     *  
     * @param {String}
     * @return (String|mix)
     */
    trim : function(aStr){
      if (!jp.isString(aStr)) {
        return aStr;
      }
      return jp.rtrim(jp.ltrim(aStr));
    },
    /**
     * <p>Converts any iterable into a true array</p>
     * eg: jp.toArray("23,24,26",1,2)
     *  
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
    },
    /**
     * Appends content to the query string of a URL, handling logic for whether to place
     * a question mark or ampersand.
     * @param {String} url The URL to append to.
     * @param {String} s The content to append to the URL.
     * @return (String) The resulting URL
     */
    urlAppend : function(aUrl, aStr){
      if(!jp.isEmpty(aStr)){
        return aUrl + (aUrl.indexOf('?') === -1 ? '?' : '&') + aStr;
      }
      return aUrl;
    },
    //--------------------------------------------- operate form ---------------------------------------------
    /**
     * <p>create hidden field</p>
     * 
     * @param {string} aElementName
     * @param {string} aValueStr
     * @return {element} 隐藏域
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
     * <p>get all checked value(",") by checkbox name</p>
     * 
     * @param {String} aCheckboxName
     * @return string
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
    getCheckIds : function(aCheckboxName){
	  var checkNames = "";
	  var elementArr = DOC.getElementsByName(aCheckboxName);
	  var size = elementArr.length;
	  for (var i = 0; i < size; i++) {
	    var element = elementArr[i];
	    if (element.checked) {
	      checkNames += element.id + ",";
	    }
	  }
	  if (checkNames != "") {
		  checkNames = checkNames.substr(0, checkNames.length - 1);
	  }
	  return checkNames;
	},
	/**      
     * <p>set checkbox state</p>
     *     
     * @param {string} aCheckBoxName
     * @param {boolean} aStateBool     
     */   
    setCheckBoxState : function(aCheckBoxName, aStateBool){        
      var elArr = DOC.getElementsByName(name);        
      for (var i = 0; i < elArr.length; i++) {               
        elArr[i].checked = aStateBool;        
      }        
    },
    /**      
     * <p>select checkbox</p>
     * eg: jp.selectCheckbox("ids","1,2")     
     *     
     * @param {string} aElementName      
     * @param {string} aValueStr (,)     
     */
    selectCheckbox : function(aElementName,aValueStr){        
      if ( !jp.isEmpty(aValueStr) ) {
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
     * <p>check all checkbox</p>
     * eg: <input id="checkHead" onclick="checkAll(this,'key')" type="checkbox"/>
     * 
     * @param {element} aTriggerElement
     * @param {string} aTargetCheckBoxName
     */
    checkAll : function(aTriggerElement, aTargetCheckBoxName){
      for (var i = 0; i < DOC.getElementsByName(aTargetCheckBoxName).length; i++) {
        DOC.getElementsByName(aTargetCheckBoxName)[i].checked = aTriggerElement.checked;
      }
    },
    /**      
     * <p>check select</p>     
     *      
     * @param {string} aElementName
     * @param {string} aValueStr     
     */   
    selectOption : function(aElementName, aValueStr){   
      if (!jp.isEmpty(aValueStr)) {  
        var optionArr = document.getElementsByName(aElementName)[0].options;
        for (var i = 0; i < optionArr.length; i++) {
          if (optionArr[i].value === aValueStr) {
            optionArr[i].selected = true;
            break;
          }        
        }
      }
    },
    /**      
     * <p>select radio</p>     
     *
     * @param {string} aElementName
     * @param {string} aValueStr     
     */   
    selectRadio : function(aElementName, aValueStr){
      if(!jp.isEmpty(aValueStr)){ 
        var radioEl = DOC.getElementsByName(aElementName);
        for (var i = 0; i < radioEl.length; i++) {
          if(radioEl[i].value == aValueStr) {  
            radioEl[i].checked = "checked";
            break;
          }
        }
      }
    },
    //---------------------------------------------- jp Validation ------------
    /**
     * Length should be 9
     * First Character in Id No. should be in Capital
     * Last Character in Id No. should be in Capital
     * First character should be S/T/F/G in Capital Letter
     * Invalid NRIC/FIN/CBC
     * 
     * @param {string} nric
     */
    isNRIC : function(nric) {
      var weight = 0;
      if(nric.length != 9) {   //Check for Length(=9)
        return false;
      }
      firstChar = nric.substring(0,1);
      lastChar = nric.substring(8,9);
      upperFirst = nric.substring(0,1).toUpperCase();
      upperLast  = nric.substring(8,9).toUpperCase();
      if (firstChar!=upperFirst) {
        return false;
      }
      if (lastChar!=upperLast) {
        return false;
      }
      if(firstChar == 'T'  || firstChar == 'G') {
        weight = 4;
      } else if(firstChar == 'S' || firstChar == 'F') { 
    	  
      } else {
        return false;
      }
     
      //Calculate the Summation on NRIC No. digits.
      var chkno = parseInt(nric.substring(1,2),10) * 2;
      chkno = chkno + parseInt(nric.substring(2,3),10) * 7;
      chkno = chkno + parseInt(nric.substring(3,4),10) * 6;
      chkno = chkno + parseInt(nric.substring(4,5),10) * 5;
      chkno = chkno + parseInt(nric.substring(5,6),10) * 4;
      chkno = chkno + parseInt(nric.substring(6,7),10) * 3;
      chkno = chkno + parseInt(nric.substring(7,8),10) * 2;
      chkno = chkno + weight;
     
      //Get the Remainder and minus it from 11.
      chkno = chkno%11;
      chkno = 11 - chkno;
     
      if(jp.isLastCharInNRIC(chkno,firstChar,lastChar) == false) {
        return false;
      }
      return true;
    },
    isLastCharInNRIC : function(chkno,firstChar,lastChar){
      var actualChar ="";
      if(firstChar == 'S' || firstChar == 'T') {
        if(chkno == 1) {
          actualChar ="A";
        } else if(chkno == 2) {
          actualChar ="B";
        } else if(chkno == 3) {
          actualChar ="C";
        } else if(chkno == 4) {
          actualChar ="D";
        } else if(chkno == 5) {
          actualChar ="E";
        } else if(chkno == 6) {
          actualChar ="F";
        } else if(chkno == 7) {
          actualChar ="G";
        } else if(chkno == 8) {
          actualChar ="H";
        } else if(chkno == 9) {
          actualChar ="I";
        } else if(chkno == 10) {
          actualChar ="Z";
        } else if(chkno == 11) {
          actualChar ="J";
        }
      }
      if(firstChar == 'G' || firstChar == 'F') {
        if(chkno == 1) {
          actualChar ="K";
        } else if(chkno == 2) {
          actualChar ="L";
        } else if(chkno == 3) {
          actualChar ="M";
        } else if(chkno == 4) {
          actualChar ="N";
        } else if(chkno == 5) {
          actualChar ="P";
        } else if(chkno == 6) {
          actualChar ="Q";
        } else if(chkno == 7) {
          actualChar ="R";
        } else if(chkno == 8) {
          actualChar ="T";
        } else if(chkno == 9) {
          actualChar ="U";
        } else if(chkno == 10) {
          actualChar ="W";
        } else if(chkno == 11) {
          actualChar ="X";
        }
      } 
      if(actualChar == lastChar) {
        return true;
      } else {
        return false;
      }
    },
    //---------------------------------------------- System ------------
    currentLanguage : function() {
	  var lang = getStorageValue(jp.keyLang);
	  if (!lang) {
		var navLang = navigator.language;
		if (navLang.indexOf("zh")==0) {
		  lang = "zh_CN";
		} else {
		  lang = "en_SG";
		}
	  }
	  return lang;
	},
	setCurrentLanguage : function (language) {
	  localStorage.setItem(jp.keyLang, language);
	  currentLanguage = language;
	},
	setCookie : function(cname, cvalue, exdays) {
	  var d = new Date();
	  d.setTime(d.getTime() + (exdays*24*60*60*1000));
	  var expires = "expires="+d.toGMTString();
	  document.cookie = cname + "=" + cvalue + "; " + expires;
	},
	getCookie : function(cname) {
	  var name = cname + "=";
	  var ca = document.cookie.split(';');
	  for(var i=0; i<ca.length; i++) {
	    var c = ca[i];
	    while (c.charAt(0)==' ') c = c.substring(1);
	    if (c.indexOf(name) != -1) return c.substring(name.length,c.length);
	  }
	  return "";
	},
	deleteCookie : function (name) {
	  document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
	},
	supportsLocalStorage : function() {
	  try {
	    return 'localStorage' in window && window['localStorage'] !== null;
	  } catch (e) {
	    return false;
	  }
	},
	getStorageValue : function(name) {
	  var value = sessionStorage.getItem(name);
	  if (!value) {
		  value = localStorage.getItem(name);
	  }
	  return value;
	},
	/**
	 * <p>Load css file names with comma</p>
	 * 
	 * @param fileNames
	 * @returns
	 */
	loadCss : function(fileNames) {
	  if ("all"==fileNames) {
		fileNames = "jqueryUiCss,jpCss";
	  }
	  var fileNameArr = fileNames.split(",");
	  for (var index in fileNameArr) {
		var fileName = fileNameArr[index];
		var cssTag = document.getElementById(fileName);
		var headEl = document.getElementsByTagName('head').item(0);
		if (cssTag) head.removeChild(cssTag);
		var linkEl = document.createElement('link');
		switch(fileName) {
		  case "jqueryUiCss":
			  linkEl.href = "../jqueryui/v1.11.2/jquery-ui.css"; break;
		  case "jpCss":
			  linkEl.href = "jp.css"; break;
		  default:
		      continue;
		} 
		linkEl.rel = 'stylesheet';
		linkEl.type = 'text/css';
		linkEl.id = fileName;
		headEl.appendChild(linkEl);
	  } 
	},

	/**
	 * <p>Load js file names with comma</p>
	 * 
	 * @param fileNames
	 * @returns
	 */
	loadJs : function(fileNames) {
	  if ("all"==fileNames) {
		fileNames = "jqueryJs,jqueryUiJs";
	  }
	  var fileNameArr = fileNames.split(",");
	  for (var index in fileNameArr) {
		var fileName = fileNameArr[index];
		var jsTag = document.getElementById(fileName);
		var headEl = document.getElementsByTagName('head').item(0);
		if (jsTag) headEl.removeChild(jsTag);
		if (fileName=="i18nZhCnJs") {
			var enEl = document.getElementById("i18nEnSgJs");
			if (enEl) {
				headEl.removeChild(enEl);
			}
		} else if (fileName=="i18nEnSgJs") {
			var zhEl = document.getElementById("i18nZhCnJs");
			if (zhEl) {
				headEl.removeChild(zhEl);
			}
		}
		var scriptEl = document.createElement('script');
		scriptEl.id = fileName;
		scriptEl.type = "text/javascript";
		switch(fileName) {
		  case "jqueryJs":
			  scriptEl.src = "../jquery/jquery-1.11.1.js"; break;
		  case "jqueryUiJs":
			  scriptEl.src = "/jquery-ui/v1.11/jquery-ui.js"; break;
		  case "i18nEnSgJs":
			  scriptEl.src = "i18n/i18n_en_SG.js"; break;
		  case "i18nZhCnJs":
			  scriptEl.src = "i18n/i18n_zh_CN.js"; break;
		  default:
		      continue;
		}
		headEl.appendChild(scriptEl);
	  }
	}
  });
})();

