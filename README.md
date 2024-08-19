## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).


### Documentation for Tic Tac Toe Project

## Project Overview
    This project is a graphical Tic Tac Toe game implemented in Java using Swing. The application allows two players to play Tic Tac Toe on a 3x3 grid. It tracks the scores of both players, displays the highest score, and provides various dialogs to interact with the user, such as game-over and draw notifications.

## Project Structure
1. Views Package
    - TicTacToeForm.java: The main game window and logic for the Tic Tac Toe game.
    - RoundedBorder.java: A custom border with rounded corners for components.
    - RoundedButton.java: A custom button with rounded corners and custom painting.

2. Helper Package
    - CustomDialogResult.java: A helper class to manage dialog results.

3. App.java: The entry point of the application, which initializes and displays the TicTacToeForm window.

## Class Descriptions

# TicTacToeForm
    - Purpose: Represents the main game window for Tic Tac Toe.
    - Key Components:
        - JLabel for player scores and highest score.
        - JButton array for the Tic Tac Toe grid.
        - JDialog for custom dialogs to show messages and get user input.
    
# Attributes:
    - playerXScore, playerOScore, highestScore: Scores for players and highest score.
    - gameChances: Array to track game state (0 for X, 1 for O, 2 for empty).
    - activePlayer: Indicates which player is currently active (0 for X, 1 for O).
    - wps: Winning positions for Tic Tac Toe.
    - isGameOver: Flag to indicate if the game is over.

    Methods:
        - TicTacToeForm(): Constructor initializes the game window.
        - centerFrame(): Centers the window on the screen.
        - createGameLayout(): Creates and arranges the GUI components.
        - showCustomConfirmDialog(), showCustomfirmDialog(): Display custom dialogs with messages and icons.
        - actionPerformed(ActionEvent e): Handles button clicks and game logic.
        - resetGame(): Resets the game board for a new game.
        - resetScores(): Resets player scores and highest score.

# RoundedBorder
    - Purpose: Provides a custom border with rounded corners.
    Attributes:
        - radius: Radius of the border's rounded corners.
        - borderColor: Color of the border.
    
    Methods:
        - paintBorder(): Draws the rounded border.
        - getBorderInsets(): Returns the insets of the border.
    
# RoundedButton
    - Purpose: A custom button with rounded corners and custom painting.
    - Attributes:
        - backgroundColor: Background color of the button.
        - borderColor: Border color of the button.
        - borderRadius: Radius of the button's rounded corners.
    
    Methods:
        - paintComponent(): Paints the button background, border, and text.
        - getInsets(), getPreferredSize(): Provides insets and preferred size for the button.
        - setBorderColor(Color color): Updates the border color.


# CustomDialogResult
    - Purpose: Stores the result of custom dialog interactions.
    - Methods:
        - setResult(int result): Sets the result of the dialog.
        - getResult(): Retrieves the result of the dialog.

# User Interface
    - Main Window:
        - Title: "Tic Tac Toe"
        - Size: 800x600 pixels.
        - Layout: Uses BorderLayout with a central grid and side panel.

    - Components:
        - Heading: Displays "Tic Tac Toe" at the top.
        - Score Panel: Displays scores for Player X and Player O, and the highest score.
        - Game Grid: A 3x3 grid of buttons where players make their moves.
        - Clock: Displays the current date and time at the bottom.

    - Game Logic
        - Player Moves: Players click on buttons to make their moves. The button changes its icon to either X or O.
        - Win Checking: After each move, the game checks if there is a winning combination.
        - Game Over: If a player wins or if the game ends in a draw, a custom dialog is shown.
        - Score Updating: Scores are updated based on the outcome of the game, and the highest score is tracked.
        - Replay Option: Players are prompted to play again or exit after a game ends.
        
# Custom Dialogs
    - Confirmation Dialog: Asks the player if they want to play again or exit after a game ends.
    - Information Dialog: Displays messages such as "Game Over," "Draw," or "Position Already Occupied."
    
# Development Notes
    - Ensure that image paths are correct and that the necessary images are available in the specified directories.
    - Customize the border radius, button sizes, and other UI elements to fit the desired aesthetics.
    - Handle any exceptions or errors that may occur during game execution.
