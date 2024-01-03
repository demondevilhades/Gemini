package io.github.demondevilhades.gemini.domain.context;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 
 * @author awesome
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@SuperBuilder
public class TextContent extends Content {

    private List<Part> parts;
}
