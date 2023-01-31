package com.mann;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.jdbc.Driver;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			PrintWriter printWriter = response.getWriter();
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/CRUDexercise", "root", "Mann@Admin_13");
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			PreparedStatement preparedStatement = connection.prepareStatement("select username from authentication where username=? and password=?");
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, password);
			System.out.println(name+" "+password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("dashboard.jsp");
				requestDispatcher.forward(request, response);
			}
			else {
				printWriter.println("Login Again !!");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
