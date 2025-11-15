# Jeu ±123D (Version 3)

Un projet implémentant le jeu de plateau **±123D** en Java. Ce jeu met en compétition deux joueurs (Max et Min) sur un plateau linéaire, où l'objectif principal est d'atteindre le drapeau ou d'empêcher l'adversaire de jouer.

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

Compilez tous les fichiers source Java :

```bash
javac *.java
