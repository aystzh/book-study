package aystzh.com.study.config.security;

import org.springframework.security.access.ConfigAttribute;

import java.util.Map;

/**
 * Created by zhanghuan on 2020/5/9.
 */
public interface DynamicSecurityService {
    /**
     * 加载资源ANT通配符和资源对应MAP
     */
    Map<String, ConfigAttribute> loadDataSource();
}
