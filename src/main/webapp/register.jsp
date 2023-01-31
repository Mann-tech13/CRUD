<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/register.css" rel="stylesheet" type="text/css">
</head>
<body>
	<form class="form " action=RegisterServlet method="post">
		<h1 class="Register">Register</h1>

		<div class="name">
			<input class="enter-name" name="name" type="text" placeholder="Enter your name" required/>
		</div>
		<div class="email">
			<input class="enter-email" name="email" type="text" placeholder="Enter your email" required/>
		</div>
		<div class="password">
			<input class="enter-password" name="password" type="password"
				placeholder="Enter your password" required/>
		</div>
		<div class="repassword">
			<input class="enter-repassword" name="re_password" type="password"
				placeholder="Re-Enter password" required/>
		</div>
		<div class="submit-btn-div">
			<input class="Reg-btn" type="submit" value="Register" />
		</div>
	</form>
</body>
</html>