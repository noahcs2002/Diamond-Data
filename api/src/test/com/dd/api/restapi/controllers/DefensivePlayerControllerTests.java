package com.dd.api.restapi.controllers;

import com.dd.api.restapi.models.DefensivePlayer;
import com.dd.api.restapi.services.DefensivePlayerService;
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
import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DefensivePlayerController.class)
public class DefensivePlayerControllerTests {

    private final String base = "/diamond-data/api/defensive-players/";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DataSource dataSource;

    @MockBean
    private DefensivePlayerService service;

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
    public void idealGetPlayerTest() throws Exception {
        Long id = 1L;
        DefensivePlayer player = new DefensivePlayer();
        player.setId(id);

        when(service.getDefensivePlayer(id)).thenReturn(player);

        MvcResult result = mockMvc.perform(get(base+"/get")
                        .param("id", asJsonString(id)))
                        .andExpect(status().isOk())
                        .andReturn();

        int status = result.getResponse().getStatus();
        String body = result.getResponse().getContentAsString();
        DefensivePlayer foundPlayer = objectMapper.readValue(body, DefensivePlayer.class);

        assertEquals(200, status, 0);
        assertEquals(player, foundPlayer);

        verify(service, times(1)).getDefensivePlayer(any(Long.class));
    }

    @Test
    public void idealGetAllTest() throws Exception {

        DefensivePlayer playerOne = new DefensivePlayer();
        playerOne.setId(1L);

        DefensivePlayer playerTwo = new DefensivePlayer();
        playerTwo.setId(2L);

        List<DefensivePlayer> players = new ArrayList<>();
        players.add(playerOne);
        players.add(playerTwo);

        when(service.getAllPlayers()).thenReturn(players);

        MvcResult result = mockMvc.perform(get(base+"/get-all"))
                .andExpect(status().isOk())
                .andReturn();

        int status = result.getResponse().getStatus();
        String data = result.getResponse().getContentAsString();
        List<DefensivePlayer> results = objectMapper.readValue(data, new TypeReference<List<DefensivePlayer>>(){});

        assertEquals(200, status, 0);
        assertEquals(players, results);
        verify(service, times(1)).getAllPlayers();
    }

    @Test
    public void idealGetByTeamTest() throws Exception {
        Long teamId = 1L;
        DefensivePlayer player = new DefensivePlayer();
        List<DefensivePlayer> players = new ArrayList<>();
        players.add(player);

        when(service.getAllPlayersByTeam(teamId)).thenReturn(players);

        MvcResult result = mockMvc.perform(get(base+"/get-by-team")
                        .param("teamId", asJsonString(teamId)))
                        .andExpect(status().isOk())
                        .andReturn();

        int status = result.getResponse().getStatus();

        String data = result.getResponse().getContentAsString();

        List<DefensivePlayer> returnedPlayers = objectMapper.readValue(data, new TypeReference<List<DefensivePlayer>>() {});

        assertEquals(200, status, 0);
        assertEquals(players, returnedPlayers);
        verify(service, times(1)).getAllPlayersByTeam(any(Long.class));
    }

    @Test
    public void idealCreateTest() throws Exception {
       DefensivePlayer defensivePlayer = new DefensivePlayer();

       when(this.service.createDefensivePlayer(defensivePlayer)).thenReturn(defensivePlayer);

       MvcResult mvcResult = mockMvc.perform(post(base+"/create")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(asJsonString(defensivePlayer)))
               .andExpect(status().isOk())
               .andReturn();

       int status = mvcResult.getResponse().getStatus();
       String body = mvcResult.getResponse().getContentAsString();
       DefensivePlayer returned = objectMapper.readValue(body, DefensivePlayer.class);

       assertEquals(200, status, 0);
       verify(service, times(1)).createDefensivePlayer(any(DefensivePlayer.class));
       assertEquals(defensivePlayer, returned);
    }

    @Test
    public void idealDeleteTest() throws Exception {
        Long id = 1L;
        when(this.service.deletePlayer(id)).thenReturn(true);

        MvcResult result = mockMvc.perform(delete(base+"/delete")
                        .param("id", ""+id))
                .andExpect(status().isOk())
                .andReturn();

        int status = result.getResponse().getStatus();
        String content = result.getResponse().getContentAsString();
        Boolean verdict = objectMapper.readValue(content, Boolean.class);

        assertEquals(Boolean.TRUE, verdict);
        assertEquals(200, status, 0);
        verify(service, times(1)).deletePlayer(any(Long.class));
    }

    @Test(expected = Exception.class)
    public void getReturns500_whenError() throws Exception {

        String message = "testing exception message";
        Long id = 1L;
        when(this.service.getDefensivePlayer(id)).thenThrow(new Exception());

        MvcResult result = mockMvc.perform(get(base+"/get")
                        .param("id", id.toString()))
                .andExpect(status().is5xxServerError())
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(500, status, 0);
        assertTrue(Objects.requireNonNull(result.getResolvedException()).getMessage().contains(message));
    }

    @Test(expected = Exception.class)
    public void getAllReturns500_whenError() throws Exception {
        when(service.getAllPlayers()).thenThrow(new Exception());
        String message = "testing exception message";

        MvcResult result = mockMvc.perform(get(base+"/get-all"))
                .andExpect(status().is5xxServerError())
                .andReturn();

        assertEquals(500, result.getResponse().getStatus());
        assertTrue(Objects.requireNonNull(result.getResolvedException()).getMessage().contains(message));
    }

    @Test(expected = Exception.class)
    public void getByTeamReturns500_whenError() throws Exception {
        Long teamId = 1L;
        String message = "testing exception message";
        when(service.getAllPlayersByTeam(teamId)).thenThrow(new Exception());

        MvcResult result = mockMvc.perform(get(base+"get-by-team")
                        .param("teamId", teamId.toString()))
                .andExpect(status().is5xxServerError())
                .andReturn();

        assertEquals(500, result.getResponse().getStatus());
        assertTrue(Objects.requireNonNull(result.getResolvedException()).getMessage().contains(message));
    }

    @Test(expected = Exception.class)
    public void createReturns500_whenError() throws Exception{
        String message = "testing exception message";
        when(service.createDefensivePlayer(any(DefensivePlayer.class))).thenThrow(new Exception(message));

        MvcResult result = mockMvc.perform(post(base+"/create"))
                .andExpect(status().is5xxServerError())
                .andReturn();

        assertEquals(500, result.getResponse().getStatus());
        assertTrue(Objects.requireNonNull(result.getResolvedException()).getMessage().contains(message));
    }

    @Test(expected = Exception.class)
    public void updateReturns500_whenError() throws Exception {
        when(service.updateDefensivePlayer(any(Long.class), any(DefensivePlayer.class)))
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
        when(service.deletePlayer(anyLong())).thenThrow(new Exception(message));

        MvcResult result = mockMvc.perform(delete(base+"/delete"))
                .andExpect(status().is5xxServerError())
                .andReturn();

        assertEquals(500, result.getResponse().getStatus());
        assertTrue(Objects.requireNonNull(result.getResolvedException()).getMessage().contains(message));
    }
}
