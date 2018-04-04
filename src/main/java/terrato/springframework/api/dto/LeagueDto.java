
package terrato.springframework.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import terrato.springframework.domain.Difficulty;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "id",
    "name",
    "region"
})
public class LeagueDto {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("region")
    private String region;

    private Difficulty difficulty;


}
