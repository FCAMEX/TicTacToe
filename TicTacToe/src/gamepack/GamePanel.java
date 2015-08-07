package gamepack;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Serves as the Panel that holds the GameBoard. It handles all Methods related
 * to the Computer Players and Victory/Tie Conditions.
 * 
 * @version 7/14/2015
 * @author Fernando Araujo
 * 
 */
public class GamePanel extends JPanel {

	/**
	 * created to fulfill the requirements of this class and separate this class version from others
	 */
	private static final long serialVersionUID = -5561317289049989706L;
	/**
	 * variable that holds an instance of the GameBoard currently in use
	 */
	private GameBoard game = new GameBoard();
	/**
	 * variable that holds the current turn count of the game
	 */
	private int turnCount = 0;
	/**
	 * boolean variable that stores if the computer is set as the X player
	 */
	private boolean compX = false;
	/**
	 * boolean variable that stores if the computer is set as the O player
	 */
	private boolean compO = false;
	/**
	 * variable that stores the X location of the last computer move (for X
	 * moves)
	 */
	private int botX;
	/**
	 * variable that stores the O location of the last computer move (for X
	 * moves)
	 */
	private int botO;
	/**
	 * variable that stores if the non-computer player started in a corner or
	 * not (for X moves)
	 */
	private boolean corner = false;
	/**
	 * variable that defines an appropriate delay when clicking to improve
	 * computer moves' fluidity
	 */
	private int delay = 150;
	/**
	 * variable that stores the current instance of the glass pane
	 */
	private EndGlassPane glass;
	/**
	 * variable that stores the current game board size
	 */
	private int boardSize;

	/**
	 * class constructor that creates a new game board and sets up the game
	 * panel
	 * 
	 * @param boardSize
	 *            the size of the desired board game
	 * @param glass
	 *            the current glass pane in use
	 * @param compX
	 *            boolean indicating if the computer controls X
	 * @param compO
	 *            boolean indicating if the computer controls O
	 */
	public GamePanel(int boardSize, EndGlassPane glass, boolean compX,
			boolean compO) {

		super();
		this.glass = glass;
		this.boardSize = boardSize;
		game = new GameBoard(boardSize, glass, compX, compO);
		corner = false;
		this.compX = compX;
		this.compO = compO;
		panelSetup();

	}

