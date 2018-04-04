package terrato.springframework.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import terrato.springframework.api.dto.LeagueDto;
import terrato.springframework.domain.League;

/**
 * Created by onenight on 2018-03-31.
 */
@Component
public class LeagueToLeagueDtoConvert implements Converter<League ,LeagueDto> {



    @Override
    public LeagueDto convert(League source) {

        LeagueDto leagueDto = new LeagueDto();
        leagueDto.setId(source.getId().toString());
        leagueDto.setName(leagueDto.getName());
        leagueDto.setRegion(source.getNationality().getNationality());

        return leagueDto;
    }
}
