package aystzh.com.study.config.security.compoment;

import aystzh.com.base.response.WrapMapper;
import aystzh.com.study.entity.security.SysAdminDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by zhanghuan on 2020/4/27.
 */
public class RestfulAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        SysAdminDetails sysAdminDetails = (SysAdminDetails) authentication.getPrincipal();
        sysAdminDetails.getSysAdmin().setPassword(null);
        String s = new ObjectMapper().writeValueAsString(WrapMapper.ok().message("登录成功"));
        out.write(s);
        out.flush();
        out.close();
    }
}
