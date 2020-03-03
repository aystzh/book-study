package aystzh.com.study.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanghuan on 2019/12/5.
 */
public class HighlighterUtil {

    // 将字符串插入需要修饰的代码。
    public static String highLighterString(String title, List<Integer> iList) {
        int one = iList.get(0);
        int forint = 0;
        List<List<Integer>> Lists = new ArrayList<List<Integer>>();
        List<Integer> listIndex = new ArrayList<Integer>();
        for (Integer integer : iList) {
            if (integer == one) {
                one = integer + 1;
                listIndex.add(integer);
            } else {
                Lists.add(listIndex);
                listIndex = new ArrayList<Integer>();
                listIndex.add(integer);
                one = integer + 1;
            }
            forint = forint + 1;
        }
        Lists.add(listIndex);
        return highLighterinsert(Lists, title);
    }

    public static String highLighterinsert(List<List<Integer>> Lists, String title) {
        StringBuffer buffer = new StringBuffer(title);
        int bufsiza = 0;
        String startHtml = "<span style='color:red;background-color: yellow;'>";
        String endHtml = "</span>";
        for (List<Integer> list : Lists) {
            int start = list.get(0);
            buffer.insert(bufsiza + start, startHtml);
            bufsiza = bufsiza + startHtml.length();
            int end = list.get(list.size() - 1);
            buffer.insert(bufsiza + end + 1, endHtml);
            bufsiza = bufsiza + endHtml.length();
        }
        return buffer.toString();
    }

}
