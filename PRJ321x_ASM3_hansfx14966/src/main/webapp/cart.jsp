<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>

<jsp:include page="header.jsp" />

<div class="body p-3" style="min-width: 300px;">

	<c:set var="p" value="${sessionScope.cart.items}" scope="page" />



	<table id="cart-table-product" style="min-width: 543px;">
		<tr>
			<th></th>
			<th>Products in Cart: ${p.size()}</th>
			<th>Quantity</th>
			<th>Price ($)</th>
			<th>Amount ($)</th>
		</tr>

		<c:forEach var="i" items="${p}" varStatus="row">
			<tr>
				<td>${i.product.id}</td>
				<td>${i.product.name}</td>
				<td>
					<div class="clearfix">
						<span class="cart-number-quantity">${i.quantity}</span>
						<button onclick="plusClicked($(this),${i.product.price},10)"
							class="cart-incrementerbutton">+</button>
						<button onclick="minusClicked($(this),${i.product.price},1)"
							class="cart-incrementerbutton">-</button>
						<form id="formDelete"
							action="${pageContext.request.contextPath}/addtocart">
							<input type="hidden" name="action" value="delete"> <input
								id="formDeleteID" type="hidden" name="id"
								value="${i.product.id}">
							<button id="formDeleteSubmit" type="submit"
								style="width: fit-content;"
								class="cart-incrementerbutton cart-drop-item">Drop item</button>
						</form>

					</div>
				</td>
				<td>${i.product.price}</td>
				<td>${i.amount}</td>
			</tr>

		</c:forEach>

		<tr>
			<td colspan="4" style="font-weight: bolder;">Total Amount ($)</td>
			<td>${sessionScope.cart.getAmount()}</td>
		</tr>
	</table>

	<br>

	<button id="saveCart" type="button" class="cart-button-submit">Save
		Changes to Cart</button>
	<br> <br>

	<c:set var="acc" value="${sessionScope.account}" scope="page"></c:set>
	<form action="${pageContext.request.contextPath}/pay" onsubmit="return ${sessionScope.account!=null?true:false}" >
	<table class="cart-table-account-info">
		<tr>
			<th>Customer name</th>
			<td>${acc.name!=null?acc.name:"Please login or register"}</td>
		</tr>
		<tr>
			<th>Customer address</th>
			<td>${acc.address!=null?acc.address:"Please login or register"}</td>
		</tr>
		<tr>
			<th>Discount code (if any)</th>
			<td><input type="text" name="discount" value=""></td>
		</tr>
	</table>

	<br>

		<button type="submit" class="cart-button-submit">Submit</button>
	</form>
	

</div>
</div>

<%@ include file="footer.jsp"%>

<script>
	function minusClicked(a, price,min) {
		let x = a.parent().find("span").text();
		let n = parseInt(x);
		if ((n - 1) >= min)
			n--;
		a.parent().find("span").text(n);	//set the quantity cell
		var amount = (n * price).toFixed(2);
		a.parent().parent().parent().find("td").last().text(amount);	// set the amount column
		
		//update the total amount value
		var sum=0;
		var table = document.getElementById("cart-table-product");
		for (var i=1; i<table.rows.length;i++){
			var row = table.rows[i];
			if(i==table.rows.length-1) {
				row.cells[row.cells.length-1].innerHTML = sum.toFixed(2);
			} else {
				var lc=row.cells[row.cells.length-1].innerHTML;
				sum+=parseFloat(lc); 
			}
		}   
	}

	function plusClicked(a, price, max) {
		let x = a.parent().find("span").text();
		let n = parseInt(x);
		if ((n + 1) <= max)
			n++;
		a.parent().find("span").text(n);	//set the quantity cell
		var amount = (n * price).toFixed(2);
		a.parent().parent().parent().find("td").last().text(amount);  // set the amount column

		//update the total amount value
		var sum=0;
		var table = document.getElementById("cart-table-product");
		for (var i=1; i<table.rows.length;i++){
			var row = table.rows[i];
			if(i==table.rows.length-1) {
				row.cells[row.cells.length-1].innerHTML = sum.toFixed(2);
			} else {
				var lc=row.cells[row.cells.length-1].innerHTML;
				sum+=parseFloat(lc); 
			}
		}   
  
	}
	
	$(() => {
        // function will get executed 
        // on click of submit button
        $("#formDeleteSubmit").click(function(ev) {
        	var iid = parseInt(a.parent().parent().parent().find("td").first()
    				.text());
        	alert("aaaaa");
        	$("#formDeleteID").value(iid);
        	alert("aaaaa");
            var form = $("#formDelete");
            var url = form.attr('action');
            $.ajax({
                type: "POST",
                url: url,
                data: form.serialize(),
                success: function(data) {
                      
                    // Ajax call completed successfully
                    alert("Form Submited Successfully");
                },
                error: function(data) {
                      
                    // Some error in ajax call
                    alert("some Error");
                }
            });
            ev.preventDefault();
        });  
        
        $("#saveCart").click(()=>{
        	var rs = $("#cart-table-product tr");
        	
    		// Read all the table save in to c array
    		var s = "";
    		var count = 0;
     		rs.each(function() {
    			var id = $(this).find("td:eq(0)").text();
    			if (id!="" && id!="Total Amount"){
        			console.log("id = " + id);
        			var quan = $(this).find("span").text();
        			console.log("quan = " + quan);
        			var ri="";
        			if (count==0){
        				ri = "" + id + "/" + quan;
        			} else {
        				ri = "," + id + "/" + quan;
        			}
        			s += ri;
        			count++;
    			}
    		});
    		
    		console.log (s);
    		
       		var jqxhr = $.post( "<%=request.getContextPath()%>/addtocart", 
           			{
           				"action" : "savechanges",
           				"table" : s
           			}
       	   			,function(response) {
       	   		 	 console.log("success");
       	   			   window.location.href = "cart.jsp"; 
       	   			})
          			.fail(function(){
          				alert("error"); 
          			});
       		
       		alert("Cart is saved changes");
       		location.reload();
       		//end of this function click
        	});
        
        //end of document ready
        });

	 
</script>


</body>
</html>