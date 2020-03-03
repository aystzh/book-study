package aystzh.com.study.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.Lists;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhanghuan on 2019/9/4.
 */
public class HighLightResultMapper implements SearchResultMapper {


    private static ObjectMapper objectMapper = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .enable(MapperFeature.USE_ANNOTATIONS);
    private static Logger logger = LoggerFactory.getLogger(HighLightResultMapper.class);

    @Override
    public <T>AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
        List<T> chunk = Lists.newArrayList();
        SearchHits searchHits = response.getHits();
        for (SearchHit searchHit : searchHits) {
            if (response.getHits().getHits().length <= 0) {
                return null;
            }

            Map<String, Object> entityMap = searchHit.getSourceAsMap();
            Map<String, HighlightField> highlightFieldMap = searchHit.getHighlightFields();
            if (highlightFieldMap.size() != 0) {
                for (String highName : highlightFieldMap.keySet()) {
                    Text text[]=searchHit.getHighlightFields().get(highName).fragments();
                    if(text.length>0)
                    {
                        String highValue = searchHit.getHighlightFields().get(highName).fragments()[0].toString();

                        //转换 全拼、首拼高亮字段 xx.spy 、xx.fpy
                        String[] arr = highName.split("\\.", 0);
                        highName = arr[0];

                        if("content".equals(highName)){
                            //遍历 高亮结果集，覆盖 正常结果集
                            entityMap.put("summary", highValue);
                        }else{
                            entityMap.put(highName, highValue);
                        }
                    }
                }
            }
            try {
                String data = objectMapper.writeValueAsString(entityMap);
                T t = objectMapper.readValue(data, clazz);
                chunk.add(t);
            } catch (JsonProcessingException e) {
                logger.error("数据转换错误:", e);
            } catch (IOException e) {
                logger.error("数据转换错误:", e);
            }
        }
        if (chunk.size() > 0) {
            return new AggregatedPageImpl<T>(chunk, pageable, searchHits.getTotalHits());
        }
        return new AggregatedPageImpl<T>(new ArrayList<>());
    }
}
