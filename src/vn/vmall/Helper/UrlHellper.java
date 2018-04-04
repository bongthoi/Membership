package vn.vmall.Helper;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.utils.URIBuilder;


public class UrlHellper {

	public static String get_url(HttpServletRequest request,Map<String,String> param) throws URISyntaxException{
		/*String uri = request.getScheme() + "://" +   // "http" + "://
	             request.getServerName() +       // "myhost"
	             ":" + request.getServerPort() + // ":" + "8080"
	             request.getRequestURI() +       // "/people"
	            (request.getQueryString() != null ? "?" +
	             request.getQueryString() : ""); // "?" + "lastname=Fox&age=30"
		*/
		URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setPath(request.getRequestURI());
        for ( String key : param.keySet() ) {
        	uriBuilder.setParameter(key, param.get(key));
			    //System.out.println( key );
			}
		//UriBuilder builder = new UriBuilder();
			/*UriBuilder builder =new UriBuilder();
		            /*.fromPath("www.leveluplunch.com")
		            .scheme(request.getScheme())
		            .path(request.getRequestURI());
		   for ( String key : param.keySet() ) {
			   builder=builder.queryParam(key, param.get(key));
			    //System.out.println( key );
			}*/
    
		   URI uri = uriBuilder.build();

		   return uri.toString();
		  
		
	}
	public static String get_uribylastpath(HttpServletRequest request,String laspath){
		String new_uri=request.getContextPath()+""+request.getServletPath()+""+laspath;
		return new_uri;
	}
}
