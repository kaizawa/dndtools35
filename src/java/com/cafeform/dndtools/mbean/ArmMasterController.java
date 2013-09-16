package com.cafeform.dndtools.mbean;

import com.cafeform.dndtools.entity.ArmMaster;
import com.cafeform.dndtools.mbean.util.JsfUtil;
import com.cafeform.dndtools.mbean.util.PaginationHelper;
import com.cafeform.dndtools.ejb.ArmMasterFacade;
import com.cafeform.dndtools.ejb.ArmType1MasterFacade;
import com.cafeform.dndtools.ejb.ArmType2MasterFacade;
import com.cafeform.dndtools.ejb.ArmType3MasterFacade;
import com.cafeform.dndtools.ejb.CharacterArmRecordFacade;
import com.cafeform.dndtools.ejb.CharacterRecordFacade;
import com.cafeform.dndtools.ejb.DamageTypeMasterFacade;
import com.cafeform.dndtools.entity.ArmType1Master;
import com.cafeform.dndtools.entity.ArmType2Master;
import com.cafeform.dndtools.entity.ArmType3Master;
import com.cafeform.dndtools.entity.CharacterArmRecord;
import com.cafeform.dndtools.entity.CharacterRecord;
import com.cafeform.dndtools.entity.DamageTypeMaster;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class ArmMasterController implements Serializable {

    @Inject
    protected ArmType1MasterFacade armType1MasterFacade;
    @Inject
    protected ArmType2MasterFacade armType2MasterFacade;
    @Inject
    protected ArmType3MasterFacade armType3MasterFacade;
    @Inject
    protected DamageTypeMasterFacade damageTypeMasterFacade;
    @Inject
    protected ArmMasterFacade armMasterFacade;
    @Inject
    protected SessionController sessionController;
    @Inject
    protected CharacterRecordFacade characterRecordFacade;
    @Inject
    protected CharacterArmRecordFacade characterArmRecordFacade;
    private ArmMaster currentArmMaster;
    private DataModel items = null;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private SelectItem[] armType1MasterOptions;
    private SelectItem[] armType2MasterOptions;
    private SelectItem[] armType3MasterOptions;
    private SelectItem[] damageTypeMasterOptions;
    private List<ArmMaster> filteredArms;
    private List<ArmMaster> allArms;
    private ArmMaster selectedArm;

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
        if (currentArmMaster == null) {
            currentArmMaster = new ArmMaster();
            selectedItemIndex = -1;
        }
        return currentArmMaster;
    }

    private ArmMasterFacade getFacade() {
        return armMasterFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(100) {
                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(),
                        getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        currentArmMaster = (ArmMaster) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        currentArmMaster = new ArmMaster();
        selectedItemIndex = -1;
        return "Edit";
    }

    public String prepareEdit() {
        currentArmMaster = (ArmMaster) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String prepareCopy() {
        currentArmMaster = copy((ArmMaster) getItems().getRowData());
        currentArmMaster.setName(currentArmMaster.getName() + "のコピー");
        selectedItemIndex = -1;
        return "Edit";
    }

    public String createEnhanced(int level) {
        currentArmMaster = copy((ArmMaster) getItems().getRowData());
        currentArmMaster.setName(currentArmMaster.getName() + "+" + level);
        currentArmMaster.setEnhancementBonus(level);
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
        currentArmMaster.setDescription(null == currentArmMaster.getDescription() ? "高品質"
            : currentArmMaster.getDescription() + "\n高品質");
        currentArmMaster.setPrice(currentArmMaster.getPrice() + additionalPrice + 300);
        selectedItemIndex = -1;
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(currentArmMaster);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ArmMasterUpdated"));
            return prepareList();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        currentArmMaster = (ArmMaster) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyCurrent() {
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(currentArmMaster);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ArmMasterDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            currentArmMaster = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
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
        return (currentArmMaster == null || currentArmMaster.getId() == null);
    }

    @PostConstruct
    public void init() {
        allArms = armMasterFacade.findAll();
        armType1MasterOptions = createFilterOptions(armType1MasterFacade.findAll().toArray(new ArmType1Master[0]));
        armType2MasterOptions = createFilterOptions(armType2MasterFacade.findAll().toArray(new ArmType2Master[0]));
        armType3MasterOptions = createFilterOptions(armType3MasterFacade.findAll().toArray(new ArmType3Master[0]));
        damageTypeMasterOptions = createFilterOptions(damageTypeMasterFacade.findAll().toArray(new DamageTypeMaster[0]));
    }

    public SelectItem[] getDamageTypeMasterOptions() {
        return damageTypeMasterOptions;
    }

    public String saveButton_action() {
        doSave();
        return null;
    }

    public void doSave() {
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

    private SelectItem[] createFilterOptions(Object[] data) {
        SelectItem[] options = new SelectItem[data.length + 1];

        options[0] = new SelectItem("", "選択");
        for (int i = 0; i < data.length; i++) {
            options[i + 1] = new SelectItem(data[i], data[i].toString());
        }

        return options;
    }

    public SelectItem[] getArmType1MasterOptions() {
        return armType1MasterOptions;
    }

    public SelectItem[] getArmType2MasterOptions() {
        return armType2MasterOptions;
    }

    public SelectItem[] getArmType3MasterOptions() {
        return armType3MasterOptions;
    }

    public String addArm() {
        doSave();
        return returnCharacterRecordPageButton_action();
    }
}
