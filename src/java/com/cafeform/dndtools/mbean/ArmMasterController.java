package com.cafeform.dndtools.mbean;

import com.cafeform.dndtools.entity.ArmMaster;
import com.cafeform.dndtools.mbean.util.JsfUtil;
import com.cafeform.dndtools.ejb.ArmMasterFacade;
import com.cafeform.dndtools.ejb.ArmType1MasterFacade;
import com.cafeform.dndtools.ejb.ArmType2MasterFacade;
import com.cafeform.dndtools.ejb.ArmType3MasterFacade;
import com.cafeform.dndtools.ejb.CharacterArmRecordFacade;
import com.cafeform.dndtools.ejb.CharacterRecordFacade;
import com.cafeform.dndtools.ejb.DamageTypeMasterFacade;
import com.cafeform.dndtools.ejb.SizeMasterFacade;
import com.cafeform.dndtools.entity.ArmType1Master;
import com.cafeform.dndtools.entity.ArmType2Master;
import com.cafeform.dndtools.entity.ArmType3Master;
import com.cafeform.dndtools.entity.CharacterArmRecord;
import com.cafeform.dndtools.entity.CharacterRecord;
import com.cafeform.dndtools.entity.DamageTypeMaster;
import com.cafeform.dndtools.entity.SizeMaster;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class ArmMasterController implements Serializable {

    @EJB protected ArmType1MasterFacade armType1MasterFacade;
    @EJB protected ArmType2MasterFacade armType2MasterFacade;
    @EJB protected ArmType3MasterFacade armType3MasterFacade;    
    @EJB protected DamageTypeMasterFacade damageTypeMasterFacade;
    @EJB protected ArmMasterFacade armMasterFacade;
    @EJB protected CharacterRecordFacade characterRecordFacade;
    @EJB protected CharacterArmRecordFacade characterArmRecordFacade;
    @EJB protected SizeMasterFacade sizeMasterFacade;    
    @Inject protected ApplicationController applicationController;    
    @Inject protected SessionController sessionController;
    
  
    private List<ArmMaster> filteredArms;
    private ArmMaster selectedArm;

    public ApplicationController getApplicationController() {
        return applicationController;
    }

    public void setApplicationController(ApplicationController applicationController) {
        this.applicationController = applicationController;
    }
    
    public ArmMaster getSelectedArm() {
        return selectedArm;
    }

    public void setSelectedArm(ArmMaster selectedArm) {
        this.selectedArm = selectedArm;
    }

    public ArmMasterController() {
    }

    public SessionController getSessionController() {
        return sessionController;
    }

    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }

    public ArmMaster getSelected() {
        if (selectedArm == null) {
            selectedArm = new ArmMaster();
        }
        return selectedArm;
    }

    private ArmMasterFacade getArmMasterFacade() {
        return armMasterFacade;
    }

    public String prepareList() {
        selectedArm = null;
        return "List";
    }

    public String prepareCreate() {
        selectedArm = new ArmMaster();
        return "Edit";
    }

    public String prepareEdit() {
        return "Edit";
    }

    public String prepareCopy() {
        selectedArm = copy(selectedArm);
        selectedArm.setName(selectedArm.getName() + "のコピー");
        return "Edit";
    }

    public String createEnhanced(int level) {
        selectedArm = copy(selectedArm);
        selectedArm.setName(selectedArm.getName() + "+" + level);
        selectedArm.setEnhancementBonus(level);
        int additionalPrice = 0;
        switch (level) {
            case 1:
                additionalPrice = 2000;
                break;
            case 2:
                additionalPrice = 8000;
                break;
            case 3:
                additionalPrice = 18000;
                break;
            case 4:
                additionalPrice = 32000;                
                break;
            case 5:
                additionalPrice = 50000;
        }
        selectedArm.setMasterwork(true);
        selectedArm.setPrice(selectedArm.getPrice() + additionalPrice + 300);

        return "Edit";
    }
    
    private void resetArmList()
    {
        applicationController.resetArmList();
        filteredArms = null;
    }

    public String update() {
        try {
            if (null == selectedArm.getId()) 
            {
                getArmMasterFacade().create(selectedArm);
                JsfUtil.addSuccessMessage("追加されました。");                
            }
            else 
            {
                getArmMasterFacade().edit(selectedArm);
                JsfUtil.addSuccessMessage("更新されました。");
            }
            resetArmList();
            return prepareList();
        } catch (Exception e) {
            resetArmList();            
            JsfUtil.addErrorMessage("更新に失敗しました");
            return null;
        }
    }

    public String destroy() {
        try {
            getArmMasterFacade().remove(selectedArm);
            JsfUtil.addSuccessMessage("削除されました");            
            resetArmList();                                        
            return prepareList();                    
        } catch (Exception e) {
            JsfUtil.addErrorMessage("削除できませんでした。まだ使われている可能性があります。");
            return null;
        }
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(armMasterFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(armMasterFacade.findAll(), true);
    }

    private ArmMaster copy(ArmMaster original) {
        ArmMaster copied = new ArmMaster();
        copied.setName(original.getName());
        copied.setWeight(original.getWeight());
        copied.setPrice(original.getPrice());
        copied.setPage(original.getPage());
        copied.setNaturalReachMultiplier(original.getNaturalReachMultiplier());
        copied.setRange(original.getRange());
        copied.setDamageDiceNum(original.getDamageDiceNum());
        copied.setCriticalMultiplier(original.getCriticalMultiplier());
        copied.setThreatRange(original.getThreatRange());
        copied.setSecondDamageDiceNum(original.getSecondDamageDiceNum());
        copied.setSecondCriticalMultiplier(original.getSecondCriticalMultiplier());
        copied.setSecondThreatRange(original.getSecondThreatRange());
        copied.setSecondDamageType(original.getSecondDamageType());
        copied.setEnhancementBonus(original.getEnhancementBonus());
        copied.setDescription(original.getDescription());
        copied.setRulebook(original.getRulebook());
        copied.setSecondDamageDiceType(original.getSecondDamageDiceType());
        copied.setDamageDiceType(original.getDamageDiceType());
        copied.setDamageType(original.getDamageType());
        copied.setArmType3(original.getArmType3());
        copied.setArmType2(original.getArmType2());
        copied.setArmType1(original.getArmType1());
        copied.setMasterwork(original.isMasterwork());
        copied.setSize(original.getSize());
        copied.setComposite_long_bow_streangth_bonus(original.getComposite_long_bow_streangth_bonus());
        return copied;
    }

    @FacesConverter(forClass = ArmMaster.class)
    public static class ArmMasterControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ArmMasterController controller = (ArmMasterController) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, "armMasterController");
            return controller.armMasterFacade.find(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof ArmMaster) {
                ArmMaster o = (ArmMaster) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type "
                    + object.getClass().getName() + "; expected type: " + ArmMaster.class.getName());
            }
        }
    }

    /*
     * 削除ボタンの表示
     */
    public boolean isDeleteButtonDisabled() {
        return (selectedArm == null || selectedArm.getId() == null);
    }

    public String addCharacterArmRecord() {
        CharacterRecord characterRecord = getSessionController().getCharacterData().getCharacterRecord();
        List<CharacterArmRecord> armRecordList = characterRecord.getCharacterArmRecordList();
        //更新時間を記録
        Date date = new Date();
        characterRecord.setSaveTime(date);

        CharacterArmRecord newArmRecord = new CharacterArmRecord();
        newArmRecord.setCharacterId(characterRecord);
        newArmRecord.setArmId(selectedArm);

        try {
            //characterRecordFacade.edit(characterRecord);
            characterArmRecordFacade.create(newArmRecord);
            armRecordList.add(newArmRecord);
            JsfUtil.addSuccessMessage("追加されました");
        } catch (Exception ex) {
            JsfUtil.addSuccessMessage("武器の追加にに失敗しました");
        }
        return "EditCharacterRecordPage";
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
}
