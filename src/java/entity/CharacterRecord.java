/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
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
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ka78231
 */
@Entity
@Table(name = "CHARACTER_RECORD")
@NamedQueries({
    @NamedQuery(name = "CharacterRecord.findById", query = "SELECT c FROM CharacterRecord c WHERE c.id = :id"),
    @NamedQuery(name = "CharacterRecord.findBySaveTime", query = "SELECT c FROM CharacterRecord c WHERE c.saveTime = :saveTime"),
    @NamedQuery(name = "CharacterRecord.findBySheetId", query = "SELECT c FROM CharacterRecord c WHERE c.sheetId = :sheetId"),
    @NamedQuery(name = "CharacterRecord.findByCharacterName", query = "SELECT c FROM CharacterRecord c WHERE c.characterName = :characterName"),
    @NamedQuery(name = "CharacterRecord.findByPlayerName", query = "SELECT c FROM CharacterRecord c WHERE c.playerName = :playerName"),
    @NamedQuery(name = "CharacterRecord.findByExperience", query = "SELECT c FROM CharacterRecord c WHERE c.experience = :experience"),
    @NamedQuery(name = "CharacterRecord.findByAge", query = "SELECT c FROM CharacterRecord c WHERE c.age = :age"),
    @NamedQuery(name = "CharacterRecord.findByHeight", query = "SELECT c FROM CharacterRecord c WHERE c.height = :height"),
    @NamedQuery(name = "CharacterRecord.findByWeight", query = "SELECT c FROM CharacterRecord c WHERE c.weight = :weight"),
    @NamedQuery(name = "CharacterRecord.findByDescription", query = "SELECT c FROM CharacterRecord c WHERE c.description = :description"),
    @NamedQuery(name = "CharacterRecord.findByInitiativeMiscModifier", query = "SELECT c FROM CharacterRecord c WHERE c.initiativeMiscModifier = :initiativeMiscModifier"),
    @NamedQuery(name = "CharacterRecord.findByInitiativeFeatModifier", query = "SELECT c FROM CharacterRecord c WHERE c.initiativeFeatModifier = :initiativeFeatModifier"),
    @NamedQuery(name = "CharacterRecord.findBySpeedMiscModifier", query = "SELECT c FROM CharacterRecord c WHERE c.speedMiscModifier = :speedMiscModifier"),
    @NamedQuery(name = "CharacterRecord.findBySpeedFeatModifier", query = "SELECT c FROM CharacterRecord c WHERE c.speedFeatModifier = :speedFeatModifier"),
    @NamedQuery(name = "CharacterRecord.findByHairColor", query = "SELECT c FROM CharacterRecord c WHERE c.hairColor = :hairColor"),
    @NamedQuery(name = "CharacterRecord.findBySkinColor", query = "SELECT c FROM CharacterRecord c WHERE c.skinColor = :skinColor"),
    @NamedQuery(name = "CharacterRecord.findByEyeColor", query = "SELECT c FROM CharacterRecord c WHERE c.eyeColor = :eyeColor"),
    @NamedQuery(name = "CharacterRecord.findByDamageReduction", query = "SELECT c FROM CharacterRecord c WHERE c.damageReduction = :damageReduction"),
    @NamedQuery(name = "CharacterRecord.findBySpellResistance", query = "SELECT c FROM CharacterRecord c WHERE c.spellResistance = :spellResistance"),
    @NamedQuery(name = "CharacterRecord.findByLanguage", query = "SELECT c FROM CharacterRecord c WHERE c.language = :language"),
    @NamedQuery(name = "CharacterRecord.findByFeatDescription", query = "SELECT c FROM CharacterRecord c WHERE c.featDescription = :featDescription"),
    @NamedQuery(name = "CharacterRecord.findByAttackDescription", query = "SELECT c FROM CharacterRecord c WHERE c.attackDescription = :attackDescription"),
    @NamedQuery(name = "CharacterRecord.findBySpellDescription", query = "SELECT c FROM CharacterRecord c WHERE c.spellDescription = :spellDescription"),
    @NamedQuery(name = "CharacterRecord.findByItemDescription", query = "SELECT c FROM CharacterRecord c WHERE c.itemDescription = :itemDescription"),
    @NamedQuery(name = "CharacterRecord.findByAcArmor", query = "SELECT c FROM CharacterRecord c WHERE c.acArmor = :acArmor"),
    @NamedQuery(name = "CharacterRecord.findByAcShield", query = "SELECT c FROM CharacterRecord c WHERE c.acShield = :acShield"),
    @NamedQuery(name = "CharacterRecord.findByAcMiscMod", query = "SELECT c FROM CharacterRecord c WHERE c.acMiscMod = :acMiscMod")})
