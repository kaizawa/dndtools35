/*
 * EditCharacterPerLevelPage.java
 *
 * Created on 2009/01/10, 13:26:57
 */
package com.cafeform.dndtools.mbean;

import com.cafeform.dndtools.ejb.ArmMasterFacade;
import com.cafeform.dndtools.ejb.ArmType1MasterFacade;
import com.cafeform.dndtools.entity.ArmMaster;
import com.cafeform.dndtools.entity.ArmType1Master;
import java.util.Date;
import javax.enterprise.context.SessionScoped;
import com.cafeform.dndtools.mbean.util.JsfUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class CharacterSheetArmController implements Serializable { 
        
    private SelectItem[] armType1MasterOptions = new SelectItem[0];
    private List<ArmMaster> filteredArms;
    private List<ArmMaster> allArms;    
    
    @Inject protected ArmType1MasterFacade armType1MasterFacade;
    @Inject protected ArmType1MasterFacade armType2MasterFacade;    
    @Inject protected ArmType1MasterFacade armType3MasterFacade; 
    @Inject protected ArmMasterFacade armMasterFacade;

    public ArmType1MasterFacade getArmType1MasterFacade() {
        return armType1MasterFacade;
    }

    public void setArmType1MasterFacade(ArmType1MasterFacade armType1MasterFacade) {
        this.armType1MasterFacade = armType1MasterFacade;
    }

    public ArmType1MasterFacade getArmType2MasterFacade() {
        return armType2MasterFacade;
    }

    public void setArmType2MasterFacade(ArmType1MasterFacade armType2MasterFacade) {
        this.armType2MasterFacade = armType2MasterFacade;
    }

    public ArmType1MasterFacade getArmType3MasterFacade() {
        return armType3MasterFacade;
    }

    public void setArmType3MasterFacade(ArmType1MasterFacade armType3MasterFacade) {
        this.armType3MasterFacade = armType3MasterFacade;
    }

    public ArmMasterFacade getArmMasterFacade() {
        return armMasterFacade;
    }

    public void setArmMasterFacade(ArmMasterFacade armMasterFacade) {
        this.armMasterFacade = armMasterFacade;
    }
    
    @PostConstruct
    public void init() {
        allArms = armMasterFacade.findAll();
        armType1MasterOptions = createFilterOptions(armType1MasterFacade.findAll().toArray(new ArmType1Master[0]));
    }
    
    public String saveButton_action() {
        doSave();
        return null;
    }

    public void doSave() {
        // 全レベルのスキルレコードと、成長レコードも保存する
        //List<CharacterSkillGrowthRecord> skillGrowthList = getCharacterData().getCharacterSkillGrowthRecordList();
        //List<CharacterGrowthRecord> growthList = getCharacterData().getCharacterGrowthRecordList();
        // 更新時刻記録用にキャラクターレコードも保存する。
        // ここで保存するのは、本当は好ましくは無いが。

        //更新時間を記録
        Date date = new Date();
        //getCharacterData().setSaveTime(date);
        
        try {
            /*
            characterRecordFacade.edit(getCharacterData().getCharacterRecord());

            for (CharacterSkillGrowthRecord skillGrowth : skillGrowthList) {
                characterSkillGrowthRecordFacade.edit(skillGrowth);
            }
            for (CharacterGrowthRecord growth : growthList) {
                characterGrowthRecordFacade.edit(growth);
            }
            */ 
            JsfUtil.addSuccessMessage("保存されました");

        } catch (Exception ex) {
            JsfUtil.addSuccessMessage("成長記録の保存に失敗しました");
        }
    }

    public String returnCharacterRecordPageButton_action() {
        return "EditCharacterRecordPage";
    }

    public List<ArmMaster> getFilteredArms() {
        return filteredArms;
    }

    public void setFilteredArms(List<ArmMaster> filteredArms) {
        this.filteredArms = filteredArms;
    }

    public List<ArmMaster> getAllArms() {
        return allArms;
    }

    private SelectItem[] createFilterOptions(ArmType1Master[] data) {
        SelectItem[] options = new SelectItem[data.length + 1];

        options[0] = new SelectItem("", "Select");
        for (int i = 0; i < data.length; i++) {
            options[i + 1] = new SelectItem(data[i], data[i].getName());
        }

        return options;
    }

    public SelectItem[] getArmType1MasterOptions() {
        return armType1MasterOptions;
    }
}

