package org.ganjp.core.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class CoderUtil {  
	private static String PREFIX = "\\u";
	
    public static char ascii2Char(int ASCII) {  
        return (char) ASCII;  
    }  
  
    public static int char2ASCII(char c) {  
        return (int) c;  
    }  
  
    public static String ascii2String(int[] ASCIIs) {  
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < ASCIIs.length; i++) {  
            sb.append((char) ascii2Char(ASCIIs[i]));  
        }  
        return sb.toString();  
    }  
  
    public static String ascii2String(String ASCIIs) {  
        String[] ASCIIss = ASCIIs.split(",");  
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < ASCIIss.length; i++) {  
            sb.append((char) ascii2Char(Integer.parseInt(ASCIIss[i])));  
        }  
        return sb.toString();  
    }  
  
    public static int[] string2ASCII(String s) {// 字符串转换为ASCII码  
        if (s == null || "".equals(s)) {  
            return null;  
        }  
  
        char[] chars = s.toCharArray();  
        int[] asciiArray = new int[chars.length];  
  
        for (int i = 0; i < chars.length; i++) {  
            asciiArray[i] = char2ASCII(chars[i]);  
        }  
        return asciiArray;  
    }  
  
    public static String getIntArrayString(int[] intArray) {  
        return getIntArrayString(intArray, ",");  
    }  
  
    public static String getIntArrayString(int[] intArray, String delimiter) {  
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < intArray.length; i++) {  
            sb.append(intArray[i]).append(delimiter);  
        }  
        return sb.toString();  
    }  
  
    public static String getASCII(int begin, int end) {  
        StringBuffer sb = new StringBuffer();  
        for (int i = begin; i < end; i++) {  
            sb.append(i).append(":").append((char) i).append("\t");  
            // sb.append((char) i).append("\t");  
            if (i % 10 == 0) {  
                sb.append("\n");  
            }  
        }  
        return sb.toString();  
    }  
  
    public static String getCHASCII(int begin, int end) {  
        return getASCII(19968, 40869);  
    }  
  
    public static void showASCII(int begin, int end) {  
        for (int i = begin; i < end; i++) {  
            // System.out.print(i + ":" + (char) i + "\t");  
            System.out.print((char) i + "\t");  
            if (i % 10 == 0) {  
                System.out.println();  
            }  
        }  
    }  
  
    public static void showCHASCII() {  
        showASCII(19968, 40869);  
    }  
  
    public static void showIntArray(int[] intArray) {  
        showIntArray(intArray, ",");  
    }  
  
    public static void showIntArray(int[] intArray, String delimiter) {  
        for (int i = 0; i < intArray.length; i++) {  
            System.out.print(intArray[i] + delimiter);  
        }  
    }  
  
    public static void createFile(String filePathAndName, String fileContent)  
            throws IOException {  
  
        String filePath = filePathAndName;  
        filePath = filePath.toString();  
        File myFilePath = new File(filePath);  
        if (!myFilePath.exists()) {  
            myFilePath.createNewFile();  
        }  
        FileWriter resultFile = new FileWriter(myFilePath);  
        PrintWriter myFile = new PrintWriter(resultFile);  
        String strContent = fileContent;  
        myFile.println(strContent);  
        myFile.close();  
        resultFile.close();  
    }  
  
    public static String byteToHex(byte b) {   
        return Integer.toHexString((b & 0x000000FF) | 0xFFFFFF00).substring(6).toUpperCase();   
    } 
    
    public static void charToUTF8(char chineseChar) {   
        try {   
            //--中文字符转UTF-8   
            byte[] encodeArr = String.valueOf(chineseChar).getBytes("UTF-8");   
            //十进制编码输出   
            for (int i = 0; i < encodeArr.length; i++) {   
                //-28 -72 -83   
                System.out.print(encodeArr[i] + " ");   
            }   
            System.out.println();   
            //十六进制编码输出   
            for (int i = 0; i < encodeArr.length; i++) {   
                //E4 B8 AD   
                System.out.print(byteToHex(encodeArr[i]) + " ");   
            }   
            System.out.println();   
//            //所占字节数   
//            System.out.println(encodeArr.length);//3   
//            //--英文字符转UTF-8   
//            encodeArr = String.valueOf(englishChar).getBytes("UTF-8");   
//            //十进制编码输出   
//            for (int i = 0; i < encodeArr.length; i++) {   
//                //97   
//                System.out.print(encodeArr[i] + " ");   
//            }   
//            System.out.println();   
//            //十六进制编码输出   
//            for (int i = 0; i < encodeArr.length; i++) {   
//                //61   
//                System.out.print(byteToHex(encodeArr[i]) + " ");   
//            }   
//            System.out.println();   
//            //所占字节数   
//            System.out.println(encodeArr.length);//1   

        } catch (UnsupportedEncodingException e) {   
            e.printStackTrace();   
        }   
    }   
    
    public static String native2Ascii(String str) {
    	char[] chars = str.toCharArray();
    	StringBuffer sb = new StringBuffer();
    	for (int i = 0; i < chars.length; i++) {
    	sb.append(char2Ascii(chars[i]));
    	}
    	return sb.toString();
    }
    
    private static String char2Ascii(char c) {
    	if (c > 255) {
    		StringBuffer sb = new StringBuffer();
	    	sb.append(PREFIX);
	    	int code = (c >> 8);
	    	String tmp = Integer.toHexString(code);
	    	if (tmp.length() == 1) {
	    		sb.append("0");
	    	}
	    	sb.append(tmp);
	    	code = (c & 0xFF);
	    	tmp = Integer.toHexString(code);
	    	if (tmp.length() == 1) {
	    		sb.append("0");
	    	}
	    	sb.append(tmp);
	    	return sb.toString();
    	} else {
    		return Character.toString(c);
    	}
    }
    
    public static String toHexString1(byte[] b)
    {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < b.length; ++i)
        {
            buffer.append(toHexString1(b[i]));
        }
        return buffer.toString();
    }

    public static String toHexString1(byte b) {
        String s = Integer.toHexString(b & 0xFF);
        if (s.length() == 1)
        {
            return "0" + s;
        } else
        {
            return s;
        }
    }

    public static String toHexString2(byte[] b) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < b.length; ++i)
        {
            buffer.append(toHexString2(b[i]));
        }
        return buffer.toString();
    }

    public static String toHexString2(byte b) {
        char[] buffer = new char[2];
        buffer[0] = Character.forDigit((b >>> 4) & 0x0F, 16);
        buffer[1] = Character.forDigit(b & 0x0F, 16);
        return new String(buffer);
    }
    public static void main(String[] args) throws IOException {  
//        String s = "烤房ID";  
//        showIntArray(string2ASCII(s), " ");  
//        System.out.println();  
//        System.out.println(ascii2String(string2ASCII(s)));  
//        charToUTF8('烤');
//        System.out.println(native2Ascii("烤房ID"));
    	  System.out.println(Integer.parseInt("120",16));
    }  
  
}  
