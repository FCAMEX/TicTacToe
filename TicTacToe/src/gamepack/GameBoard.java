package gamepack;

import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Defines and constructs the GameBoard, an array of the tiles and holds methods
 * used for Victory Conditions.
 * 
 * @version 7/14/2015
 * @author Fernando Araujo
 * 
 */
public class GameBoard {
	/**
	 * variable that holds the size of the board 3*3 (the board is dynamically
	 * generated so this can be changed)
	 */
	private int boardSize = 3;
	/**
	 * 2 dimensional array of tiles that holds all the tiles of the game board
	 */
	Tile[][] board = new Tile[10][10];
	/**
	 * boolean variable that indicates if the X player has won
	 */
	private boolean xVictory = false;
	/**
	 * boolean variable that indicates if the O player has won
	 */
	private boolean oVictory = false;
	/**
	 * boolean variable that stores if the computer is set as the X player
	 */
	private boolean compX = false;
	/**
	 * boolean variable that stores if the computer is set as the O player
	 */
	private boolean compO = false;
	/**
	 * variable that stores the current instance of the glass pane
	 */
	private EndGlassPane glass = new EndGlassPane();

	/**
	 * no argument constructor that calls the parent's constructor
	 */
	public GameBoard() {
		super();

	}

	/**
	 * constructor that creates a new GameBoard
	 * 
	 * @param boardSize
	 *            integer that defines the size of the desired GameBoard
	 * @param glass
	 *            instance of the glass pane currently in use
	 */
	public GameBoard(int boardSize, EndGlassPane glass, boolean compX,
			boolean compO) {
		super();
		this.boardSize = boardSize;
		this.glass = glass;
		this.compX = compX;
		this.compO = compO;
		boardSetup();

	}

	/**
	 * get method that returns xVixctory
	 * 
	 * @return boolean that indicates if x has won
	 */
	public boolean getxVictory() {

		return xVictory;
	}

	/**
	 * get method that returns oVixctory
	 * 
	 * @return boolean that indicates if o has won
	 */
	public boolean getoVictory() {

		return oVictory;
	}

	/**
	 * get method that returns the stored boardSize
	 * 
	 * @param boardSize
	 *            integer that defines the size of the desired GameBoard
	 */
	public int getSize() {

		return boardSize;
	}

	/**
	 * method that checks if either the X or Y player have attained victory
	 * 
	 * @return boolean that indicates if either X or Y have won
	 */
	public boolean winCheck() {

		return xVictory || oVictory;

	}

	/**
	 * method that dynamically sets up the board by creating al the game Tiles
	 * needed depending on the size
	 */
	private void boardSetup() {

		try {

			Image imgO = ImageIO
					.read(Main.class.getResource("resources/O.jpg"));
			Image imgX = ImageIO
					.read(Main.class.getResource("resources/X.jpg"));

			for (int i = 0; i < boardSize; i++) {
				for (int y = 0; y < boardSize; y++) {
					Tile a = new Tile(imgO, imgX);

					board[i][y] = a;
				}
			}
		} catch (IOException ex) {
		}

	}

	/**
	 * method that checks if either X or O have won by calling checkDiag,
	 * checkRow and checkCol this method also updates the victory indicators
	 * xVictory and oVictory
	 */

	public void boardCheck() {

		checkDiag();
		if (xVictory || oVictory)
			return;

		for (int i = 0; i < boardSize; i++) {
			checkRow(i);
			if (xVictory || oVictory)
				return;
			checkCol(i);
			if (xVictory || oVictory)
				return;
		}

	}

	/**
	 * helper method that checks if either X or O have won by in any diagonal
	 * location this method also updates the victory indicators xVictory and
	 * oVictory and sets up victory conditions on GlassPane
	 */
	private void checkDiag() {

		int xValue = 1;
		int oValue = 2;

		xVictory = true;
		oVictory = true;

		for (int i = 0; i < boardSize; i++) {

			if (board[i][i].getValue() != xValue)
				xVictory = false;
			if (board[i][i].getValue() != oValue)
				oVictory = false;

		}
		if (xVictory) {
			if (compX || compO)
				glass.setDWin(0, "Computer X Wins!");
			else
				glass.setDWin(0, "1st Player X Wins!");
			return;

		}

		if (oVictory) {
			if (compX || compO)
				glass.setDWin(0, "Computer O Wins!");
			else
				glass.setDWin(0, "2nd Player O Wins!");
			return;
		}

		xVictory = true;
		oVictory = true;
		int y = boardSize - 1;

		for (int i = 0; i < boardSize; i++) {

			if (board[i][y].getValue() != xValue)
				xVictory = false;
			if (board[i][y].getValue() != oValue)
				oVictory = false;
			y--;
		}

		if (xVictory) {

			if (compX || compO)
				glass.setDWin(1, "Computer X Wins!");
			else
				glass.setDWin(1, "1st Player X Wins!");

		}

		if (oVictory) {

			if (compX || compO)
				glass.setDWin(1, "Computer O Wins!");
			else
				glass.setDWin(1, "2nd Player O Wins!");

		}

	}

	/**
	 * helper method that checks if either X or O have won by in any row this
	 * method also updates the victory indicators xVictory and oVictory and sets
	 * up victory conditions on GlassPane
	 */
	private void checkRow(int row) {

		int xValue = 1;
		int oValue = 2;

		xVictory = true;
		oVictory = true;

		for (int i = 0; i < boardSize; i++) {

			if (board[row][i].getValue() != xValue)
				xVictory = false;
			if (board[row][i].getValue() != oValue)
				oVictory = false;
		}

		if (xVictory) {
			if (compX || compO)
				glass.setHWin(row, "Computer X Wins!");
			else
				glass.setHWin(row, "1st Player X Wins!");

		}

		if (oVictory) {
			if (compX || compO)
				glass.setHWin(row, "Computer O Wins!");
			else
				glass.setHWin(row, "2nd Player O Wins!");
		}
	}

	/**
	 * helper method that checks if either X or O have won by in any column this
	 * method also updates the victory indicators xVictory and oVictory and sets
	 * up victory conditions on GlassPane
	 */
	private void checkCol(int col) {

		int xValue = 1;
		int oValue = 2;

		xVictory = true;
		oVictory = true;

		for (int i = 0; i < boardSize; i++) {

			if (board[i][col].getValue() != xValue)
				xVictory = false;
			if (board[i][col].getValue() != oValue)
				oVictory = false;
		}

		if (xVictory) {
			if (compX || compO)
				glass.setVWin(col, "Computer X Wins!");
			else
				glass.setVWin(col, "1st Player X Wins!");

		}
		if (oVictory) {
			if (compX || compO)
				glass.setVWin(col, "Computer O Wins!");
			else
				glass.setVWin(col, "2nd Player O Wins!");
		}

	}

}
