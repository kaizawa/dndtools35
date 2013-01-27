/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author ka78231
 */
@Entity
@Table(name = "CHARACTER_SKILL_GROWTH_RECORD")
@NamedQueries({@NamedQuery(name = "CharacterSkillGrowthRecord.findByCharacterId", query = "SELECT c FROM CharacterSkillGrowthRecord c WHERE c.characterSkillGrowthRecordPK.characterId = :characterId"), @NamedQuery(name = "CharacterSkillGrowthRecord.findByCharacterLevel", query = "SELECT c FROM CharacterSkillGrowthRecord c WHERE c.characterSkillGrowthRecordPK.characterLevel = :characterLevel"), @NamedQuery(name = "CharacterSkillGrowthRecord.findBySkillId", query = "SELECT c FROM CharacterSkillGrowthRecord c WHERE c.characterSkillGrowthRecordPK.skillId = :skillId"), @NamedQuery(name = "CharacterSkillGrowthRecord.findBySkillPoint", query = "SELECT c FROM CharacterSkillGrowthRecord c WHERE c.skillPoint = :skillPoint"), @NamedQuery(name = "CharacterSkillGrowthRecord.findByDescription", query = "SELECT c FROM CharacterSkillGrowthRecord c WHERE c.description = :description")})
public class CharacterSkillGrowthRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CharacterSkillGrowthRecordPK characterSkillGrowthRecordPK;
    @Column(name = "SKILL_POINT")
    private Integer skillPoint;
    @Column(name = "DESCRIPTION")
    private String description;
    @JoinColumn(name = "CHARACTER_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne
    private CharacterRecord characterRecord;
    @JoinColumn(name = "SKILL_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne
    private SkillMaster skillMaster;

    public CharacterSkillGrowthRecord() {
    }

    public CharacterSkillGrowthRecord(CharacterSkillGrowthRecordPK characterSkillGrowthRecordPK) {
        this.characterSkillGrowthRecordPK = characterSkillGrowthRecordPK;
    }

    public CharacterSkillGrowthRecord(int characterId, int characterLevel, int skillId) {
        this.characterSkillGrowthRecordPK = new CharacterSkillGrowthRecordPK(characterId, characterLevel, skillId);
    }

    public CharacterSkillGrowthRecordPK getCharacterSkillGrowthRecordPK() {
        return characterSkillGrowthRecordPK;
    }

    public void setCharacterSkillGrowthRecordPK(CharacterSkillGrowthRecordPK characterSkillGrowthRecordPK) {
        this.characterSkillGrowthRecordPK = characterSkillGrowthRecordPK;
    }

    public Integer getSkillPoint() {
        return skillPoint;
    }

    public void setSkillPoint(Integer skillPoint) {
        this.skillPoint = skillPoint;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CharacterRecord getCharacterRecord() {
        return characterRecord;
    }

    public void setCharacterRecord(CharacterRecord characterRecord) {
        this.characterRecord = characterRecord;
    }

    public SkillMaster getSkillMaster() {
        return skillMaster;
    }

    public void setSkillMaster(SkillMaster skillMaster) {
        this.skillMaster = skillMaster;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (characterSkillGrowthRecordPK != null ? characterSkillGrowthRecordPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CharacterSkillGrowthRecord)) {
            return false;
        }
        CharacterSkillGrowthRecord other = (CharacterSkillGrowthRecord) object;
        if ((this.characterSkillGrowthRecordPK == null && other.characterSkillGrowthRecordPK != null) || (this.characterSkillGrowthRecordPK != null && !this.characterSkillGrowthRecordPK.equals(other.characterSkillGrowthRecordPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CharacterSkillGrowthRecord[characterSkillGrowthRecordPK=" + characterSkillGrowthRecordPK + "]";
    }

}
