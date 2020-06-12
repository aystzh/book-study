package aystzh.com.study.config;

import aystzh.com.study.entity.Author;
import aystzh.com.study.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.List;

/**
 * Created by zhanghuan on 2020/6/10.
 */
@Component
public class MyFilter implements Filter {

    @Autowired
    private AuthorService authorService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter 初始化 init方法");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("filter 处理方法");
        List<Author> all = authorService.findAll();
        System.out.println(all);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("filter 销毁 destroy方法");
    }
}
