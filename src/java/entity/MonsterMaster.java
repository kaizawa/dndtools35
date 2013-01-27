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
    @NamedQuery(name = "MonsterMaster.findByName", query = "SELECT m FROM MonsterMaster m WHERE m.name = :name"),
    @NamedQuery(name = "MonsterMaster.findByHitDiceNum", query = "SELECT m FROM MonsterMaster m WHERE m.hitDiceNum = :hitDiceNum"),
    @NamedQuery(name = "MonsterMaster.findByInitiative", query = "SELECT m FROM MonsterMaster m WHERE m.initiative = :initiative"),
    @NamedQuery(name = "MonsterMaster.findBySpeed", query = "SELECT m FROM MonsterMaster m WHERE m.speed = :speed"),
    @NamedQuery(name = "MonsterMaster.findByAc", query = "SELECT m FROM MonsterMaster m WHERE m.ac = :ac"),
    @NamedQuery(name = "MonsterMaster.findByAcTouch", query = "SELECT m FROM MonsterMaster m WHERE m.acTouch = :acTouch"),
    @NamedQuery(name = "MonsterMaster.findByAcFlatFooted", query = "SELECT m FROM MonsterMaster m WHERE m.acFlatFooted = :acFlatFooted"),
    @NamedQuery(name = "MonsterMaster.findByBaseAttack", query = "SELECT m FROM MonsterMaster m WHERE m.baseAttack = :baseAttack"),
    @NamedQuery(name = "MonsterMaster.findByGrapple", query = "SELECT m FROM MonsterMaster m WHERE m.grapple = :grapple"),
    @NamedQuery(name = "MonsterMaster.findByAttack", query = "SELECT m FROM MonsterMaster m WHERE m.attack = :attack"),
    @NamedQuery(name = "MonsterMaster.findByReach", query = "SELECT m FROM MonsterMaster m WHERE m.reach = :reach"),
    @NamedQuery(name = "MonsterMaster.findBySpecialAttacks", query = "SELECT m FROM MonsterMaster m WHERE m.specialAttacks = :specialAttacks"),
    @NamedQuery(name = "MonsterMaster.findBySpecialQualities", query = "SELECT m FROM MonsterMaster m WHERE m.specialQualities = :specialQualities"),
    @NamedQuery(name = "MonsterMaster.findByEnvironment", query = "SELECT m FROM MonsterMaster m WHERE m.environment = :environment"),
    @NamedQuery(name = "MonsterMaster.findByOrganization", query = "SELECT m FROM MonsterMaster m WHERE m.organization = :organization"),
    @NamedQuery(name = "MonsterMaster.findByChallengeRating", query = "SELECT m FROM MonsterMaster m WHERE m.challengeRating = :challengeRating"),
    @NamedQuery(name = "MonsterMaster.findByTreasure", query = "SELECT m FROM MonsterMaster m WHERE m.treasure = :treasure"),
    @NamedQuery(name = "MonsterMaster.findByAdvancement", query = "SELECT m FROM MonsterMaster m WHERE m.advancement = :advancement"),
    @NamedQuery(name = "MonsterMaster.findByLevelAdjustment", query = "SELECT m FROM MonsterMaster m WHERE m.levelAdjustment = :levelAdjustment"),
    @NamedQuery(name = "MonsterMaster.findByHitPointModifier", query = "SELECT m FROM MonsterMaster m WHERE m.hitPointModifier = :hitPointModifier")})
public class MonsterMaster implements Serializable {
    @Size(max = 2000)
    @Column(name = "FULL_ATTACK", length = 2000)
    private String fullAttack;
    @JoinTable(name = "MONSTER_MASTER_SUB_TYPE_MASTER", joinColumns = {
        @JoinColumn(name = "MONSTERMASTERLIST_ID", referencedColumnName = "ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "SUBTYPEMASTERLIST_ID", referencedColumnName = "ID", nullable = false)})
    @ManyToMany
    private List<SubTypeMaster> subTypeMasterList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Size(max = 400)
    @Column(name = "NAME", length = 400)
    private String name;
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
    private Integer baseAttack = 0;
    @Column(name = "GRAPPLE")
    private Integer grapple = 0;
    @Size(max = 2000)
    @Column(name = "ATTACK", length = 2000)
    private String attack;
    @Column(name = "REACH")
    private Integer reach;
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
    @Size(max = 2000)
    @Column(name = "ADVANCEMENT", length = 2000)
    private String advancement;
    @Column(name = "LEVEL_ADJUSTMENT")
    private Integer levelAdjustment;
    @Column(name = "HIT_POINT_MODIFIER")
    private Integer hitPointModifier = 0;
    @JoinColumn(name = "TYPE", referencedColumnName = "ID")
    @ManyToOne
    private TypeMaster type;
    @JoinColumn(name = "SIZE_ID", referencedColumnName = "ID")
    @ManyToOne
    private SizeMaster sizeId;
    @JoinColumn(name = "HIT_DICE_TYPE", referencedColumnName = "ID")
    @ManyToOne
    private DiceMaster hitDiceType;
    @JoinColumn(name = "ALIGNMENT", referencedColumnName = "ID")
    @ManyToOne
    private AlignmentMaster alignment;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getReach() {
        return reach;
    }

    public void setReach(Integer reach) {
        this.reach = reach;
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

    public Integer getHitPointModifier() {
        return hitPointModifier;
    }

    public void setHitPointModifier(Integer hitPointModifier) {
        this.hitPointModifier = hitPointModifier;
    }

    public TypeMaster getType() {
        return type;
    }

    public void setType(TypeMaster type) {
        this.type = type;
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

    public AlignmentMaster getAlignment() {
        return alignment;
    }

    public void setAlignment(AlignmentMaster alignment) {
        this.alignment = alignment;
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
    
    @XmlTransient
    public List<SubTypeMaster> getSubTypeMasterList() {
        return subTypeMasterList;
}

    public void setSubTypeMasterList(List<SubTypeMaster> subTypeMasterList) {
        this.subTypeMasterList = subTypeMasterList;
    }

    public String getFullAttack() {
        return fullAttack;
    }

    public void setFullAttack(String fullAttack) {
        this.fullAttack = fullAttack;
    }
}
