package com.welcome.home.playandroid.bean;

import java.util.List;

public class LoginBean {


    /**
     * collectIds : []
     * email :
     * icon :
     * id : 5371
     * password : 12345111
     * type : 0
     * username : lhh1111
     */

    private String email;
    private String icon;
    private int id;
    private String password;
    private int type;
    private String username;
    private List<?> collectIds;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<?> getCollectIds() {
        return collectIds;
    }

    public void setCollectIds(List<?> collectIds) {
        this.collectIds = collectIds;
    }
}
