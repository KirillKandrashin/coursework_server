package coursework.server.controller;

import coursework.server.entity.Publisher;
import coursework.server.service.PublisherService;
import org.junit.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;

public class PublisherControllerTest {


    @Autowired
    private MockBean mockMvc;

    @MockBean
    private PublisherService publisherService;

    String examplePublisherJson = "{\"name\":\"Имаджинариум\"}";


    @Test
    public void Testcreate() throws Exception{
        Publisher publisher = new Publisher();

        Mockito.when(
                publisherService.create(Mockito.any(Publisher.class)).thenReturn(publisher)
        );

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/publishers")
                .accept(MediaType.APPLICATION_JSON).content(examplePublisherJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

        assertEquals("http://localhost:8080/publishers/1",
                response.getHeader(HttpHeaders.LOCATION));

    }

    @Test
    public void TestfindAll() throws Exception {
        Publisher publisher = new Publisher();

        Mockito.when(
                publisherService.findAll(Mockito.any()).thenReturn(publisher);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/publishers").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "{id:1,name:Имаджинариум,bookList:[]}";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void Testfind() throws Exception {
        Publisher publisher = new Publisher();

        Mockito.when(
                publisherService.findAll(Mockito.any()).thenReturn(publisher);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/publishers/1").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "{id:1,name:Имаджинариум,bookList:[]}";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }
}
}