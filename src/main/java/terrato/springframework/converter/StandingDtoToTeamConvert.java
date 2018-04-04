//package terrato.springframework.converter;
//
//import org.springframework.core.convert.converter.Converter;
//import terrato.springframework.api.dto.StandingDto;
//import terrato.springframework.domain.Team;
//
///**
// * Created by onenight on 2018-03-27.
// */
//public class StandingDtoToTeamConvert implements Converter<StandingDto, Team> {
//
//    OverallDtoToBalanceConverter overallDtoToBalanceConverter;
//
//    public StandingDtoToTeamConvert(OverallDtoToBalanceConverter overallDtoToBalanceConverter) {
//        this.overallDtoToBalanceConverter = overallDtoToBalanceConverter;
//    }
//
//    @Override
//    public Team convert(StandingDto source) {
//
//        final Team team = new Team();
//
//        team.setName(source.getTeam());
//        team.setBalanceOfMatches(overallDtoToBalanceConverter.convert(source.getOverallDto()));
//        return team;
//    }
//}