public class CharacterRecord implements Serializable {
    @Column(name =     "SAVE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date saveTime;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "SHEET_ID")
    private Integer sheetId;
    @Column(name = "CHARACTER_NAME")
    private String characterName;
    @Column(name = "PLAYER_NAME")
    private String playerName;
    @Column(name = "EXPERIENCE")
    private Integer experience;
    @Column(name = "AGE")
    private Integer age;
    @Column(name = "HEIGHT")
    private Integer height;
    @Column(name = "WEIGHT")
    private Integer weight;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "INITIATIVE_MISC_MODIFIER")
    private Integer initiativeMiscModifier;
    @Column(name = "INITIATIVE_FEAT_MODIFIER")
    private Integer initiativeFeatModifier;
    @Column(name = "SPEED_MISC_MODIFIER")
    private Integer speedMiscModifier;
    @Column(name = "SPEED_FEAT_MODIFIER")
    private Integer speedFeatModifier;
    @Column(name = "HAIR_COLOR")
    private String hairColor;
    @Column(name = "SKIN_COLOR")
    private String skinColor;
    @Column(name = "EYE_COLOR")
    private String eyeColor;
    @Column(name = "DAMAGE_REDUCTION")
    private Integer damageReduction;
    @Column(name = "SPELL_RESISTANCE")
    private Integer spellResistance;
    @Column(name = "LANGUAGE")
    private String language;
    @Column(name = "FEAT_DESCRIPTION")
    private String featDescription;
    @Column(name = "ATTACK_DESCRIPTION")
    private String attackDescription;
    @Column(name = "SPELL_DESCRIPTION")
    private String spellDescription;
    @Column(name = "ITEM_DESCRIPTION")
    private String itemDescription;
    @Column(name = "DEFENCE_DESCRIPTION")
    private String defenceDescription;
    @Column(name = "UPDATE_DESCRIPTION")
    private String updateDescription;
    @Column(name = "AC_ARMOR")
    private Integer acArmor;
    @Column(name = "AC_SHIELD")
    private Integer acShield;
    @Column(name = "AC_MISC_MOD")
    private Integer acMiscMod;
    @JoinColumn(name = "ALIGNMENT_ID", referencedColumnName = "ID")
    @ManyToOne
    private AlignmentMaster alignmentId;
    @JoinColumn(name = "CAMPAIGN_ID", referencedColumnName = "ID")
    @ManyToOne
    private CampaignMaster campaignId;
    @JoinColumn(name = "GENDER_ID", referencedColumnName = "ID")
    @ManyToOne
    private GenderMaster genderId;
    @JoinColumn(name = "RACE_ID", referencedColumnName = "ID")
    @ManyToOne
    private RaceMaster raceId;
    @JoinColumn(name = "RELIGION_ID", referencedColumnName = "ID")
    @ManyToOne
    private ReligionMaster religionId;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "characterRecord")
    private CharacterEquipment characterEquipment;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "characterRecord")
    private List<CharacterAbilityRecord> characterAbilityRecordList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "characterRecord")
    private List<CharacterGrowthRecord> characterGrowthRecordList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "characterRecord")
    private List<CharacterSaveRecord> characterSaveRecordList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "characterRecord")
    private List<CharacterSkillGrowthRecord> characterSkillGrowthRecordList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "characterRecord")
    private List<CharacterSkillRecord> characterSkillRecordList;

    public List<CharacterAbilityRecord> getCharacterAbilityRecordList() {
        return characterAbilityRecordList;
    }

    public CharacterEquipment getCharacterEquipment() {
        return characterEquipment;
    }

    public void setCharacterEquipment(CharacterEquipment characterEquipment) {
        this.characterEquipment = characterEquipment;
    }

    public List<CharacterGrowthRecord> getCharacterGrowthRecordList() {
        return characterGrowthRecordList;
    }

    public void setCharacterGrowthRecordList(List<CharacterGrowthRecord> characterGrowthRecordList) {
        this.characterGrowthRecordList = characterGrowthRecordList;
    }

    public List<CharacterSaveRecord> getCharacterSaveRecordList() {
        return characterSaveRecordList;
    }

    public void setCharacterSaveRecordList(List<CharacterSaveRecord> characterSaveRecordList) {
        this.characterSaveRecordList = characterSaveRecordList;
    }

    public List<CharacterSkillGrowthRecord> getCharacterSkillGrowthRecordList() {
        return characterSkillGrowthRecordList;
    }

    public void setCharacterSkillGrowthRecordList(List<CharacterSkillGrowthRecord> characterSkillGrowthRecordList) {
        this.characterSkillGrowthRecordList = characterSkillGrowthRecordList;
    }

    public List<CharacterSkillRecord> getCharacterSkillRecordList() {
        return characterSkillRecordList;
    }

    public void setCharacterSkillRecordList(List<CharacterSkillRecord> characterSkillRecordList) {
        this.characterSkillRecordList = characterSkillRecordList;
    }
    public void setCharacterAbilityRecordList(List<CharacterAbilityRecord> characterAbilityRecordCollection) {
        this.characterAbilityRecordList = characterAbilityRecordCollection;
    }

    public String getUpdateDescription() {
        return updateDescription;
    }

    public void setUpdateDescription(String updateDescription) {
        this.updateDescription = updateDescription;
    }

    public CharacterRecord() {
    }

    public CharacterRecord(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(Date saveTime) {
        this.saveTime = saveTime;
    }

    public Integer getSheetId() {
        return sheetId;
    }

    public void setSheetId(Integer sheetId) {
        this.sheetId = sheetId;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getInitiativeMiscModifier() {
        return initiativeMiscModifier;
    }

    public void setInitiativeMiscModifier(Integer initiativeMiscModifier) {
        this.initiativeMiscModifier = initiativeMiscModifier;
    }

    public Integer getInitiativeFeatModifier() {
        return initiativeFeatModifier;
    }

    public void setInitiativeFeatModifier(Integer initiativeFeatModifier) {
        this.initiativeFeatModifier = initiativeFeatModifier;
    }

    public Integer getSpeedMiscModifier() {
        return speedMiscModifier;
    }

    public void setSpeedMiscModifier(Integer speedMiscModifier) {
        this.speedMiscModifier = speedMiscModifier;
    }

    public Integer getSpeedFeatModifier() {
        return speedFeatModifier;
    }

    public void setSpeedFeatModifier(Integer speedFeatModifier) {
        this.speedFeatModifier = speedFeatModifier;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(String skinColor) {
        this.skinColor = skinColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public Integer getDamageReduction() {
        return damageReduction;
    }

    public void setDamageReduction(Integer damageReduction) {
        this.damageReduction = damageReduction;
    }

    public Integer getSpellResistance() {
        return spellResistance;
    }

    public void setSpellResistance(Integer spellResistance) {
        this.spellResistance = spellResistance;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getFeatDescription() {
        return featDescription;
    }

    public void setFeatDescription(String featDescription) {
        this.featDescription = featDescription;
    }

    public String getAttackDescription() {
        return attackDescription;
    }

    public void setAttackDescription(String attackDescription) {
        this.attackDescription = attackDescription;
    }

    public String getSpellDescription() {
        return spellDescription;
    }

    public void setSpellDescription(String spellDescription) {
        this.spellDescription = spellDescription;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public Integer getAcArmor() {
        return acArmor;
    }

    public void setAcArmor(Integer acArmor) {
        this.acArmor = acArmor;
    }

    public Integer getAcShield() {
        return acShield;
    }

    public void setAcShield(Integer acShield) {
        this.acShield = acShield;
    }

    public Integer getAcMiscMod() {
        return acMiscMod;
    }

    public void setAcMiscMod(Integer acMiscMod) {
        this.acMiscMod = acMiscMod;
    }

    public AlignmentMaster getAlignmentId() {
        return alignmentId;
    }

    public void setAlignmentId(AlignmentMaster alignmentId) {
        this.alignmentId = alignmentId;
    }

    public CampaignMaster getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(CampaignMaster campaignId) {
        this.campaignId = campaignId;
    }

    public GenderMaster getGenderId() {
        return genderId;
    }

    public void setGenderId(GenderMaster genderId) {
        this.genderId = genderId;
    }

    public RaceMaster getRaceId() {
        return raceId;
    }

    public void setRaceId(RaceMaster raceId) {
        this.raceId = raceId;
    }

    public ReligionMaster getReligionId() {
        return religionId;
    }

    public void setReligionId(ReligionMaster religionId) {
        this.religionId = religionId;
    }

    public String getDefenceDescription() {
        return defenceDescription;
    }

    public void setDefenceDescription(String defenceDescription) {
        this.defenceDescription = defenceDescription;
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
        if (!(object instanceof CharacterRecord)) {
            return false;
        }
        CharacterRecord other = (CharacterRecord) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CharacterRecord[id=" + id + "]";
    }
}