	/**
	 * method that sets up the game panel and adds ActionListeners to all the
	 * tiles
	 */
	private void panelSetup() {

		GridLayout lay = new GridLayout(0, boardSize);
		lay.setVgap(5);
		lay.setHgap(5);
		this.setLayout(lay);
		this.setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 15));

		for (int i = 0; i < game.getSize(); i++)
			for (int y = 0; y < game.getSize(); y++) {
				game.board[i][y].addActionListener(new Actions());
				this.add(game.board[i][y]);
			}

		if (compX) {

			game.board[1][1].doClick(delay);
		}

	}

	/**
	 * method that gets called when the computer is set to X player (1st) it
	 * handles all the automatic turn taking of the computer and always ties or
	 * wins
	 */

	private void autoTurnX() {

		if (turnCount == 2) {

			if ((!game.board[0][0].isEmpty() || !game.board[0][2].isEmpty()
					|| !game.board[2][0].isEmpty() || !game.board[2][2]
						.isEmpty())) {
				corner = true;
			}

			if ((game.board[1][0].isEmpty() && (!game.board[2][1].isEmpty()) || !game.board[0][1]
					.isEmpty())) {
				game.board[1][0].doClick(delay);
				botO = 1;
				botX = 0;

			} else {
				game.board[0][1].doClick(delay);
				botO = 0;
				botX = 1;

			}
		} else if (turnCount == 4) {

			if (!corner) {
				if (!checkWin(1))
					if (botO == 0 && botX == 1) {
						if (game.board[0][2].isEmpty())
							game.board[0][2].doClick(delay);
						else
							game.board[0][0].doClick(delay);
					} else {
						if (game.board[2][0].isEmpty())
							game.board[2][0].doClick(delay);
						else
							game.board[0][0].doClick(delay);
					}

			} else {
				if (game.board[0][0].isEmpty() && game.board[2][2].isEmpty()) {
					game.board[2][2].doClick(delay);
					botO = 2;
					botX = 2;
				} else if (game.board[0][2].isEmpty()
						&& game.board[2][0].isEmpty()) {
					game.board[2][0].doClick(delay);
					botO = 2;
					botX = 0;
				} else {
					checkWin(1);
				}
			}

		} else {
			if (!checkWin(1))
				randPlay();
		}

	}

	/**
	 * method that gets called when the computer is set to O player (2nd) it
	 * handles all the automatic turn taking of the computer and always ties or
	 * wins
	 */
	private void autoTurnO() {

		if (turnCount == 1) {

			if (game.board[1][1].isEmpty()) {
				game.board[1][1].doClick(delay);
			} else {
				int rand = (int) (Math.random() * 4);

				if (rand == 0)
					game.board[0][0].doClick(delay);
				else if (rand == 1)
					game.board[0][2].doClick(delay);
				else if (rand == 2)
					game.board[2][0].doClick(delay);
				else if (rand == 3)
					game.board[2][2].doClick(delay);
			}
		} else {

			if (!checkWin(2))
				randPlay();
		}

	}

	/**
	 * helper method that checks if there is a possibility to win or prevent
	 * enemy from winning in 1 turn.
	 * 
	 * @param myVal
	 *            value that specifies if you want to check Xs (1) or Os (2) for
	 *            victory
	 * @return boolean indicating if there is a possibility to win or prevent
	 *         enemy from winning in 1 turn
	 */
	private boolean checkWin(int myVal) {

		boolean h;

		int checkVal = 1;

		if (myVal == 1)
			checkVal = 2;

		h = checkWinHorizontal(myVal);
		if (!h)
			h = checkWinVertical(myVal);
		if (!h)
			h = checkWinDiagonal(myVal);
		if (!h)
			h = checkWinHorizontal(checkVal);
		if (!h)
			h = checkWinVertical(checkVal);
		if (!h)
			h = checkWinDiagonal(checkVal);
		return h;
	}

	/**
	 * helper method that checks if there is a possibility to win in any row in
	 * 1 turn
	 * 
	 * @param checkVal
	 *            value that specifies if you want to check Xs (1) or Os (2) for
	 *            victory
	 * @return boolean indicating if there is a possibility to win 1 turn
	 */
	private boolean checkWinHorizontal(int checkVal) {

		for (int i = 0; i < game.getSize(); i++) {

			int v1 = game.board[i][0].getTileValue();
			int v2 = game.board[i][1].getTileValue();
			int v3 = game.board[i][2].getTileValue();

			if (v1 == 0 && v2 == checkVal && v3 == checkVal) {
				game.board[i][0].doClick(delay);
				return true;
			} else if (v1 == checkVal && v2 == 0 && v3 == checkVal) {

				game.board[i][1].doClick(delay);
				return true;
			} else if (v1 == checkVal && v2 == checkVal && v3 == 0) {

				game.board[i][2].doClick(delay);
				return true;
			}
		}
		return false;
	}

	/**
	 * helper method that checks if there is a possibility to win in any column
	 * in 1 turn
	 * 
	 * @param checkVal
	 *            value that specifies if you want to check Xs (1) or Os (2) for
	 *            victory
	 * @return boolean indicating if there is a possibility to win 1 turn
	 */
	private boolean checkWinVertical(int checkVal) {

		for (int i = 0; i < game.getSize(); i++) {

			int v1 = game.board[0][i].getTileValue();
			int v2 = game.board[1][i].getTileValue();
			int v3 = game.board[2][i].getTileValue();

			if (v1 == 0 && v2 == checkVal && v3 == checkVal) {
				game.board[0][i].doClick(delay);
				return true;
			} else if (v1 == checkVal && v2 == 0 && v3 == checkVal) {

				game.board[1][i].doClick(delay);
				return true;
			} else if (v1 == checkVal && v2 == checkVal && v3 == 0) {

				game.board[2][i].doClick(delay);
				return true;
			}
		}
		return false;
	}

	/**
	 * helper method that checks if there is a possibility to win in any
	 * diagonal in 1 turn
	 * 
	 * @param checkVal
	 *            value that specifies if you want to check Xs (1) or Os (2) for
	 *            victory
	 * @return boolean indicating if there is a possibility to win 1 turn
	 */
	private boolean checkWinDiagonal(int checkVal) {

		int v1 = game.board[0][0].getTileValue();
		int v2 = game.board[1][1].getTileValue();
		int v3 = game.board[2][2].getTileValue();

		if (v1 == 0 && v2 == checkVal && v3 == checkVal) {
			game.board[0][0].doClick(delay);
			return true;
		} else if (v1 == checkVal && v2 == 0 && v3 == checkVal) {

			game.board[1][1].doClick(delay);
			return true;
		} else if (v1 == checkVal && v2 == checkVal && v3 == 0) {

			game.board[2][2].doClick(delay);
			return true;
		}

		v1 = game.board[2][0].getTileValue();
		v2 = game.board[1][1].getTileValue();
		v3 = game.board[0][2].getTileValue();

		if (v1 == 0 && v2 == checkVal && v3 == checkVal) {
			game.board[2][0].doClick(delay);
			return true;
		} else if (v1 == checkVal && v2 == 0 && v3 == checkVal) {

			game.board[1][1].doClick(delay);
			return true;
		} else if (v1 == checkVal && v2 == checkVal && v3 == 0) {

			game.board[0][2].doClick(delay);
			return true;
		}

		return false;

	}

	/**
	 * helper method that fills out any non played tile available (used in case of
	 * imminent tie)
	 */
	private void randPlay() {

		for (int i = 0; i < game.getSize(); i++)
			for (int u = 0; u < game.getSize(); u++) {
				if (game.board[i][u].isEmpty()) {
					game.board[i][u].doClick(delay);
					return;
				}
			}

	}

	/**
	 * Action listener that takes care of all the button action events of the
	 * GameBoard it hold all the logic related to handling turns and automated
	 * computer player movements
	 */
	private class Actions implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			Object source = e.getSource();
			if (source instanceof Tile) {

				if (((Tile) source).getTileValue() == 0 && game.noVictory()) {
					turnCount++;

					if (turnCount % 2 == 1) {
						((Tile) source).setX();
						if (compO) {

							autoTurnO();

						}

					} else {
						((Tile) source).setO();

						if (compX) {

							autoTurnX();
						}

					}

				}

				game.boardCheck();

				if (game.noVictory() && turnCount == boardSize * boardSize)
					glass.setTie();

			}

		}

	}

}
