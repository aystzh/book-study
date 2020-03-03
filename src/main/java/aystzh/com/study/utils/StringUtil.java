package aystzh.com.study.utils;

/**
 * 判断字符串是否为空、非空
 * 判断一组字符串是否全部为空、非空、包含空、包含非空
 * <code>Null</code>：没有指向对象
 * <code>Empty</code>：长度为0的字符串对象
 * <code>Blank</code>：仅有空格的字符串对象
 */
public class StringUtil {
    /**
     * 判断字符串是否为空
     *
     * @param s
     * @return
     */
    public static boolean isNull(String s) {
        return null == s;
    }

    /**
     * 判断字符串是否为非空
     *
     * @param s
     * @return
     */
    public static boolean isNotNull(String s) {
        return !isNull(s);
    }

    /**
     * 判断一组字符串是否全部为空
     *
     * @param ss
     * @return
     */
    public static boolean allIsNull(String... ss) {
        boolean f = true;
        for (String s : ss) {
            f = isNull(s);
            if (!f) {
                break;
            }
        }
        return f;
    }

    /**
     * 判断一组字符串是否全部为非空
     *
     * @param ss
     * @return
     */
    public static boolean allIsNotNull(String... ss) {
        boolean f = true;
        for (String s : ss) {
            f = isNotNull(s);
            if (!f) {
                break;
            }
        }
        return f;
    }

    /**
     * 判断一组字符串是否包含空字符串
     *
     * @param ss
     * @return
     */
    public static boolean hasNull(String... ss) {
        return !allIsNotNull(ss);
    }

    /**
     * 判断一组字符串是否包含非空字符串
     *
     * @param ss
     * @return
     */
    public static boolean hasNotNull(String... ss) {
        return !allIsNull(ss);
    }

    /**
     * 判断字符串是否为空
     *
     * @param s
     * @return
     */
    public static boolean isEmpty(String s) {
        return isNull(s) || "".equals(s);
    }

    /**
     * 判断字符串是否为非空
     *
     * @param s
     * @return
     */
    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }


    /**
     * 判断一组字符串是否全部为空
     *
     * @param ss
     * @return
     */
    public static boolean allIsEmpty(String... ss) {
        boolean f = true;
        for (String s : ss) {
            f = isEmpty(s);
            if (!f) {
                break;
            }
        }
        return f;
    }

    /**
     * 判断一组字符串是否全部为非空
     *
     * @param ss
     * @return
     */
    public static boolean allIsNotEmpty(String... ss) {
        boolean f = true;
        for (String s : ss) {
            f = isNotEmpty(s);
            if (!f) {
                break;
            }
        }
        return f;
    }

    /**
     * 判断一组字符串是否包含空字符串
     *
     * @param ss
     * @return
     */
    public static boolean hasEmpty(String... ss) {
        return !allIsNotEmpty(ss);
    }

    /**
     * 判断一组字符串是否包含非空字符串
     *
     * @param ss
     * @return
     */
    public static boolean hasNotEmpty(String... ss) {
        return !allIsEmpty(ss);
    }

    /**
     * 判断字符串是否为空
     *
     * @param s
     * @return
     */
    public static boolean isBlank(String s) {
        return isEmpty(s) || "".equals(s.trim());
    }

    /**
     * 判断字符串是否为非空
     *
     * @param s
     * @return
     */
    public static boolean isNotBlank(String s) {
        return !isBlank(s);
    }

    /**
     * 判断一组字符串是否全部为空
     *
     * @param ss
     * @return
     */
    public static boolean allIsBlank(String... ss) {
        boolean f = true;
        for (String s : ss) {
            f = isBlank(s);
            if (!f) {
                break;
            }
        }
        return f;
    }

    /**
     * 判断一组字符串是否全部为非空
     *
     * @param ss
     * @return
     */
    public static boolean allIsNotBlank(String... ss) {
        boolean f = true;
        for (String s : ss) {
            f = isNotBlank(s);
            if (!f) {
                break;
            }
        }
        return f;
    }

    /**
     * 判断一组字符串是否包含空字符串
     *
     * @param ss
     * @return
     */
    public static boolean hasBlank(String... ss) {
        return !allIsNotBlank(ss);
    }

    /**
     * 判断一组字符串是否包含非空字符串
     *
     * @param ss
     * @return
     */
    public static boolean hasNotBlank(String... ss) {
        return !allIsBlank(ss);
    }

    /**
     * 判断字符串数组是否包含某个字符
     */
    public static boolean isHave(String[] strs, String s) {
        if (null != strs) {
            for (int i = 0; i < strs.length; i++) {
                if (strs[i].indexOf(s) != -1) {//循环查找字符串数组中的每个字符串中是否包含所有查找的内容
                    return true;//查找到了就返回真，不在继续查询
                }
            }
        }
        return false;//没找到返回false
    }

    /**
     * 把字符创的首字母转换为大写
     */
    public static String getCapitalString(String fildeName) {
        char[] chars = fildeName.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        return new String(chars);
    }
//---------------------------------------------------------------------

    /**
     * " "针对空格不为空
     *
     * @param cs
     * @return
     */
    public static boolean isEmpty(CharSequence cs) {
        return (cs == null) || (cs.length() == 0);
    }

    public static boolean isNotEmpty(CharSequence cs) {
        return !isEmpty(cs);
    }

    /**
     * " "针对空格为空
     *
     * @param cs
     * @return
     */
    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if ((cs == null) || ((strLen = cs.length()) == 0)) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }

    public static String changeNullToEmptyStr(Object obj) {
        String returnStr;
        if (obj == null) {
            returnStr = "";
        } else {
            returnStr = obj.toString();
        }
        return returnStr;
    }


}
