package org.nba.wsbasket.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "actions")
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idaction;

    @ManyToOne
    @JoinColumn(name = "idtype", nullable = false)
    private TypeAction typeAction;

    @ManyToOne
    @JoinColumn(name = "idjoueur", nullable = false)
    private Joueur joueur;

    @ManyToOne
    @JoinColumn(name = "idmatch", nullable = false)
    private Match match;

    @Column(name = "dateheure", nullable = false)
    private LocalDateTime dateheure;

    @Column(name = "valeur", nullable = false)
    private int valeur;
}
