package com.dd.api.restapi.controllers;

import com.dd.api.restapi.models.OffensivePlayer;
import com.dd.api.restapi.services.OffensivePlayerService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OffensivePlayerController.class)
public class OffensivePlayerControllerTests {

    private final String base = "/diamond-data/api/offensive-players/";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DataSource dataSource;

    @MockBean
    private OffensivePlayerService service;

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
        OffensivePlayer player = new OffensivePlayer();
        player.setId(id);

        when(service.getOffensivePlayer(id)).thenReturn(player);

        MvcResult result = mockMvc.perform(get(base+"/get")
                                    .param("id", asJsonString(id)))
                                    .andExpect(status().isOk())
                                    .andReturn();

        int status = result.getResponse().getStatus();
        String body = result.getResponse().getContentAsString();
        OffensivePlayer foundPlayer = objectMapper.readValue(body, OffensivePlayer.class);

        assertEquals(200, status, 0);
        assertEquals(player, foundPlayer);
    }

    @Test
    public void idealGetAllTest() throws Exception {

        OffensivePlayer playerOne = new OffensivePlayer();
        playerOne.setFirstName("Foo 1");
        playerOne.setLastName("Bar 1");
        playerOne.setId(1L);

        OffensivePlayer playerTwo = new OffensivePlayer();
        playerTwo.setFirstName("Foo 2");
        playerTwo.setLastName("Bar 2");
        playerTwo.setId(2L);

        List<OffensivePlayer> players = new ArrayList<>();
        players.add(playerOne);
        players.add(playerTwo);

        when(service.getAll()).thenReturn(players);

        MvcResult result = mockMvc.perform(get(base+"/get-all"))
                .andExpect(status().isOk())
                .andReturn();

        int status = result.getResponse().getStatus();
        String data = result.getResponse().getContentAsString();
        List<OffensivePlayer> results = objectMapper.readValue(data, new TypeReference<List<OffensivePlayer>>(){});

        assertEquals(200, status, 0);
        assertEquals(players, results);
        verify(service, times(1)).getAll();
    }

    @Test
    public void idealGetByTeamTest() throws Exception {
        Long teamId = 1L;
        OffensivePlayer player = new OffensivePlayer();
        List<OffensivePlayer> players = new ArrayList<>();
        players.add(player);

        when(service.getByTeam(teamId)).thenReturn(players);

        MvcResult result = mockMvc.perform(get(base+"/get-by-team")
                        .param("teamId", asJsonString(teamId)))
                .andExpect(status().isOk())
                .andReturn();

        int status = result.getResponse().getStatus();

        String data = result.getResponse().getContentAsString();

        List<OffensivePlayer> returnedPlayers = objectMapper.readValue(data, new TypeReference<List<OffensivePlayer>>() {});

        assertEquals(200, status, 0);
        assertEquals(players, returnedPlayers);
        verify(service, times(1)).getByTeam(any(Long.class));
    }

    @Test
    public void idealCreateTest() throws Exception {
        OffensivePlayer defensivePlayer = new OffensivePlayer();

        when(this.service.createPlayer(defensivePlayer)).thenReturn(defensivePlayer);

        MvcResult mvcResult = mockMvc.perform(post(base+"/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(defensivePlayer)))
                .andExpect(status().isOk())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String body = mvcResult.getResponse().getContentAsString();
        OffensivePlayer returned = objectMapper.readValue(body, OffensivePlayer.class);

        assertEquals(200, status, 0);
        verify(service, times(1)).createPlayer(any(OffensivePlayer.class));
        assertEquals(defensivePlayer, returned);
    }

    @Test
    public void idealDeleteTest() throws Exception {
        Long id = 1L;
        when(this.service.delete(id)).thenReturn(true);

        MvcResult result = mockMvc.perform(delete(base+"/delete")
                        .param("id", ""+id))
                .andExpect(status().isOk())
                .andReturn();

        int status = result.getResponse().getStatus();
        String content = result.getResponse().getContentAsString();
        Boolean verdict = objectMapper.readValue(content, Boolean.class);

        assertEquals(Boolean.TRUE, verdict);
        assertEquals(200, status, 0);
        verify(service, times(1)).delete(any(Long.class));
    }

    @Test(expected = Exception.class)
    public void getReturns500_whenError() throws Exception {

        String message = "testing exception message";
        Long id = 1L;
        when(this.service.getOffensivePlayer(id)).thenThrow(new Exception());

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
        when(service.getAll()).thenThrow(new Exception());
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
        when(service.getByTeam(teamId)).thenThrow(new Exception());

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
        when(service.createPlayer(any(OffensivePlayer.class))).thenThrow(new Exception(message));

        MvcResult result = mockMvc.perform(post(base+"/create"))
                .andExpect(status().is5xxServerError())
                .andReturn();

        assertEquals(500, result.getResponse().getStatus());
        assertTrue(Objects.requireNonNull(result.getResolvedException()).getMessage().contains(message));
    }

    @Test(expected = Exception.class)
    public void updateReturns500_whenError() throws Exception {
        when(service.update(any(Long.class), any(OffensivePlayer.class)))
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

        MvcResult result = mockMvc.perform(delete(base+"/delete"))
                .andExpect(status().is5xxServerError())
                .andReturn();

        assertEquals(500, result.getResponse().getStatus());
        assertTrue(Objects.requireNonNull(result.getResolvedException()).getMessage().contains(message));
    }

}
