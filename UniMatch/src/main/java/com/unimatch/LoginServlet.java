package com.unimatch;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext sc = getServletContext();
		RequestDispatcher rd;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(sc.getInitParameter("sqlurl"), sc.getInitParameter("sqluname"), sc.getInitParameter("sqlpswd"));
			PreparedStatement st =con.prepareStatement("select email from users where email = ?;");
			st.setString(1, request.getParameter("email"));
			ResultSet rs;
			rs = st.executeQuery();
			st.close();
			con.close();
			if(rs.next()==false) {
				request.setAttribute("errorMessage", "Could Not Login, Try Signing Up");
				rd = request.getRequestDispatcher("/ErrorScript");
				rd.include(request, response);
				rd = request.getRequestDispatcher("/index.html");
				rd.forward(request,response);
				return;
			}
			HttpSession session = request.getSession();
			session.setAttribute("email", request.getParameter("email"));
			session.setMaxInactiveInterval(300);
			rd = request.getRequestDispatcher("/mainpage");
			rd.forward(request,response);
			
			

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Something is wrong");
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println("Cannot Load Driver");
			e1.printStackTrace();
		} 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
