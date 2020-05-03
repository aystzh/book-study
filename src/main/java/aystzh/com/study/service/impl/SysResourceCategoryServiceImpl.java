package aystzh.com.study.service.impl;

import aystzh.com.study.dao.security.SysResourceCategoryMapper;
import aystzh.com.study.entity.security.SysResourceCategory;
import aystzh.com.study.service.SysResourceCategoryService;
import aystzh.com.base.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by zhanghuan on 2020/05/03.
 */
@Service
@Transactional
public class SysResourceCategoryServiceImpl extends AbstractService<SysResourceCategory> implements SysResourceCategoryService {
    @Resource
    private SysResourceCategoryMapper sysResourceCategoryMapper;

}
