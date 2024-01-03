package io.github.demondevilhades.gemini;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import io.github.demondevilhades.gemini.domain.context.Content;
import io.github.demondevilhades.gemini.domain.context.ContextResponse;
import io.github.demondevilhades.gemini.domain.context.Part;
import io.github.demondevilhades.gemini.domain.context.TextContent;
import io.github.demondevilhades.gemini.domain.context.embedding.BatchEmbeddingRequest;
import io.github.demondevilhades.gemini.domain.context.embedding.BatchEmbeddingResponse;
import io.github.demondevilhades.gemini.domain.context.embedding.EmbeddingRequest;
import io.github.demondevilhades.gemini.domain.context.embedding.EmbeddingResponse;
import io.github.demondevilhades.gemini.domain.context.stream.StreamGenerateContentRequest;
import io.github.demondevilhades.gemini.domain.context.stream.StreamGenerateContentResponse;
import io.github.demondevilhades.gemini.domain.context.textandimage.ImageContent;
import io.github.demondevilhades.gemini.domain.context.textandimage.ImagePart;
import io.github.demondevilhades.gemini.domain.context.textandimage.InlineData;
import io.github.demondevilhades.gemini.domain.context.textandimage.TextAndImageInputRequest;
import io.github.demondevilhades.gemini.domain.context.textonly.TextOnlyInputRequest;
import io.github.demondevilhades.gemini.domain.context.token.CountTokensRequest;
import io.github.demondevilhades.gemini.domain.context.token.CountTokensResponse;
import io.github.demondevilhades.gemini.domain.model.GetModelRequest;
import io.github.demondevilhades.gemini.domain.model.GetModelResponse;
import io.github.demondevilhades.gemini.domain.model.ListModelsRequest;
import io.github.demondevilhades.gemini.domain.model.ListModelsResponse;
import io.github.demondevilhades.gemini.domain.model.Model;
import io.github.demondevilhades.gemini.util.Config;
import io.github.demondevilhades.gemini.util.ResourcesUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author awesome
 */
@Slf4j
class ClientTest {

    private Client client = null;
    
    private final String firstQuestion = "Hello! Who are you?";
    
    private final String imageQuestion = "What do you see?";
    private final String imagePath = "test.jpeg";

    private final String question0 = "There is a question with three options A, B, C, the answer option is random, and you don't know the answer. Suppose you randomly guessed A. Now you are told one of the wrong answers from the remaining two options, suppose the wrong answer you are told is B. Would you change your choice to C at this time? Why?";
    private final String question1 = "Are you sure?";

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
        TextContent content = TextContent.builder().parts(Collections.singletonList(new Part(firstQuestion))).build();
        TextOnlyInputRequest request = new TextOnlyInputRequest(Collections.singletonList(content));
        ContextResponse response = client.request(request);

        log.info("text : {}", response.getCandidates().get(0).getContent().getParts().get(0).getText());
    }

    @Test
    public void testTextAndImageInput() throws IOException {
        String str = ResourcesUtils.getResourceFile(imagePath);

        ImageContent content = ImageContent.builder()
                .parts(ImagePart.buildImagePart(imageQuestion, InlineData.buildJpegData(new File(str)))).build();
        TextAndImageInputRequest request = new TextAndImageInputRequest(Collections.singletonList(content));
        ContextResponse response = client.request(request);

        log.info("text : {}", response.getCandidates().get(0).getContent().getParts().get(0).getText());
    }

    @Test
    public void testChat() throws IOException {
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
        TextContent content = TextContent.builder().parts(Collections.singletonList(new Part(question0)))
                .role(Content.Role.USER).build();
        StreamGenerateContentRequest request = new StreamGenerateContentRequest(Collections.singletonList(content));
        
        client.stream(request, new StreamGenerateContentResponse() {

            @Override
            protected void read(ContextResponse t) {
                String text = t.getCandidates().get(0).getContent().getParts().get(0).getText();
                log.info("text : {}", text);
            }
        });
    }

    @Test
    public void testCountTokens() throws IOException {
        TextContent content = TextContent.builder().parts(Collections.singletonList(new Part(firstQuestion))).build();
        CountTokensRequest request = new CountTokensRequest(Collections.singletonList(content));
        CountTokensResponse response = client.request(request);
        log.info("totalTokens : {}", response.getTotalTokens());
    }

    @Test
    public void testEmbedding() throws IOException {
        TextContent content = TextContent.builder().parts(Collections.singletonList(new Part(firstQuestion))).build();
        EmbeddingRequest request = new EmbeddingRequest(content);
        EmbeddingResponse response = client.request(request);
        log.info("embedding : {}", StringUtils.join(response.getEmbedding(), ","));
    }

    @Test
    public void testBatchEmbedding() throws IOException {
        TextContent content = TextContent.builder().parts(Collections.singletonList(new Part(firstQuestion))).build();
        EmbeddingRequest embeddingRequest = new EmbeddingRequest(content);
        BatchEmbeddingRequest request = new BatchEmbeddingRequest(Collections.singletonList(embeddingRequest));
        BatchEmbeddingResponse response = client.request(request);
        log.info("embedding : {}", StringUtils.join(response.getEmbeddings().get(0), ","));
    }
}
