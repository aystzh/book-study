package java8.override;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;

/**
 * Created by zhanghuan on 2020/6/26.
 */
public class ListAndSetTest {

    @Test
    public void overrideTest() {
        List<Integer> list = Lists.newArrayListWithCapacity(6);
        HashSet<Integer> sets = Sets.newHashSetWithExpectedSize(6);
        for (int i = -3; i < 3; i++) {//-3 -2 -1 0 1 2
            sets.add(i);
            list.add(i);
        }

        for (int i = 0; i < 3; i++) {// 0 1 2
            sets.remove(i);// -3 -2 -1
            list.remove(i);// 0 1 2
        }
        System.out.println(sets);//[-1, -2, -3]
        System.out.println(list);//[-2, 0, 2]
    }
}
