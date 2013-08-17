/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean;

import entity.ScenarioRecord;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author kaizawa
 */

@RequestScoped
public class TemplateControl {

    @Inject
    private ScenarioRecordController scenarioRecordController;

    public EncounterRecordController getEncounterRecordController() {
        return encounterRecordController;
    }

    public void setEncounterRecordController(EncounterRecordController encounterRecordController) {
        this.encounterRecordController = encounterRecordController;
    }

    public ScenarioRecordController getScenarioRecordController() {
        return scenarioRecordController;
    }

    public void setScenarioRecordController(ScenarioRecordController scenarioRecordController) {
        this.scenarioRecordController = scenarioRecordController;
    }
    
    @Inject
    private EncounterRecordController encounterRecordController;

    /**
     * Creates a new instance of Template
     */
    public TemplateControl() {
    }

    public String prepareScenarioList() {
        encounterRecordController.reset();
        scenarioRecordController.reset();
        return "/scenarioRecord/List";
    }

    public String prepareIndex() {
        encounterRecordController.reset();
        scenarioRecordController.reset();
        return "/index";
    }
}
