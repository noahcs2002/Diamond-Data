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
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.sql.DataSource;

import java.util.ArrayList;
import java.util.List;

import static com.dd.api.testhelper.Helpers.asJsonString;
import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DefensivePlayerController.class)
public class DefensivePlayerControllerTests {

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

        MvcResult result = mockMvc.perform(get("/diamond-data/api/defensive-players/get")
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
        playerOne.setFirstName("Foo 1");
        playerOne.setLastName("Bar 1");
        playerOne.setId(1L);

        DefensivePlayer playerTwo = new DefensivePlayer();
        playerTwo.setFirstName("Foo 2");
        playerTwo.setLastName("Bar 2");
        playerTwo.setId(2L);

        List<DefensivePlayer> players = new ArrayList<>();
        players.add(playerOne);
        players.add(playerTwo);

        when(service.getAllPlayers()).thenReturn(players);

        MvcResult result = mockMvc.perform(get("/diamond-data/api/defensive-players/get-all"))
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

        MvcResult result = mockMvc.perform(get("/diamond-data/api/defensive-players/get-by-team")
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
        
    }
}
