package aystzh.com.study.entity.security;

import aystzh.com.base.annotations.CreateTime;
import aystzh.com.base.annotations.ModifyTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "sys_permission")
public class SysPermission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 父级权限id
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 权限值
     */
    private String value;

    /**
     * 图表
     */
    private String icon;

    /**
     * 权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）
     */
    private Integer type;

    /**
     * 前端资源路径
     */
    private String uri;

    /**
     * 状态1:启用0:禁用
     */
    private Boolean enabled;

    /**
     * 排序
     */
    private Integer sort;

    @CreateTime
    @Column(name = "create_time")
    private Date createTime;

    private Integer creator;

    @ModifyTime
    @Column(name = "modify_time")
    private Date modifyTime;

    private Integer modifier;

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
     * 获取父级权限id
     *
     * @return parent_id - 父级权限id
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置父级权限id
     *
     * @param parentId 父级权限id
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取资源名称
     *
     * @return name - 资源名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置资源名称
     *
     * @param name 资源名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取权限值
     *
     * @return value - 权限值
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置权限值
     *
     * @param value 权限值
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 获取图表
     *
     * @return icon - 图表
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置图表
     *
     * @param icon 图表
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）
     *
     * @return type - 权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）
     *
     * @param type 权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）
     */
    public void setType(Integer type) {
        this.type = type;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * 获取状态1:启用0:禁用
     *
     * @return enabled - 状态1:启用0:禁用
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * 设置状态1:启用0:禁用
     *
     * @param enabled 状态1:启用0:禁用
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * 获取排序
     *
     * @return sort - 排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序
     *
     * @param sort 排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
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

    @Override
    public String toString() {
        return "SysPermission{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", icon='" + icon + '\'' +
                ", type=" + type +
                ", uri='" + uri + '\'' +
                ", enabled=" + enabled +
                ", sort=" + sort +
                ", createTime=" + createTime +
                ", creator=" + creator +
                ", modifyTime=" + modifyTime +
                ", modifier=" + modifier +
                '}';
    }
}