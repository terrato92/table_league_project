package terrato.springframework.service;

import org.springframework.transaction.annotation.Transactional;
import terrato.springframework.api.dto.LeagueDto;
import terrato.springframework.domain.League;
import terrato.springframework.domain.Team;

import java.util.List;
import java.util.Set;

/**
 * Created by onenight on 2018-03-03.
 */
public interface LeagueService {


    Set<League> getLeagues();


    List<League> getLeaguesDto();

    Set<Team> showLeagueTeams(Long idLeague);

    League getLeagueById(Long aLong);

    @Transactional
    LeagueDto getLeagueDtoById(Long idLeague);

    @Transactional
    LeagueDto saveLeagueDto(LeagueDto leagueDto);

    void deleteLeagueById(Long id);

}
