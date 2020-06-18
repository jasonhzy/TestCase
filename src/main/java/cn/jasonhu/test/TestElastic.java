package cn.jasonhu.test;

import cn.jasonhu.test.Utils.ElasticUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import io.searchbox.client.JestClient;
import io.searchbox.core.Count;
import io.searchbox.core.CountResult;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.core.search.aggregation.TermsAggregation;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * JestClient + elasticsearch
 *
 *  参考资料：
 *  1、http://www.voidcn.com/article/p-cvtdvhla-brb.html
 */
//分组查询
//1、根据team分组
//(1)SQL写法
//    select team, count(*) as user_count from user group by team;
//(2)ES写法
//    TermsBuilder teamAgg= AggregationBuilders.terms("user_count").field("team");
//    sbuilder.addAggregation(teamAgg);
//    SearchResult result = client.execute();
//2、根据team、age分组
//(1)SQL写法
//    select team, age, count(*) as user_count from user group by team, age;
//(2)ES写法
//    TermsBuilder teamAgg = AggregationBuilders.terms("user_count").field("team");
//    TermsBuilder ageAgg = AggregationBuilders.terms("age_count").field("age");
//    sbuilder.addAggregation(teamAgg.subAggregation(ageAgg));
//    SearchResult result = client.execute();
//3、根据team分组，并计算最大年龄
//(1)SQL写法
//    select team, max(age) as max_age from user group by team;
//(2)ES写法
//    TermsBuilder teamAgg= AggregationBuilders.terms("user_count ").field("team");
//    MaxBuilder ageAgg= AggregationBuilders.max("max_age").field("age");
//    sbuilder.addAggregation(teamAgg.subAggregation(ageAgg));
//    SearchResult result = client.execute();

public class TestElastic {

    private static final Logger logger = LoggerFactory.getLogger(TestElastic.class);

    private String ACCOUNT_KEYWORD = "msg.from_account.keyword";
    private String ROOM_KEYWORD = "msg.type.keyword";
    private String ACCOUNT_AGGREGATION = "accountAgg";

    private String PREFIX_INDEX = "cy-im-";
    private String INDEX_TYPE = "cy_im_msg";
    private String MSG_TYPE = "room";

    /**
     * 精确匹配
     */
    @Test
    public void testTermQuery() {
        JestClient client = null;
        try {
            client = ElasticUtil.getClient();

            //单条件精确匹配
            QueryBuilder query = QueryBuilders.termQuery(ACCOUNT_KEYWORD, "123456");

            //多条件精确匹配
//            QueryBuilder query = QueryBuilders.boolQuery()
//                    .must(QueryBuilders.termQuery(ACCOUNT_KEYWORD, "210969997"))
//                    .must(QueryBuilders.termQuery(ROOM_KEYWORD, MSG_TYPE));

            //多值精确匹配
//            QueryBuilder query = QueryBuilders.boolQuery()
//                    .must(QueryBuilders.termsQuery(ACCOUNT_KEYWORD, new String[]{ "123456", "789012"}))
//                    .must(QueryBuilders.termQuery(ROOM_KEYWORD, MSG_TYPE));

            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(query)
                    .from(0)
                    .size(1);

            Search search = new Search.Builder(searchSourceBuilder.toString())
                    .addIndex(PREFIX_INDEX + "2019-12-01")
                    .addType(INDEX_TYPE)
                    .build();
            SearchResult result = client.execute(search);
            if (!result.isSucceeded()) {
                System.out.println(result.getErrorMessage());
                return;
            }

            JsonObject jsonObject = result.getJsonObject();
            System.out.println("====" + jsonObject.toString());
            JsonObject hitsobject = jsonObject.getAsJsonObject("hits");
            long total = hitsobject.get("total").getAsLong();
            System.out.println("------" + total);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (null != client) {
                ElasticUtil.close(client);
            }
        }
    }

    @Test
    public void testQueryCount() {
        JestClient client = null;
        try {
            client = ElasticUtil.getClient();
            QueryBuilder query = QueryBuilders.termQuery(ACCOUNT_KEYWORD, "123456");

            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(query);

            Count count = new Count.Builder()
                    .addIndex(PREFIX_INDEX + "2019-12-01")
                    .addType(INDEX_TYPE)
                    .query(searchSourceBuilder.toString())
                    .build();

            CountResult result = client.execute(count);
            if (!result.isSucceeded()) {
                logger.error("jestclient search count error: {}", result.getErrorMessage());
                return;
            }
            JSONObject jsonObj = JSONObject.parseObject(result.getJsonString());
            System.out.println("Count:" + jsonObj.getInteger("count"));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("jestclient connection error: {}", e.getMessage());
        } finally {
            if (null != client) {
                ElasticUtil.close(client);
            }
        }
    }


    /**
     * 聚合分组： group by/ count
     */
    @Test
    public void testAggregation() {
        JestClient client = null;
        try {
            client = ElasticUtil.getClient();
            QueryBuilder query = QueryBuilders.boolQuery()
                    .must(QueryBuilders.termsQuery(ACCOUNT_KEYWORD, new String[]{"123456", "789012"}))
                    .must(QueryBuilders.termQuery(ROOM_KEYWORD, MSG_TYPE));
            //聚合按照from_account分组，求count
            AggregationBuilder aggregation = AggregationBuilders.terms("accountAgg").field(ACCOUNT_KEYWORD);

            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(query)
                    .aggregation(aggregation);
            Search search = new Search.Builder(searchSourceBuilder.toString())
                    .addIndex(PREFIX_INDEX + "2019-12-01")
                    .addType(INDEX_TYPE)
                    .build();

            SearchResult result = client.execute(search);
            if (!result.isSucceeded()) {
                logger.error("jestclient search count error: {}", result.getErrorMessage());
                return;
            }
            System.out.println("====");
            System.out.println(result.getSourceAsString());
            //首先取最外层的聚合，拿到桶
            List<TermsAggregation.Entry> accountAgg = result.getAggregations().getTermsAggregation("accountAgg").getBuckets();
            for (TermsAggregation.Entry entry : accountAgg) { //循环桶，每个分组里的值
                System.out.println(entry.getKey() + "===" + entry.getCount());
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("jestclient connection error: {}", e.getMessage());
        } finally {
            if (null != client) {
                ElasticUtil.close(client);
            }
        }
    }

}
