/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean;

import entity.ScenarioCharacterRecord;

/**
 *
 * @author kaizawa
 */
public class ScenarioCharacterRecordFactory {

    static ScenarioCharacterRecord getInstance(CharacterSummary base) {
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

        return chara;
    }
}
