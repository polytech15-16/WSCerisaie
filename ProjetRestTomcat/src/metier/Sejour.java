package metier;
// Generated 5 nov. 2015 15:21:55 by Hibernate Tools 4.3.1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Sejour generated by hbm2java
 */
@Entity
@Table(name = "sejour", catalog = "cerisaie")
@XmlRootElement(name = "Sejour")
public class Sejour implements java.io.Serializable {

	private Integer numSej;
	private Client client;
	private Emplacement emplacement;
	private Date dateDebSej;
	private Date dateFinSej;
	private Integer nbPersonnes;
	private Set<Activite> activites = new HashSet<Activite>(0);

	public Sejour() {
	}

	public Sejour(Client client, Emplacement emplacement) {
		this.client = client;
		this.emplacement = emplacement;
	}

	public Sejour(Client client, Emplacement emplacement, Date dateDebSej, Date dateFinSej, Integer nbPersonnes,
			Set<Activite> activites) {
		this.client = client;
		this.emplacement = emplacement;
		this.dateDebSej = dateDebSej;
		this.dateFinSej = dateFinSej;
		this.nbPersonnes = nbPersonnes;
		this.activites = activites;
	}

	@Override
	public String toString() {
		return "Sejour [numSej=" + numSej + ", client=" + client + ", emplacement=" + emplacement + ", dateDebSej="
				+ dateDebSej + ", dateFinSej=" + dateFinSej + ", nbPersonnes=" + nbPersonnes + ", activites="
				+ activites + "]";
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "NumSej", unique = true, nullable = false)
	public Integer getNumSej() {
		return this.numSej;
	}

	public void setNumSej(Integer numSej) {
		this.numSej = numSej;
	}

	@XmlTransient
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NumCli", nullable = false)
	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@XmlTransient
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NumEmpl", nullable = false)
	public Emplacement getEmplacement() {
		return this.emplacement;
	}

	public void setEmplacement(Emplacement emplacement) {
		this.emplacement = emplacement;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DateDebSej", length = 0)
	public Date getDateDebSej() {
		return this.dateDebSej;
	}

	public void setDateDebSej(Date dateDebSej) {
		this.dateDebSej = dateDebSej;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DateFinSej", length = 0)
	public Date getDateFinSej() {
		return this.dateFinSej;
	}

	public void setDateFinSej(Date dateFinSej) {
		this.dateFinSej = dateFinSej;
	}

	@Column(name = "NbPersonnes")
	public Integer getNbPersonnes() {
		return this.nbPersonnes;
	}

	public void setNbPersonnes(Integer nbPersonnes) {
		this.nbPersonnes = nbPersonnes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sejour")
	public Set<Activite> getActivites() {
		return this.activites;
	}

	public void setActivites(Set<Activite> activites) {
		this.activites = activites;
	}

}
