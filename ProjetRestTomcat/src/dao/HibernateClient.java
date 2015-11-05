package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import erreur.ServiceHibernateException;
import metier.Activite;
import metier.Client;
import metier.Sejour;
import service.ServiceHibernate;

public class HibernateClient {
	private Session session;

	public HibernateClient() {
		session = ServiceHibernate.currentSession();
	}

	// On récupère toutes les lignes de la table dans une liste
	/*
	 * (non-Javadoc)
	 * 
	 * @see hibernate.service.InterfaceHibrnateStage#getTouteslesLignes()
	 */

	public List<Client> getTouslesClients() throws HibernateException, ServiceHibernateException {
		List<Client> mesClients = null;
		try {
			// On passe une requete de type SQL mais on travaille sur la
			// classe
			Query query = session.createQuery("select c from Client as c");
			mesClients = query.list();
		} catch (Exception ex) {
			System.out.println("Erreur ServiceHiber : " + ex.getMessage());
		}
		return mesClients;
	}

	public Client getUnClient(int numCli) throws HibernateException, ServiceHibernateException {
		try {
			Query query = session.createQuery("SELECT c FROM Client AS c where c.numCli = " + numCli);
			List<Client> lesClients = query.list();
			if (lesClients != null && lesClients.size() > 0) {
				return lesClients.get(0);
			}
		} catch (Exception ex) {
			System.out.println("Erreur ServiceHiber : " + ex.getMessage());
		}
		return null;
	}

	public boolean saveClient(Client c) throws HibernateException, ServiceHibernateException {
		int ret = 0;
		try {
			Query query = session.createQuery("INSERT INTO Client VALUES (" + c.getNumCli() + ", " + c.getNomCli() + ""
					+ c.getAdrRueCli() + "" + c.getCpCli() + "" + c.getVilleCli() + "" + c.getPieceCli() + ""
					+ c.getNumPieceCli() + ")");
			ret = query.executeUpdate();
		} catch (Exception ex) {
			System.out.println("Erreur ServiceHiber : " + ex.getMessage());
		}
		return (ret > 0);
	}

	public boolean deleteClient(String numCli) throws HibernateException, ServiceHibernateException {
		int ret = 0;
		try {
			session = ServiceHibernate.currentSession();
			Query query = session.createQuery("DELETE FROM Client AS c WHERE c.NumCli = '" + numCli + "'");
			ret = query.executeUpdate();
		} catch (Exception ex) {
			System.out.println("Erreur ServiceHiber : " + ex.getMessage());
		}
		return (ret > 0);
	}

	public List<Sejour> getTouslesSejours() throws HibernateException, ServiceHibernateException {
		List<Sejour> mesSejours = null;
		try {
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

	public Sejour getUnSejour(String numSej) throws HibernateException, ServiceHibernateException {
		Sejour sejour = null;
		try {
			session = ServiceHibernate.currentSession();
			Query query = session.createQuery("SELECT s FROM Sejour AS s where s.NumSej = '" + numSej + "'");
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
			session = ServiceHibernate.currentSession();
			Query query = session.createQuery("SELECT a FROM Activite AS a where a.NumSej = '" + numSej + "'");
			mesActivites = query.list();
		} catch (Exception ex) {
			System.out.println("Erreur ServiceHiber : " + ex.getMessage());
		}
		return mesActivites;
	}
}
