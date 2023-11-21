package org.nba.wsbasket.controllers;

import org.nba.wsbasket.models.*;
import org.nba.wsbasket.service.InitialisationDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InsertController {
    InitialisationDataService initialisationDataService;

    @Autowired
    public InsertController(InitialisationDataService initialisationDataService) {
        this.initialisationDataService = initialisationDataService;
    }

    // Complète ici, un endpoint pour chaque fonction d'insertion
    @PostMapping("/saisons")
    public Saison insertSaison(@RequestBody Saison saison) {
        return initialisationDataService.insertSaison(saison);
    }

    @PostMapping("/equipes")
    public Equipe insertEquipe(@RequestBody Equipe equipe) {
        return initialisationDataService.insertEquipe(equipe);
    }

    @PostMapping("/joueurs")
    public Joueur insertJoueur(@RequestBody Joueur joueur) {
        return initialisationDataService.insertJoueur(joueur);
    }

    @PostMapping("/contrats")
    public Contrat insertContrat(@RequestBody Contrat contrat) {
        return initialisationDataService.insertContrat(contrat);
    }

    @PostMapping("/matchs")
    public Match insertMatch(@RequestBody Match match) {
        return initialisationDataService.insertMatch(match);
    }

    @PostMapping("/mouvements")
    public Mouvement insertMouvement(@RequestBody Mouvement mouvement) {
        return initialisationDataService.insertMouvement(mouvement);
    }

    @PostMapping("/actions")
    public Action insertAction(@RequestBody Action action) {
        return initialisationDataService.insertAction(action);
    }
}
