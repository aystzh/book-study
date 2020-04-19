package aystzh.com.study.controller.security;

import aystzh.com.base.response.ApiResponse;
import aystzh.com.base.response.WrapMapper;
import aystzh.com.base.response.Wrapper;
import aystzh.com.study.entity.security.SysAdmin;
import aystzh.com.study.service.SysAdminService;
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
@RequestMapping("/sys/admin")
public class SysAdminController {
    @Autowired
    private SysAdminService sysAdminService;

    @PostMapping
    public Wrapper add(@RequestBody SysAdmin sysAdmin) {
        sysAdminService.save(sysAdmin);
        return WrapMapper.ok();
    }

    @DeleteMapping("/{id}")
    public Wrapper delete(@PathVariable Integer id) {
        sysAdminService.deleteById(id);
        return WrapMapper.ok();
    }

    @PutMapping
    public Wrapper update(@RequestBody SysAdmin sysAdmin) {
        Assert.notNull(sysAdmin.getId(),"id cant be null");
        sysAdminService.update(sysAdmin);
        return WrapMapper.ok();
    }

    @GetMapping("/{id}")
    public Wrapper detail(@PathVariable Integer id) {
        SysAdmin sysAdmin = sysAdminService.findById(id);
        return WrapMapper.ok().result(sysAdmin);
    }

    @GetMapping
    public Wrapper list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SysAdmin> list = sysAdminService.findAll();
        PageInfo<SysAdmin> pageInfo = new PageInfo<>(list);
        ApiResponse<SysAdmin> apiResponse = new ApiResponse<>(pageInfo.getPages(), pageInfo.getPageSize(), pageInfo.getList(), pageInfo.getTotal());
        return WrapMapper.ok().result(apiResponse);
    }
}
