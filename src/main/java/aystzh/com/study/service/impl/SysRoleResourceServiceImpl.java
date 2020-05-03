package aystzh.com.study.service.impl;

import aystzh.com.study.dao.security.SysRoleResourceMapper;
import aystzh.com.study.entity.security.SysRoleResource;
import aystzh.com.study.service.SysRoleResourceService;
import aystzh.com.base.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by zhanghuan on 2020/05/03.
 */
@Service
@Transactional
public class SysRoleResourceServiceImpl extends AbstractService<SysRoleResource> implements SysRoleResourceService {
    @Resource
    private SysRoleResourceMapper sysRoleResourceMapper;

}
