package io.hithub.demondevilhades.gemini.domain;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;

import io.hithub.demondevilhades.gemini.EndPoint;

/**
 * 
 * @author awesome
 *
 * @param <RESP>
 */
public abstract class GetRequest<RESP extends Response> extends Request<RESP> {

    @Override
    public HttpRequestBase getRequest(String apiKey) {
        return new HttpGet(EndPoint.V1_BETA + reqPath(apiKey));
    }
}
