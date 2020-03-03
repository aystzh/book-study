package aystzh.com.base.request;

import java.io.Serializable;


/**
 * Created by zhanghuan on 2019/9/4.
 */
public class EsHighLightSetting implements Serializable {

    private String field;

    private String preTag = "<span style='color:red;background-color: yellow;'>";

    private String postTag= "</span>";

    public EsHighLightSetting(String field, String preTag, String postTag) {
        this.field = field;
        this.preTag = preTag;
        this.postTag = postTag;
    }

    public EsHighLightSetting(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getPreTag() {
        return preTag;
    }

    public void setPreTag(String preTag) {
        this.preTag = preTag;
    }

    public String getPostTag() {
        return postTag;
    }

    public void setPostTag(String postTag) {
        this.postTag = postTag;
    }
}
