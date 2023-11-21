INSERT INTO saisons (libelle, debut, fin)
VALUES ('Saison 2023', '2023-01-01', '2023-12-31'),
       ('Saison 2024', '2024-01-01', '2024-12-31');


INSERT INTO equipes (nom, acronyme)
VALUES
    ('Lions', 'LIO'),
    ('Faucons', 'FAU');

INSERT INTO saison_equipes (idsaison, idequipe)
VALUES (1, 1),
       (1, 2),

       (2, 1),
       (2, 2);


select * from saison_equipes where idsaison = 1;

INSERT INTO joueurs (nom, prenom)
VALUES
    ('Johnson', 'Michael'),
    ('Smith', 'Christopher'),
    ('Williams', 'Anthony'),
    ('Jones', 'Andrew'),
    ('Davis', 'Brandon'),
    ('Miller', 'Jordan'),
    ('Brown', 'Marcus'),
    ('Taylor', 'Derrick'),
    ('Wilson', 'Jeremy'),
    ('Moore', 'Kevin'),
    ('Anderson', 'Tyler'),
    ('Jackson', 'Kyle');


INSERT INTO contrats (idequipe, idjoueur, numero, debut, fin)
VALUES (1, 1, 1, '2023-01-01', '2023-12-31'),
       (1, 2, 2, '2023-01-01', '2023-12-31'),
       (1, 3, 3, '2023-01-01', '2023-12-31'),
       (1, 4, 3, '2023-01-01', '2023-12-31'),
       (1, 5, 3, '2023-01-01', '2023-12-31'),
       (1, 6, 3, '2023-01-01', '2023-12-31'),
       (2, 7, 3, '2023-01-01', '2023-12-31'),
       (2, 8, 3, '2023-01-01', '2023-12-31'),
       (2, 9, 3, '2023-01-01', '2023-12-31'),
       (2, 10, 3, '2023-01-01', '2023-12-31'),
       (2, 11, 3, '2023-01-01', '2023-12-31'),
       (2, 12, 3, '2023-01-01', '2023-12-31');


INSERT INTO stades (nom)
VALUES
    ('Stade Olympique'),
    ('Stade des Lumières');

INSERT INTO matchs (idsaison, idstade, idequipe1, idequipe2, debut, fin)
VALUES (1, 1, 1, 2, '2023-01-01 14:00:00', '2023-01-01 16:00:00'),
       (1, 2, 2, 1, '2023-01-02 14:00:00', '2023-01-02 16:00:00');


INSERT INTO type_mouvements (nom)
VALUES ('entree'),
       ('sortie');


INSERT INTO mouvements (idmatch, idtype, idjoueur, dateheure)
VALUES
    --  EQUIPE 1
    -- Entrées sur terrains
    (1, 1, 1, '2023-01-01 14:00:00'),
    (1, 1, 2, '2023-01-01 14:00:00'),
    (1, 1, 3, '2023-01-01 14:00:00'),
    (1, 1, 4, '2023-01-01 14:00:00'),
    (1, 1, 5, '2023-01-01 14:00:00'),


    -- Sorties terrains
    (1, 2, 1, '2023-01-01 16:00:00'),
    (1, 2, 2, '2023-01-01 16:00:00'),
    (1, 2, 3, '2023-01-01 16:00:00'),
    (1, 2, 4, '2023-01-01 16:00:00'),
    (1, 2, 5, '2023-01-01 16:00:00'),


    --  EQUIPE 2
    -- Entrées sur terrains
    (1, 1, 7, '2023-01-01 14:00:00'),
    (1, 1, 8, '2023-01-01 14:00:00'),
    (1, 1, 9, '2023-01-01 14:00:00'),
    (1, 1, 10, '2023-01-01 14:00:00'),
    (1, 1, 11, '2023-01-01 14:00:00'),


    -- Sorties terrains
    (1, 2, 7, '2023-01-01 16:00:00'),
    (1, 2, 8, '2023-01-01 16:00:00'),
    (1, 2, 9, '2023-01-01 16:00:00'),
    (1, 2, 10, '2023-01-01 16:00:00'),
    (1, 2, 11, '2023-01-01 16:00:00');

INSERT INTO type_actions (nom)
VALUES ('3P'),
       ('FG'),
       ('LF'),
       ('REBOND'),
       ('PD'),
       ('TO'),
       ('STL'),
       ('BLK');

