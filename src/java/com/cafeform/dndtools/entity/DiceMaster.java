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
@Table(name = "DICE_MASTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DiceMaster.findAll", query = "SELECT d FROM DiceMaster d"),
    @NamedQuery(name = "DiceMaster.findById", query = "SELECT d FROM DiceMaster d WHERE d.id = :id"),
    @NamedQuery(name = "DiceMaster.findByName", query = "SELECT d FROM DiceMaster d WHERE d.name = :name"),
    @NamedQuery(name = "DiceMaster.findByType", query = "SELECT d FROM DiceMaster d WHERE d.type = :type")})
public class DiceMaster implements Serializable {
    @OneToMany(mappedBy = "secondDamageDiceType")
    private List<ArmMaster> armMasterList;
    @OneToMany(mappedBy = "damageDiceType")
    private List<ArmMaster> armMasterList1;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Size(max = 20)
    @Column(name = "NAME", length = 20)
    private String name;
    @Column(name = "TYPE")
    private Integer type;
    @OneToMany(mappedBy = "hitDiceType")
    private List<MonsterMaster> monsterMasterList;

    public DiceMaster() {
    }

    public DiceMaster(Integer id) {
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
        if (!(object instanceof DiceMaster)) {
            return false;
        }
        DiceMaster other = (DiceMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //return "entity.DiceMaster[ id=" + id + " ]";
        return name;
    }

    @XmlTransient
    public List<ArmMaster> getArmMasterList() {
        return armMasterList;
    }

    public void setArmMasterList(List<ArmMaster> armMasterList) {
        this.armMasterList = armMasterList;
    }

    @XmlTransient
    public List<ArmMaster> getArmMasterList1() {
        return armMasterList1;
    }

    public void setArmMasterList1(List<ArmMaster> armMasterList1) {
        this.armMasterList1 = armMasterList1;
    }
    
}
