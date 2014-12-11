package org.ganjp.gcore.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {  
    protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };  
  
    protected static MessageDigest messagedigest = null;  
    static {  
        try {  
            messagedigest = MessageDigest.getInstance("MD5");  
        } catch (NoSuchAlgorithmException nsaex) {  
            System.err.println(MD5Util.class.getName()  + " init fail, MessageDigest don't supportMD5Util.");  
            nsaex.printStackTrace();  
        }  
    }  
      
    /** 
     * <p>Get MD5 String</p>
     *  
     * @param s 
     * @return 
     */  
    public static String getMD5String(String s) {  
        return getMD5String(s.getBytes());  
    }  
      
    /**
     * <p>Is string MD5 varify code equal string MD5ed</p>
     *  
     * @param password
     * @param md5PwdStr
     * @return 
     */  
    public static boolean checkString(String str, String md5Str) {  
        String s = getMD5String(str);  
        return s.equals(md5Str);  
    }  
      
    /** 
     * <p>Get file md5 verify code.</p>
     *  
     * @param file 
     * @return 
     * @throws IOException 
     */  
    public static String getFileMD5String(File file) throws IOException {         
        InputStream fis;  
        fis = new FileInputStream(file);  
        byte[] buffer = new byte[1024];  
        int numRead = 0;  
        while ((numRead = fis.read(buffer)) > 0) {  
            messagedigest.update(buffer, 0, numRead);  
        }  
        fis.close();  
        return bufferToHex(messagedigest.digest());  
    }  
  
    public static String getMD5String(byte[] bytes) {  
        messagedigest.update(bytes);  
        return bufferToHex(messagedigest.digest());  
    }  
  
    private static String bufferToHex(byte bytes[]) {  
        return bufferToHex(bytes, 0, bytes.length);  
    }  
  
    private static String bufferToHex(byte bytes[], int m, int n) {  
        StringBuffer stringbuffer = new StringBuffer(2 * n);  
        int k = m + n;  
        for (int l = m; l < k; l++) {  
            appendHexPair(bytes[l], stringbuffer);  
        }  
        return stringbuffer.toString();  
    }  
  
    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {  
        char c0 = hexDigits[(bt & 0xf0) >> 4];
        char c1 = hexDigits[bt & 0xf];
        stringbuffer.append(c0);  
        stringbuffer.append(c1);  
    }  
      
    public static void main(String[] args) throws IOException {  
//        long begin = System.currentTimeMillis();  
//  
//        File file = new File("C:/12345.txt");  
//        String md5 = getFileMD5String(file);  
//  
//        long end = System.currentTimeMillis();  
//        System.out.println("md5:" + md5 + " time:" + ((end - begin) / 1000) + "s");  
    	
    	//mid=1000088918&order_number=AC001566&result=Paid&confirmation_code=78C501&transaction_id=000011025598&authorization_code=T24071&
    	//card_number=XXXXXXXXXXXX1111&currency_code=SGD&amount=0.01&authorized_currency_code=SGD&authorized_amount=0.01&signature=5ea4fa4e045e5a1179b69b50f360ce1a
        String str = "authorization_code=123456&confirmation_code=6ACB2926&order_number=12345 &result=Paid&transaction_id=3711184054&secret_key=REDDOT";
		String result = "a7e482e0719b7b1bbfa2b5f53a8e0b12";
		System.out.println(getMD5String(str));
		
		String str1 = "amount=0.01&authorized_amount=0.01&authorization_code=T24071&authorized_currency_code=SGD&card_number=XXXXXXXXXXXX1111" + 
		"&confirmation_code=78C501&currency_code=SGD&mid=1000088918&order_number=AC001566&result=Paid&transaction_id=000011025598&secret_key=y1h3sp";
		System.out.println(getMD5String(str1));
    }  
}  
