package coursework.server.controller;

import coursework.server.entity.Visitor;
import coursework.server.service.VisitorService;
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

import static org.junit.Assert.assertEquals;


public class VisitorControllerTest {
    @Autowired
    private MockBean mockMvc;

    @MockBean
    private VisitorService visitorService;

    String exampleVisitorJson = "{\"first_name\":\"test\"last_name\":\"test\"\"library_card\":\"1234\"}";


    @Test
    public void Testcreate() throws Exception{
        Visitor visitor = new Visitor();

        Mockito.when(
                visitorService.create(Mockito.any(Visitor.class)).thenReturn(visitor)
        );

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/visitors")
                .accept(MediaType.APPLICATION_JSON).content(exampleVisitorJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

        assertEquals("http://localhost:8080/visitors/1",
                response.getHeader(HttpHeaders.LOCATION));

    }

    @Test
    public void TestfindAll() throws Exception {
        Visitor visitor = new Visitor();

        Mockito.when(
                visitorService.findAll(Mockito.any()).thenReturn(visitor);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/visitors").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "{id:1,first_name:test,last_name:test, library_card:1234}";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void Testfind() throws Exception {
        Visitor visitor = new Visitor();

        Mockito.when(
                visitorService.findAll(Mockito.any()).thenReturn(visitor);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/visitors/1").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "{id:1,first_name:test,last_name:test, library_card:1234}";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }
}