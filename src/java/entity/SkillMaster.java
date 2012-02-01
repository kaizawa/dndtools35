/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ka78231
 */
@Entity
@Table(name = "SKILL_MASTER")
@NamedQueries({@NamedQuery(name = "SkillMaster.findById", query = "SELECT s FROM SkillMaster s WHERE s.id = :id"), @NamedQuery(name = "SkillMaster.findBySkillName", query = "SELECT s FROM SkillMaster s WHERE s.skillName = :skillName"), @NamedQuery(name = "SkillMaster.findByAcceptNoRank", query = "SELECT s FROM SkillMaster s WHERE s.acceptNoRank = :acceptNoRank")})
public class SkillMaster implements Serializable {
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

}
