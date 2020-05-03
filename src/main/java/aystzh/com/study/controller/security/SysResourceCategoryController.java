package aystzh.com.study.controller.security;

import aystzh.com.base.response.ApiResponse;
import aystzh.com.base.response.WrapMapper;
import aystzh.com.base.response.Wrapper;
import aystzh.com.study.entity.security.SysResourceCategory;
import aystzh.com.study.service.SysResourceCategoryService;
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
@RequestMapping("/sys/resource/category")
public class SysResourceCategoryController {
    @Autowired
    private SysResourceCategoryService sysResourceCategoryService;

    @PostMapping
    public Wrapper add(@RequestBody SysResourceCategory sysResourceCategory) {
        sysResourceCategoryService.save(sysResourceCategory);
        return WrapMapper.ok();
    }

    @DeleteMapping("/{id}")
    public Wrapper delete(@PathVariable Integer id) {
        sysResourceCategoryService.deleteById(id);
        return WrapMapper.ok();
    }

    @PutMapping
    public Wrapper update(@RequestBody SysResourceCategory sysResourceCategory) {
        Assert.notNull(sysResourceCategory.getId(),"id cant be null");
        sysResourceCategoryService.update(sysResourceCategory);
        return WrapMapper.ok();
    }

    @GetMapping("/{id}")
    public Wrapper detail(@PathVariable Integer id) {
        SysResourceCategory sysResourceCategory = sysResourceCategoryService.findById(id);
        return WrapMapper.ok().result(sysResourceCategory);
    }

    @GetMapping
    public Wrapper list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SysResourceCategory> list = sysResourceCategoryService.findAll();
        PageInfo<SysResourceCategory> pageInfo = new PageInfo<>(list);
        ApiResponse<SysResourceCategory> apiResponse = new ApiResponse<>(pageInfo.getPages(), pageInfo.getPageSize(), pageInfo.getList(), pageInfo.getTotal());
        return WrapMapper.ok().result(apiResponse);
    }
}
