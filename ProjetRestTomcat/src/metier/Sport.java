package metier;
// Generated 4 nov. 2015 15:25:31 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Sport generated by hbm2java
 */
@Entity
@Table(name = "sport", catalog = "cerisaie")
public class Sport implements java.io.Serializable {

	private Integer codeSport;
	private String libelleSport;
	private String uniteTpsSport;
	private float tarifUnite;
	private Set activites = new HashSet(0);

	public Sport() {
	}

	public Sport(String libelleSport, String uniteTpsSport, float tarifUnite) {
		this.libelleSport = libelleSport;
		this.uniteTpsSport = uniteTpsSport;
		this.tarifUnite = tarifUnite;
	}

	public Sport(String libelleSport, String uniteTpsSport, float tarifUnite, Set activites) {
		this.libelleSport = libelleSport;
		this.uniteTpsSport = uniteTpsSport;
		this.tarifUnite = tarifUnite;
		this.activites = activites;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "CodeSport", unique = true, nullable = false)
	public Integer getCodeSport() {
		return this.codeSport;
	}

	public void setCodeSport(Integer codeSport) {
		this.codeSport = codeSport;
	}

	@Column(name = "LibelleSport", nullable = false, length = 10)
	public String getLibelleSport() {
		return this.libelleSport;
	}

	public void setLibelleSport(String libelleSport) {
		this.libelleSport = libelleSport;
	}

	@Column(name = "UniteTpsSport", nullable = false, length = 10)
	public String getUniteTpsSport() {
		return this.uniteTpsSport;
	}

	public void setUniteTpsSport(String uniteTpsSport) {
		this.uniteTpsSport = uniteTpsSport;
	}

	@Column(name = "TarifUnite", nullable = false, precision = 12, scale = 0)
	public float getTarifUnite() {
		return this.tarifUnite;
	}

	public void setTarifUnite(float tarifUnite) {
		this.tarifUnite = tarifUnite;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sport")
	public Set getActivites() {
		return this.activites;
	}

	public void setActivites(Set activites) {
		this.activites = activites;
	}

}
