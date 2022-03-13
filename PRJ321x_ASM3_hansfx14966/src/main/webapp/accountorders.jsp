<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp"></jsp:include>

<div class="body p-3" style="min-width: 350px;">
	<div class="body-content d-flex flex-wrap justify-content-center">

		<!-- establish new connection -->

		<c:set var="lo" value="${listOrders}" />

		<c:forEach var="item" items="${listOrders}">

			<div class="container border rounded mb-3 pt-3">
				<p
					style="background-color: #BDC3C7; color: #fff; font-weight: bold;">&nbsp;&nbsp;Order
					ID: ${item.orderID}</p>
				<p>Total Amount: $ ${item.price}</p>
				<div class="border mb-3 ps-2 pt-2" style="background-color: #EBF9FD;">
					<c:forEach var="po" items="${item.lp}">
						<p>Product: ${po.nameProduct} - id: ${po.productID}</p>
						<p>Quantity: ${po.amountProduct}</p>
					</c:forEach>
				</div>
			</div>
		</c:forEach>
	</div>

	<%@ include file="footer.jsp"%>

	</body>
	</html>