package com.mann;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("flag", 1);
		Enumeration paramNames = request.getParameterNames();
		String paramName = "";
		while (paramNames.hasMoreElements()) {
			paramName = (String) paramNames.nextElement();
//			System.out.println(paramName);
		}
		
		
//		System.out.println(paramName);
		String[] classNameStrings = paramName.split("_");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/CRUDexercise", "root",
					"Mann@Admin_13");
			String query = "delete from employee where E_ID = ?";
		      PreparedStatement preparedStmt = connection.prepareStatement(query);
		      preparedStmt.setString(1, classNameStrings[0]);
		      preparedStmt.execute();
		} catch (Exception e) {
			// TODO: handle exception
		}
		request.setAttribute("empId", classNameStrings[0]);
		request.setAttribute("depId", classNameStrings[2]);
		request.setAttribute("empName", classNameStrings[1]);
		request.setAttribute("depname", classNameStrings[3]);
		
		try {
			 
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
		if (dispatcher != null) {
			dispatcher.forward(request, response);
		}

//		RequestDispatcher requestDispatcher = request.getRequestDispatcher("EmployeeRegister");
//		requestDispatcher.forward(request,response);

	}

}
