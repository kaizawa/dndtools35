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
@Table(name = "SUB_TYPE_MASTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SubTypeMaster.findAll", query = "SELECT s FROM SubTypeMaster s"),
    @NamedQuery(name = "SubTypeMaster.findById", query = "SELECT s FROM SubTypeMaster s WHERE s.id = :id"),
    @NamedQuery(name = "SubTypeMaster.findByName", query = "SELECT s FROM SubTypeMaster s WHERE s.name = :name")})
public class SubTypeMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Size(max = 100)
    @Column(name = "NAME", length = 100)
    private String name;
    @ManyToMany(mappedBy = "subTypeMasterList")
    private List<MonsterMaster> monsterMasterList;

    public SubTypeMaster() {
    }

    public SubTypeMaster(Integer id) {
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
        if (!(object instanceof SubTypeMaster)) {
            return false;
        }
        SubTypeMaster other = (SubTypeMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.SubTypeMaster[ id=" + id + " ]";
    }
    
}
