package com.company.project.model;

import javax.persistence.*;

@Table(name = "tb_user")
public class TbUser {
    @Id
    private Integer uid;

    private String username;

    private String pasword;

    /**
     * @return uid
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * @param uid
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return pasword
     */
    public String getPasword() {
        return pasword;
    }

    /**
     * @param pasword
     */
    public void setPasword(String pasword) {
        this.pasword = pasword;
    }
}