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
@Table(name = "SKILL_MASTER")
@NamedQueries({@NamedQuery(name = "SkillMaster.findById", query = "SELECT s FROM SkillMaster s WHERE s.id = :id"), @NamedQuery(name = "SkillMaster.findBySkillName", query = "SELECT s FROM SkillMaster s WHERE s.skillName = :skillName"), @NamedQuery(name = "SkillMaster.findByAcceptNoRank", query = "SELECT s FROM SkillMaster s WHERE s.acceptNoRank = :acceptNoRank")})
public class SkillMaster implements Serializable {
    @JoinTable(name = "SKILL_SYNERGY_MASTER", joinColumns = {
        @JoinColumn(name = "SKILL_ID", referencedColumnName = "ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "SKILL_ID", referencedColumnName = "ID", nullable = false)})
    @ManyToMany
    private List<SkillMaster> skillMasterList;
    @ManyToMany(mappedBy = "skillMasterList")
    private List<SkillMaster> skillMasterList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "skillMaster")
    private List<MonsterSkillRecord> monsterSkillRecordList;
    @Column(name = "ARMOR_CHECK")
    private Integer armorCheck;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "SKILL_NAME")
    private String skillName;
    @Column(name = "ACCEPT_NO_RANK")
    private Integer acceptNoRank;
    @JoinColumn(name = "ABILITY_ID", referencedColumnName = "ID")
    @ManyToOne
    private AbilityMaster abilityId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "skillMaster")
    private Collection<CharacterSkillGrowthRecord> characterSkillGrowthRecordCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "skillMaster")
    private Collection<CharacterSkillRecord> characterSkillRecordCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "skillMaster")
    private List<SkillSynergyMaster> skillSynergyMasterList;

    public List<SkillSynergyMaster> getSkillSynergyMasterList() {
        return skillSynergyMasterList;
    }

    public void setSkillSynergyMasterList(List<SkillSynergyMaster> skillSynergyMasterList) {
        this.skillSynergyMasterList = skillSynergyMasterList;
    }

    public int getArmorCheck() {
        return armorCheck;
    }

    public void setArmorCheck(int armorCheck) {
        this.armorCheck = armorCheck;
    }

    public SkillMaster() {
    }

    public SkillMaster(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public Integer getAcceptNoRank() {
        return acceptNoRank;
    }

    public void setAcceptNoRank(Integer acceptNoRank) {
        this.acceptNoRank = acceptNoRank;
    }

    public AbilityMaster getAbilityId() {
        return abilityId;
    }

    public void setAbilityId(AbilityMaster abilityId) {
        this.abilityId = abilityId;
    }

    public Collection<CharacterSkillGrowthRecord> getCharacterSkillGrowthRecordCollection() {
        return characterSkillGrowthRecordCollection;
    }

    public void setCharacterSkillGrowthRecordCollection(Collection<CharacterSkillGrowthRecord> characterSkillGrowthRecordCollection) {
        this.characterSkillGrowthRecordCollection = characterSkillGrowthRecordCollection;
    }

    public Collection<CharacterSkillRecord> getCharacterSkillRecordCollection() {
        return characterSkillRecordCollection;
    }

    public void setCharacterSkillRecordCollection(Collection<CharacterSkillRecord> characterSkillRecordCollection) {
        this.characterSkillRecordCollection = characterSkillRecordCollection;
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
        if (!(object instanceof SkillMaster)) {
            return false;
        }
        SkillMaster other = (SkillMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.SkillMaster[id=" + id + "]";
    }

    public void setArmorCheck(Integer armorCheck) {
        this.armorCheck = armorCheck;
    }

    @XmlTransient
    public List<SkillMaster> getSkillMasterList() {
        return skillMasterList;
    }

    public void setSkillMasterList(List<SkillMaster> skillMasterList) {
        this.skillMasterList = skillMasterList;
    }

    @XmlTransient
    public List<SkillMaster> getSkillMasterList1() {
        return skillMasterList1;
    }

    public void setSkillMasterList1(List<SkillMaster> skillMasterList1) {
        this.skillMasterList1 = skillMasterList1;
    }

    @XmlTransient
    public List<MonsterSkillRecord> getMonsterSkillRecordList() {
        return monsterSkillRecordList;
    }

    public void setMonsterSkillRecordList(List<MonsterSkillRecord> monsterSkillRecordList) {
        this.monsterSkillRecordList = monsterSkillRecordList;
    }

}
