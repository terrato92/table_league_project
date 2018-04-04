package terrato.springframework.service.implementation;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import terrato.springframework.domain.League;
import terrato.springframework.domain.Team;
import terrato.springframework.repository.LeagueRepository;
import terrato.springframework.repository.TeamRepository;
import terrato.springframework.service.PointsService;
import terrato.springframework.service.TeamService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Created by onenight on 2018-03-04.
 */
public class TeamServiceImplTest {

    @Mock
    TeamRepository teamRepository;

    @Mock
    LeagueRepository leagueRepository;

    @Mock
    TeamService teamService;


    @Mock
    PointsService pointsService;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        teamService = new TeamServiceImpl(leagueRepository, teamRepository, pointsService);

    }


    @Test
    public void testFindTeamById() throws Exception {
        Team team = new Team();
        team.setId(2L);

        when(teamRepository.findOne(anyLong())).thenReturn(team);

        Team team1 = teamService.findTeamById(2L);

        assertNotNull(team1);
        assertEquals(team.getId(), Long.valueOf(2L));
    }


    @Test
    public void testSaveTeam() throws Exception {
        Team team = new Team();
        team.setId(1L);
        League league = new League();
        league.setId(2L);
        team.setLeague(league);

        Optional<League> leagueOptional = Optional.of(new League());

        League saveLeague = new League();
        saveLeague.setId(3L);
        saveLeague.addTeam(team);
        saveLeague.getTeams().iterator().next().setId(1L);

        when(leagueRepository.findOne(anyLong())).thenReturn(leagueOptional.get());
        when(leagueRepository.save((League) any())).thenReturn(saveLeague);

        Team team1 = teamService.saveTeam(team, league.getId());

        assertEquals(Long.valueOf(1L), team1.getId());
        verify(leagueRepository, times(1)).findOne(anyLong());

    }

    @Test
    public void deleteTeam() throws Exception {
        teamService.deleteTeam(1L);

        verify(teamRepository, times(1)).delete(anyLong());
    }


    @Test
    public void setTeamByPoints() throws Exception {
        League league = loadData();

        when(leagueRepository.findOne(anyLong())).thenReturn(league);

        teamService.setTeamByPoints(league.getId());

        assertEquals("Manchester", league.getTeams().iterator().next().getName());

    }

    private League loadData() {
        Team team1 = new Team();
        team1.setId(1L);
        team1.setName("Chelsea");
        team1.setPoints(6);
        Team team2 = new Team();
        team1.setId(2L);
        team2.setPoints(9);
        team2.setName("Manchester");

        Set<Team> teams = new HashSet<>();
        teams.add(team1);
        teams.add(team2);

        League league = new League();
        league.setId(3L);
        league.setTeams(teams);

        team1.setLeague(league);
        team2.setLeague(league);
        return league;
    }

}