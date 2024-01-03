package io.github.demondevilhades.gemini.domain.context.embedding;

import io.github.demondevilhades.gemini.domain.Response;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class EmbeddingResponse implements Response {

    private Embedding embedding;
}
