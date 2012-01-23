/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean;

import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;


/**
 *
 * @author kaizawa
 */
public class BaseBean {
    
    @ManagedProperty(value="#{sessionBean}")
    private SessionBean sessionBean;

    @ManagedProperty(value="#{applicationBean}")    
    private ApplicationBean applicationBean;  
    
    @ManagedProperty(value="#{playercharacterController}")
    private PlayercharacterController playercharacterController;

    public PlayercharacterController getPlayercharacterController() {
        return playercharacterController;
    }

    public void setPlayercharacterController(PlayercharacterController playercharacterController) {
        this.playercharacterController = playercharacterController;
    }
    
    FacesContext context = FacesContext.getCurrentInstance();

    public ApplicationBean getApplicationBean() {
        return applicationBean;
    }

    public void setApplicationBean(ApplicationBean applicationBean) {
        this.applicationBean = applicationBean;
    }

    public SessionBean getSessionBean() {
        return sessionBean;
    }

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }
}
