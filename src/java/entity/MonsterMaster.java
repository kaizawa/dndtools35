/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kaizawa
 */
@Entity
@Table(name = "MONSTER_MASTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MonsterMaster.findAll", query = "SELECT m FROM MonsterMaster m"),
    @NamedQuery(name = "MonsterMaster.findById", query = "SELECT m FROM MonsterMaster m WHERE m.id = :id"),
    @NamedQuery(name = "MonsterMaster.findByType", query = "SELECT m FROM MonsterMaster m WHERE m.type = :type"),
    @NamedQuery(name = "MonsterMaster.findByHitDiceNum", query = "SELECT m FROM MonsterMaster m WHERE m.hitDiceNum = :hitDiceNum"),
    @NamedQuery(name = "MonsterMaster.findByInitiative", query = "SELECT m FROM MonsterMaster m WHERE m.initiative = :initiative"),
    @NamedQuery(name = "MonsterMaster.findBySpeed", query = "SELECT m FROM MonsterMaster m WHERE m.speed = :speed"),
    @NamedQuery(name = "MonsterMaster.findByAc", query = "SELECT m FROM MonsterMaster m WHERE m.ac = :ac"),
    @NamedQuery(name = "MonsterMaster.findByAcTouch", query = "SELECT m FROM MonsterMaster m WHERE m.acTouch = :acTouch"),
    @NamedQuery(name = "MonsterMaster.findByAcFlatFooted", query = "SELECT m FROM MonsterMaster m WHERE m.acFlatFooted = :acFlatFooted"),
    @NamedQuery(name = "MonsterMaster.findByBaseAttack", query = "SELECT m FROM MonsterMaster m WHERE m.baseAttack = :baseAttack"),
    @NamedQuery(name = "MonsterMaster.findByGrapple", query = "SELECT m FROM MonsterMaster m WHERE m.grapple = :grapple"),
    @NamedQuery(name = "MonsterMaster.findByAttack", query = "SELECT m FROM MonsterMaster m WHERE m.attack = :attack"),
    @NamedQuery(name = "MonsterMaster.findBySpaceReach", query = "SELECT m FROM MonsterMaster m WHERE m.spaceReach = :spaceReach"),
    @NamedQuery(name = "MonsterMaster.findBySpecialAttacks", query = "SELECT m FROM MonsterMaster m WHERE m.specialAttacks = :specialAttacks"),
    @NamedQuery(name = "MonsterMaster.findBySpecialQualities", query = "SELECT m FROM MonsterMaster m WHERE m.specialQualities = :specialQualities"),
    @NamedQuery(name = "MonsterMaster.findByEnvironment", query = "SELECT m FROM MonsterMaster m WHERE m.environment = :environment"),
    @NamedQuery(name = "MonsterMaster.findByOrganization", query = "SELECT m FROM MonsterMaster m WHERE m.organization = :organization"),
    @NamedQuery(name = "MonsterMaster.findByChallengeRating", query = "SELECT m FROM MonsterMaster m WHERE m.challengeRating = :challengeRating"),
    @NamedQuery(name = "MonsterMaster.findByTreasure", query = "SELECT m FROM MonsterMaster m WHERE m.treasure = :treasure"),
    @NamedQuery(name = "MonsterMaster.findByAlignment", query = "SELECT m FROM MonsterMaster m WHERE m.alignment = :alignment"),
    @NamedQuery(name = "MonsterMaster.findByAdvancement", query = "SELECT m FROM MonsterMaster m WHERE m.advancement = :advancement"),
    @NamedQuery(name = "MonsterMaster.findByLevelAdjustment", query = "SELECT m FROM MonsterMaster m WHERE m.levelAdjustment = :levelAdjustment")})
