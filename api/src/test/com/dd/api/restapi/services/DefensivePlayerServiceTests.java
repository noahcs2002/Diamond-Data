package com.dd.api.restapi.services;

import com.dd.api.restapi.models.DefensivePlayer;
import com.dd.api.restapi.models.Team;
import com.dd.api.restapi.repositories.DefensivePlayerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class DefensivePlayerServiceTests {

    @Mock
    private DefensivePlayerRepository defensivePlayerRepository;

    @InjectMocks
    private DefensivePlayerService service;

    @Before
    public void setUp() {
        openMocks(this);
    }

    @Test
    public void idealCreateDefensivePlayerTest() {

        // Arrange
        DefensivePlayer player = new DefensivePlayer();
        when(defensivePlayerRepository.save(player)).thenReturn(player);

        // Act
        DefensivePlayer returnedPlayer = this.service.createDefensivePlayer(player);

        // Assert
        assertEquals(player, returnedPlayer);
        verify(defensivePlayerRepository, times(1)).save(player);
    }

    @Test
    public void idealGetDefensivePlayerTest() {

        // Arrange
        Long id = 1L;
        DefensivePlayer player = new DefensivePlayer();
        player.setGhostedDate(0);
        when(this.defensivePlayerRepository.findById(id)).thenReturn(Optional.of(player));

        // Act
        DefensivePlayer returnedPlayer = this.service.getDefensivePlayer(id);

        // Assert
        assertEquals(player, returnedPlayer);
        verify(this.defensivePlayerRepository, times(1)).findById(anyLong());
    }

    @Test
    public void getDefensivePlayerReturnsEmptyOnDeletedUser() {

        // Arrange
        Long id = 1L;
        DefensivePlayer player = new DefensivePlayer();
        player.setId(id);
        player.setGhostedDate(1012120123L);
        when(this.defensivePlayerRepository.findById(id)).thenReturn(Optional.of(player));

        // Act
        DefensivePlayer returnedPlayer = this.service.getDefensivePlayer(id);

        // Assert
        assertNull(returnedPlayer);
        verify(this.defensivePlayerRepository, times(1)).findById(anyLong());
    }

    @Test
    public void getDefensivePlayerReturnsNullWhenNoPlayerFound() {
        when(this.defensivePlayerRepository.findById(anyLong())).thenReturn(Optional.empty());

        DefensivePlayer player = this.service.getDefensivePlayer(1L);
        assertNull(player);
    }

    @Test
    public void idealGetPlayersByTeam() {

        // Arrange
        Long teamId = 1L;
        Team team = new Team();

        team.setId(teamId);

        DefensivePlayer playerOne = new DefensivePlayer();

        playerOne.setTeam(team);

        List<DefensivePlayer> playerList = List.of(playerOne);
        when(defensivePlayerRepository.findAll()).thenReturn(playerList);

        List<DefensivePlayer> expected = List.of(playerOne);

        // Act
        List<DefensivePlayer> returnedPlayers = this.service.getAllPlayersByTeam(teamId);

        // Assert
        assertEquals(expected, returnedPlayers);
        verify(defensivePlayerRepository, times(1)).findAll();
    }

    @Test
    public void getPlayersByTeamFindsOnlyPlayersOnOneTeam() {

        // Arrange
        Long teamId = 1L;
        Long badTeamId = 1234L;

        Team team = new Team();
        Team badTeam = new Team();

        badTeam.setId(badTeamId);
        team.setId(teamId);

        DefensivePlayer playerWithValidId = new DefensivePlayer();
        DefensivePlayer playerWithInvalidId = new DefensivePlayer();

        playerWithInvalidId.setTeam(badTeam);
        playerWithValidId.setTeam(team);

        List<DefensivePlayer> players = List.of(playerWithInvalidId, playerWithValidId);
        List<DefensivePlayer> expected = List.of(playerWithValidId);

        when(this.defensivePlayerRepository.findAll()).thenReturn(players);

        // Act
        List<DefensivePlayer> result = this.service.getAllPlayersByTeam(teamId);

        // Assert
        assertEquals(expected, result);
        verify(this.defensivePlayerRepository, times(1)).findAll();
    }

    @Test
    public void getPlayersByTeamDoesNotReturnDeletedPlayers() {
        // Arrange
        Long teamId = 1L;

        Team team = new Team();

        team.setId(teamId);

        DefensivePlayer validPlayer = new DefensivePlayer();
        DefensivePlayer invalidPlayer = new DefensivePlayer();

        validPlayer.setTeam(team);
        invalidPlayer.setTeam(team);

        invalidPlayer.setGhostedDate(1234L);

        List<DefensivePlayer> players = List.of(invalidPlayer, validPlayer);
        List<DefensivePlayer> expected = List.of(validPlayer);

        when(this.defensivePlayerRepository.findAll()).thenReturn(players);

        // Act
        List<DefensivePlayer> result = this.service.getAllPlayersByTeam(teamId);

        // Assert
        assertEquals(expected, result);
        verify(this.defensivePlayerRepository, times(1)).findAll();
    }

    @Test
    public void idealGetAllPlayersTest() {

        // Arrange
        DefensivePlayer playerOne = new DefensivePlayer();
        DefensivePlayer playerTwo = new DefensivePlayer();

        List<DefensivePlayer> players = List.of(playerTwo, playerOne);
        List<DefensivePlayer> expected = List.of(playerTwo, playerOne);

        when(this.defensivePlayerRepository.findAll()).thenReturn(players);

        // Act
        List<DefensivePlayer> result = this.service.getAllPlayers();

        // Assert
        assertEquals(expected, result);
        verify(defensivePlayerRepository, times(1)).findAll();
    }

    @Test
    public void getPlayersReturnsOnlyNotDeletedPlayers() {
        // Arrange
        DefensivePlayer playerOne = new DefensivePlayer();
        DefensivePlayer playerTwo = new DefensivePlayer();

        playerTwo.setGhostedDate(102234);

        List<DefensivePlayer> players = List.of(playerTwo, playerOne);
        List<DefensivePlayer> expected = List.of(playerOne);

        when(this.defensivePlayerRepository.findAll()).thenReturn(players);

        // Act
        List<DefensivePlayer> result = this.service.getAllPlayers();

        // Assert
        assertEquals(expected, result);
        verify(defensivePlayerRepository, times(1)).findAll();
    }

    @Test
    public void deletePlayerVerificationTest() {
        Long id = 123L;
        DefensivePlayer player = new DefensivePlayer();
        player.setId(id);

        when(defensivePlayerRepository.findById(anyLong())).thenReturn(Optional.of(player));

        this.service.deletePlayer(id);

        verify(defensivePlayerRepository, times(1)).findById(anyLong());
        verify(defensivePlayerRepository, times(1)).save(player);
    }
}