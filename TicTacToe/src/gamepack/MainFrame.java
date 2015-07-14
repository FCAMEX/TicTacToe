package gamepack;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;

/**
 * Serves as the main Frame where all the GUI elements, GlassPane and the Game
 * Panel are placed into. It handles all the main menus and their action events.
 * 
 * @version 7/14/2015
 * @author Fernando Araujo
 * 
 */
public class MainFrame extends JFrame {
	/**
	 * variable that holds the size of the board 3*3 (the board is dynamically
	 * generated so this can be changed)
	 */
	private int boardSize = 3;
	/**
	 * stores an instance of GlassPane utilized by this frame
	 */
	private EndGlassPane topPane = new EndGlassPane();
	/**
	 * boolean variable that stores if the computer is set as the X player
	 */
	private boolean compX = false;
	/**
	 * boolean variable that stores if the computer is set as the O player
	 */
	private boolean compO = false;
	/**
	 * stores the current instance of GamePanel useful to reset the game if
	 * needed
	 */
	private GamePanel pan = new GamePanel(3, topPane, compX, compO);
	/**
	 * panel used to store the components of the game menu stores buttons for
	 * back, reset and close game
	 */
	private JPanel menu = new JPanel();
	/**
	 * panel used to store the components of the first menu
	 * 
	 */
	private JPanel firstMenu = new JPanel();
	/**
	 * panel used to store the components of the first game mode menu stores
	 * buttons for player vs player, player vs computer and computer vs computer
	 */
	private JPanel gameMode = new JPanel();
	/**
	 * panel used to store the components of the turn select menu stores buttons
	 * for computer or player going first
	 */
	private JPanel turnSelect = new JPanel();

	/**
	 * no-argument constructor, calls for the setup of first Menu and defines
	 * basic properties of Frame
	 */
	public MainFrame() {
		super("TicTacToe");

		setupFirstMenu();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(boardSize * 120, boardSize * 200);
		setVisible(true);
		setResizable(false);

	}

	/**
	 * method that sets up the first menu that includes the cover art and the
	 * mode selection menu
	 */
	private void setupFirstMenu() {

		firstMenu = new JPanel();
		firstMenu.setLayout(new BoxLayout(firstMenu, BoxLayout.Y_AXIS));
		JPanel firstLog = new JPanel();
		gameMode = new JPanel();

		try {
			Image logo = ImageIO.read(Main.class
					.getResource("resources/TicTacLogo.png"));
			JLabel picLab = new JLabel(new ImageIcon(logo));
			picLab.setPreferredSize(new Dimension(360, 360));
			picLab.setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 15));

