package aystzh.com.study.config;

import aystzh.com.study.entity.Author;
import aystzh.com.study.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by zhanghuan on 2020/6/10.
 */
@Component
public class MyInterceptor implements HandlerInterceptor {

    @Autowired
    private AuthorService authorService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("interceptor 前置通知");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        List<Author> all = authorService.findAll();
        System.out.println(all);
        System.out.println("interceptor处理当中");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        System.out.println("interceptor 后置通知");
    }

}
