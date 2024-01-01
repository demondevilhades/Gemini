package io.hithub.demondevilhades.gemini.domain.context.textandimage;

import java.util.List;

import io.hithub.demondevilhades.gemini.domain.context.Content;
import lombok.Getter;
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
@SuperBuilder
public class ImageContent extends Content {

    private List<ImagePart> parts;
}
