/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean;

import entity.CharacterGrowthRecord;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @version PrintableCharacterRecordPage.java
 * @version Created on 2009/02/20, 23:11:21
 * @author ka78231
 */
@ManagedBean
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
    protected String classList;

    public void setClassList(String classList){
        this.classList = classList;
    }

    public String getClassList() {
        return classList;
    }

}
