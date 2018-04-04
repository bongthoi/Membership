package vn.vmall.Helper;

import java.io.UnsupportedEncodingException;
import java.util.UUID;
import org.apache.axis.encoding.Base64;

public class EncrypterDecrypter {
	public static String encodeString(String text) {
		byte[] bytes = null;
		try {
			bytes = text.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String encodeString = Base64.encode(bytes);
		return encodeString;
	}
	public static String decodeString(String encodeText) throws UnsupportedEncodingException {
		byte[] decodeBytes = Base64.decode(encodeText);
		String str = new String(decodeBytes, "UTF-8");
		return str;
	}
	public static String get_string_random(){
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}
	public static String encodeCategory(String cate){
		String uuid = cate+"&"+UUID.randomUUID().toString();
		String str = encodeString(uuid);
		return str;
	}
	public static String decodeCategory(String str){
		String uuid="";
		try {
			uuid = decodeString(str);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[]arr = uuid.split("&");
		String strre = "";
		if(arr.length>0){
			strre = arr[0];
		}		
		return strre;
	}
	public static void main(String[] args) {
		try {
			String cate = "001";
			String en = encodeCategory(cate);			
			String de = decodeCategory(en);
			System.out.println(en);
			System.out.println(de);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

