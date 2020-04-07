package java8;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;

/**
 * java8 comparator语法学习
 * created by zhanghuan on 2020/4/6.
 */

public class ComparatorTest {
    @Test
    public void testLambdaComparator() {
        List<Human> humans = Lists.newArrayList(new Human("zhangsan",12),new Human("wangwu",33));

        humans.sort((Human h,Human s)->h.getName().compareTo(s.getName()));
        Assert.assertThat(humans.get(0), equalTo(new Human("zhangsan", 12)));
    }

}
