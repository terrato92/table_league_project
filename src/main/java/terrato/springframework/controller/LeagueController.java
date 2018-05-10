package terrato.springframework.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import terrato.springframework.api.dto.LeagueDto;
import terrato.springframework.service.LeagueService;
import terrato.springframework.service.NationalityService;
import terrato.springframework.service.TeamService;
import terrato.springframework.service.implementation.DataLoadImpl;

import javax.validation.Valid;

/**
 * Created by onenight on 2018-03-03.
 */
@Slf4j
@Controller
public class LeagueController {

//    private static final String URI = "/api.football-api.com/2.0/competitions?Authorization=565ec012251f932ea4000001fa542ae9d994470e73fdb314a8a56d76";

    private static final String URI = "/api.football-api.com/2.0/competitions";


    private final LeagueService leagueService;

    private final TeamService teamService;

    private final NationalityService nationalityService;

    private final DataLoadImpl loadData;

    public LeagueController(LeagueService leagueService,
                            TeamService teamService,
                            NationalityService nationalityService,
                            DataLoadImpl loadData) {

        this.leagueService = leagueService;
        this.teamService = teamService;
        this.nationalityService = nationalityService;

        this.loadData = loadData;
    }



    @GetMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAllLeaguesJSON(Model model) {
        model.addAttribute("leagues", leagueService.getLeaguesDto());

        return "index";
    }

    @GetMapping({"", "/", "/index"})
    public String showLeagues(Model model) {

        model.addAttribute("leagues", leagueService.getLeagues());

        return "index";
    }

    @GetMapping("league/{id}/show")
    public String showLeagueById(@PathVariable String id, Model model) {
        model.addAttribute("league", leagueService.getLeagueById(Long.valueOf(id)));

        model.addAttribute("teams", teamService.findTeamByLeagueId(Long.valueOf(id)));

        return "league/show";
    }

    @GetMapping("league/new")
    public String newLeague(Model model) {
        model.addAttribute("league", new LeagueDto());

        model.addAttribute("nationalities", nationalityService.listAllNationalities());

        return "league/leagueform";
    }

    @GetMapping("league/{id}/delete")
    public String deleteLeague(@PathVariable String id) {
        leagueService.deleteLeagueById(Long.valueOf(id));
        return "redirect:/";
    }

//    @PutMapping
//    @RequestMapping("league/{leagueId}/update")
//    public String updateLeague(@PathVariable String leagueId, Model model) {
//        model.addAttribute("league", leagueService.getLeagueDtoById(Long.valueOf(leagueId)));
//
//        model.addAttribute("nationalities", nationalityService.listAllNationalities());
//
//        return "league/leagueform";
//    }

    @PostMapping("league")
    public String saveOrUpdateLeagueDto(@Valid @ModelAttribute("league") LeagueDto leagueDto, BindingResult bindingResult,
                                        Model model) {

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> log.debug(objectError.toString()));
            model.addAttribute("nationalities", nationalityService.listAllNationalities());

            return "league/leagueform";
        }

        LeagueDto leagueDto1 = leagueService.saveLeagueDto(leagueDto);

        return "redirect:/league/" +leagueDto1.getId() + "/show";

    }


}
