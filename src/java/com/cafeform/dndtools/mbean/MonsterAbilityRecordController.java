package com.cafeform.dndtools.mbean;

import com.cafeform.dndtools.entity.MonsterAbilityRecord;
import com.cafeform.dndtools.mbean.util.JsfUtil;
import com.cafeform.dndtools.mbean.util.PaginationHelper;
import com.cafeform.dndtools.ejb.MonsterAbilityRecordFacade;
import java.io.Serializable;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Named;

@Named
@SessionScoped
public class MonsterAbilityRecordController implements Serializable {

    private MonsterAbilityRecord current;
    private DataModel items = null;
    @Inject
    private com.cafeform.dndtools.ejb.MonsterAbilityRecordFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public MonsterAbilityRecordController() {
    }

    public MonsterAbilityRecord getSelected() {
        if (current == null) {
            current = new MonsterAbilityRecord();
            current.setMonsterAbilityRecordPK(new com.cafeform.dndtools.entity.MonsterAbilityRecordPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private MonsterAbilityRecordFacade getFacade() {
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
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
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
        current = (MonsterAbilityRecord) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new MonsterAbilityRecord();
        current.setMonsterAbilityRecordPK(new com.cafeform.dndtools.entity.MonsterAbilityRecordPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage("MonsterAbilityRecordCreated");
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "永続性エラーが発生しました");
            return null;
        }
    }

    public String prepareEdit() {
        current = (MonsterAbilityRecord) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage("MonsterAbilityRecordUpdated");
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "永続性エラーが発生しました");
            return null;
        }
    }

    public String destroy() {
        current = (MonsterAbilityRecord) getItems().getRowData();
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
            JsfUtil.addSuccessMessage("MonsterAbilityRecordDeleted");
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "永続性エラーが発生しました");
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
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
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

    @FacesConverter(forClass = MonsterAbilityRecord.class)
    public static class MonsterAbilityRecordControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MonsterAbilityRecordController controller = (MonsterAbilityRecordController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "monsterAbilityRecordController");
            return controller.ejbFacade.find(getKey(value));
        }

        com.cafeform.dndtools.entity.MonsterAbilityRecordPK getKey(String value) {
            com.cafeform.dndtools.entity.MonsterAbilityRecordPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new com.cafeform.dndtools.entity.MonsterAbilityRecordPK();
            key.setMonster(Integer.parseInt(values[0]));
            key.setAbilityId(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(com.cafeform.dndtools.entity.MonsterAbilityRecordPK value) {
            StringBuffer sb = new StringBuffer();
            sb.append(value.getMonster());
            sb.append(SEPARATOR);
            sb.append(value.getAbilityId());
            return sb.toString();
        }

        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof MonsterAbilityRecord) {
                MonsterAbilityRecord o = (MonsterAbilityRecord) object;
                return getStringKey(o.getMonsterAbilityRecordPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + MonsterAbilityRecordController.class.getName());
            }
        }
    }
}
