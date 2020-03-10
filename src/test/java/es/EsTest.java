package es;

import aystzh.com.BookStudyApplication;
import aystzh.com.study.entity.index.AuthorIndexEntity;
import aystzh.com.study.repository.AuthorIndexRepository;
import aystzh.com.study.utils.BeanMapping;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhanghuan on 2020/2/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BookStudyApplication.class)
public class EsTest {

    @Resource
    private AuthorIndexRepository authorIndexRepository;

    /*@Resource
    private AuthorEntityMapper authorEntityMapper;

    @Test
    public void test() throws Exception {
        List<AuthorEntity> authorEntity = authorEntityMapper.selectAll();
        List<AuthorIndexEntity> authorIndexEntity = BeanMapping.mapList(authorEntity, AuthorIndexEntity.class);
        Iterable<AuthorIndexEntity> authorIndexEntities = authorIndexRepository.saveAll(authorIndexEntity);
        System.out.println(authorIndexEntities);
    }

    @Test
    public void threadTest() throws Exception {
        TestThread work = new TestThread("work");
        work.doWork(new AuthorEntity());
        work.addTask(new AuthorEntity());
        work.addTask(Lists.newArrayList());
    }*/
}
