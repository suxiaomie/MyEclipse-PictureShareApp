package com.mcl.control;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartUpload;
import com.mcl.beans.Androidrecord;
import com.mcl.dao.RecordDAO;

public class Upload extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public Upload() {
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

//		try {
//			smartUpload.initialize(this.getServletConfig(), request, response);
//			smartUpload.upload();
//			String msg = smartUpload.getRequest().getParameter("msg");
//			if (msg != null)	msg = new String(msg.getBytes("GBK"), "utf-8");
//			com.jspsmart.upload.Files files = smartUpload.getFiles();
//			for (int i = 0; i < files.getCount(); i++) {
//				com.jspsmart.upload.File file = files.getFile(i);
//				if (!file.isMissing()) {
//					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//					String name = sdf.format(new java.util.Date());
//					name = name + "." + file.getFileExt();// �õ��ļ�����չ��
//					String filename = this.getServletContext().getRealPath("/") + "images/" + name;
//					file.saveAs(filename);
//					System.out.println(filename);
//				}
//			}

		
        String filePath = getServletContext().getRealPath("/") + "img";//�����ϴ��ļ�����·��
		
        File fold = new File(filePath);
        if (!fold.exists()) {
        	fold.mkdir();
            System.out.println(filePath+"�ļ��д���");
        }
        
        SmartUpload su = new SmartUpload();//ʵ�����ϴ����
        su.initialize(getServletConfig(), request, response);//��ʼ��SmartUpload
        su.setMaxFileSize(1024*1024*10);//�����ϴ��ļ�����10M
        su.setTotalMaxFileSize(1024*1024*100);//���������ļ���С100M
        su.setAllowedFilesList("bmp,jpg,gif,png,jpeg"); //���������ϴ��ļ�����
        
        String result = "�ϴ�ʧ��";
        try {
            
            su.setDeniedFilesList("rar,jsp,js");//���ý�ֹ�ϴ��ļ�����
            
            su.upload();//�ϴ��ļ�
            
            String fromId = su.getRequest().getParameter("fromId").trim();//��ȡ����
            System.out.println("fromId:"+fromId);
            
            com.jspsmart.upload.Files files = su.getFiles();	//��ȡ�ļ�
            com.jspsmart.upload.File file = files.getFile(0);	//��һ��
            
            //������
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			String name = sdf.format(new Date());
			name = name + fromId;
			name = name + "." + file.getFileExt();// �õ��ļ�����չ��
			String filename = filePath + "/" + name;
			
			//�����ļ�
			file.saveAs(filename);
			System.out.println(filename);
			
            //su.save(filePath);//�����ļ�
            
            //�޸����ݿ�
            if(updateRecord(name,Integer.parseInt(fromId))){
            	result = "�ϴ��ɹ�";
            }
            
        } catch (Exception e) {
            //result = "�ϴ�ʧ�ܣ�";
            e.printStackTrace();
        }
        //request.setAttribute("smartResult", result);
        //request.getRequestDispatcher("index.jsp").forward(request, response);
        out.println(result);
	}


	public boolean updateRecord(String name,int fromId){
		RecordDAO dao = new RecordDAO();
		Androidrecord record = new Androidrecord();
		record.setName(name);
		record.setCount(0);
		record.setFromId(fromId);
		if(dao.addRecord(record)){
			return true;
		}
		return false;
	}
	
	
	public void init() throws ServletException {
		// Put your code here
	}

}
