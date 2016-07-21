package org.bitholic.dao;

/**
 * Created by bitholic on 16/7/13.
 */
public class Account {
    private String uid;
    private String passwd;
    private Integer privilege;
    private Integer remember;
    private Integer messageNumber;

    public Account() { }

    public Account(String uid, String passwd, Integer privilege, Integer messageNumber, Integer remember) {
        this.uid = uid;
        this.passwd = passwd;
        this.privilege = privilege;
        this.messageNumber = messageNumber;
        this.remember = remember;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Integer privilege) {
        this.privilege = privilege;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Integer getRemember() {
        return remember;
    }

    public void setRemember(Integer remember) {
        this.remember = remember;
    }

    public Integer getMessageNumber() {
        return messageNumber;
    }

    public void setMessageNumber(Integer messageNumber) {
        this.messageNumber = messageNumber;
    }
}
