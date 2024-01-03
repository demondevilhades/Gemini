package io.github.demondevilhades.gemini.domain.context.textandimage;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.github.demondevilhades.gemini.domain.PostRequest;
import io.github.demondevilhades.gemini.domain.context.ContextResponse;
import io.github.demondevilhades.gemini.domain.context.GenerationConfig;
import io.github.demondevilhades.gemini.domain.context.SafetySetting;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @see https://ai.google.dev/tutorials/rest_quickstart#text-and-image_input
 * 
 * @author awesome
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TextAndImageInputRequest extends PostRequest<ContextResponse> {

    @JsonIgnore
    @Getter
    private final String basePath = "/models/gemini-pro-vision:generateContent?key=%s";
    
    private List<ImageContent> contents;
    private List<SafetySetting> safetySettings;
    private GenerationConfig generationConfig;
    
	public TextAndImageInputRequest(List<ImageContent> contents) {
		this.contents = contents;
	}
    
    @Override
    protected String reqPath(String apiKey) {
        return String.format(getBasePath(), apiKey);
    }
}
