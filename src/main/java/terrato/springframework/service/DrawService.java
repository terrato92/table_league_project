package terrato.springframework.service;

import terrato.springframework.domain.BalanceOfMatches;

/**
 * Created by onenight on 2018-03-04.
 */
public interface DrawService {
    BalanceOfMatches drawMatch(Long idTeam);
}
