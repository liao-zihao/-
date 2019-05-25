package com.lzh;

import org.hibernate.Transaction;
import org.hibernate.Session;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
public class PersonManager {

	public static void main(String[] args) {
		PersonManager pm=new PersonManager();
		System.out.println("zhunei");
		pm.createAndStorePerson();
		HiberateUtil.sessionFactory.close();
	}

	private void createAndStorePerson() {
		Session session=HiberateUtil.currentSession();
		Transaction tx=session.beginTransaction();
		Person person=new Person();
		person.setAge(20);
		person.setName("liaozihao");
		person.getSchools().add("xiaoxue");
		person.getSchools().add("daxue");
		session.save(person);
		tx.commit();
		HiberateUtil.closeSession();
	}
}
