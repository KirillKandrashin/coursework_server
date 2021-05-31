package coursework.server.controller;

import coursework.server.entity.User;
import coursework.server.service.UserService;
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

public class UserControllerTest {


    @Autowired
    private MockBean mockMvc;

    @MockBean
    private UserService userService;

    String exampleUserJson = "{\"name\":\"test_user\"password\":\"qwerty\"}";


    @Test
    public void Testcreate() throws Exception{
        User user = new User();

        Mockito.when(
                userService.create(Mockito.any(User.class)).thenReturn(user)
        );

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users")
                .accept(MediaType.APPLICATION_JSON).content(exampleUserJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

        assertEquals("http://localhost:8080/users/1",
                response.getHeader(HttpHeaders.LOCATION));

    }

    @Test
    public void TestfindAll() throws Exception {
        User user = new User();

        Mockito.when(
                userService.findAll(Mockito.any()).thenReturn(user);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/users").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "{id:1,name:test_user,password:qwerty}";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void Testfind() throws Exception {
        User user = new User();

        Mockito.when(
                userService.findAll(Mockito.any()).thenReturn(user);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/users/1").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "{id:1,name:test_user,password:qwerty}";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }
}