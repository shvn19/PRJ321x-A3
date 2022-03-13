<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<jsp:include page="header.jsp" />

<%-- <div class="fw-bold d-flex justify-content-center mt-4"
	style="background-color: yellow;">
	<c:out value="Search results for: '${requestScope.searchString}'"></c:out>
</div> --%>

<div class="body p-3">

	<div class="body-content d-flex flex-wrap justify-content-center">

		<div class="container-fluid row " style="min-width: 350px;">
			<div
				class="cell-image col-12 col-md-6 d-flex align-items-center mb-4 mb-md-0">
				<img alt="${product.name}" src="${product.src}"
					style="object-fit: cover;" class="m-auto d-block">

			</div>

			<div class="col-12 col-md-6">
				<div class="price">$ ${product.price}</div>
				<div class="info-product-name">${product.name}</div>
				<div class="description">
					Product Description: <br /> ${product.description }
				</div>
				<div class=" right-info-quantity-cluster">
					<br> <label class="mb-2">Quantity</label>

					<form action="${pageContext.request.contextPath}/addtocart"
						method="get">
						<input type="hidden" name="id" value="${product.id}" /> <input
							type="hidden" name="action" value="add" />
						<div class="form-group mb-3">
							<div class="d-flex">
								<div id="down"
									class="incrementerbutton px-1 py-1 justify-content-center bg-primary rounded-0">
									<div class="glyphicon glyphicon-minus">-</div>
								</div>
								<input type="text" name="quantity" id="myNumber"
									class="form-control input-number rounded-0" value="1" />
								<div id="up"
									class="incrementerbutton px-1 py-1 justify-content-center bg-primary rounded-0">
									<div class="glyphicon glyphicon-plus">+</div>
								</div>
							</div>
						</div>

						<button class="button-addtocart" type="submit">Add to
							cart</button>
					</form>
				</div>
			</div>

		</div>

	</div>

</div>
</div>

<%@ include file="footer.jsp"%>

<script>
	function up(max) {
		document.getElementById("myNumber").value = parseInt(document
				.getElementById("myNumber").value) + 1;
		if (document.getElementById("myNumber").value >= parseInt(max)) {
			document.getElementById("myNumber").value = max;
		}
	}
	function down(min) {
		document.getElementById("myNumber").value = parseInt(document
				.getElementById("myNumber").value) - 1;
		if (document.getElementById("myNumber").value <= parseInt(min)) {
			document.getElementById("myNumber").value = min;
		}
	}

	$(document).ready(function(){ 
        $("#up").click(()=>{
            up('10');
        });

        $("#down").click(()=>{
            down('1');
        });
    });
</script>


</body>
</html>