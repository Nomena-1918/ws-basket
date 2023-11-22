package org.nba.wsbasket.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.nba.wsbasket.api.StatsIonic;
import org.nba.wsbasket.api.StatsNetlify;
import org.nba.wsbasket.models.Equipe;
import org.nba.wsbasket.models.Joueur;
import org.nba.wsbasket.models.JoueurIonic;
import org.nba.wsbasket.models.Saison;
import org.nba.wsbasket.repositories.EquipeRepository;
import org.nba.wsbasket.repositories.JoueurRepository;
import org.nba.wsbasket.repositories.SaisonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatsController {

    SaisonRepository saisonRepository;
    JoueurRepository joueurRepository;
    EquipeRepository equipeRepository;


    @Autowired
    public StatsController (SaisonRepository saisonRepository, JoueurRepository joueurRepository,     EquipeRepository equipeRepository) {
        this.equipeRepository=equipeRepository;
        this.joueurRepository=joueurRepository;
        this.saisonRepository = saisonRepository;
    }


    // @GetMapping("/equipes")
    // public List<Equipe> getAllEquipe() {
    //     return equipeRepository.findAll();
    // }


    @GetMapping("/equipes/matchs-joues/{idequipe}")
    public int getMinuteJoueeEquipe(@PathVariable Long idequipe) {
        return equipeRepository.getMatchJoue(idequipe);
    }


    @GetMapping("/stats/netlify")
    public List<StatsNetlify> getStatsNetlify() {
         List<StatsNetlify> listStats = new ArrayList<>();

        Saison s = saisonRepository.findSaisonBetween(LocalDateTime.now());

        List<Equipe> listEquipes = equipeRepository.findEquipeSaison(s.getIdsaison());
        List<Joueur> listJoueur = new ArrayList<>();

        StatsNetlify stat;
        for (Equipe e : listEquipes) {
            listJoueur = joueurRepository.findByEquipeIdAndDate(e.getIdequipe(), LocalDateTime.now());

            for (Joueur j : listJoueur) {
                stat = new StatsNetlify(j.getNom(), j.getPrenom(), e.getAcronyme(), equipeRepository.getMatchJoue(e.getIdequipe()), joueurRepository.getMatchJoueur(j.getIdjoueur()), joueurRepository.getPointMarqueParMatch(j.getIdjoueur()), joueurRepository.getRebondsParMatch(j.getIdjoueur()), joueurRepository.getPasseDeParMatch(j.getIdjoueur()), joueurRepository.getMinuteJoues(j.getIdjoueur()), joueurRepository.calculer_eff(j.getIdjoueur()), joueurRepository.pourcentageFieldGoal(j.getIdjoueur()), joueurRepository.pourcentageThreeThrows(j.getIdjoueur()), joueurRepository.pourcentageLancerFranc(j.getIdjoueur()));

                listStats.add(stat);
            }
                
        }
        
        return listStats;
    }

    @GetMapping("/stats/ionic/{idjoueur}/{idequipe}")
    public StatsIonic getStatsIonic(@PathVariable long idjoueur,@PathVariable long idequipe) {
        StatsIonic stat;
        Optional<Joueur> joueur = joueurRepository.findById(idjoueur);
        Joueur j = new Joueur();
        if(joueur.isPresent()) {
            j = joueur.get();
        }

        Optional<Equipe> eq = equipeRepository.findById(idequipe);
        Equipe e = new Equipe();
        if(eq.isPresent()) {
            e = eq.get();
        }
        JoueurIonic ji = new JoueurIonic(j, "0");
            
        stat = new StatsIonic(j.getNom(), j.getPrenom(), ji.getNumero(), j.getIdjoueur(), equipeRepository.getMatchJoue(e.getIdequipe()),joueurRepository.getMatchJoueur(j.getIdjoueur()), joueurRepository.getPointMarqueParMatch(j.getIdjoueur()), joueurRepository.getRebondsParMatch(j.getIdjoueur()), joueurRepository.getPasseDeParMatch(j.getIdjoueur()), joueurRepository.getMinuteJoues(j.getIdjoueur()), joueurRepository.calculer_eff(j.getIdjoueur()), joueurRepository.pourcentageFieldGoal(j.getIdjoueur()), joueurRepository.pourcentageThreeThrows(j.getIdjoueur()), joueurRepository.pourcentageLancerFranc(j.getIdjoueur()));

        return stat;
    }



}
