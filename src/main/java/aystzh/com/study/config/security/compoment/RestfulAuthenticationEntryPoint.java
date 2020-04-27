package aystzh.com.study.config.security.compoment;

import aystzh.com.base.response.WrapMapper;
import aystzh.com.base.response.Wrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpStatus;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by zhanghuan on 2020/4/27.
 */
public class RestfulAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpStatus.SC_UNAUTHORIZED);
        PrintWriter out = response.getWriter();
        Wrapper<Object> objectWrapper = WrapMapper.error().message("访问失败");
        if (authException instanceof InsufficientAuthenticationException) {
            objectWrapper.setMessage("认证失败，请联系管理员!");
        }
        out.write(new ObjectMapper().writeValueAsString(objectWrapper));
        out.flush();
        out.close();
    }
}
