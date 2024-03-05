package com.dd.api.restapi.services;

import com.dd.api.auth.providers.AuthorizationService;
import com.dd.api.restapi.models.Team;
import com.dd.api.restapi.repositories.TeamRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class TeamServiceTests {

    @Mock
    private TeamRepository repository;
    @Mock
    private AuthorizationService authorizationService;

    @InjectMocks
    private TeamService service;

    @Before
    public void setUp() {
        openMocks(this);
    }

    @Test
    public void idealCreateTeamTest() {
        Team team = new Team();
        when(this.repository.save(team)).thenReturn(team);

        Team res = this.service.createTeam(team);

        assertEquals(team, res);
        verify(this.repository, times(1)).save(team);
    }

    @Test
    public void idealGetTeamTest() {
        Long teamId = 1L;
        Team team = new Team();
        team.setId(teamId);
        when(repository.findById(anyLong())).thenReturn(Optional.of(team));

        Team res = this.service.getTeamById(teamId);

        assertEquals(team, res);
        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    public void getTeamReturnsNull_whenIdFindsNoTeam() {
        Long teamId = 1L;
        Team team = new Team();
        team.setId(teamId);
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        Team res = this.service.getTeamById(teamId);

        assertNull(res);
        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    public void idealGetAllTeamsTest() {
        Team teamOne = new Team();
        Team teamTwo = new Team();
        List<Team> teams = List.of(teamOne, teamTwo);

        when(this.repository.findAll()).thenReturn(teams);

        List<Team> res = this.service.getAllTeams();

        assertEquals(teams, res);
        verify(repository, times(1)).findAll();
    }

    @Test
    public void getAllTeamsReturnsOnlyNonDeletedTeams() {
        Team teamOne = new Team();
        Team teamTwo = new Team();
        teamTwo.setGhostedDate(1234L);

        List<Team> teams = List.of(teamOne, teamTwo);
        when(this.repository.findAll()).thenReturn(teams);

        List<Team> res = this.service.getAllTeams();

        assertEquals(List.of(teamOne), res);
        verify(this.repository, times(1)).findAll();
    }

    @Test
    public void updateTeamTest() {
        Long id = 1L;
        Team team = new Team();
        team.setId(id);

        Team newModel = new Team();


        when(repository.save(newModel)).thenReturn(team);

        Team res = this.service.updateTeam(id, newModel);
        assertEquals(res, team);
        verify(repository, times(1)).save(newModel);
    }

    @Test
    public void idealDeleteTeamTest() {
        Long id = 1L;
        Team team = new Team();
        team.setId(id);

        when(repository.findById(id)).thenReturn(Optional.of(team));
        boolean res = this.service.delete(id);

        assertTrue(res);
        verify(repository, times(1)).save(team);
        verify(repository, times(1)).findById(anyLong());
    }
}
