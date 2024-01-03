package io.github.demondevilhades.gemini.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author awesome
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Model {
    
    private String name;
    private String version;
    private String displayName;
    private String description;
    private String inputTokenLimit;
    private String outputTokenLimit;
    private String temperature;
    private String topP;
    private String topK;
    private String[] supportedGenerationMethods;
}
