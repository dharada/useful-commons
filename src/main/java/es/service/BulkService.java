package es.service;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.client.transport.TransportClient;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class BulkService {

    private TransportClient transportClient;

    private final String loganme = "mail_log-" + new SimpleDateFormat("yyyy.MM.dd").format(new Date());

    private Map<String, Integer> typeMap = new HashMap<String, Integer>() {
        {
            put("job", 0);
            put("click", 0);
        }
    };


    public void deleteLog(Integer pre_id) throws IOException, InterruptedException {

        BulkRequestBuilder bulkRequest = transportClient.prepareBulk();

        for (String key : typeMap.keySet()) {
            IntStream.range(pre_id, typeMap.get(key)).forEach(id ->
                    bulkRequest.add(transportClient
                            .prepareDelete(loganme, key, String.valueOf(id))));

            typeMap.put(key, 0);
        }

        bulkRequest.setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE).execute();
    }
}