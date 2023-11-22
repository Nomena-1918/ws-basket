package org.nba.wsbasket.models;

import jakarta.persistence.*;

@Entity
@Table(name = "joueurs")
public class JoueurIonic extends Joueur {
    String numero = "0";

    public JoueurIonic(Joueur j, String n) {
        super.setIdjoueur(j.getIdjoueur());
        super.setNom(j.getNom());
        super.setPrenom(j.getPrenom());
        this.setNumero(n);
    }

    public JoueurIonic() {
    }

    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
}