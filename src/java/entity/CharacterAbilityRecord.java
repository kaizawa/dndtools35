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
@Table(name = "CHARACTER_ABILITY_RECORD")
@NamedQueries({@NamedQuery(name = "CharacterAbilityRecord.findByCharacterId", query = "SELECT c FROM CharacterAbilityRecord c WHERE c.characterAbilityRecordPK.characterId = :characterId"), @NamedQuery(name = "CharacterAbilityRecord.findByAbilityId", query = "SELECT c FROM CharacterAbilityRecord c WHERE c.characterAbilityRecordPK.abilityId = :abilityId"), @NamedQuery(name = "CharacterAbilityRecord.findByBase", query = "SELECT c FROM CharacterAbilityRecord c WHERE c.base = :base"), @NamedQuery(name = "CharacterAbilityRecord.findByFeatModifier", query = "SELECT c FROM CharacterAbilityRecord c WHERE c.featModifier = :featModifier"), @NamedQuery(name = "CharacterAbilityRecord.findByMiscModifier", query = "SELECT c FROM CharacterAbilityRecord c WHERE c.miscModifier = :miscModifier"), @NamedQuery(name = "CharacterAbilityRecord.findByDescription", query = "SELECT c FROM CharacterAbilityRecord c WHERE c.description = :description")})
public class CharacterAbilityRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CharacterAbilityRecordPK characterAbilityRecordPK;
    @Column(name = "BASE")
    private Integer base;
    @Column(name = "FEAT_MODIFIER")
    private Integer featModifier;
    @Column(name = "MISC_MODIFIER")
    private Integer miscModifier;
    @Column(name = "DESCRIPTION")
    private String description;
    @JoinColumn(name = "ABILITY_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne
    private AbilityMaster abilityMaster;
    @JoinColumn(name = "CHARACTER_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne
    private CharacterRecord characterRecord;

    public CharacterAbilityRecord() {
    }

    public CharacterAbilityRecord(CharacterAbilityRecordPK characterAbilityRecordPK) {
        this.characterAbilityRecordPK = characterAbilityRecordPK;
    }

    public CharacterAbilityRecord(int characterId, int abilityId) {
        this.characterAbilityRecordPK = new CharacterAbilityRecordPK(characterId, abilityId);
    }

    public CharacterAbilityRecordPK getCharacterAbilityRecordPK() {
        return characterAbilityRecordPK;
    }

    public void setCharacterAbilityRecordPK(CharacterAbilityRecordPK characterAbilityRecordPK) {
        this.characterAbilityRecordPK = characterAbilityRecordPK;
    }

    public Integer getBase() {
        return base;
    }

    public void setBase(Integer base) {
        this.base = base;
    }

    public Integer getFeatModifier() {
        return featModifier;
    }

    public void setFeatModifier(Integer featModifier) {
        this.featModifier = featModifier;
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

    public AbilityMaster getAbilityMaster() {
        return abilityMaster;
    }

    public void setAbilityMaster(AbilityMaster abilityMaster) {
        this.abilityMaster = abilityMaster;
    }

    public CharacterRecord getCharacterRecord() {
        return characterRecord;
    }

    public void setCharacterRecord(CharacterRecord characterRecord) {
        this.characterRecord = characterRecord;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (characterAbilityRecordPK != null ? characterAbilityRecordPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CharacterAbilityRecord)) {
            return false;
        }
        CharacterAbilityRecord other = (CharacterAbilityRecord) object;
        if ((this.characterAbilityRecordPK == null && other.characterAbilityRecordPK != null) || (this.characterAbilityRecordPK != null && !this.characterAbilityRecordPK.equals(other.characterAbilityRecordPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CharacterAbilityRecord[characterAbilityRecordPK=" + characterAbilityRecordPK + "]";
    }

}
