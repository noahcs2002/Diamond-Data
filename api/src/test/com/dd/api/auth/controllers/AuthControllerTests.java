package com.dd.api.auth.controllers;

import com.dd.api.auth.models.User;
import com.dd.api.auth.providers.AuthorizationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.sql.DataSource;

import static com.dd.api.testhelper.Helpers.asJsonString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthController.class)
public class AuthControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorizationService service;

    @TestConfiguration
    static class TestDatabaseConfiguration {
        @Bean
        public DataSource dataSource() {
            return new EmbeddedDatabaseBuilder()
                    .setType(EmbeddedDatabaseType.H2)
                    .build();
        }
    }

    @Autowired
    private DataSource dataSource;

    @Test
    public void simpleLoginTest() throws Exception {
        String email = "mockEmail@provider.com";
        String password = "password1234";

        MvcResult result = mockMvc.perform(get("/diamond-data/api/auth/login")
                        .param("email", email)
                        .param("password", password))
                .andExpect(status().isOk())
                .andReturn();

        int status = result.getResponse().getStatus();

        assertEquals(200, status, 0);
        verify(service, times(1)).login(any(String.class), any(String.class));
    }

    @Test
    public void simpleSignUpTest() throws Exception {

        String email = "mock";
        String password = "password";
        User user = new User();
        user.setPassword(password);
        user.setEmail(email);

        when(service.createUser(user)).thenReturn(user);

        MvcResult result = mockMvc.perform(post("/diamond-data/api/auth/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                .andExpect(status().isOk())
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(200, status, 0);

        verify(service, times(1)).createUser(any(User.class));
    }

    @Test
    public void simpleDeleteAccountTest() throws Exception {
        Long id = 1L;
        when(service.deleteUser(id)).thenReturn(true);

        MvcResult result = mockMvc.perform(delete("/diamond-data/api/auth/delete-account")
                        .param("id", asJsonString(id)))
                .andExpect(status().isOk())
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(200, status, 0);

        verify(service, times(1)).deleteUser(id);
    }
}