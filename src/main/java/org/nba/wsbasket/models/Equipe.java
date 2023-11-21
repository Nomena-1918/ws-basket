package org.nba.wsbasket.models;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "equipes")
public class Equipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idequipe;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "acronyme", nullable = false)
    private String acronyme;
}
