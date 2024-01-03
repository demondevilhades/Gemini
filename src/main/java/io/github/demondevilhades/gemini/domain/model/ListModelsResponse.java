package io.github.demondevilhades.gemini.domain.model;

import java.util.List;

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
public class ListModelsResponse implements Response {

    private List<Model> models;
}
