package service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import erreur.ServiceHibernateException;

public class ServiceHibernate {

	private static final SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;

	static {

		try {

			// on lit la configuration du fichier hibernate.cfg.xml
			Configuration configuration = new Configuration();
			configuration.configure();
			serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw new ServiceHibernateException("Impossible de construire la SessionFactory: " + ex.getMessage(), ex);
		}
	}

	public static final ThreadLocal<Session> session = new ThreadLocal<Session>();

	public static Session currentSession() throws ServiceHibernateException {
		Session s = null;
		try {
			s = session.get();
			// Open a new Session, if this Thread has none yet
			if (s == null) {
				s = sessionFactory.openSession();
				session.set(s);
			}
		} catch (Exception ex) {
			System.out.println("session " + ex.getMessage());
			throw new ServiceHibernateException("Impossible d'accéder à la SessionFactory: " + ex.getMessage(), ex);

		}
		return s;
	}

	public static void closeSession() throws ServiceHibernateException {
		try {
			Session s = session.get();
			session.set(null);
			if (s != null)
				s.close();
		} catch (Exception ex) {
			throw new ServiceHibernateException("Impossible de fermer la SessionFactory: " + ex.getMessage(), ex);
		}
	}
}