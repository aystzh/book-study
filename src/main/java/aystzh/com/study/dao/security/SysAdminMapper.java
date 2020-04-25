package aystzh.com.study.dao.security;

import aystzh.com.base.core.Mapper;
import aystzh.com.study.entity.security.SysAdmin;
import aystzh.com.study.entity.security.SysRole;

import java.util.List;

public interface SysAdminMapper extends Mapper<SysAdmin> {
    List<SysRole> selectHrRolesById(Integer id);
}