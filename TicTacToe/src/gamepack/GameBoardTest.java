package gamepack;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameBoardTest {
	
	int size = 3;
	boolean compX = false;
	boolean compY = false;
		
	@Test
	public void testGameBoardConstructor() {
		
		
		EndGlassPane glass = new EndGlassPane(size);
		GameBoard tester = new GameBoard(size, glass, compX, compY );
		
		assertEquals("Tiles start with their value set as 0", 0, tester.board[0][1].getTileValue());
		assertEquals("All the tiles must hold the same value (0)", tester.board[0][1].getTileValue(), tester.board[0][2].getTileValue());
	}

	@Test
	public void testNoVictory() {
		
	
		EndGlassPane glass = new EndGlassPane(size);
		GameBoard tester = new GameBoard(size, glass, compX, compY );
		
		assertEquals("noVicotry() method should return true for a newly created GameBoard ", true ,tester.noVictory());
		
	}

	@Test
	public void testGetSize() {
		
	
		EndGlassPane glass = new EndGlassPane(size);
		GameBoard tester = new GameBoard(size, glass, compX, compY );
		
		assertEquals("getSice() should return the current boardSize value", 3, tester.getSize());
	}

	@Test
	public void testWinCheck() {
	
		EndGlassPane glass = new EndGlassPane(size);
		GameBoard tester = new GameBoard(size, glass, compX, compY );
		
		assertEquals("winCheck() should return false for a newly created GameBoard", 3, tester.getSize());

	}

	@Test
	public void testBoardCheckHorizontal() {
		

		EndGlassPane glass = new EndGlassPane(size);
		GameBoard tester = new GameBoard(size, glass, compX, compY );
		for(int i = 0; i< size; i++){
		tester.board[0][i].setX();
		}
		tester.boardCheck();
		
		assertEquals("The boardCheck() should return true since the board is set up as a horizontal victory", true, tester.winCheck());
		
	}

	@Test
	public void testBoardCheckVertical() {

		
		EndGlassPane glass = new EndGlassPane(size);
		GameBoard tester = new GameBoard(size, glass, compX, compY );
		for(int i = 0; i< size; i++){
		tester.board[i][0].setX();
		}
		tester.boardCheck();
		
		assertEquals("The winCheck() should return true since the board is set up as a vertical victory", true, tester.winCheck());
		
	}
	
	
	@Test
	public void testBoardCheckDiagonal() {

		
		EndGlassPane glass = new EndGlassPane(size);
		GameBoard tester = new GameBoard(size, glass, compX, compY );
		for(int i = 0; i< size; i++){
		tester.board[i][i].setX();
		}
		tester.boardCheck();
		
		assertEquals("The winCheck() should return true since the board is set up as a diagonal victory", true, tester.winCheck());
		
	}
	
	
	
}
