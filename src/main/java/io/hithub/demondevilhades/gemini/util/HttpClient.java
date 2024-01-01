package io.hithub.demondevilhades.gemini.util;

import java.io.Closeable;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author awesome
 */
@Slf4j
public class HttpClient implements Closeable {

    protected final int timeout =  60 * 1000;
    protected final RequestConfig defRequestConfig = RequestConfig.custom().setConnectionRequestTimeout(timeout)
            .setSocketTimeout(timeout).setConnectTimeout(timeout).build();

    protected CloseableHttpClient closeableHttpClient;
    protected HttpClientContext httpClientContext = HttpClientContext.create();

    @SuppressWarnings("serial")
    public static final Map<String, String> HEADER_JSON = new HashMap<String, String>() {
        {
            put("Content-Type", "application/json");
        }
    };

    public HttpClient(HttpHost proxy) {
        HttpClientBuilder builder = HttpClients.custom().setDefaultRequestConfig(defRequestConfig);
        if(proxy != null) {
			builder.setProxy(proxy);
        }
        closeableHttpClient = builder.build();
    }

    @Override
    public void close() throws IOException {
        closeableHttpClient.close();
    }

    protected String http(HttpRequestBase httpRequestBase, Map<String, String> headerMap) throws IOException {
        if (headerMap != null) {
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                httpRequestBase.addHeader(entry.getKey(), entry.getValue());
            }
        }

        try (CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpRequestBase,
                httpClientContext);) {
            int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
            String str = EntityUtils.toString(closeableHttpResponse.getEntity());
            log.info("response: statusCode = {}, msg = {}", statusCode, str);
            return str;
        }
    }

    /**
     * 
     * @param url
     * @param headerMap
     * @return
     * @throws IOException
     */
    public String httpGet(String url, Map<String, String> headerMap) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        return http(httpGet, headerMap);
    }

    /**
     * 
     * @param url
     * @param headerMap
     * @param entityStr
     * @return
     * @throws IOException
     */
    public String httpPost(String url, Map<String, String> headerMap, String entityStr) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        if (StringUtils.isNotEmpty(entityStr)) {
            httpPost.setEntity(new StringEntity(entityStr, StandardCharsets.UTF_8));
        }
        return http(httpPost, headerMap);
    }
}
