package coursework.server.controller;

import coursework.server.entity.Author;
import coursework.server.entity.Book;
import coursework.server.service.AuthorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = Author.class)
@WithMockUser
public class AuthorControllerTest {

    @Autowired
    private MockBean mockMvc;

    @MockBean
    private AuthorService authorService;

    String exampleAuthorJson = "{\"name\":\"DC\"}";


    @Test
    public void Testcreate() throws Exception{
        Author author = new Author();

        Mockito.when(
                authorService.create(Mockito.any(Author.class)).thenReturn(author)
                );

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/authors")
                .accept(MediaType.APPLICATION_JSON).content(exampleAuthorJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

        assertEquals("http://localhost:8080/authors/1",
                response.getHeader(HttpHeaders.LOCATION));

    }

    @Test
    public void TestfindAll() throws Exception {
        Author author = new Author();

        Mockito.when(
                authorService.findAll(Mockito.any()).thenReturn(author);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/authors").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "{id:1,name:DC,bookList:[]]}";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void Testfind() throws Exception {
        Author author = new Author();

        Mockito.when(
                authorService.findAll(Mockito.any()).thenReturn(author);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/authors/1").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "{id:1,name:DC,bookList:[]}";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }
}