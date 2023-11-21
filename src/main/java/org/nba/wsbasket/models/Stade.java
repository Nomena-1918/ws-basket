package org.nba.wsbasket.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "stades")
public class Stade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idstade;

    @Column(name = "nom")
    private String nom;
}
