package io.hithub.demondevilhades.gemini;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import io.hithub.demondevilhades.gemini.domain.context.Content;
import io.hithub.demondevilhades.gemini.domain.context.ContextResponse;
import io.hithub.demondevilhades.gemini.domain.context.Part;
import io.hithub.demondevilhades.gemini.domain.context.TextContent;
import io.hithub.demondevilhades.gemini.domain.context.textandimage.ImageContent;
import io.hithub.demondevilhades.gemini.domain.context.textandimage.ImagePart;
import io.hithub.demondevilhades.gemini.domain.context.textandimage.InlineData;
import io.hithub.demondevilhades.gemini.domain.context.textandimage.TextAndImageInputRequest;
import io.hithub.demondevilhades.gemini.domain.context.textonly.TextOnlyInputRequest;
import io.hithub.demondevilhades.gemini.domain.model.GetModelRequest;
import io.hithub.demondevilhades.gemini.domain.model.GetModelResponse;
import io.hithub.demondevilhades.gemini.domain.model.ListModelsRequest;
import io.hithub.demondevilhades.gemini.domain.model.ListModelsResponse;
import io.hithub.demondevilhades.gemini.domain.model.Model;
import io.hithub.demondevilhades.gemini.util.Config;
import io.hithub.demondevilhades.gemini.util.ResourcesUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author awesome
 */
@Slf4j
public class ClientTest {

    private Client client = null;

    @BeforeClass
    public static void beforeClass() throws Exception {
        Config.init();
    }

    @Before
    public void before() {
        client = Client.build(Config.get("API_KEY"), Config.get("proxy.server"), Config.getInt("proxy.port"));
    }

    @After
    public void after() throws IOException {
        if (client != null) {
            client.close();
        }
    }

    @Test
    public void testGetModel() throws IOException {
        GetModelRequest request = new GetModelRequest(GetModelRequest.modelName.GEMINI_PRO);
        GetModelResponse response = client.request(request);
        log.info("model : {}", response);
    }

    @Test
    public void testListModels() throws IOException {
        ListModelsRequest request = new ListModelsRequest();
        ListModelsResponse response = client.request(request);
        List<Model> models = response.getModels();
        for (Model model : models) {
            log.info("model : {}", model);
        }
    }

    @Test
    public void testTextOnlyInput() throws IOException {
        
        TextContent content = TextContent.builder().parts(Collections.singletonList(new Part("Hello! Who are you?"))).build();
        TextOnlyInputRequest request = new TextOnlyInputRequest(Collections.singletonList(content));
        ContextResponse response = client.request(request);

        log.info("text : {}", response.getCandidates().get(0).getContent().getParts().get(0).getText());
    }

    @Test
    public void testTextAndImageInput() throws IOException {
        String str = ResourcesUtils.getResourceFile("test.jpeg");

        ImageContent content = ImageContent.builder()
                .parts(ImagePart.buildImagePart("What do you see?", InlineData.buildJpegData(new File(str)))).build();
        TextAndImageInputRequest request = new TextAndImageInputRequest(Collections.singletonList(content));
        ContextResponse response = client.request(request);

        log.info("text : {}", response.getCandidates().get(0).getContent().getParts().get(0).getText());
    }

    @Test
    public void testChat() throws IOException {
        String question0 = "There is a question with three options A, B, C, the answer option is random, and you don't know the answer. Suppose you randomly guessed A. Now you are told one of the wrong answers from the remaining two options, suppose the wrong answer you are told is B. Would you change your choice to C at this time? Why?";
        String question1 = "Are you sure?";
        TextContent content = TextContent.builder().parts(Collections.singletonList(new Part(question0)))
                .role(Content.Role.USER).build();
        TextOnlyInputRequest request0 = new TextOnlyInputRequest(Collections.singletonList(content));
        ContextResponse response0 = client.request(request0);

        String text = response0.getCandidates().get(0).getContent().getParts().get(0).getText();
        log.info("text : {}", text);

        TextContent content1 = TextContent.builder().parts(Collections.singletonList(new Part(text)))
                .role(Content.Role.MODEL).build();
        TextContent content2 = TextContent.builder().parts(Collections.singletonList(new Part(question1)))
                .role(Content.Role.USER).build();
        TextOnlyInputRequest request = new TextOnlyInputRequest(content, content1, content2);
        ContextResponse response = client.request(request);

        String text1 = response.getCandidates().get(0).getContent().getParts().get(0).getText();
        log.info("text1 : {}", text1);
    }

    @Test
    public void testStreamGenerateContent() throws IOException {
        // TODO
    }

    @Test
    public void testCountTokens() throws IOException {
        // TODO
    }

    @Test
    public void testEmbedding() throws IOException {
        // TODO
    }

    @Test
    public void testBatchEmbedding() throws IOException {
        // TODO
    }
}
