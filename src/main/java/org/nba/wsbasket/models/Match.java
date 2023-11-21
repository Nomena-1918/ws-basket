package org.nba.wsbasket.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "matchs")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idmatch;

    @ManyToOne
    @JoinColumn(name = "idsaison", nullable = false)
    private Saison saison;

    @ManyToOne
    @JoinColumn(name = "idstade", nullable = false)
    private Stade stade;

    @ManyToOne
    @JoinColumn(name = "idequipe1", nullable = false)
    private Equipe equipe1;

    @ManyToOne
    @JoinColumn(name = "idequipe2", nullable = false)
    private Equipe equipe2;

    @Column(name = "debut", nullable = false)
    private LocalDateTime debut;

    @Column(name = "fin", nullable = false)
    private LocalDateTime fin;
}
