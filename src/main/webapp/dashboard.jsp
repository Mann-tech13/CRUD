<%@page import="org.eclipse.jdt.internal.compiler.ast.TryStatement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page language="java" import="java.util.*"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.sql.PreparedStatement"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/dashboard.css" rel="stylesheet" type="text/css">
<%-- <style <%@include file="/webapp/css/dashboard.css" %>></style> --%>
</head>
<body>
	<form method="post" class="Mydashboard" action="">
		<div class="form">
			<h3 class="Employee">Employee details</h3>
			<div class="name">
				<%
				String temp = (String) request.getAttribute("empName");
				if (temp != null) {
				%>
				<input type="text" name="E_name" placeholder="Enter your name"
					class="enter-name" value="<%=request.getAttribute("empName")%>" required="required" />
				<%
				} else {
				%>

				<input type="text" name="E_name" placeholder="Enter your name"
					class="enter-name" required="required"/>
				<%
				}
				%>
			</div>

			<div class="depId">
				<%
				temp = (String) request.getAttribute("depId");
				if (temp != null) {
				%>
				<input type="text" name="DID" placeholder="Enter your name"
					class="enter-depId" value="<%=request.getAttribute("depId")%>" required/>
				<%
				} else {
				%>

				<input type="text" name="DID" placeholder="Enter department Id"
					class="enter-depId" required/>
				<%
				}
				%>
			</div>
			<div class="empId">
				<%
				temp = (String) request.getAttribute("empId");
				if (temp != null) {
				%>
				<input type="text" name="EID" placeholder="Enter your id"
					class="enter-empId" value="<%=request.getAttribute("empId")%>" required/>
				<%
				} else {
				%>

				<input type="text" name="EID" placeholder="Enter your Id"
					class="enter-empId" required/>
				<%
				}
				%>

			</div>
			<div class="depName">
				<%
				temp = (String) request.getAttribute("empId");
				if (temp != null) {
				%>
				<input type="text" name="D_name" placeholder="Enter department name"
					class="enter-depName" value="<%=request.getAttribute("depname")%>" required/>
				<%
				} else {
				%>

				<input type="text" name="D_name" placeholder="Enter department name"
					class="enter-depName" required/>
				<%
				}
				%>


			</div>
			<div class="save">
				<input type="button" name="Save" value="Save" class="btn-save"
					onclick="saveClick()" />
					<input type="button" name="Clear" value="Clear" class="btn-clear"
					onclick="clearClick()" />
			</div>
		</div>
	</form>
	<div class="tdata"></div>
	<div class="bottom-field">
		<ul class="pagination">
			<div class="numbering">
			
			</div>
		</ul>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
	<script src="javascript/processes.js"></script>



</body>
</html>