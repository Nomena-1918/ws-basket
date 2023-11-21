-- Insertion des données pour la table code_comptable
INSERT INTO code_comptable (code)
VALUES ('AR1'), ('AR2'), ('AL1');

-- Insertion des données pour la table methode_sortie
INSERT INTO methode_sortie (nom, description)
VALUES ('FIFO', 'ASC'), ('LIFO', 'DESC');

-- Insertion des données pour la table unite
INSERT INTO unite (nom)
VALUES ('kg'), ('litre');

-- Insertion des données pour la table magasin
INSERT INTO magasin (nom)
VALUES ('Magasin 1'), ('Magasin 2'), ('Magasin 3');

-- Insertion des données pour la table article
INSERT INTO article (nom, idMethodeSortie, idUnite, idCodeComptable)
VALUES ('Riz blanc', 1, 1, 1), ('Riz rouge', 2, 1, 2), ('Lait', 1, 2, 3);


SELECT a.*
FROM article a
         JOIN code_comptable c ON a.idCodeComptable = c.id
WHERE c.code LIKE :code || '%' or c.code = :code;



-- Insertion des données pour la table entree_stock
INSERT INTO entree_stock (idArticle, idMagasin, prix_unitaire, quantite, date_entree_stock)
VALUES
    (1, 1, 2000, 10, '2023-01-01 00:00:00'),
    (1, 1, 2500, 20, '2023-01-02 00:01:00'),

    (2, 2, 5000, 30, '2023-02-02 00:10:00'),
    (2, 2, 5400, 9, '2023-02-03 00:01:00'),

    (3, 3, 900, 30, '2023-03-01 00:00:00'),
    (3, 3, 1000, 7, '2023-03-04 00:01:00');

-- Insertion des données pour la table sortie_stock
INSERT INTO sortie_stock (idEntreeStock, quantite, date_sortie_stock)
VALUES
    (1, 5, '2023-01-05 00:00:00'), -- Sortie de 5 articles de l'entrée 1
    (1, 3, '2023-01-06 00:00:00'), -- Sortie de 3 articles de l'entrée 1
    (2, 10, '2023-02-05 00:00:00'), -- Sortie de 10 articles de l'entrée 2
    (3, 30, '2023-03-02 00:00:00'); -- Sortie de 30 articles de l'entrée 3

--truncate entree_stock, sortie_stock restart identity cascade;

--===============================================
-- Requête pour connaître quelles entrées de stock pour quelle quantité à sortir du stock
-- Avec les restes
SELECT
    es.id,
    es.idArticle,
    es.idMagasin,
    es.prix_unitaire,
    es.quantite_restante as quantite,
    es.date_entree_stock
FROM (
         SELECT
             es.id,
             es.idArticle,
             es.idMagasin,
             es.prix_unitaire,
             es.quantite,
             es.date_entree_stock,
             es.quantite - COALESCE(ss.quantite_sortie, 0) as quantite_restante,
             SUM(es.quantite - COALESCE(ss.quantite_sortie, 0)) OVER (ORDER BY es.date_entree_stock desc) as quantite_cumulative
         FROM
             entree_stock es
                 LEFT JOIN
             (SELECT idEntreeStock, SUM(quantite) as quantite_sortie
              FROM sortie_stock
              GROUP BY idEntreeStock) ss
             ON
                 es.id = ss.idEntreeStock
         WHERE
                 es.idArticle = 2
           AND es.idMagasin = 2
         ORDER BY
             es.date_entree_stock desc
     ) as es
WHERE (quantite_restante > 0) and (quantite_cumulative < 12 OR (quantite_cumulative - quantite_restante) < 12);

-- Quand c'est FIFO ASC
-- Quand c'est LIFO DESC
--===============================================


--===============================================
-- Toutes les entrées de stock à une date donnée
select e.idarticle, e.idmagasin, sum(e.quantite) as quantite_entrees, sum(e.prix_unitaire * e.quantite) as montant_entrees_stock
from entree_stock e
where
    date_entree_stock <= now() and
    idarticle = 1 and
    idmagasin = 1
