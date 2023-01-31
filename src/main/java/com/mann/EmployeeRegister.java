package com.mann;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmployeeRegister
 */
@WebServlet("/EmployeeRegister")
public class EmployeeRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String E_name = request.getParameter("E_name");
		String D_name = request.getParameter("D_name");
		String EID = request.getParameter("EID");
		String DID = request.getParameter("DID");
		List<String> FETCH = new ArrayList<String>();

//		PrintWriter printWriter = response.getWriter();
//		if (E_name == null || E_name == "" || D_name == null || D_name == "" || EID == null || EID == "" || DID == null
//				|| DID == "") {
//			printWriter.println("Something is missing in the form. Kindly recheck and try again.");
//		} else {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/CRUDexercise", "root",
						"Mann@Admin_13");

				String sql = "INSERT INTO Employee(E_ID, E_name, D_ID, D_name) " + "VALUES(?, ?, ?, ?)";
				PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, EID);
				preparedStatement.setString(2, E_name);
				preparedStatement.setString(3, DID);
				preparedStatement.setString(4, D_name);
//					System.out.println(E_name);
//					System.out.println(D_name);
//					System.out.println(EID);
//					System.out.println(DID);
//					System.out.println("------------");

				int rowAffected = preparedStatement.executeUpdate();
				if (rowAffected == 1) {

					RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
					if (dispatcher != null) {
						dispatcher.forward(request, response);
					}

				}

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
//	}

}