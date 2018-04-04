package terrato.springframework.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import terrato.springframework.api.dto.LeagueDto;
import terrato.springframework.domain.Difficulty;
import terrato.springframework.domain.League;
import terrato.springframework.domain.Nationality;

/**
 * Created by onenight on 2018-03-27.
 */
@Component
public class LeagueDtoToLeagueConverter implements Converter<LeagueDto, League> {



    @Override
    public League convert(LeagueDto source) {

        final League league = new League();
        league.setName(source.getName());

        if (source.getRegion() != null){
            Nationality nationality = new Nationality();
            nationality.setNationality(source.getRegion());
            league.setNationality(nationality);
        }
        league.setDifficulty(Difficulty.UNKNOWN);

        return league;
    }
}
