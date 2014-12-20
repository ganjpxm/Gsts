package org.ganjp.core.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * jsonObject:{}  jsonArr:[]
 * char a='[' ;//91   char b='{';//123  char c=']';//93  char d='}';//125
 * char e=',';//44	  char f='"';//34   char g=':';//58
 * @author ganjp
 *
 */
public class JsonUtil {
	private static Logger log = LoggerFactory.getLogger(JsonUtil.class);
	public static String EMPTY_JSON_OBJECT_STR = "{}";
	public static String EMPTY_JSON_ARR_STR = "[]";
	
	public static void main(String[] args) throws Exception {
		String jsonStr = "[{id:\"4028cb8131d1e59e0131d1e59edb0000\", \"cd\":\"0101\",\"level\":3,\"isParent\":true}]";
		List list = (List)toObject(jsonStr);
		Map map = (Map)list.get(0);
		System.out.println(map.get("id"));
		System.out.println(map.get("cd"));
		System.out.println(map.get("level"));
		System.out.println(map.get("isParent"));
	}

	//-------------------------------------------- json转化成object ----------------------------------	
	/**
	 * 把json字符串转化成对象
	 * @param jsonStr
	 * @return object
	 */
	public static Object toObject(String jsonStr){
		List colls = new ArrayList();
		List results = new ArrayList();
		List strList = toStrList(jsonStr);
		boolean flag = false;
		String key = null;
		String outerKey = null;
		for (int i=0; i<strList.size(); i++) {
			Object obj = strList.get(i);
			if (obj.toString().equals("[")) {
				List list = new ArrayList();
				colls.add(list);
			} else if (obj.toString().equals("{")) {
				Map map = new HashMap();
				colls.add(map);
			} else if (obj.toString().equals("}")) {
				if (flag) {
					Map map = (Map)colls.get(colls.size()-1);
					if (colls.get(colls.size()-2) instanceof Map) {
						((Map)colls.get(colls.size()-2)).put(key, map);
					} else if (colls.get(colls.size()-2) instanceof List) {
						((List)colls.get(colls.size()-2)).add(map);
					}
					colls.remove(colls.size()-1);
					flag=false;
				} else {
					if ((colls.size()-2)>=0) {
						if(colls.get(colls.size()-2) instanceof Map){
							((Map)colls.get(colls.size()-2)).put(outerKey, colls.get(colls.size()-1));
						}else if(colls.get(colls.size()-2) instanceof List){
							((List)colls.get(colls.size()-2)).add(colls.get(colls.size()-1));
						}
					} else {
						results.add(colls.get(colls.size()-1));
					}
					colls.remove(colls.size()-1);
				}
			} else if (obj.toString().equals("]")) {
				if (flag) {
					List list=(List)colls.get(colls.size()-1);
					if ((colls.size()-2)>0) {
						if (colls.get(colls.size()-2) instanceof Map) {
							((Map)colls.get(colls.size()-2)).put(outerKey, list);
						} else if(colls.get(colls.size()-2) instanceof List) {
							((List)colls.get(colls.size()-2)).add(list);
						}
					}
					colls.remove(colls.size()-1);
					flag = false;
				} else {
					if ((colls.size()-2)>=0) {
						if (colls.get(colls.size()-2) instanceof Map) {
							((Map)colls.get(colls.size()-2)).put(outerKey, colls.get(colls.size()-1));
						} else if(colls.get(colls.size()-2) instanceof List) {
							((List)colls.get(colls.size()-2)).add(colls.get(colls.size()-1));
						}
					} else {
						results.add(colls.get(colls.size()-1));
					}
					colls.remove(colls.size()-1);
				}
			} else if(obj.toString().equals(",")) {
			} else if(obj.toString().equals(":")) {
			} else {
				if (colls.get(colls.size()-1) instanceof List) {
					((List)colls.get(colls.size()-1)).add(obj);
				}
				if (colls.get(colls.size()-1) instanceof Map) {
					String string = strList.get(i+2).toString();
					char ch = string.charAt(0);
					String str = obj.toString();
					if (!((int)ch==91) && !((int)ch==123)) {
						((Map)colls.get(colls.size()-1)).put(obj, strList.get(i+2));
						i = i+2;
					} else if ((int)ch==123) {
						flag = true;
						key = strList.get(i).toString();
					} else if ((int)ch==91 && ((int)(strList.get(i+3).toString().charAt(0))==123)) {
						flag = true;
						key = strList.get(i).toString();
					}
				}
				if (colls.get(colls.size()-1) instanceof Map) {
					String string = null;
					if ((i+2)<=(strList.size()-1)) {
						string = strList.get(i+2).toString();
					}
					char ch=0;
					if (string!=null) {
						ch = string.charAt(0);
					}
					if ((int)ch==91 && ((int)(strList.get(i+3).toString().charAt(0))!=123)) {
						flag = true;
						outerKey = strList.get(i).toString();
					}
				}
			}
		}
		if (results.size()==1) {
			return results.get(0);
		}
		return null;
	}
	/**
	 * 把json字符串逐个添加到List
	 * 输入："[{\"id\":\"001\", \"name\":\"ganjp\"}]"
	 * 输出：List([,{,id,:,001,name,:,ganjp,},])
	 * @param jsonStr
	 * @return List(string)
	 */
	private static List toStrList(String jsonStr){
		List strList = new ArrayList();
		int intBef = 0;
		int intAft = 0;
		int count = 0;
		boolean flag = false;
		String ret = null;
		char c1 = 0;
		char c2 = 0;
		for (int i=0; i<jsonStr.length(); i++) {
			char ch = jsonStr.charAt(i);
			int chInt = (int)ch;
			if ((chInt==34)) { //'"'
				count++;
				if(count==1){
					intBef=i;
					flag=true;
				}
				if(count==2){
					intAft=i;
					flag=false;
					count=0;
					ret=jsonStr.substring(intBef+1, intAft);
					intBef=0;
					intAft=0;
					strList.add(ret);
				}
			} else if ((chInt==91 || chInt==123 || chInt==44 || chInt==58) && !flag) {// 91([) 123({) 44(:) 58(,) 34(")
				//对key或没有"的处理
				if ((int)(jsonStr.charAt(i+1))!=34 && (int)(jsonStr.charAt(i+1))!=91 && (int)(jsonStr.charAt(i+1))!=123) {
					intBef = i;
					flag = true;
					c1 = ch;
				}
				strList.add(new Character(ch));
			} else if ((chInt==93 || chInt==125 || chInt==44 || chInt==58) && flag) {//125(})
				c2 = ch;
				intAft = i;
				ret = jsonStr.substring(intBef+1, intAft);
				strList.add(ret);
				if ((int)c1==44 && (int)c2==44) {
					i=i-1;
				} else if ((int)c1==44 && ((int)c2==44 || (int)c2==125)) {
					i=i-1;
				} else if ((int)c1==91 && (int)c2==44) {
					i=i-1;
				}
				intBef = 0;
				intAft = 0;
				c1 = 0;
				c2 = 0;
				flag = false;
				strList.add(new Character(ch));
			} else {
				if (!flag) {
					strList.add(new Character(ch));
				}
			}
		}
		return strList;
	}
	
