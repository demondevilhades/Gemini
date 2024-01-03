package io.github.demondevilhades.gemini.domain.context.stream;

import io.github.demondevilhades.gemini.domain.StreamResponse;
import io.github.demondevilhades.gemini.domain.context.ContextResponse;
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
public abstract class StreamGenerateContentResponse extends StreamResponse<ContextResponse> {
    
    protected final Class<ContextResponse> tClass = ContextResponse.class;
}
