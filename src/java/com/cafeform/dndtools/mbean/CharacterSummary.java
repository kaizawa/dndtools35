package com.cafeform.dndtools.mbean;

/**
 *
 * @author kaizawa
 */
public interface CharacterSummary {

    public String  getName();    
    public String  getKlass();    
    public String  getSizeAndType();
    public String  getHitDice();
    public Integer getHitPoint();
    public Integer getInitiative();    
    public String  getSpeed();
    public String  getArmorClass();
    public String  getBaseAttackGrapple();
    public String  getAttack();    
    public String  getFullAttack();    
    public String  getSpaceAndReach();
    public String  getSpecialAttack();    
    public String  getSpecialQualities();
    public String  getSave();    
    public String  getAbilities();
    public String  getSkills();
    public String  getFeats();
    public String  getEnvironment();    
    public String  getOrganization();
    public Integer getChallengeRating();
    public String  getTreasure();
    public String  getAlignment();
    public String  getAdvancement();
    public Integer getLevelAdjustment();
}
