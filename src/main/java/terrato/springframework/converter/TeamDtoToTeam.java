package terrato.springframework.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import terrato.springframework.api.dto.TeamDto;
import terrato.springframework.domain.Nationality;
import terrato.springframework.domain.Power;
import terrato.springframework.domain.Team;

/**
 * Created by onenight on 2018-04-04.
 */
@Component
public class TeamDtoToTeam implements Converter<TeamDto, Team> {

    LeagueDtoToLeagueConverter converter;

    public TeamDtoToTeam(LeagueDtoToLeagueConverter converter) {
        this.converter = converter;
    }

    @Override
    public Team convert(TeamDto source) {

        final Team team = new Team();

        team.setName(source.getName());
        team.setPower(Power.UNKNOWN);

        if (source.getCountry() != null) {
            Nationality nationality = new Nationality();
            nationality.setNationality(source.getCountry());
            team.setNationality(nationality);
        }
        team.setLeague(converter.convert(source.getLeague()));
        return team;
    }
}
