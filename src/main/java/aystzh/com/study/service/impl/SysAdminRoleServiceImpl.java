package aystzh.com.study.service.impl;

import aystzh.com.study.dao.security.SysAdminRoleMapper;
import aystzh.com.study.entity.security.SysAdminRole;
import aystzh.com.study.service.SysAdminRoleService;
import aystzh.com.base.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by zhanghuan on 2020/04/19.
 */
@Service
@Transactional
public class SysAdminRoleServiceImpl extends AbstractService<SysAdminRole> implements SysAdminRoleService {
    @Resource
    private SysAdminRoleMapper sysAdminRoleMapper;

}
