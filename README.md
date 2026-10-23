# IKI-GAME
⚔ IKI GAME

About the Project
Legends of Valoria is a simple Java game character application using Java Swing. Users can select heroes, perform attacks, use special abilities, and create their own characters.

Features

Warrior and Mage character types

Unique attacks and special abilities

Graphical user interface with a fantasy theme

Character selection dropdown

Battle log to display character actions

Create new characters using user input

Input validation for empty, duplicate, and overly long names

Object-Oriented Programming Concepts

Abstraction

The abstract GameCharacter class defines the common properties and behaviors of every character. Its subclasses implement their own attacks and special moves.

Abstract Data Type (ADT)

An ArrayList<GameCharacter> stores and manages the game characters. It supports adding characters and retrieving a character by its selected index.

GUI

Java Swing provides the application window, buttons, dropdown menu, text area, and input dialog.

User Input

Users can enter a character name and select a character type. The program validates the name before adding a new character.

Character Types

Warrior: Uses a mighty sword and Shield Bash.

Mage: Casts a fireball and uses Lightning Storm.

Requirements

Java Development Kit (JDK)

A Java IDE or terminal

How to Run

Clone or download the repository.

Open the project in your Java IDE.

Place Main.java, GameCharacter.java, Warrior.java, and Mage.java in the same source folder.

Run Main.java.
