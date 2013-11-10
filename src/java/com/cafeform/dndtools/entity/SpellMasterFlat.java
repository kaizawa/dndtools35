/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
@Table(name = "SPELL_MASTER_FLAT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SpellMasterFlat.findAll", query = "SELECT s FROM SpellMasterFlat s"),
    @NamedQuery(name = "SpellMasterFlat.findById", query = "SELECT s FROM SpellMasterFlat s WHERE s.id = :id"),
    @NamedQuery(name = "SpellMasterFlat.findByName", query = "SELECT s FROM SpellMasterFlat s WHERE s.name = :name"),
    @NamedQuery(name = "SpellMasterFlat.findByEnglishName", query = "SELECT s FROM SpellMasterFlat s WHERE s.englishName = :englishName"),
    @NamedQuery(name = "SpellMasterFlat.findByJapaneseName", query = "SELECT s FROM SpellMasterFlat s WHERE s.japaneseName = :japaneseName"),
    @NamedQuery(name = "SpellMasterFlat.findBySchool", query = "SELECT s FROM SpellMasterFlat s WHERE s.school = :school"),
    @NamedQuery(name = "SpellMasterFlat.findBySubschool", query = "SELECT s FROM SpellMasterFlat s WHERE s.subschool = :subschool"),
    @NamedQuery(name = "SpellMasterFlat.findByComplement", query = "SELECT s FROM SpellMasterFlat s WHERE s.complement = :complement"),
    @NamedQuery(name = "SpellMasterFlat.findBySound", query = "SELECT s FROM SpellMasterFlat s WHERE s.sound = :sound"),
    @NamedQuery(name = "SpellMasterFlat.findByAction", query = "SELECT s FROM SpellMasterFlat s WHERE s.action = :action"),
    @NamedQuery(name = "SpellMasterFlat.findByComponent", query = "SELECT s FROM SpellMasterFlat s WHERE s.component = :component"),
    @NamedQuery(name = "SpellMasterFlat.findByExperience", query = "SELECT s FROM SpellMasterFlat s WHERE s.experience = :experience"),
    @NamedQuery(name = "SpellMasterFlat.findByCastingTime", query = "SELECT s FROM SpellMasterFlat s WHERE s.castingTime = :castingTime"),
    @NamedQuery(name = "SpellMasterFlat.findByDuration", query = "SELECT s FROM SpellMasterFlat s WHERE s.duration = :duration"),
    @NamedQuery(name = "SpellMasterFlat.findByDistance", query = "SELECT s FROM SpellMasterFlat s WHERE s.distance = :distance"),
    @NamedQuery(name = "SpellMasterFlat.findByTargetArea", query = "SELECT s FROM SpellMasterFlat s WHERE s.targetArea = :targetArea"),
    @NamedQuery(name = "SpellMasterFlat.findBySavingThrow", query = "SELECT s FROM SpellMasterFlat s WHERE s.savingThrow = :savingThrow"),
    @NamedQuery(name = "SpellMasterFlat.findByResistance", query = "SELECT s FROM SpellMasterFlat s WHERE s.resistance = :resistance"),
    @NamedQuery(name = "SpellMasterFlat.findByDescription", query = "SELECT s FROM SpellMasterFlat s WHERE s.description = :description"),
    @NamedQuery(name = "SpellMasterFlat.findByManual", query = "SELECT s FROM SpellMasterFlat s WHERE s.manual = :manual"),
    @NamedQuery(name = "SpellMasterFlat.findByWizard", query = "SELECT s FROM SpellMasterFlat s WHERE s.wizard = :wizard"),
    @NamedQuery(name = "SpellMasterFlat.findByCleric", query = "SELECT s FROM SpellMasterFlat s WHERE s.cleric = :cleric"),
    @NamedQuery(name = "SpellMasterFlat.findBySorcerer", query = "SELECT s FROM SpellMasterFlat s WHERE s.sorcerer = :sorcerer"),
    @NamedQuery(name = "SpellMasterFlat.findByDruid", query = "SELECT s FROM SpellMasterFlat s WHERE s.druid = :druid"),
    @NamedQuery(name = "SpellMasterFlat.findByBard", query = "SELECT s FROM SpellMasterFlat s WHERE s.bard = :bard"),
    @NamedQuery(name = "SpellMasterFlat.findByPaladin", query = "SELECT s FROM SpellMasterFlat s WHERE s.paladin = :paladin"),
    @NamedQuery(name = "SpellMasterFlat.findByRanger", query = "SELECT s FROM SpellMasterFlat s WHERE s.ranger = :ranger"),
    @NamedQuery(name = "SpellMasterFlat.findByAirDomain", query = "SELECT s FROM SpellMasterFlat s WHERE s.airDomain = :airDomain"),
    @NamedQuery(name = "SpellMasterFlat.findByAnimalDomain", query = "SELECT s FROM SpellMasterFlat s WHERE s.animalDomain = :animalDomain"),
    @NamedQuery(name = "SpellMasterFlat.findByChaosDomain", query = "SELECT s FROM SpellMasterFlat s WHERE s.chaosDomain = :chaosDomain"),
    @NamedQuery(name = "SpellMasterFlat.findByDeathDomain", query = "SELECT s FROM SpellMasterFlat s WHERE s.deathDomain = :deathDomain"),
    @NamedQuery(name = "SpellMasterFlat.findByDestructionDomain", query = "SELECT s FROM SpellMasterFlat s WHERE s.destructionDomain = :destructionDomain"),
    @NamedQuery(name = "SpellMasterFlat.findByEarthDomain", query = "SELECT s FROM SpellMasterFlat s WHERE s.earthDomain = :earthDomain"),
    @NamedQuery(name = "SpellMasterFlat.findByEvilDomain", query = "SELECT s FROM SpellMasterFlat s WHERE s.evilDomain = :evilDomain"),
    @NamedQuery(name = "SpellMasterFlat.findByFireDomain", query = "SELECT s FROM SpellMasterFlat s WHERE s.fireDomain = :fireDomain"),
    @NamedQuery(name = "SpellMasterFlat.findByGoodDomain", query = "SELECT s FROM SpellMasterFlat s WHERE s.goodDomain = :goodDomain"),
    @NamedQuery(name = "SpellMasterFlat.findByHealingDomain", query = "SELECT s FROM SpellMasterFlat s WHERE s.healingDomain = :healingDomain"),
    @NamedQuery(name = "SpellMasterFlat.findByKnowledgeDomain", query = "SELECT s FROM SpellMasterFlat s WHERE s.knowledgeDomain = :knowledgeDomain"),
    @NamedQuery(name = "SpellMasterFlat.findByLawDomain", query = "SELECT s FROM SpellMasterFlat s WHERE s.lawDomain = :lawDomain"),
    @NamedQuery(name = "SpellMasterFlat.findByLuckDomain", query = "SELECT s FROM SpellMasterFlat s WHERE s.luckDomain = :luckDomain"),
    @NamedQuery(name = "SpellMasterFlat.findByMagicDomain", query = "SELECT s FROM SpellMasterFlat s WHERE s.magicDomain = :magicDomain"),
    @NamedQuery(name = "SpellMasterFlat.findByPlantDomain", query = "SELECT s FROM SpellMasterFlat s WHERE s.plantDomain = :plantDomain"),
    @NamedQuery(name = "SpellMasterFlat.findByProtectionDomain", query = "SELECT s FROM SpellMasterFlat s WHERE s.protectionDomain = :protectionDomain"),
    @NamedQuery(name = "SpellMasterFlat.findByStrengthDomain", query = "SELECT s FROM SpellMasterFlat s WHERE s.strengthDomain = :strengthDomain"),
    @NamedQuery(name = "SpellMasterFlat.findBySunDomain", query = "SELECT s FROM SpellMasterFlat s WHERE s.sunDomain = :sunDomain"),
    @NamedQuery(name = "SpellMasterFlat.findByTravelDomain", query = "SELECT s FROM SpellMasterFlat s WHERE s.travelDomain = :travelDomain"),
    @NamedQuery(name = "SpellMasterFlat.findByTrickeryDomain", query = "SELECT s FROM SpellMasterFlat s WHERE s.trickeryDomain = :trickeryDomain"),
    @NamedQuery(name = "SpellMasterFlat.findByWarDomain", query = "SELECT s FROM SpellMasterFlat s WHERE s.warDomain = :warDomain"),
    @NamedQuery(name = "SpellMasterFlat.findByWaterDomain", query = "SELECT s FROM SpellMasterFlat s WHERE s.waterDomain = :waterDomain")})
