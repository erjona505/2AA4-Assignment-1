# Catan Simulator - Quick Start Guide

## Requirements

- Java Development Kit (JDK) 8 or higher
- Terminal/Command Prompt

## Quick Start (3 Steps)

### 1. Compile the Code

```bash
cd catan-simulator
chmod +x compile.sh
./compile.sh
```

### 2. Configure the Game (Optional)

Edit `config.txt` to set the number of rounds:

```
turns: 50
```

Valid range: 1-8192

### 3. Run the Simulator

```bash
./run.sh
```

## What You'll See

```
╔════════════════════════════════════════════════════╗
║   SETTLERS OF CATAN - SIMULATOR DEMONSTRATION     ║
║   McMaster University - SFWRENG 2AA4              ║
║   Assignment 1 - 2026W                            ║
╚════════════════════════════════════════════════════╝

=== Catan Simulator Starting ===
Max rounds: 50

=== Setup Phase ===
Player 0: placed initial settlement at node 0
Player 0: placed initial road at edge 0
Player 1: placed initial settlement at node 10
...

--- Round 1 ---
[1] / Player 0: rolled 8
[1] / Player 0: received resources, now has 7 cards
[1] / Player 1: rolled 6
[1] / Player 1: received resources, now has 5 cards
...
Victory Points: P0=1, P1=1, P2=1, P3=1

--- Round 2 ---
...
```

## Understanding the Output

### Turn Format
```
[RoundNumber] / Player [ID]: [Action]
```

Example:
```
[5] / Player 2: rolled 9
[5] / Player 2: received resources, now has 6 cards
[5] / Player 2: built settlement at node 34
```

### Actions You'll See

- **rolled X**: Player rolled the dice (2-12)
- **received resources**: Player gained resources from tiles
- **built road at edge X**: Player built a road
- **built settlement at node X**: Player built a settlement
- **upgraded to city at node X**: Player upgraded settlement to city
- **has X cards (>7)**: Player must build due to excess cards
- **unable to spend cards**: Player couldn't build despite excess cards

### Victory Points

At the end of each round:
```
Victory Points: P0=3, P1=2, P2=4, P3=1
```

### Game End

```
=== Game Over ===
Final Scores:
Player 0: 8 VP
Player 1: 6 VP
Player 2: 10 VP  ← Winner!
Player 3: 5 VP

Winner: Player 2 with 10 victory points!
```

## Modifying the Configuration

### Change Number of Rounds

Edit `config.txt`:
```
turns: 100  # Play 100 rounds instead of 50
```

### Valid Values

- Minimum: 1 round
- Maximum: 8192 rounds
- Default: 50 rounds

## Game Mechanics

### Victory Points

- Settlement: 1 VP
- City: 2 VP
- Game ends when any player reaches 10 VP

### Building Costs

| Building   | Wood | Brick | Wheat | Sheep | Ore |
|------------|------|-------|-------|-------|-----|
| Road       | 1    | 1     | -     | -     | -   |
| Settlement | 1    | 1     | 1     | 1     | -   |
| City       | -    | -     | 2     | -     | 3   |

### Rules Enforced

1. ✅ Settlements must be ≥2 nodes apart
2. ✅ Roads must connect to existing roads/settlements
3. ✅ Cities must upgrade existing settlements
4. ✅ One building per node
5. ✅ One road per edge
6. ✅ Must have resources to build
7. ✅ Players with >7 cards must attempt to build

## Troubleshooting

### "javac: command not found"
Install JDK:
```bash
# Ubuntu/Debian
sudo apt-get install default-jdk

# macOS
brew install openjdk
```

### "Permission denied" when running scripts
Make scripts executable:
```bash
chmod +x compile.sh run.sh
```

### Game ends too quickly
Increase rounds in `config.txt`:
```
turns: 100
```

### Game runs forever
Decrease rounds or wait for a player to reach 10 VP:
```
turns: 20
```

## Project Structure

```
catan-simulator/
├── src/main/java/catan/     # Source code
│   ├── Demonstrator.java    # Main entry point ⭐
│   ├── Game.java            # Game controller
│   ├── Map.java             # Board
│   ├── Agent.java           # Player
│   └── ...                  # Other classes
├── config.txt               # Configuration
├── compile.sh               # Compile script
├── run.sh                   # Run script
└── README.md                # Full documentation
```

## Next Steps

1. Read `README.md` for detailed documentation
2. Read `ASSIGNMENT_REPORT.md` for design decisions
3. Examine source code in `src/main/java/catan/`
4. Modify `config.txt` and experiment
5. Try different random seeds by rerunning

## Support

For issues or questions:
1. Check README.md for detailed info
2. Review ASSIGNMENT_REPORT.md for design rationale
3. Examine source code comments
4. Refer to assignment PDF for requirements

---

**Enjoy exploring the Catan Simulator!** 🎲🏠🛤️
