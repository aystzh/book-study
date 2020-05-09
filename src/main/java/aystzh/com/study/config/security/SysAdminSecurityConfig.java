package aystzh.com.study.config.security;

import aystzh.com.study.service.DynamicSecurityService;
import aystzh.com.study.service.SysAdminService;
import aystzh.com.study.service.impl.DynamicSecurityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SysAdminSecurityConfig extends SecurityConfig {

    @Autowired
    private SysAdminService sysAdminService;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> sysAdminService.loadUserByUsername(username);
    }


    @Bean
    public DynamicSecurityService dynamicSecurityService(){
        return new DynamicSecurityServiceImpl();
    }
}
