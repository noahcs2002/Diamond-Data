package com.dd.api.auth.validators;

import com.dd.api.auth.models.User;
import com.dd.api.restapi.models.*;
import com.dd.api.restapi.services.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class ValidatorTests {

    @Mock
    private TeamService teamService;

    @Mock
    private OffensivePlayerService offensivePlayerService;

    @Mock
    private DefensivePlayerService defensivePlayerService;

    @Mock
    private GameService gameService;

    @Mock
    private PitcherService pitcherService;

    @Mock
    private PlayerService playerService;

    @InjectMocks
    private Validator validator;

    private Team team;
    private User user;
    private Long userId = 1234L;
    private long teamId = 9876L;

    @Before
    public void setUp() {
        openMocks(this);
        this.team = new Team();
        this.user = new User();
        this.user.setId(userId);
        this.team.setId(teamId);
        this.team.setUser(user);
    }

    @Test
    public void idealValidateTeamTest() {
        when(this.teamService.getTeamById(anyLong())).thenReturn(this.team);

        boolean res = this.validator.validateTeam(userId, teamId);

        assertTrue(res);
        verify(this.teamService, times(1)).getTeamById(anyLong());
    }

    @Test
    public void validateTeamReturnsFalseWhenNotPermitted() {
        Team badRequestTeam = new Team();
        User badRequestUser = new User();
        badRequestUser.setId(1L);
        badRequestTeam.setUser(badRequestUser);
        badRequestTeam.setId(1L);
        when(this.teamService.getTeamById(anyLong())).thenReturn(badRequestTeam);

        boolean res = this.validator.validateTeam(userId, teamId);

        assertFalse(res);
        verify(this.teamService, times(1)).getTeamById(anyLong());
    }

    @Test(expected = NullPointerException.class)
    public void validateTeamFailsOnNullUserId() {
        this.validator.validateTeam(null, teamId);
    }

    @Test(expected = NullPointerException.class)
    public void validateTeamFailsOnNullTeamId() {
        this.validator.validateTeam(userId, null);
    }

    @Test
    public void idealValidateDefensivePlayerTest() {
        final DefensivePlayer defensivePlayer = new DefensivePlayer();
        defensivePlayer.setId(12345L);
        defensivePlayer.setTeam(team);
        when(this.defensivePlayerService.getDefensivePlayer(anyLong())).thenReturn(defensivePlayer);
        when(this.teamService.getTeamById(anyLong())).thenReturn(team);

        boolean res = this.validator.validateDefensivePlayer(userId, 12345L);

        assertTrue(res);
        verify(this.teamService, times(1)).getTeamById(anyLong());
        verify(this.defensivePlayerService, times(1)).getDefensivePlayer(anyLong());
    }

    @Test
    public void validateDefensivePlayerReturnsFalseOnNoPermission() {
        final DefensivePlayer defensivePlayer = new DefensivePlayer();
        Team badTeam = new Team();
        User badUser = new User();
        badUser.setId(1_000L);
        badTeam.setId(87_923_478_964_523L);
        badTeam.setUser(badUser);
        defensivePlayer.setId(12345L);
        defensivePlayer.setTeam(team);
        when(this.defensivePlayerService.getDefensivePlayer(anyLong())).thenReturn(defensivePlayer);
        when(this.teamService.getTeamById(anyLong())).thenReturn(badTeam);

        boolean res = this.validator.validateDefensivePlayer(userId, 12345L);

        assertFalse(res);
        verify(this.teamService, times(1)).getTeamById(anyLong());
        verify(this.defensivePlayerService, times(1)).getDefensivePlayer(anyLong());
    }

    @Test(expected = NullPointerException.class)
    public void validateDefensivePlayerFailsOnNullUserId() {
        this.validator.validateDefensivePlayer(null, this.userId);
    }

    @Test(expected = NullPointerException.class)
    public void validateDefensivePlayerFailsOnNullPlayerId() {
        this.validator.validateDefensivePlayer(1L, null);
    }

    @Test
    public void idealValidateGameTest() {
        Game game = new Game();
        game.setId(1L);
        game.setTeam(team);
        when(this.gameService.getGameById(anyLong())).thenReturn(game);
        when(this.teamService.getTeamById(anyLong())).thenReturn(team);

        boolean res = this.validator.validateGame(userId, 1L);

        assertTrue(res);
        verify(this.teamService, times(1)).getTeamById(anyLong());
        verify(this.gameService, times(1)).getGameById(anyLong());
    }

    @Test
    public void validateGameReturnsFalseOnNoAccess() {
        Game game = new Game();
        User badUser = new User();
        badUser.setId(123L);
        Team badTeam = new Team();
        badTeam.setUser(badUser);
        badTeam.setId(12L);
        game.setTeam(badTeam);
        when(this.teamService.getTeamById(anyLong())).thenReturn(badTeam);
        when(gameService.getGameById(anyLong())).thenReturn(game);

        boolean res = this.validator.validateGame(userId, 1L);

        assertFalse(res);
        verify(this.gameService, times(1)).getGameById(anyLong());
        verify(this.teamService, times(1)).getTeamById(anyLong());
    }

    @Test(expected = NullPointerException.class)
    public void validateGameFailsOnNullGameId() {
        this.validator.validateGame(1L, null);
    }

    @Test(expected = NullPointerException.class)
    public void validateGameFailsOnNullUserId() {
        this.validator.validateGame(null, 1L);
    }
    @Test
    public void idealValidateOffensivePlayerTest() {
        final OffensivePlayer defensivePlayer = new OffensivePlayer();
        defensivePlayer.setId(12345L);
        defensivePlayer.setTeam(team);
        when(this.offensivePlayerService.getOffensivePlayer(anyLong())).thenReturn(defensivePlayer);
        when(this.teamService.getTeamById(anyLong())).thenReturn(team);

        boolean res = this.validator.validateOffensivePlayer(userId, 12345L);

        assertTrue(res);
        verify(this.teamService, times(1)).getTeamById(anyLong());
        verify(this.offensivePlayerService, times(1)).getOffensivePlayer(anyLong());
    }

    @Test
    public void validateOffensivePlayerReturnsFalseOnNoPermission() {
        final OffensivePlayer defensivePlayer = new OffensivePlayer();
        Team badTeam = new Team();
        User badUser = new User();
        badUser.setId(1_000L);
        badTeam.setId(87_923_478_964_523L);
        badTeam.setUser(badUser);
        defensivePlayer.setId(12345L);
        defensivePlayer.setTeam(team);
        when(this.offensivePlayerService.getOffensivePlayer(anyLong())).thenReturn(defensivePlayer);
        when(this.teamService.getTeamById(anyLong())).thenReturn(badTeam);

        boolean res = this.validator.validateOffensivePlayer(userId, 12345L);

        assertFalse(res);
        verify(this.teamService, times(1)).getTeamById(anyLong());
        verify(this.offensivePlayerService, times(1)).getOffensivePlayer(anyLong());
    }

    @Test(expected = NullPointerException.class)
    public void validateOffensivePlayerFailsOnNullUserId() {
        this.validator.validateOffensivePlayer(null, this.userId);
    }

    @Test(expected = NullPointerException.class)
    public void validateOffensivePlayerFailsOnNullPlayerId() {
        this.validator.validateOffensivePlayer(1L, null);
    }

    @Test
    public void idealValidatePitcherTest() {
        final Pitcher pitcher = new Pitcher();
        pitcher.setId(12345L);
        pitcher.setTeam(team);
        when(this.pitcherService.getPitcherById(anyLong())).thenReturn(pitcher);
        when(this.teamService.getTeamById(anyLong())).thenReturn(team);

        boolean res = this.validator.validatePitcher(userId, 12345L);

        assertTrue(res);
        verify(this.teamService, times(1)).getTeamById(anyLong());
        verify(this.pitcherService, times(1)).getPitcherById(anyLong());
    }

    @Test
    public void validatePitcherReturnsFalseOnNoPermission() {
        final Pitcher pitcher = new Pitcher();
        Team badTeam = new Team();
        User badUser = new User();
        badUser.setId(1_000L);
        badTeam.setId(87_923_478_964_523L);
        badTeam.setUser(badUser);
        pitcher.setId(12345L);
        pitcher.setTeam(team);
        when(this.pitcherService.getPitcherById(anyLong())).thenReturn(pitcher);
        when(this.teamService.getTeamById(anyLong())).thenReturn(badTeam);

        boolean res = this.validator.validatePitcher(userId, 12345L);

        assertFalse(res);
        verify(this.teamService, times(1)).getTeamById(anyLong());
        verify(this.pitcherService, times(1)).getPitcherById(anyLong());
    }

    @Test(expected = NullPointerException.class)
    public void validatePitcherFailsOnNullUserId() {
        this.validator.validatePitcher(null, this.userId);
    }

    @Test(expected = NullPointerException.class)
    public void validatePitcherFailsOnNullPlayerId() {
        this.validator.validatePitcher(1L, null);
    }

    @Test
    public void idealValidatePlayerTest() {
        final Player player = new Player();
        final OffensivePlayer o = new OffensivePlayer();
        final DefensivePlayer d = new DefensivePlayer();
        o.setTeam(team);
        d.setTeam(team);
        player.setOffensivePlayer(o);
        player.setDefensivePlayer(d);
        player.setId(12345L);
        when(this.playerService.getPlayerById(anyLong())).thenReturn(player);
        when(this.teamService.getTeamById(anyLong())).thenReturn(team);

        boolean res = this.validator.validatePlayer(userId, 12345L);

        assertTrue(res);
        verify(this.teamService, times(2)).getTeamById(anyLong());
        verify(this.playerService, times(1)).getPlayerById(anyLong());
    }

    @Test
    public void validatePlayerReturnsFalseOnNoPermission() {
        final Player player = new Player();
        Team badTeam = new Team();
        User badUser = new User();
        final OffensivePlayer o = new OffensivePlayer();
        final DefensivePlayer d = new DefensivePlayer();
        player.setDefensivePlayer(d);
        player.setOffensivePlayer(o);
        badUser.setId(1_000L);
        badTeam.setId(87_923_478_964_523L);
        badTeam.setUser(badUser);
        player.setId(12345L);
        player.getOffensivePlayer().setTeam(team);
        player.getDefensivePlayer().setTeam(team);
        when(this.playerService.getPlayerById(anyLong())).thenReturn(player);
        when(this.teamService.getTeamById(anyLong())).thenReturn(badTeam);

        boolean res = this.validator.validatePlayer(userId, 12345L);

        assertFalse(res);
        // Should fire twice, will fire once because its &&, backs out at first false
        verify(this.teamService, times(1)).getTeamById(anyLong());
        verify(this.playerService, times(1)).getPlayerById(anyLong());
    }

    @Test(expected = NullPointerException.class)
    public void validatePlayerFailsOnNullUserId() {
        this.validator.validatePlayer(null, this.userId);
    }

    @Test(expected = NullPointerException.class)
    public void validatePlayerFailsOnNullPlayerId() {
        this.validator.validatePlayer(1L, null);
    }


}
