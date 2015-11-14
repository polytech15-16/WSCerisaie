package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import erreur.ServiceHibernateException;
import metier.Activite;
import metier.Client;
import metier.Sejour;
import service.ServiceHibernate;

public class HibernateClient {
	//private Session session;

	public HibernateClient() {
		//session = ServiceHibernate.currentSession();
	}

	// On récupère toutes les lignes de la table dans une liste
	/*
	 * (non-Javadoc)
	 * 
	 * @see hibernate.service.InterfaceHibrnateStage#getTouteslesLignes()
	 */

	public List<Client> getTouslesClients() throws HibernateException, ServiceHibernateException {
		List<Client> mesClients = null;
		Session session;
		session = ServiceHibernate.currentSession();
		try {
			// On passe une requete de type SQL mais on travaille sur la
			// classe
			Query query = session.createQuery("select c from Client as c");
			query.setCacheable(false);
			mesClients = query.list();
		} catch (Exception ex) {
			System.out.println("Erreur ServiceHiber : " + ex.getMessage());
		}
		System.out.println(mesClients.toString());
		ServiceHibernate.closeSession();
		return mesClients;
	}

	public Client getUnClient(int numCli) throws HibernateException, ServiceHibernateException {
		try {

			Session session;
			session = ServiceHibernate.currentSession();
			Query query = session.createQuery("SELECT c FROM Client AS c where c.numCli = " + numCli);
			query.setCacheable(false);
			List<Client> lesClients = query.list();
			if (lesClients != null && lesClients.size() > 0) {
				return lesClients.get(0);
			}
		} catch (Exception ex) {
			System.out.println("Erreur ServiceHiber : " + ex.getMessage());
		}

		ServiceHibernate.closeSession();
		return null;
	}

	public void saveClient(Client c) throws HibernateException, ServiceHibernateException {

		Session session;
		session = ServiceHibernate.currentSession();
		Transaction tx = session.beginTransaction();
		System.out.println(c.toString());
		session.saveOrUpdate(c);
		tx.commit();
		

		ServiceHibernate.closeSession();
	}

	public boolean deleteClient(String numCli) throws HibernateException, ServiceHibernateException {
		try {
			Session session;
			session = ServiceHibernate.currentSession();
			Transaction tx = session.beginTransaction();
			//session = ServiceHibernate.currentSession();
			Client c = getUnClient(Integer.parseInt(numCli));
			for (Sejour s : c.getSejours()) {
				for (Activite a : s.getActivites()) {
					session.delete(a);
				}
				session.delete(s);
			}
			session.delete(c);
			tx.commit();
		} catch (Exception ex) {
			System.out.println("Erreur ServiceHiber : " + ex.getMessage());
			return false;
		}
		return true;
	}

	public List<Sejour> getTouslesSejours() throws HibernateException, ServiceHibernateException {
		List<Sejour> mesSejours = null;
		try {
			Session session;
			session = ServiceHibernate.currentSession();
			// On passe une requete de type SQL mais on travaille sur la
			// classe
			Query query = session.createQuery("select s from Sejour as s");
			mesSejours = query.list();
		} catch (Exception ex) {
			System.out.println("Erreur ServiceHiber : " + ex.getMessage());
		}
		return mesSejours;
	}

	public Sejour getUnSejour(int numSej) throws HibernateException, ServiceHibernateException {
		Sejour sejour = null;
		try {
			Session session;
			session = ServiceHibernate.currentSession();
			Query query = session.createQuery("SELECT s FROM Sejour AS s where s.numSej = " + numSej);
			List<Sejour> lesSejours = query.list();
			if (lesSejours != null && lesSejours.size() > 0) {
				sejour = lesSejours.get(0);
			}
		} catch (Exception ex) {
			System.out.println("Erreur ServiceHiber : " + ex.getMessage());
		}
		return sejour;
	}

	public List<Activite> getActivites(int numSej) throws HibernateException, ServiceHibernateException {
		List<Activite> mesActivites = null;
		try {
			Session session;
			session = ServiceHibernate.currentSession();
			Query query = session.createQuery("SELECT a FROM Activite AS a where a.NumSej = '" + numSej + "'");
			mesActivites = query.list();
		} catch (Exception ex) {
			System.out.println("Erreur ServiceHiber : " + ex.getMessage());
		}
		return mesActivites;
	}
}
