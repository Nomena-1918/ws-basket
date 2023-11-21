package org.nba.wsbasket.repositories;


import org.nba.wsbasket.models.Saison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface SaisonRepository extends JpaRepository<Saison, Long> {

    @Query("select s from Saison s where :date between s.debut and s.fin")
    Saison findSaisonBetween(LocalDateTime date);

}
