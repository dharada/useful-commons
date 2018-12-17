
package es.service;


import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.IncludeExclude;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.pipeline.PipelineAggregatorBuilders;
import org.elasticsearch.search.aggregations.pipeline.bucketselector.BucketSelectorPipelineAggregationBuilder;

import java.util.HashMap;

//import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
//import org.elasticsearch.search.aggregations.pipeline.having.BucketSelectorBuilder;


public class RankingCommandParameter {


    /**
     * outputLv
     */
    private Integer outputLv;

    /**
     * getOutputSuffix
     */
    public String getOutputSuffix() {
        if (outputLv == null) {
            return "_3";
        } else switch (outputLv) {
            case 1:
                return "_1";
            default:
                return "_3";
        }
    }

//    /**
//     * @param fromDate
//     * @param toDate
//     * @param from
//     * @param size
//     * @author Keisuke Tanaka
//     */
//    public RankingCommandParameter(
//            CustomerEntity customer,
//            ZonedDateTime fromDate,
//            ZonedDateTime toDate,
//            Integer from,
//            Integer size,
//            Integer eqfi
//    ) {
//        super(ReportsIndex.INDEX_NAME, ReportsIndex.TYPE_NAME, customer, fromDate, toDate, from, size, 0, eqfi);
//    }

//    /**
//     * @param fromDate
//     * @param toDate
//     * @param from
//     * @param size
//     */
//    public RankingCommandParameter(
//            CustomerEntity customer,
//            ZonedDateTime fromDate,
//            ZonedDateTime toDate,
//            Integer from,
//            Integer size
//    ) {
//        this(customer, fromDate, toDate, from, size, null);
//    }

//    /**
//     * @param fromDate
//     * @param toDate
//     * @param from
//     * @param size
//     */
//    public RankingCommandParameter(
//            ZonedDateTime fromDate,
//            ZonedDateTime toDate,
//            Integer from,
//            Integer size
//    ) {
//        this(null, fromDate, toDate, from, size, CATEGORY_RANKING_TYPE_PERIOD);
//    }

    /**
     *
     */
    public String[] getIncludeSources() {
        return new String[]{"*"};
    }

//    /**
//     */
//    @Override
//    public BoolQueryBuilder getPostFilter() throws ElasticsearchException {
//        return super.getPostFilter();
//    }

    /**
     *
     */
    public BoolQueryBuilder getLogQuery() throws ElasticsearchException {
        return QueryBuilders.boolQuery();
    }

    /**
     */
//    @Override
//    public AggregationBuilder getAggregation() throws ElasticsearchException, IOException {
//        return this.getGroupingAggregationBuilder()
//                .subAggregation(this.getSourceAggregationBuilder());
//    }


//    /**
//     * レポート修正対象の事前集計クエリ(aggregation)
//     *
//     * @return
//     * @throws ElasticsearchException
//     */
//    public AggregationBuilder getCountAggregation() throws ElasticsearchException {
//        return this.getRangeFilterAggregationBuilder()
//                .subAggregation(
//                        this.getLogGroupingAggregationBuilder());
//    }

//    /**
//     * レポート修正対象の事前集計クエリ(query)
//     *
//     * @return
//     * @throws ElasticsearchException
//     */
//    public BoolQueryBuilder getCountLogQuery() throws ElasticsearchException {
//        return super.getLogQuery()
//                .must(this.getRegionFilter())
//                .must(this.getEqfiLogFilter())
//                .must(this.getCustomerIdFilter())
//                .must(this.getCategoryFilter());
//    }

    /**
     *
     */
    protected BoolQueryBuilder getCustomerFilter() {
        return QueryBuilders.boolQuery();
    }

    /* Parameter filter */

    /**
     * @return analysts.region_id フィルタ
     * @author Keisuke Tanaka
     */
    private BoolQueryBuilder getRegionFilter() {
        BoolQueryBuilder regionFilter = QueryBuilders.boolQuery();

//        if (this.regionId != null && this.getEqfi() != null) {
//            switch (this.getEqfi()) {
//                case 1:
//                    regionFilter
//                            .must(QueryBuilders.termQuery("customer.eq_region_id", this.regionId));
//                    break;
//                case 2:
//                    regionFilter
//                            .must(QueryBuilders.termQuery("customer.fi_region_id", this.regionId));
//                    break;
//                case 3:
//                    regionFilter
//                            .should(QueryBuilders.termQuery("customer.eq_region_id", this.regionId))
//                            .should(QueryBuilders.termQuery("customer.fi_region_id", this.regionId));
//                    break;
//            }
//        }

        return regionFilter;
    }

