package aystzh.com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by zhanghuan on 2020/2/23.
 */
@SpringBootApplication
@ComponentScan("aystzh.com")
@MapperScan(basePackages = {"aystzh.com.study.dao"})
@EnableScheduling
public class BookStudyApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BookStudyApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(BookStudyApplication.class, args);
    }
}
