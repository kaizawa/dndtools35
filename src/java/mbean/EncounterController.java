package mbean;

import ejb.EncounterCharacterFacade;
import entity.Encounter;
import mbean.util.JsfUtil;
import mbean.util.PaginationHelper;
import ejb.EncounterFacade;
import entity.EncounterCharacter;
import entity.Playercharacter;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@ManagedBean(name = "encounterController")
@SessionScoped
public class EncounterController extends BaseBean implements Serializable {

    private Encounter current;
    private DataModel items = null;
    @EJB
    private EncounterFacade encounterFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    @EJB
    private EncounterCharacterFacade encounterCharacterFacade;
    
    private List<EncounterCharacter> encounterCharacterList;

    public List<EncounterCharacter> getEncounterCharacterList() {
        return encounterCharacterList;
    }

    public void setEncounterCharacterList(List<EncounterCharacter> encounterCharacterList) {
        this.encounterCharacterList = encounterCharacterList;
    }
    
    private HtmlDataTable dataTable = new HtmlDataTable();

    public HtmlDataTable getDataTable() {
        return dataTable;
    }

    public void setDataTable(HtmlDataTable hdt) {
        this.dataTable = hdt;
    }    

    public EncounterController() {
    }

    public Encounter getSelected() {
        if (current == null) {
            current = new Encounter();
            selectedItemIndex = -1;
        }
        return current;
    }

    private EncounterFacade getFacade() {
        return encounterFacade;
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
        current = (Encounter) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Encounter();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("EncounterCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Encounter) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("EncounterUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Encounter) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("EncounterDeleted"));
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
        return JsfUtil.getSelectItems(encounterFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(encounterFacade.findAll(), true);
    }

    @FacesConverter(forClass = Encounter.class)
    public static class EncounterControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EncounterController controller = (EncounterController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "encounterController");
            return controller.encounterFacade.find(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuffer sb = new StringBuffer();
            sb.append(value);
            return sb.toString();
        }

        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Encounter) {
                Encounter o = (Encounter) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + EncounterController.class.getName());
            }
        }
    }  
    
    private boolean charaSelected;
    /**
     * @return the charaSelected
     */
    public boolean isCharaSelected() {
        int index = dataTable.getRowIndex();
        
        Playercharacter playerCharacter =  getSessionBean().getPlayerCharacterList().get(index);

        for(EncounterCharacter encChar : getEncounterCharacterList()){
            if(encChar.getPlayerCharacter().equals(playerCharacter)){
                return true;
            }
        }
        return false;
    }    
    
   /**
     * @param charaSelected the charaSelected to set
     */
    public void setCharaSelected(boolean charaSelected) {
        int index = dataTable.getRowIndex();
        
        Playercharacter playerCharacer =  getSessionBean().getPlayerCharacterList().get(index);       
        if (playerCharacer != null) {
            if (charaSelected) {
                EncounterCharacter encChara = new EncounterCharacter();
                encChara.setEncounter(current);
                encChara.setPlayerCharacter(playerCharacer);
                encounterCharacterFacade.create(encChara);
            } else {
                List<EncounterCharacter> encCharaList = encounterCharacterFacade.findByPlayerCharacter(playerCharacer);
                for(EncounterCharacter encChara : encCharaList){
                    encounterCharacterFacade.remove(encChara);
                }
            }
        }
    }      
}
