package br.com.cmdweb.util;

import javax.swing.JOptionPane;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			Configuration config = new Configuration();
			config.configure();
			ServiceRegistry registro = new StandardServiceRegistryBuilder()
					.applySettings(config.getProperties()).build();
			SessionFactory sessionfactory = config.buildSessionFactory(registro);
			
			return sessionfactory;
			
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			JOptionPane.showMessageDialog(null, "Erro ai iniciar sessão: \n" + ex);
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
