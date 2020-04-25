package aystzh.com.study.service.impl;

import aystzh.com.base.core.AbstractService;
import aystzh.com.study.dao.security.SysAdminMapper;
import aystzh.com.study.entity.security.SysAdmin;
import aystzh.com.study.entity.security.SysAdminDetails;
import aystzh.com.study.entity.security.SysRole;
import aystzh.com.study.service.SysAdminService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;


/**
 * Created by zhanghuan on 2020/04/19.
 */
@Service
@Transactional
public class SysAdminServiceImpl extends AbstractService<SysAdmin> implements SysAdminService {
    @Resource
    private SysAdminMapper sysAdminMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Example example = new Example(SysAdmin.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", username);
        SysAdmin sysAdmin = sysAdminMapper.selectOneByExample(example);
        if (Objects.isNull(sysAdmin)) {
            throw new UsernameNotFoundException("用户未找到");
        }
        Integer id = sysAdmin.getId();
        List<SysRole> sysRoles = sysAdminMapper.selectHrRolesById(id);
        return new SysAdminDetails(sysAdmin, sysRoles);
    }
}
