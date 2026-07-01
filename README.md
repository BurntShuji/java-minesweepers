# 💣 Java Minesweeper with Persistent Leaderboard

A classic implementation of the retro puzzle game **Minesweeper**, built from scratch using Java Swing and Abstract Window Toolkit (AWT). This project features dynamic board state updates, right-click flag placements, robust edge-case exception handling, and a persistent local text file leaderboard.

---

## 👤 Author Information
* **Developer:** II Landicho (BurntShuji)
* **Project Completion Date:** June 30, 2026
* **Language:** Java SE
* **Framework:** Java Swing / AWT

---

## 🎮 Game Rules & Conditions
* **Objective:** Clear all tiles across the grid that do not contain hidden mines.
* **Winning Condition:** Successfully open all safe grid spaces (`(Rows * Columns) - Mine Count`). Winning awards a massive point bonus on the score ranking system!
* **Losing Condition:** Left-clicking an active mine tile (`💣`). The board is frozen, remaining hidden mines are exposed, and your current point total is registered.

---

## ✨ Features

* **Custom Player Initialization:** Prompt dialog at startup collects your username or handles quick-start anonymous play.
* **Interactive Controls:**
  * **Left-Click:** Reveal a grid cell. Safe blocks dynamically display color-coded indicators based on surrounding mine clusters.
  * **Right-Click:** Toggle custom red flag markers (`🚩`) to lock down suspected bomb sites without disabling background rendering properties.
* **Live Score Tracker:** Real-time updates populate the window's main header display tracking safe tile completion.
* **Persistent File Leaderboard:** Automated file I/O operations log matches directly to `minesweepLeadB.txt`. Top 10 high scores are read, sanitized of trailing whitespaces, sorted via Lambda expressions, and cleanly output into custom option frames.
* **Seamless Replay Loop:** Easily clear out the active board matrix, regenerate mine grids, and re-register names on request without manually restarting the bytecode application.

---

## 💡 Customization Adjustments
To customize the visual theme properties or parameters manually inside `Minesweeper.java`, tweak the parameters below:
* **Grid Density:** Alter `numRow`, `numCol`, or `mineCount` fields to transition between Easy, Medium, or Hard modes.
* **Sizing Rules:** Decrease `tileSize = 70;` down to smaller integers to allow expert fields to render smoothly onto compact monitor resolutions.
    ├── App.java            # Main entry point initiating application runtime sequences
    ├── Leaderboard.java    # Class managing file reading, appending data, and sorting high scores
    └── Minesweeper.java    # Principal GUI frame layout housing grid state engines & event handling

---

  ## 📜 Credits & Acknowledgments
* Base game engine, grid system, and mine-laying logic adapted from the tutorial by [Kenny Yip Coding](https://www.youtube.com/@KennyYipCoding) on YouTube.
* Custom leaderboard integration, file IO handlers, live score tracking, and visual polish expansions added by BurntShuji.


## 📂 Project Architecture

```text
```text
java-minesweeper/
│
├── minesweepLeadB.txt      # Local storage file tracking player names and scored totals
├── README.md               # Project documentation and guide guidelines
│
└── src/
    ├── App.java            # Main entry point initiating application runtime sequences
    ├── Leaderboard.java    # Class managing file reading, appending data, and sorting high scores
    └── Minesweeper.java    # Principal GUI frame layout housing grid state engines & event handling
