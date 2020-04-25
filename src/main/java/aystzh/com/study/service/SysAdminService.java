package aystzh.com.study.service;

import aystzh.com.base.core.Service;
import aystzh.com.study.entity.security.SysAdmin;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


/**
 * Created by zhanghuan on 2020/04/19.
 */
public interface SysAdminService  extends Service<SysAdmin> {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
