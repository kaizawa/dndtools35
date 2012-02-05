/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.EncounterBattleMember;
import entity.ScenarioRecord;
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
    @NamedQuery(name = "ScenarioCharacterRecord.findById", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.id = :id"),
    @NamedQuery(name = "ScenarioCharacterRecord.findByName", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.name = :name"),
    @NamedQuery(name = "ScenarioCharacterRecord.findBySizeId", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.sizeId = :sizeId"),
    @NamedQuery(name = "ScenarioCharacterRecord.findByType", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.type = :type"),
    @NamedQuery(name = "ScenarioCharacterRecord.findByHitDice", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.hitDice = :hitDice"),
    @NamedQuery(name = "ScenarioCharacterRecord.findByHitPoint", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.hitPoint = :hitPoint"),
    @NamedQuery(name = "ScenarioCharacterRecord.findByInitiative", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.initiative = :initiative"),
    @NamedQuery(name = "ScenarioCharacterRecord.findBySpeed", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.speed = :speed"),
    @NamedQuery(name = "ScenarioCharacterRecord.findByAc", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.ac = :ac"),
    @NamedQuery(name = "ScenarioCharacterRecord.findByBaseAttackGrapple", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.baseAttackGrapple = :baseAttackGrapple"),
    @NamedQuery(name = "ScenarioCharacterRecord.findByAttack", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.attack = :attack"),
    @NamedQuery(name = "ScenarioCharacterRecord.findBySpaceReach", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.spaceReach = :spaceReach"),
    @NamedQuery(name = "ScenarioCharacterRecord.findBySpecialAttacks", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.specialAttacks = :specialAttacks"),
    @NamedQuery(name = "ScenarioCharacterRecord.findBySpecialQualities", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.specialQualities = :specialQualities"),
    @NamedQuery(name = "ScenarioCharacterRecord.findByEnvironment", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.environment = :environment"),
    @NamedQuery(name = "ScenarioCharacterRecord.findByOrganization", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.organization = :organization"),
    @NamedQuery(name = "ScenarioCharacterRecord.findByChallengeRating", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.challengeRating = :challengeRating"),
    @NamedQuery(name = "ScenarioCharacterRecord.findByTreasure", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.treasure = :treasure"),
    @NamedQuery(name = "ScenarioCharacterRecord.findByAlignment", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.alignment = :alignment"),
    @NamedQuery(name = "ScenarioCharacterRecord.findByAdvancement", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.advancement = :advancement"),
    @NamedQuery(name = "ScenarioCharacterRecord.findByLevelAdjustment", query = "SELECT s FROM ScenarioCharacterRecord s WHERE s.levelAdjustment = :levelAdjustment")})
public class ScenarioCharacterRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Size(max = 400)
    @Column(name = "NAME", length = 400)
    private String name;
    @Column(name = "SIZE_ID")
    private Integer sizeId;
    @Size(max = 400)
    @Column(name = "TYPE", length = 400)
    private String type;
    @Size(max = 400)
    @Column(name = "HIT_DICE", length = 400)
    private String hitDice;
    @Column(name = "HIT_POINT")
    private Integer hitPoint;
    @Column(name = "INITIATIVE")
    private Integer initiative;
    @Column(name = "SPEED")
    private Integer speed;
    @Size(max = 400)
    @Column(name = "AC", length = 400)
    private String ac;
    @Size(max = 400)
    @Column(name = "BASE_ATTACK_GRAPPLE", length = 400)
    private String baseAttackGrapple;
    @Size(max = 2000)
    @Column(name = "ATTACK", length = 2000)
    private String attack;
    @Size(max = 200)
    @Column(name = "SPACE_REACH", length = 200)
    private String spaceReach;
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
    @Size(max = 200)
    @Column(name = "ALIGNMENT", length = 200)
    private String alignment;
    @Size(max = 2000)
    @Column(name = "ADVANCEMENT", length = 2000)
    private String advancement;
    @Column(name = "LEVEL_ADJUSTMENT")
    private Integer levelAdjustment;
    @JoinColumn(name = "SCENARIO", referencedColumnName = "ID")
    @ManyToOne
    private ScenarioRecord scenario;
    @OneToMany(mappedBy = "scenarioCharacterRecord")
    private List<EncounterBattleMember> encounterBattleMemberList;

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

    public Integer getSizeId() {
        return sizeId;
    }

    public void setSizeId(Integer sizeId) {
        this.sizeId = sizeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public String getAc() {
        return ac;
    }

    public void setAc(String ac) {
        this.ac = ac;
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

    public String getSpaceReach() {
        return spaceReach;
    }

    public void setSpaceReach(String spaceReach) {
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

    public ScenarioRecord getScenario() {
        return scenario;
    }

    public void setScenario(ScenarioRecord scenario) {
        this.scenario = scenario;
    }

    @XmlTransient
    public List<EncounterBattleMember> getEncounterBattleMemberList() {
        return encounterBattleMemberList;
    }

    public void setEncounterBattleMemberList(List<EncounterBattleMember> encounterBattleMemberList) {
        this.encounterBattleMemberList = encounterBattleMemberList;
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
        return "ejb.ScenarioCharacterRecord[ id=" + id + " ]";
    }
    
}
