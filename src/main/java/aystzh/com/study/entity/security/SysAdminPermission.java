package aystzh.com.study.entity.security;

import aystzh.com.base.annotations.CreateTime;
import aystzh.com.base.annotations.ModifyTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "sys_admin_permission")
public class SysAdminPermission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "admin_id")
    private Integer adminId;

    @Column(name = "permission_id")
    private Integer permissionId;

    private Integer type;

    private Integer creator;

    @CreateTime
    @Column(name = "create_time")
    private Date createTime;

    private Integer modifier;

    @ModifyTime
    @Column(name = "modify_time")
    private Date modifyTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return admin_id
     */
    public Integer getAdminId() {
        return adminId;
    }

    /**
     * @param adminId
     */
    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    /**
     * @return permission_id
     */
    public Integer getPermissionId() {
        return permissionId;
    }

    /**
     * @param permissionId
     */
    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    /**
     * @return type
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return creator
     */
    public Integer getCreator() {
        return creator;
    }

    /**
     * @param creator
     */
    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return modifier
     */
    public Integer getModifier() {
        return modifier;
    }

    /**
     * @param modifier
     */
    public void setModifier(Integer modifier) {
        this.modifier = modifier;
    }

    /**
     * @return modify_time
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * @param modifyTime
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        return "SysAdminPermission{" +
                "id=" + id +
                ", adminId=" + adminId +
                ", permissionId=" + permissionId +
                ", type=" + type +
                ", creator=" + creator +
                ", createTime=" + createTime +
                ", modifier=" + modifier +
                ", modifyTime=" + modifyTime +
                '}';
    }
}