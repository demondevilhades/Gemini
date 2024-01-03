package io.github.demondevilhades.gemini.domain;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import io.github.demondevilhades.gemini.util.JSONUtils;

/**
 * 
 * @author awesome
 */
public abstract class StreamResponse<T> implements Response {

    /**
     * 
     * @param is
     * @throws IOException
     */
    public void parseArray(InputStream is) throws IOException {
        JsonParser parser = JSONUtils.createJsonParser(is);
        JsonToken jsonToken = parser.nextToken();
        if(jsonToken == JsonToken.START_ARRAY) {
            while(parser.nextToken() != JsonToken.END_ARRAY) {
                T t = parser.readValueAs(getTClass());
                read(t);
            }
        }
    }
    
    /**
     * 
     * @param t
     */
    protected abstract void read(T t);
    
    protected abstract Class<T> getTClass();
}
