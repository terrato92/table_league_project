package terrato.springframework.service.implementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import terrato.springframework.api.dto.LeagueDto;
import terrato.springframework.converter.LeagueDtoToLeagueConverter;
import terrato.springframework.converter.LeagueToLeagueDtoConvert;
import terrato.springframework.domain.League;
import terrato.springframework.domain.Team;
import terrato.springframework.exception.NotFoundException;
import terrato.springframework.repository.LeagueRepository;
import terrato.springframework.service.LeagueService;

import java.util.*;

/**
 * Created by onenight on 2018-03-03.
 */
@Service
public class LeagueServiceImpl implements LeagueService {

    LeagueRepository leagueRepository;
    private final LeagueDtoToLeagueConverter leagueDtoToLeagueConverter;
    private final LeagueToLeagueDtoConvert leagueToLeagueDtoConvert;
    private final DataLoadImpl dataLoad;

    @Autowired
    public LeagueServiceImpl(LeagueRepository leagueRepository, LeagueDtoToLeagueConverter leagueDtoToLeagueConverter, LeagueToLeagueDtoConvert leagueToLeagueDtoConvert, DataLoadImpl dataLoad) {
        this.leagueRepository = leagueRepository;

        this.leagueDtoToLeagueConverter = leagueDtoToLeagueConverter;
        this.leagueToLeagueDtoConvert = leagueToLeagueDtoConvert;
        this.dataLoad = dataLoad;
    }


    @Override
    public Set<League> getLeagues() {
        Set<League> leagues = new HashSet<>();
        leagueRepository.findAll().iterator().forEachRemaining(leagues::add);

        return leagues;
    }

    @Override
    public List<League> getLeaguesDto() {
        List<LeagueDto> leagueDtos = dataLoad.loadLeagues();
        List<League> leaguess = new ArrayList<>();

        for (LeagueDto leagues : leagueDtos) {

            League league = leagueDtoToLeagueConverter.convert(leagues);
            leagueRepository.save(league);
            leaguess.add(league);
        }


        return leaguess;
    }

    @Override
    public Set<Team> showLeagueTeams(Long idLeague) {
        Optional<League> leagueOptional = Optional.ofNullable(leagueRepository.findOne(idLeague));

        if (leagueOptional.isPresent()) {
            return leagueOptional.get().getTeams();
        } else {
            throw new NotFoundException("I can't find league with id: " + idLeague);
        }
    }

    @Override
    public League getLeagueById(Long idLeague) {
        Optional<League> leagueOptional = Optional.ofNullable(leagueRepository.findOne(idLeague));

        if (!leagueOptional.isPresent()) {
            throw new NotFoundException("I can't find league with id: " + idLeague);
        } else {
            return leagueOptional.get();

        }
    }

    @Override
    public LeagueDto getLeagueDtoById(Long idLeague) {
        return leagueToLeagueDtoConvert.convert(getLeagueById(idLeague));
    }


    @Override
    @Transactional
    public LeagueDto saveLeagueDto(LeagueDto leagueDto) {
        League detachedLeague = leagueDtoToLeagueConverter.convert(leagueDto);
        League saveLeague = leagueRepository.save(detachedLeague);

        return leagueToLeagueDtoConvert.convert(saveLeague);
    }


    @Override
    public void deleteLeagueById(Long idLeague) {
        leagueRepository.delete(idLeague);
    }


}
