package com.cafeform.dndtools.mbean;

import com.cafeform.dndtools.entity.CampaignMaster;
import com.cafeform.dndtools.mbean.util.JsfUtil;
import com.cafeform.dndtools.mbean.util.PaginationHelper;
import com.cafeform.dndtools.ejb.CampaignMasterFacade;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Named
@SessionScoped
public class CampaignMasterController implements Serializable {

    private CampaignMaster current;
    private DataModel items = null;
    @Inject
    private com.cafeform.dndtools.ejb.CampaignMasterFacade ejbFacade;
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

    public CampaignMasterController() {
    }

    public CampaignMaster getSelectedCampaign() {
        current = getSessionController().getSelectedCampaign();
        if (current == null) {
            current = new CampaignMaster();
            selectedItemIndex = -1;
            getSessionController().setSelectedCampaign(current);
        }
        return current;
    }

    private CampaignMasterFacade getFacade() {
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
        getSessionController().setSelectedCampaign(null);        
        getSessionController().setSelectedScenarioRecord(null);
        recreateModel();
        return "/campaignMaster/List";
    }

    public String prepareView() {
        current = (CampaignMaster) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        getSessionController().setSelectedCampaign(current);
        return "View";
    }

    public String prepareCreate() {
        current = new CampaignMaster();
        selectedItemIndex = -1;
        getSessionController().setSelectedCampaign(null);
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage("作成されました");
            return prepareList();
        } catch (Exception e) {
            JsfUtil.addErrorMessage("永続性エラーが発生しました");
            return null;
        }
    }

    public String prepareEdit() {
        current = (CampaignMaster) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        getSessionController().setSelectedCampaign(current);
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage("保存しました");
            return "List";
        } catch (Exception e) {
            JsfUtil.addErrorMessage("永続性エラーが発生しました");
            return null;
        }
    }

    public String destroy() {
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
            JsfUtil.addSuccessMessage("削除されました");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("永続性エラーが発生しました");
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
            getSessionController().setSelectedCampaign(current);
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

    @FacesConverter(forClass = CampaignMaster.class)
    public static class CampaignMasterControllerConverter implements Converter {
        InitialContext ctx;
        private com.cafeform.dndtools.ejb.CampaignMasterFacade ejbFacade;

        public CampaignMasterControllerConverter() {
            try {
                ctx = new InitialContext();
                ejbFacade = (CampaignMasterFacade) ctx.lookup("java:module/CampaignMasterFacade");
            } catch (NamingException ex) {
                Logger.getLogger(CampaignMasterController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            return ejbFacade.find(getKey(value));
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
            if (object instanceof CampaignMaster) {
                CampaignMaster o = (CampaignMaster) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + CampaignMasterController.class.getName());
            }
        }
    }
}
