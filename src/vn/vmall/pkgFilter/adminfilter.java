package vn.vmall.pkgFilter;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import vn.vmall.AdminController.ad_loginController;
import vn.vmall.Helper.ErrorMessageModel;

//@WebFilter({"/123123"})
public class adminfilter implements Filter {
		private ArrayList<String> urlList;
		private String contextpath;
		private String urltemp="";
		private String Redirect_contextpath;
	   public void  init(FilterConfig config) 
	                         throws ServletException{
		   	String urls = config.getInitParameter("avoid-urls");
		   	urltemp=urls;
			StringTokenizer token = new StringTokenizer(urls, ",");
			contextpath=config.getInitParameter("contextpath");
			Redirect_contextpath=config.getInitParameter("Redirect_contextpath");
			urlList = new ArrayList<String>();
			while (token.hasMoreTokens()) {
				urlList.add(contextpath+""+token.nextToken());
			}
	   }
	   public void  doFilter(ServletRequest request, 
	                 ServletResponse response,
	                 FilterChain chain) 
	                 throws java.io.IOException, ServletException {
	      // Get the IP address of client machine.   
	      String ipAddress = request.getRemoteAddr();
	      // Log the IP address and current timestamp.
	     /* //System.out.println("IP "+ ipAddress + ", Time "
	                                       + new Date().toString());*/
	      HttpServletResponse res = (HttpServletResponse) response;  
	      HttpServletRequest req = (HttpServletRequest) request;  
	     // PrintWriter out=response.getWriter();
	      String url = req.getRequestURI();
	  	//  String url = req.getServletPath();
	 	/* //System.out.println("req PATHTinfo:"+  req.getPathInfo()); 
	  	 //System.out.println("req PATHTRABS:"+  req.getPathTranslated()); 
	 	 //System.out.println("req URL:"+req.getRequestURL()); 
	  	 //System.out.println("req uri:"+req.getRequestURI()); */
	  	/*System.out.println("url uri:"+url);
	  	 System.out.println("urlList:"+urltemp);
		 System.out.println("-----------------------------------------------------------------------------------");*/
	  	  boolean allowedRequest = false;
		
			if(urlList.contains(url)) {
			// System.out.println("da~ contants");
				allowedRequest = true;
			}
		 ErrorMessageModel mes= new ErrorMessageModel();
		 Gson gs=new Gson();
	      if (!allowedRequest) {
				HttpSession session = req.getSession(false);
				if (null == session) {
							res.setStatus(403);
							PrintWriter out=res.getWriter();
							mes.setF(403);
							mes.setMessage("Access denied !No session");
							out.print(gs.toJson(mes));

				}else{
					  if(session.getAttribute(ad_loginController.KEY_SESSION_USER) == null){
						  			res.setStatus(403);
									PrintWriter out=res.getWriter();
									mes.setF(403);
									mes.setMessage("Access denied !No session");
									out.print(gs.toJson(mes));
			    	  }else{
			    		  chain.doFilter(request,response);
			    	  }
				}
				
			}else{//allowedRequest=true
				HttpSession session = req.getSession(false);
				if(session != null&&session.getAttribute(ad_loginController.KEY_SESSION_USER) != null){			
						PrintWriter out=res.getWriter();
						mes.setF(-1);
						mes.setMessage("Can't do login again!beacause session already !");
						out.print(gs.toJson(mes));
				}else{
					chain.doFilter(request,response);
				}				
			}
	   }
	   public void destroy( ){
	      /* Called before the Filter instance is removed 
	      from service by the web container*/
	   }
	}
