package io.hithub.demondevilhades.gemini.domain.context.textonly;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.hithub.demondevilhades.gemini.domain.PostRequest;
import io.hithub.demondevilhades.gemini.domain.context.Content;
import io.hithub.demondevilhades.gemini.domain.context.ContextResponse;
import io.hithub.demondevilhades.gemini.domain.context.GenerationConfig;
import io.hithub.demondevilhades.gemini.domain.context.SafetySetting;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @see https://ai.google.dev/tutorials/rest_quickstart#text-only_input
 * @see https://ai.google.dev/tutorials/rest_quickstart#multi-turn_conversations_chat
 * @see https://ai.google.dev/tutorials/rest_quickstart#configuration
 * 
 * @author awesome
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TextOnlyInputRequest extends PostRequest<ContextResponse> {

    @JsonIgnore
    @Getter
    private final String basePath = "/models/gemini-pro:generateContent?key=%s";
    
    private List<Content> contents;
    private List<SafetySetting> safetySettings;
    private GenerationConfig generationConfig;
    
	public TextOnlyInputRequest(List<Content> contents) {
		this.contents = contents;
	}
    
    public TextOnlyInputRequest(Content... contents) {
        this.contents = new ArrayList<>();
        for (Content content : contents) {
			this.contents.add(content);
		}
    }
    
    @Override
    protected String reqPath(String apiKey) {
        return String.format(getBasePath(), apiKey);
    }
}
