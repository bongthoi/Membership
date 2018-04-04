package vn.vmall.Helper;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class responseUtf8 {

	
	public static void response_Utf8(HttpServletResponse response,String text) throws IOException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		 out.print(text);
	}
}
