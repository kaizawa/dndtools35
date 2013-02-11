package mbean;

import ejb.*;
import entity.MonsterMaster;
import mbean.util.JsfUtil;
import mbean.util.PaginationHelper;
import entity.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@ManagedBean(name = "monsterMasterController")
@SessionScoped
public class MonsterMasterController implements Serializable {
        
    @ManagedProperty(value = "#{sessionController}")
    private SessionController sessionController;

    public SessionController getSessionController() {
        return sessionController;
    }

    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }

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
    @EJB
    private ejb.MonsterMasterFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public MonsterMasterController() {
        initList();
    }

    private void initList() {
        monsterAbilityList.clear();
        monsterSaveList.clear();
        for (int i = 0; i < 6; i++) {
            monsterAbilityList.add(10);
        }
        for (int i = 0; i < 3; i++) {
            monsterSaveList.add(0);
        }
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
            pagination = new PaginationHelper(300) {

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
        current = (MonsterMaster) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        initList();
        current = new MonsterMaster();
        selectedItemIndex = -1;
        
        if(getSessionController().loggedIn == false){
            getSessionController().setTargetPage("/monsterMaster/Create");
            return "/login/LoginPage";
        }
        return "/monsterMaster/Create";
    }

    public String create() {
        if(getSessionController().isLoggedIn() == false){
            getSessionController().setTargetPage("/monsterMaster/Edit");
            return "/login/LoginPage";
        }
        try {
            getFacade().create(current);
            List<AbilityMaster> abilities = abilityMasterFacade.findAll();
            for (AbilityMaster ability : abilities) {
                MonsterAbilityRecord ab = new MonsterAbilityRecord(current.getId().intValue(), ability.getId().intValue());
                ab.setBase(monsterAbilityList.get(ability.getId() - 1));
                getMonsterAbilityRecordFacade().create(ab);
            }
            List<SaveMaster> saves = saveMasterFacade.findAll();
            for (SaveMaster save : saves) {
                MonsterSaveRecord sv = new MonsterSaveRecord(current.getId().intValue(), save.getId().intValue());
                sv.setMiscModifier(monsterSaveList.get(save.getId() - 1));
                monsterSaveRecordFacade.create(sv);
            }
            recreatePagination();
            recreateModel();
            JsfUtil.addSuccessMessage("モンスターが作成されました");
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "永続性エラーが発生しました");
            return null;
        }
    }

    public String prepareEdit() {               
        current = (MonsterMaster) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        List<MonsterAbilityRecord> abilityList = monsterAbilityRecordFacade.findByMonsterMaster(current);
        for (MonsterAbilityRecord ability : abilityList) {
            monsterAbilityList.set(ability.getAbilityMaster().getId() - 1, ability.getBase());
        }
        List<MonsterSaveRecord> saveList = monsterSaveRecordFacade.findByMonsterMaster(current);
        for (MonsterSaveRecord save : saveList) {
            monsterSaveList.set(save.getSaveMaster().getId() - 1, save.getMiscModifier());
        }
        
        if(getSessionController().loggedIn == false){
            getSessionController().setTargetPage("/monsterMaster/Edit");
            return "/login/LoginPage";
        }
        return "/monsterMaster/Edit";
    }

    public String update() {
        if(getSessionController().isLoggedIn() == false){
            getSessionController().setTargetPage("/monsterMaster/Edit");
            return "/login/LoginPage";
        }
        try {
            getFacade().edit(current);
            List<AbilityMaster> abilities = abilityMasterFacade.findAll();
            for (AbilityMaster ability : abilities) {
                MonsterAbilityRecord ab = new MonsterAbilityRecord(current.getId().intValue(), ability.getId().intValue());
                ab.setBase(monsterAbilityList.get(ability.getId() - 1));
                getMonsterAbilityRecordFacade().edit(ab);
            }
            List<SaveMaster> saves = saveMasterFacade.findAll();
            for (SaveMaster save : saves) {
                MonsterSaveRecord sv = new MonsterSaveRecord(current.getId().intValue(), save.getId().intValue());
                sv.setMiscModifier(monsterSaveList.get(save.getId() - 1));
                monsterSaveRecordFacade.edit(sv);
            }
            recreatePagination();
            recreateModel();
            JsfUtil.addSuccessMessage("更新されました");
            return null;
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "永続性エラーが発生しました");
            return null;
        }
    }

    public String destroy() {
        current = (MonsterMaster) getItems().getRowData();
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
            return "List";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage("MonsterMasterDeleted");
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

    @FacesConverter(forClass = MonsterMaster.class)
    public static class MonsterMasterControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MonsterMasterController controller = (MonsterMasterController) facesContext.getApplication().getELResolver().
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
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + MonsterMasterController.class.getName());
            }
        }
    }
    /*
     * 各種セーブの setter/getter Index は 0 から順に 頑健(fort), 反応(Ref), 意思(will)
     */
    private List<Integer> monsterSaveList = new ArrayList<Integer>(3);

    public List<Integer> getMonsterSaveList() {
        return monsterSaveList;
    }

    public void setMonsterSaveList(List<Integer> monsterSaveList) {
        this.monsterSaveList = monsterSaveList;
    }

    public int getFortSave() {
        return monsterSaveList.get(0);
    }

    public void setFortSave(int fortSave) {
        monsterSaveList.set(0, fortSave);
    }

    public int getRefSave() {
        return monsterSaveList.get(1);
    }

    public void setRefSave(int refSave) {
        monsterSaveList.set(1, refSave);
    }

    public int getWillSave() {
        return monsterSaveList.get(2);
    }

    public void setWillSave(int willSave) {
        monsterSaveList.set(2, willSave);
    }
    /*
     * 各種能力値の setter/getter インデックスは 0 から順に Str, Dex, Con, Int Wis, Cha
     */
    private List<Integer> monsterAbilityList = new ArrayList<Integer>();

    public List<Integer> getMonsterAbilityList() {
        return monsterAbilityList;
    }

    public void setMonsterAbilityList(List<Integer> monsterAbilityList) {
        this.monsterAbilityList = monsterAbilityList;
    }

    public int getStrAbility() {
        return monsterAbilityList.get(0);
    }

    public void setStrAbility(int strAbility) {
        monsterAbilityList.set(0, strAbility);
    }

    public int getDexAbility() {
        return monsterAbilityList.get(1);
    }

    public void setDexAbility(int dexAbility) {
        monsterAbilityList.set(1, dexAbility);
    }

    public int getConAbility() {
        return monsterAbilityList.get(2);
    }

    public void setConAbility(int conAbility) {
        monsterAbilityList.set(2, conAbility);
    }

    public int getIntAbility() {
        return monsterAbilityList.get(3);
    }

    public void setIntAbility(int intAbility) {
        monsterAbilityList.set(3, intAbility);
    }

    public int getWisAbility() {
        return monsterAbilityList.get(4);
    }

    public void setWisAbility(int wisAbility) {
        monsterAbilityList.set(4, wisAbility);
    }

    public int getChaAbility() {
        return monsterAbilityList.get(5);
    }

    public void setChaAbility(int chaAbility) {
        monsterAbilityList.set(5, chaAbility);
    }
}
