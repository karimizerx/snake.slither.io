- [x] Etape 1:

  - [x] déplacement d’un seul segment
  - [x] dans une grille à maillage rectangulaire
  - [x] coordonnées entières
  - [x] avec pas de 1 unité vers le nord, l’est, le sud ou l’ouest.
  - [x] Le déplacement est tour par tour.

- [ ] Etape 2:

  - [ ] ajouter les **joueurs**
  - [ ] implémenter une **IA**

- [ ] Etape 3: **déplacement d’un serpent de multiples segments**

  - [x] Le serpent se divise en segments, seul le premier segment fait un pas dans la direction indiquée à chaque mise à jour. Le second fait un pas vers le premier, le troisième vers le second, et ainsi de suite.
  - [ ] Cela implique que le serpent ne reste pas tout droit, et qu’il y a une certaine **inertie** dans le déplacement de la queue

- [ ] Etape 4: **gestion de la mort**

  - [x] collision avec un obstacle tiers
  - [ ] auto-collision :arrow_right: <span style="color:red">On oublie ça</span>

- [ ] Etape 5: **gestion de la croissance**

  - [x] quand on passe sur un morceau de nourriture, il disparaît, un autre apparaît à une position aléatoire sur le terrain et le serpent s’allonge d’un segment. :arrow_right: <span style="color:red">Gérer la concurrence</span>

- [x] Etape 6: **déplacement en temps réel dans une fenêtre graphique**

  - [x] on se donne une « unité » de temps qui correspond au temps nécessaire pour faire un pas entier
  - [x] on fixe une nouvelle direction toutes les « unités » de temps :arrow_right: celle-ci est donnée par la dernière flèche de direction appuyée par le joueur

- [ ] Etape 7: **deux joueurs au clavier**

- [x] Etape 8: **déplacement fluide**

  - [x] les coordonnées sont maintenant des double
  - [x] la taille du pas doit être égale à la **vitesse fois le temps écoulé** depuis le dernier pas
  - [x] on met à jour la direction à chaque fois que c’est possible sans attendre une unité de temps fixée

- [ ] Etape 9: **déplacement libre**

  - [ ] la direction n’est plus donnée par la dernière touche appuyée, mais par la position du pointeur par rapport à la tête du serpent
  - [ ] cette direction peut suivre un angle quelconque, représenté par un double
  - [ ] le pointeur lui-même peut être déplacé à la souris et/ou au clavier

- [ ] Etape 10: **terrain sans bords**

  - [ ] le terrain est bien plus grand que la zone d’affichage (gérer défilement)
  - [ ] traverser un bord fait ré-apparaître le serpent sur le bord opposé :arrow_right: faire en sorte qu’on ne se rende pas compte de cela dans l’UI qui doit donner l’illusion d’un terrain infini

- [ ] Etape 11: **règles personnalisées**

- [ ] Etape 12: **mode réseau classique**

  - [ ] en mode client/serveur le serveur fait foi pour ce qui est de l’état du jeu (ce qui n’empêche pas au client d’interpoler les déplacements pour donner une impression de fluidité)
  - [ ] le jeu n’est plus limité à 2 joueurs

- [ ] Etape 13: **monde permanent**

  - [ ] comme dans slither.io, la partie n’est jamais terminée, et on peut la rejoindre n’importe quand

- [ ] Etape 14: **règles slither.io**
  - [ ] la nourriture provient de la mort d’un serpent
  - [ ] la vitesse dépend de la taille
  - [ ] on peut « brûler » un segment pour augmenter temporairement sa vitesse...
- :warning: Au point où vous en êtes, ces modifications devraient très peu changer votre base de code
