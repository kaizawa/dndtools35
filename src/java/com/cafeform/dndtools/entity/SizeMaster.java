/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafeform.dndtools.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kaizawa
 */
@Entity
@Table(name = "SIZE_MASTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SizeMaster.findAll", query = "SELECT s FROM SizeMaster s"),
    @NamedQuery(name = "SizeMaster.findById", query = "SELECT s FROM SizeMaster s WHERE s.id = :id"),
    @NamedQuery(name = "SizeMaster.findBySizeName", query = "SELECT s FROM SizeMaster s WHERE s.sizeName = :sizeName"),
    @NamedQuery(name = "SizeMaster.findByAcModifier", query = "SELECT s FROM SizeMaster s WHERE s.acModifier = :acModifier")})
public class SizeMaster implements Serializable {
    @OneToMany(mappedBy = "sizeId")
    private List<RaceMaster> raceMasterList;
    @Column(name = "REACH")
    private Integer reach;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CONTACT_SPACE", precision = 52)
    private Double contactSpace;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Size(max = 100)
    @Column(name = "SIZE_NAME", length = 100)
    private String sizeName;
    @Column(name = "AC_MODIFIER")
    private Integer acModifier;
    @OneToMany(mappedBy = "sizeId")
    private List<MonsterMaster> monsterMasterList;

    public SizeMaster() {
    }

    public SizeMaster(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public Integer getAcModifier() {
        return acModifier;
    }

    public void setAcModifier(Integer acModifier) {
        this.acModifier = acModifier;
    }

    @XmlTransient
    public List<MonsterMaster> getMonsterMasterList() {
        return monsterMasterList;
    }

    public void setMonsterMasterList(List<MonsterMaster> monsterMasterList) {
        this.monsterMasterList = monsterMasterList;
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
        if (!(object instanceof SizeMaster)) {
            return false;
        }
        SizeMaster other = (SizeMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //return "entity.SizeMaster[ id=" + id + " ]";
        return sizeName;
    }

    public Integer getReach() {
        return reach;
    }

    public void setReach(Integer reach) {
        this.reach = reach;
    }

    public Double getContactSpace() {
        return contactSpace;
    }

    public void setContactSpace(Double contactSpace) {
        this.contactSpace = contactSpace;
    }

    @XmlTransient
    public List<RaceMaster> getRaceMasterList() {
        return raceMasterList;
    }

    public void setRaceMasterList(List<RaceMaster> raceMasterList) {
        this.raceMasterList = raceMasterList;
    }
    
}
