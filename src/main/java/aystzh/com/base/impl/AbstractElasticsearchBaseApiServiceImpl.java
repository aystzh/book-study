package aystzh.com.base.impl;


import aystzh.com.base.BaseApiService;
import aystzh.com.base.entity.BaseEntity;
import aystzh.com.base.enums.PageOrderType;
import aystzh.com.base.request.ApiRequest;
import aystzh.com.base.request.ApiRequestFilter;
import aystzh.com.base.request.ApiRequestOrder;
import aystzh.com.base.request.ApiRequestPage;
import aystzh.com.base.response.ApiResponse;
import aystzh.com.study.utils.BeanMapping;
import aystzh.com.study.utils.HighLightResultMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.join.query.JoinQueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.cardinality.CardinalityAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.cardinality.InternalCardinality;
import org.elasticsearch.search.aggregations.metrics.sum.InternalSum;
import org.elasticsearch.search.aggregations.metrics.sum.SumAggregationBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ES抽象实现类
 * Created by zhanghuan on 2019/9/4.
 */
public abstract class AbstractElasticsearchBaseApiServiceImpl implements BaseApiService {


    @Autowired
    protected ElasticsearchTemplate elasticsearchTemplate;

    protected final transient Logger logger = LoggerFactory.getLogger(this.getClass());

    protected final static Long SCROLL_TIME = 5 * 60 * 1000L;

    protected Sort convertSort(ApiRequestPage requestPage) {
        if (requestPage.getOrderList() != null && !requestPage.getOrderList().isEmpty()) {
            List<Sort.Order> orderList = new ArrayList<>();
            for (ApiRequestOrder requestOrder : requestPage.getOrderList()) {
                orderList.add(this.convertSortOrder(requestOrder));
            }
            return new Sort(orderList);
        }
        return null;
    }

    private Sort.Order convertSortOrder(ApiRequestOrder requestOrder) {
        Sort.Direction direction;
        if (requestOrder.getOrderType().equals(PageOrderType.DESC)) {
            direction = Sort.Direction.DESC;
        } else {
            direction = Sort.Direction.ASC;
        }
        return new Sort.Order(direction, requestOrder.getField());
    }

    protected Pageable convertPageable(ApiRequestPage requestPage) {
        return new PageRequest(requestPage.getPage(), requestPage.getPageSize(), this.convertSort(requestPage));
    }

