package aystzh.com.study.controller.security;

import aystzh.com.base.response.ApiResponse;
import aystzh.com.base.response.WrapMapper;
import aystzh.com.base.response.Wrapper;
import aystzh.com.study.entity.security.SysAdmin;
import aystzh.com.study.service.SysAdminService;
import aystzh.com.study.utils.VerificationCode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

/**
 * Created by zhanghuan on 2020/04/19.
 */
@Log4j2
@RestController
@RequestMapping("/sys/admin")
public class SysAdminController {
    @Autowired
    private SysAdminService sysAdminService;

    @ApiOperation(value = "登录接口",notes = "登录")
    @GetMapping("/login")
    public Wrapper login() {
        //从threadLocal当中获取用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return WrapMapper.error().message("尚未登录，请登录");
    }

    @ApiOperation(value = "获取验证码接口",notes = "验证码接口")
    @GetMapping("/verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        VerificationCode verificationCode = new VerificationCode();
        BufferedImage image = verificationCode.getImage();
        String text = verificationCode.getText();
        request.getSession(true).setAttribute("verify_code", text);
        log.info("验证码是：{}", text);
        VerificationCode.output(image, response.getOutputStream());
    }

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
        Assert.notNull(sysAdmin.getId(), "id cant be null");
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
