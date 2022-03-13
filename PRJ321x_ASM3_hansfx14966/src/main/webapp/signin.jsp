<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign in</title>

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

<link rel="stylesheet" href="css/signin.css">
</head>
<body>

	<%
	String err = (String) session.getAttribute("error");
	System.out.println(err);
	Cookie[] cookies = request.getCookies();
	String name = "";
	String pass = "";
	for (Cookie c : cookies) {
		if (c.getName().equals("user"))
			name = c.getValue();
		else if (c.getName().equals("password"))
			pass = c.getValue();
	}
	System.out.println(name);
	System.out.println(pass);
	%>

	<div
		class="row container signin-container col-8 mx-auto mt-3 px-0 shadow-lg">
		<div class="w-50 h-100 d-flex flex-column justify-content-center ">
			<form action="${pageContext.request.contextPath}/login" method="post">
				<h1 class="fw-bolder d-block">Sign in</h1>
				<input class="form-control signin-input .bg-light mb-3 d-block"
					type="text" placeholder="name@company.com"
					aria-label="default input example" name="username" value=<%=name%>>
				<input class="form-control signin-input .bg-light mb-3 d-block"
					type="password" placeholder="password"
					aria-label="default input example" name="password" value=<%=pass%>>
				<input class="mb-3" type="checkbox" name="rememberme"
					value="Remember me?" checked> 
					<label for="rememberme">Remember me</label> 
					<a href="#" class="text-center w-100 d-block mb-3">Forgot
					your password</a>
					<a href="${pageContext.request.contextPath}/registeraccount.jsp" class="text-center w-100 d-block mb-3">Register new Account</a>

				<!-- <div class="d-grid gap-2 col-6 mx-auto"> -->
				<input type="submit"
					class="btn signin-submit rounded-3 d-block mx-auto w-50"
					value="Login">
				<!-- </div> -->
			</form>
		</div>

		<div
			class="signin-extrainfo w-50 h-100 d-flex justify-content-center flex-column shadow">
			<h1 class="d-block fw-bolder"><%=session.getAttribute("isNew") == null ? "Welcome back!" : "Error:"%></h1>
			<p class="d-block"><%=session.getAttribute("isNew") == null ? "To keep connected with us please login with your personal info" : err%></p>
			<%
			session.removeAttribute("isNew");
			%>
			<!-- the isNew is release, press F5 or open new tab, Welcome text will appear -->
		</div>
	</div>
</body>
</html>