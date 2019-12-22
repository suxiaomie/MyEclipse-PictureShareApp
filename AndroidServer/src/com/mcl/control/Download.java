package com.mcl.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.mcl.beans.Androidrecord;
import com.mcl.dao.RecordDAO;

public class Download extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public Download() {
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

	/**
	 * 返回json数据，包含5条数据
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		System.out.println("图片数据请求中");
		
		String indexS = request.getParameter("startIndex").trim();
		String countS = request.getParameter("count").trim();
		int index = Integer.parseInt(indexS);
		int count = Integer.parseInt(countS);
		System.out.println("index:"+index);
		System.out.println("count:"+count);
		
		RecordDAO dao = new RecordDAO();
		List<Androidrecord> list = dao.queryAll(index,count);
		//list 转 json String
		String json = JSON.toJSONString(list);
		out.println(json);
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
