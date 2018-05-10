package terrato.springframework.service.implementation;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import terrato.springframework.converter.LeagueDtoToLeagueConverter;
import terrato.springframework.converter.LeagueToLeagueDtoConvert;
import terrato.springframework.domain.League;
import terrato.springframework.repository.LeagueRepository;
import terrato.springframework.service.LeagueService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

/**
 * Created by onenight on 2018-03-03.
 */
public class LeagueDtoServiceImplTest {


    @Mock
    LeagueRepository leagueRepository;

    LeagueService leagueService;
    private LeagueDtoToLeagueConverter leagueDtoToLeagueConverter;
    private LeagueToLeagueDtoConvert leagueToLeagueDtoConvert;
    private DataLoadImpl dataLoad;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        leagueService = new LeagueServiceImpl(leagueRepository, leagueDtoToLeagueConverter, leagueToLeagueDtoConvert, dataLoad);
    }


    @Test
    public void testGetLeagueById() throws Exception {
        League league = new League();
        league.setId(1L);
        league.setName("liga+");

        given(leagueRepository.findOne(anyLong())).willReturn(league);

        League leagueDto = leagueService.getLeagueById(1L);

        then(leagueRepository).should(times(1)).findOne(anyLong());

        assertThat(leagueDto.getName(), is(equalTo(league.getName())));

    }

    @Test
    public void testAddLeague() throws Exception {
        League newLeague = new League();
        newLeague.setId(1L);

        leagueRepository.save(newLeague);
        verify(leagueRepository, times(1)).save(newLeague);
    }

    @Test
    public void testDeleteLeagueById() throws Exception {
        leagueService.deleteLeagueById(2L);

        verify(leagueRepository, times(1)).delete(anyLong());

    }

}