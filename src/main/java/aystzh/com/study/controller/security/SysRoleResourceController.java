package aystzh.com.study.controller.security;

import aystzh.com.base.response.ApiResponse;
import aystzh.com.base.response.WrapMapper;
import aystzh.com.base.response.Wrapper;
import aystzh.com.study.entity.security.SysRoleResource;
import aystzh.com.study.service.SysRoleResourceService;
import lombok.extern.log4j.Log4j2;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* Created by zhanghuan on 2020/05/03.
*/
@Log4j2
@RestController
@RequestMapping("/sys/role/resource")
public class SysRoleResourceController {
    @Autowired
    private SysRoleResourceService sysRoleResourceService;

    @PostMapping
    public Wrapper add(@RequestBody SysRoleResource sysRoleResource) {
        sysRoleResourceService.save(sysRoleResource);
        return WrapMapper.ok();
    }

    @DeleteMapping("/{id}")
    public Wrapper delete(@PathVariable Integer id) {
        sysRoleResourceService.deleteById(id);
        return WrapMapper.ok();
    }

    @PutMapping
    public Wrapper update(@RequestBody SysRoleResource sysRoleResource) {
        Assert.notNull(sysRoleResource.getId(),"id cant be null");
        sysRoleResourceService.update(sysRoleResource);
        return WrapMapper.ok();
    }

    @GetMapping("/{id}")
    public Wrapper detail(@PathVariable Integer id) {
        SysRoleResource sysRoleResource = sysRoleResourceService.findById(id);
        return WrapMapper.ok().result(sysRoleResource);
    }

    @GetMapping
    public Wrapper list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SysRoleResource> list = sysRoleResourceService.findAll();
        PageInfo<SysRoleResource> pageInfo = new PageInfo<>(list);
        ApiResponse<SysRoleResource> apiResponse = new ApiResponse<>(pageInfo.getPages(), pageInfo.getPageSize(), pageInfo.getList(), pageInfo.getTotal());
        return WrapMapper.ok().result(apiResponse);
    }
}
