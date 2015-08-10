
Tic Tac Toe - Instructions

Author: Fernando Araujo
Date: 7/14/2015

To play the game run the Tic Tac Toe Jar or compile & run the code

********************************************************************************************************************************************************
Running a Jar file

In order to run a Jar file the Java JRE or JDK needs to be installed in the computer.

The latest version of JRE here:  http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html


After installation of JRE/JDK

- Download the Tic Tac Toe game code

- Find the TicTacToe.jre file 

- Double click on the file (The program should automatically start*)


*If the program does not start

    - Click on the Run TicTacToe.bat file
    - The program should automatically start


********************************************************************************************************************************************************
Compiling the Tic Tac Toe Java project

- Open the desired Java IDE installed in your computer

- Import the TicTacToe Project

- Compile and Run the Main class


********************************************************************************************************************************************************
Game Instructions

Main Menu

- Select Player vs Player   ------ to play against another human

- Select Player vs Computer ------ a new menu will appear prompting the user to choose who goes first, in other words who plays as X (computer or player)*

- Select Computer Vs Computer ----- to watch the computer play against another computer**

- Select Options ----- to open the Options menu and change the Game Board size to any size between 2 and 4***

Game Menu

- Back button ----- click on this button if you want to go back to the Main menu and select a different game mode

- Restart button ----- click on this button if you want to reset the Game board and play another match in the same game mode

- Close button ----- click on this button if you want to close the game (alternatively you can click on the x button at the corner of the window)



IMPORTANT NOTES:

*In this version of Tic Tac Toe the X player always goes first

**In the Computer vs Computer mode the algorithm runs until they reach a tie. It uses the same algorithm that is utilized in Person vs Computer mode
(the program lumps all the requested redraw() calls together and therefore only draws the outcome of the match)  
if this is a problem I can try to solve by splitting the program into multiple threads 

*** The board is currently optimized for size 3. If the user picks any size other than 3, the player vs player option will still work properly,
but the Computer player options will not.


***********************************************************************************************************************************************************