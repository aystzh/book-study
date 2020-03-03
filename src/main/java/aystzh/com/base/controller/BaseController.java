package aystzh.com.base.controller;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by zhanghuan on 2019/9/18.
 */
public abstract class BaseController {
    /**
     * 获取当前请求request对象
     *
     * @return HttpServletRequest
     */
    protected HttpServletRequest getHttpServletRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes())
                .getRequest();
        return request;
    }

    /**
     * 获取当前请求session
     *
     * @return HttpSession
     */
    protected HttpSession getHttpSession() {
        return getHttpServletRequest().getSession();
    }

    protected String replaceBlank(String keywords) {
        return keywords.replaceAll("\\s*", "");
    }

    protected String stringFilter(String str) throws PatternSyntaxException {
        // 只允许字母和数字
        // String   regEx  =  "[^a-zA-Z0-9]";

        // 清除掉所有特殊字符
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    /**
     * 获取token
     * @param request request上下文
     * @return token
     */
    protected String getUserToken(HttpServletRequest request) {
        return getHttpServletRequest().getHeader("Authorization");
    }
}
