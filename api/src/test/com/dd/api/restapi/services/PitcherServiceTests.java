package com.dd.api.restapi.services;

import com.dd.api.restapi.models.Pitcher;
import com.dd.api.restapi.models.Team;
import com.dd.api.restapi.repositories.PitcherRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class PitcherServiceTests {
    @Mock
    private PitcherRepository repository;

    @Mock
    private TeamService teamService;

    @InjectMocks
    private PitcherService service;



    @Before
    public void setUp() {
        openMocks(this);
    }

    @Test
    public void idealCreatePitcherTest() {

        // Arrange
        Pitcher player = new Pitcher();
        when(repository.save(any(Pitcher.class))).thenReturn(player);

        // Act
        Pitcher returnedPlayer = this.service.createPitcher(player, 1L);

        // Assert
        assertEquals(player, returnedPlayer);
        verify(repository, times(1)).save(player);
    }

    @Test
    public void idealGetPitcherTest() {

        // Arrange
        Long id = 1L;
        Pitcher player = new Pitcher();
        player.setGhostedDate(0);
        when(this.repository.findById(id)).thenReturn(Optional.of(player));

        // Act
        Pitcher returnedPlayer = this.service.getPitcherById(id);

        // Assert
        assertEquals(player, returnedPlayer);
        verify(this.repository, times(1)).findById(anyLong());
    }

    @Test
    public void getPitcherByIdReturnsEmptyOnDeletedUser() {

        // Arrange
        Long id = 1L;
        Pitcher player = new Pitcher();
        player.setId(id);
        player.setGhostedDate(1012120123L);
        when(this.repository.findById(id)).thenReturn(Optional.of(player));

        // Act
        Pitcher returnedPlayer = this.service.getPitcherById(id);

        // Assert
        assertNull(returnedPlayer);
        verify(this.repository, times(1)).findById(anyLong());
    }

    @Test
    public void getPitcherByIdReturnsNullWhenNoPlayerFound() {
        when(this.repository.findById(anyLong())).thenReturn(Optional.empty());

        Pitcher player = this.service.getPitcherById(1L);
        assertNull(player);
    }

    @Test
    public void idealGetPlayersByTeam() {

        // Arrange
        Long teamId = 1L;
        Team team = new Team();

        team.setId(teamId);

        Pitcher playerOne = new Pitcher();

        playerOne.setTeam(team);

        List<Pitcher> playerList = List.of(playerOne);
        when(repository.findAll()).thenReturn(playerList);

        List<Pitcher> expected = List.of(playerOne);

        // Act
        List<Pitcher> returnedPlayers = this.service.getPitchersByTeam(teamId);

        // Assert
        assertEquals(expected, returnedPlayers);
        verify(repository, times(1)).findAll();
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

        Pitcher playerWithValidId = new Pitcher();
        Pitcher playerWithInvalidId = new Pitcher();

        playerWithInvalidId.setTeam(badTeam);
        playerWithValidId.setTeam(team);

        List<Pitcher> players = List.of(playerWithInvalidId, playerWithValidId);
        List<Pitcher> expected = List.of(playerWithValidId);

        when(this.repository.findAll()).thenReturn(players);

        // Act
        List<Pitcher> result = this.service.getPitchersByTeam(teamId);

        // Assert
        assertEquals(expected, result);
        verify(this.repository, times(1)).findAll();
    }

    @Test
    public void getPlayersByTeamDoesNotReturnDeletedPlayers() {
        // Arrange
        Long teamId = 1L;

        Team team = new Team();

        team.setId(teamId);

        Pitcher validPlayer = new Pitcher();
        Pitcher invalidPlayer = new Pitcher();

        validPlayer.setTeam(team);
        invalidPlayer.setTeam(team);

        invalidPlayer.setGhostedDate(1234L);

        List<Pitcher> players = List.of(invalidPlayer, validPlayer);
        List<Pitcher> expected = List.of(validPlayer);

        when(this.repository.findAll()).thenReturn(players);

        // Act
        List<Pitcher> result = this.service.getPitchersByTeam(teamId);

        // Assert
        assertEquals(expected, result);
        verify(this.repository, times(1)).findAll();
    }

    @Test
    public void idealGetAllPlayersTest() {

        // Arrange
        Pitcher playerOne = new Pitcher();
        Pitcher playerTwo = new Pitcher();

        List<Pitcher> players = List.of(playerTwo, playerOne);
        List<Pitcher> expected = List.of(playerTwo, playerOne);

        when(this.repository.findAll()).thenReturn(players);

        // Act
        List<Pitcher> result = this.service.getAll();

        // Assert
        assertEquals(expected, result);
        verify(repository, times(1)).findAll();
    }

    @Test
    public void getPlayersReturnsOnlyNotDeletedPlayers() {
        // Arrange
        Pitcher playerOne = new Pitcher();
        Pitcher playerTwo = new Pitcher();

        playerTwo.setGhostedDate(102234);

        List<Pitcher> players = List.of(playerTwo, playerOne);
        List<Pitcher> expected = List.of(playerOne);

        when(this.repository.findAll()).thenReturn(players);

        // Act
        List<Pitcher> result = this.service.getAll();

        // Assert
        assertEquals(expected, result);
        verify(repository, times(1)).findAll();
    }

    @Test
    public void deletePitcherVerificationTest() {
        Long id = 123L;
        Pitcher player = new Pitcher();
        player.setId(id);

        when(repository.findById(anyLong())).thenReturn(Optional.of(player));

        this.service.deletePitcher(id);

        verify(repository, times(1)).findById(anyLong());
        verify(repository, times(1)).save(player);
    }
}
