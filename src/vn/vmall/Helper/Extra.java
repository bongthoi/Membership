package vn.vmall.Helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;


public class Extra {
	private final static String default_lang="VN";
	public static String nganluong_url = "https://www.nganluong.vn/checkout.php?";
    public static String merchant_site_code = "47145";	//thay mã merchant site mà b?n dã dang ký vào dây
    public static String secure_pass = "5dd8b6ff7b17127c8fa8ceb7958906c5";   
    
    
    
    
	public static String get_current_lang(HttpServletRequest request){
		HttpSession session = request.getSession();	
		String lang=session.getAttribute("language")==null?default_lang:session.getAttribute("language").toString();
		return lang;
	}
	
	public static String replace_origin_html(String str_html){
		return str_html.replace( "&amp;","&").replace("&lt;","<").replace("&gt;",">").replace( "&quot;","\"").replace("&blink;","'");
	}
	public static String replace_html_origin(String str_html){
		return str_html.replace( "&","&amp;").replace("<","&lt;").replace(">","&gt;").replace( "\"","&quot;").replace("'","&blink;");
	}
public static String enscript(String str){
		
		String result = "";
		char[]arr = str.toCharArray();
		for(int i=0;i<arr.length;i++){
			int n = (int)arr[i];//convert to 10
			result += convert_binary(n);
		}
		return result;
	}
	public static String convert_binary(int n){
		String result = "";
		int[]arr = {1,2,4,8,16,32,64};
		for(int i= arr.length - 1 ;i >=0 ;i--){
			if(n>=arr[i]){
				result+="1";
				n-=arr[i];
			}
			else{
				result+="0";
			}
		}
		return result;
	}
	public static  String descript(String str){
		String result = "";
		char[]arr = str.toCharArray();
		int leng = str.length()/7;
		for(int i=0;i<leng;i++){
			String _10= str.substring(i*7,(i+1)*7);
			result +=convert_10(_10);
		}
		return result;
	}
	public static char convert_10(String str){		
		int _10=0;
		int[]arr = {1,2,4,8,16,32,64};
		char[]arr_str = str.toCharArray();
		for(int i=0;i<str.length();i++){
			if(arr_str[7-1-i]=='1'){
				_10+=arr[i];
			}
		}		
		char _c = (char)_10;
		return _c;
	}
	public static String getRandomString(int loai, int dodai) {       
        //Loai : kieu ran dom
        //0 : ngau nhien chi cac chu cai thuong
        //1 : ngau nhien chi cac chu cai hoa
        //2 : ngau nhien ca chu hoa va thuong
        //3 : ngau nhien chu va so
        String ketqua = "";
        String hoa = "QWERTYUIOPASDFGHJKLZXCVBNM";
        String thuong = hoa.toLowerCase();
        String so = "1234567890";
        String randomchuoi = "";
        if (loai > 3 || loai < 0) {
            ketqua = "Loai khong hop le, cho phep tu 0 - 3";
        } else if (loai == 0) {
            randomchuoi = thuong;
        } else if (loai == 1) {
            randomchuoi = hoa;
        } else if (loai == 2) {
            randomchuoi = hoa + thuong;
        } else if (loai == 3) {
            randomchuoi = hoa + thuong + so;
        }
        for (int i = 0; i < dodai; i++) {
            int temp = (int) Math.round(Math.random() * randomchuoi.length());
            ketqua += randomchuoi.charAt(temp);
        }
        return ketqua;
    }
	public static String readFile(String filename) throws IOException
	{
		String content="";
		
		
		BufferedReader in = new BufferedReader(
		   new InputStreamReader(
                      new FileInputStream(filename), "UTF8"));
		        
		String str;		      
		while ((str = in.readLine()) != null) {
		    content+=str;
		}		        
        in.close();
        return content;	   
	}
	public static String format_currency(String price) {
		NumberFormat formatter = new DecimalFormat("###,###");
		String resp = formatter.format(Double.parseDouble(price));
		resp = resp.replaceAll(",", ".");
		return resp;
	}
	public static String format_date(String strdate) {
		 String pattern = "yyyy-MM-dd";
		 SimpleDateFormat format = new SimpleDateFormat(pattern);
		 Date date = null;
		try {
			date = format.parse(strdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return format.format(date);
	}
	public static void main(String[] args) {
		System.out.println(format_date("2016-06-21 00:00:00"));
	}
}
