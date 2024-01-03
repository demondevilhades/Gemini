package io.github.demondevilhades.gemini.domain.context.stream;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.github.demondevilhades.gemini.domain.PostRequest;
import io.github.demondevilhades.gemini.domain.context.Content;
import io.github.demondevilhades.gemini.domain.context.GenerationConfig;
import io.github.demondevilhades.gemini.domain.context.SafetySetting;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @see https://ai.google.dev/tutorials/rest_quickstart#stream_generate_content
 * 
 * TODO
 * 
 * @author awesome
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StreamGenerateContentRequest extends PostRequest<StreamGenerateContentResponse> {

    @JsonIgnore
    @Getter
    private final String basePath = "/models/gemini-pro:streamGenerateContent?key=%s";
    
    private List<Content> contents;
    private List<SafetySetting> safetySettings;
    private GenerationConfig generationConfig;
    
	public StreamGenerateContentRequest(List<Content> contents) {
		this.contents = contents;
	}
    
    @Override
    protected String reqPath(String apiKey) {
        return String.format(getBasePath(), apiKey);
    }
}
