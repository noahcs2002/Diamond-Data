package com.dd.api.restapi.controllers;

import com.dd.api.auth.models.User;
import com.dd.api.auth.validators.Validator;
import com.dd.api.restapi.models.Pitcher;
import com.dd.api.restapi.models.Team;
import com.dd.api.restapi.services.PitcherService;
import com.dd.api.util.exceptions.NoAccessPermittedException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PitcherController.class)
public class PitcherControllerTests {
    private final String base = "/diamond-data/api/pitchers/";

    private final Long userId = 1234L;
    private Team team;
    private User user;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DataSource dataSource;

    @MockBean
    private PitcherService service;

    @MockBean
    private Validator validator;

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

    @Before
    public void setUp() {
        team = new Team();
        this.user = new User();
        this.user.setId(userId);
        this.team.setUser(user);
    }

    @Test
    public void idealGetPlayerTest() throws Exception {
        Long id = 1L;
        Pitcher player = new Pitcher();
        player.setId(id);

        when(service.getPitcherById(id)).thenReturn(player);
        when(this.validator.validatePitcher(anyLong(), anyLong())).thenReturn(true);

        MvcResult result = mockMvc.perform(get(base + "/get")
                        .param("id", asJsonString(id))
                        .param("userId", asJsonString(userId)))
                .andExpect(status().isOk())
                .andReturn();

        int status = result.getResponse().getStatus();
        String body = result.getResponse().getContentAsString();
        Pitcher foundPlayer = objectMapper.readValue(body, Pitcher.class);

        assertEquals(200, status, 0);
        assertEquals(player, foundPlayer);

        verify(service, times(1)).getPitcherById(any(Long.class));
    }

    @Test(expected = NoAccessPermittedException.class)
    public void getPlayerThrowsNoAccessOnNoAccess() throws Exception {
        Long id = 1L;
        Pitcher player = new Pitcher();
        player.setId(id);

        when(service.getPitcherById(id)).thenReturn(player);
        when(this.validator.validatePitcher(anyLong(), anyLong())).thenReturn(false);

        mockMvc.perform(get(base + "/get")
                        .param("id", asJsonString(id))
                        .param("userId", asJsonString(userId)));
    }

    @Test
    public void idealGetByTeamTest() throws Exception {
        Long teamId = 1L;
        Pitcher player = new Pitcher();
        List<Pitcher> players = new ArrayList<>();
        players.add(player);

        when(service.getPitchersByTeam(teamId)).thenReturn(players);
        when(this.validator.validateTeam(anyLong(), anyLong())).thenReturn(true);

        MvcResult result = mockMvc.perform(get(base + "/get-by-team")
                        .param("teamId", asJsonString(teamId))
                        .param("userId", asJsonString(userId)))
                .andExpect(status().isOk())
                .andReturn();

        int status = result.getResponse().getStatus();

        String data = result.getResponse().getContentAsString();

        List<Pitcher> returnedPlayers = objectMapper.readValue(data, new TypeReference<List<Pitcher>>() {
        });

        assertEquals(200, status, 0);
        assertEquals(players, returnedPlayers);
        verify(service, times(1)).getPitchersByTeam(any(Long.class));
    }

    @Test(expected = NoAccessPermittedException.class)
    public void getByTeamThrowsNoAccessPermittedWhenNoAcess() throws Exception {
        Long teamId = 1L;
        Pitcher player = new Pitcher();
        List<Pitcher> players = new ArrayList<>();
        players.add(player);

        when(service.getPitchersByTeam(teamId)).thenReturn(players);
        when(this.validator.validateTeam(anyLong(), anyLong())).thenReturn(false);

        mockMvc.perform(get(base + "/get-by-team")
                        .param("teamId", asJsonString(teamId))
                        .param("userId", asJsonString(userId)));
    }

