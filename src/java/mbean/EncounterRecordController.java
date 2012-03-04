package mbean;

import com.google.common.collect.Collections2;
import ejb.CharacterRecordFacade;
import ejb.EncounterMemberFacade;
import entity.EncounterRecord;
import mbean.util.JsfUtil;
import mbean.util.PaginationHelper;
import ejb.EncounterRecordFacade;
import entity.CharacterRecord;
import entity.EncounterMember;
import entity.ScenarioCharacterRecord;

import java.io.Serializable;
import java.text.StringCharacterIterator;
import java.util.*;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
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
    private CharacterRecordFacade characterRecordFacade;
    @EJB
    private EncounterMemberFacade encounterMemberFacade;
    private EncounterRecord current;
    private DataModel items = null;
    @EJB
    private EncounterRecordFacade encounterRecordFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    
    @ManagedProperty(value="#{scenarioRecordController}")
    private ScenarioRecordController scenarioRecordController;
    
    public ScenarioRecordController getScenarioRecordController() {
        return scenarioRecordController;
    }

    public void setScenarioRecordController(ScenarioRecordController scenarioRecordController) {
        this.scenarioRecordController = scenarioRecordController;
    }

    public EncounterRecordController() {
    }

    public EncounterRecord getSelected() {
        if (current == null) {
            current = new EncounterRecord();
            selectedItemIndex = -1;
        }
        return current;
    }

    private EncounterRecordFacade getFacade() {
        return encounterRecordFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(100) {

                @Override
                public int getItemsCount() {
                    return getFacade().countByScenarioRecord(scenarioRecordController.getSelected());
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(
                            getFacade().findByScenarioRecordRange(
                            scenarioRecordController.getSelected(),
                            new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    /*
     * encounterRecord/List は実際には表示されず、リストに相当するものは ScenarioRecord/Edit
     * に手表示される。なのでここでは ScenarioRecord/Edit を返す。
     */
    public String prepareList() {
        recreateModel();
        current = new EncounterRecord();
        selectedItemIndex = -1;
        return "/scenarioRecord/Edit";
    }


    /*
     * Edit ページが呼ばれる前のページに戻る。
     */
    public String cancelEdit() {
        /*
         * もしシナリオ編集画面から来ている場合には現在選択されている エンカウンターをリセットしておく。
         */
        if ("/scenarioRecord/Edit.xhtml".equals(previousPage)) {
            recreateModel();
            current = new EncounterRecord();
            selectedItemIndex = -1;
        }
        return previousPage;
    }

    public String prepareView() {
        current = (EncounterRecord) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "/encounterRecord/View";
    }

    public String prepareCreate() {
        current = new EncounterRecord();
        selectedItemIndex = -1;
        return null;
    }

    public String create() {
        try {
            current.setScenarioRecord(scenarioRecordController.getSelected());
            getFacade().create(current);
            addPlayerCharacter();
            JsfUtil.addSuccessMessage("エンカウンターが追加されました。");
            return prepareList();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "永続性エラーが発生しました");
            return null;
        }
    }

    public String getPreviousPage() {
        return previousPage;
    }

    public void setPreviousPage(String previousPage) {
        this.previousPage = previousPage;
    }
    private String previousPage = null;

    public String prepareEdit() {
        current = (EncounterRecord) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        previousPage = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return "/encounterRecord/Edit";
    }

    public String editCurrentEncounter() {
        previousPage = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return "/encounterRecord/Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage("保存されました。");
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "永続性エラーが発生しました");
        }
        /*
         * 編集ページの前のページに戻る
         */
        return previousPage;
    }

    public String destroy() {
        current = (EncounterRecord) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return null;
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
            JsfUtil.addErrorMessage(e, "永続性エラーが発生しました");
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().countByScenarioRecord(scenarioRecordController.getSelected());
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findByScenarioRecordRange(
                    scenarioRecordController.getSelected(),
                    new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
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

    public String resetBattle() {
        current.setRound(1);
        try {
            encounterRecordFacade.edit(current);
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Persistence Error Occured");
        }
        return null;
    }

    public String resetRound() {
        try {
            encounterRecordFacade.edit(current);
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Persistence Error Occured");
        }
        return null;
    }

    public EncounterRecord getCurrent() {
        return current;
    }

    public String reread() {
        items = null;
        return null;
    }

    public void reset() {
        recreateModel();
        current = new EncounterRecord();
        selectedItemIndex = -1;
    }

    /*
     * EncounterMember のメンバーキャンペーンの PC を追加する。
     */
    private String addPlayerCharacter() {
        Integer campaignId = current.getScenarioRecord().getCampaign().getId();
        List<CharacterRecord> charaRecordList;
        try {
            charaRecordList = characterRecordFacade.findByCampaignId(campaignId);
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "プレイヤーキャラクターの取得時に永続性エラーが発生しました");
            return "/encounterRecord/View";
        }

        /*
         * CharacterRecord は DB の CHARACTER_RECORD のエンティティ。 CharacterData は
         * CharacterRecode を内包し、各種計算を行うメソッドを持つ。 SenarioCharacterRecord は元となる
         * CharacterData/MonstterData を元にして シナリオを通じて使う一時的な情報を保持する。
         */
        for (CharacterRecord charaRecord : charaRecordList) {
            ScenarioCharacterRecord chara;
            try {
                CharacterData charaData = new CharacterData(charaRecord);
                chara = ScenarioCharacterRecordFactory.getInstance(charaData);
            }catch(Exception e){
                JsfUtil.addErrorMessage("ScenarioCharacterRecord 作成時にエラーが発生しました。 " + e.toString());
                e.printStackTrace();
                return "/encounterRecord/View";                                
            }

            try {
                EncounterMember member = new EncounterMember();
                member.setEncounterRecord(current);
                member.setScenarioCharacterRecord(chara);
                member.setInitiative(0);
                member.setMyTurn(false);
                encounterMemberFacade.edit(member);
            } catch (Exception e) {
                JsfUtil.addErrorMessage(e, "プレイヤーキャラクターの追加時に永続性エラーが発生しました");
                return "/encounterRecord/View";                
            }
        }
        return "/encounterRecord/View";
    }
}
