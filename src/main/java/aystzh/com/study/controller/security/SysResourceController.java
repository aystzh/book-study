package aystzh.com.study.controller.security;

import aystzh.com.base.response.ApiResponse;
import aystzh.com.base.response.WrapMapper;
import aystzh.com.base.response.Wrapper;
import aystzh.com.study.entity.security.SysResource;
import aystzh.com.study.service.SysResourceService;
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
@RequestMapping("/sys/resource")
public class SysResourceController {
    @Autowired
    private SysResourceService sysResourceService;

    @PostMapping
    public Wrapper add(@RequestBody SysResource sysResource) {
        sysResourceService.save(sysResource);
        return WrapMapper.ok();
    }

    @DeleteMapping("/{id}")
    public Wrapper delete(@PathVariable Integer id) {
        sysResourceService.deleteById(id);
        return WrapMapper.ok();
    }

    @PutMapping
    public Wrapper update(@RequestBody SysResource sysResource) {
        Assert.notNull(sysResource.getId(),"id cant be null");
        sysResourceService.update(sysResource);
        return WrapMapper.ok();
    }

    @GetMapping("/{id}")
    public Wrapper detail(@PathVariable Integer id) {
        SysResource sysResource = sysResourceService.findById(id);
        return WrapMapper.ok().result(sysResource);
    }

    @GetMapping
    public Wrapper list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SysResource> list = sysResourceService.findAll();
        PageInfo<SysResource> pageInfo = new PageInfo<>(list);
        ApiResponse<SysResource> apiResponse = new ApiResponse<>(pageInfo.getPages(), pageInfo.getPageSize(), pageInfo.getList(), pageInfo.getTotal());
        return WrapMapper.ok().result(apiResponse);
    }
}
