package aystzh.com.study.config.security.compoment;

import aystzh.com.study.service.DynamicSecurityService;
import cn.hutool.core.util.URLUtil;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 动态权限数据源，用于获取动态权限规则
 * created by zhanghuan on 2020/4/20.
 */
public class DynamicSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private static Map<String, ConfigAttribute> configAttributeMap = null;
    @Autowired
    private DynamicSecurityService dynamicSecurityService;

    @PostConstruct
    public void loadDataSource() {
        configAttributeMap = dynamicSecurityService.loadDataSource();
    }

    public void clearDataSource() {
        configAttributeMap.clear();
        configAttributeMap = null;
    }


    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if (Objects.isNull(configAttributeMap)) {
            this.loadDataSource();
        }
        List<ConfigAttribute> configAttributeLists = Lists.newArrayList();
        String url = ((FilterInvocation) object).getRequestUrl();
        String path = URLUtil.getPath(url);
        PathMatcher pathMatcher = new AntPathMatcher();
        for (String pattern : configAttributeMap.keySet()) {
            if (pathMatcher.match(pattern, path)) {
                configAttributeLists.add(configAttributeMap.get(pattern));
            }
        }
        return configAttributeLists;
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
