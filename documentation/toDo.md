## Implemented Features

- [x] Etape 1:

  - [x] déplacement d’un seul segment
  - [x] dans une grille à maillage rectangulaire
  - [x] coordonnées entières
  - [x] avec pas de 1 unité vers le nord, l’est, le sud ou l’ouest.
  - [x] Le déplacement est tour par tour.

- [ ] Etape 2:

  - [x] ajouter les **joueurs**
  - [ ] implémenter une **IA**

- [x] Etape 3: **déplacement d’un serpent de multiples segments**

  - [x] Le serpent se divise en segments, seul le premier segment fait un pas dans la direction indiquée à chaque mise à jour. Le second fait un pas vers le premier, le troisième vers le second, et ainsi de suite.
  - [x] Cela implique que le serpent ne reste pas tout droit, et qu’il y a une certaine **inertie** dans le déplacement de la queue

- [x] Etape 4: **gestion de la mort**

  - [x] collision avec un obstacle tiers
  - [ ] auto-collision :arrow_right: **On oublie ça**

- [x] Etape 5: **gestion de la croissance**

  - [x] quand on passe sur un morceau de nourriture, il disparaît, un autre apparaît à une position aléatoire sur le terrain et le serpent s’allonge d’un segment.

- [x] Etape 6: **déplacement en temps réel dans une fenêtre graphique**

  - [x] on se donne une « unité » de temps qui correspond au temps nécessaire pour faire un pas entier
  - [x] on fixe une nouvelle direction toutes les « unités » de temps :arrow_right: celle-ci est donnée par la dernière flèche de direction appuyée par le joueur

- [x] Etape 7: **deux joueurs au clavier**

- [x] Etape 8: **déplacement fluide**

  - [x] les coordonnées sont maintenant des double
  - [x] la taille du pas doit être égale à la **vitesse fois le temps écoulé** depuis le dernier pas
  - [x] on met à jour la direction à chaque fois que c’est possible sans attendre une unité de temps fixée

- [x] Etape 9: **déplacement libre**

  - [ ] la direction n’est plus donnée par la dernière touche appuyée, mais par la position du pointeur par rapport à la tête du serpent
  - [x] cette direction peut suivre un angle quelconque, représenté par un double
  - [x] le pointeur lui-même peut être déplacé à la souris et/ou au clavier

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

## Objectifs

- [ ] Maîtrise du polymorphisme: implémenter **plusieurs types de segments**
- [ ] Gestion correcte de la concurrence: implémenter un **mode réseau**

## Contraintes techniques

### Réseau

- [ ] architecture client-serveur
- [ ] le **serveur est un programme séparé du client** (ou peut être lancé comme tel)

_Facultatif:_ On aimerait que les clients et serveurs soient interopérables entre les binômes (c’est bon pour la
qualité), mais il ne faut pas que cela vous fasse perdre trop de temps. Cela dit n’hésitez pas à
partager votre protocole sur le forum Moodle du projet si vous arrivez au mode réseau.
Une implémentation basique de client et de serveur TCP vous seront fournis à titre d’exemple

### Concurrence

- [x] mode solo: le logiciel s’exécute **sans création explicite de thread supplémentaire** (i.e entièrement dans le thread applicatif du toolkit graphique choisi)
- [ ] mode réseau: le **client crée un thread séparé pour gérer la connexion au serveur**.
- [ ] mode réseau: le **serveur utilisera un thread pool** (1 thread par connexion client).
- [ ] vérifier la bonne synchronisation
- [ ] privilégier les structures immuables
- [ ] limiter le nombre de variables partagées.

### Paramétrabilité et réutilisabilité

- [ ] utiliser la **composition** plutôt que l'héritage
- [ ] **fabriques statiques**: prévoir une **classe pour stocker et centraliser la configuration**
- [ ] les objets du jeu s’instancient en lisant ces paramètres
- [x] une instance d’une telle configuration est fabriquée grâce à **l’écran de configuration du jeu**
- [ ] une instance de configuration doit **pouvoir être sérialisée/désérialisée** (en JSON ?)

### Testabilité

- [ ] Dans tous les modes, tout ce que vous écrirez devra avoir été programmé de telle sorte à ce que le comportement soit testable.
- [ ] Le _découpage en méthodes_ doit être suffisamment fin pour permettre des **tests unitaires pertinents**.
- [ ] Le _découpage en classes_ et la **programmation « à l’interface »** doivent permettre d’**effectuer facilement des tests**.

## Critères d'évalutation:

- [ ] README.md:

  - [x] comment compiler
  - [x] comment exécuter
  - [x] comment tester
  - [x] fonctionnalités effectivement implémentées
  - [x] choix techniques

- [ ] Code architecturé intelligement:

  - [ ] utilisation des **patrons de conception vus en cours**
  - [x] utilisation des constructions les plus appropriées fournies par Java (Property, Abstract class, Getter & Setter...)

- [ ] L’exécution doit être correcte:

  - [x] elle **respecte le cahier des charges** et ne quitte pas de façon non contrôlée
  - [ ] en mode graphique, les **exceptions doivent être rattrapées**
  - [ ] Dans tous les cas: si le problème n’est pas réparable, **un message d’erreur doit être présenté à l’utilisateur**. Aucune de ces erreurs ne doit être due à un problème de programmation (NullPointerException...)

- [ ] Diagrammes de classe
- [ ] Utilisation des patrons de conception vus en cours
- [ ] Documentation (javadoc)
- [ ] Tests aussi exhausitfs que possible
- [x] [_Commentaires expliquant les portions de codes pas évidentes_]()
- [x] Compilation et exécution avec Gradle
- [x] Respect des conventions de codage

Fonctionnalités:

- Menu démarrer graphique avec choix du mode de jeu
- SNAKE: Fonctionnel
- SlitherIo: Fonctionnel
  => manque gestion de la mort (retour au menu)
