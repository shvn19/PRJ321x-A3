<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>

<%@page import="context.DBContext"%>
<%@ page import="java.sql.Connection, model.Product, java.util.*"%>

<jsp:include page="header.jsp" />

<div class="fw-bold d-flex justify-content-center mt-4" style="background-color: #F1C40F;">
<c:out value="Search results for: '${searchString}'"></c:out>
</div>

<% List<Product> lp = (List<Product>)request.getAttribute("results"); %>

<div class='fw-bold d-flex justify-content-center mt-4 py-4 <%= lp.size()==0?"":"d-none" %>' style='background-color: #E74C3C;color:#fff;'>
No product found
</div>

<div class="body p-3">

	<div class="body-content d-flex flex-wrap justify-content-center">

		<br />

		<c:forEach var="item" items="${results}">
			<div class="card shadow">
				<img src="${item.src}" class="card-img-top" alt="${item.name}">
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
					href='${pageContext.request.contextPath}/search?action=search&pageIndex=${pageIndex-1}&searchString=${searchString}' tabindex="-1">&#60;&#60;</a></li>
				<c:forEach var="i" begin="1" end="${numberOfPages}">
					<li class='page-item ${pageIndex==i?"active":"" }'><a class="page-link" href='${pageContext.request.contextPath}/search?action=search&pageIndex=${i}&searchString=${searchString}'>${i}</a></li>
				</c:forEach>
				<li class='page-item ${pageIndex==numberOfPages?"disabled":"" }'><a class="page-link border-0" href='${pageContext.request.contextPath}/search?action=search&pageIndex=${pageIndex+1}&searchString=${searchString}'>&#62;&#62;</a>
				</li>
			</ul>
		</nav>
	</div>
</div>
</div>

<%@ include file="footer.jsp"%>

</body>
</html>