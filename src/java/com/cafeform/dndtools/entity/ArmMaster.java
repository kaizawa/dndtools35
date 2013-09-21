package com.cafeform.dndtools.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kaizawa
 */
@Entity
@Table(name = "ARM_MASTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ArmMaster.findAll", query = "SELECT a FROM ArmMaster a"),
    @NamedQuery(name = "ArmMaster.findById", query = "SELECT a FROM ArmMaster a WHERE a.id = :id"),
    @NamedQuery(name = "ArmMaster.findByName", query = "SELECT a FROM ArmMaster a WHERE a.name = :name"),
    @NamedQuery(name = "ArmMaster.findByWeight", query = "SELECT a FROM ArmMaster a WHERE a.weight = :weight"),
    @NamedQuery(name = "ArmMaster.findByPrice", query = "SELECT a FROM ArmMaster a WHERE a.price = :price"),
    @NamedQuery(name = "ArmMaster.findByPage", query = "SELECT a FROM ArmMaster a WHERE a.page = :page"),
    @NamedQuery(name = "ArmMaster.findByNaturalReachMultiplier", query = "SELECT a FROM ArmMaster a WHERE a.naturalReachMultiplier = :naturalReachMultiplier"),
    @NamedQuery(name = "ArmMaster.findByRange", query = "SELECT a FROM ArmMaster a WHERE a.range = :range"),
    @NamedQuery(name = "ArmMaster.findByDamageDiceNum", query = "SELECT a FROM ArmMaster a WHERE a.damageDiceNum = :damageDiceNum"),
    @NamedQuery(name = "ArmMaster.findByCriticalMultiplier", query = "SELECT a FROM ArmMaster a WHERE a.criticalMultiplier = :criticalMultiplier"),
    @NamedQuery(name = "ArmMaster.findByThreatRange", query = "SELECT a FROM ArmMaster a WHERE a.threatRange = :threatRange"),
    @NamedQuery(name = "ArmMaster.findBySecondDamageDiceNum", query = "SELECT a FROM ArmMaster a WHERE a.secondDamageDiceNum = :secondDamageDiceNum"),
    @NamedQuery(name = "ArmMaster.findBySecondCriticalMultiplier", query = "SELECT a FROM ArmMaster a WHERE a.secondCriticalMultiplier = :secondCriticalMultiplier"),
    @NamedQuery(name = "ArmMaster.findBySecondThreatRange", query = "SELECT a FROM ArmMaster a WHERE a.secondThreatRange = :secondThreatRange"),
    @NamedQuery(name = "ArmMaster.findBySecondDamageType", query = "SELECT a FROM ArmMaster a WHERE a.secondDamageType = :secondDamageType"),
    @NamedQuery(name = "ArmMaster.findByEnhancementBonus", query = "SELECT a FROM ArmMaster a WHERE a.enhancementBonus = :enhancementBonus"),
    @NamedQuery(name = "ArmMaster.findByDescription", query = "SELECT a FROM ArmMaster a WHERE a.description = :description")})
