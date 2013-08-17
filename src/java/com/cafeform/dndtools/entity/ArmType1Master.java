package com.cafeform.dndtools.entity;

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
@Table(name = "ARM_TYPE1_MASTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ArmType1Master.findAll", query = "SELECT a FROM ArmType1Master a"),
    @NamedQuery(name = "ArmType1Master.findById", query = "SELECT a FROM ArmType1Master a WHERE a.id = :id"),
    @NamedQuery(name = "ArmType1Master.findByName", query = "SELECT a FROM ArmType1Master a WHERE a.name = :name")})
public class ArmType1Master implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 100)
    @Column(name = "NAME")
    private String name;
    @OneToMany(mappedBy = "armType1")
    private Collection<ArmMaster> armMasterCollection;

    public ArmType1Master() {
    }

    public ArmType1Master(Integer id) {
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
        if (!(object instanceof ArmType1Master)) {
            return false;
        }
        ArmType1Master other = (ArmType1Master) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //return "entity.ArmType1Master[ id=" + id + " ]";
        return name;
    }

}
