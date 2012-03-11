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
@Table(name = "SCENARIO_CHARACTER_RECORD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScenarioCharacterRecord.findAll", query = "SELECT s FROM ScenarioCharacterRecord s"),
    @NamedQuery(name = "ScenarioCharacterRecord.findByScenario", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.scenario = :scenario"),
    @NamedQuery(name = "ScenarioCharacterRecord.findById", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.id = :id"),
    @NamedQuery(name = "ScenarioCharacterRecord.findByName", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.name = :name"),
    @NamedQuery(name = "ScenarioCharacterRecord.findByKlass", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.klass = :klass"),
    @NamedQuery(name = "ScenarioCharacterRecord.findBySizeAndType", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.sizeAndType = :sizeAndType"),
    @NamedQuery(name = "ScenarioCharacterRecord.findByHitDice", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.hitDice = :hitDice"),
    @NamedQuery(name = "ScenarioCharacterRecord.findByHitPoint", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.hitPoint = :hitPoint"),
    @NamedQuery(name = "ScenarioCharacterRecord.findByInitiative", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.initiative = :initiative"),
    @NamedQuery(name = "ScenarioCharacterRecord.findByBaseAttackGrapple", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.baseAttackGrapple = :baseAttackGrapple"),
    @NamedQuery(name = "ScenarioCharacterRecord.findByAttack", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.attack = :attack"),
    @NamedQuery(name = "ScenarioCharacterRecord.findByFullAttack", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.fullAttack = :fullAttack"),
    @NamedQuery(name = "ScenarioCharacterRecord.findBySpaceAndReach", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.spaceAndReach = :spaceAndReach"),
    @NamedQuery(name = "ScenarioCharacterRecord.findBySpecialAttacks", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.specialAttacks = :specialAttacks"),
    @NamedQuery(name = "ScenarioCharacterRecord.findBySpecialQualities", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.specialQualities = :specialQualities"),
    @NamedQuery(name = "ScenarioCharacterRecord.findBySave", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.save = :save"),
    @NamedQuery(name = "ScenarioCharacterRecord.findByAbilities", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.abilities = :abilities"),
    @NamedQuery(name = "ScenarioCharacterRecord.findBySkills", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.skills = :skills"),
    @NamedQuery(name = "ScenarioCharacterRecord.findByFeats", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.feats = :feats"),
    @NamedQuery(name = "ScenarioCharacterRecord.findByEnvironment", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.environment = :environment"),
    @NamedQuery(name = "ScenarioCharacterRecord.findByOrganization", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.organization = :organization"),
    @NamedQuery(name = "ScenarioCharacterRecord.findByChallengeRating", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.challengeRating = :challengeRating"),
    @NamedQuery(name = "ScenarioCharacterRecord.findByTreasure", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.treasure = :treasure"),
    @NamedQuery(name = "ScenarioCharacterRecord.findByAlignment", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.alignment = :alignment"),
    @NamedQuery(name = "ScenarioCharacterRecord.findByAdvancement", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.advancement = :advancement"),
    @NamedQuery(name = "ScenarioCharacterRecord.findByLevelAdjustment", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.levelAdjustment = :levelAdjustment"),
    @NamedQuery(name = "ScenarioCharacterRecord.findByIsPlayerCharacter", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.isPlayerCharacter = :isPlayerCharacter"),
    @NamedQuery(name = "ScenarioCharacterRecord.findByComments", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.comments = :comments"),
    @NamedQuery(name = "ScenarioCharacterRecord.findByArmorClass", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.armorClass = :armorClass"),
    @NamedQuery(name = "ScenarioCharacterRecord.findBySpeed", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.speed = :speed")})
public class ScenarioCharacterRecord implements Serializable {
    @OneToMany(mappedBy = "scenarioCharacterRecord")
    private List<EncounterMember> encounterMemberList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Size(max = 400)
    @Column(name = "NAME", length = 400)
    private String name;
    @Size(max = 200)
    @Column(name = "KLASS", length = 200)
    private String klass;
    @Size(max = 400)
    @Column(name = "SIZE_AND_TYPE", length = 400)
    private String sizeAndType;
    @Size(max = 400)
    @Column(name = "HIT_DICE", length = 400)
    private String hitDice;
    @Column(name = "HIT_POINT")
    private Integer hitPoint;
    @Column(name = "INITIATIVE")
    private Integer initiative;
    @Size(max = 400)
    @Column(name = "BASE_ATTACK_GRAPPLE", length = 400)
    private String baseAttackGrapple;
    @Size(max = 2000)
    @Column(name = "ATTACK", length = 2000)
    private String attack;
    @Size(max = 2000)
    @Column(name = "FULL_ATTACK", length = 2000)
    private String fullAttack;
    @Size(max = 200)
    @Column(name = "SPACE_AND_REACH", length = 200)
    private String spaceAndReach;
    @Size(max = 4000)
    @Column(name = "SPECIAL_ATTACKS", length = 4000)
    private String specialAttacks;
    @Size(max = 2000)
    @Column(name = "SPECIAL_QUALITIES", length = 2000)
    private String specialQualities;
    @Size(max = 2000)
    @Column(name = "SAVE", length = 2000)
    private String save;
    @Size(max = 2000)
    @Column(name = "ABILITIES", length = 2000)
    private String abilities;
    @Size(max = 2000)
    @Column(name = "SKILLS", length = 2000)
    private String skills;
    @Size(max = 2000)
    @Column(name = "FEATS", length = 2000)
    private String feats;
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
    @Size(max = 200)
    @Column(name = "ALIGNMENT", length = 200)
    private String alignment;
    @Size(max = 2000)
    @Column(name = "ADVANCEMENT", length = 2000)
    private String advancement;
    @Column(name = "LEVEL_ADJUSTMENT")
    private Integer levelAdjustment;
    @Column(name = "IS_PLAYER_CHARACTER")
    private Short isPlayerCharacter = 0;
    @Size(max = 4000)
    @Column(name = "COMMENTS", length = 4000)
    private String comments;
    @Size(max = 400)
    @Column(name = "ARMOR_CLASS", length = 400)
    private String armorClass;
    @Size(max = 400)
    @Column(name = "SPEED", length = 400)
    private String speed;
    @JoinColumn(name = "SCENARIO", referencedColumnName = "ID")
    @ManyToOne
    private ScenarioRecord scenario;

    public ScenarioCharacterRecord() {
    }

    public ScenarioCharacterRecord(Integer id) {
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

    public String getKlass() {
        return klass;
    }

    public void setKlass(String klass) {
        this.klass = klass;
    }

    public String getSizeAndType() {
        return sizeAndType;
    }

    public void setSizeAndType(String sizeAndType) {
        this.sizeAndType = sizeAndType;
    }

    public String getHitDice() {
        return hitDice;
    }

    public void setHitDice(String hitDice) {
        this.hitDice = hitDice;
    }

    public Integer getHitPoint() {
        return hitPoint;
    }

    public void setHitPoint(Integer hitPoint) {
        this.hitPoint = hitPoint;
    }

    public Integer getInitiative() {
        return initiative;
    }

    public void setInitiative(Integer initiative) {
        this.initiative = initiative;
    }

    public String getBaseAttackGrapple() {
        return baseAttackGrapple;
    }

    public void setBaseAttackGrapple(String baseAttackGrapple) {
        this.baseAttackGrapple = baseAttackGrapple;
    }

    public String getAttack() {
        return attack;
    }

    public void setAttack(String attack) {
        this.attack = attack;
    }

    public String getFullAttack() {
        return fullAttack;
    }

    public void setFullAttack(String fullAttack) {
        this.fullAttack = fullAttack;
    }

    public String getSpaceAndReach() {
        return spaceAndReach;
    }

    public void setSpaceAndReach(String spaceAndReach) {
        this.spaceAndReach = spaceAndReach;
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

    public String getSave() {
        return save;
    }

    public void setSave(String save) {
        this.save = save;
    }

    public String getAbilities() {
        return abilities;
    }

    public void setAbilities(String abilities) {
        this.abilities = abilities;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getFeats() {
        return feats;
    }

    public void setFeats(String feats) {
        this.feats = feats;
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

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
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

    public Short getIsPlayerCharacter() {
        return isPlayerCharacter;
    }

    public void setIsPlayerCharacter(Short isPlayerCharacter) {
        this.isPlayerCharacter = isPlayerCharacter;
    }
    
    public Boolean isPlayerCharacter (){
        return getIsPlayerCharacter() != 0 ? true : false;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getArmorClass() {
        return armorClass;
    }

    public void setArmorClass(String armorClass) {
        this.armorClass = armorClass;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public ScenarioRecord getScenario() {
        return scenario;
    }

    public void setScenario(ScenarioRecord scenario) {
        this.scenario = scenario;
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
        if (!(object instanceof ScenarioCharacterRecord)) {
            return false;
        }
        ScenarioCharacterRecord other = (ScenarioCharacterRecord) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ScenarioCharacterRecord[ id=" + id + " ]";
    }

    @XmlTransient
    public List<EncounterMember> getEncounterMemberList() {
        return encounterMemberList;
    }

    public void setEncounterMemberList(List<EncounterMember> encounterMemberList) {
        this.encounterMemberList = encounterMemberList;
    }
    
}
