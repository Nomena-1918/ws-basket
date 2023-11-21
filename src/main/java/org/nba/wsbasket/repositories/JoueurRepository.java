package org.nba.wsbasket.repositories;


import org.nba.wsbasket.models.Joueur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDateTime;

@Repository
public interface JoueurRepository extends JpaRepository<Joueur, Long> {

    @Query("select c.joueur from Contrat c where c.equipe.idequipe = :equipeId and :dateActuel between c.debut and c.fin")
    List<Joueur> findByEquipeIdAndDate(Long equipeId, LocalDateTime dateActuel);
}
