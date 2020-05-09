package aystzh.com.study.service.impl;

import aystzh.com.study.entity.security.SysResource;
import aystzh.com.study.service.DynamicSecurityService;
import aystzh.com.study.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 动态权限配置相关
 * Created by zhanghuan on 2020/5/9.
 */
@Service
public class DynamicSecurityServiceImpl implements DynamicSecurityService {

    @Autowired
    private SysResourceService sysResourceService;

    /**
     * 加载所有资源
     * @return map
     */
    @Override
    public Map<String, ConfigAttribute> loadDataSource() {
        Map<String, ConfigAttribute> map = new ConcurrentHashMap<>();
        List<SysResource> resourceList = sysResourceService.findAll();
        for (SysResource sysResource : resourceList) {
            map.put(sysResource.getUrl(),new org.springframework.security.access.SecurityConfig(sysResource.getId()+":"+sysResource.getName()));
        }
        return map;
    }
}
