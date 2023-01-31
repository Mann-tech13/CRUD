package com.mann;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Enumeration;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Enumeration paramNames = request.getParameterNames();
		String paramName = "";
		while (paramNames.hasMoreElements()) {
			paramName = (String) paramNames.nextElement();
		}
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/CRUDexercise", "root",
					"Mann@Admin_13");
			String query = "delete from employee where E_ID = ?";
		      PreparedStatement preparedStmt = connection.prepareStatement(query);
		      preparedStmt.setString(1, paramName);
		      preparedStmt.execute();
		      RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
		      if (dispatcher != null) {
					dispatcher.forward(request, response);
				}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
