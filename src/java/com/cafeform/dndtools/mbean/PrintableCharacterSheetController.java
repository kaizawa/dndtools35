/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafeform.dndtools.mbean;

import com.cafeform.dndtools.entity.CharacterGrowthRecord;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class PrintableCharacterSheetController extends CharacterSheetController {

    /*
     * キャラクタレベル毎のクラスの選択
     */
    public String getSelectedClassNameByRow() {
        int index = growthTable.getRowIndex();
        CharacterGrowthRecord growth = getCharacterData().getCharacterGrowthRecordList().get(index);
        if (growth.getClassId() == null) {
            return null;
        } else {
            return growth.getClassId().getClassName();
        }

    }

    /*
     * キャラクタ記述
     */
    public String getCharacterDescription() {
        return textToHtml(getCharacterData().getDescription());
    }
    /*
     * スペル記述
     */

    public String getSpellDescription() {
        return textToHtml(getCharacterData().getSpellDescription());
    }
    /*
     * 防御記述
     */

    public String getDefenceDescription() {
        return textToHtml(getCharacterData().getDefenceDescription());
    }
    /*
     * 攻撃記述
     */

    public String getAttackDescription() {
        return textToHtml(getCharacterData().getAttackDescription());
    }
    /*
     * アイテム記述
     */

    public String getItemDescription() {
        return textToHtml(getCharacterData().getItemDescription());
    }
    /*
     * 特技記述
     */

    public String getFeatDescription() {
        return textToHtml(getCharacterData().getFeatDescription());
    }
    /*
     * クラスリスト
     */
    public String getClassList() {
        return getCharacterData().getClassList();
    }

}