package aystzh.com.study.dao.security;

import aystzh.com.base.core.Mapper;
import aystzh.com.study.entity.security.SysMenu;

import java.util.List;

public interface SysMenuMapper extends Mapper<SysMenu> {

    List<SysMenu> selectAllMenusWithRole();

}