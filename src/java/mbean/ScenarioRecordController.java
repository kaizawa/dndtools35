package mbean;

import ejb.ScenarioRecordFacade;
import entity.CampaignMaster;
import entity.ScenarioRecord;
import java.io.Serializable;

import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import mbean.util.JsfUtil;
import mbean.util.PaginationHelper;


@SessionScoped
public class ScenarioRecordController implements Serializable {

    private ScenarioRecord current;
    private DataModel items = null;
    @Inject
    private ejb.ScenarioRecordFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    @Inject
    private SessionController sessionController;

    public SessionController getSessionController() {
        return sessionController;
    }

    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }

    public ScenarioRecordController() {
    }

    public ScenarioRecord getSelected() {
        if (current == null) {
            current = new ScenarioRecord();
            selectedItemIndex = -1;
        }
        return current;
    }

    private ScenarioRecordFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    if (getSelectedCampaign() == null) {
                        JsfUtil.addErrorMessage("selectedCampaing is null1");                        
                        return getFacade().count();
                    } else {
                        return getFacade().countByCampaign(getSelectedCampaign());
                    }
                }

                @Override
                public DataModel createPageDataModel() {
                    if (getSelectedCampaign() == null) {                    
                        JsfUtil.addErrorMessage("selectedCampaing is null2");
                        return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                    } else {
                        return new ListDataModel(getFacade().findRangeByCampaign(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}, getSelectedCampaign()));
                    }
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        getSessionController().setSelectedScenarioRecord(null);
        recreateModel();
        current = new ScenarioRecord();
        selectedItemIndex = -1;
        return "/scenarioRecord/List";
    }

    public String prepareView() {
        current = (ScenarioRecord) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new ScenarioRecord();
        selectedItemIndex = -1;
        return "/scenarioRecord/Create";
    }

    public String create() {
        try {
            current.setCampaign(getSelectedCampaign());
            getFacade().create(current);
            JsfUtil.addSuccessMessage("新しいシナリオが作成されました");
            return prepareList();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "永続性エラーが発生しました");
            return null;
        }
    }

    public String prepareEdit() {
        current = (ScenarioRecord) getItems().getRowData();
        selectedItemIndex = getPagination().getPageFirstItem() + getItems().getRowIndex();
        getSessionController().setSelectedScenarioRecord(current);
        return "/scenarioRecord/Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage("シナリオが更新されました");
            return "/scenarioRecord/Edit";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "永続性エラーが発生しました");
            return null;
        }
    }

    public String cancel() {
        return "/scenarioRecord/List";
    }

    public String destroy() {
        selectedItemIndex = getPagination().getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        return prepareList();
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
            JsfUtil.addSuccessMessage("シナリオが削除されました");
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
            if (getPagination().getPageFirstItem() >= count) {
                getPagination().previousPage();
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

    @FacesConverter(forClass = ScenarioRecord.class)
    public static class ScenarioRecordControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ScenarioRecordController controller = (ScenarioRecordController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "scenarioRecordController");
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
            if (object instanceof ScenarioRecord) {
                ScenarioRecord o = (ScenarioRecord) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + ScenarioRecordController.class.getName());
            }
        }
    }

    public void reset() {
        recreateModel();
        current = new ScenarioRecord();
        selectedItemIndex = -1;
    }

    /*
     * 選択されたキャンペーン
     */
    public CampaignMaster getSelectedCampaign() {
        recreatePagination();
        recreateModel();
        return getSessionController().getSelectedCampaign();
    }

    public void setSelectedCampaign(CampaignMaster selectedCampaign) {
        if (getSessionController().getSelectedCampaign() != null
                && getSessionController().getSelectedCampaign().getId() != selectedCampaign.getId()) {
            recreatePagination();
            recreateModel();
        }
        getSessionController().setSelectedCampaign(selectedCampaign);
    }
}
