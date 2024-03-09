package com.dd.api.restapi.controllers;

import com.dd.api.restapi.models.OffensivePlayer;
import com.dd.api.restapi.models.Team;
import com.dd.api.restapi.services.OffensivePlayerService;
import com.dd.api.restapi.services.TeamService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.dd.api.testhelper.Helpers.asJsonString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TeamController.class)
public class TeamControllerTests {

    private final String base = "/diamond-data/api/teams/";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DataSource dataSource;

    @MockBean
    private TeamService service;

    @Autowired
    private ObjectMapper objectMapper;

    @TestConfiguration
    static class TestDatabaseConfiguration {
        @Bean
        public DataSource dataSource() {
            return new EmbeddedDatabaseBuilder()
                    .setType(EmbeddedDatabaseType.H2)
                    .build();
        }
    }

    @Test
    public void idealGetTeamTest() throws Exception {
        Long id = 1L;
        Team team = new Team();
        team.setId(id);

        when(service.getTeamById(anyLong())).thenReturn(team);

        MvcResult result = mockMvc.perform(get(base + "/get")
                        .param("id", asJsonString(id)))
                .andExpect(status().isOk())
                .andReturn();

        int status = result.getResponse().getStatus();
        String body = result.getResponse().getContentAsString();
        Team res = objectMapper.readValue(body, Team.class);

        assertEquals(200, status, 0);
        assertEquals(team, res);
    }

    @Test
    public void idealGetAllTest() throws Exception {
        Long id = 1L;
        Team team = new Team();
        team.setId(id);

        List<Team> teams = List.of(team);

        when(service.getAllTeams()).thenReturn(teams);

        MvcResult result = mockMvc.perform(get(base + "/get-all"))
                .andExpect(status().isOk())
                .andReturn();

        int status = result.getResponse().getStatus();
        String body = result.getResponse().getContentAsString();
        List<Team> res = objectMapper.readValue(body, new TypeReference<List<Team>>() {
        });

        assertEquals(200, status, 0);
        assertEquals(teams, res);
    }

    @Test
    public void idealCreateTest() throws Exception {
        Team team = new Team();
        team.setId(1L);
        team.setName("Test");

        when(service.createTeam(any(Team.class))).thenReturn(team);

        MvcResult result = mockMvc.perform(post(base + "/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(team)))
                .andExpect(status().isOk())
                .andReturn();

        int status = result.getResponse().getStatus();
        String body = result.getResponse().getContentAsString();

        Team res = objectMapper.readValue(body, Team.class);

        assertEquals(team, res);
        assertEquals(200, status, 0);
        verify(service, times(1)).createTeam(any(Team.class));
    }

    @Test
    public void idealDeleteTest() throws Exception {
        Long id = 1L;
        when(this.service.delete(anyLong())).thenReturn(true);

        MvcResult result = mockMvc.perform(delete(base + "/delete")
                        .param("id", asJsonString(id)))
                .andExpect(status().isOk())
                .andReturn();

        int status = result.getResponse().getStatus();
        String content = result.getResponse().getContentAsString();
        Boolean verdict = objectMapper.readValue(content, Boolean.class);

        assertEquals(Boolean.TRUE, verdict);
        assertEquals(200, status, 0);
        verify(service, times(1)).delete(anyLong());
    }

    @Test(expected = Exception.class)
    public void getReturns500_whenError() throws Exception {
        String message = "testing exception message";
        Long id = 1L;
        when(this.service.getTeamById(id)).thenThrow(new Exception());

        MvcResult result = mockMvc.perform(get(base + "/get")
                        .param("id", id.toString()))
                .andExpect(status().is5xxServerError())
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(500, status, 0);
        assertTrue(Objects.requireNonNull(result.getResolvedException()).getMessage().contains(message));

    }

    @Test(expected = Exception.class)
    public void getAllReturns500_whenError() throws Exception {
        when(service.getAllTeams()).thenThrow(new Exception());
        String message = "testing exception message";

        MvcResult result = mockMvc.perform(get(base + "/get-all"))
                .andExpect(status().is5xxServerError())
                .andReturn();

        assertEquals(500, result.getResponse().getStatus());
        assertTrue(Objects.requireNonNull(result.getResolvedException()).getMessage().contains(message));
    }

    @Test(expected = Exception.class)
    public void getByTeamReturns500_whenError() throws Exception {
        Long teamId = 1L;
        String message = "testing exception message";
        when(service.getTeamById(teamId)).thenThrow(new Exception());

        MvcResult result = mockMvc.perform(get(base + "get-by-team")
                        .param("teamId", teamId.toString()))
                .andExpect(status().is5xxServerError())
                .andReturn();

        assertEquals(500, result.getResponse().getStatus());
        assertTrue(Objects.requireNonNull(result.getResolvedException()).getMessage().contains(message));
    }

    @Test(expected = Exception.class)
    public void createReturns500_whenError() throws Exception {
        String message = "testing exception message";
        when(service.createTeam(any(Team.class))).thenThrow(new Exception(message));

        MvcResult result = mockMvc.perform(post(base + "/create"))
                .andExpect(status().is5xxServerError())
                .andReturn();

        assertEquals(500, result.getResponse().getStatus());
        assertTrue(Objects.requireNonNull(result.getResolvedException()).getMessage().contains(message));
    }

    @Test(expected = Exception.class)
    public void updateReturns500_whenError() throws Exception {
        when(service.updateTeam(any(Long.class), any(Team.class)))
                .thenThrow(new Exception());
        String message = "testing exception message";

        MvcResult result = mockMvc.perform(put(base + "/update"))
                .andExpect(status().is5xxServerError())
                .andReturn();

        assertEquals(500, result.getResponse().getStatus());
        assertTrue(Objects.requireNonNull(result.getResolvedException()).getMessage().contains(message));
    }

    @Test(expected = Exception.class)
    public void deleteReturns500_whenError() throws Exception {
        String message = "testing exception message";
        when(service.delete(anyLong())).thenThrow(new Exception(message));

        MvcResult result = mockMvc.perform(delete(base + "/delete"))
                .andExpect(status().is5xxServerError())
                .andReturn();

        assertEquals(500, result.getResponse().getStatus());
        assertTrue(Objects.requireNonNull(result.getResolvedException()).getMessage().contains(message));
    }

}

