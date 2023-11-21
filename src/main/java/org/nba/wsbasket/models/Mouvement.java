package org.nba.wsbasket.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "mouvements")
public class Mouvement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idmouvement;

    @ManyToOne
    @JoinColumn(name = "idmatch", nullable = false)
    private Match match;

    @ManyToOne
    @JoinColumn(name = "idtype", nullable = false)
    private TypeMouvement typeMouvement;

    @ManyToOne
    @JoinColumn(name = "idjoueur", nullable = false)
    private Joueur joueur;

    @Column(name = "dateheure", nullable = false)
    private LocalDateTime dateheure;
}
