# tpPokemon


_________________________________________________________________________________________________________________________________________________________
  

# Projet réalisé par :
Stéphane Thiry et Frédéric Turcq-Santucci


_________________________________________________________________________________________________________________________________________________________
  


# Fonctionnement 

Se positionner dans le dossier tpPokemon, lancer le main présent dans le fichier src/Client/main.java.

A la création d'un nouveau compte un fichier texte sera créé dans le dossier sauvegarde au nom donné par le joueur.

Si vous souhaitez charger votre sauvegarde, répondez "y" à la première question et renseignez le nom de votre compte.


Une fois dans le menu principal vous aurez une liste de choix, entrez en console le numéro correspondant au choix souhaité.
- Pour évoluer ou supprimer un pokemon il vous faudra choisir l'option "3. Voir ses pokemons"  puis dans ce sous-menu vous aurez accès aux options "évoluer" et "supprimer".

Pour se faire affronter deux dresseurs vous devez au préalable :
- créer deux dresseur
- posséder 6 pokemons minimum
- lancer le serveur "src/Serveur/Serveur.java"
- le combat se lance grâce à l'option "2. Combattre un autre dresseur"
- il vous sera demandé de selectionner 6 pokemons pour combattre
- une fois ceci fait le combat s'exécute automatiquement
- une fois le combat fini le pseudo du gagnant est affiché et celui-ci gagne 100 points d'xp (ce qui le fait monter d'un niveau)


_________________________________________________________________________________________________________________________________________________________
  

#  ATTENTION  
Pour faire évoluer un pokémon vous aurez besoin de :
  -  10 bonbons si le pokémon n'a qu'une seule évolution ou si c'est sa dernière évolution
  -  5 bonbons si le pokémon possède plusieurs évolutions et que c'est sa première évolution
  -  10 bonbon pour "Evoli", il évoluera dans une des 3 évolutions possibles : Pyroli, Voltali, Aquali, de manière aléatoire

