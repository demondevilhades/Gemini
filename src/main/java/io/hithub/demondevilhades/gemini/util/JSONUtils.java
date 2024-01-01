package io.hithub.demondevilhades.gemini.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;

/**
 * 
 * @author awesome
 */
public class JSONUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JSONUtils.class);

    private static final ObjectMapper OM = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
//            .enable(SerializationFeature.WRAP_ROOT_VALUE)
//            .enable(DeserializationFeature.UNWRAP_ROOT_VALUE)
            .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

    /**
     * 
     * @param obj
     * @return
     */
    public static final String toString(Object obj) {
        try {
            return OM.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error", e);
            return null;
        }
    }

    /**
     * 
     * @param <T>
     * @param jsonStr
     * @param clazz
     * @return
     */
    public static final <T> T toT(String jsonStr, Class<T> clazz) {
        try {
            return OM.readValue(jsonStr, clazz);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error", e);
            return null;
        }
    }

    /**
     * 
     * @param <T>
     * @param jsonStr
     * @param clazz
     * @return
     */
    public static final <T> List<T> toList(String jsonStr, Class<T> clazz) {
        try {
            CollectionType collectionType = OM.getTypeFactory().constructCollectionType(ArrayList.class, clazz);
            return OM.readValue(jsonStr, collectionType);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error", e);
            return null;
        }
    }
}
