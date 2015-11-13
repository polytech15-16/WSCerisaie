package metier;
// Generated 5 nov. 2015 15:21:55 by Hibernate Tools 4.3.1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * TypeEmplacement generated by hbm2java
 */
@Entity
@Table(name = "type_emplacement", catalog = "cerisaie")
@XmlRootElement(name = "TypeEmplacement")
public class TypeEmplacement implements java.io.Serializable {

	private Integer codeTypeE;
	private String libtypepl;
	private float tariftypepl;
	private Set<Emplacement> emplacements = new HashSet<Emplacement>(0);

	public TypeEmplacement() {
	}

	public TypeEmplacement(String libtypepl, float tariftypepl) {
		this.libtypepl = libtypepl;
		this.tariftypepl = tariftypepl;
	}

	public TypeEmplacement(String libtypepl, float tariftypepl, Set<Emplacement> emplacements) {
		this.libtypepl = libtypepl;
		this.tariftypepl = tariftypepl;
		this.emplacements = emplacements;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "CodeTypeE", unique = true, nullable = false)
	public Integer getCodeTypeE() {
		return this.codeTypeE;
	}

	public void setCodeTypeE(Integer codeTypeE) {
		this.codeTypeE = codeTypeE;
	}

	@Column(name = "LIBTYPEPL", nullable = false, length = 30)
	public String getLibtypepl() {
		return this.libtypepl;
	}

	public void setLibtypepl(String libtypepl) {
		this.libtypepl = libtypepl;
	}

	@Column(name = "TARIFTYPEPL", nullable = false, precision = 12, scale = 0)
	public float getTariftypepl() {
		return this.tariftypepl;
	}

	public void setTariftypepl(float tariftypepl) {
		this.tariftypepl = tariftypepl;
	}

	@XmlTransient
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "typeEmplacement")
	public Set<Emplacement> getEmplacements() {
		return this.emplacements;
	}

	public void setEmplacements(Set<Emplacement> emplacements) {
		this.emplacements = emplacements;
	}

}
