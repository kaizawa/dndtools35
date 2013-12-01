/*
 * ApplicationController1.java
 *
 * Created on 2008/12/23, 23:35:51
 */
package com.cafeform.dndtools.mbean;

import com.cafeform.dndtools.entity.SpellMasterFlat;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named
@SessionScoped
public class SpellMasterController implements Serializable {
    @Inject protected ApplicationController applicationController;    
    @Inject protected SessionController sessionController;
    
    public SessionController getSessionController() {
        return sessionController;
    }

    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }
    
    public ApplicationController getApplicationController() {
        return applicationController;
    }

    public void setApplicationController(ApplicationController applicationController) {
        this.applicationController = applicationController;
    }
    
    public SpellMasterController() 
    {
        
    }

    @PostConstruct
    public void init() 
    {
        
    }
    
    public List<SpellMasterFlat> getSpellMasterFlatList () 
    {
        return getApplicationController().getSpellMasterFlat();
    }

}
