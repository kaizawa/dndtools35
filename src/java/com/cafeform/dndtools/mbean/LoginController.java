package com.cafeform.dndtools.mbean;

import com.cafeform.dndtools.ejb.PlayerMasterFacade;
import com.cafeform.dndtools.entity.PlayerMaster;
import java.security.MessageDigest;
import com.cafeform.dndtools.mbean.util.JsfUtil;
import javax.enterprise.context.RequestScoped;

import javax.inject.Inject;

import javax.inject.Named;

@Named
@RequestScoped
public class LoginController {

    @Inject
    private SessionController sessionController;

    public SessionController getSessionController() {
        return sessionController;
    }

    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }
    @Inject
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

    public void logout_action() {
        setPlayerMaster(null);
        setLoggedIn(false);
    }

    public String login_action() {

        PlayerMaster player = PlayerMasterFacade.findByUsername(this.userName);
        
        String digest = null;
        try {      
            digest = getDigest(this.password);
        } catch (Exception e){
            JsfUtil.addErrorMessage("ログイン処理が失敗しました.");
        }        

        if (player == null || !player.getPassword().equals(digest)) {
            JsfUtil.addErrorMessage("ログインに失敗しました. ユーザ名かパスワードが間違っています.");
            return null;
        }
        setPlayerMaster(player);

        setLoggedIn(true);

        JsfUtil.addSuccessMessage("ログインが成功しました.");

        return getSessionController().getTargetPage();
    }

    public String register_action() {
        // TODO:アクションを処理します。戻り値は、
        //  ナビゲーションケース名で、null の場合は同じページに戻ります。
        return null;
    }

    public boolean isLoggedIn() {
        return getSessionController().isLoggedIn();
    }

    public void setLoggedIn(boolean loggedIn) {
        getSessionController().setLoggedIn(loggedIn);
    }

    public boolean isNotLoggedIn() {
        return getSessionController().isNotLoggedIn();
    }

    public void setPlayerMaster(PlayerMaster player) {
        getSessionController().setPlayerMaster(player);
    }

    public PlayerMaster getPlayerMaster() {
        return getSessionController().getPlayerMaster();
    }

    private String getDigest(String data) throws Exception {

        StringBuilder s = new StringBuilder();
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] dat = data.getBytes();
        md.update(dat);

        byte[] digest = md.digest();
        for (int i = 0; i < digest.length; i++) {
            int d = digest[i];

            if (d < 0) { // byte 128-255
                d += 256;
            }
            if (d < 16) { //0-15 16
                s.append("0");
            }
            s.append(Integer.toString(d, 16));
        }
        return s.toString();
    }
}
