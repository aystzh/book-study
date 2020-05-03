package aystzh.com.study.controller.security;

import aystzh.com.base.response.ApiResponse;
import aystzh.com.base.response.WrapMapper;
import aystzh.com.base.response.Wrapper;
import aystzh.com.study.entity.security.SysPermission;
import aystzh.com.study.service.SysPermissionService;
import lombok.extern.log4j.Log4j2;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* Created by zhanghuan on 2020/05/02.
*/
@Log4j2
@RestController
@RequestMapping("/sys/permission")
public class SysPermissionController {
    @Autowired
    private SysPermissionService sysPermissionService;

    @PostMapping
    public Wrapper add(@RequestBody SysPermission sysPermission) {
        sysPermissionService.save(sysPermission);
        return WrapMapper.ok();
    }

    @DeleteMapping("/{id}")
    public Wrapper delete(@PathVariable Integer id) {
        sysPermissionService.deleteById(id);
        return WrapMapper.ok();
    }

    @PutMapping
    public Wrapper update(@RequestBody SysPermission sysPermission) {
        Assert.notNull(sysPermission.getId(),"id cant be null");
        sysPermissionService.update(sysPermission);
        return WrapMapper.ok();
    }

    @GetMapping("/{id}")
    public Wrapper detail(@PathVariable Integer id) {
        SysPermission sysPermission = sysPermissionService.findById(id);
        return WrapMapper.ok().result(sysPermission);
    }

    @GetMapping
    public Wrapper list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SysPermission> list = sysPermissionService.findAll();
        PageInfo<SysPermission> pageInfo = new PageInfo<>(list);
        ApiResponse<SysPermission> apiResponse = new ApiResponse<>(pageInfo.getPages(), pageInfo.getPageSize(), pageInfo.getList(), pageInfo.getTotal());
        return WrapMapper.ok().result(apiResponse);
    }
}
