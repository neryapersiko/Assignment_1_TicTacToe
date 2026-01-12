# Tic Tac Toe - Android App

A simple, clean, and functional Tic Tac Toe game developed for Android. This app allows two players to compete on the same device.

## Features
- **Two-Player Local Gameplay**: Play with a friend on a single device.
- **Dynamic UI**:
    - **Player X**: Distinctive Pink/Red color.
    - **Player O**: Distinctive Blue color.
- **Game Logic**:
    - Automatic detection of winning combinations (horizontal, vertical, and diagonal).
    - Detection of draw scenarios when the board is full.
- **Interactive Feedback**: 
    - Real-time turn status updates.
    - Large, colorful winner announcements.
- **Play Again**: A "Play Again" button appears at the end of each game to reset the board instantly.

## UI Design
- **Grid Layout**: A classic 3x3 grid with thick black separators for a modern look.
- **Responsive Elements**: Large, easy-to-tap buttons with clear symbols that fill the cells.
- **Color Coded**: Visual cues help players easily distinguish their moves.

## Technical Details
- **Language**: Kotlin
- **UI Framework**: Android XML (GridLayout, LinearLayout)
- **Best Practices**:
    - Usage of `strings.xml` for all app text.
    - Usage of `colors.xml` for consistent styling.
    - Clean separation of logic and presentation.

## How to Run
1. Clone the repository.
2. Open the project in **Android Studio**.
3. Build and run on an emulator or a physical Android device.

---
*Developed as part of an Android Development assignment.*
