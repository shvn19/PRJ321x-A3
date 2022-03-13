<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Register new Account</title>

<!-- Retrieve fonts from google -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Baloo+2:wght@400;500;600;700;800&display=swap"
	rel="stylesheet">


<!-- Integrate bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>

<link rel="stylesheet" href="css/registeraccount.css">
</head>
<body>
	<div class="register-outer-container container">

		<label for="" class="my-header">Register New Account</label>

		<label for="" class="bg-danger fst-italic fw-bold " style='${error==null?"display:none;":"" }'>${error}</label>
		
		<form action="${pageContext.request.contextPath}/register"
			method="post">
			<div class="form-floating">
				<input name="mail" type="email" class="form-control mt-3"
					id="floatingInput" placeholder="name@example.com" value='${mail!=null?mail:""}'>
				<label for="floatingInput">Email to login</label>
			</div>
			<div class="form-floating">
				<input name="password" type="password" class="form-control mt-3"
					id="floatingPassword" placeholder="Password" value='${password!=null?password:""}'> <label
					for="floatingPassword">Password</label>
			</div>
			<div class="form-floating">
				<input name="name" type="text" class="form-control mt-3"
					id="floatingName" placeholder="Lee Nguyễn" value='${name!=null?name:""}'> <label
					for="floatingName">Your Name</label>
			</div>
			<div class="form-floating">
				<input name="address" type="text" class="form-control mt-3"
					id="floatingAddress" placeholder="Việt Nam" value='${address!=null?address:""}'> <label
					for="floatingAddress">Your Address</label>
			</div>
			<div class="form-floating">
				<input name="phone" type="text" class="form-control mt-3"
					id="floatingPhone" placeholder="09xxxxxxxx" value='${phone!=null?phone:""}'> <label
					for="floatingPhone">Your Phone Number</label>
			</div>
			<input type="submit"
				class="btn register-submit rounded-3 d-block w-100 mt-3"
				value="Register">
		</form>

		<a href="${pageContext.request.contextPath}/index.jsp"
			class="d-block mt-3">Back to homepage</a>

	</div>
</body>
</html>