public class ArmMaster implements Serializable {
    @OneToMany(mappedBy = "armId")
    private List<CharacterArmRecord> characterArmRecordList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 400)
    @Column(name = "NAME")
    private String name;
    @Column(name = "WEIGHT")
    private Integer weight;
    @Column(name = "PRICE")
    private Integer price;
    @Column(name = "PAGE")
    private Integer page;
    @Column(name = "NATURAL_REACH_MULTIPLIER")
    private Integer naturalReachMultiplier;
    @Column(name = "RANGE")
    private Integer range;
    @Column(name = "DAMAGE_DICE_NUM")
    private Integer damageDiceNum;
    @Column(name = "CRITICAL_MULTIPLIER")
    private Integer criticalMultiplier;
    @Column(name = "THREAT_RANGE")
    private Integer threatRange;
    @Column(name = "SECOND_DAMAGE_DICE_NUM")
    private Integer secondDamageDiceNum;
    @Column(name = "SECOND_CRITICAL_MULTIPLIER")
    private Integer secondCriticalMultiplier;
    @Column(name = "SECOND_THREAT_RANGE")
    private Integer secondThreatRange;
    @JoinColumn(name = "SECOND_DAMAGE_TYPE", referencedColumnName = "ID")
    @ManyToOne
    private DamageTypeMaster secondDamageType;
    @Column(name = "ENHANCEMENT_BONUS")
    private Integer enhancementBonus;
    @Size(max = 4000)
    @Column(name = "DESCRIPTION")
    private String description;
    @JoinColumn(name = "RULEBOOK", referencedColumnName = "ID")
    @ManyToOne
    private RulebookMaster rulebook;
    @JoinColumn(name = "SECOND_DAMAGE_DICE_TYPE", referencedColumnName = "ID")
    @ManyToOne
    private DiceMaster secondDamageDiceType;
    @JoinColumn(name = "DAMAGE_DICE_TYPE", referencedColumnName = "ID")
    @ManyToOne
    private DiceMaster damageDiceType;
    @JoinColumn(name = "DAMAGE_TYPE", referencedColumnName = "ID")
    @ManyToOne
    private DamageTypeMaster damageType;
    @JoinColumn(name = "ARM_TYPE3", referencedColumnName = "ID")
    @ManyToOne
    private ArmType3Master armType3;
    @JoinColumn(name = "ARM_TYPE2", referencedColumnName = "ID")
    @ManyToOne
    private ArmType2Master armType2;
    @JoinColumn(name = "ARM_TYPE1", referencedColumnName = "ID")
    @ManyToOne
    private ArmType1Master armType1;
    @JoinColumn(name = "SIZE", referencedColumnName = "ID")
    @ManyToOne
    private SizeMaster size;
    @Column(name = "COMPOSITE_LONG_BOW_STRENGTH_BONUS")
    private Integer composite_long_bow_streangth_bonus;
    @Column(name = "MASTERWORK")
    private boolean masterwork;

    public boolean isMasterwork() {
        return masterwork;
    }

    public void setMasterwork(boolean masterwork) {
        this.masterwork = masterwork;
    }

    public Integer getComposite_long_bow_streangth_bonus() {
        return composite_long_bow_streangth_bonus;
    }

    public void setComposite_long_bow_streangth_bonus(Integer composite_long_bow_streangth_bonus) {
        this.composite_long_bow_streangth_bonus = composite_long_bow_streangth_bonus;
    }

    public SizeMaster getSize() {
        return size;
    }

    public void setSize(SizeMaster size) {
        this.size = size;
    }

    public ArmMaster() {
    }

    public ArmMaster(Integer id) {
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

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getNaturalReachMultiplier() {
        return naturalReachMultiplier;
    }

    public void setNaturalReachMultiplier(Integer naturalReachMultiplier) {
        this.naturalReachMultiplier = naturalReachMultiplier;
    }

    public Integer getRange() {
        return range;
    }

    public void setRange(Integer range) {
        this.range = range;
    }

    public Integer getDamageDiceNum() {
        return damageDiceNum;
    }

    public void setDamageDiceNum(Integer damageDiceNum) {
        this.damageDiceNum = damageDiceNum;
    }

    public Integer getCriticalMultiplier() {
        return criticalMultiplier;
    }

    public void setCriticalMultiplier(Integer criticalMultiplier) {
        this.criticalMultiplier = criticalMultiplier;
    }

    public Integer getThreatRange() {
        return threatRange;
    }

    public void setThreatRange(Integer threatRange) {
        this.threatRange = threatRange;
    }

    public Integer getSecondDamageDiceNum() {
        return secondDamageDiceNum;
    }

    public void setSecondDamageDiceNum(Integer secondDamageDiceNum) {
        this.secondDamageDiceNum = secondDamageDiceNum;
    }

    public Integer getSecondCriticalMultiplier() {
        return secondCriticalMultiplier;
    }

    public void setSecondCriticalMultiplier(Integer secondCriticalMultiplier) {
        this.secondCriticalMultiplier = secondCriticalMultiplier;
    }

    public Integer getSecondThreatRange() {
        return secondThreatRange;
    }

    public void setSecondThreatRange(Integer secondThreatRange) {
        this.secondThreatRange = secondThreatRange;
    }

    public DamageTypeMaster getSecondDamageType() {
        return secondDamageType;
    }

    public void setSecondDamageType(DamageTypeMaster secondDamageType) {
        this.secondDamageType = secondDamageType;
    }

    public Integer getEnhancementBonus() {
        return enhancementBonus;
    }

    public void setEnhancementBonus(Integer enhancementBonus) {
        this.enhancementBonus = enhancementBonus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RulebookMaster getRulebook() {
        return rulebook;
    }

    public void setRulebook(RulebookMaster rulebook) {
        this.rulebook = rulebook;
    }

    public DiceMaster getSecondDamageDiceType() {
        return secondDamageDiceType;
    }

    public void setSecondDamageDiceType(DiceMaster secondDamageDiceType) {
        this.secondDamageDiceType = secondDamageDiceType;
    }

    public DiceMaster getDamageDiceType() {
        return damageDiceType;
    }

    public void setDamageDiceType(DiceMaster damageDiceType) {
        this.damageDiceType = damageDiceType;
    }

    public DamageTypeMaster getDamageType() {
        return damageType;
    }

    public void setDamageType(DamageTypeMaster damageType) {
        this.damageType = damageType;
    }

    public ArmType3Master getArmType3() {
        return armType3;
    }

    public void setArmType3(ArmType3Master armType3) {
        this.armType3 = armType3;
    }

    public ArmType2Master getArmType2() {
        return armType2;
    }

    public void setArmType2(ArmType2Master armType2) {
        this.armType2 = armType2;
    }

    public ArmType1Master getArmType1() {
        return armType1;
    }

    public void setArmType1(ArmType1Master armType1) {
        this.armType1 = armType1;
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
        if (!(object instanceof ArmMaster)) {
            return false;
        }
        ArmMaster other = (ArmMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ArmMaster[ id=" + id + " ]";
    }

    @XmlTransient
    public List<CharacterArmRecord> getCharacterArmRecordList() {
        return characterArmRecordList;
    }

    public void setCharacterArmRecordList(List<CharacterArmRecord> characterArmRecordList) {
        this.characterArmRecordList = characterArmRecordList;
    }

}
