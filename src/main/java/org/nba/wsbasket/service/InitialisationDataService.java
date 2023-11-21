package org.nba.wsbasket.service;

import org.nba.wsbasket.models.*;
import org.nba.wsbasket.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class InitialisationDataService {
    ActionRepository actionRepository;
    ContratRepository contratRepository;
    EquipeRepository equipeRepository;
    JoueurRepository joueurRepository;
    MatchRepository matchRepository;
    MouvementRepository mouvementRepository;
    SaisonRepository saisonRepository;

    public InitialisationDataService() {
    }
    @Autowired
    public InitialisationDataService(ActionRepository actionRepository, ContratRepository contratRepository, EquipeRepository equipeRepository, JoueurRepository joueurRepository, MatchRepository matchRepository, MouvementRepository mouvementRepository, SaisonRepository saisonRepository) {
        this.actionRepository = actionRepository;
        this.contratRepository = contratRepository;
        this.equipeRepository = equipeRepository;
        this.joueurRepository = joueurRepository;
        this.matchRepository = matchRepository;
        this.mouvementRepository = mouvementRepository;
        this.saisonRepository = saisonRepository;
    }


    public Saison insertSaison(Saison saison) {
        // vérifier date fin après date début
        if (saison.getFin().isBefore(saison.getDebut())) {
            throw new IllegalArgumentException("La date de fin doit être après la date de début");
        }
        saisonRepository.save(saison);
        return saison;
    }

    public Equipe insertEquipe(Equipe equipe) {
        return equipeRepository.save(equipe);
    }

    public Joueur insertJoueur(Joueur joueur) {
        return joueurRepository.save(joueur);
    }

    public Contrat insertContrat(Contrat contrat) {
        return contratRepository.save(contrat);
    }

    public Match insertMatch(Match match) {
        // les 2 équipes doivent être différentes
        if (Objects.equals(match.getEquipe1().getIdequipe(), match.getEquipe2().getIdequipe())) {
            throw new IllegalArgumentException("Les 2 équipes doivent être différentes");
        }

        // date entre début et fin de saison
        if (match.getDebut().isBefore(match.getSaison().getDebut()) || match.getDebut().isAfter(match.getSaison().getFin())) {
            throw new IllegalArgumentException("La date du match doit être entre le début et la fin de la saison");
        }

        return matchRepository.save(match);
    }

    public Mouvement insertMouvement(Mouvement mouvement) {
        // dateheure entre début et fin du match
        if (mouvement.getDateheure().isBefore(mouvement.getMatch().getDebut()) || mouvement.getDateheure().isAfter(mouvement.getMatch().getFin())) {
            throw new IllegalArgumentException("La date du mouvement doit être entre le début et la fin du match");
        }

        return mouvementRepository.save(mouvement);
    }

    public Action insertAction(Action action) {
        // dateheure entre début et fin du match
        if (action.getDateheure().isBefore(action.getMatch().getDebut()) || action.getDateheure().isAfter(action.getMatch().getFin())) {
            throw new IllegalArgumentException("La date de l'action doit être entre le début et la fin du match");
        }

        // si le TypeAction est 3P la valeur doit être 3 ou 0
        if (action.getTypeAction().getIdtypeAction() == 1 && action.getValeur() != 3 && action.getValeur() != 0) {
            throw new IllegalArgumentException("La valeur de l'action doit être 3 ou 0");
        }

        // si le TypeAction est LF la valeur doit être 1 ou 0
        if (action.getTypeAction().getIdtypeAction() == 2 && action.getValeur() != 1 && action.getValeur() != 0) {
            throw new IllegalArgumentException("La valeur de l'action doit être 1 ou 0");
        }

        // si le TypeAction est FG la valeur doit être 2 ou 0
        if (action.getTypeAction().getIdtypeAction() == 3 && action.getValeur() != 2 && action.getValeur() != 0) {
            throw new IllegalArgumentException("La valeur de l'action doit être 2 ou 0");
        }

        // le reste doit être égale à 0
        if (action.getTypeAction().getIdtypeAction() != 1 && action.getTypeAction().getIdtypeAction() != 2 && action.getTypeAction().getIdtypeAction() != 3 && action.getValeur() != 0) {
            throw new IllegalArgumentException("La valeur de l'action doit être 0");
        }

        return actionRepository.save(action);
    }
}
