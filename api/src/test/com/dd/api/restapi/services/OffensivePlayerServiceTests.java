package com.dd.api.restapi.services;

import com.dd.api.restapi.controllers.OffensivePlayerController;
import com.dd.api.restapi.models.OffensivePlayer;
import com.dd.api.restapi.models.Team;
import com.dd.api.restapi.repositories.OffensivePlayerRepository;
import jakarta.inject.Inject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class OffensivePlayerServiceTests {

    @Mock
    private OffensivePlayerRepository repository;

    @InjectMocks
    private OffensivePlayerService service;

    @Before
    public void setUp() {
        openMocks(this);
    }

    @Test
    public void createPlayerInvocationTest() {
        OffensivePlayer player = new OffensivePlayer();
        when(this.repository.save(any(OffensivePlayer.class))).thenReturn(player);

        OffensivePlayer res = this.service.createPlayer(player);

        assertEquals(player, res);
        verify(repository, times(1)).save(player);
    }

    @Test
    public void idealGetOffensivePlayerTest() {
        Long id = 1L;
        OffensivePlayer player = new OffensivePlayer();
        player.setId(id);

        when(this.repository.findById(anyLong())).thenReturn(Optional.of(player));

        OffensivePlayer returnedPlayer = this.service.getOffensivePlayer(id);

        assertEquals(player, returnedPlayer);
        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    public void getOffensivePlayerDoesNotCaptureDeletedPlayers() {
        Long id = 1L;
        OffensivePlayer player = new OffensivePlayer();
        player.setId(id);
        player.setGhostedDate(123L);

        when(this.repository.findById(anyLong())).thenReturn(Optional.of(player));

        OffensivePlayer res = this.service.getOffensivePlayer(id);

        assertNull(res);
        verify(this.repository, times(1)).findById(anyLong());
    }

    @Test
    public void idealGetAllTest() {
        OffensivePlayer playerOne = new OffensivePlayer();
        OffensivePlayer playerTwo = new OffensivePlayer();
        OffensivePlayer playerThree = new OffensivePlayer();

        List<OffensivePlayer> players = List.of(playerOne, playerTwo, playerThree);

        when(repository.findAll()).thenReturn(players);

        List<OffensivePlayer> res = this.service.getAll();

        assertEquals(players, res);
        verify(repository, times(1)).findAll();
    }

    @Test
    public void getAllDoesNotCaptureDeletedPlayers() {
        OffensivePlayer playerOne = new OffensivePlayer();
        OffensivePlayer playerTwo = new OffensivePlayer();
        OffensivePlayer playerThree = new OffensivePlayer();
        playerThree.setGhostedDate(1223123L);

        List<OffensivePlayer> players = List.of(playerOne, playerTwo, playerThree);
        List<OffensivePlayer> expected = List.of(playerOne, playerTwo);

        when(repository.findAll()).thenReturn(players);

        List<OffensivePlayer> res = this.service.getAll();

        assertEquals(expected, res);
        verify(repository, times(1)).findAll();
    }

    @Test
    public void idealGetByTeamTest() {
        Team team = new Team();
        Long teamId = 1234L;
        team.setId(teamId);

        OffensivePlayer playerOne = new OffensivePlayer();
        OffensivePlayer playerTwo = new OffensivePlayer();
        OffensivePlayer playerThree = new OffensivePlayer();

        playerOne.setTeam(team);
        playerTwo.setTeam(team);
        playerThree.setTeam(team);

        List<OffensivePlayer> players = List.of(playerOne, playerTwo, playerThree);

        when(repository.findAll()).thenReturn(players);

        List<OffensivePlayer> res = this.service.getByTeam(teamId);

        assertEquals(players, res);
        verify(repository, times(1)).findAll();
    }

    @Test
    public void getByTeamFiltersByTeamAppropriately() {
        Team team = new Team();
        Team throwaway = new Team();
        throwaway.setId(1234134L);
        Long teamId = 1234L;
        team.setId(teamId);

        OffensivePlayer playerOne = new OffensivePlayer();
        OffensivePlayer playerTwo = new OffensivePlayer();
        OffensivePlayer playerThree = new OffensivePlayer();

        playerOne.setTeam(team);
        playerTwo.setTeam(team);
        playerThree.setTeam(throwaway);

        List<OffensivePlayer> players = List.of(playerOne, playerTwo, playerThree);
        List<OffensivePlayer> exp = List.of(playerOne, playerTwo);

        when(repository.findAll()).thenReturn(players);

        List<OffensivePlayer> res = this.service.getByTeam(teamId);

        assertEquals(exp, res);
        verify(repository, times(1)).findAll();
    }

    @Test
    public void getByTeamDoesNotCaptureDeletedPlayers() {
        Team team = new Team();
        Long teamId = 1234L;
        team.setId(teamId);

        OffensivePlayer playerOne = new OffensivePlayer();
        OffensivePlayer playerTwo = new OffensivePlayer();
        OffensivePlayer playerThree = new OffensivePlayer();

        playerThree.setGhostedDate(1234L);

        playerOne.setTeam(team);
        playerTwo.setTeam(team);
        playerThree.setTeam(team);

        List<OffensivePlayer> players = List.of(playerOne, playerTwo, playerThree);
        List<OffensivePlayer> exp = List.of(playerOne, playerTwo);

        when(repository.findAll()).thenReturn(players);

        List<OffensivePlayer> res = this.service.getByTeam(teamId);

        assertEquals(exp, res);
        verify(repository, times(1)).findAll();
    }

    @Test
    public void updateInvocationTest() {
        OffensivePlayer player = new OffensivePlayer();

        when(repository.save(any(OffensivePlayer.class))).thenReturn(player);

        this.service.update(1L, player);
        verify(repository, times(1)).save(any(OffensivePlayer.class));
    }

    @Test
    public void deleteInvocationTest() {
        OffensivePlayer player = new OffensivePlayer();
        when(repository.findById(anyLong())).thenReturn(Optional.of(player));

        service.delete(1L);

        verify(repository, times(1)).findById(anyLong());
        verify(repository, times(1)).save(any());
    }
}
