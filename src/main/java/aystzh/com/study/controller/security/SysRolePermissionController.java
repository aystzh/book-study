package aystzh.com.study.controller.security;

import aystzh.com.base.response.ApiResponse;
import aystzh.com.base.response.WrapMapper;
import aystzh.com.base.response.Wrapper;
import aystzh.com.study.entity.security.SysRolePermission;
import aystzh.com.study.service.SysRolePermissionService;
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
@RequestMapping("/sys/role/permission")
public class SysRolePermissionController {
    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    @PostMapping
    public Wrapper add(@RequestBody SysRolePermission sysRolePermission) {
        sysRolePermissionService.save(sysRolePermission);
        return WrapMapper.ok();
    }

    @DeleteMapping("/{id}")
    public Wrapper delete(@PathVariable Integer id) {
        sysRolePermissionService.deleteById(id);
        return WrapMapper.ok();
    }

    @PutMapping
    public Wrapper update(@RequestBody SysRolePermission sysRolePermission) {
        Assert.notNull(sysRolePermission.getId(),"id cant be null");
        sysRolePermissionService.update(sysRolePermission);
        return WrapMapper.ok();
    }

    @GetMapping("/{id}")
    public Wrapper detail(@PathVariable Integer id) {
        SysRolePermission sysRolePermission = sysRolePermissionService.findById(id);
        return WrapMapper.ok().result(sysRolePermission);
    }

    @GetMapping
    public Wrapper list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SysRolePermission> list = sysRolePermissionService.findAll();
        PageInfo<SysRolePermission> pageInfo = new PageInfo<>(list);
        ApiResponse<SysRolePermission> apiResponse = new ApiResponse<>(pageInfo.getPages(), pageInfo.getPageSize(), pageInfo.getList(), pageInfo.getTotal());
        return WrapMapper.ok().result(apiResponse);
    }
}
