package mbean;

import entity.ArmMaster;
import mbean.util.JsfUtil;
import mbean.util.PaginationHelper;
import ejb.ArmMasterFacade;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

@SessionScoped
public class ArmMasterController implements Serializable {

    private ArmMaster current;
    private DataModel items = null;
    @Inject
    private ejb.ArmMasterFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public ArmMasterController() {
    }

    public ArmMaster getSelected() {
        if (current == null) {
            current = new ArmMaster();
            selectedItemIndex = -1;
        }
        return current;
    }

    private ArmMasterFacade getFacade() {
        return ejbFacade;
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
        current = (ArmMaster) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new ArmMaster();
        selectedItemIndex = -1;
        return "Edit";
    }

    public String prepareEdit() {
        current = (ArmMaster) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String prepareCopy() {
        current = copy((ArmMaster) getItems().getRowData());
        current.setName(current.getName() + "のコピー");
        selectedItemIndex = -1;
        return "Edit";
    }
    
    public String createEnhanced(int level) {
        current = copy((ArmMaster) getItems().getRowData());
        current.setName(current.getName() + "+" + level);
        current.setEnhancementBonus(level);
        int additionalPrice = 0;
        switch(level){
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
        current.setDescription(null == current.getDescription() ? "高品質"
                : current.getDescription() + "\n高品質");
        current.setPrice(current.getPrice() + additionalPrice + 300);
        selectedItemIndex = -1;
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ArmMasterUpdated"));
            return prepareList();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (ArmMaster) getItems().getRowData();
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
            getFacade().remove(current);
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
            return controller.ejbFacade.find(getKey(value));
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
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + ArmMaster.class.getName());
            }
        }
    }

    /*
     * 削除ボタンの表示
     */
    public boolean isDeleteButtonDisabled() {
        return (current == null || current.getId() == null);
    }
}
