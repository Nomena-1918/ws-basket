package org.nba.wsbasket.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "type_mouvements")
public class TypeMouvement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idtypeMouvement;

    @Column(name = "nom", nullable = false)
    private String nom;
}
