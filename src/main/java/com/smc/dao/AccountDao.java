package com.smc.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.smc.model.UserModel;
import com.smc.utils.Constants;
import com.smc.utils.HibernateUtil;
import com.smc.utils.ResponseModel;

public class AccountDao {

	public static UserModel userLogin(int id, String name) {
		UserModel user = new UserModel();
		user.setUserId(id);
		user.setUserName(name);
		user.setUserMessage("Hello Message");
		return user;
	}

	public static ResponseModel userTest(String name, String message) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		UserModel user1 = new UserModel();
		user1.setUserName(name);
		user1.setUserMessage(message);
		session.beginTransaction();
		session.persist(user1);
		session.getTransaction().commit();
		session.close();
		int statusCode = 622;
		List<UserModel> list = new ArrayList<UserModel>();
		list.add(user1);
		return new ResponseModel(statusCode, "User Login Done", Constants.SUCCESS, list, null, null);
	}
}
