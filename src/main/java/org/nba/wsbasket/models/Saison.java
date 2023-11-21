package org.nba.wsbasket.models;

import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "saisons")
public class Saison {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idsaison;

    @Column(name = "libelle", nullable = false)
    private String libelle;

    @Column(name = "debut", nullable = false)
    private LocalDateTime debut;

    @Column(name = "fin", nullable = false)
    private LocalDateTime fin;
}
