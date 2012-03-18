package mbean;

import ejb.*;
import entity.MonsterMaster;
import mbean.util.JsfUtil;
import mbean.util.PaginationHelper;
import entity.*;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@ManagedBean (name="monsterMasterController")
@SessionScoped
public class MonsterMasterController implements Serializable {
    @EJB
    private MonsterSaveRecordFacade monsterSaveRecordFacade;
    @EJB
    private SaveMasterFacade saveMasterFacade;
    @EJB
    private MonsterAbilityRecordFacade monsterAbilityRecordFacade;
    @EJB
    private AbilityMasterFacade abilityMasterFacade;

    public AbilityMasterFacade getAbilityMasterFacade() {
        return abilityMasterFacade;
    }

    public void setAbilityMasterFacade(AbilityMasterFacade abilityMasterFacade) {
        this.abilityMasterFacade = abilityMasterFacade;
    }


    public MonsterAbilityRecordFacade getMonsterAbilityRecordFacade() {
        return monsterAbilityRecordFacade;
    }

    public void setMonsterAbilityRecordFacade(MonsterAbilityRecordFacade monsterAbilityRecordFacade) {
        this.monsterAbilityRecordFacade = monsterAbilityRecordFacade;
    }

    public MonsterSaveRecordFacade getMonsterSaveRecordFacade() {
        return monsterSaveRecordFacade;
    }

    public void setMonsterSaveRecordFacade(MonsterSaveRecordFacade monsterSaveRecordFacade) {
        this.monsterSaveRecordFacade = monsterSaveRecordFacade;
    }

    public SaveMasterFacade getSaveMasterFacade() {
        return saveMasterFacade;
    }

    public void setSaveMasterFacade(SaveMasterFacade saveMasterFacade) {
        this.saveMasterFacade = saveMasterFacade;
    }

    private MonsterMaster current;
    private DataModel items = null;
    @EJB private ejb.MonsterMasterFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public MonsterMasterController() {
    }

    public MonsterMaster getSelected() {
        if (current == null) {
            current = new MonsterMaster();
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
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem()+getPageSize()}));
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
        current = (MonsterMaster)getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new MonsterMaster();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            List<AbilityMaster> abilities = abilityMasterFacade.findAll();
            for (AbilityMaster ability : abilities) {
                MonsterAbilityRecord ab = new MonsterAbilityRecord(current.getId().intValue(), ability.getId().intValue());
                getMonsterAbilityRecordFacade().create(ab);
            }
            List<SaveMaster> saves = saveMasterFacade.findAll();
            for (SaveMaster save : saves) {
                MonsterSaveRecord sv = new MonsterSaveRecord(current.getId().intValue(), save.getId().intValue());
                monsterSaveRecordFacade.create(sv);
            }                
            JsfUtil.addSuccessMessage("MonsterMasterCreated");            
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "永続性エラーが発生しました");
            return null;
        }
    }

    public String prepareEdit() {
        current = (MonsterMaster)getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage("MonsterMasterUpdated" );
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "永続性エラーが発生しました");
            return null;
        }
    }

    public String destroy() {
        current = (MonsterMaster)getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
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
            getFacade().remove(current);
            JsfUtil.addSuccessMessage("MonsterMasterDeleted" );
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "永続性エラーが発生しました");
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count-1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex+1}).get(0);
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

    @FacesConverter(forClass=MonsterMaster.class)
    public static class MonsterMasterControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MonsterMasterController controller = (MonsterMasterController)facesContext.getApplication().getELResolver().
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
            if (object instanceof MonsterMaster) {
                MonsterMaster o = (MonsterMaster) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: "+MonsterMasterController.class.getName());
            }
        }

    }

}
