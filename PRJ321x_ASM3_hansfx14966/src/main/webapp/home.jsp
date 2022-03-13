<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%@page import="context.DBContext"%>
<%@ page import="java.sql.Connection"%>


<%@ page
	import="java.util.List, 
				context.DBContext,
				model.Product,
				java.util.ArrayList"%>

<jsp:include page="header.jsp"></jsp:include>

<div class="body p-3">
	<div class="body-content d-flex flex-wrap justify-content-center">

		<!-- establish new connection -->   

		<c:forEach var="item" items="${results}">
			<div class="card shadow">
			<div class="card-img-top-container w-100 d-flex justify-content-center" style="height: 198px;">
				<img src="${item.src}" class="card-img-top mx-auto my-auto" alt="${item.name}">
			</div>
				
				<div class="card-body">
					<h5 class="card-title">${item.type}</h5>
					<a href="${pageContext.request.contextPath}/product?id=${item.id}" class="card-link">${item.name}</a>
					<!-- <p class="card-text">iPhone 11 Pro Max 512 GB</p> -->
					<h6>$ ${item.price}</h6>
				</div>
			</div> 
		</c:forEach>
	</div>
	<!-- ************this part is for selecting the page***************  -->

	<div class="body-page-selector">
		<nav aria-label="Page navigation example">
			<ul class="pagination justify-content-center">
				<li class='page-item ${pageIndex==1?"disabled":"" }'><a class="page-link border-0"
					href='${pageContext.request.contextPath}/search?action=home&pageIndex=${pageIndex-1}' tabindex="-1">&#60;&#60;</a></li>
				<c:forEach var="i" begin="1" end="${numberOfPages}">
					<li class='page-item ${pageIndex==i?"active":"" }'><a class="page-link" href='${pageContext.request.contextPath}/search?action=home&pageIndex=${i}'>${i}</a></li>
				</c:forEach>
				<li class='page-item ${pageIndex==numberOfPages?"disabled":"" }'><a class="page-link border-0" href='${pageContext.request.contextPath}/search?action=home&pageIndex=${pageIndex+1}'>&#62;&#62;</a>
				</li>
			</ul>
		</nav>
	</div>

</div>

<%@ include file="footer.jsp"%>

</body>
</html>