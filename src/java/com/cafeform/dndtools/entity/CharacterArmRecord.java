/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafeform.dndtools.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kaizawa
 */
@Entity
@Table(name = "CHARACTER_ARM_RECORD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CharacterArmRecord.findAll", query = "SELECT c FROM CharacterArmRecord c"),
    @NamedQuery(name = "CharacterArmRecord.findById", query = "SELECT c FROM CharacterArmRecord c WHERE c.id = :id"),
    @NamedQuery(name = "CharacterArmRecord.findByDescription", query = "SELECT c FROM CharacterArmRecord c WHERE c.description = :description")})
public class CharacterArmRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 4000)
    @Column(name = "DESCRIPTION")
    private String description;
    @JoinColumn(name = "CHARACTER_ID", referencedColumnName = "ID")
    @ManyToOne
    private CharacterRecord characterId;
    @JoinColumn(name = "ARM_ID", referencedColumnName = "ID")
    @ManyToOne
    private ArmMaster armId;

    public CharacterArmRecord() {
    }

    public CharacterArmRecord(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CharacterRecord getCharacterId() {
        return characterId;
    }

    public void setCharacterId(CharacterRecord characterId) {
        this.characterId = characterId;
    }

    public ArmMaster getArmId() {
        return armId;
    }

    public void setArmId(ArmMaster armId) {
        this.armId = armId;
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
        if (!(object instanceof CharacterArmRecord)) {
            return false;
        }
        CharacterArmRecord other = (CharacterArmRecord) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cafeform.dndtools.entity.CharacterArmRecord[ id=" + id + " ]";
    }
}
