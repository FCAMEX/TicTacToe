package gamepack;

import static org.junit.Assert.*;

import org.junit.Test;

public class GamePanelTest {

	int size = 3;
	boolean compX = false;
	boolean compY = false;
	
	@Test
	public void GamePanelConstructorTest() {
		
		
		EndGlassPane glass = new EndGlassPane(size);
		GamePanel tester = new GamePanel(size, glass, compX, compY);
		tester.game.board[0][0].doClick();
	
		assertNotEquals("After click, the tile should hold a different value than the rest",tester.game.board[0][0].getTileValue(), tester.game.board[0][1].getTileValue());
		
		assertEquals("When created, all tiles should hold the same value (0)",tester.game.board[0][1].getTileValue(), tester.game.board[0][2].getTileValue());
	}
	
	@Test
	public void GamePanelAutoXTest1(){
		
		EndGlassPane glass = new EndGlassPane(size);
		GamePanel tester = new GamePanel(size, glass, true, false);
		
		assertEquals("When X is chosen as the computer player, its first move should be the center tile",1, tester.game.board[1][1].getTileValue());
		
	}
	
	@Test
	public void GamePanelAutoYTest1(){
		
		EndGlassPane glass = new EndGlassPane(size);
		GamePanel tester = new GamePanel(size, glass, false, true);
		tester.game.board[0][0].doClick();
		assertEquals("When 	Y is chosen as the computer player, its first move should be the center tile if possible",2, tester.game.board[1][1].getTileValue());
		tester = new GamePanel(size, glass, false, true);
		tester.game.board[1][1].doClick();
		boolean found = false;
		if(tester.game.board[0][0].getTileValue() == 2 || tester.game.board[0][2].getTileValue() == 2|| tester.game.board[2][0].getTileValue() == 2|| tester.game.board[2][2].getTileValue() == 2)
		found = true;
		assertEquals("When 	Y is chosen as the computer player, its first move should be the a corner tile if the center tile is taken",true, found);

	}
	
	@Test
	public void GamePanelFullAuto(){
		
		EndGlassPane glass = new EndGlassPane(size);
		GamePanel tester = new GamePanel(size, glass, true, true);
		
		assertEquals("When Computer vs Computer mode has been chosen, the game is not be won by anyone", false , tester.game.winCheck());
		assertEquals("When Computer vs Computer mode has been chosen, the game should end in a tie", true, glass.getTie());
	}
	

}
