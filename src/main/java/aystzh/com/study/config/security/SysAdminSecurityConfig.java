package aystzh.com.study.config.security;

import aystzh.com.study.entity.security.SysResource;
import aystzh.com.study.service.SysAdminService;
import aystzh.com.study.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SysAdminSecurityConfig extends SecurityConfig {

    @Autowired
    private SysAdminService sysAdminService;

    @Autowired
    private SysResourceService sysResourceService;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> sysAdminService.loadUserByUsername(username);
    }


    @Bean
    public DynamicSecurityService dynamicSecurityService() {
        return new DynamicSecurityService() {
            @Override
            public Map<String, ConfigAttribute> loadDataSource() {
                Map<String, ConfigAttribute> map = new ConcurrentHashMap<>();
                List<SysResource> resourceList = sysResourceService.findAll();
                for (SysResource sysResource : resourceList) {
                    map.put(sysResource.getUrl(), new org.springframework.security.access.SecurityConfig(sysResource.getId() + ":" + sysResource.getName()));
                }
                return map;
            }
        };
    }
}
