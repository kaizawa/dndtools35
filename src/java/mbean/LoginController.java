package mbean;

import ejb.PlayerMasterFacade;
import entity.PlayerMaster;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import mbean.util.JsfUtil;

@ManagedBean
@RequestScoped
public class LoginController  {    
    
    @ManagedProperty(value = "#{sessionController}")
    private SessionController sessionController;

    public SessionController getSessionController() {
        return sessionController;
    }

    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }
    
    @EJB
    private PlayerMasterFacade PlayerMasterFacade;

    private String userName;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * <p>Construct a new Page bean instance.</p>
     */
    public LoginController() {
    }

    public String charaEditLink_action() {
        // TODO: ボタンクリックアクションを処理します。戻り値は、
        // ナビゲーションケース名で、null の場合は同じページに戻ります。
        return null;
    }

    public String charaViewButton_action() {
        // TODO: ボタンクリックアクションを処理します。戻り値は、
        // ナビゲーションケース名で、null の場合は同じページに戻ります。
        return null;
    }
    
    public void logout_action(){
        setPlayerMaster(null);
        setLoggedIn(false);
    }

    public String login_action() {

        String username = this.userName;
        String passwd = this.password;

        PlayerMaster player =  PlayerMasterFacade.findByUsername(username);

        if(player == null){
            JsfUtil.addErrorMessage("ログインに失敗しました. ユーザ名(" + username + ")が間違っています");            
            return null;
        }

        if( !player.getPassword().equals(passwd)){
            JsfUtil.addErrorMessage("ログインに失敗しました. パスワードが間違っています");
            return null;
        }
        
        JsfUtil.addSuccessMessage("ログインが成功しました");

         setLoggedIn(true);
         setPlayerMaster(player);

        return null;
    }

    public String register_action() {
        // TODO:アクションを処理します。戻り値は、
        //  ナビゲーションケース名で、null の場合は同じページに戻ります。
        return null;
    }
    
    public boolean isLoggedIn() {
        return getSessionController().isLoggedIn();
    }
    public void setLoggedIn(boolean loggedIn){
        getSessionController().setLoggedIn(loggedIn);
    }

    public boolean isNotLoggedIn(){
        return getSessionController().isNotLoggedIn();
    } 
    
    public void setPlayerMaster(PlayerMaster player){
        getSessionController().setPlayerMaster(player);
    }
    
    public PlayerMaster getPlayerMaster(){
        return getSessionController().getPlayerMaster();
    }
}

