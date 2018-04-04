package terrato.springframework.converter;

import org.junit.Before;
import org.junit.Test;
import terrato.springframework.api.dto.LeagueDto;
import terrato.springframework.domain.League;

import static junit.framework.Assert.assertEquals;

/**
 * Created by onenight on 2018-03-27.
 */

public class LeagueDtoToLeagueTest {

    LeagueDtoToLeagueConverter leagueDtoToLeagueConverter;

    @Before
    public void setUp() throws Exception {
        leagueDtoToLeagueConverter = new LeagueDtoToLeagueConverter();
    }

    @Test
    public void convert() throws Exception {
        LeagueDto leagueDto = new LeagueDto();
        leagueDto.setName("liga+");

        League league = leagueDtoToLeagueConverter.convert(leagueDto);

        assertEquals(league.getName(), leagueDto.getName());
    }

}