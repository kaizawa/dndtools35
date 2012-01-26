package mbean;

import com.google.common.collect.Collections2;
import ejb.EncounterBattleMemberFacade;
import ejb.EncounterCharacterFacade;
import entity.EncounterRecord;
import mbean.util.JsfUtil;
import mbean.util.PaginationHelper;
import ejb.EncounterRecordFacade;
import entity.EncounterBattleMember;
import entity.EncounterCharacter;

import java.io.Serializable;
import java.util.*;
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

@ManagedBean(name = "encounterRecordController")
@SessionScoped
public class EncounterRecordController implements Serializable {

    @EJB
    private EncounterBattleMemberFacade encounterBattleMemberFacade;
    @EJB
    private EncounterCharacterFacade encounterCharacterFacade;
    private EncounterRecord current;
    private DataModel items = null;
    @EJB
    private EncounterRecordFacade encounterRecordFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public EncounterRecordController() {
    }

    public EncounterRecord getSelected() {
        if (current == null) {
            current = new EncounterRecord();
            selectedItemIndex = -1;
        }
        return current;
    }

    public HtmlDataTable getEncounterCharacterTable() {
        return encounterCharacterTable;
    }

    public void setEncounterCharacterTable(HtmlDataTable encounterCharacterTable) {
        this.encounterCharacterTable = encounterCharacterTable;
    }

    public void sortBattleMember() {
        Collections.sort(battleMemberList, new Comparator<EncounterBattleMember>() {

            @Override
            public int compare(EncounterBattleMember mem1, EncounterBattleMember mem2) {

                return mem2.getInitiative().compareTo(mem1.getInitiative());
            }
        });
    }

    private EncounterRecordFacade getFacade() {
        return encounterRecordFacade;
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
        current = (EncounterRecord) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new EncounterRecord();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("EncounterRecordCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (EncounterRecord) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("EncounterRecordUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (EncounterRecord) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("EncounterRecordDeleted"));
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
        return JsfUtil.getSelectItems(encounterRecordFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(encounterRecordFacade.findAll(), true);
    }

    @FacesConverter(forClass = EncounterRecord.class)
    public static class EncounterRecordControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EncounterRecordController controller = (EncounterRecordController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "encounterRecordController");
            return controller.encounterRecordFacade.find(getKey(value));
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
            if (object instanceof EncounterRecord) {
                EncounterRecord o = (EncounterRecord) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + EncounterRecordController.class.getName());
            }
        }
    }
    private boolean charaSelected;

    public boolean isCharaSelected() {

        int index = encounterCharacterTable.getRowIndex();

        EncounterCharacter encounterCharacter = encounterCharacterFacade.findAll().get(index);

        for (EncounterBattleMember member : encounterBattleMemberFacade.findAll()) {
            if (member.getEncounterCharacter().equals(encounterCharacter)) {
                return true;
            }
        }
        return false;
    }
    private HtmlDataTable encounterCharacterTable = new HtmlDataTable();

    public void setCharaSelected(boolean charaSelected) {
        int index = encounterCharacterTable.getRowIndex();

        EncounterCharacter encounterCharacter = encounterCharacterFacade.findAll().get(index);

        try {
            if (encounterCharacter != null) {
                if (charaSelected) {
                    if (encounterBattleMemberFacade.findByEncounterCharacter(encounterCharacter).isEmpty()) {
                        EncounterBattleMember member = new EncounterBattleMember();
                        member.setEncounterRecord(current);
                        member.setEncounterCharacter(encounterCharacter);
                        encounterBattleMemberFacade.edit(member);
                        JsfUtil.addSuccessMessage("Character successfully added as Battle Member");
                    }
                } else {
                    List<EncounterBattleMember> memberList = encounterBattleMemberFacade.findByEncounterCharacter(encounterCharacter);
                    for (EncounterBattleMember member : memberList) {
                        encounterBattleMemberFacade.remove(member);
                        JsfUtil.addSuccessMessage("Character successfully removed from Battle Members");
                    }
                }
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e + "Persistence Error Occured");
        }
    }
    List<EncounterBattleMember> battleMemberList;

    public List<EncounterBattleMember> getBattleMemberList() {
        battleMemberList = encounterBattleMemberFacade.findByEncounterRecord(current);
        sortBattleMember();
        setTurn();
        return battleMemberList;
    }

    public void setTurn() {
        if (current.getTurnCharacter() == null) {
            if (battleMemberList.size() > 0) {
                EncounterBattleMember first = battleMemberList.get(0);
                first.setMyTurn(true);
                current.setTurnCharacter(first);
                try {
                    encounterRecordFacade.edit(current);
                    encounterBattleMemberFacade.edit(first);
                } catch (Exception e) {
                    JsfUtil.addErrorMessage("Persistance Error Happened");
                }
                JsfUtil.addSuccessMessage("TrunCharacter was null");
            }
        } else {
            for (EncounterBattleMember member : battleMemberList) {
                if (member.equals(current.getTurnCharacter())) {
                    member.setMyTurn(true);
                }else { 
                    member.setMyTurn(false);
                }
                try {
                    encounterBattleMemberFacade.edit(member);
                } catch (Exception e){
                    JsfUtil.addErrorMessage("Persistance Error Happened");                    
                }
            }
            //JsfUtil.addSuccessMessage("TrunCharacter is " + current.getTurnCharacter().getEncounterCharacter().getName());
        }
    }

    public String saveBattleMembers() {
        try {
            for (EncounterBattleMember member : battleMemberList) {
                encounterBattleMemberFacade.edit(member);
            }
            JsfUtil.addSuccessMessage("Member's Poropety Updated");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Persistence Error Occured");
        }
        return null;
    }

    public void setSelectedMember(EncounterBattleMember selectedMember) {
        EncounterBattleMember member = (EncounterBattleMember) battleMemberTable.getRowData();

    }
    private HtmlDataTable battleMemberTable = new HtmlDataTable();

    public HtmlDataTable getBattleMemberTable() {
        return battleMemberTable;
    }

    public void setBattleMemberTable(HtmlDataTable battleMemberTable) {
        this.battleMemberTable = battleMemberTable;
    }

    public String nextTrun() {
        int current_index;
        current_index = battleMemberList.indexOf(current.getTurnCharacter());
        EncounterBattleMember nextChara;
        if (battleMemberList.size() <= current_index + 1) {
            nextChara = battleMemberList.get(0);                    
            // got next round
            nextChara.setMyTurn(true);
            current.setTurnCharacter(nextChara);
            current.setRound(current.getRound() + 1);
        } else {
            nextChara = battleMemberList.get(current_index + 1);                                
            current.setTurnCharacter(nextChara);
            nextChara.setMyTurn(true);
        }
        try {
            encounterRecordFacade.edit(current);
            encounterBattleMemberFacade.edit(nextChara);                
        } catch (Exception e){
            JsfUtil.addErrorMessage("Persistence Error Occured");            
        }
        return null;
    }
}
