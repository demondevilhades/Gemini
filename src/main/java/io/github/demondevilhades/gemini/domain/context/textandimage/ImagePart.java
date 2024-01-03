package io.github.demondevilhades.gemini.domain.context.textandimage;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
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
@Builder
public class ImagePart {

    private String text;
    private InlineData inlineData;

    /**
     * 
     * @param text
     * @return
     */
    public static ImagePart buildText(String text) {
        return builder().text(text).build();
    }

    /**
     * 
     * @param inlineData
     * @return
     */
    public static ImagePart buildInlineData(InlineData inlineData) {
        return builder().inlineData(inlineData).build();
    }

    /**
     * 
     * @param text
     * @param inlineData
     * @return
     */
    public static List<ImagePart> buildImagePart(String text, InlineData inlineData) {
        List<ImagePart> imagePartList = new ArrayList<>();
        imagePartList.add(buildText("你看到了什么？"));
        imagePartList.add(buildInlineData(inlineData));
        return imagePartList;
    }
}
