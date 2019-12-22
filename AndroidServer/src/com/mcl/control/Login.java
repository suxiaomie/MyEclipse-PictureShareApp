package com.mcl.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mcl.beans.Androiduser;
import com.mcl.dao.UserDAO;

public class Login extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public Login() {
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
		System.out.println("登录验证请求中");
		UserDAO dao = new UserDAO();
		String user = request.getParameter("user").toString().trim();
		String pswd = request.getParameter("pswd").toString().trim();
		//System.out.println("user"+user+"\npswd:"+pswd);
		String code = "-2";
		Androiduser userBean = new Androiduser();
		if(dao.isSignIn(user)){
			userBean = dao.logIn(user, pswd);
			if(userBean != null){
				System.out.println("登录成功");
				//返回用户id
				code = userBean.getId().toString();
			}
		}else{
			//没注册
			code = "-3";
		}
		
		//System.out.println(code);
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
