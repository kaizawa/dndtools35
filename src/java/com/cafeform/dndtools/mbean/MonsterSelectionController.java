package com.cafeform.dndtools.mbean;

import com.cafeform.dndtools.ejb.EncounterMemberFacade;
import com.cafeform.dndtools.mbean.util.PaginationHelper;
import com.cafeform.dndtools.ejb.MonsterMasterFacade;
import com.cafeform.dndtools.entity.EncounterMember;
import com.cafeform.dndtools.entity.EncounterRecord;
import com.cafeform.dndtools.entity.MonsterMaster;
import com.cafeform.dndtools.entity.ScenarioCharacterRecord;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import com.cafeform.dndtools.mbean.util.JsfUtil;
import javax.inject.Named;

@Named
@SessionScoped
public class MonsterSelectionController implements Serializable {
    
    private int numberOfMonsters = 1;

    public int getNumberOfMonsters() {
        return 1;
    }

    public void setNumberOfMonsters(int numberOfMonsters) {
        this.numberOfMonsters = numberOfMonsters;
    }

    private Integer MAX_NAME_INDEX = 100;
    @Inject
    private EncounterMemberFacade encounterMemberFacade;
    @Inject
    private EncounterRecordController encounterRecordController;
    
    public EncounterRecordController getEncounterRecordController() {
        return encounterRecordController;
    }

    public void setEncounterRecordController(EncounterRecordController encounterRecordController) {
        this.encounterRecordController = encounterRecordController;
    }
    private MonsterData current;
    private DataModel items = null;
    @Inject
    private com.cafeform.dndtools.ejb.MonsterMasterFacade monsterMasterFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public MonsterSelectionController() {
    }

    public MonsterData getSelected() {
        if (current == null) {
            current = new MonsterData(null);
            selectedItemIndex = -1;
        }
        return current;
    }

    private MonsterMasterFacade getMonsterMasterFacade() {
        return monsterMasterFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(300) {

                @Override
                public int getItemsCount() {
                    return getMonsterMasterFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    List<MonsterMaster> monsterMasterList = getMonsterMasterFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()});
                    List<MonsterData> monsterDataList = MonsterData.getMonsterDataListFromMaster(monsterMasterList);
                    return new ListDataModel(monsterDataList);
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "/monsterSelection/List";
    }

    public String prepareView() {
        current = (MonsterData) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    private void updateCurrentItem() {
        int count = getMonsterMasterFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            MonsterMaster monsterMaster = getMonsterMasterFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
            current = new com.cafeform.dndtools.mbean.MonsterData(monsterMaster);
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
        return JsfUtil.getSelectItems(monsterMasterFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(monsterMasterFacade.findAll(), true);
    }

    @FacesConverter(forClass = MonsterData.class)
    public static class MonsterMasterControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MonsterSelectionController controller = (MonsterSelectionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "monsterMasterController");
            return controller.monsterMasterFacade.find(getKey(value));
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
            if (object instanceof MonsterData) {
                MonsterData o = (MonsterData) object;
                return o.getName();
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + MonsterSelectionController.class.getName());
            }
        }
    }
    
    /*
     * EncounterMember のメンバーとして選択/解除する
     */
    public String add() {
        for(int i = 0 ; i < numberOfMonsters ; i ++){
            addOne();
        }            
        return "/encounterRecord/Battle";                    
    }    

    /*
     * EncounterMember のメンバーとして選択/解除する
     */
    public void addOne() {
        current = (MonsterData) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();

        EncounterRecord encounter = encounterRecordController.getCurrent();
        ScenarioCharacterRecord chara = ScenarioCharacterRecordFactory.getInstance(current);
        /*
         * ユニークな名前をつける
         */
        int i;
        List<EncounterMember> members = encounterMemberFacade.findByEncounterRecord(encounter);
        for (i = 1; i <= MAX_NAME_INDEX; i++) {
            Boolean found = false;
            for (EncounterMember member : members) {
                if (member.getScenarioCharacterRecord().getName().equals(chara.getName() + i)) {
                    found = true;
                }
            }
            if (!found) {
                chara.setName(chara.getName() + i);
                break;
            }
        }
        if (i > MAX_NAME_INDEX) {
            JsfUtil.addErrorMessage("これ以上同じモンスターを追加できません.");
            return;
        }

        JsfUtil.addSuccessMessage(chara.getName() + "が追加されました。");

        try {
            EncounterMember member = new EncounterMember();
            member.setEncounterRecord(encounter);
            member.setScenarioCharacterRecord(chara);
            member.setInitiative(0);
            member.setMyTurn(false);
            encounterMemberFacade.edit(member);
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e + "Persistence Error Occured");
        }
        return;
    }

    public String cancel() {
        return "/encounterRecord/Battle";
    }
}
