package aystzh.com.base.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.apache.http.Header;

/**
 * Created by zhanghuan on 2020/2/5.
 */
@Data
public class HttpResponse {

    private String content;

    @JsonIgnore
    private Header[] headers;
    @JsonIgnore
    private String reasonPhrase;
    @JsonIgnore
    private int statusCode;
    @JsonIgnore
    private String body;

}
