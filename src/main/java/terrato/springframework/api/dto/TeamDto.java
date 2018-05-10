
package terrato.springframework.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "team_id",
    "is_national",
    "name",
    "country",
    "founded",
    "leagues",
    "venue_name",
    "venue_id",
    "venue_surface",
    "venue_address",
    "venue_city",
    "venue_capacity",
    "coach_name",
    "coach_id",
    "squad",
    "sidelined",
    "transfers_in",
    "transfers_out",
    "statistics"
})
public class TeamDto {

    @JsonProperty("team_id")
    public String teamId;

    @NotEmpty
    @JsonProperty("name")
    public String name;

    @NotEmpty
    @JsonProperty("country")
    public String country;


    @JsonProperty("league")
    public LeagueDto league;


}
