package com.synycs.truckbay.server;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by hadoop on 27/05/15.
 * Login bean
 */

@XmlRootElement
public class Login {
    String userName;
    String passWord;
    String appId;
    public Login(){

    }
    public Login(String userName,String passWord){
        this.userName=userName;
        this.passWord=passWord;

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
