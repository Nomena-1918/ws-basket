package org.nba.wsbasket.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "contrats")
public class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idcontrat;

    @ManyToOne
    @JoinColumn(name = "idequipe", nullable = false)
    private Equipe equipe;

    @ManyToOne
    @JoinColumn(name = "idjoueur", nullable = false)
    private Joueur joueur;

    @Column(name = "numero", nullable = false)
    private int numero;

    @Column(name = "debut", nullable = false)
    private LocalDateTime debut;

    @Column(name = "fin", nullable = false)
    private LocalDateTime fin;
}
