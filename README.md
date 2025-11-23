## 🎮 ±123D Game Rules

The **±123D** game is a two-player board game, generally referred to as **Max (Player A, blue)** and **Min (Player B, red)**, played on a linear board of configurable size.

### 1. The Board and Objective

* **Board**: A sequence of numbered cells, from `0` to `N-1`.
* **Positions**: Players start at cell 0.
* **Flag**: At the start of the game, the flag is located at the last cell; the player who brings it back to cell 0 wins the game.
* **Burnt Cells**: Cells left by a player are marked as "burnt" and become inaccessible.

* **Objective**: Reach and move the flag to cell 0, or force the opponent into a situation where they cannot make a valid move.

  <img width="1298" height="196" alt="Capture d’écran du 2025-11-22 19-38-57" src="https://github.com/user-attachments/assets/9fa87646-b964-440b-9410-0a0b30ef0f25" />


### 2. Turn Sequence

Players take turns playing (Max starts). On their turn, a player must perform **only one** of the following two actions:

#### A. Movement Action

The player moves from their current position to a new cell.

* **Allowed Moves**: **±1, ±2, or ±3** cells.
* **Conditions**:
    1.  The new position must be **within the board limits**.
    2.  The new position must **not be a burnt cell** (`x`).
* **Consequence**: The cell the player just **left** is marked as **burnt** (`x`).

#### B. Flag Movement Action (Action "D")

The player can move the flag to a free cell at a distance of **±D**, where **D > 3** (e.g., +4, -5, +9, etc.).

* **Conditions**:
    1.  The new position of the flag must be **within the board limits**.
    2.  The new position must **not be a burnt cell** (`x`).
    3.  The new position must **be different from both players' positions**.
* **Consequence**:
    1.  The cell the player just **left** is marked as **burnt** (`x`).
    2.  The flag is moved to the new position.

### 3. End Game Conditions

* **Victory by Flag Movement:** A player reaches the flag's cell and successfully moves it to cell 0 during their turn. This player **wins immediately**.
* **Victory by Blockage:** A player cannot perform **any** valid action (movement or flag displacement). This player **loses the game**, and the opponent wins.


## 🚀 Features

* **Graphical User Interface (GUI)**: Allows playing with a user-friendly visual interface.

  <img width="1551" height="376" alt="image" src="https://github.com/user-attachments/assets/69ada9e8-40e8-497b-b8c2-333e90cf4735" />

* **Console Mode**: Allows playing directly via the command line.
* **Multiple Player Types**:
    * **Manual (Human)**: Player interacting via the GUI or Console.
    * **Random (`JoueurAleatoire`)**: Player making valid moves at random.
    * **Artificial (AI - `JoueurArtificiel`)**: Implementation of an artificial intelligence based on the **Minimax algorithm with Alpha-Beta pruning** for optimal play (see source code for AI authors).
    * **External Interfaces**: Supports external players via command line (`JoueurInterfaceCmd`) or socket (`JoueurInterfaceSocket`), allowing the use of an AI developed in another language.

## ⚙️ Project Structure

The project consists mainly of Java files:

| File | Description |
| :--- | :--- |
| `Plateau.java` | Contains the main game logic, board state, and turn/action management. |
| **`JeuGUI.java`** | Entry point for the GUI version. Also implements the human player for the GUI. |
| **`JeuConsole.java`** | Entry point for the command line version. |
| **`AfficheurPlateau.java`** | Swing component for the graphical rendering of the game board. |
| **`ConfigDialog.java`** | Configuration dialog to choose board size and player types for A and B. |
| **`ConfigJoueurPanel.java`**| Configuration panel for a single player (choice of type: Manual, Random, AI, External). |
| **`Joueur.java`** | Interface that all player types must implement. |
| **`JoueurArtificiel.java`** | AI implementation (Minimax Alpha-Beta). |
| **`JoueurAleatoire.java`** | Implementation of a player making random moves. |
| **`JoueurConsole.java`** | Player implementation for the console interface (read/write on `stdin`/`stdout`). |
| **`JoueurInterfaceCmd.java`**| Manages the interface with an external player launched as a command line process. |
| **`JoueurInterfaceSocket.java`**| Manages the interface with an external player via a network connection (socket). |

## 🛠️ Compilation and Execution

This project is written in **Java**.

### 1. Prerequisites

Ensure you have the **JDK (Java Development Kit)** installed on your machine.

### 2. Compilation

Import the project locally and run the following commands:

```bash
make
make run

