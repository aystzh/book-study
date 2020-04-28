package aystzh.com.study.service.impl;

import aystzh.com.study.dao.security.SysMenuMapper;
import aystzh.com.study.entity.security.SysMenu;
import aystzh.com.study.service.SysMenuService;
import aystzh.com.base.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by zhanghuan on 2020/04/19.
 */
@Service
@Transactional
public class SysMenuServiceImpl extends AbstractService<SysMenu> implements SysMenuService {
    @Resource
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> findAllMenusWithRole() {
        return sysMenuMapper.selectAllMenusWithRole();
    }

    @Override
    public List<SysMenu> findMenusByAdminId(Integer adminId) {

        return null;
    }
}
