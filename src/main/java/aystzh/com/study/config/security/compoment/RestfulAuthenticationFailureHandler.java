package aystzh.com.study.config.security.compoment;

import aystzh.com.base.response.WrapMapper;
import aystzh.com.base.response.Wrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by zhanghuan on 2020/4/27.
 */
public class RestfulAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        Wrapper<Object> wrapper = WrapMapper.error().message(exception.getMessage());
        if (exception instanceof LockedException) {
            wrapper.setMessage("账户被锁定，请联系管理员!");
        } else if (exception instanceof CredentialsExpiredException) {
            wrapper.setMessage("密码过期，请联系管理员!");
        } else if (exception instanceof AccountExpiredException) {
            wrapper.setMessage("账户过期，请联系管理员!");
        } else if (exception instanceof DisabledException) {
            wrapper.setMessage("账户被禁用，请联系管理员!");
        } else if (exception instanceof BadCredentialsException) {
            wrapper.setMessage("用户名或者密码输入错误，请重新输入!");
        }
        out.write(new ObjectMapper().writeValueAsString(wrapper));
        out.flush();
        out.close();
    }
}
