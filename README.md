# Projet Applications Distribuées

## Installation de MariaDB et création de la BDD

Installer MariaDB (pour la configuration entrer le mot de passe "password" sinon cela ne fonctionne pas)

mysql --version
Pour s'assurer que la commande mysql est reconnue
Sinon ajouter la variable d'environnement pour aller au dossier bin d'où est installé MariaDB

mysql -u root -p
Puis entrer le mot de passe "password" pour accéder à la console MariaDB

Commandes à entrer pour configurer la base de données :

CREATE DATABASE teamplayerdb;
CREATE USER 'teamplayer_user'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON teamplayerdb.* TO 'teamplayer_user'@'localhost';
FLUSH PRIVILEGES;
EXIT;

La base de donnée est maintenant prête à être utilisée avec le projet.

## Lancer le projet

Ouvrir le projet sur VSCode et exécuter les commandes suivantes pour le projet :
mvn clean install
mvn clean
mvn spring-boot:run

## Voir la liste des teams et des players

Pour voir la liste des teams avec leur nom, leur id et les joueurs qui la compose, ainsi que la liste des joueurs avec leur id, aller sur :

http://localhost:8080/index.html

Pour l’instant la liste devrait être vide.

## Utilisation API avec Postman

Après chaque modification, on peut actualiser la page de la partie III pour voir les modifications.

### Créer des teams

Aller sur Postman, mettre l’URL suivante :
http://localhost:8080/api/teams

Mettre la requête en POST puis sur l'onglet Body sélectionner Raw et format JSON et mettre

{
	"name": "Ferrari"
}

Puis envoyer la requête
La team sera créée et un message sera renvoyé dans la console avec également l’id de la team (créé automatiquement) qu’il faudra retenir pour la suite quand on voudra ajouter des players ou modifier une équipe (on peut également voir l’id de la team sur la liste des teams de la page web).

Si on regarde la liste des team on voit que l’équipe Ferrari a été créée mais qu’il n’y a pas de joueurs dedans. On va d’abord créer une autre équipe de Formule 1 et on s’occupera des pilotes plus tard.

On refait pareil avec une autre équipe et on met cette fois

{
	"name": "McLaren"
}

Ici à nouveau après éxecution de la requête postman nous montre l’id de la team.

On a donc bien nos deux écuries de Formule 1 Ferrari et Mercedes qui ont été créées et que l’on peut voir sur http://localhost:8080/api/teams

### Modifier une team

Je veux modifier l’équipe McLaren pour mettre à la place une équipe Mercedes, je vais donc la modifier.

Je met l’URL suivante sur Postman :
http://localhost:8080/api/teams/id En remplaçant id par l’id de la team McLaren (indiqué par Postman ou bien sur la liste des teams)

Mettre la requête en PUT puis sur l'onglet Body sélectionner Raw et format JSON et mettre

{
	"name": "Mercedes"
}

On a donc bien notre écurie Mercedes qui remplace McLaren.

### Créer des joueurs

Je veux maintenant créer des pilotes et les ajouter aux écuries.

Aller sur Postman, mettre l’URL suivante :
http://localhost:8080/api/players

Mettre la requête en POST puis sur l'onglet Body sélectionner Raw et format JSON et mettre

{
	"name": "Charles Leclerc",
	"team": {
		"id": id →En remplaçant id par l’id de la team Ferrari
	}
}

Puis envoyer la requête.

Ici également Postman nous montre l’id généré automatiquement à la création du joueur (on peut aussi le voir sur la liste des players de la page web).

On fait de même pour les pilotes des autres écuries avec les JSON suivants :

Pour Ferrari :
{
	"name": "Carlos Sainz",
	"team": {
		"id": id →En remplaçant id par l’id de la team Ferrari
	}
}

Pour Mercedes :
{
	"name": "George Russell",
	"team": {
		"id": id →En remplaçant id par l’id de la team Mercedes
	}
}

{
	"name": "Andrea Kimi Antonelli",
	"team": {
		"id": id →En remplaçant id par l’id de la team Mercedes
	}
}

Maintenant quand on regarde la liste des teams on voit les deux teams avec leur id respectif et les deux pilotes de chaque écurie.

Et en regardant la liste des joueurs, on voit les 4 pilotes différents avec leur id respectif.

### Modifier un joueur

En rentrant le nom du deuxième pilote de Ferrari je me suis trompé car ce n’est plus celui là pour la prochaine saison, je vais donc modifier le pilote “Carlos Sainz” pour le renommer “Lewis Hamilton”.

Je met l’URL suivante sur Postman :
http://localhost:8080/api/players/id En remplaçant id par l’id du pilote Carlos Sainz (indiqué par Postman ou bien sur la liste des players)

Mettre la requête en PUT puis sur l'onglet Body sélectionner Raw et format JSON et mettre

{
	"name": "Lewis Hamilton"
}

On a donc bien notre pilote Lewis Hamilton qui remplace Carlos Sainz dans l’écurie Ferrari.

### Supprimer un joueur

On va maintenant essayer de supprimer un pilote de l’écurie Mercedes.

Je met l’URL suivante sur Postman :
http://localhost:8080/api/players/id En remplaçant id par l’id du pilote Andrea Kimi Antonelli (indiqué par Postman ou bien sur la liste des players)

Mettre la requête en DELETE puis envoyer la requête sans Body.

On a donc bien notre pilote Andrea Kimi Antonelli qui a été supprimé et qui n’apparaît plus ni dans la liste des players, ni dans les players de la team Mercedes.

### Supprimer une team

On va maintenant essayer de supprimer carrément l’écurie Mercedes.

Je met l’URL suivante sur Postman :
http://localhost:8080/api/teams/id En remplaçant id par l’id de l’écurie Mercedes (indiqué par Postman ou bien sur la liste des teams)

Mettre la requête en DELETE puis envoyer la requête sans Body.

On a donc bien l’écurie Mercedes qui a été supprimée ainsi que tous les pilotes qui en faisaient partie.