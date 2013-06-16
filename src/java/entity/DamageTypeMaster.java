package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kaizawa
 */
@Entity
@Table(name = "DAMAGE_TYPE_MASTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DamageTypeMaster.findAll", query = "SELECT d FROM DamageTypeMaster d"),
    @NamedQuery(name = "DamageTypeMaster.findById", query = "SELECT d FROM DamageTypeMaster d WHERE d.id = :id"),
    @NamedQuery(name = "DamageTypeMaster.findByName", query = "SELECT d FROM DamageTypeMaster d WHERE d.name = :name")})
public class DamageTypeMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 100)
    @Column(name = "NAME")
    private String name;
    @OneToMany(mappedBy = "damageType")
    private Collection<ArmMaster> armMasterCollection;

    public DamageTypeMaster() {
    }

    public DamageTypeMaster(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<ArmMaster> getArmMasterCollection() {
        return armMasterCollection;
    }

    public void setArmMasterCollection(Collection<ArmMaster> armMasterCollection) {
        this.armMasterCollection = armMasterCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DamageTypeMaster)) {
            return false;
        }
        DamageTypeMaster other = (DamageTypeMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //return "entity.DamageTypeMaster[ id=" + id + " ]";
        return name;
    }

}
