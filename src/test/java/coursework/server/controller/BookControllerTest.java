package coursework.server.controller;

import coursework.server.entity.Book;
import coursework.server.service.AuthorService;
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

public class BookControllerTest {

    @Autowired
    private MockBean mockMvc;

    @MockBean
    private AuthorService authorService;

    String exampleAuthorJson = "{\"title\":\"test\"type\":\"test\"genre\":\"test\"number_of_copies\":\"1\"}";


    @Test
    public void Testcreate() throws Exception{
        Book book = new Book();

        Mockito.when(
                authorService.create(Mockito.any(Book.class)).thenReturn(book)
        );

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/authors")
                .accept(MediaType.APPLICATION_JSON).content(exampleAuthorJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

        assertEquals("http://localhost:8080/books/1",
                response.getHeader(HttpHeaders.LOCATION));

    }

    @Test
    public void TestfindAll() throws Exception {
        Book book = new Book();

        Mockito.when(
                authorService.findAll(Mockito.any()).thenReturn(book);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/authors").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "{title:test,type:test,genre:test,number_of_copies:1}";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void Testfind() throws Exception {
        Book book = new Book();

        Mockito.when(
                authorService.findAll(Mockito.any()).thenReturn(book);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/books/1").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "{title:test,type:test,genre:test,number_of_copies:1,authorList:[], publisherList:[]}";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }
}