package com.cafeform.dndtools.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kaizawa
 */
@Entity
@Table(name = "RULEBOOK_MASTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RulebookMaster.findAll", query = "SELECT r FROM RulebookMaster r"),
    @NamedQuery(name = "RulebookMaster.findById", query = "SELECT r FROM RulebookMaster r WHERE r.id = :id"),
    @NamedQuery(name = "RulebookMaster.findByName", query = "SELECT r FROM RulebookMaster r WHERE r.name = :name"),
    @NamedQuery(name = "RulebookMaster.findByDescription", query = "SELECT r FROM RulebookMaster r WHERE r.description = :description")})
public class RulebookMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 400)
    @Column(name = "NAME")
    private String name;
    @Size(max = 4000)
    @Column(name = "DESCRIPTION")
    private String description;
    @OneToMany(mappedBy = "rulebook")
    private Collection<ArmMaster> armMasterCollection;

    public RulebookMaster() {
    }

    public RulebookMaster(Integer id) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(object instanceof RulebookMaster)) {
            return false;
        }
        RulebookMaster other = (RulebookMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //return "entity.RulebookMaster[ id=" + id + " ]";
        return name;
    }

}
