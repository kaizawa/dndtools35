package com.cafeform.dndtools.mbean;

import com.cafeform.dndtools.entity.CharacterRecord;
import com.cafeform.dndtools.mbean.util.JsfUtil;
import com.cafeform.dndtools.mbean.util.PaginationHelper;
import com.cafeform.dndtools.ejb.CharacterRecordFacade;
import java.io.Serializable;
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
public class CharacterRecordController implements Serializable {

    @Inject
    private CharacterRecordFacade characterRecordFacade;
    private CharacterRecord currentCharacterRecord;
    private DataModel items = null;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public CharacterRecordController() {
    }

    public CharacterRecord getSelected() {
        if (currentCharacterRecord == null) {
            currentCharacterRecord = new CharacterRecord();
            selectedItemIndex = -1;
        }
        return currentCharacterRecord;
    }

    private CharacterRecordFacade getCharacterRecordFacade() {
        return characterRecordFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {
                @Override
                public int getItemsCount() {
                    return getCharacterRecordFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getCharacterRecordFacade().findRange(
                        new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
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
        currentCharacterRecord = (CharacterRecord) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        currentCharacterRecord = new CharacterRecord();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getCharacterRecordFacade().create(currentCharacterRecord);
            JsfUtil.addSuccessMessage("CharacterRecordCreated");
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "PersistenceErrorOccured");
            return null;
        }
    }

    public String prepareEdit() {
        currentCharacterRecord = (CharacterRecord) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getCharacterRecordFacade().edit(currentCharacterRecord);
            JsfUtil.addSuccessMessage("CharacterRecordUpdated");
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "PersistenceErrorOccured");
            return null;
        }
    }

    public String destroy() {
        currentCharacterRecord = (CharacterRecord) getItems().getRowData();
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
            getCharacterRecordFacade().remove(currentCharacterRecord);
            JsfUtil.addSuccessMessage("CharacterRecordDeleted");
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "PersistenceErrorOccured");
        }
    }

    private void updateCurrentItem() {
        int count = getCharacterRecordFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            currentCharacterRecord = getCharacterRecordFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
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
        return JsfUtil.getSelectItems(characterRecordFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(characterRecordFacade.findAll(), true);
    }

    @FacesConverter(forClass = CharacterRecord.class)
    public static class CharacterRecordControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CharacterRecordController controller = (CharacterRecordController) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, "characterRecordController");
            return controller.characterRecordFacade.find(getKey(value));
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
            if (object instanceof CharacterRecord) {
                CharacterRecord o = (CharacterRecord) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type "
                    + object.getClass().getName() + "; expected type: "
                    + CharacterRecordController.class.getName());
            }
        }
    }
}
