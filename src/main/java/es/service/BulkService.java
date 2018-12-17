/**
 * Copyright (c) Mizuho Securities Co., Ltd. All Rights Reserved.
 * <p>
 * {LICENSE DESCRIPTION}
 */
package es.service;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.client.transport.TransportClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class BulkService {

    private TransportClient transportClient;

    private final String typeAccesslog = "apache_log-" + new SimpleDateFormat("yyyy.MM.dd").format(new Date());

    private final String typeMaillog = "mail_log-" + new SimpleDateFormat("yyyy.MM.dd").format(new Date());

    private Map<String, Integer> EMAIL_TYPE_MAP = new HashMap<String, Integer>() {
        {
            put("job", 0);
            put("bounced", 0);
            put("storage", 0);
            put("subscribers", 0);
            put("sent", 0);
            put("opened", 0);
            put("click", 0);
        }
    };

    public Integer indexAccesslog(String filename, Integer pre_id) throws IOException, InterruptedException {
        return indexAccesslog(filename, pre_id, typeAccesslog);
    }

    /**
     * @throws Exception
     */
    public Integer indexAccesslog(String filename, Integer pre_id, String index) throws IOException, InterruptedException {

        BulkRequestBuilder bulkRequest = transportClient.prepareBulk();

        BufferedReader br = new BufferedReader(
                new FileReader(
                        new File(ClassLoader.getSystemResource("bulk/" + filename + ".json.txt").getPath())));

        while (br.ready()) {
            bulkRequest.add(transportClient.prepareIndex(index, "access")
                    .setId((pre_id++).toString())
                    .setSource(br.readLine()));
        }

        bulkRequest.setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE).execute();

        Thread.sleep(3000);

        return pre_id;
    }

    public void deleteAccesslog(Integer pre_id, Integer suf_id) throws IOException, InterruptedException {
        deleteAccesslog(pre_id, suf_id, typeAccesslog);
    }

    /**
     * @throws Exception
     */
    public void deleteAccesslog(Integer pre_id, Integer suf_id, String index) throws IOException, InterruptedException {

        BulkRequestBuilder bulkRequest = transportClient.prepareBulk();

        IntStream.range(pre_id, suf_id).forEach(id ->
                bulkRequest.add(transportClient
                        .prepareDelete(index, "access", String.valueOf(id))));

        bulkRequest.setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE).execute();
    }

    /**
     * @throws Exception
     */
    public void inedxMaillog(Integer pre_id) throws IOException, InterruptedException {

        BulkRequestBuilder bulkRequest = transportClient.prepareBulk();


        for (String key : EMAIL_TYPE_MAP.keySet()) {
            Integer initId = pre_id;
            BufferedReader br = new BufferedReader(
                    new FileReader(
                            new File(getClass().getClassLoader().getResource("bulk/maillog_" + key + ".json.txt").getPath())));

            while (br.ready()) {
                bulkRequest.add(transportClient.prepareIndex("mail_log-" + new SimpleDateFormat("yyyy.MM.dd").format(new Date()), key)
                        .setId((initId++).toString())
                        .setSource(br.readLine()));
            }

            EMAIL_TYPE_MAP.put(key, initId);
        }

        BulkResponse test = bulkRequest.setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE).execute().actionGet();

        Thread.sleep(3000);
    }

    /**
     * @throws Exception
     */
    public void deleteMaillog(Integer pre_id) throws IOException, InterruptedException {

        BulkRequestBuilder bulkRequest = transportClient.prepareBulk();

        for (String key : EMAIL_TYPE_MAP.keySet()) {
            IntStream.range(pre_id, EMAIL_TYPE_MAP.get(key)).forEach(id ->
                    bulkRequest.add(transportClient
                            .prepareDelete(typeMaillog, key, String.valueOf(id))));

            EMAIL_TYPE_MAP.put(key, 0);
        }

        bulkRequest.setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE).execute();
    }
}