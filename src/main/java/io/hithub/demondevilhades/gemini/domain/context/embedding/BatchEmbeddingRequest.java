package io.hithub.demondevilhades.gemini.domain.context.embedding;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.hithub.demondevilhades.gemini.domain.PostRequest;
import io.hithub.demondevilhades.gemini.domain.Response;
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
public class BatchEmbeddingRequest extends PostRequest<Response> {

    @JsonIgnore
    @Getter
    private final String basePath = "/models/embedding-001:batchEmbedContents?key=%s";

    private List<EmbeddingRequest> requests;
    
    @Override
    protected String reqPath(String apiKey) {
        return String.format(getBasePath(), apiKey);
    }
}
