package com.mann;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

/**
 * Servlet implementation class GetCount
 */
@WebServlet("/GetCount")
public class GetCount extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/CRUDexercise", "root",
			"Mann@Admin_13");
			Statement statement = connection.createStatement();
			String query = "select count(*) from employee";
			ResultSet resultSetCount = statement.executeQuery(query);
			resultSetCount.next();
			int count = resultSetCount.getInt(1);
			JSONArray json = new JSONArray();
			json.put(count);
			response.setContentType("application/json;charset=UTF-8");
			String jsonString = json.toString();
			response.getWriter().write(jsonString);
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
