package aystzh.com.study.controller.security;

import aystzh.com.base.response.ApiResponse;
import aystzh.com.base.response.WrapMapper;
import aystzh.com.base.response.Wrapper;
import aystzh.com.study.entity.security.SysMenuRole;
import aystzh.com.study.service.SysMenuRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* Created by zhanghuan on 2020/04/19.
*/
@RestController
@RequestMapping("/sys/menu/role")
public class SysMenuRoleController {
    @Autowired
    private SysMenuRoleService sysMenuRoleService;

    @PostMapping
    public Wrapper add(@RequestBody SysMenuRole sysMenuRole) {
        sysMenuRoleService.save(sysMenuRole);
        return WrapMapper.ok();
    }

    @DeleteMapping("/{id}")
    public Wrapper delete(@PathVariable Integer id) {
        sysMenuRoleService.deleteById(id);
        return WrapMapper.ok();
    }

    @PutMapping
    public Wrapper update(@RequestBody SysMenuRole sysMenuRole) {
        Assert.notNull(sysMenuRole.getId(),"id cant be null");
        sysMenuRoleService.update(sysMenuRole);
        return WrapMapper.ok();
    }

    @GetMapping("/{id}")
    public Wrapper detail(@PathVariable Integer id) {
        SysMenuRole sysMenuRole = sysMenuRoleService.findById(id);
        return WrapMapper.ok().result(sysMenuRole);
    }

    @GetMapping
    public Wrapper list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SysMenuRole> list = sysMenuRoleService.findAll();
        PageInfo<SysMenuRole> pageInfo = new PageInfo<>(list);
        ApiResponse<SysMenuRole> apiResponse = new ApiResponse<>(pageInfo.getPages(), pageInfo.getPageSize(), pageInfo.getList(), pageInfo.getTotal());
        return WrapMapper.ok().result(apiResponse);
    }
}
