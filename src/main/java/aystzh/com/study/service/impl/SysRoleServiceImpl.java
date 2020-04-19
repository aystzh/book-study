package aystzh.com.study.service.impl;

import aystzh.com.study.dao.security.SysRoleMapper;
import aystzh.com.study.entity.security.SysRole;
import aystzh.com.study.service.SysRoleService;
import aystzh.com.base.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by zhanghuan on 2020/04/19.
 */
@Service
@Transactional
public class SysRoleServiceImpl extends AbstractService<SysRole> implements SysRoleService {
    @Resource
    private SysRoleMapper sysRoleMapper;

}
