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


    @Query(value="select distinct count(idmatch) from matchs where idequipe1 = :idEquipe or idequipe2 = :idEquipe", nativeQuery = true)
    int getMatchJoue(Long idEquipe);
}
