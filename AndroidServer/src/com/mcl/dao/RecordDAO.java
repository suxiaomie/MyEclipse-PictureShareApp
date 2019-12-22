package com.mcl.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mcl.beans.Androidrecord;
import com.mcl.hibernate.HibernateSessionFactory;

public class RecordDAO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RecordDAO dao = new RecordDAO();
		List<Androidrecord> list = dao.queryAll(5, 5);
		int i = 0;
		if(list!=null){
			for(Androidrecord tt:list){
				System.out.println(i + ":" + tt.getId());
				i++;
			}
		}
	}

	public Session getSession(){
		return HibernateSessionFactory.getSession();
	}
	
	/**
	 * ��Ӽ�¼
	 * @param record
	 * @return
	 */
	public boolean addRecord(Androidrecord record){
		Session se = getSession();
		try{
			Transaction tr = se.beginTransaction();
			se.save(record);
			tr.commit();
			System.out.println("����һ����¼");
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			se.close();
		}
	}
	
	/**
	 * ����id��ѯ��¼ʵ��
	 * @param id
	 * @return
	 */
	public Androidrecord queryOne(int id){
		Session se = getSession();
		try{
			Query qr = se.createQuery("from Androidrecord where id = ?");
			qr.setParameter(0, id);
			Androidrecord record = (Androidrecord)qr.uniqueResult();
			return record;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			se.close();
		}
	}
	/*
	 * Query query = session.createQuery("from Monkey");

        //�õ����������
        ScrollableResults scroll = query.scroll();
        //���������һ��
        scroll.last();
        int i = scroll.getRowNumber() + 1;
        System.out.println("�ܼ�·����" + i);

        //���÷�ҳλ��
        query.setFirstResult(0);
        query.setMaxResults(3);

        System.out.println(query.list());
        
        
        Query q = session.createQuery("from Cat as c");
����������q.setFirstResult(20000);
����������q.setMaxResults(100);
����������List l = q.list();
	 * */
	/**
	 * ��ҳ��ѯͼƬ
	 * @param startIndex
	 * @param count
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Androidrecord> queryAll(int startIndex,int count){
		List<Androidrecord> list = new ArrayList<>();
		Session se = getSession();
		try{
			Query qr = se.createQuery("from Androidrecord");
			qr.setFirstResult(startIndex);
			qr.setMaxResults(count);
			list = qr.list();
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			se.close();
		}
	}
	
	/**
	 * ���¼�¼��������Ҫ
	 * @param record
	 * @return
	 */
	public boolean updateRecord(Androidrecord record){
		Session se = getSession();
		try{
			Transaction tr = se.beginTransaction();
			se.update(record);
			tr.commit();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			se.close();
		}
	}
	
	public int getCount(){
		Session se = getSession();
		int count = 0;
		try{
			Query qr = se.createQuery("from Androidrecord");
			count = qr.list().size();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			se.close();
		}
		return count;
	}
}
