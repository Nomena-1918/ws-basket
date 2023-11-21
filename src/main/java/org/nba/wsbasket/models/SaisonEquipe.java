package org.nba.wsbasket.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "saison_equipes")
public class SaisonEquipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idsaisonequipe;

    @ManyToOne
    @JoinColumn(name = "idsaison", nullable = false)
    private Saison saison;

    @ManyToOne
    @JoinColumn(name = "idequipe", nullable = false)
    private Equipe equipe;
}
