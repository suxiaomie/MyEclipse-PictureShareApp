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
			System.out.println("��ע��");
		}
		if(dao.logIn(user, pswd)!=null){
			System.out.println("��¼�ɹ�");
		}
		Androiduser user1 = new Androiduser();
		user1.setUser("xxx");
		user1.setPswd("1234");
		if(!dao.isSignIn(user1.getUser())){
			if(dao.addUser(user1)){
				System.out.println("ע��ɹ�");
			}
		}
	}

	public Session getSession(){
		return HibernateSessionFactory.getSession();
	}
	/**
	 * ����û�
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
	 * �Ƿ���ע��
	 * @param user
	 * @return
	 */
	public boolean isSignIn(String user){
		Session se = getSession();
		try {
			//�˴�ӦΪORMӳ��������������������ݿ��еı���
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
	 * ��¼
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
