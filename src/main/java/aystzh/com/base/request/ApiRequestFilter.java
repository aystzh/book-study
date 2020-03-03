package aystzh.com.base.request;




import aystzh.com.base.enums.OperatorType;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 请求过滤条件
 * Created by zhanghuan on 2019/9/4.
 */
public class ApiRequestFilter implements Serializable {
    private static final long serialVersionUID = 6145292948839762699L;

    private String field;
    private List<String> fields;
    private Object value;
    private OperatorType operatorType;
    private List<Object> valueList;

    private boolean cascadeParent;
    private ApiRequest cascadeRequest;

    private Map<String,Float> weightMap;

    public ApiRequestFilter(OperatorType operatorType, boolean cascadeParent, String field, ApiRequest apiRequest) {
        this.operatorType = operatorType;
        this.cascadeParent = cascadeParent;
        this.field = field;
        this.cascadeRequest = apiRequest;

    }

    public ApiRequestFilter(OperatorType operatorType, String field, Object value) {
        this.field = field;
        this.value = value;
        this.operatorType = operatorType;
    }

    public ApiRequestFilter(OperatorType operatorType, String field, List<Object> valueList) {
        this.field = field;
        this.valueList = valueList;
        this.operatorType = operatorType;
    }

    public ApiRequestFilter(OperatorType operatorType, List<String> fields, Object value) {
        this.fields = fields;
        this.value = value;
        this.operatorType = operatorType;
    }

    public ApiRequestFilter(OperatorType operatorType, Map<String,Float> weightMap, Object value) {
        this.weightMap = weightMap;
        this.value = value;
        this.operatorType = operatorType;
    }
    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public OperatorType getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(OperatorType operatorType) {
        this.operatorType = operatorType;
    }

    public List<Object> getValueList() {
        return valueList;
    }

    public void setValueList(List<Object> valueList) {
        this.valueList = valueList;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public Map<String, Float> getWeightMap() {
        return weightMap;
    }

    public void setWeightMap(Map<String, Float> weightMap) {
        this.weightMap = weightMap;
    }

    public boolean isCascadeParent() {
        return cascadeParent;
    }

    public void setCascadeParent(boolean cascadeParent) {
        this.cascadeParent = cascadeParent;
    }

    public ApiRequest getCascadeRequest() {
        return cascadeRequest;
    }

    public void setCascadeRequest(ApiRequest cascadeRequest) {
        this.cascadeRequest = cascadeRequest;
    }
}
