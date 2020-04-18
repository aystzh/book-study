package mybatis;

import aystzh.com.study.entity.Author;
import aystzh.com.study.service.AuthorService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * created by zhanghuan on 2020/4/18.
 */
public class MybatisTest {


    @Autowired
    private AuthorService authorService;

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
}
