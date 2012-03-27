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
        int index = characterSummaryTable.getRowIndex();
        CharacterData characterData =  getCharacterDataList().get(index);
        
        //管理Beanへ反映
        setCharacterData(characterData);
        return "EditCharacterDataPage";
    }

    @Override
    public Integer getSaveTotal() {
        int index = characterSummaryTable.getRowIndex();
        CharacterData characterData =  getCharacterDataList().get(index);
        SaveMaster save = (SaveMaster) getSaveTable().getRowData();
        return characterData.getSaveTotalById(save.getId());
    }

    @Override
    public Integer getAbilityModifier() {
        int index = characterSummaryTable.getRowIndex();
        CharacterData characterData =  getCharacterDataList().get(index);
        AbilityMaster ability = (AbilityMaster) abilityTable.getRowData();
        return characterData.getAbilityModifierById(ability.getId());
    }

    @Override
    public Integer getAbilityTotal() {
        int index = characterSummaryTable.getRowIndex();        
        CharacterData characterData =  getCharacterDataList().get(index);       
        AbilityMaster ability = (AbilityMaster) abilityTable.getRowData();
        return characterData.getAbilityTotalById(ability.getId());
    }

    public String getCommonSkills() {
        int index = characterSummaryTable.getRowIndex();
        
        CharacterData characterData =  getCharacterDataList().get(index);        

        return "隠れ身: " + characterData.getSkillTotalCheckModifierById(4) + "<br>"
                + "聞き耳: " + characterData.getSkillTotalCheckModifierById(7) + "<br>"
                + "交渉: " + characterData.getSkillTotalCheckModifierById(13) + "<br>"
                + "視認: " + characterData.getSkillTotalCheckModifierById(14) + "<br>"
                + "忍び足: " + characterData.getSkillTotalCheckModifierById(15) + "<br>"
                + "真意看破: " + characterData.getSkillTotalCheckModifierById(21) + "<br>"
                + "捜索: " + characterData.getSkillTotalCheckModifierById(28);
    }
    
    public String getAttackDescriptionWithBR(){
        int index = characterSummaryTable.getRowIndex();        
        CharacterData characterData =  getCharacterDataList().get(index);
        
        return characterData.getAttackDescriptionWithBR();
    }
    

    public Integer getHitPoint(){
        int index = characterSummaryTable.getRowIndex();
        
        CharacterData characterData =  getCharacterDataList().get(index);
        
        return characterData.getHitPoint();
    } 
    
    public Integer getInitiative(){
        int index = characterSummaryTable.getRowIndex();
        
        CharacterData characterData =  getCharacterDataList().get(index);
        
        return characterData.getInitiative();
    }   
    
    public Integer getAcTouch(){
        int index = characterSummaryTable.getRowIndex();
        
        CharacterData characterData =  getCharacterDataList().get(index);
        
        return characterData.getAcTouch();
    } 
    
    public Integer getAcNormal(){
        int index = characterSummaryTable.getRowIndex();
        
        CharacterData characterData =  getCharacterDataList().get(index);
        
        return characterData.getAcNormal();
    }  
    
    public Integer getAcFlatFooted(){
        int index = characterSummaryTable.getRowIndex();
        
        CharacterData characterData =  getCharacterDataList().get(index);
        
        return characterData.getAcFlatFooted();
    }        
    
    public String getAbilityShortName(){
        AbilityMaster ability = (AbilityMaster) abilityTable.getRowData();

        return getAbilityShortName(ability);        
    }
    
    public Integer getCharacterLevel(){
        int index = characterSummaryTable.getRowIndex();
        
        CharacterData characterData =  getCharacterDataList().get(index);
        return characterData.getCharacterLevel();
    }
    
      public String getClassList(){
        int index = characterSummaryTable.getRowIndex();
        
        CharacterData characterData =  getCharacterDataList().get(index);
        return characterData.getClassList();
    }  
      
          /*
     * キャラクターデータのリスト
     */
    public List<CharacterData> getCheckedCharacterList() {

        List<CharacterData> charaDataList = new ArrayList<CharacterData>();
        
        for(Object obj: getSessionController().getCheckedCharacterSet()){
            CharacterData charaData = (CharacterData)obj;
            charaDataList.add(charaData);
        }
        return charaDataList;
    }
}
