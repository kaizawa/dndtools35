/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafeform.dndtools.mbean;

import com.cafeform.dndtools.entity.AbilityMaster;
import com.cafeform.dndtools.entity.CharacterArmRecord;
import com.cafeform.dndtools.entity.SaveMaster;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.convert.NumberConverter;
import javax.inject.Named;

@Named
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

        return "<table><tr>"
             + "<td>聞き耳:</td><td>" + characterData.getSkillTotalCheckModifierById(7) + "</td><td>__</td></tr>"
             + "<td>視認:</td><td>" + characterData.getSkillTotalCheckModifierById(14) + "</td><td>__</td></tr>"
             + "<td>交渉:</td><td>" + characterData.getSkillTotalCheckModifierById(13) + "</td><td>__</td></tr>"
             + "<td>隠れ身:</td><td>" + characterData.getSkillTotalCheckModifierById(4) + "</td><td>__</td></tr>"
             + "<td>忍び足:</td><td>" + characterData.getSkillTotalCheckModifierById(15) + "</td><td>__</td></tr>"
             + "<td>真意看破:</td><td>" + characterData.getSkillTotalCheckModifierById(21) + "</td><td>__</td></tr>"
             + "<td>捜索:</td><td>" + characterData.getSkillTotalCheckModifierById(28) + "</td><td>__</td></tr>"
             + "</table>";
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
    
    public String getAttackDescription() {
        CharacterData characterData =  (CharacterData)characterSummaryTable.getRowData();
        //return textToHtml(characterData.getAttackDescription());
        List<CharacterArmRecord> armRecordList = characterData.getCharacterRecord().getCharacterArmRecordList();
        StringBuilder armListStr = new StringBuilder();
        
        for (CharacterArmRecord armRecord : armRecordList) 
        {
            armListStr.append("▼").append(armRecord.getArmId().getName()).append("\n");
            armListStr.append(characterData.getAttackModifiers(armRecord)).append("\n");
            if (null != armRecord.getDescription()){
                armListStr.append(armRecord.getDescription()).append("\n");
            }
        }
        return textToHtml(armListStr.toString());
    }
}
