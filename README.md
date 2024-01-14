## The Snake Slither

### Liens vers :

- [**le code source**](/src/main/java/slitherio/)
- [**les tests**](/src/test/java/slitherio/mainTest.java)
- [**les ressources du jeu**](/src/main/resources/)
- [**toute la documentation**](/documentation/)

### Pour compiler :

```
make build
```

### Pour exécuter :

```
make run
```

### Pour tester :

```
make test
```

### Pour lire la documentation :

**Avec chrome:**

```
make javadoc
```

**Avec un autre lecteur de fichiers HTML, par exemple vscode:**

```
code documentation/javadoc/overview-summary.html
```

### Fonctionnalités implémentées:

Lire [**_toDo.md_**](/documentation/toDo.md).

### Choix techniques:

- Le jeu peut être mis en pause et relancé avec la touche `P`.
- Durant une partie, on peut revenir au menu principale à tout moment avec la touche `ESCAP`.
- Depuis le menu principal, on peut arrêter le programme avec la touche `ESCAP`.
- Le terrain des jeux **SNAKE** et **SLITHERIO** est sans bord: traverser un bord fait apparaître le serpent de l'autre côté.
- Dans **SLITHERIO**, il n'y a pas d'auto-collision.
- ...

### Architecture

Lire [**_diagramme.de.classes.png_**](/documentation/architecture/diagramme.de.classes.png).
