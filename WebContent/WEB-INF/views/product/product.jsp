<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div>
	<div class="row">
        <div class="col-xs-12 col-sm-12 col-md-12" >
            <ul class="thumbnail-list cd-items cd-container">    
            	<c:forEach var="product" items="${list_product}" varStatus="loopCounter">
            		<c:set var="c_url_detail1" value="${url_detailproduct}"/>
            		<c:set var="c_productid" value="${product.product_id}"/>
            		<c:set var="c_url_detail2" value="${fn:replace(c_url_detail1, '{id}', c_productid)}"/>
	            	<li> 
	             	 <div class="block_image"><a href="<c:out value="${c_url_detail2}"/>" title="<c:out value="${product.product_name}"/>">
	              <img src="${path_product_img}<c:out value="${product.product_image}"/>"  class="img img-responsive"></a>
	                
	                    <h4 class=""><c:out value="${product.product_name}"/></h4>
	                        <div class="product-price">
	                         <span class="normal-price"><c:out value="${product.product_price}"/></span>
	                        </div>
	                    </div>
	                </li>
				</c:forEach>			
        </ul>
    </div>
     <div class="col-xs-12 col-sm-12 col-md-12" >
				 <nav aria-label="Page navigation" style="text-align:center">
				 ${pagging} 
				  <!-- <ul class="pagination pagination-lg">
				    <li>
				      <a href="#" aria-label="Previous">
				        <span aria-hidden="true">&laquo;</span>
				      </a>
				    </li>
				    <li class="active"><a href="#">1</a></li>
				    <li><a href="#">2</a></li>
				    <li><a href="#">3</a></li>
				    <li><a href="#">4</a></li>
				    <li><a href="#">5</a></li>
				    <li>
				      <a href="#" aria-label="Next">
				        <span aria-hidden="true">&raquo;</span>
				      </a>
				    </li>
				  </ul> -->
				</nav>
	 </div>			
</div>
</div>