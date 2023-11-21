-- Réinitialisation des séquences et suppression des données
TRUNCATE sortie_stock, entree_stock, article, magasin, methode_sortie, unite RESTART IDENTITY;

TRUNCATE sortie_stock, entree_stock restart identity cascade;