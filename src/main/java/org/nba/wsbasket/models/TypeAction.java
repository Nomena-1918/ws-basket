package org.nba.wsbasket.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "type_actions")
public class TypeAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idtypeAction;

    @Column(name = "nom", nullable = false)
    private String nom;
}
