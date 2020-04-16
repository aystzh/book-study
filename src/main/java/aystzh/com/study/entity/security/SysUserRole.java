package aystzh.com.study.entity.security;

import java.io.Serializable;

/**
 * created by zhanghuan on 2020/4/16.
 */
public class SysUserRole implements Serializable {
    private Integer id;

    private Integer hrid;

    private Integer rid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHrid() {
        return hrid;
    }

    public void setHrid(Integer hrid) {
        this.hrid = hrid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }
}
