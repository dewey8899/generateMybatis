package com.company.project.model;

import javax.persistence.*;

@Table(name = "tb_role")
public class TbRole {
    @Id
    private Integer rid;

    private String rname;

    /**
     * @return rid
     */
    public Integer getRid() {
        return rid;
    }

    /**
     * @param rid
     */
    public void setRid(Integer rid) {
        this.rid = rid;
    }

    /**
     * @return rname
     */
    public String getRname() {
        return rname;
    }

    /**
     * @param rname
     */
    public void setRname(String rname) {
        this.rname = rname;
    }
}