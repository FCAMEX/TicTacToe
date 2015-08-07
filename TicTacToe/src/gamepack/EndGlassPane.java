package gamepack;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * Creates a Glass pane that serves as an indicator for Victory/Ties. Draws a
 * red line indicating victory and announces wins or ties. It is set as the
 * glass panel of the MainFrame.
 * 
 * @version 7/14/2015
 * @author Fernando Araujo
 * 
 */
class EndGlassPane extends JPanel {

	/**
	 * created to fulfill the requirements of this class and separate this class version from others
	 */
	private static final long serialVersionUID = 2658174473255803716L;
	/**
	 * indicates if the game is tied
	 */
	private boolean tie = false;
	/**
	 * indicates if the game has been won
	 */
	private boolean win = false;
	/**
	 * stores the text announcing the winner (X or O players)
	 */
	private String winLabel = "";
	/**
	 * stores the x starting coordinate of the winning line indicator
	 */
	private int x1 = 0;
	/**
	 * stores the y starting coordinate of the winning line indicator
	 */
	private int y1 = 0;
	/**
	 * stores the x ending coordinate of the winning line indicator
	 */
	private int x2 = 0;
	/**
	 * stores the y ending coordinate of the winning line indicator
	 */
	private int y2 = 0;

	/**
	 * no-argument class constructor, sets the boolean indicators tie and win to
	 * false
	 */
	public EndGlassPane() {
		super();
		tie = false;
		win = false;

	}

	/**
	 * set method that sets up and draws the winner label and line indicator if
	 * the victory is in a vertical position (column)
	 * 
	 * @param pos
	 *            the position (column) of the vertical win
	 * @param winner
	 *            the winner string indicating the winning player
	 */
	public void setVWin(int pos, String winner) {

		win = true;

		x1 = 85 + (pos * 115);
		y1 = 190;
		x2 = 85 + (pos * 115);
		y2 = 490;
		winLabel = winner;
		repaint();

	}

	/**
	 * set method that sets up and draws the winner label and line indicator if
	 * the victory is in a horizontal position (row)
	 * 
	 * @param pos
	 *            the position (row) of the horizontal win
	 * @param winner
	 *            the winner string indicating the winning player
	 */
	public void setHorizontalWin(int pos, String winner) {

		win = true;

		x1 = 60;
		y1 = 210 + (pos * 125);
		x2 = 350;
		y2 = 210 + (pos * 125);
		winLabel = winner;
		repaint();

	}

	/**
	 * set method that sets up and draws the winner label and line indicator if
	 * the victory is in a diagonal position
	 * 
	 * @param pos
	 *            the position of the diagonal win
	 * @param winner
	 *            the winner string indicating the winning player
	 */
	public void setDiagonalWin(int pos, String winner) {

		win = true;

		x1 = 60 + (pos * 290);
		y1 = 190;
		x2 = 350 - (pos * 290);
		y2 = 490;
		winLabel = winner;
		repaint();

	}

	/**
	 * set method that sets up if the game has been tied or not
	 *
	 */
	public void setTie() {

		this.tie = true;
		repaint();
	}

	/**
	 * method that sets up all the win or tie indicators drawn in this glass
	 * panel, it is called by repaint()
	 */
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.WHITE);
		g2.fillRect(30, 80, 350, 50);

		if (win) {

			g2.setColor(Color.BLACK);
			g2.setFont(new Font("Arial", Font.BOLD, 30));
			g2.drawString(winLabel, 75, 120);
			g2.setColor(Color.red);
			g2.setStroke(new BasicStroke(15));
			g.drawLine(x1, y1, x2, y2);
		}

		if (tie) {

			g2.setColor(Color.BLACK);
			g2.setFont(new Font("Arial", Font.BOLD, 30));
			/*
	  stores the text announcing if there is a tie
	 */
			String tieLabel = "It's a Tie!";
			g2.drawString(tieLabel, 140, 120);
		}
	}

}
