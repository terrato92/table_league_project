package terrato.springframework.service.implementation;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import terrato.springframework.api.dto.LeagueDto;
import terrato.springframework.service.LoadData;

import java.util.List;

/**
 * Created by onenight on 2018-04-03.
 */
@Service
public class DataLoadImpl implements LoadData {

    private final String transactionUrl = "http://api.football-api.com/2.0/competitions?Authorization=565ec012251f932ea4000001fa542ae9d994470e73fdb314a8a56d76";

    @Override
    public List<LeagueDto> loadLeagues() {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<LeagueDto>> rateResponse =
                restTemplate.exchange(transactionUrl,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<LeagueDto>>() {
                        });

        List<LeagueDto> rates = rateResponse.getBody();

        return rates;
    }
}