	//-------------------------------------------- object转化成json ----------------------------------	
	/**
	 * 把对象转化成json字符串
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj){
		StringBuffer sb = new StringBuffer();
		if(obj instanceof List){
			sb.append("[");
			List list=(List)obj;
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Object object = (Object) iterator.next();
				if (object instanceof List) {
					sb.append(toJson(object));
					sb.append(",");
				} else if(object instanceof Object[]) {
					sb.append(toJson(object));
					sb.append(",");
				} else if(object instanceof Map) {
					sb.append(toJson(object));
					sb.append(",");
				} else {
					if (object instanceof String) {
						sb.append(StringUtil.quoteAndReplaceTransferChar((String)object)).append(",");
					} else {
						sb.append(object).append(",");
					}
				}
			}
			sb.deleteCharAt(sb.length()-1);
			sb.append("]");
		} else if (obj instanceof Object[]) {
			sb.append("[");
			Object[] objs=(Object[])obj;
			for (int i = 0; i < objs.length; i++) {
				Object value = objs[i];
				if (value instanceof List) {
					sb.append(toJson(value));
					sb.append(",");
				} else if(value instanceof Object[]) {
					sb.append(toJson(value));
					sb.append(",");
				} else if(value instanceof Map) {
					sb.append(toJson(value));
					sb.append(",");
				} else {
					if (value instanceof String) {
						sb.append("\"").append(value).append("\"").append(",");
					} else {
						sb.append(value).append(",");
					}
				}
			}
			sb.deleteCharAt(sb.length()-1);
			sb.append("]");
		}
		if (obj instanceof Map) {
			sb.append("{");
			Map map=(Map)obj;
			Set keySet = map.keySet();
			for (Iterator iterator2 = keySet.iterator(); iterator2.hasNext();) {
				Object key = (Object) iterator2.next();
				Object value = map.get(key);
				if (value instanceof List) {
					sb.append("\"").append(key).append("\"").append(":");
					sb.append(toJson(value));
					sb.append(",");
				} else if(value instanceof Object[]) {
					sb.append("\"").append(key).append("\"").append(":");
					sb.append(toJson(value));
					sb.append(",");
				} else if(value instanceof Map) {
					sb.append("\"").append(key).append("\"").append(":");
					sb.append(toJson(value));
					sb.append(",");
				} else {
					if (value instanceof String) {
						sb.append("\"").append(key).append("\"").append(":").append("\"").append(value).append("\"").append(",");
					} else {
						sb.append("\"").append(key).append("\"").append(":").append(value).append(",");
					}
				}
			}
			sb.deleteCharAt(sb.length()-1);
			sb.append("}");
		}
		return sb.toString();
	}
	
	/**
	 * 获取json的key对应的原始值
	 * 
	 * @param obj
	 * @return
	 */
	public static String getPrimitiveValue(Object obj) {
		if (obj == null) {  
            return "null";  
        } else if (obj instanceof String) {  
            return StringUtil.quoteJsonLib((String)obj);  
        } else if (obj instanceof Number || obj instanceof Character || obj instanceof Boolean) {  
        	if (obj instanceof Character) {  
                Character c = (Character) obj;  
                char[] carr = {c.charValue()};  
                return StringUtil.quoteJsonLib((new String(carr)));  
            } else {  
            	return String.valueOf(obj);  
            }  
        } else if (obj instanceof Date || obj instanceof Timestamp) {  
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
            String date = format.format((Date)obj);  
            return "\""+ date +"\"";  
        } else {
        	return "\""+ String.valueOf(obj) + "\"";
        }
	}
	//--------------------------------  对json字符串的操作 --------------------------------------
	/**
	 * 向json数组增加json对象
	 * @param jsonArrStr
	 * @param jsonObjectStr
	 * @return
	 */
	public static String addJsonObject(StringBuffer jsonArrStrBuffer, StringBuffer jsonObjectStrBuffer) {
		if (EMPTY_JSON_ARR_STR.equals(jsonArrStrBuffer.toString())) {
			jsonArrStrBuffer.insert(jsonArrStrBuffer.length()-1, jsonObjectStrBuffer);
		} else {
			jsonArrStrBuffer.insert(jsonArrStrBuffer.length()-1, jsonObjectStrBuffer.insert(0, ","));
		}
		return jsonArrStrBuffer.toString();
	}
	
