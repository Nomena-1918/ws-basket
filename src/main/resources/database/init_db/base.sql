create table code_comptable(
    id serial primary key,
    code varchar(100) not null
);

CREATE TYPE ordre_tri AS ENUM ('ASC', 'DESC');
create table methode_sortie(
    id serial primary key,
    nom varchar(100) not null,
    description ordre_tri
);

create table unite(
    id serial primary key,
    nom varchar(100) not null
);

create table magasin(
    id serial primary key,
    nom varchar(100) not null
);

create table article(
    id serial primary key,
    nom varchar(100) not null,
    idMethodeSortie int references methode_sortie(id),
    idUnite int references unite(id),
    idCodeComptable int references code_comptable(id)
);

create table entree_stock(
    id serial primary key,
    idArticle int references article(id),
    idMagasin int references magasin(id),
    prix_unitaire decimal not null check ( prix_unitaire>0 ),
    quantite decimal not null check ( quantite>0 ),
    date_entree_stock timestamp not null default now()
);

create table entree_stock_pour_sortie(
    id serial primary key,
    idEntree_stock int references entree_stock(id),
    quantite_restante decimal not null check ( quantite_restante>0 ),
    quantite_cumulative decimal not null check ( quantite_cumulative>0 )

);

create table sortie_stock(
    id serial primary key,
    idEntreeStock int references entree_stock(id),
    quantite decimal not null check ( quantite>0 ),
    date_sortie_stock timestamp not null default now()
);

