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
@Table(name = "CHARACTER_SKILL_RECORD")
@NamedQueries({@NamedQuery(name = "CharacterSkillRecord.findByCharacterId", query = "SELECT c FROM CharacterSkillRecord c WHERE c.characterSkillRecordPK.characterId = :characterId"), @NamedQuery(name = "CharacterSkillRecord.findBySkillId", query = "SELECT c FROM CharacterSkillRecord c WHERE c.characterSkillRecordPK.skillId = :skillId"), @NamedQuery(name = "CharacterSkillRecord.findByMiscModifier", query = "SELECT c FROM CharacterSkillRecord c WHERE c.miscModifier = :miscModifier"), @NamedQuery(name = "CharacterSkillRecord.findByDescription", query = "SELECT c FROM CharacterSkillRecord c WHERE c.description = :description")})
public class CharacterSkillRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CharacterSkillRecordPK characterSkillRecordPK;
    @Column(name = "MISC_MODIFIER")
    private Integer miscModifier;
    @Column(name = "DESCRIPTION")
    private String description;
    @JoinColumn(name = "CHARACTER_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne
    private CharacterRecord characterRecord;
    @JoinColumn(name = "SKILL_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne
    private SkillMaster skillMaster;

    public CharacterSkillRecord() {
    }

    public CharacterSkillRecord(CharacterSkillRecordPK characterSkillRecordPK) {
        this.characterSkillRecordPK = characterSkillRecordPK;
    }

    public CharacterSkillRecord(int characterId, int skillId) {
        this.characterSkillRecordPK = new CharacterSkillRecordPK(characterId, skillId);
    }

    public CharacterSkillRecordPK getCharacterSkillRecordPK() {
        return characterSkillRecordPK;
    }

    public void setCharacterSkillRecordPK(CharacterSkillRecordPK characterSkillRecordPK) {
        this.characterSkillRecordPK = characterSkillRecordPK;
    }

    public Integer getMiscModifier() {
        return miscModifier;
    }

    public void setMiscModifier(Integer miscModifier) {
        this.miscModifier = miscModifier;
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
        hash += (characterSkillRecordPK != null ? characterSkillRecordPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CharacterSkillRecord)) {
            return false;
        }
        CharacterSkillRecord other = (CharacterSkillRecord) object;
        if ((this.characterSkillRecordPK == null && other.characterSkillRecordPK != null) || (this.characterSkillRecordPK != null && !this.characterSkillRecordPK.equals(other.characterSkillRecordPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CharacterSkillRecord[characterSkillRecordPK=" + characterSkillRecordPK + "]";
    }

}
