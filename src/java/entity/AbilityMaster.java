/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ka78231
 */
@Entity
@Table(name = "ABILITY_MASTER")
@NamedQueries({@NamedQuery(name = "AbilityMaster.findById", query = "SELECT a FROM AbilityMaster a WHERE a.id = :id"), @NamedQuery(name = "AbilityMaster.findByAbilityName", query = "SELECT a FROM AbilityMaster a WHERE a.abilityName = :abilityName")})
public class AbilityMaster implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "abilityMaster")
    private List<MonsterSkillRecord> monsterSkillRecordList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "abilityMaster")
    private List<MonsterAbilityRecord> monsterAbilityRecordList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "ABILITY_NAME")
    private String abilityName;
    @OneToMany(mappedBy = "abilityId")
    private Collection<SkillMaster> skillMasterCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "abilityMaster")
    private Collection<ClassAbilityMaster> classAbilityMasterCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "abilityMaster")
    private Collection<CharacterAbilityRecord> characterAbilityRecordCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "abilityMaster")
    private Collection<RaceAbilityMaster> raceAbilityMasterCollection;
    @OneToMany(mappedBy = "abilityId")
    private Collection<SaveMaster> saveMasterCollection;

    public AbilityMaster() {
    }

    public AbilityMaster(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName;
    }

    public Collection<SkillMaster> getSkillMasterCollection() {
        return skillMasterCollection;
    }

    public void setSkillMasterCollection(Collection<SkillMaster> skillMasterCollection) {
        this.skillMasterCollection = skillMasterCollection;
    }

    public Collection<ClassAbilityMaster> getClassAbilityMasterCollection() {
        return classAbilityMasterCollection;
    }

    public void setClassAbilityMasterCollection(Collection<ClassAbilityMaster> classAbilityMasterCollection) {
        this.classAbilityMasterCollection = classAbilityMasterCollection;
    }

    public Collection<CharacterAbilityRecord> getCharacterAbilityRecordCollection() {
        return characterAbilityRecordCollection;
    }

    public void setCharacterAbilityRecordCollection(Collection<CharacterAbilityRecord> characterAbilityRecordCollection) {
        this.characterAbilityRecordCollection = characterAbilityRecordCollection;
    }

    public Collection<RaceAbilityMaster> getRaceAbilityMasterCollection() {
        return raceAbilityMasterCollection;
    }

    public void setRaceAbilityMasterCollection(Collection<RaceAbilityMaster> raceAbilityMasterCollection) {
        this.raceAbilityMasterCollection = raceAbilityMasterCollection;
    }

    public Collection<SaveMaster> getSaveMasterCollection() {
        return saveMasterCollection;
    }

    public void setSaveMasterCollection(Collection<SaveMaster> saveMasterCollection) {
        this.saveMasterCollection = saveMasterCollection;
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
        if (!(object instanceof AbilityMaster)) {
            return false;
        }
        AbilityMaster other = (AbilityMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.AbilityMaster[id=" + id + "]";
    }

    @XmlTransient
    public List<MonsterSkillRecord> getMonsterSkillRecordList() {
        return monsterSkillRecordList;
    }

    public void setMonsterSkillRecordList(List<MonsterSkillRecord> monsterSkillRecordList) {
        this.monsterSkillRecordList = monsterSkillRecordList;
    }

    @XmlTransient
    public List<MonsterAbilityRecord> getMonsterAbilityRecordList() {
        return monsterAbilityRecordList;
    }

    public void setMonsterAbilityRecordList(List<MonsterAbilityRecord> monsterAbilityRecordList) {
        this.monsterAbilityRecordList = monsterAbilityRecordList;
    }

}
