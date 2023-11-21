-- Fonction retournant quelles entrées de stock pour quelle quantité à sortir du stock
CREATE OR REPLACE FUNCTION get_entrees_pour_sorties(
    quantite_a_sortir DECIMAL,
    idarticle_sortant INT,
    idmagasin_sortant INT,
    ordre_tri_date_entree_stock VARCHAR
)
    RETURNS TABLE(
                     id INT,
                     idArticle INT,
                     idMagasin INT,
                     prix_unitaire DECIMAL,
                     quantite DECIMAL,
                     date_entree_stock TIMESTAMP,
                     quantite_restante DECIMAL,
                     quantite_cumulative DECIMAL
                 )
    LANGUAGE plpgsql AS $$
BEGIN
    IF ordre_tri_date_entree_stock = 'ASC' THEN
        RETURN QUERY
        -- Votre requête ici avec ORDER BY es.date_entree_stock
            SELECT *
            FROM (
                     SELECT
                         es.id,
                         es.idArticle,
                         es.idMagasin,
                         es.prix_unitaire,
                         es.quantite,
                         es.date_entree_stock,
                         es.quantite - COALESCE(ss.quantite_sortie, 0) as quantite_restante,
                         SUM(es.quantite - COALESCE(ss.quantite_sortie, 0)) OVER (ORDER BY es.date_entree_stock) as quantite_cumulative
                     FROM
                         entree_stock es
                             LEFT JOIN
                         (SELECT s.idEntreeStock, SUM(s.quantite) as quantite_sortie
                          FROM sortie_stock s
                          GROUP BY idEntreeStock) ss
                         ON
                                 es.id = ss.idEntreeStock
                     WHERE
                             es.idArticle = idarticle_sortant
                       AND es.idMagasin = idmagasin_sortant
                     ORDER BY
                         es.date_entree_stock
                 ) as subquery
            WHERE (subquery.quantite_restante > 0)
              and (subquery.quantite_cumulative <= quantite_a_sortir OR (subquery.quantite_cumulative - subquery.quantite_restante) <= quantite_a_sortir);

    ELSIF ordre_tri_date_entree_stock = 'DESC' THEN
        RETURN QUERY
        -- Votre requête ici avec ORDER BY es.date_entree_stock DESC
            SELECT *
            FROM (
                     SELECT
                         es.id,
                         es.idArticle,
                         es.idMagasin,
                         es.prix_unitaire,
                         es.quantite,
                         es.date_entree_stock,
                         es.quantite - COALESCE(ss.quantite_sortie, 0) as quantite_restante,
                         SUM(es.quantite - COALESCE(ss.quantite_sortie, 0)) OVER (ORDER BY es.date_entree_stock DESC) as quantite_cumulative
                     FROM
                         entree_stock es
                             LEFT JOIN
                         (SELECT s.idEntreeStock, SUM(s.quantite) as quantite_sortie
                          FROM sortie_stock s
                          GROUP BY idEntreeStock) ss
                         ON
                                 es.id = ss.idEntreeStock
                     WHERE
                             es.idArticle = idarticle_sortant
                       AND es.idMagasin = idmagasin_sortant
                     ORDER BY
                         es.date_entree_stock DESC
                 ) as subquery
            WHERE (subquery.quantite_restante > 0)
              and (subquery.quantite_cumulative <= quantite_a_sortir OR (subquery.quantite_cumulative - subquery.quantite_restante) <= quantite_a_sortir);

    ELSE
        RAISE EXCEPTION 'Ordre de tri de la date entree stock incorrect : %', ordre_tri_date_entree_stock;
    END IF;
END; $$;

SELECT * FROM get_entrees_pour_sorties(10, 1, 1, 'DESC');

--=========================================================================================

-- Fonction retournant l'état de stock d'un article dans un magasin
CREATE OR REPLACE FUNCTION get_etat_stock(
    _date_etat_stock varchar,
    _idarticle_sortant INT,
    _idmagasin_sortant INT
)
    RETURNS TABLE(
                     id INT,
                     idMagasin INT,
                     idArticle INT,
                     prix_unitaire DECIMAL,
                     quantite DECIMAL,
                     date_etat_stock TIMESTAMP
                 )
    LANGUAGE plpgsql AS $$
BEGIN
    RETURN QUERY
        SELECT
            1 as id,
            entrees.idMagasin,
            entrees.entrees_idArticle as idArticle,
            COALESCE(((entrees.montant_entrees_stock - COALESCE(sorties.montant_sorties_stock, 0)) / NULLIF((entrees.quantite_entrees - COALESCE(sorties.quantite_sorties, 0)), 0)), 0) AS prix_unitaire,
            entrees.quantite_entrees - COALESCE(sorties.quantite_sorties, 0) AS quantite,
            _date_etat_stock::timestamp
        FROM
            (
                select entrees.idarticle as entrees_idArticle, entrees.idmagasin, sum(entrees.quantite) as quantite_entrees, sum(entrees.prix_unitaire * entrees.quantite) as montant_entrees_stock
                from entree_stock entrees
                where
                        date_entree_stock <= _date_etat_stock::timestamp and
                        entrees.idarticle = _idarticle_sortant and
                        entrees.idmagasin = _idmagasin_sortant
                group by entrees.idarticle, entrees.idmagasin
            ) AS entrees

                LEFT JOIN
            (
                select e.idarticle as sorties_idArticle, e.idmagasin, sum(s.quantite) as quantite_sorties, sum(e.prix_unitaire * s.quantite) as montant_sorties_stock
                from sortie_stock s
                         join entree_stock e on s.idEntreeStock = e.id
                where
                        s.date_sortie_stock <= _date_etat_stock::timestamp and
                        e.idarticle = _idarticle_sortant and
                        e.idmagasin = _idmagasin_sortant
                group by e.idarticle, e.idmagasin
            ) AS sorties
            ON entrees.entrees_idArticle = sorties.sorties_idArticle AND entrees.idMagasin = sorties.idMagasin;
END; $$;



SELECT * FROM get_etat_stock('2023-11-12 19:28:00', 3, 3);
