create table saisons(
    idsaison serial primary key,
    libelle varchar(100) unique not null,
    debut timestamp not null,
    fin timestamp not null check ( fin > debut )
);

create table equipes(
    idequipe serial primary key,
    nom varchar(100) unique,
    acronyme varchar(10)
);

create table saison_equipes(
    idsaisonequipe serial primary key,
    idsaison int references saisons (idsaison) not null,
    idequipe int references equipes (idequipe) not null
);

create table joueurs(
    idjoueur serial primary key,
    nom varchar(100) not null,
    prenom varchar(100) not null
);

create table contrats(
    idcontrat serial primary key,
    idequipe int references equipes (idequipe) not null,
    idjoueur int references joueurs (idjoueur) not null,
    numero int not null check ( numero > 0 ),
    debut timestamp not null,
    fin timestamp not null check ( fin > debut )
);

create table stades(
    idstade serial primary key,
    nom varchar(100)
);

create table matchs(
    idmatch serial primary key,
    idsaison int references saisons (idsaison) not null,
    idstade int references stades (idstade) not null,
    idequipe1 int references equipes (idequipe) not null,
    idequipe2 int references equipes (idequipe) not null,
    debut timestamp not null,
    fin timestamp not null check ( fin > debut )
);

create table type_actions(
    idtype_action serial primary key,
    nom varchar(100) not null
);

create table actions(
    idaction serial primary key,
    idtype int references type_actions (idtype_action) not null,
    idjoueur int references joueurs (idjoueur) not null,
    idmatch int references matchs (idmatch) not null,
    dateheure timestamp not null,
    valeur int not null default 0
);

create table type_mouvements(
    idtype_mouvement serial primary key,
    nom varchar(100) not null
);

create table mouvements(
    idmouvement serial primary key,
    idmatch int references matchs (idmatch) not null,
    idtype int references type_mouvements (idtype_mouvement) not null,
    idjoueur int references joueurs (idjoueur) not null,
    dateheure timestamp not null
);