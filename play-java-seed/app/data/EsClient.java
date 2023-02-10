package data;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

public class EsClient {
    private RestClient restClient;

    public RestClient EsClient(){
         restClient = RestClient.builder(
                new HttpHost("localhost",9200,"http")
        ).build();
        System.out.println("successfully connected");
        return restClient;
    }

}
