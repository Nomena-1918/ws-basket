package org.nba.wsbasket.controllers;

import org.nba.wsbasket.models.Equipe;
import org.nba.wsbasket.models.Joueur;
import org.nba.wsbasket.models.Saison;
import org.nba.wsbasket.repositories.EquipeRepository;
import org.nba.wsbasket.repositories.JoueurRepository;
import org.nba.wsbasket.repositories.SaisonRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class DataController {
    SaisonRepository saisonRepository;
    EquipeRepository equipeRepository;

    JoueurRepository joueurRepository;


    @Autowired
    public DataController(SaisonRepository saisonRepository, EquipeRepository equipeRepository, JoueurRepository joueurRepository) {
        this.saisonRepository = saisonRepository;
        this.equipeRepository = equipeRepository;
        this.joueurRepository = joueurRepository;
    }


    @GetMapping("/saisons")
    public List<Saison> getSaisons() {
        return saisonRepository.findAll();
    }

    @GetMapping("/equipes")
    public List<Equipe> getEquipes() {
        Saison s = saisonRepository.findSaisonBetween(LocalDateTime.now());
        return equipeRepository.findEquipeSaison(s.getIdsaison());
    }

    @GetMapping("/joueurs/equipe/{idEquipe}")
    public List<Joueur> getEquipesBySaison(@PathVariable Long idEquipe) {
        return joueurRepository.findByEquipeIdAndDate(idEquipe, LocalDateTime.now());
    }

}
