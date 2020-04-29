package aystzh.com.study.config.security;

import aystzh.com.study.entity.security.SysMenu;
import aystzh.com.study.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * 动态权限数据源，用于获取动态权限规则
 * created by zhanghuan on 2020/4/20.
 */
@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    SysMenuService sysMenuService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        List<SysMenu> sysMenus = sysMenuService.findAllMenusWithRole();
        for (SysMenu sysMenu : sysMenus) {
        /*    if (antPathMatcher.match(sysMenu.getUrl(), requestUrl)) {
                List<SysRole> roles = sysMenu.getRoles();
                return SecurityConfig.createList(roles.stream().map(SysRole::getName).collect(Collectors.toList()).toArray(new String[roles.size()]));
            }*/
        }
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
