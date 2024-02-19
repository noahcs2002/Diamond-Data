package com.dd.api.auth.controllers;

import com.dd.api.auth.models.User;
import com.dd.api.auth.providers.AuthorizationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
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

import javax.print.attribute.standard.Media;
import javax.sql.DataSource;

import static com.dd.api.testhelper.Helpers.asJsonString;
import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

    @Before
    public void setUp() {
//        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void loginEndpointExists() throws Exception {
        String email = "mockEmail@provider.com";
        String password = "password1234";

        mockMvc.perform(get("/diamond-data/api/auth/login")
                        .param("email", email)
                        .param("password", password)
                        )
                        .andExpect(status().isOk());
    }

    @Test
    public void signUpEndpointExists() throws Exception {

        String email = "mock";
        String password = "password";
        User user = new User();
        user.setPassword(password);
        user.setEmail(email);

        when(service.createUser(user)).thenReturn(user);

        mockMvc.perform(post("/diamond-data/api/auth/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user)))
                .andExpect(status().isOk());
    }





}
