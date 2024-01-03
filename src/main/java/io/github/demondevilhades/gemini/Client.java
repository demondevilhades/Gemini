package io.github.demondevilhades.gemini;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;

import io.github.demondevilhades.gemini.domain.Request;
import io.github.demondevilhades.gemini.domain.Response;
import io.github.demondevilhades.gemini.domain.StreamResponse;
import io.github.demondevilhades.gemini.util.HttpClient;

/**
 * 
 * @author awesome
 */
public class Client extends HttpClient {
    
    private String apiKey;

    public Client(String apiKey, HttpHost proxy) {
        super(proxy);
        this.apiKey = apiKey;
    }
    
    /**
     * 
     * @param apiKey
     * @param proxyHostname
     * @param proxyPort
     * @return
     */
    public static Client build(String apiKey, String proxyHostname, int proxyPort) {
        HttpHost proxy = null;
        if(StringUtils.isNotEmpty(proxyHostname) && proxyPort > 0) {
            proxy = new HttpHost(proxyHostname, proxyPort);
        }
        return new Client(apiKey, proxy);
    }

    /**
     * 
     * @param <REQ>
     * @param <RESP>
     * @param request
     * @return
     * @throws IOException
     */
    public <REQ extends Request<RESP> , RESP extends Response> RESP request(REQ request) throws IOException {
        String reqStr = http(request.getRequest(apiKey), HEADER_JSON);
        return (RESP) request.trans(reqStr);
    }

    /**
     * 
     * @param <REQ>
     * @param <RESP>
     * @param request
     * @param response
     * @throws IOException
     */
    public <REQ extends Request<RESP>, RESP extends StreamResponse<?>> void stream(REQ request, RESP response)
            throws IOException {
        try(CloseableHttpResponse closeableHttpResponse = streamHttp(request.getRequest(apiKey), HEADER_JSON);
                InputStream is = closeableHttpResponse.getEntity().getContent();){
            response.parseArray(is);
        }
    }
}
