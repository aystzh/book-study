package aystzh.com.base.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;

/**
 * 封装API响应对象供自定义查询使用
 * 支持分页
 * Created by zhanghuan on 2019/9/4.
 */
public class ScrollResponse<E> implements Serializable {

    private static final long serialVersionUID = 2490364023692403771L;
    protected final transient Logger logger = LoggerFactory.getLogger(this.getClass());

    /* 回写请求时的分页设置 */
    private String scrollId;
    private List<E> datas;
    private String md5;

    public ScrollResponse(String scrollId, List<E> datas) {
        this.scrollId = scrollId;
        this.datas = datas;
    }

    public String getScrollId() {
        return scrollId;
    }

    public void setScrollId(String scrollId) {
        this.scrollId = scrollId;
    }

    public List<E> getDatas() {
        return datas;
    }

    public void setDatas(List<E> datas) {
        this.datas = datas;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
}
