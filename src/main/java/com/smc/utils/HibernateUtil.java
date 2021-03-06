package com.smc.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.smc.model.UserModel;

public class HibernateUtil {

	private static SessionFactory sessionFactory = buildSessionFactory();
	private static ServiceRegistry serviceRegistry;

	private static SessionFactory buildSessionFactory() {
		try {
			Configuration configuration = new Configuration().configure();
			configuration.addAnnotatedClass(UserModel.class);
			serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			return sessionFactory;

		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory " + "creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		// Close caches and connection pools
		getSessionFactory().close();
	}
}
