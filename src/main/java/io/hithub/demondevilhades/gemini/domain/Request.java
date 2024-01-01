package io.hithub.demondevilhades.gemini.domain;

import java.lang.reflect.ParameterizedType;

import org.apache.http.client.methods.HttpRequestBase;

import io.hithub.demondevilhades.gemini.util.JSONUtils;

/**
 * 
 * @author awesome
 *
 * @param <RESP>
 */
public abstract class Request<RESP extends Response> {

    protected abstract String getBasePath();
    
    public abstract HttpRequestBase getRequest(String apiKey);
    
    @SuppressWarnings("unchecked")
    protected final Class<RESP> respClass = (Class<RESP>) ((ParameterizedType) getClass().getGenericSuperclass())
            .getActualTypeArguments()[0];

    /**
     * 
     * @param apiKey
     * @return
     */
    protected String reqPath(String apiKey) {
        return String.format(getBasePath(), apiKey);
    }
    
	/**
	 * 
	 * @param reqStr
	 * @return
	 */
    public RESP trans(String reqStr) {
        return JSONUtils.toT(reqStr, respClass);
    }
}
