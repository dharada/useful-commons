package rest.client;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class HighLevelRestClientTest {


    @Test
    public void testClient() {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http"),
                        new HttpHost("localhost", 9201, "http")));


    }


    @Test
    public void testSSLTLSClient() {

        String user = "elastic";
        String password = "changeme";
        String host = "elasticsearch.sample.net";
        int port = 9200;
        String trustStorePass = "fancyPassword";

        // add basic auth
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(user, password));

        HttpHost elasticHost = new HttpHost(host, port, "https");

        RestHighLevelClient restHighLevelClient = null;
        RestClient restClient = null;

        try {
            Path keyStorePath = Paths.get(ClassLoader.getSystemResource("truststore.jks").toURI());
            KeyStore truststore = KeyStore.getInstance("jks");

            try (InputStream is = Files.newInputStream(keyStorePath)) {
                truststore.load(is, trustStorePass.toCharArray());
            }

            SSLContextBuilder sslBuilder = SSLContexts.custom().loadTrustMaterial(truststore, null);
            final SSLContext sslContext = sslBuilder.build();

            RestClientBuilder builder = RestClient.builder(elasticHost).
                    setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setSSLContext(sslContext));
            builder.setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider));

            builder.setRequestConfigCallback(requestConfigBuilder -> requestConfigBuilder
                    .setConnectTimeout(5000)
                    .setSocketTimeout(60000))
                    .setMaxRetryTimeoutMillis(60000);

            restHighLevelClient = new RestHighLevelClient(builder);


            // do sth
            aggregateData(restHighLevelClient);


        } catch (IOException | CertificateException | NoSuchAlgorithmException | KeyStoreException | KeyManagementException | URISyntaxException e) {
            throw new RuntimeException(e);
        } finally {

            try {
                restClient.close();
            } catch (IOException e) {
                // ignore
            }
        }


    }

    private void aggregateData(RestHighLevelClient restHighLevelClient) {

        // do something


    }

}
