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
 * Servlet implementation class SignupServlet
 */
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext sc = getServletContext();
		RequestDispatcher rd;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(sc.getInitParameter("sqlurl"), sc.getInitParameter("sqluname"), sc.getInitParameter("sqlpswd"));
			PreparedStatement st =con.prepareStatement("insert into users values(?,?,?,?,?,?,?,?,NULL,NULL,NULL)");
			st.setString(1, request.getParameter("email"));
			st.setString(2, request.getParameter("firstName"));
			st.setString(3, request.getParameter("lastName"));
			st.setString(4, request.getParameter("gender"));
			st.setString(5, request.getParameter("DOB"));
			st.setString(6, request.getParameter("ig"));
			st.setString(7, "images/"+request.getParameter("email"));
			int x = st.executeUpdate();	
			st.close();
			con.close();
			if(x==0)
			{
				request.setAttribute("errorMessage", "Could Not SignUp");
				rd = request.getRequestDispatcher("/ErrorScript");
				rd.include(request, response);
				rd = request.getRequestDispatcher("/index.html");
				rd.forward(request,response);
				return;
			}
			HttpSession session = request.getSession();
			session.setAttribute("email", request.getParameter("email"));
			session.setMaxInactiveInterval(300);
			rd = request.getRequestDispatcher("/setup_edit_profile.html");
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

}
