package mybatis;

import aystzh.com.BookStudyApplication;
import aystzh.com.study.dao.security.SysAdminMapper;
import aystzh.com.study.dao.security.SysAdminRoleMapper;
import aystzh.com.study.dao.tmp.HrMapper;
import aystzh.com.study.dao.tmp.HrRoleMapper;
import aystzh.com.study.entity.Author;
import aystzh.com.study.entity.security.SysAdmin;
import aystzh.com.study.entity.security.SysAdminRole;
import aystzh.com.study.entity.tmp.Hr;
import aystzh.com.study.entity.tmp.HrRole;
import aystzh.com.study.service.AuthorService;
import aystzh.com.study.utils.BeanMapping;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

/**
 * created by zhanghuan on 2020/4/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BookStudyApplication.class)
public class MybatisTest {


    @Autowired
    private AuthorService authorService;
    @Resource
    private HrMapper hrMapper;

    @Resource
    private SysAdminMapper sysAdminMapper;

    @Resource
    private HrRoleMapper hrRoleMapper;

    @Resource
    private SysAdminRoleMapper sysAdminRoleMapper;

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
    public void testGenerateData() throws Exception {
       /* List<Hr> hrs = hrMapper.selectAll();
        for (Hr hr : hrs) {
            SysAdmin sysAdmin = BeanMapping.map(hr, SysAdmin.class);
            System.out.println(sysAdmin);
            sysAdmin.setCreator(1);
            sysAdmin.setModifier(1);
            sysAdminMapper.insertSelective(sysAdmin);
        }*/

        List<HrRole> hrRoles = hrRoleMapper.selectAll();
        List<SysAdminRole> sysAdminRoles =  Lists.newArrayListWithCapacity(hrRoles.size());
        for (HrRole hrRole : hrRoles) {
            SysAdminRole sysAdminRole = new SysAdminRole();
            sysAdminRole.setAdminId(hrRole.getHrid());
            sysAdminRole.setRoleId(hrRole.getRid());
            sysAdminRole.setModifier(1);
            sysAdminRole.setCreator(1);
            sysAdminRole.setId(hrRole.getId());
            sysAdminRoles.add(sysAdminRole);
            sysAdminRoleMapper.insertSelective(sysAdminRole);
        }
        System.out.println(sysAdminRoles);
        sysAdminRoleMapper.insertList(sysAdminRoles);
    }
}
