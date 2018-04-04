//package terrato.springframework.converter;
//
//import lombok.Synchronized;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.stereotype.Component;
//import terrato.springframework.api.dto.OverallDto;
//import terrato.springframework.domain.BalanceOfMatches;
//
//
///**
// * Created by onenight on 2018-03-26.
// */
//@Component
//public class OverallDtoToBalanceConverter  implements Converter<OverallDto, BalanceOfMatches>{
//
//
//    @Synchronized
//    @Override
//    public BalanceOfMatches convert(OverallDto source) {
//
//        final BalanceOfMatches balanceOfMatches = new BalanceOfMatches();
//        balanceOfMatches.setWins(source.wins);
//        balanceOfMatches.setDraws(source.draws);
//        balanceOfMatches.setDefeats(source.losts);
//
//        return balanceOfMatches;
//    }
//}
