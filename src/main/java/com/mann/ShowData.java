package com.mann;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowData
 */
@WebServlet("/ShowData")
public class ShowData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String start = request.getParameter("start");
		String end = request.getParameter("end");
//		System.out.println(start);
//		System.out.println(end);
		List<String> fetch = new ArrayList<String>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/CRUDexercise", "root",
					"Mann@Admin_13");
//			Statement statement = connection.createStatement();
//			String query = "select count(*) from employee";
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from employee LIMIT " + start + ", " + end);
//			preparedStatement.setString(1, start);
//			preparedStatement.setString(2, end);
//			ResultSet resultSetCount = statement.executeQuery(query);
//			resultSetCount.next();
//			int count = resultSetCount.getInt(1);

			ResultSet resultSet = preparedStatement.executeQuery();
			JSONArray json = new JSONArray();
			ResultSetMetaData metadata = resultSet.getMetaData();
			int numColumns = metadata.getColumnCount();

			while (resultSet.next()) {
				JSONObject obj = new JSONObject();
				for (int i = 1; i <= numColumns; ++i) {
					String column_name = metadata.getColumnName(i);

					obj.put(column_name, resultSet.getObject(column_name));

//                System.out.println("rs.getObject('" + column_name + "')........." + resultSet.getObject(column_name));
				}
				json.put(obj);
//            System.out.println("Added JSON object to JSON Array..");
			}
//	        json.put(count);
//	        System.out.println(json);
			response.setContentType("application/json;charset=UTF-8");
			String jsonString = json.toString();
			System.out.println("jsonString:" + jsonString);
			response.getWriter().write(jsonString);

			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("Status", "Success");
			jsonMap.put("Rows", 100);

			// RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
			// dispatcher.forward(request, response);
			connection.close();

//			while (resultSet.next()) {
//				fetch.add(resultSet.getString(1));
//				//					System.out.println(resultSet.getString(1));
//				fetch.add(resultSet.getString(2));
//				//					System.out.println(resultSet.getString(2));
//				fetch.add(resultSet.getString(3));
//				//					System.out.println(resultSet.getString(3));
//				fetch.add(resultSet.getString(4));
//				//					System.out.println(resultSet.getString(4));
//				//					FETCH.add("❌")
//			}
			// request.setAttribute("displayData", fetch);
			// if (dispatcher != null) {
			// }
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