INSERT INTO actions (idtype, idjoueur, idmatch, dateheure, valeur)
VALUES
    -- Les 3P
    (1, 1, 1, '2023-01-01 14:00:00', 3),
    (1, 2, 1, '2023-01-01 14:01:00', 0),
    (1, 3, 1, '2023-01-01 14:30:00', 3),

    -- Les Field Goals
    (2, 4, 1, '2023-01-01 14:35:00', 2),
    (2, 5, 1, '2023-01-01 14:40:00', 0),
    (2, 7, 1, '2023-01-01 14:45:00', 2),

    -- Les Lancers Francs
    (3, 8, 1, '2023-01-01 15:00:00', 1),
    (3, 9, 1, '2023-01-01 15:05:00', 0),
    (3, 10, 1, '2023-01-01 15:10:00', 1),

    -- Les Rebonds
    (4, 11, 1, '2023-01-01 15:20:00', 0),
    (4, 1, 1, '2023-01-01 15:30:00', 0),
    (4, 2, 1, '2023-01-01 15:35:00', 0),

    -- Les Passes décisives
    (5, 3, 1, '2023-01-01 15:40:00', 0),
    (5, 4, 1, '2023-01-01 15:41:00', 0),
    (5, 5, 1, '2023-01-01 15:42:00', 0),

    -- Les Turn Overs
    (6, 7, 1, '2023-01-01 15:43:00', 0),
    (6, 8, 1, '2023-01-01 15:44:00', 0),
    (6, 9, 1, '2023-01-01 15:45:00', 0),

    -- Les Steals
    (7, 10, 1, '2023-01-01 15:46:00', 0),
    (7, 11, 1, '2023-01-01 15:47:00', 0),
    (7, 1, 1, '2023-01-01 15:48:00', 0),

    -- Les Blocks
    (8, 2, 1, '2023-01-01 15:49:00', 0),
    (8, 3, 1, '2023-01-01 15:50:00', 0),
    (8, 4, 1, '2023-01-01 15:55:00', 0);


-- Nbr Matchs joués dans chaque saison
-- Nbr Points marqués par match en moyenne dans chaque saison
-- Nbr Rebonds par match en moyenne dans chaque saison
-- Nbr Passes décisives par match en moyenne dans chaque saison
-- Nbr Blocks par match en moyenne dans chaque saison
-- Nbr minutes jouées par match en moyenne dans chaque saison


-- Matchs joués par équipe
select distinct count(idmatch) from matchs where idequipe1 = 1 or idequipe2 = 1;

-- Match joués par joueur
create view v_matchs_joues as
select idmatch, idjoueur from mouvements group by idmatch, idjoueur;

select count(*) from v_matchs_joues where idjoueur = 6;

-- Points marqués par match
select sum(valeur) from actions where idjoueur = 1;

select sum(valeur)/(select count(*) from v_matchs_joues where idjoueur = 1) as points_moyens from actions where idjoueur = 1;

-- Rebonds moyens par match
select count(*) from actions where idtype = 4 and idjoueur = 1;

select count(*)/(select count(*) from v_matchs_joues where idjoueur = 1) as points_moyens from actions where idtype = 4 and idjoueur = 1;


-- Passes décisives moyennes par match
select count(*) from actions where idtype = 5 and idjoueur = 4;

select count(*)/(select count(*) from v_matchs_joues where idjoueur = 4) as points_moyens from actions where idtype = 5 and idjoueur = 4;


-- Total minutes

-- View Entrees
create view v_entrees_joueur as
select * from mouvements where idtype = 1 order by dateheure;

-- View Sorties
create view v_sorties_joueur as
select * from mouvements where idtype = 2 order by dateheure;

--and idjoueur = 1
-- Minutes jouées toatles
create or replace view minutes_jouees as
select sum(EXTRACT(EPOCH FROM (v_sorties_joueur.dateheure - v_entrees_joueur.dateheure)))/60 as minutes_jouee, v_entrees_joueur.idjoueur from v_entrees_joueur join v_sorties_joueur on  v_entrees_joueur.idjoueur = v_sorties_joueur.idjoueur group by v_entrees_joueur.idjoueur;


select */ from minutes_jouees where idjoueur = 1;


