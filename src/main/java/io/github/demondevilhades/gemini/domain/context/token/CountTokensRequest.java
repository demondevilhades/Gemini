package io.github.demondevilhades.gemini.domain.context.token;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.github.demondevilhades.gemini.domain.PostRequest;
import io.github.demondevilhades.gemini.domain.context.Content;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @see https://ai.google.dev/tutorials/rest_quickstart#count_tokens
 * 
 * @author awesome
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CountTokensRequest extends PostRequest<CountTokensResponse> {

    @JsonIgnore
    @Getter
    private final String basePath = "/models/gemini-pro:countTokens?key=%s";
    
    private List<Content> contents;
    
    @Override
    protected String reqPath(String apiKey) {
        return String.format(getBasePath(), apiKey);
    }
}