public class SpellMasterFlat implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 200)
    @Column(name = "NAME")
    private String name;
    @Size(max = 200)
    @Column(name = "ENGLISH_NAME")
    private String englishName;
    @Size(max = 200)
    @Column(name = "JAPANESE_NAME")
    private String japaneseName;
    @Size(max = 200)
    @Column(name = "SCHOOL")
    private String school;
    @Size(max = 200)
    @Column(name = "SUBSCHOOL")
    private String subschool;
    @Size(max = 200)
    @Column(name = "COMPLEMENT")
    private String complement;
    @Column(name = "SOUND")
    private Integer sound;
    @Column(name = "ACTION")
    private Integer action;
    @Column(name = "COMPONENT")
    private Integer component;
    @Column(name = "EXPERIENCE")
    private Integer experience;
    @Size(max = 200)
    @Column(name = "CASTING_TIME")
    private String castingTime;
    @Size(max = 200)
    @Column(name = "DURATION")
    private String duration;
    @Size(max = 200)
    @Column(name = "DISTANCE")
    private String distance;
    @Size(max = 200)
    @Column(name = "TARGET_AREA")
    private String targetArea;
    @Size(max = 200)
    @Column(name = "SAVING_THROW")
    private String savingThrow;
    @Size(max = 200)
    @Column(name = "RESISTANCE")
    private String resistance;
    @Size(max = 2000)
    @Column(name = "DESCRIPTION")
    private String description;
    @Size(max = 200)
    @Column(name = "MANUAL")
    private String manual;
    @Column(name = "WIZARD")
    private Integer wizard;
    @Column(name = "CLERIC")
    private Integer cleric;
    @Column(name = "SORCERER")
    private Integer sorcerer;
    @Column(name = "DRUID")
    private Integer druid;
    @Column(name = "BARD")
    private Integer bard;
    @Column(name = "PALADIN")
    private Integer paladin;
    @Column(name = "RANGER")
    private Integer ranger;
    @Column(name = "AIR_DOMAIN")
    private Integer airDomain;
    @Column(name = "ANIMAL_DOMAIN")
    private Integer animalDomain;
    @Column(name = "CHAOS_DOMAIN")
    private Integer chaosDomain;
    @Column(name = "DEATH_DOMAIN")
    private Integer deathDomain;
    @Column(name = "DESTRUCTION_DOMAIN")
    private Integer destructionDomain;
    @Column(name = "EARTH_DOMAIN")
    private Integer earthDomain;
    @Column(name = "EVIL_DOMAIN")
    private Integer evilDomain;
    @Column(name = "FIRE_DOMAIN")
    private Integer fireDomain;
    @Column(name = "GOOD_DOMAIN")
    private Integer goodDomain;
    @Column(name = "HEALING_DOMAIN")
    private Integer healingDomain;
    @Column(name = "KNOWLEDGE_DOMAIN")
    private Integer knowledgeDomain;
    @Column(name = "LAW_DOMAIN")
    private Integer lawDomain;
    @Column(name = "LUCK_DOMAIN")
    private Integer luckDomain;
    @Column(name = "MAGIC_DOMAIN")
    private Integer magicDomain;
    @Column(name = "PLANT_DOMAIN")
    private Integer plantDomain;
    @Column(name = "PROTECTION_DOMAIN")
    private Integer protectionDomain;
    @Column(name = "STRENGTH_DOMAIN")
    private Integer strengthDomain;
    @Column(name = "SUN_DOMAIN")
    private Integer sunDomain;
    @Column(name = "TRAVEL_DOMAIN")
    private Integer travelDomain;
    @Column(name = "TRICKERY_DOMAIN")
    private Integer trickeryDomain;
    @Column(name = "WAR_DOMAIN")
    private Integer warDomain;
    @Column(name = "WATER_DOMAIN")
    private Integer waterDomain;

    public SpellMasterFlat() {
    }

    public SpellMasterFlat(Integer id) {
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

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getJapaneseName() {
        return japaneseName;
    }

    public void setJapaneseName(String japaneseName) {
        this.japaneseName = japaneseName;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSubschool() {
        return subschool;
    }

    public void setSubschool(String subschool) {
        this.subschool = subschool;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public Integer getSound() {
        return sound;
    }

    public void setSound(Integer sound) {
        this.sound = sound;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public Integer getComponent() {
        return component;
    }

    public void setComponent(Integer component) {
        this.component = component;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public String getCastingTime() {
        return castingTime;
    }

    public void setCastingTime(String castingTime) {
        this.castingTime = castingTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getTargetArea() {
        return targetArea;
    }

    public void setTargetArea(String targetArea) {
        this.targetArea = targetArea;
    }

    public String getSavingThrow() {
        return savingThrow;
    }

    public void setSavingThrow(String savingThrow) {
        this.savingThrow = savingThrow;
    }

    public String getResistance() {
        return resistance;
    }

    public void setResistance(String resistance) {
        this.resistance = resistance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManual() {
        return manual;
    }

    public void setManual(String manual) {
        this.manual = manual;
    }

    public Integer getWizard() {
        return wizard;
    }

    public void setWizard(Integer wizard) {
        this.wizard = wizard;
    }

    public Integer getCleric() {
        return cleric;
    }

    public void setCleric(Integer cleric) {
        this.cleric = cleric;
    }

    public Integer getSorcerer() {
        return sorcerer;
    }

    public void setSorcerer(Integer sorcerer) {
        this.sorcerer = sorcerer;
    }

    public Integer getDruid() {
        return druid;
    }

    public void setDruid(Integer druid) {
        this.druid = druid;
    }

    public Integer getBard() {
        return bard;
    }

    public void setBard(Integer bard) {
        this.bard = bard;
    }

    public Integer getPaladin() {
        return paladin;
    }

    public void setPaladin(Integer paladin) {
        this.paladin = paladin;
    }

    public Integer getRanger() {
        return ranger;
    }

    public void setRanger(Integer ranger) {
        this.ranger = ranger;
    }

    public Integer getAirDomain() {
        return airDomain;
    }

    public void setAirDomain(Integer airDomain) {
        this.airDomain = airDomain;
    }

    public Integer getAnimalDomain() {
        return animalDomain;
    }

    public void setAnimalDomain(Integer animalDomain) {
        this.animalDomain = animalDomain;
    }

    public Integer getChaosDomain() {
        return chaosDomain;
    }

    public void setChaosDomain(Integer chaosDomain) {
        this.chaosDomain = chaosDomain;
    }

    public Integer getDeathDomain() {
        return deathDomain;
    }

    public void setDeathDomain(Integer deathDomain) {
        this.deathDomain = deathDomain;
    }

    public Integer getDestructionDomain() {
        return destructionDomain;
    }

    public void setDestructionDomain(Integer destructionDomain) {
        this.destructionDomain = destructionDomain;
    }

    public Integer getEarthDomain() {
        return earthDomain;
    }

    public void setEarthDomain(Integer earthDomain) {
        this.earthDomain = earthDomain;
    }

    public Integer getEvilDomain() {
        return evilDomain;
    }

    public void setEvilDomain(Integer evilDomain) {
        this.evilDomain = evilDomain;
    }

    public Integer getFireDomain() {
        return fireDomain;
    }

    public void setFireDomain(Integer fireDomain) {
        this.fireDomain = fireDomain;
    }

    public Integer getGoodDomain() {
        return goodDomain;
    }

    public void setGoodDomain(Integer goodDomain) {
        this.goodDomain = goodDomain;
    }

    public Integer getHealingDomain() {
        return healingDomain;
    }

    public void setHealingDomain(Integer healingDomain) {
        this.healingDomain = healingDomain;
    }

    public Integer getKnowledgeDomain() {
        return knowledgeDomain;
    }

    public void setKnowledgeDomain(Integer knowledgeDomain) {
        this.knowledgeDomain = knowledgeDomain;
    }

    public Integer getLawDomain() {
        return lawDomain;
    }

    public void setLawDomain(Integer lawDomain) {
        this.lawDomain = lawDomain;
    }

    public Integer getLuckDomain() {
        return luckDomain;
    }

    public void setLuckDomain(Integer luckDomain) {
        this.luckDomain = luckDomain;
    }

    public Integer getMagicDomain() {
        return magicDomain;
    }

    public void setMagicDomain(Integer magicDomain) {
        this.magicDomain = magicDomain;
    }

    public Integer getPlantDomain() {
        return plantDomain;
    }

    public void setPlantDomain(Integer plantDomain) {
        this.plantDomain = plantDomain;
    }

    public Integer getProtectionDomain() {
        return protectionDomain;
    }

    public void setProtectionDomain(Integer protectionDomain) {
        this.protectionDomain = protectionDomain;
    }

    public Integer getStrengthDomain() {
        return strengthDomain;
    }

    public void setStrengthDomain(Integer strengthDomain) {
        this.strengthDomain = strengthDomain;
    }

    public Integer getSunDomain() {
        return sunDomain;
    }

    public void setSunDomain(Integer sunDomain) {
        this.sunDomain = sunDomain;
    }

    public Integer getTravelDomain() {
        return travelDomain;
    }

    public void setTravelDomain(Integer travelDomain) {
        this.travelDomain = travelDomain;
    }

    public Integer getTrickeryDomain() {
        return trickeryDomain;
    }

    public void setTrickeryDomain(Integer trickeryDomain) {
        this.trickeryDomain = trickeryDomain;
    }

    public Integer getWarDomain() {
        return warDomain;
    }

    public void setWarDomain(Integer warDomain) {
        this.warDomain = warDomain;
    }

    public Integer getWaterDomain() {
        return waterDomain;
    }

    public void setWaterDomain(Integer waterDomain) {
        this.waterDomain = waterDomain;
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
        if (!(object instanceof SpellMasterFlat)) {
            return false;
        }
        SpellMasterFlat other = (SpellMasterFlat) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cafeform.dndtools.entity.SpellMasterFlat[ id=" + id + " ]";
    }
    
}
