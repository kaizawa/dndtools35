package com.cafeform.dndtools.mbean;

import com.cafeform.dndtools.entity.MonsterSaveRecord;
import com.cafeform.dndtools.mbean.util.JsfUtil;
import com.cafeform.dndtools.mbean.util.PaginationHelper;
import com.cafeform.dndtools.ejb.MonsterSaveRecordFacade;
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
public class MonsterSaveRecordController implements Serializable {

    private MonsterSaveRecord current;
    private DataModel items = null;
    @Inject
    private com.cafeform.dndtools.ejb.MonsterSaveRecordFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public MonsterSaveRecordController() {
    }

    public MonsterSaveRecord getSelected() {
        if (current == null) {
            current = new MonsterSaveRecord();
            current.setMonsterSaveRecordPK(new com.cafeform.dndtools.entity.MonsterSaveRecordPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private MonsterSaveRecordFacade getFacade() {
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
        current = (MonsterSaveRecord) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new MonsterSaveRecord();
        current.setMonsterSaveRecordPK(new com.cafeform.dndtools.entity.MonsterSaveRecordPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            recreateModel();
            JsfUtil.addSuccessMessage("MonsterSaveRecordCreated");
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "永続性エラーが発生しました");
            return null;
        }
    }

    public String prepareEdit() {
        current = (MonsterSaveRecord) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            recreateModel();
            JsfUtil.addSuccessMessage("MonsterSaveRecordUpdated");
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "永続性エラーが発生しました");
            return null;
        }
    }

    public String destroy() {
        current = (MonsterSaveRecord) getItems().getRowData();
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
            JsfUtil.addSuccessMessage("MonsterSaveRecordDeleted");
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

    @FacesConverter(forClass = MonsterSaveRecord.class)
    public static class MonsterSaveRecordControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MonsterSaveRecordController controller = (MonsterSaveRecordController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "monsterSaveRecordController");
            return controller.ejbFacade.find(getKey(value));
        }

        com.cafeform.dndtools.entity.MonsterSaveRecordPK getKey(String value) {
            com.cafeform.dndtools.entity.MonsterSaveRecordPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new com.cafeform.dndtools.entity.MonsterSaveRecordPK();
            key.setMonster(Integer.parseInt(values[0]));
            key.setSaveId(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(com.cafeform.dndtools.entity.MonsterSaveRecordPK value) {
            StringBuffer sb = new StringBuffer();
            sb.append(value.getMonster());
            sb.append(SEPARATOR);
            sb.append(value.getSaveId());
            return sb.toString();
        }

        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof MonsterSaveRecord) {
                MonsterSaveRecord o = (MonsterSaveRecord) object;
                return getStringKey(o.getMonsterSaveRecordPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + MonsterSaveRecordController.class.getName());
            }
        }
    }
}