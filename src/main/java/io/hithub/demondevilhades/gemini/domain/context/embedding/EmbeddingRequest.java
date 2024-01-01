package io.hithub.demondevilhades.gemini.domain.context.embedding;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.hithub.demondevilhades.gemini.domain.PostRequest;
import io.hithub.demondevilhades.gemini.domain.Response;
import io.hithub.demondevilhades.gemini.domain.context.Content;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @see https://ai.google.dev/tutorials/rest_quickstart#embedding
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
public class EmbeddingRequest extends PostRequest<Response> {

    @JsonIgnore
    @Getter
    private final String basePath = "/models/embedding-001:embedContent?key=%s";

    private String model = "models/embedding-001";
    private Content content;
    
    @Override
    protected String reqPath(String apiKey) {
        return String.format(getBasePath(), apiKey);
    }
}
