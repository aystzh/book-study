package aystzh.com.study.service.impl;

import aystzh.com.study.dao.security.SysAdminPermissionMapper;
import aystzh.com.study.entity.security.SysAdminPermission;
import aystzh.com.study.service.SysAdminPermissionService;
import aystzh.com.base.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by zhanghuan on 2020/05/03.
 */
@Service
@Transactional
public class SysAdminPermissionServiceImpl extends AbstractService<SysAdminPermission> implements SysAdminPermissionService {
    @Resource
    private SysAdminPermissionMapper sysAdminPermissionMapper;

}
