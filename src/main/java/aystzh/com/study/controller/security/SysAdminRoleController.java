package aystzh.com.study.controller.security;

import aystzh.com.base.response.ApiResponse;
import aystzh.com.base.response.WrapMapper;
import aystzh.com.base.response.Wrapper;
import aystzh.com.study.entity.security.SysAdminRole;
import aystzh.com.study.service.SysAdminRoleService;
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
@RequestMapping("/sys/admin/role")
public class SysAdminRoleController {
    @Autowired
    private SysAdminRoleService sysAdminRoleService;

    @PostMapping
    public Wrapper add(@RequestBody SysAdminRole sysAdminRole) {
        sysAdminRoleService.save(sysAdminRole);
        return WrapMapper.ok();
    }

    @DeleteMapping("/{id}")
    public Wrapper delete(@PathVariable Integer id) {
        sysAdminRoleService.deleteById(id);
        return WrapMapper.ok();
    }

    @PutMapping
    public Wrapper update(@RequestBody SysAdminRole sysAdminRole) {
        Assert.notNull(sysAdminRole.getId(),"id cant be null");
        sysAdminRoleService.update(sysAdminRole);
        return WrapMapper.ok();
    }

    @GetMapping("/{id}")
    public Wrapper detail(@PathVariable Integer id) {
        SysAdminRole sysAdminRole = sysAdminRoleService.findById(id);
        return WrapMapper.ok().result(sysAdminRole);
    }

    @GetMapping
    public Wrapper list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SysAdminRole> list = sysAdminRoleService.findAll();
        PageInfo<SysAdminRole> pageInfo = new PageInfo<>(list);
        ApiResponse<SysAdminRole> apiResponse = new ApiResponse<>(pageInfo.getPages(), pageInfo.getPageSize(), pageInfo.getList(), pageInfo.getTotal());
        return WrapMapper.ok().result(apiResponse);
    }
}
