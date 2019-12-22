package com.mcl.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mcl.beans.Androiduser;
import com.mcl.dao.UserDAO;

public class Signin extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public Signin() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		System.out.println("注册验证请求中");
		UserDAO dao = new UserDAO();
		String user = request.getParameter("user").toString().trim();
		String pswd = request.getParameter("pswd").toString().trim();
		String code = "false";
		if(dao.isSignIn(user)){
			code = "-12";
		}else{
			Androiduser userBean = new Androiduser();
			userBean.setUser(user);
			userBean.setPswd(pswd);
			if(dao.addUser(userBean)){
				code = "-11";
			}else{
				code = "-10";
			}
		}
		out.println(code);
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
