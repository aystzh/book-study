package aystzh.com.study.service.impl;

import aystzh.com.study.dao.security.SysResourceMapper;
import aystzh.com.study.entity.security.SysResource;
import aystzh.com.study.service.SysResourceService;
import aystzh.com.base.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by zhanghuan on 2020/05/02.
 */
@Service
@Transactional
public class SysResourceServiceImpl extends AbstractService<SysResource> implements SysResourceService {
    @Resource
    private SysResourceMapper sysResourceMapper;

}