    /**
     * @return paramter.eqfi フィルタ
     * @author Keisuke Tanaka
     */
    private BoolQueryBuilder getEqfiLogFilter() {
        BoolQueryBuilder eqfiFilter = QueryBuilders.boolQuery();
//        if (this.getEqfi() != null) {
//            switch (this.getEqfi()) {
//                case 1:
//                case 2:
//                    eqfiFilter.must(QueryBuilders.termQuery("report.eqfi", this.getEqfi()));
//                    break;
//                case 3:
//                    eqfiFilter
//                            .should(QueryBuilders.termQuery("report.eqfi", 1))
//                            .should(QueryBuilders.termQuery("report.eqfi", 2));
//                    break;
//            }
//        }
        return eqfiFilter;
    }

    /**
     * @return paramter.customer.id フィルタ
     * @author Keisuke Tanaka
     */
    private BoolQueryBuilder getCustomerIdFilter() {
        BoolQueryBuilder customerFilter = QueryBuilders.boolQuery();
//        if (this.getCustomer() != null) {
//            return customerFilter.must(
//                    QueryBuilders.termQuery("customer.id", this.getCustomer().getId()));
//        }

        return customerFilter;
    }


//    private BoolQueryBuilder getCategoryFilter() {
//        BoolQueryBuilder categoryFilter = QueryBuilders.boolQuery();
//
//        if (this.categoryIdLv1 != null) {
//            categoryFilter.must(
//                    QueryBuilders.termQuery("category.id_1", this.categoryIdLv1));
//        }
//
//        if (this.categoryIdLv3 != null) {
//            categoryFilter.must(
//                    QueryBuilders.termQuery("category.id_3", this.categoryIdLv3));
//        }
//
//        return categoryFilter;
//    }

//    /* Aggregation filter */
//
//    private AggregationBuilder getLogGroupingAggregationBuilder() {
//        return AggregationBuilders.terms("grouping").field("category.id" + getOutputSuffix()).size(this.getSize() + this.getFrom())
//                .exclude(this.getExclude().toArray(new String[this.getExclude().size()]));
//    }

    private AggregationBuilder getGroupingAggregationBuilder() {

        TermsAggregationBuilder grouping = AggregationBuilders.terms("grouping");

        int sizeParam = 2;
        return grouping.field("category.id" + getOutputSuffix()).size(sizeParam).includeExclude(new IncludeExclude(null, new String[]{"1", "2"}))
                ;
    }

//    /**
//     * @return Source取得Aggregation
//     * @author Keisuke Tanaka
//     */
//    private AggregationBuilder getSourceAggregationBuilder() {
//        return AggregationBuilders.topHits("source")
//                .sort(SortBuilders.fieldSort("@timestamp").order(SortOrder.DESC))
//                .fetchSource(new String[]{"category.*" + getOutputSuffix()}, null)
//                .size(1);
//    }

    /**
     * @return Bucketフィルタ(集計期間[date_range]のデータが存在しないBucketは弾く)
     * @author Keisuke Tanaka
     */
//    protected BucketSelectorBuilder getBucketSelectorAggregationBuilder() {
//        return PipelineAggregatorBuilders.having("bucket_filter")
//                .setBucketsPathsMap(new HashMap<String, String>() {{
//                    put("count", "date_range>_count");
//                }})
//                .script(new Script("count > 0"));
//    }
    protected BucketSelectorPipelineAggregationBuilder getBucketSelectorAggregationBuilder() {

        BucketSelectorPipelineAggregationBuilder bucketSelectorPipelineAggregationBuilder = PipelineAggregatorBuilders.bucketSelector("bucket_filter", new HashMap<String, String>() {{
            put("count", "date_range>_count");
        }}, new Script("count > 0"));

//            BucketScriptPipelineAggregationBuilder bucketSelectorPipelineAggregationBuilder2 = PipelineAggregatorBuilders.bucketScript("bucket_filter", new HashMap<String, String>() {{
//                put("count", "date_range>_count");
//            }}, new Script("count > 0"));

        return bucketSelectorPipelineAggregationBuilder;

    }

}
