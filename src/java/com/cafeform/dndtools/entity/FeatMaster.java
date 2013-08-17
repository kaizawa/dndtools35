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
@Table(name = "FEAT_MASTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FeatMaster.findAll", query = "SELECT f FROM FeatMaster f"),
    @NamedQuery(name = "FeatMaster.findById", query = "SELECT f FROM FeatMaster f WHERE f.id = :id"),
    @NamedQuery(name = "FeatMaster.findByName", query = "SELECT f FROM FeatMaster f WHERE f.name = :name"),
    @NamedQuery(name = "FeatMaster.findByDescription", query = "SELECT f FROM FeatMaster f WHERE f.description = :description")})
public class FeatMaster implements Serializable {
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
    @Size(max = 4000)
    @Column(name = "DESCRIPTION", length = 4000)
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "featMaster")
    private List<MonsterFeatRecord> monsterFeatRecordList;

    public FeatMaster() {
    }

    public FeatMaster(Integer id) {
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
    public List<MonsterFeatRecord> getMonsterFeatRecordList() {
        return monsterFeatRecordList;
    }

    public void setMonsterFeatRecordList(List<MonsterFeatRecord> monsterFeatRecordList) {
        this.monsterFeatRecordList = monsterFeatRecordList;
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
        if (!(object instanceof FeatMaster)) {
            return false;
        }
        FeatMaster other = (FeatMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.FeatMaster[ id=" + id + " ]";
    }
    
}
