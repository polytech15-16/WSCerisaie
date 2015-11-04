package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import erreur.ServiceHibernateException;
import metier.Client;
import service.ServiceHibernate;

public class HibernateClient {
	private Session session;

	// On récupère toutes les lignes de la table dans une liste
	/*
	 * (non-Javadoc)
	 * 
	 * @see hibernate.service.InterfaceHibrnateStage#getTouteslesLignes()
	 */

	public List<Client> getTouslesClients() throws HibernateException, ServiceHibernateException {
		List<Client> mesClients = null;
		try {
			session = ServiceHibernate.currentSession();
			// On passe une requ�te de type SQL mlais on travaille sur la
			// classe
			Query query = session.createQuery("select c from Client as c");
			mesClients = query.list();
		} catch (Exception ex) {
			System.out.println("Erreur ServiceHiber : " + ex.getMessage());
		}
		return mesClients;
	}
}
