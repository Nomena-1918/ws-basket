package org.nba.wsbasket.repositories;

import org.nba.wsbasket.models.Equipe;
import org.nba.wsbasket.models.Saison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipeRepository extends JpaRepository<Equipe, Long> {
    @Query("select s.equipe from SaisonEquipe s where s.saison.idsaison = :idSaison")
    List<Equipe> findEquipeSaison(Long idSaison);
}
