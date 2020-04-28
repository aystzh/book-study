package aystzh.com.study.service;
import aystzh.com.study.entity.security.SysMenu;
import aystzh.com.base.core.Service;

import java.util.List;


/**
 * Created by zhanghuan on 2020/04/19.
 */
public interface SysMenuService extends Service<SysMenu> {
    //查询所有菜单和角色的关系
     List<SysMenu> findAllMenusWithRole();

    List<SysMenu> findMenusByAdminId(Integer adminId);
}
