package terrato.springframework.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by onenight on 2018-03-02.
 */


@Getter
@Setter
@EqualsAndHashCode(exclude = "league")
@Table
@Entity
public class Team implements Comparable<Team>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "team", fetch = FetchType.LAZY)
    private Set<Player> players = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "balance_id")
    private BalanceOfMatches balanceOfMatches = new BalanceOfMatches();

    @Enumerated(value = EnumType.STRING)
    private Power power;

    @Transient
    private int points;

    @ManyToOne
    @JoinColumn(name = "league_id")
    private League league;

    @Lob
    private Byte[] image;

    @OneToOne
    private Nationality nationality;


    @Override
    public int compareTo(Team o) {
        return this.getPoints() < o.getPoints() ? 1 : -1;
    }

    public Team addPlayer(Player player){
        player.setTeam(this);
        players.add(player);
        return this;
    }

}
