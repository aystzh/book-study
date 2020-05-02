package aystzh.com.study.service.impl;

import aystzh.com.study.dao.security.SysPermissionMapper;
import aystzh.com.study.entity.security.SysPermission;
import aystzh.com.study.service.SysPermissionService;
import aystzh.com.base.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by zhanghuan on 2020/05/02.
 */
@Service
@Transactional
public class SysPermissionServiceImpl extends AbstractService<SysPermission> implements SysPermissionService {
    @Resource
    private SysPermissionMapper sysPermissionMapper;

}
