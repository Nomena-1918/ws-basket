-- Réinitialisation des séquences et suppression des données
TRUNCATE mouvements, type_mouvements, actions, type_actions, matchs, stades,  contrats, joueurs, saison_equipes, equipes, saisons RESTART IDENTITY;

-- Restart matchs d'une saison
TRUNCATE actions restart identity cascade;