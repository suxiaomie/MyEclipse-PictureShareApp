package com.mcl.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mcl.beans.Androidrecord;
import com.mcl.dao.RecordDAO;

public class AddCount extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public AddCount() {
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
		
	}

	/**
	 * ����
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		System.out.println("�������ݸ���������");
		
		String result = "����ʧ��";
		String pid = request.getParameter("picId").trim();
		int picId = Integer.parseInt(pid);
		System.out.println("ͼƬid:"+pid);
		if(updateRecord(picId)){
			result = "���޳ɹ�";
		}
		out.print(result);
		out.close();
		
	}

	//����id�޸����ݿ�
	public boolean updateRecord(int id){
		RecordDAO dao = new RecordDAO();
		Androidrecord record = new Androidrecord();
		record = dao.queryOne(id);
		if(record!=null){
			int count = record.getCount();
			record.setCount(count+1);
			if(dao.updateRecord(record)){
				return true;
			}
		}
		return false;
	}
	
	public void init() throws ServletException {
		// Put your code here
	}

}
