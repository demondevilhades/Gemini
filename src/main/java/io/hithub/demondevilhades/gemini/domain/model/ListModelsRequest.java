package io.hithub.demondevilhades.gemini.domain.model;

import io.hithub.demondevilhades.gemini.domain.GetRequest;
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
public class ListModelsRequest extends GetRequest<ListModelsResponse> {

    @Getter
    private final String basePath = "/models?key=%s";
}
