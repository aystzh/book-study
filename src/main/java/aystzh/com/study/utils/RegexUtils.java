package aystzh.com.study.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * @author yilanqunzhi
 * @title: RegexUtils
 * @projectName richfit.search
 * @packageName com.elens.search.utils
 * @description: 正则表达式工具类
 * @date 2019/9/8 10:04
 */
public class RegexUtils {

    /**
     * 判断字符串是否为汉字
     *
     * @param str
     * @return
     */
    public static boolean isChinese(String str) {
        boolean flag = false;
        Pattern p = compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            flag = true;
        }
        return flag;
    }

    /**
     * 判断是否有特殊字符
     *
     * @param str
     * @Author:lz
     * @Date:11:21 PM 2020/2/20
     */
    public static boolean isSpecial(String str) {
        boolean flag = false;
        String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher("adsada_asdads");
        if (m.find()) {
            flag = true;
        }
        return flag;
    }


    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
    }

    /**
     * 判断字符串是否为乱码
     *
     * @param strName
     * @return
     */
    public static boolean isMessyCode(String strName) {
        Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");
        Matcher m = p.matcher(strName);
        String after = m.replaceAll("");
        String temp = after.replaceAll("\\p{P}", "");
        char[] ch = temp.trim().toCharArray();
        float chLength = ch.length;
        float count = 0;
        for (char c : ch) {
            if (!Character.isLetterOrDigit(c)) {
                if (!isChinese(c)) {
                    count = count + 1;
                    System.out.print(c);
                }
            }
        }
        float result = count / chLength;
        return result > 0.4;

    }

    public static void main(String[] args) {
        System.out.println(isMessyCode("*��JTP.jar�ļ����JTP�ļ���ȡ��ͼƬ��Դ"));
        System.out.println(isMessyCode("你好"));
        System.out.println(isMessyCode("123123adfasdfsa"));
        System.out.println(isMessyCode("123123a测试——@#￥%%dfasdfsa"));

        System.out.println(isMessyCode("*��JTP.jar�ļ����JTP�ļ���ȡ��ͼƬ��Դ"));
        System.out.println(isMessyCode("你好"));
        System.out.println(isMessyCode("123123adfasdfsa"));
        System.out.println(isMessyCode("123123a测试——@#￥%%dfasdfsa"));
    }

}