			firstLog.add(picLab);

		} catch (IOException ex) {
		}

		gameMode.setLayout(new GridLayout(0, 1));
		JButton pvp = new JButton("Player vs Player");
		JButton pvc = new JButton("Player vs Computer");
		JButton cvc = new JButton("Computer vs Computer");

		pvp.setPreferredSize(new Dimension(50, 50));
		pvc.setPreferredSize(new Dimension(50, 50));
		cvc.setPreferredSize(new Dimension(50, 50));
		pvp.setFocusPainted(false);
		pvc.setFocusPainted(false);
		cvc.setFocusPainted(false);
		pvp.setFont(new Font("Arial", Font.BOLD, 30));
		pvc.setFont(new Font("Arial", Font.BOLD, 30));
		cvc.setFont(new Font("Arial", Font.BOLD, 30));

		pvp.addActionListener(new MainActions());
		pvc.addActionListener(new MainActions());
		cvc.addActionListener(new MainActions());

		firstMenu.setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 15));
		gameMode.add(pvp);
		gameMode.add(pvc);
		gameMode.add(cvc);
		firstMenu.add(firstLog);
		firstMenu.add(gameMode);

		add(firstMenu);

	}

	/**
	 * method that sets up the turn select menu, its properties and buttons
	 */
	private void turnSelect() {

		turnSelect = new JPanel();
		JButton player = new JButton("Player 1");
		JButton comp = new JButton("Computer");
		player.setFocusPainted(false);
		comp.setFocusPainted(false);
		turnSelect.setBorder(BorderFactory.createEmptyBorder(10, 15, 40, 15));
		JLabel selectStart = new JLabel(" Select Starting Player",
				SwingConstants.CENTER);
		selectStart.setPreferredSize(new Dimension(300, 50));
		selectStart.setFont(new Font("Arial", Font.BOLD, 20));
		player.setPreferredSize(new Dimension(110, 40));
		comp.setPreferredSize(new Dimension(110, 40));
		turnSelect.add(selectStart);
		turnSelect.add(player);
		turnSelect.add(comp);
		player.addActionListener(new MainActions());
		comp.addActionListener(new MainActions());
	}

	/**
	 * method that initializes the game, and sets up all the game menu buttons
	 */
	private void setupGame() {

		menu = new JPanel();
		topPane = new EndGlassPane();
		JPanel topMenu = new JPanel();
		JButton closeB = new JButton("Close Game");
		JButton restartB = new JButton("Restart");
		JButton back = new JButton("Back");

		this.setGlassPane(topPane);
		topPane.setOpaque(false);
		topPane.setVisible(true);

		pan = new GamePanel(boardSize, topPane, compX, compO);

		closeB.setPreferredSize(new Dimension(100, 50));

		closeB.setMargin(new Insets(1, 1, 1, 1));
		restartB.setPreferredSize(new Dimension(100, 50));

		restartB.setMargin(new Insets(1, 1, 1, 1));
		back.setPreferredSize(new Dimension(100, 50));
		back.setMargin(new Insets(1, 1, 1, 1));
		closeB.setFocusPainted(false);
		restartB.setFocusPainted(false);
		back.setFocusPainted(false);

		topMenu.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		topMenu.add(back);
		topMenu.add(restartB);
		topMenu.add(closeB);

		menu.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

		BoxLayout menuBox = new BoxLayout(menu, BoxLayout.Y_AXIS);
		menu.setLayout(menuBox);
		menu.add(topMenu);
		menu.add(pan);
		add(menu);
		back.addActionListener(new MainActions());
		closeB.addActionListener(new MainActions());
		restartB.addActionListener(new MainActions());

	}

	/**
	 * Action listener that takes care of all the button action events of the
	 * GUI it hold all the logic related to changing menus and calls to
	 * start/restart/close the game
	 */
	public class MainActions implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			Object source = e.getSource();
			if (source instanceof JButton) {

				if (((JButton) source).getText().equals("Close Game"))
					dispose();

				if (((JButton) source).getText().equals("Restart")) {
					menu.remove(pan);
					topPane = new EndGlassPane();
					setGlassPane(topPane);
					topPane.setOpaque(false);
					topPane.setVisible(true);
					pan = new GamePanel(boardSize, topPane, compX, compO);
					menu.add(pan);
					menu.revalidate();
					menu.repaint();

				}

				if (((JButton) source).getText().equals("Player vs Player")) {
					remove(firstMenu);
					compX = false;
					compO = false;
					setupGame();
					revalidate();
					repaint();

				}

				if (((JButton) source).getText().equals("Player vs Computer")) {

					firstMenu.remove(gameMode);
					turnSelect();
					firstMenu.add(turnSelect);
					firstMenu.revalidate();
					firstMenu.repaint();

				}

				if (((JButton) source).getText().equals("Computer vs Computer")) {
					remove(firstMenu);
					compX = true;
					compO = true;
					setupGame();
					revalidate();
					repaint();

				}
				if (((JButton) source).getText().equals("Back")) {
					remove(menu);
					setupFirstMenu();
					topPane.setVisible(false);
					revalidate();
					repaint();

				}

				if (((JButton) source).getText().equals("Player 1")) {

					remove(firstMenu);
					compX = false;
					compO = true;
					setupGame();
					revalidate();
					repaint();

				}
				if (((JButton) source).getText().equals("Computer")) {

					remove(firstMenu);
					compX = true;
					compO = false;
					setupGame();
					revalidate();
					repaint();

				}

			}
		}

	}
}