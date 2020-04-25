package mybatis;

import aystzh.com.BookStudyApplication;
import aystzh.com.study.entity.Author;
import aystzh.com.study.entity.security.SysMenu;
import aystzh.com.study.service.AuthorService;
import aystzh.com.study.service.SysMenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * created by zhanghuan on 2020/4/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BookStudyApplication.class)
public class MybatisTest {


    @Autowired
    private AuthorService authorService;
    @Autowired
    private SysMenuService sysMenuService;

    @Test
    public void testAuthor() {
        Condition condition = new Condition(Author.class);
        Example.Criteria criteria = condition.createCriteria();
        Example example = new Example(Author.class);
        Example.builder(Author.class).orderBy();
        criteria.andLike("name", "贝吉");
        List<Author> authors = authorService.findByCondition(condition);
        System.out.println(authors);
    }

    @Test
    public void testSecurity() {
        List<SysMenu> allMenusWithRole = sysMenuService.findAllMenusWithRole();
        System.out.println(allMenusWithRole);
    }
}
