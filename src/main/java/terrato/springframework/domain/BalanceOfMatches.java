package terrato.springframework.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by onenight on 2018-03-02.
 */
@Data
@Table
@Entity
public class BalanceOfMatches {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long teamId;

    private Integer wins = 0;

    private int defeats = 0;

    private int draws = 0;

    public BalanceOfMatches() {
        this.wins = 0;
        this.defeats = 0;
        this.draws = 0;
    }
}
