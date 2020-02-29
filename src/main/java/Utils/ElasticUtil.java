package Utils;

import com.google.gson.GsonBuilder;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.indices.IndicesExists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElasticUtil {

    private static final Logger logger = LoggerFactory.getLogger(ElasticUtil.class);

    private static final String ES_HOST = "http://123.56.82.71";
    private static final int ES_HTTP_PORT = 9200;
    private static JestClient client;

    public static synchronized JestClient getClient() {
        if (client == null) {
            build();
        }
        return client;
    }

    public static void close(JestClient client) {
        if (null != client) {
            try {
                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void build() {
        JestClientFactory factory = new JestClientFactory();
        //实例化客户端工厂类
        factory.setHttpClientConfig(new HttpClientConfig.Builder(ES_HOST + ":" + ES_HTTP_PORT)
                .multiThreaded(true)
                .defaultMaxTotalConnectionPerRoute(2)
                .maxTotalConnection(2) //设置总连接数为2个
                .connTimeout(10000) //设置连接超时时间
                .readTimeout(10000)    //设置读取超时时间
                .gson(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()) //设置 JSON 日期格式
                .build());
        client = factory.getObject();
    }

    /**
     * 判断索引目录是否存在
     */
    public static boolean indicesExists(JestClient client, String index){
        try{
            IndicesExists indicesExists = new IndicesExists.Builder(index).build();
            JestResult result = client.execute(indicesExists);
            if(result.isSucceeded()){
                return true;
            }
            logger.info("confirm index {} : {}", index, result.getErrorMessage());
        }catch (Exception e){
            logger.error("index exist error: {}", e.getMessage());
        }
        return false;
    }
}
