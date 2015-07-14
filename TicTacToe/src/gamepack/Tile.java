package gamepack;

import java.awt.*;
import javax.swing.*;

/**
 * Serves as the class that defines the X and O game tiles
 * 
 * @version 7/14/2015
 * @author Fernando Araujo
 * 
 */
public class Tile extends JButton {
	/**
	 * variable that holds a value indicating if the tile is an X (1) a O(2) or
	 * empty (0)
	 */
	private int value = 0;
	/**
	 * variable that holds the image to draw if the tile is in O position
	 */
	private Image imgO;
	/**
	 * variable that holds the image to draw if the tile is in X position
	 */
	private Image imgX;

	/**
	 * constructor that creates a new tile and defines basic properties
	 * 
	 * @param O
	 *            image to draw for the O position
	 * @param X
	 *            image to draw for the X position
	 */
	public Tile(Image O, Image X) {

		imgO = O;
		imgX = X;
		setPreferredSize(new Dimension(100, 100));

	}

	/**
	 * get method that returns the value of value (if the tile holds an X, O or
	 * is empty)
	 * 
	 * @return value
	 */
	public int getValue() {

		return value;
	}

	/**
	 * set method that sets the value of this tile to X (1) and repaints
	 */
	public void setX() {

		setIcon(new ImageIcon(imgX));
		value = 1;
		paintImmediately(this.getBounds());
		try {
			Thread.sleep(50);
		} catch (InterruptedException ee) {
		}
	}

	/**
	 * set method that sets the value of this tile to O (2) and repaints
	 */
	public void setO() {

		setIcon(new ImageIcon(imgO));
		value = 2;
		paintImmediately(this.getBounds());
		try {
			Thread.sleep(50);
		} catch (InterruptedException ee) {
		}
	}

	/**
	 * get method that returns if the tile is empty
	 * 
	 * @return boolean indicating if the value equals 0
	 */
	public boolean isEmpty() {

		return value == 0;
	}

}
