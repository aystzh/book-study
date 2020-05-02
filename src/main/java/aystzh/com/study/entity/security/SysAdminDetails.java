package aystzh.com.study.entity.security;

import com.google.common.collect.Lists;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class SysAdminDetails implements UserDetails {
    private SysAdmin sysAdmin;

    private List<SysRole> roles;

    public SysAdminDetails(SysAdmin sysAdmin, List<SysRole> roles) {
        this.sysAdmin = sysAdmin;
        this.roles = roles;
    }

    public SysAdmin getSysAdmin() {
        return sysAdmin;
    }

    public void setSysAdmin(SysAdmin sysAdmin) {
        this.sysAdmin = sysAdmin;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return sysAdmin.getEnabled();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> objects = Lists.newArrayListWithCapacity(roles.size());
        for (SysRole sysRole : roles) {
            objects.add(new SimpleGrantedAuthority(sysRole.getName()));
        }
        return objects;
    }

    @Override
    public String getPassword() {
        return sysAdmin.getPassword();
    }

    @Override
    public String getUsername() {
        return sysAdmin.getUsername();
    }

    @Override
    public String toString() {
        return "SysAdminDetails{" +
                "sysAdmin=" + sysAdmin +
                ", roles=" + roles +
                '}';
    }
}
