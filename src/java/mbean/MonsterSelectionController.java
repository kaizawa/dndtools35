package mbean;

import ejb.EncounterMemberFacade;
import mbean.util.PaginationHelper;
import ejb.MonsterMasterFacade;
import entity.EncounterMember;
import entity.EncounterRecord;
import entity.MonsterMaster;
import entity.ScenarioCharacterRecord;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import mbean.util.JsfUtil;

@ManagedBean(name = "monsterDataController")
@SessionScoped
public class MonsterSelectionController implements Serializable {

    @EJB
    private EncounterMemberFacade encounterMemberFacade;
    @ManagedProperty(value = "#{encounterRecordController}")
    private EncounterRecordController encounterRecordController;
    private MonsterData current;
    private DataModel items = null;
    @EJB
    private ejb.MonsterMasterFacade ejbFacade;
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

    private MonsterMasterFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    List<MonsterMaster> monsterMasterList = getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()});
                    List<MonsterData> monsterDataList = MonsterData.getMonsterDataListFromMaster(monsterMasterList);
                    return new ListDataModel(monsterDataList);
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
        current = (MonsterData) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
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
            MonsterMaster monsterMaster = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
            current = new mbean.MonsterData(monsterMaster);
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
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    @FacesConverter(forClass = MonsterData.class)
    public static class MonsterMasterControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MonsterSelectionController controller = (MonsterSelectionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "monsterMasterController");
            return controller.ejbFacade.find(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuffer sb = new StringBuffer();
            sb.append(value);
            return sb.toString();
        }

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

    public boolean isSelected() {
        return false;
    }

    /*
     * EncounterMember のメンバーとして選択/解除する
     */
    public void setSelected(boolean charaSelected) {
        /*
        EncounterRecord encounter = encounterRecordController.getCurrent();
        MonsterData monster = (MonsterData) getItems().getRowData();
        ScenarioCharacterRecord chara = ScenarioCharacterRecordFactory.getInstance(monster);

        try {
            if (charaSelected) {
                    EncounterMember member = new EncounterMember();
                    member.setEncounterRecord(encounter);
                    member.setScenarioCharacterRecord(encounterCharacter);
                    encounterMemberFacade.edit(member);
            } else {
                List<EncounterMember> memberList = encounterMemberFacade.findByEncounterMonsterRecordAndEncounterRecord(encounterCharacter, current);
                for (EncounterMember member : memberList) {
                    encounterMemberFacade.remove(member);
                }
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e + "Persistence Error Occured");
        }
        */
    }
}
