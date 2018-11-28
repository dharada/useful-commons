package time;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.hamcrest.Matchers;
import org.joda.time.DateTimeUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.assertThat;

public class QueryBuilderTest {

    @Test
    public void testYyyyMMdd() throws Exception {
        assertThat(DateTimeUtil.yyyyMMdd(), Matchers.containsString("/"));
    }


    @Test
    public void testQueryBuilder() throws IOException {


        RestClient client = RestClient.builder(
                new HttpHost("localhost", 9200, "http")).build();

        Date from = new Date();
        Date to = new Date(DateTimeUtils.currentTimeMillis());

        String TIME_FIELD = "timestamp";

        QueryBuilder queryBuilder = QueryBuilders.rangeQuery(TIME_FIELD)
                .timeZone("+09:00")
                .gte(from)
                .lte(to);

        System.out.println( queryBuilder.toString());

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(queryBuilder);

        SearchRequest searchRequest = Requests.searchRequest("testIndex");
        searchRequest.source(sourceBuilder);

        System.out.println(searchRequest.toString());


    }
}
