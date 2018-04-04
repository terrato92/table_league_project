//package terrato.springframework.converter;
//
//import org.springframework.core.convert.converter.Converter;
//import terrato.springframework.api.dto.PlayerDto;
//import terrato.springframework.domain.Player;
//
///**
// * Created by onenight on 2018-03-27.
// */
//public class PlayerDtoToPlayerConverter implements Converter<PlayerDto, Player> {
//
//
//
//    @Override
//    public Player convert(PlayerDto source) {
//
//        final Player player = new Player();
//        player.setName(source.getName());
//        player.setPosition(source.getPosition());
//
//        return player;
//    }
//}
