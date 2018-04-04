package terrato.springframework.service;

import terrato.springframework.api.dto.LeagueDto;

import java.util.List;

/**
 * Created by onenight on 2018-03-19.
 */
public interface LoadData {

    List<LeagueDto> loadLeagues();

//    List<LeagueDto> getUsers();
}
