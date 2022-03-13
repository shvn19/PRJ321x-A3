<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin</title>
<!-- Embed Google font -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Moo+Lah+Lah&family=Redressed&family=Saira+Condensed:wght@100;200;300;400;500;600;700;800;900&display=swap"
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

<link rel="stylesheet" href="../css/admin.css">
</head>
<body>
	<%
	Object o = session.getAttribute("user");
	if (o == null) {
		response.sendRedirect("../home.jsp");
	} else {
	String usm;
	usm = (String) o;
	String name = usm.split("@")[0];
	System.out.println(name);
	String welcome = "Welcome " + name;
	System.out.println(welcome);
	%>

	<div class="container-fluid">
        <div class="logout d-flex">
            <div>
            	<%= welcome %>
            </div>
            <div>
	            <form action="/PRJ321x_Assignment_2_hansfx14966/logout" method="post">
	            	<button class="bg-transparent border-0 fw-bold text-decoration-underline" style="color:#ec8816" type="submit">Logout</button>
	            </form>
            </div>

        </div>
		<div class="row">
			<div class="sidebar col-2 d-block">
				<div class="left-sidebar">

					<ul class="nav flex-column nav-pills">
						<li class="nav-item">
							<!-- <p class="logo">cellphoneH</p> -->
							<p class="nav-link logo">cellphone H</p>
						</li>

						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="#"> <svg
									xmlns="http://www.w3.org/2000/svg" width="16" height="16"
									fill="currentColor" class="bi bi-speedometer2"
									viewBox="0 0 16 16">
                                    <path
										d="M8 4a.5.5 0 0 1 .5.5V6a.5.5 0 0 1-1 0V4.5A.5.5 0 0 1 8 4zM3.732 5.732a.5.5 0 0 1 .707 0l.915.914a.5.5 0 1 1-.708.708l-.914-.915a.5.5 0 0 1 0-.707zM2 10a.5.5 0 0 1 .5-.5h1.586a.5.5 0 0 1 0 1H2.5A.5.5 0 0 1 2 10zm9.5 0a.5.5 0 0 1 .5-.5h1.5a.5.5 0 0 1 0 1H12a.5.5 0 0 1-.5-.5zm.754-4.246a.389.389 0 0 0-.527-.02L7.547 9.31a.91.91 0 1 0 1.302 1.258l3.434-4.297a.389.389 0 0 0-.029-.518z" />
                                    <path fill-rule="evenodd"
										d="M0 10a8 8 0 1 1 15.547 2.661c-.442 1.253-1.845 1.602-2.932 1.25C11.309 13.488 9.475 13 8 13c-1.474 0-3.31.488-4.615.911-1.087.352-2.49.003-2.932-1.25A7.988 7.988 0 0 1 0 10zm8-7a7 7 0 0 0-6.603 9.329c.203.575.923.876 1.68.63C4.397 12.533 6.358 12 8 12s3.604.532 4.923.96c.757.245 1.477-.056 1.68-.631A7 7 0 0 0 8 3z" />
                                </svg> Dashboard</span>
						</a></li>
						<li class="nav-item"><a class="nav-link" href="#"> <svg
									xmlns="http://www.w3.org/2000/svg" width="16" height="16"
									fill="currentColor" class="bi bi-person-fill"
									viewBox="0 0 16 16">
                                <path
										d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z" />
                              </svg> Staff Manager
						</a></li>
					</ul>
				</div>
			</div>

			<div class="col-10 main mx-0 px-0 d-flex flex-column">
				<div class="w-100 image-container">
					<img class="admin-image" src="../images/adminpage-image.jpg"
						style="position: relative;" alt="">
				</div>

				<div class=" content-container p-3">
					<div class="col-8">
						<table class="table table-dark table-hover caption-top">
							<caption class="table-caption p-2">Members of the team</caption>
							<thead>
								<tr>
									<th>ID</th>
									<th>Name</th>
									<th>Student ID</th>
									<th>Class</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th scope="row">1</th>
									<td>Mark</td>
									<td>Otto</td>
									<td>@mdo</td>
								</tr>
								<tr>
									<th scope="row">2</th>
									<td>Jacob</td>
									<td>Thornton</td>
									<td>@fat</td>
								</tr>
								<tr>
									<th scope="row">3</th>
									<td colspan="2">Larry the Bird</td>
									<td>@twitter</td>
								</tr>
							</tbody>
						</table>
					</div>

				</div>

			</div>
		</div>
	</div>
	<%
	}
	%>
</body>
</html>
