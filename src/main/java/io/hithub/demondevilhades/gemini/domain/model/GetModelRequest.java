package io.hithub.demondevilhades.gemini.domain.model;

import io.hithub.demondevilhades.gemini.domain.GetRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author awesome
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class GetModelRequest extends GetRequest<GetModelResponse> {
    
    public static final class modelName {
        
        public static final String GEMINI_PRO = "gemini-pro";
    }

    @Getter
    private final String basePath = "/models/%s?key=%s";
    
    private final String modelName;

    @Override
    protected String reqPath(String apiKey) {
        return String.format(getBasePath(), modelName, apiKey);
    }
}
