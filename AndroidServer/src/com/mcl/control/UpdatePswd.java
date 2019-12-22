package com.mcl.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mcl.beans.Androiduser;
import com.mcl.dao.UserDAO;

public class UpdatePswd extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public UpdatePswd() {
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
		doPost(request, response);
	}

	//–ﬁ∏ƒ√‹¬Î
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		System.out.println("–ﬁ∏ƒ√‹¬Î«Î«Û÷–");
		
		String user = request.getParameter("user").toString().trim();
		String oldpswd = request.getParameter("oldpswd").toString().trim();
		String newpswd = request.getParameter("newpswd").toString().trim();
		
		System.out.println("user"+user);
		System.out.println("oldpswd:"+oldpswd);
		System.out.println("newpswd:"+newpswd);
		
		String result = "error";
		UserDAO dao = new UserDAO();
		Androiduser userBean = new Androiduser();
		userBean = dao.logIn(user, oldpswd);
		if(userBean!=null){
			userBean.setPswd(newpswd);
			if(dao.updatePswd(userBean)){
				result = "success";
			}
		}else{
			result = "nofind";
		}
		System.out.println(result);
		out.println(result);
		
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
