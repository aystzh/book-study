package aystzh.com.study.service.impl;

import aystzh.com.study.dao.security.SysAdminMapper;
import aystzh.com.study.entity.security.SysAdmin;
import aystzh.com.study.service.SysAdminService;
import aystzh.com.base.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by zhanghuan on 2020/04/19.
 */
@Service
@Transactional
public class SysAdminServiceImpl extends AbstractService<SysAdmin> implements SysAdminService {
    @Resource
    private SysAdminMapper sysAdminMapper;

}
