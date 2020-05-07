package com.feel.common.config;
import java.util.UUID;

/**
 * 
 *
 * @author liaijun
 * @version  3.4, 2016年8月5日 上午10:44:22 
 * @since   Yeexun 3.4
 */
public class Base64Encoder {
    @SuppressWarnings("restriction")
	public static String getBASE64(String s) {  
        if (s == null)  
            return null;  
        return (new sun.misc.BASE64Encoder()).encode(s.getBytes());  
    }  
    // 将 BASE64 编码的字符串 s 进行解码   解密
    @SuppressWarnings("restriction")
	public static String getFromBASE64(String s) {  
        if (s == null)  
            return null;  
        sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();  
        try {  
            byte[] b = decoder.decodeBuffer(s);  
            return new String(b);  
        } catch (Exception e) {  
            return null;  
        }  
    }  
    public static String mTOa(Object ming){
        return Base64Encoder.getBASE64(Base64Encoder.getBASE64(Base64Encoder.getBASE64((String)ming)));
    }
    public static String aTOm(String an){
        return Base64Encoder.getFromBASE64(Base64Encoder.getFromBASE64(Base64Encoder.getFromBASE64(an)));
    }
    public static void main(String[] args) {
    	String str=UUID.randomUUID().toString().replaceAll("-", "");
    	System.out.println("================="+str);
    	str=str+""+(int)((Math.random()*9+1)*10);
		String msg = "1000_1_"+str;
          String a = mTOa(msg.toString());
          System.out.println(">>>>>>>>>>>>>>>>>>>>>"+mTOa(""+100));
          System.out.println(a);//加密
          System.out.println(mTOa(49+"")+"        >>>>>>>>>>>>>>>>>>>>");//加密
          System.out.println("=================");//加密
          System.out.println(aTOm(a));//解密
          System.out.println("=================");
          System.out.println(aTOm("VFZSQmQwMUVRWGROUkVGM1RVUkNaazVFU1hoT2VrcHNUVmRTYUUxNlZYZE9SRTE2VFZSck5VNXFa\r\n" + 
          		"ekJPUkdkNlQwZFdiRnBFWnpCTw0KYWxwbVRWRTlQUT09"));//解密
          System.out.println("=================");
          System.out.println(aTOm("VFZSQmQwMUVRWGROUkVGM1RVUkNaazVFU1hoT2VrcHNUVmRTYUUxNlZYZE9SRTE2VFZSck5VNXFaekJPUkdkNlQwZFdiRnBFWnpCTw0KYWxwbVRWRTlQUT09"));//解密
    }
}