    protected QueryBuilder convertQueryBuilder(ApiRequest request) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (request == null || request.getFilterList() == null || request.getFilterList().isEmpty()) {
            return boolQueryBuilder;
        }
        for (ApiRequestFilter filter : request.getFilterList()) {
            switch (filter.getOperatorType()) {
                case EQ:
                    boolQueryBuilder.must(QueryBuilders.termQuery(filter.getField(), filter.getValue()));
                    break;
                case GE:
                    if (filter.getValue() instanceof Comparable) {
                        boolQueryBuilder.must(QueryBuilders.rangeQuery(filter.getField()).gte(filter.getValue()));
                    } else {
                        logger.error("字段({})不是可比较对象, value={}", filter.getField(), filter.getValue());
                    }
                    break;
                case LE:
                    if (filter.getValue() instanceof Comparable) {
                        boolQueryBuilder.must(QueryBuilders.rangeQuery(filter.getField()).lte(filter.getValue()));
                    } else {
                        logger.error("字段({})不是可比较对象, value={}", filter.getField(), filter.getValue());
                    }
                    break;
                case GT:
                    if (filter.getValue() instanceof Comparable) {
                        boolQueryBuilder.must(QueryBuilders.rangeQuery(filter.getField()).gt(filter.getValue()));
                    } else {
                        logger.error("字段({})不是可比较对象, value={}", filter.getField(), filter.getValue());
                    }
                    break;
                case LT:
                    if (filter.getValue() instanceof Comparable) {
                        boolQueryBuilder.must(QueryBuilders.rangeQuery(filter.getField()).lt(filter.getValue()));
                    } else {
                        logger.error("字段({})不是可比较对象, value={}", filter.getField(), filter.getValue());
                    }
                    break;
                case BETWEEN:
                    Object val1 = filter.getValueList().get(0);
                    Object val2 = filter.getValueList().get(1);
                    if (val1 instanceof Comparable && val2 instanceof Comparable) {
                        boolQueryBuilder.must(QueryBuilders.rangeQuery(filter.getField()).from(val1).to(val2));
                    } else {
                        logger.error("字段({})不是可比较对象, value1={}, value2={}", filter.getField(), val1, val2);
                    }
                    break;
                case IN:
                    boolQueryBuilder.must(QueryBuilders.termsQuery(filter.getField(), filter.getValueList()));
                    break;
                case LIKE:
                    MatchPhraseQueryBuilder matchQueryBuilder = QueryBuilders.matchPhraseQuery(filter.getField(), filter.getValue());
                    boolQueryBuilder.must(matchQueryBuilder);
                    break;
                case LIKES:
                    BoolQueryBuilder subBoolQueryBuilder = QueryBuilders.boolQuery();
                    for (Object value : filter.getValueList()) {
                        MatchPhraseQueryBuilder queryBuilder = QueryBuilders.matchPhraseQuery(filter.getField(), org.apache.lucene.queryparser.classic.QueryParser.escape(value.toString()));
                        subBoolQueryBuilder.should(queryBuilder);
                    }
                    boolQueryBuilder.must(subBoolQueryBuilder);
                    break;
                case LIKE_PREFIX:
                    MatchPhrasePrefixQueryBuilder matchPhrasePrefixQueryBuilder = QueryBuilders.matchPhrasePrefixQuery(filter.getField(), filter.getValue());
                    boolQueryBuilder.must(matchPhrasePrefixQueryBuilder);
                    break;
                case MULTI_MATCH:
                    MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(org.apache.lucene.queryparser.classic.QueryParser.escape(filter.getValue().toString()), filter.getFields().toArray(new String[filter.getFields().size()]));
                    multiMatchQueryBuilder.type(MultiMatchQueryBuilder.Type.BEST_FIELDS);
                    multiMatchQueryBuilder.minimumShouldMatch("30%");
                    multiMatchQueryBuilder.tieBreaker(0.3f);
                    boolQueryBuilder.must(multiMatchQueryBuilder);
                    break;
                case MULTI_MATCH_WITH_WEIGHT:
                    MultiMatchQueryBuilder multiMatchQueryWithWeightBuilder = QueryBuilders.multiMatchQuery(filter.getValue()).fields(filter.getWeightMap());
                    multiMatchQueryWithWeightBuilder.type(MultiMatchQueryBuilder.Type.BEST_FIELDS);
                    multiMatchQueryWithWeightBuilder.minimumShouldMatch("30%");
                    multiMatchQueryWithWeightBuilder.tieBreaker(0.3f);
                    boolQueryBuilder.must(multiMatchQueryWithWeightBuilder);
                    break;
                case NOTIN:
                    boolQueryBuilder.mustNot(QueryBuilders.termsQuery(filter.getField(), filter.getValueList()));
                    break;
                case CASCADE:
                    if (request.equals(filter.getCascadeRequest())) {
                        break;
                    }
                    if (filter.isCascadeParent()) {
                        QueryBuilder parentQuery = JoinQueryBuilders.hasParentQuery(filter.getField(), convertQueryBuilder(filter.getCascadeRequest()), Boolean.TRUE);
                        boolQueryBuilder.must(parentQuery);
                    } else {
                        QueryBuilder childQuery = JoinQueryBuilders.hasChildQuery(filter.getField(), convertQueryBuilder(filter.getCascadeRequest()), ScoreMode.None);
                        boolQueryBuilder.must(childQuery);
                    }
                    break;
                case OR:
                    if (org.springframework.util.CollectionUtils.isEmpty(filter.getValueList())) {
                        return boolQueryBuilder;
                    }
                    for (Object filterObject : filter.getValueList()) {
                        ApiRequestFilter requestFilter = (ApiRequestFilter) filterObject;
                        QueryBuilder boolShouldQueryBuilder = createShouldQueryBuilder(requestFilter);
                        if (boolShouldQueryBuilder != null) {
                            boolQueryBuilder.should(boolShouldQueryBuilder);
                        }
                    }
                    break;
                case MATCH:
                    MatchQueryBuilder fullTextMatchQueryBuilder = QueryBuilders.matchQuery(filter.getField(), filter.getValue());
                    boolQueryBuilder.must(fullTextMatchQueryBuilder);
                    break;
                default:
                    logger.error("不支持的运算符, op={}", filter.getOperatorType());
            }
        }
        logger.info("es query json : {}", boolQueryBuilder.toString());
        return boolQueryBuilder;
    }

    private QueryBuilder createShouldQueryBuilder(ApiRequestFilter filter) {
        switch (filter.getOperatorType()) {
            case EQ:
                return QueryBuilders.termQuery(filter.getField(), filter.getValue());
            case GE:
                if (filter.getValue() instanceof Comparable) {
                    return QueryBuilders.rangeQuery(filter.getField()).gte(filter.getValue());
                } else {
                    logger.error("字段({})不是可比较对象, value={}", filter.getField(), filter.getValue());
                }
                break;
            case LE:
                if (filter.getValue() instanceof Comparable) {
                    return QueryBuilders.rangeQuery(filter.getField()).lte(filter.getValue());
                } else {
                    logger.error("字段({})不是可比较对象, value={}", filter.getField(), filter.getValue());
                }
                break;
            case GT:
                if (filter.getValue() instanceof Comparable) {
                    return QueryBuilders.rangeQuery(filter.getField()).gt(filter.getValue());
                } else {
                    logger.error("字段({})不是可比较对象, value={}", filter.getField(), filter.getValue());
                }
                break;
            case LT:
                if (filter.getValue() instanceof Comparable) {
                    return QueryBuilders.rangeQuery(filter.getField()).lt(filter.getValue());
                } else {
                    logger.error("字段({})不是可比较对象, value={}", filter.getField(), filter.getValue());
                }
                break;
            case BETWEEN:
                Object val1 = filter.getValueList().get(0);
                Object val2 = filter.getValueList().get(1);
                if (val1 instanceof Comparable && val2 instanceof Comparable) {
                    return QueryBuilders.rangeQuery(filter.getField()).from(val1).to(val2);
                } else {
                    logger.error("字段({})不是可比较对象, value1={}, value2={}", filter.getField(), val1, val2);
                }
                break;
            case IN:
                return QueryBuilders.termsQuery(filter.getField(), filter.getValueList());
            case LIKE:
                return QueryBuilders.matchPhraseQuery(filter.getField(), filter.getValue());
            case LIKES:
                BoolQueryBuilder subBoolQueryBuilder = QueryBuilders.boolQuery();
                for (Object value : filter.getValueList()) {
                    MatchPhraseQueryBuilder queryBuilder = QueryBuilders.matchPhraseQuery(filter.getField(), org.apache.lucene.queryparser.classic.QueryParser.escape(value.toString()));
                    subBoolQueryBuilder.should(queryBuilder);
                }
                return subBoolQueryBuilder;
            case LIKE_PREFIX:
                return QueryBuilders.matchPhrasePrefixQuery(filter.getField(), filter.getValue());
            case MULTI_MATCH:
                MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(org.apache.lucene.queryparser.classic.QueryParser.escape(filter.getValue().toString()), filter.getFields().toArray(new String[filter.getFields().size()]));
                multiMatchQueryBuilder.type(MultiMatchQueryBuilder.Type.BEST_FIELDS);
                multiMatchQueryBuilder.minimumShouldMatch("30%");
                multiMatchQueryBuilder.tieBreaker(0.3f);
                return multiMatchQueryBuilder;
            case MULTI_MATCH_WITH_WEIGHT:
                MultiMatchQueryBuilder multiMatchQueryWithWeightBuilder = QueryBuilders.multiMatchQuery(filter.getValue()).fields(filter.getWeightMap());
                multiMatchQueryWithWeightBuilder.type(MultiMatchQueryBuilder.Type.BEST_FIELDS);
                multiMatchQueryWithWeightBuilder.minimumShouldMatch("30%");
                multiMatchQueryWithWeightBuilder.tieBreaker(0.3f);
                return multiMatchQueryWithWeightBuilder;
            case NOTIN:
                BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
                return boolQueryBuilder.mustNot(QueryBuilders.termsQuery(filter.getField(), filter.getValueList()));
            case MATCH:
                return QueryBuilders.matchQuery(filter.getField(), filter.getValue());
            default:
                logger.error("不支持的运算符, op={}", filter.getOperatorType());
        }
        return null;
    }

    protected <E> ApiResponse<E> convertHighLightResponse(ApiRequest apiRequest, ApiRequestPage apiRequestPage, Class<E> c) throws Exception {
        QueryBuilder queryBuilder = convertQueryBuilder(apiRequest);
        List<HighlightBuilder.Field> fieldList = Lists.newArrayList();

        fieldList.addAll(apiRequest.getEsHighLightSettings().stream().map(esHighLightSetting -> new HighlightBuilder.Field(esHighLightSetting.getField())
                .preTags(esHighLightSetting.getPreTag())
                .postTags(esHighLightSetting.getPostTag()))
                .collect(Collectors.toList()));

        NativeSearchQueryBuilder nsb = new NativeSearchQueryBuilder();
        nsb.withQuery(queryBuilder).withHighlightFields(fieldList.toArray(
                new HighlightBuilder.Field[fieldList.size()])).withPageable(convertPageable(apiRequestPage));
        return convertApiResponse(elasticsearchTemplate.queryForPage(nsb.build(), c, new HighLightResultMapper()), c);
    }

    protected <T, E> ApiResponse<E> convertApiResponse(Page<T> page, Class<E> c) throws Exception {
        ApiResponse<E> apiResponse = new ApiResponse<>();
        apiResponse.setPage(page.getNumber());
        apiResponse.setPageSize(page.getSize());
        apiResponse.setTotal(page.getTotalElements());
        apiResponse.setPagedData(BeanMapping.mapList(page.getContent(), c));

        return apiResponse;
    }

    protected <T extends BaseEntity> Map<String, Double> sum(ApiRequest apiRequest, List<String> fieldNames, Class<T> c) {

        Map<String, Double> resultMap = Maps.newHashMap();

        if (CollectionUtils.isNotEmpty(fieldNames)) {
            Document document = c.getAnnotation(Document.class);
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder()
                    .withQuery(convertQueryBuilder(apiRequest))
                    .withSearchType(SearchType.QUERY_THEN_FETCH)
                    .withIndices(document.indexName()).withTypes(document.type());

            for (String fieldName : fieldNames) {
                SumAggregationBuilder sumBuilder = AggregationBuilders.sum("sum_" + fieldName)
                        .field(fieldName);

                nativeSearchQueryBuilder.addAggregation(sumBuilder);
            }

            SearchQuery searchQuery = nativeSearchQueryBuilder.build();
            Aggregations aggregations = elasticsearchTemplate.query(searchQuery, searchResponse -> {
                return searchResponse.getAggregations();
            });

            Map<String, Aggregation> aggregationMap = aggregations.asMap();

            for (String fieldName : fieldNames) {
                InternalSum internalSum = (InternalSum) aggregationMap.get("sum_" + fieldName);
                double sumAmount = internalSum.getValue();
                resultMap.put(fieldName, sumAmount);
            }

        }
        return resultMap;

    }


    protected <T extends BaseEntity> Map<String, Long> count(ApiRequest apiRequest, List<String> fieldNames, Class<T> c) {

        Map<String, Long> resultMap = Maps.newHashMap();

        if (CollectionUtils.isNotEmpty(fieldNames)) {
            Document document = c.getAnnotation(Document.class);
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder()
                    .withQuery(convertQueryBuilder(apiRequest))
                    .withSearchType(SearchType.QUERY_THEN_FETCH)
                    .withIndices(document.indexName()).withTypes(document.type());

            for (String fieldName : fieldNames) {
                CardinalityAggregationBuilder cb = AggregationBuilders.cardinality("count_" + fieldName)
                        .field(fieldName)
                        .precisionThreshold(40000L);

                nativeSearchQueryBuilder.addAggregation(cb);
            }

            SearchQuery searchQuery = nativeSearchQueryBuilder.build();
            Aggregations aggregations = elasticsearchTemplate.query(searchQuery, searchResponse -> {
                return searchResponse.getAggregations();
            });

            Map<String, Aggregation> aggregationMap = aggregations.asMap();

            for (String fieldName : fieldNames) {
                InternalCardinality internalCardinality = (InternalCardinality) aggregationMap.get("count_" + fieldName);
                long countAmount = internalCardinality.getValue();
                resultMap.put(fieldName, countAmount);
            }

        }
        return resultMap;

    }


    protected <T extends BaseEntity> Map<String, Long> group(ApiRequest apiRequest, List<String> fieldNames, Class<T> c) {

        Map<String, Long> resultMap = Maps.newHashMap();

        if (CollectionUtils.isNotEmpty(fieldNames)) {
            Document document = c.getAnnotation(Document.class);
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder()
                    .withQuery(convertQueryBuilder(apiRequest))
                    .withSearchType(SearchType.QUERY_THEN_FETCH)
                    .withIndices(document.indexName()).withTypes(document.type());

            for (String fieldName : fieldNames) {
                TermsAggregationBuilder cb = AggregationBuilders.terms("group_" + fieldName)
                        .field(fieldName).size(1000);
               /* cb.includeExclude(new IncludeExclude(0,22));*/
                nativeSearchQueryBuilder.addAggregation(cb);
            }

            SearchQuery searchQuery = nativeSearchQueryBuilder.build();
            Aggregations aggregations = elasticsearchTemplate.query(searchQuery, searchResponse -> {
                return searchResponse.getAggregations();
            });

            Map<String, Aggregation> aggregationMap = aggregations.asMap();

            for (String fieldName : fieldNames) {
                Terms terms = (Terms) aggregationMap.get("group_" + fieldName);
                for (Terms.Bucket bucket : terms.getBuckets()) {
                    resultMap.put(bucket.getKeyAsString(), bucket.getDocCount());
                }
            }

        }
        return resultMap;

    }
}
