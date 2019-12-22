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
//					name = name + "." + file.getFileExt();// 得到文件的扩展名
//					String filename = this.getServletContext().getRealPath("/") + "images/" + name;
//					file.saveAs(filename);
//					System.out.println(filename);
//				}
//			}

		
        String filePath = getServletContext().getRealPath("/") + "img";//设置上传文件保存路径
		
        File fold = new File(filePath);
        if (!fold.exists()) {
        	fold.mkdir();
            System.out.println(filePath+"文件夹创建");
        }
        
        SmartUpload su = new SmartUpload();//实例化上传组件
        su.initialize(getServletConfig(), request, response);//初始化SmartUpload
        su.setMaxFileSize(1024*1024*10);//设置上传文件对象10M
        su.setTotalMaxFileSize(1024*1024*100);//设置所有文件大小100M
        su.setAllowedFilesList("bmp,jpg,gif,png,jpeg"); //设置允许上传文件类型
        
        String result = "上传失败";
        try {
            
            su.setDeniedFilesList("rar,jsp,js");//设置禁止上传文件类型
            
            su.upload();//上传文件
            
            String fromId = su.getRequest().getParameter("fromId").trim();//获取参数
            System.out.println("fromId:"+fromId);
            
            com.jspsmart.upload.Files files = su.getFiles();	//获取文件
            com.jspsmart.upload.File file = files.getFile(0);	//第一个
            
            //重命名
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			String name = sdf.format(new Date());
			name = name + fromId;
			name = name + "." + file.getFileExt();// 得到文件的扩展名
			String filename = filePath + "/" + name;
			
			//保存文件
			file.saveAs(filename);
			System.out.println(filename);
			
            //su.save(filePath);//保存文件
            
            //修改数据库
            if(updateRecord(name,Integer.parseInt(fromId))){
            	result = "上传成功";
            }
            
        } catch (Exception e) {
            //result = "上传失败！";
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
