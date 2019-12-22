package com.mcl.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mcl.beans.Androiduser;
import com.mcl.hibernate.HibernateSessionFactory;

public class UserDAO {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserDAO dao = new UserDAO();
		String user = "mcl";
		String pswd = "1234";
		if(dao.isSignIn(user)){
			System.out.println("已注册");
		}
		if(dao.logIn(user, pswd)!=null){
			System.out.println("登录成功");
		}
		Androiduser user1 = new Androiduser();
		user1.setUser("xxx");
		user1.setPswd("1234");
		if(!dao.isSignIn(user1.getUser())){
			if(dao.addUser(user1)){
				System.out.println("注册成功");
			}
		}
	}

	public Session getSession(){
		return HibernateSessionFactory.getSession();
	}
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public boolean addUser(Androiduser user){
		Session se = getSession();
		try{
			Transaction tr = se.beginTransaction();
			se.save(user);
			tr.commit();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			se.close();
		}
	}
	/**
	 * 是否已注册
	 * @param user
	 * @return
	 */
	public boolean isSignIn(String user){
		Session se = getSession();
		try {
			//此处应为ORM映射的类名，而不是在数据库中的表名
			Query qr = se.createQuery("from Androiduser where user=?");
			qr.setParameter(0, user);
			int num = qr.list().size();
			if(num>0)
				return true;
			else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}finally{
			se.close();
		}
	}
	/**
	 * 登录
	 * @param user
	 * @param pswd
	 * @return
	 */
	public Androiduser logIn(String user,String pswd){
		Session se = getSession();
		try {
			Transaction tr = se.beginTransaction();
			Query qr = se.createQuery("from Androiduser where user=? and pswd=?");
			qr.setParameter(0, user);
			qr.setParameter(1, pswd);
			Androiduser us = (Androiduser)qr.uniqueResult();
			tr.commit();
			if(us!=null)
				return us;
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			se.close();
		}
	}
	
	//
	public boolean updatePswd(Androiduser user){
		Session se = getSession();
		try {
			Transaction tr = se.beginTransaction();
			se.update(user);
			tr.commit();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			se.close();
		}
		
	}
}
