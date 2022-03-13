<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%> --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CellphoneH - Bring your best friend to you</title>

<!-- Embed Google font -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Baloo+2:wght@400;500;600;700;800&family=Great+Vibes&family=Saira+Condensed:wght@100&display=swap"
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

<link rel="stylesheet" href="css/home.css">
<link rel="stylesheet" href="css/infoproduct.css">
<link rel="stylesheet" href="css/cart.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
	<div class="container-fluid page-container">

		<div class="position-sticky top-0 start-0" style="z-index: 101;">
			<div class="header w-100 d-flex">
				<div class="logoslogan-container h-100 col-2 p-0 p-sm-2">
					<p class="logo d-block w-100">cellphone H</p>
					<p class="slogan d-sm-block d-none w-100">Bring your best
						friend to you</p>
				</div>
				<%-- <c:url value="${pageContext.request.contextPath}"/> --%>
				<div
					class="searchbar-container d-none d-sm-flex col border-1 border-white d-flex justify-content-center flex-column">
					<form action='${pageContext.request.contextPath}/search'
						method="get">
						<div class="input-group">
							<button
								class="btn btn-warning bg-light dropdown-toggle searchbar-btn-dropdown"
								type="button" data-bs-toggle="dropdown" aria-expanded="false">Categories</button>
							<ul class="dropdown-menu">
								<li><a class="dropdown-item" href="#">Action</a></li>
								<li><a class="dropdown-item" href="#">Another action</a></li>
								<li><a class="dropdown-item" href="#">Something else
										here</a></li>
								<li><hr class="dropdown-divider"></li>
								<li><a class="dropdown-item" href="#">Separated link</a></li>
							</ul>
							<input name="searchString" type="text" class="form-control"
								aria-label="Text input with dropdown button">
							<!--                         <label class=" input-group-text fw-bold" for="inputGroupFile02" style="background-color:#EE8824;color:#ffffff;">Search</label> -->
							<input name="action" type="hidden" value="search">
							<button class=" input-group-text fw-bold" type="submit"
								style="background-color: #EE8824; color: #ffffff;">
								Search</label>
						</div>
					</form>
				</div>
			</div>

			<div>
				<nav class="navbar navbar-expand-sm navbar-dark my-navbar">
					<div class="container-fluid">
						<button class="navbar-toggler" type="button"
							data-bs-toggle="collapse"
							data-bs-target="#navbarSupportedContent"
							aria-controls="navbarSupportedContent" aria-expanded="false"
							aria-label="Toggle navigation">
							<span class="navbar-toggler-icon"></span>
						</button>
						<div class="collapse navbar-collapse" id="navbarSupportedContent">
							<ul class="navbar-nav me-auto mb-2 mb-lg-0">
								<li class="nav-item"><a class="nav-link active"
									aria-current="page" href="index.jsp">Home</a></li>
								<li class="nav-item"><a class="nav-link" href="#">Products</a>
								</li>
								<li class="nav-item"><a class="nav-link" href="#">About
										us</a></li>
							</ul>
							<ul class="navbar-nav mb-2 mb-lg-0 flex-end">
								<li class="login nav-item"
									style='${sessionScope.account.getName()!=null?"display:none":""}'>
									<a href="signin.jsp" class="nav-link"
										style="text-decoration: none;">Login</a>
								</li>
								<li class='login nav-item float-end'
									style='${sessionScope.account.getName()!=null?"":"display:none"}'>
									<a href="${pageContext.request.contextPath}/account"
										class="nav-link" style="text-decoration: none;">${sessionScope.account.getName()!=null?sessionScope.account.getName():""}</a>
								</li>
								<li class='login nav-item float-end'
									style='${sessionScope.account.getName()!=null?"":"display:none"}'>
									<a href="${pageContext.request.contextPath}/logout"
										class="nav-link" style="text-decoration: none;">Logout</a>
								</li>
								<li class="login nav-item float-end" style=''>
									<a href="${pageContext.request.contextPath}/cart.jsp"
										class="nav-link" style="text-decoration: none;"> <img
										alt="" width="32px" src="images/cart64.png">
									</a>
								</li>
							</ul>

						</div>
					</div>
				</nav>
			</div>
		</div>