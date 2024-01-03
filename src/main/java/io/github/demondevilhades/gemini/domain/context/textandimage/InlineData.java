package io.github.demondevilhades.gemini.domain.context.textandimage;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;

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
public class InlineData {

    public static final class MimeType {

        public static final String JPEG = "image/jpeg";
    }

    private String mimeType;
    private String data;

    public static InlineData buildJpegData(File file) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(file);
        String name = file.getName();
        return new InlineData(MimeType.JPEG, getImageBase64(bufferedImage, name.substring(name.lastIndexOf(".") + 1)));
    }

    private static String getImageBase64(final BufferedImage bufferedImage, final String formatName)
            throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, formatName, baos);
        byte[] bs = baos.toByteArray();
        return Base64.getEncoder().encodeToString(bs);
    }
}