    @Test
    public void idealCreateTest() throws Exception {
        Pitcher pitcher = new Pitcher();
        user.setId(1234L);
        this.team.setUser(user);
        this.team.setId(1L);
        pitcher.setTeam(team);

        when(this.service.createPitcher(any())).thenReturn(pitcher);
        when(this.validator.validateTeam(anyLong(), anyLong())).thenReturn(true);

        MvcResult mvcResult = mockMvc.perform(post(base + "/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(pitcher))
                        .param("userId", asJsonString(userId)))
                .andExpect(status().isOk())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String body = mvcResult.getResponse().getContentAsString();
        Pitcher returned = objectMapper.readValue(body, Pitcher.class);

        assertEquals(200, status, 0);
        verify(service, times(1)).createPitcher(any(Pitcher.class));
        assertEquals(pitcher, returned);
    }

    @Test(expected = NoAccessPermittedException.class)
    public void createThrowsNoAccessPermittedWhenNoAcess() throws Exception {
        Pitcher pitcher = new Pitcher();
        user.setId(1234L);
        this.team.setUser(user);
        this.team.setId(1L);
        pitcher.setTeam(team);

        when(this.service.createPitcher(any())).thenReturn(pitcher);
        when(this.validator.validateTeam(anyLong(), anyLong())).thenReturn(false);

        mockMvc.perform(post(base + "/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(pitcher))
                        .param("userId", asJsonString(userId)));
    }

    @Test
    public void idealDeleteTest() throws Exception {
        Long id = 1L;
        when(this.service.deletePitcher(id)).thenReturn(true);
        when(this.validator.validatePitcher(anyLong(), anyLong())).thenReturn(true);

        MvcResult result = mockMvc.perform(delete(base + "/delete")
                        .param("id", asJsonString(id))
                        .param("userId", asJsonString(userId)))
                .andExpect(status().isOk())
                .andReturn();

        int status = result.getResponse().getStatus();
        String content = result.getResponse().getContentAsString();
        Boolean verdict = objectMapper.readValue(content, Boolean.class);

        assertEquals(Boolean.TRUE, verdict);
        assertEquals(200, status, 0);
        verify(service, times(1)).deletePitcher(any(Long.class));
    }

    @Test(expected=NoAccessPermittedException.class)
    public void deleteThrowsNoAccessPermittedWhenNoAccess() throws Exception {
        Long id = 1L;
        when(this.service.deletePitcher(id)).thenReturn(true);
        when(this.validator.validatePitcher(anyLong(), anyLong())).thenReturn(false);

        mockMvc.perform(delete(base + "/delete")
                        .param("id", asJsonString(id))
                        .param("userId", asJsonString(userId)));
    }

    @Test(expected = Exception.class)
    public void getReturns500_whenError() throws Exception {

        String message = "testing exception message";
        Long id = 1L;
        when(this.service.getPitcherById(id)).thenThrow(new Exception());

        MvcResult result = mockMvc.perform(get(base + "/get")
                        .param("id", id.toString())
                        .param("userId", asJsonString(userId)))
                .andExpect(status().is5xxServerError())
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(500, status, 0);
        assertTrue(Objects.requireNonNull(result.getResolvedException()).getMessage().contains(message));
    }

    @Test(expected = Exception.class)
    public void getAllReturns500_whenError() throws Exception {
        when(service.getAll()).thenThrow(new Exception());
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
        when(service.getPitchersByTeam(teamId)).thenThrow(new Exception());

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
        when(service.createPitcher(any(Pitcher.class))).thenThrow(new Exception(message));

        MvcResult result = mockMvc.perform(post(base + "/create"))
                .andExpect(status().is5xxServerError())
                .andReturn();

        assertEquals(500, result.getResponse().getStatus());
        assertTrue(Objects.requireNonNull(result.getResolvedException()).getMessage().contains(message));
    }

    @Test(expected = Exception.class)
    public void updateReturns500_whenError() throws Exception {
        when(service.updatePitcher(any(Long.class), any(Pitcher.class)))
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
        when(service.deletePitcher(anyLong())).thenThrow(new Exception(message));

        MvcResult result = mockMvc.perform(delete(base + "/delete"))
                .andExpect(status().is5xxServerError())
                .andReturn();

        assertEquals(500, result.getResponse().getStatus());
        assertTrue(Objects.requireNonNull(result.getResolvedException()).getMessage().contains(message));
    }
}