public class MonsterMaster implements Serializable {
    @Size(max = 400)
    @Column(name = "NAME", length = 400)
    private String name;
    @JoinTable(name = "MONSTER_SUB_TYPE", joinColumns = {
        @JoinColumn(name = "MONSTER", referencedColumnName = "ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "MONSTER", referencedColumnName = "ID", nullable = false)})
    @ManyToMany
    private List<MonsterMaster> monsterMasterList;
    @ManyToMany(mappedBy = "monsterMasterList")
    private List<MonsterMaster> monsterMasterList1;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "TYPE")
    private Integer type;
    @Column(name = "HIT_DICE_NUM")
    private Integer hitDiceNum;
    @Column(name = "INITIATIVE")
    private Integer initiative;
    @Column(name = "SPEED")
    private Integer speed;
    @Column(name = "AC")
    private Integer ac;
    @Column(name = "AC_TOUCH")
    private Integer acTouch;
    @Column(name = "AC_FLAT_FOOTED")
    private Integer acFlatFooted;
    @Column(name = "BASE_ATTACK")
    private Integer baseAttack;
    @Column(name = "GRAPPLE")
    private Integer grapple;
    @Size(max = 2000)
    @Column(name = "ATTACK", length = 2000)
    private String attack;
    @Column(name = "SPACE_REACH")
    private Integer spaceReach;
    @Size(max = 4000)
    @Column(name = "SPECIAL_ATTACKS", length = 4000)
    private String specialAttacks;
    @Size(max = 2000)
    @Column(name = "SPECIAL_QUALITIES", length = 2000)
    private String specialQualities;
    @Size(max = 2000)
    @Column(name = "ENVIRONMENT", length = 2000)
    private String environment;
    @Size(max = 2000)
    @Column(name = "ORGANIZATION", length = 2000)
    private String organization;
    @Column(name = "CHALLENGE_RATING")
    private Integer challengeRating;
    @Size(max = 2000)
    @Column(name = "TREASURE", length = 2000)
    private String treasure;
    @Column(name = "ALIGNMENT")
    private Integer alignment;
    @Size(max = 2000)
    @Column(name = "ADVANCEMENT", length = 2000)
    private String advancement;
    @Column(name = "LEVEL_ADJUSTMENT")
    private Integer levelAdjustment;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "monsterMaster")
    private List<MonsterSkillRecord> monsterSkillRecordList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "monsterMaster")
    private List<MonsterSaveRecord> monsterSaveRecordList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "monsterMaster")
    private List<MonsterSubType> monsterSubTypeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "monsterMaster")
    private List<MonsterAbilityRecord> monsterAbilityRecordList;
    @JoinColumn(name = "SIZE_ID", referencedColumnName = "ID")
    @ManyToOne
    private SizeMaster sizeId;
    @JoinColumn(name = "HIT_DICE_TYPE", referencedColumnName = "ID")
    @ManyToOne
    private DiceMaster hitDiceType;

    public MonsterMaster() {
    }

    public MonsterMaster(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getHitDiceNum() {
        return hitDiceNum;
    }

    public void setHitDiceNum(Integer hitDiceNum) {
        this.hitDiceNum = hitDiceNum;
    }

    public Integer getInitiative() {
        return initiative;
    }

    public void setInitiative(Integer initiative) {
        this.initiative = initiative;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getAc() {
        return ac;
    }

    public void setAc(Integer ac) {
        this.ac = ac;
    }

    public Integer getAcTouch() {
        return acTouch;
    }

    public void setAcTouch(Integer acTouch) {
        this.acTouch = acTouch;
    }

    public Integer getAcFlatFooted() {
        return acFlatFooted;
    }

    public void setAcFlatFooted(Integer acFlatFooted) {
        this.acFlatFooted = acFlatFooted;
    }

    public Integer getBaseAttack() {
        return baseAttack;
    }

    public void setBaseAttack(Integer baseAttack) {
        this.baseAttack = baseAttack;
    }

    public Integer getGrapple() {
        return grapple;
    }

    public void setGrapple(Integer grapple) {
        this.grapple = grapple;
    }

    public String getAttack() {
        return attack;
    }

    public void setAttack(String attack) {
        this.attack = attack;
    }

    public Integer getSpaceReach() {
        return spaceReach;
    }

    public void setSpaceReach(Integer spaceReach) {
        this.spaceReach = spaceReach;
    }

    public String getSpecialAttacks() {
        return specialAttacks;
    }

    public void setSpecialAttacks(String specialAttacks) {
        this.specialAttacks = specialAttacks;
    }

    public String getSpecialQualities() {
        return specialQualities;
    }

    public void setSpecialQualities(String specialQualities) {
        this.specialQualities = specialQualities;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public Integer getChallengeRating() {
        return challengeRating;
    }

    public void setChallengeRating(Integer challengeRating) {
        this.challengeRating = challengeRating;
    }

    public String getTreasure() {
        return treasure;
    }

    public void setTreasure(String treasure) {
        this.treasure = treasure;
    }

    public Integer getAlignment() {
        return alignment;
    }

    public void setAlignment(Integer alignment) {
        this.alignment = alignment;
    }

    public String getAdvancement() {
        return advancement;
    }

    public void setAdvancement(String advancement) {
        this.advancement = advancement;
    }

    public Integer getLevelAdjustment() {
        return levelAdjustment;
    }

    public void setLevelAdjustment(Integer levelAdjustment) {
        this.levelAdjustment = levelAdjustment;
    }

    @XmlTransient
    public List<MonsterSkillRecord> getMonsterSkillRecordList() {
        return monsterSkillRecordList;
    }

    public void setMonsterSkillRecordList(List<MonsterSkillRecord> monsterSkillRecordList) {
        this.monsterSkillRecordList = monsterSkillRecordList;
    }

    @XmlTransient
    public List<MonsterSaveRecord> getMonsterSaveRecordList() {
        return monsterSaveRecordList;
    }

    public void setMonsterSaveRecordList(List<MonsterSaveRecord> monsterSaveRecordList) {
        this.monsterSaveRecordList = monsterSaveRecordList;
    }

    @XmlTransient
    public List<MonsterSubType> getMonsterSubTypeList() {
        return monsterSubTypeList;
    }

    public void setMonsterSubTypeList(List<MonsterSubType> monsterSubTypeList) {
        this.monsterSubTypeList = monsterSubTypeList;
    }

    @XmlTransient
    public List<MonsterAbilityRecord> getMonsterAbilityRecordList() {
        return monsterAbilityRecordList;
    }

    public void setMonsterAbilityRecordList(List<MonsterAbilityRecord> monsterAbilityRecordList) {
        this.monsterAbilityRecordList = monsterAbilityRecordList;
    }

    public SizeMaster getSizeId() {
        return sizeId;
    }

    public void setSizeId(SizeMaster sizeId) {
        this.sizeId = sizeId;
    }

    public DiceMaster getHitDiceType() {
        return hitDiceType;
    }

    public void setHitDiceType(DiceMaster hitDiceType) {
        this.hitDiceType = hitDiceType;
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
        if (!(object instanceof MonsterMaster)) {
            return false;
        }
        MonsterMaster other = (MonsterMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.MonsterMaster[ id=" + id + " ]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<MonsterMaster> getMonsterMasterList() {
        return monsterMasterList;
    }

    public void setMonsterMasterList(List<MonsterMaster> monsterMasterList) {
        this.monsterMasterList = monsterMasterList;
    }

    @XmlTransient
    public List<MonsterMaster> getMonsterMasterList1() {
        return monsterMasterList1;
    }

    public void setMonsterMasterList1(List<MonsterMaster> monsterMasterList1) {
        this.monsterMasterList1 = monsterMasterList1;
    }
    
}