group by e.idarticle, e.idmagasin;

-- Toutes les sorties de stock à une date donnée
select e.idarticle, e.idmagasin, sum(s.quantite) as quantite_sorties, sum(e.prix_unitaire * s.quantite) as montant_sorties_stock
from sortie_stock s
join entree_stock e on s.idEntreeStock = e.id
where
    s.date_sortie_stock <= now() and
    e.idarticle = 1 and
    e.idmagasin = 1
group by e.idarticle, e.idmagasin;


-- L'état de stock (entrées - sorties) à une date donnée
SELECT
    1 as id,
    entrees.idMagasin,
    entrees.entrees_idArticle as idArticle,
    (entrees.montant_entrees_stock - COALESCE(sorties.montant_sorties_stock, 0)) / (entrees.quantite_entrees - COALESCE(sorties.quantite_sorties, 0)) AS prix_unitaire,
    entrees.quantite_entrees - COALESCE(sorties.quantite_sorties, 0) AS quantite,
    '2023-11-12 15:51:47.842263 +00:00'
FROM
    (
        select entrees.idarticle as entrees_idArticle, entrees.idmagasin, sum(entrees.quantite) as quantite_entrees, sum(entrees.prix_unitaire * entrees.quantite) as montant_entrees_stock
        from entree_stock entrees
        where
                date_entree_stock <= '2023-11-12 15:51:47.842263 +00:00' and
                entrees.idarticle = 1 and
                entrees.idmagasin = 1
        group by entrees.idarticle, entrees.idmagasin
    ) AS entrees

        LEFT JOIN
    (
        select e.idarticle as sorties_idArticle, e.idmagasin, sum(s.quantite) as quantite_sorties, sum(e.prix_unitaire * s.quantite) as montant_sorties_stock
        from sortie_stock s
                 join entree_stock e on s.idEntreeStock = e.id
        where
                s.date_sortie_stock <= '2023-11-12 15:51:47.842263 +00:00' and
                e.idarticle = 1 and
                e.idmagasin = 1
        group by e.idarticle, e.idmagasin
    ) AS sorties
    ON entrees.entrees_idArticle = sorties.sorties_idArticle AND entrees.idMagasin = sorties.idMagasin;



SELECT
    1 as id,
    entrees.idMagasin,
    entrees.entrees_idArticle as idArticle,
    COALESCE(((entrees.montant_entrees_stock - COALESCE(sorties.montant_sorties_stock, 0)) / NULLIF((entrees.quantite_entrees - COALESCE(sorties.quantite_sorties, 0)), 0)), 0) AS prix_unitaire,
    entrees.quantite_entrees - COALESCE(sorties.quantite_sorties, 0) AS quantite,
    :date_etat_stock as date_entree_stock
FROM
    (
        select entrees.idarticle as entrees_idArticle, entrees.idmagasin, sum(entrees.quantite) as quantite_entrees, sum(entrees.prix_unitaire * entrees.quantite) as montant_entrees_stock
        from entree_stock entrees
        where
                date_entree_stock <= :date_etat_stock and
                entrees.idarticle = :idarticle_sortant and
                entrees.idmagasin = :idmagasin_sortant
        group by entrees.idarticle, entrees.idmagasin
    ) AS entrees
        LEFT JOIN
    (
        select e.idarticle as sorties_idArticle, e.idmagasin, sum(s.quantite) as quantite_sorties, sum(e.prix_unitaire * s.quantite) as montant_sorties_stock
        from sortie_stock s
                 join entree_stock e on s.idEntreeStock = e.id
        where
                s.date_sortie_stock <= :date_etat_stock and
                e.idarticle = :idarticle_sortant and
                e.idmagasin = :idmagasin_sortant
        group by e.idarticle, e.idmagasin
    ) AS sorties
    ON entrees.entrees_idArticle = sorties.sorties_idArticle AND entrees.idMagasin = sorties.idMagasin
