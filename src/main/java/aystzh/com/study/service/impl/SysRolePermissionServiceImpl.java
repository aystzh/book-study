package aystzh.com.study.service.impl;

import aystzh.com.study.dao.security.SysRolePermissionMapper;
import aystzh.com.study.entity.security.SysRolePermission;
import aystzh.com.study.service.SysRolePermissionService;
import aystzh.com.base.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by zhanghuan on 2020/05/03.
 */
@Service
@Transactional
public class SysRolePermissionServiceImpl extends AbstractService<SysRolePermission> implements SysRolePermissionService {
    @Resource
    private SysRolePermissionMapper sysRolePermissionMapper;

}
