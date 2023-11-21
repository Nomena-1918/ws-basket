-- Réinitialisation des séquences et suppression des données
TRUNCATE mouvements, type_mouvements, action, type_actions, matchs, stades,  contrats, joueurs, saison_equipes, equipes, saisons RESTART IDENTITY;

-- Restart matchs d'une saison
TRUNCATE action restart identity cascade;