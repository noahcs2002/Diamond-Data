package com.dd.api.restapi.services;

import com.dd.api.auth.models.User;
import com.dd.api.auth.providers.AuthorizationService;
import com.dd.api.restapi.models.Team;
import com.dd.api.restapi.repositories.TeamRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.management.ObjectName;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        User user = new User();
        user.setId(1L);
        Team teamOne = new Team();
        Team teamTwo = new Team();
        teamOne.setUser(user);
        teamTwo.setUser(user);
        List<Team> teams = List.of(teamOne, teamTwo);

        when(this.repository.findAll()).thenReturn(teams);

        List<Team> res = this.service.getAllTeams(1L);

        assertEquals(teams, res);
        verify(repository, times(1)).findAll();
    }

    @Test
    public void getAllTeamsReturnsOnlyNonDeletedTeams() {
        Team teamOne = new Team();
        Team teamTwo = new Team();
        User user = new User();
        user.setId(1L);
        teamTwo.setGhostedDate(1234L);
        teamOne.setUser(user);
        teamTwo.setUser(user);

        List<Team> teams = List.of(teamOne, teamTwo);
        when(this.repository.findAll()).thenReturn(teams);

        List<Team> res = this.service.getAllTeams(1L);

        assertEquals(List.of(teamOne), res);
        verify(this.repository, times(1)).findAll();
    }

    @Test
    public void getAllTeamsReturnsTeamsWithCorrectUser() {
        User user = new User();
        user.setId(1L);
        User user2 = new User();
        user2.setId(2L);

        Team teamOne = new Team();
        Team teamTwo = new Team();

        teamOne.setUser(user);
        teamTwo.setUser(user2);

        List<Team> teams = List.of(teamOne, teamTwo);

        when(this.repository.findAll()).thenReturn(teams);

        List<Team> exp = List.of(teamOne);

        List<Team> res = this.service.getAllTeams(1L);

        assertEquals(1, res.size());
        assertEquals(exp, res);
    }

    @Test
    public void updateTeamTest() {
        Team base = new Team();
        User user = new User();

        base.setId(1L);
        base.setUser(user);
        user.setId(2L);

        when(this.repository.findById(anyLong())).thenReturn(Optional.of(base));

        Team newModel = new Team();
        newModel.setName("New Team");
        newModel.setUser(user);
        newModel.setId(1L);
        newModel.setGhostedDate(0);
        when(this.authorizationService.getNonTransientUser(any(User.class))).thenReturn(user);

        Team res = this.service.updateTeam(1L, newModel);
        System.out.println(res);
        System.out.println(newModel);

        assertEquals(res, newModel);
        verify(this.repository, times(1)).findById(anyLong());
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
