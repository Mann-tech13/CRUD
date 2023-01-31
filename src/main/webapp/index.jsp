<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/login.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div>
		<form action=LoginServlet method="post" class="form">
			<h3 class="Login">Login</h3>
			<div class="name">
				<input class="enter-name" name="name" type="text" placeholder="Enter your name" required/>
			</div>
			<div class="password">
				<input class="enter-pwd" name="password" type="password" placeholder="Enter your password" required/>
			</div>
			<div class="login-btn-div">
				<input class="Login-btn" type="submit" value="Login"/>
			</div>
			<div class="register-btn-div">
				<input class="Reg-btn" type="submit" value="Register" onclick="form.action='register.jsp'"/>
			</div>
		</form>
	</div>
	
</body>
</html>