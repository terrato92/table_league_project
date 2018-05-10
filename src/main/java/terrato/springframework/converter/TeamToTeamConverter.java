package terrato.springframework.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import terrato.springframework.api.dto.TeamDto;
import terrato.springframework.domain.Team;

/**
 * Created by onenight on 2018-04-04.
 */
@Component
public class TeamToTeamConverter implements Converter<Team, TeamDto> {

    LeagueToLeagueDtoConvert convert;

    public TeamToTeamConverter(LeagueToLeagueDtoConvert convert) {
        this.convert = convert;
    }

    @Override
    public TeamDto convert(Team source) {

        final TeamDto teamDto = new TeamDto();

        teamDto.setTeamId(source.getId().toString());
        teamDto.setCountry(source.getNationality().getNationality());
        teamDto.setName(source.getName());
        teamDto.setLeague(convert.convert(source.getLeague()));

        return teamDto;
    }
}
