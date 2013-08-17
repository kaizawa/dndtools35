/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafeform.dndtools.mbean;

import com.cafeform.dndtools.entity.ScenarioCharacterRecord;

/**
 *
 * @author kaizawa
 */
public class ScenarioCharacterRecordFactory {

    public static ScenarioCharacterRecord getInstance(CharacterSummary base) {
        ScenarioCharacterRecord chara = new ScenarioCharacterRecord();

        chara.setName(base.getName());
        chara.setKlass(base.getKlass());
        chara.setSizeAndType(base.getSizeAndType());
        chara.setHitDice(base.getHitDice());
        chara.setHitPoint(base.getHitPoint());
        chara.setInitiative(base.getInitiative());
        chara.setSpeed(base.getSpeed());
        chara.setArmorClass(base.getArmorClass());
        chara.setBaseAttackGrapple(base.getBaseAttackGrapple());
        chara.setAttack(base.getAttack());
        chara.setFullAttack(base.getFullAttack());
        chara.setSpaceAndReach(base.getSpaceAndReach());
        chara.setSpecialAttacks(base.getSpecialAttack());
        chara.setSpecialQualities(base.getSpecialQualities());
        chara.setSave(base.getSave());
        chara.setAbilities(base.getAbilities());
        chara.setSkills(base.getSkills());
        chara.setFeats(base.getFeats());
        chara.setEnvironment(base.getEnvironment());
        chara.setOrganization(base.getOrganization());
        chara.setChallengeRating(base.getChallengeRating());
        chara.setTreasure(base.getTreasure());
        chara.setAlignment(base.getAlignment());
        chara.setAdvancement(base.getAdvancement());
        chara.setLevelAdjustment(base.getLevelAdjustment());
        chara.setIsPlayerCharacter(base.getClass().equals(CharacterData.class) ? (short) 1 : 0);

        return chara;
    }

    public static void copy(ScenarioCharacterRecord from, ScenarioCharacterRecord to) {
        to.setName(from.getName());
        to.setKlass(from.getKlass());
        to.setSizeAndType(from.getSizeAndType());
        to.setHitDice(from.getHitDice());
        to.setHitPoint(from.getHitPoint());
        to.setInitiative(from.getInitiative());
        to.setBaseAttackGrapple(from.getBaseAttackGrapple());
        to.setAttack(from.getAttack());
        to.setFullAttack(from.getFullAttack());
        to.setSpaceAndReach(from.getSpaceAndReach());
        to.setSpecialAttacks(from.getSpecialAttacks());
        to.setSpecialQualities(from.getSpecialQualities());
        to.setSave(from.getSave());
        to.setAbilities(from.getAbilities());
        to.setSkills(from.getSkills());
        to.setFeats(from.getFeats());
        to.setEnvironment(from.getEnvironment());
        to.setOrganization(from.getOrganization());
        to.setChallengeRating(from.getChallengeRating());
        to.setTreasure(from.getTreasure());
        to.setAlignment(from.getAlignment());
        to.setAdvancement(from.getAdvancement());
        to.setLevelAdjustment(from.getLevelAdjustment());
        to.setIsPlayerCharacter(from.getIsPlayerCharacter());
        to.setComments(from.getComments());
        to.setArmorClass(from.getArmorClass());
        to.setSpeed(from.getSpeed());
        to.setScenario(from.getScenario());
        to.setEncounterMemberList(from.getEncounterMemberList());
    }
}
