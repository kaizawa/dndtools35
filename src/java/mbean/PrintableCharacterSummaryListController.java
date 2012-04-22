/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean;

import entity.AbilityMaster;
import entity.CharacterRecord;
import entity.SaveMaster;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.convert.NumberConverter;

@ManagedBean
@RequestScoped
public class PrintableCharacterSummaryListController extends CharacterSheetController {
    private HtmlDataTable characterSummaryTable = new HtmlDataTable();

    public HtmlDataTable getCharacterSummaryTable() {
        return characterSummaryTable;
    }

    public void setCharacterSummaryTable(HtmlDataTable hdt) {
        this.characterSummaryTable = hdt;
    }
    
    private NumberConverter numberConverter1 = new NumberConverter();

    public NumberConverter getNumberConverter1() {
        return numberConverter1;
    }

    public void setNumberConverter1(NumberConverter nc) {
        this.numberConverter1 = nc;
    }

    protected void _init() throws Exception {
        numberConverter1.setMinIntegerDigits(1);
        numberConverter1.setMaxIntegerDigits(40);
        numberConverter1.setMaxFractionDigits(3);
    }

    public String charalist_action() {
        return "CharacterListPage";
    }

    public String editCharaLink_action() {
        CharacterData characterData =  (CharacterData)characterSummaryTable.getRowData();
        
        //管理Beanへ反映
        setCharacterData(characterData);
        return "EditCharacterDataPage";
    }

    @Override
    public Integer getSaveTotal() {
        CharacterData characterData =  (CharacterData)characterSummaryTable.getRowData();
        SaveMaster save = (SaveMaster) getSaveTable().getRowData();
        return characterData.getSaveTotalById(save.getId());
    }

    @Override
    public Integer getAbilityModifier() {
        CharacterData characterData =  (CharacterData)characterSummaryTable.getRowData();

        AbilityMaster ability = (AbilityMaster) abilityTable.getRowData();
        return characterData.getAbilityModifierById(ability.getId());
    }

    @Override
    public Integer getAbilityTotal() {
        CharacterData characterData =  (CharacterData)characterSummaryTable.getRowData();
        AbilityMaster ability = (AbilityMaster) abilityTable.getRowData();
        return characterData.getAbilityTotalById(ability.getId());
    }

    public String getCommonSkills() {
        CharacterData characterData =  (CharacterData)characterSummaryTable.getRowData();     

        return "聞き耳: " + characterData.getSkillTotalCheckModifierById(7) + "<br>"
             + "視認: " + characterData.getSkillTotalCheckModifierById(14) + "<br>"                
             + "交渉: " + characterData.getSkillTotalCheckModifierById(13) + "<br>"
             + "隠れ身: " + characterData.getSkillTotalCheckModifierById(4) + "<br>"                
             + "忍び足: " + characterData.getSkillTotalCheckModifierById(15) + "<br>"
             + "真意看破: " + characterData.getSkillTotalCheckModifierById(21) + "<br>"
             + "捜索: " + characterData.getSkillTotalCheckModifierById(28);
    }
    
    public String getAttackDescriptionWithBR(){
        CharacterData characterData =  (CharacterData)characterSummaryTable.getRowData();
        
        return characterData.getAttackDescriptionWithBR();
    }
    

    public Integer getHitPoint(){
        CharacterData characterData =  (CharacterData)characterSummaryTable.getRowData();
        
        return characterData.getHitPoint();
    } 
    
    public Integer getInitiative(){
        CharacterData characterData =  (CharacterData)characterSummaryTable.getRowData();
      
        return characterData.getInitiative();
    }   
    
    public Integer getAcTouch(){
        CharacterData characterData =  (CharacterData)characterSummaryTable.getRowData();
        
        return characterData.getAcTouch();
    } 
    
    public Integer getAcNormal(){
        CharacterData characterData =  (CharacterData)characterSummaryTable.getRowData();
        
        return characterData.getAcNormal();
    }  
    
    public Integer getAcFlatFooted(){
        CharacterData characterData =  (CharacterData)characterSummaryTable.getRowData();
        
        return characterData.getAcFlatFooted();
    }        
    
    public String getAbilityShortName(){
        AbilityMaster ability = (AbilityMaster) abilityTable.getRowData();

        return getAbilityShortName(ability);        
    }
    
    public Integer getCharacterLevel(){
        CharacterData characterData =  (CharacterData)characterSummaryTable.getRowData();
        return characterData.getCharacterLevel();
    }
    
      public String getClassList(){
        CharacterData characterData =  (CharacterData)characterSummaryTable.getRowData();
        return characterData.getClassList();
    }  
      
    /*
     * キャラクターデータのリスト
     */
    public List<CharacterData> getCheckedCharacterList() {

        List<CharacterData> charaDataList = new ArrayList<CharacterData>();
        
        for(Object obj: getSessionController().getCheckedCharacterMap().values()){
            CharacterData charaData = (CharacterData)obj;
            charaDataList.add(charaData);
        }
        return charaDataList;
    }
}
