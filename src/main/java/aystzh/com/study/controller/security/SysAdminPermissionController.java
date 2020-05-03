package aystzh.com.study.controller.security;

import aystzh.com.base.response.ApiResponse;
import aystzh.com.base.response.WrapMapper;
import aystzh.com.base.response.Wrapper;
import aystzh.com.study.entity.security.SysAdminPermission;
import aystzh.com.study.service.SysAdminPermissionService;
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
@RequestMapping("/sys/admin/permission")
public class SysAdminPermissionController {
    @Autowired
    private SysAdminPermissionService sysAdminPermissionService;

    @PostMapping
    public Wrapper add(@RequestBody SysAdminPermission sysAdminPermission) {
        sysAdminPermissionService.save(sysAdminPermission);
        return WrapMapper.ok();
    }

    @DeleteMapping("/{id}")
    public Wrapper delete(@PathVariable Integer id) {
        sysAdminPermissionService.deleteById(id);
        return WrapMapper.ok();
    }

    @PutMapping
    public Wrapper update(@RequestBody SysAdminPermission sysAdminPermission) {
        Assert.notNull(sysAdminPermission.getId(),"id cant be null");
        sysAdminPermissionService.update(sysAdminPermission);
        return WrapMapper.ok();
    }

    @GetMapping("/{id}")
    public Wrapper detail(@PathVariable Integer id) {
        SysAdminPermission sysAdminPermission = sysAdminPermissionService.findById(id);
        return WrapMapper.ok().result(sysAdminPermission);
    }

    @GetMapping
    public Wrapper list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SysAdminPermission> list = sysAdminPermissionService.findAll();
        PageInfo<SysAdminPermission> pageInfo = new PageInfo<>(list);
        ApiResponse<SysAdminPermission> apiResponse = new ApiResponse<>(pageInfo.getPages(), pageInfo.getPageSize(), pageInfo.getList(), pageInfo.getTotal());
        return WrapMapper.ok().result(apiResponse);
    }
}
