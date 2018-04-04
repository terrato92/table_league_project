package terrato.springframework.service.implementation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import terrato.springframework.domain.Player;
import terrato.springframework.domain.Team;
import terrato.springframework.exception.NotFoundException;
import terrato.springframework.repository.NationalityRepository;
import terrato.springframework.repository.PlayerRepository;
import terrato.springframework.repository.TeamRepository;
import terrato.springframework.service.PlayerService;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

/**
 * Created by onenight on 2018-03-03.
 */
@Service
@Slf4j
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final NationalityRepository nationalityRepository;


    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository,
                             TeamRepository teamRepository,
                             NationalityRepository nationalityRepository) {

        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        this.nationalityRepository = nationalityRepository;
    }



    @Override
    public Collection<Player> getPlayersFromTeam(Long idTeam) {
        Optional<Set<Player>> playerOptional = playerRepository.getAllByTeamId(idTeam);

        if (playerOptional.isPresent()) {
            return playerOptional.get();
        } else {
            log.error("Team with id: " + idTeam + " doesn't exist");
            throw new NotFoundException("I can't find team with id: " + idTeam);
        }
    }

    @Override
    public Player getTeamPlayerById(Long idPlayer) {
        Optional<Player> playerOptional = Optional.ofNullable(playerRepository.findOne(idPlayer));

        if (!playerOptional.isPresent()) {
            throw new NotFoundException("Team doesn't exist");
        } else {
            return playerOptional.get();
        }
    }

    @Override
    @Transactional
    public Player savePlayer(Player player, Long teamId) {
        Optional<Team> teamOptional = Optional.ofNullable(teamRepository.findOne(teamId));

        if (!teamOptional.isPresent()) {
            throw new NotFoundException("Team doesn't exist");
        } else {
            Team team = teamOptional.get();

            Optional<Player> playerOptional = team
                    .getPlayers()
                    .stream()
                    .filter(player3 -> player3.getId().equals(player.getId()))
                    .findFirst();

            if (playerOptional.isPresent()) {
                Player player1 = playerOptional.get();
                player1.setTeam(team);
                player1.setName(player.getName());

                player1.setNationality(nationalityRepository.findOne(player.getNationality().getId()));

                player1.setAge(player.getAge());
                player1.setPosition(player.getPosition());
                return player1;

            } else {
                team.addPlayer(player);
                player.setTeam(team);

                return player;
            }
        }
    }


    @Override
    public void deletePlayerFromTeam(Long idPlayer) {

        Optional<Player> playerOptional = Optional.ofNullable(playerRepository.findOne(idPlayer));

        if (!playerOptional.isPresent()) {
            throw new NotFoundException("I can't find player");
        } else {
            playerRepository.delete(idPlayer);
        }
    }
}