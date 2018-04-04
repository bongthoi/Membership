<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div class="row">
        <div class="col-xs-12 col-sm-12 col-md-4" >
	        <div class="block_image" >
	         <img src="${path_product_img}<c:out value="${product.product_image}"/>"  class="img img-responsive">
        	</div>
        </div>
        
         <div class="col-xs-12 col-sm-12 col-md-8" >
        	  <div class="block_content">
        	  	<div class="col-xs-12 col-sm-12 col-md-12"><h1 class="d-name">${product.product_name}</h1></div>
        	  	<div class="col-xs-12 col-sm-12 col-md-12"><h3 class="d-price">${product.product_price}</h3></div>
        	  	<div class="col-xs-12 col-sm-12 col-md-12">
        	  	<div class="d-des">
        	  				${product.product_description}
        	  	</div>
        	  	
        	  </div>
        	  </div>
        
        </div>
        </div>
 </div>       