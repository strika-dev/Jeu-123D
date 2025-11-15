## 🎮 Les Règles du Jeu ±123D

Le jeu **±123D** est un jeu de plateau pour deux joueurs, généralement appelés **Max (Joueur A, bleu)** et **Min (Joueur B, rouge)**, joué sur un plateau linéaire de taille configurable.

### 1. Le Plateau et l'Objectif

* **Plateau** : Séquence de cases numérotées, de `0` à `N-1`.
* **Positions** : Les joueurs commencent à la case 0.
* **Drapeau** : Au début du jeu, le drapeau se trouve à la dernière case, le joueur qui le ramène à la case 0 gagne la partie
* **Cases Brûlées** : Les cases quittées par un joueur sont marquées comme "brûlées et deviennent inaccessibles.

* **Objectif** : Atteindre et déplacer le drapeau à la case 0, ou forcer l'adversaire à ne plus pouvoir effectuer de coup valide.

### 2. Le Déroulement du Tour

Les joueurs jouent à tour de rôle (Max commence). À son tour, un joueur doit effectuer **une seule** des deux actions suivantes :

#### A. Action de Mouvement (Déplacement)

Le joueur se déplace de sa position actuelle vers une nouvelle case.

* **Déplacements Permis** : **±1, ±2 ou ±3** cases.
* **Conditions** :
    1.  La nouvelle position doit être **dans les limites du plateau**.
    2.  La nouvelle position ne doit **pas être une case brûlée** (`x`).
* **Conséquence** : La case que le joueur vient de **quitter** est marquée comme **brûlée** (`x`).

#### B. Action de Déplacement du Drapeau (Action "D")

Le joueur peut déplacer le drapeau vers une case libre à une distance de **±D**, où **D > 3** (ex: +4, -5, +9, etc.).

* **Conditions** :
    1.  La nouvelle position du drapeau doit être **dans les limites du plateau**.
    2.  La nouvelle position ne doit **pas être une case brûlée** (`x`).
    3.  La nouvelle position doit **être différente de la position des deux joueurs**.
* **Conséquence** :
    1.  La case que le joueur vient de **quitter** est marquée comme **brûlée** (`x`).
    2.  Le drapeau est déplacé à la nouvelle position.

### 3. Conditions de Fin de Partie

* **Victoire par Mouvement du Drapeau :** Un joueur atteint la case du drapeau et le déplace avec succès à la case 0 lors de son tour. Ce joueur **gagne immédiatement**.
* **Victoire par Blocage :** Un joueur ne peut effectuer **aucune** action valide (mouvement ou déplacement du drapeau). Ce joueur **perd la partie**, et l'adversaire gagne.


## 🚀 Fonctionnalités

* **Interface Graphique (GUI)** : Permet de jouer avec une interface visuelle conviviale.
* **Mode Console** : Permet de jouer directement via la ligne de commande.
* **Multiples Types de Joueurs** :
    * **Manuel (Humain)** : Joueur interagissant via la GUI ou la Console.
    * **Aléatoire (`JoueurAleatoire`)** : Joueur effectuant des mouvements valides au hasard.
    * **Artificiel (IA - `JoueurArtificiel`)** : Implémentation d'une intelligence artificielle basée sur l'algorithme **Minimax avec élagage Alpha-Beta** pour un jeu optimal (voir code source pour les auteurs de l'IA).
    * **Interfaces Externes** : Supporte des joueurs externes via ligne de commande (`JoueurInterfaceCmd`) ou socket (`JoueurInterfaceSocket`), permettant d'utiliser une IA développée dans un autre langage.

## ⚙️ Structure du Projet

Le projet est principalement constitué de fichiers Java :

| Fichier | Description |
| :--- | :--- |
| `Plateau.java` | Contient la logique principale du jeu, l'état du plateau, et la gestion des tours/actions. |
| **`JeuGUI.java`** | Point d'entrée pour la version avec interface graphique (GUI). Implémente également le joueur humain pour la GUI. |
| **`JeuConsole.java`** | Point d'entrée pour la version en ligne de commande. |
| **`AfficheurPlateau.java`** | Composant Swing pour le rendu graphique du plateau de jeu. |
| **`ConfigDialog.java`** | Dialogue de configuration pour choisir la taille du plateau et les types de joueurs A et B. |
| **`ConfigJoueurPanel.java`**| Panneau de configuration pour un seul joueur (choix du type : Manuel, Aléatoire, IA, Externe). |
| **`Joueur.java`** | Interface que tous les types de joueurs doivent implémenter. |
| **`JoueurArtificiel.java`** | Implémentation de l'IA (Minimax Alpha-Beta). |
| **`JoueurAleatoire.java`** | Implémentation d'un joueur effectuant des coups aléatoires. |
| **`JoueurConsole.java`** | Implémentation du joueur pour l'interface console (lecture/écriture sur `stdin`/`stdout`). |
| **`JoueurInterfaceCmd.java`**| Gère l'interface avec un joueur externe lancé comme un processus en ligne de commande. |
| **`JoueurInterfaceSocket.java`**| Gère l'interface avec un joueur externe via une connexion réseau (socket). |

## 🛠️ Compilation et Exécution

Ce projet est écrit en **Java**.

### 1. Prérequis

Assurez-vous d'avoir le **JDK (Java Development Kit)** installé sur votre machine.

### 2. Compilation

Importer le projet localement puis éxécuter les commandes suivantes :

```bash
make
make run
