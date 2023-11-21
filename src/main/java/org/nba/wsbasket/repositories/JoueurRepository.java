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


    @Query(value="select distinct count(*) from v_matchs_joues where idjoueur=:id", nativeQuery = true)
    int getMatchJoueur(Long id);


    @Query(value="select sum(valeur)/(select count(*) from v_matchs_joues where idjoueur = :id) as points_moyens from actions where idjoueur = :id", nativeQuery = true)
    Double getPointMarqueParMatch(Long id);


    @Query(value="select count(*)/(select count(*) from v_matchs_joues where idjoueur = :id) as points_moyens from actions where idtype = 4 and idjoueur = :id", nativeQuery = true)
    Double getRebondsParMatch(Long id);


    
    @Query(value="select count(*)/(select count(*) from v_matchs_joues where idjoueur = :id) as points_moyens from actions where idtype = 3 and idjoueur = :id", nativeQuery = true)
    Double getPasseDeParMatch(Long id);


    @Query(value="select minutes_jouee/(select count(*) from v_matchs_joues where idjoueur = :id) AS minute_jouee_par_match from minutes_jouees where idjoueur = :id", nativeQuery = true)
    Double getMinuteJoues(Long id);


    @Query(value="SELECT calculer_eff(:id)", nativeQuery = true)
    Double calculer_eff(Long id);


    @Query(value="select (count(*)/(select CASE WHEN COUNT(*) = 0 THEN 1 ELSE COUNT(*) END from actions where idtype = 2 and idjoueur = :id )) * 100 from actions where idtype = 2 and valeur = 2 and idjoueur = :id", nativeQuery = true)
    Double pourcentageFieldGoal(Long id);


    @Query(value="select (count(*)/(select CASE WHEN COUNT(*) = 0 THEN 1 ELSE COUNT(*) END from actions where idtype = 3 and idjoueur = :id )) * 100 from actions where idtype = 3 and valeur = 3 and idjoueur = :id", nativeQuery = true)
    Double pourcentageThreeThrows(Long id);


    @Query(value="select (count(*)/(select CASE WHEN COUNT(*) = 0 THEN 1 ELSE COUNT(*) END from actions where idtype = 1 and idjoueur = :id )) * 100 from actions where idtype = 1 and valeur = 1 and idjoueur = :id", nativeQuery = true)
    Double pourcentageLancerFranc(Long id);


}
