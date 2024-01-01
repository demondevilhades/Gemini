package io.hithub.demondevilhades.gemini;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;

import io.hithub.demondevilhades.gemini.domain.Request;
import io.hithub.demondevilhades.gemini.domain.Response;
import io.hithub.demondevilhades.gemini.util.HttpClient;

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

    public <REQ extends Request<RESP> , RESP extends Response> RESP request(REQ request) throws IOException {
        String reqStr = http(request.getRequest(apiKey), HEADER_JSON);
        return (RESP) request.trans(reqStr);
    }
}
