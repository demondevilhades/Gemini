package io.github.demondevilhades.gemini.domain;

import java.nio.charset.StandardCharsets;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;

import io.github.demondevilhades.gemini.EndPoint;
import io.github.demondevilhades.gemini.util.JSONUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author awesome
 *
 * @param <RESP>
 */
@Slf4j
public abstract class PostRequest<RESP extends Response> extends Request<RESP> {

    @Override
    public HttpRequestBase getRequest(String apiKey) {
        HttpPost httpPost = new HttpPost(EndPoint.V1_BETA + reqPath(apiKey));
        String str = JSONUtils.toString(this);
        log.info("request: msg = {}", str);
        httpPost.setEntity(new StringEntity(str, StandardCharsets.UTF_8));
        return httpPost;
    }
}
