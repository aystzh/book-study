package aystzh.com.study.service.impl;

import aystzh.com.study.dao.security.SysMenuRoleMapper;
import aystzh.com.study.entity.security.SysMenuRole;
import aystzh.com.study.service.SysMenuRoleService;
import aystzh.com.base.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by zhanghuan on 2020/04/19.
 */
@Service
@Transactional
public class SysMenuRoleServiceImpl extends AbstractService<SysMenuRole> implements SysMenuRoleService {
    @Resource
    private SysMenuRoleMapper sysMenuRoleMapper;

}
