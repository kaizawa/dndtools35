/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author ka78231
 */
@Entity
@Table(name = "CHARACTER_EQUIPMENT")
@NamedQueries({@NamedQuery(name = "CharacterEquipment.findByCharacterId", query = "SELECT c FROM CharacterEquipment c WHERE c.characterId = :characterId"), @NamedQuery(name = "CharacterEquipment.findByHead", query = "SELECT c FROM CharacterEquipment c WHERE c.head = :head"), @NamedQuery(name = "CharacterEquipment.findByCloak", query = "SELECT c FROM CharacterEquipment c WHERE c.cloak = :cloak"), @NamedQuery(name = "CharacterEquipment.findByNecklace", query = "SELECT c FROM CharacterEquipment c WHERE c.necklace = :necklace"), @NamedQuery(name = "CharacterEquipment.findByArmRing", query = "SELECT c FROM CharacterEquipment c WHERE c.armRing = :armRing"), @NamedQuery(name = "CharacterEquipment.findByRing1", query = "SELECT c FROM CharacterEquipment c WHERE c.ring1 = :ring1"), @NamedQuery(name = "CharacterEquipment.findByRing2", query = "SELECT c FROM CharacterEquipment c WHERE c.ring2 = :ring2"), @NamedQuery(name = "CharacterEquipment.findByBelt", query = "SELECT c FROM CharacterEquipment c WHERE c.belt = :belt"), @NamedQuery(name = "CharacterEquipment.findByArmor", query = "SELECT c FROM CharacterEquipment c WHERE c.armor = :armor"), @NamedQuery(name = "CharacterEquipment.findByArm1", query = "SELECT c FROM CharacterEquipment c WHERE c.arm1 = :arm1"), @NamedQuery(name = "CharacterEquipment.findByArm2", query = "SELECT c FROM CharacterEquipment c WHERE c.arm2 = :arm2"), @NamedQuery(name = "CharacterEquipment.findByEye", query = "SELECT c FROM CharacterEquipment c WHERE c.eye = :eye"), @NamedQuery(name = "CharacterEquipment.findByRobe", query = "SELECT c FROM CharacterEquipment c WHERE c.robe = :robe"), @NamedQuery(name = "CharacterEquipment.findByVest", query = "SELECT c FROM CharacterEquipment c WHERE c.vest = :vest"), @NamedQuery(name = "CharacterEquipment.findByGlove", query = "SELECT c FROM CharacterEquipment c WHERE c.glove = :glove"), @NamedQuery(name = "CharacterEquipment.findByShield", query = "SELECT c FROM CharacterEquipment c WHERE c.shield = :shield"), @NamedQuery(name = "CharacterEquipment.findByBoots", query = "SELECT c FROM CharacterEquipment c WHERE c.boots = :boots"), @NamedQuery(name = "CharacterEquipment.findByPocket", query = "SELECT c FROM CharacterEquipment c WHERE c.pocket = :pocket"), @NamedQuery(name = "CharacterEquipment.findBySkillArmorMod", query = "SELECT c FROM CharacterEquipment c WHERE c.skillArmorMod = :skillArmorMod"), @NamedQuery(name = "CharacterEquipment.findBySkillShieldMod", query = "SELECT c FROM CharacterEquipment c WHERE c.skillShieldMod = :skillShieldMod"), @NamedQuery(name = "CharacterEquipment.findByDexAcArmorLimit", query = "SELECT c FROM CharacterEquipment c WHERE c.dexAcArmorLimit = :dexAcArmorLimit"), @NamedQuery(name = "CharacterEquipment.findByDexAcShieldLimit", query = "SELECT c FROM CharacterEquipment c WHERE c.dexAcShieldLimit = :dexAcShieldLimit")})
public class CharacterEquipment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "CHARACTER_ID", nullable = false)
    private Integer characterId;
    @Column(name = "HEAD")
    private String head;
    @Column(name = "CLOAK")
    private String cloak;
    @Column(name = "NECKLACE")
    private String necklace;
    @Column(name = "ARM_RING")
    private String armRing;
    @Column(name = "RING1")
    private String ring1;
    @Column(name = "RING2")
    private String ring2;
    @Column(name = "BELT")
    private String belt;
    @Column(name = "ARMOR")
    private String armor;
    @Column(name = "ARM1")
    private String arm1;
    @Column(name = "ARM2")
    private String arm2;
    @Column(name = "EYE")
    private String eye;
    @Column(name = "ROBE")
    private String robe;
    @Column(name = "VEST")
    private String vest;
    @Column(name = "GLOVE")
    private String glove;
    @Column(name = "SHIELD")
    private String shield;
    @Column(name = "BOOTS")
    private String boots;
    @Column(name = "POCKET")
    private Integer pocket;
    @Column(name = "SKILL_ARMOR_MOD")
    private Integer skillArmorMod;
    @Column(name = "SKILL_SHIELD_MOD")
    private Integer skillShieldMod;
    @Column(name = "DEX_AC_ARMOR_LIMIT")
    private Integer dexAcArmorLimit;
    @Column(name = "DEX_AC_SHIELD_LIMIT")
    private Integer dexAcShieldLimit;
    @JoinColumn(name = "CHARACTER_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @OneToOne
    private CharacterRecord characterRecord;

    public CharacterEquipment() {
    }

    public CharacterEquipment(Integer characterId) {
        this.characterId = characterId;
    }

    public Integer getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getCloak() {
        return cloak;
    }

    public void setCloak(String cloak) {
        this.cloak = cloak;
    }

    public String getNecklace() {
        return necklace;
    }

    public void setNecklace(String necklace) {
        this.necklace = necklace;
    }

    public String getArmRing() {
        return armRing;
    }

    public void setArmRing(String armRing) {
        this.armRing = armRing;
    }

    public String getRing1() {
        return ring1;
    }

    public void setRing1(String ring1) {
        this.ring1 = ring1;
    }

    public String getRing2() {
        return ring2;
    }

    public void setRing2(String ring2) {
        this.ring2 = ring2;
    }

    public String getBelt() {
        return belt;
    }

    public void setBelt(String belt) {
        this.belt = belt;
    }

    public String getArmor() {
        return armor;
    }

    public void setArmor(String armor) {
        this.armor = armor;
    }

    public String getArm1() {
        return arm1;
    }

    public void setArm1(String arm1) {
        this.arm1 = arm1;
    }

    public String getArm2() {
        return arm2;
    }

    public void setArm2(String arm2) {
        this.arm2 = arm2;
    }

    public String getEye() {
        return eye;
    }

    public void setEye(String eye) {
        this.eye = eye;
    }

    public String getRobe() {
        return robe;
    }

    public void setRobe(String robe) {
        this.robe = robe;
    }

    public String getVest() {
        return vest;
    }

    public void setVest(String vest) {
        this.vest = vest;
    }

    public String getGlove() {
        return glove;
    }

    public void setGlove(String glove) {
        this.glove = glove;
    }

    public String getShield() {
        return shield;
    }

    public void setShield(String shield) {
        this.shield = shield;
    }

    public String getBoots() {
        return boots;
    }

    public void setBoots(String boots) {
        this.boots = boots;
    }

    public Integer getPocket() {
        return pocket;
    }

    public void setPocket(Integer pocket) {
        this.pocket = pocket;
    }

    public Integer getSkillArmorMod() {
        return skillArmorMod;
    }

    public void setSkillArmorMod(Integer skillArmorMod) {
        this.skillArmorMod = skillArmorMod;
    }

    public Integer getSkillShieldMod() {
        return skillShieldMod;
    }

    public void setSkillShieldMod(Integer skillShieldMod) {
        this.skillShieldMod = skillShieldMod;
    }

    public Integer getDexAcArmorLimit() {
        return dexAcArmorLimit;
    }

    public void setDexAcArmorLimit(Integer dexAcArmorLimit) {
        this.dexAcArmorLimit = dexAcArmorLimit;
    }

    public Integer getDexAcShieldLimit() {
        return dexAcShieldLimit;
    }

    public void setDexAcShieldLimit(Integer dexAcShieldLimit) {
        this.dexAcShieldLimit = dexAcShieldLimit;
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
        hash += (characterId != null ? characterId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CharacterEquipment)) {
            return false;
        }
        CharacterEquipment other = (CharacterEquipment) object;
        if ((this.characterId == null && other.characterId != null) || (this.characterId != null && !this.characterId.equals(other.characterId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CharacterEquipment[characterId=" + characterId + "]";
    }

}