	/**
	 * 向json对象增加json的key:value对应项
	 * @param jsonObjectStr
	 * @param jsonObjectItem
	 * @return
	 */
	public static String addJsonObjectItem(StringBuffer jsonObjectStrBuffer, String jsonObjectItem) {
		if (EMPTY_JSON_OBJECT_STR.equals(jsonObjectStrBuffer.toString())) {
			jsonObjectStrBuffer.insert(jsonObjectStrBuffer.length()-1, jsonObjectItem);
		} else {
			jsonObjectStrBuffer.insert(jsonObjectStrBuffer.length()-1, "," + jsonObjectItem);
		}
		return jsonObjectStrBuffer.toString();
	}
	
	/**
	 * 获取json中key对应的value值赋予sql的参数值
	 * 
	 * 输入1：getParamList("[{name:ganjp,isParent:true}]", "name,isParent", new Map().put("isParent","true:1,false:0"));
	 * 输出1：List(new Object[]{"ganjp","1"})
	 * @param jsonArrStr
	 * @param dealKeyMap
	 * @return
	 */
	public static List getParamArrList(String jsonArrStr, String paramKeys, Map dealKeyMap) {
		if (StringUtil.isBlank(jsonArrStr) || JsonUtil.EMPTY_JSON_ARR_STR.equals(jsonArrStr)) {
			return null;
		}
		Object mapListObject = toObject(jsonArrStr);
		if (mapListObject == null) {
			return null;
		} else {
			return getParamArrList((List)mapListObject, paramKeys, dealKeyMap);
		}
	}
	
	public static List getParamArrList(List mapList, String paramKeys, Map dealKeyMap) {
		if (mapList==null || mapList.isEmpty() || StringUtil.isBlank(paramKeys)) {
			return null;
		}
		String[] paramKeyArr = paramKeys.split(",");
		List paramArrList = new ArrayList();
		for (Iterator iterator = mapList.iterator(); iterator.hasNext();) {
			Map map = (Map) iterator.next();
			if (map.isEmpty() || paramKeyArr.length != map.size()) {
				log.error("map的个数与paramKeys不一致!");
				continue;
			}
			Object[] paramArr = new Object[map.size()];
			for (int j = 0; j < paramKeyArr.length; j++) {
				Object key = paramKeyArr[j];
				//需要特殊处理的key值
				if (dealKeyMap!=null && dealKeyMap.containsKey(key)) {
					//如果dealKeyMap里的主键集合里面有key值 那么进行对应的替换
					Object dealKeyvalue = dealKeyMap.get(key);
					String keyValue = (String)map.get(key);
					paramArr[j] = StringUtil.getAfterColonValue(dealKeyvalue.toString(), keyValue);
				} else {
					paramArr[j] = map.get(key);
				}
			}
			paramArrList.add(paramArr);
		}
		return paramArrList;
	}
	
	public static List getParamArrList(String jsonArrStr,String paramKeys) {
		return getParamArrList(jsonArrStr, paramKeys, null);
	}
	
}
