import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GObject;
import acm.graphics.GPolygon;
import acm.program.GraphicsProgram;


public class GameScene {
	private GraphicsProgram program;
	private Board board;
	private CellType playerType;
	private CellType computerType;
	private IComputerPlayer computerPlayer;
	private WinSequence winSequence;
	
	public GameScene(GraphicsProgram program, Board board, CellType playerType, CellType computerType, IComputerPlayer computerPlayer) {
		this.program = program;
		this.board = board;
		this.playerType = playerType;
		this.computerType = computerType;
		this.computerPlayer = computerPlayer;
		if(computerType == CellType.CROSS){
			computerPlayer.makeTurn();
		}
	}
	
	public boolean isEnded(){
		return winSequence != null;
	}
	
	public boolean isPlayerWon(){
		return isEnded() && winSequence.getWinType() == playerType;
	}
	
	public boolean isComputerWon(){
		return isEnded() && winSequence.getWinType() == computerType;
	}
	
	public void handleClick(MouseEvent event){
		if(isEnded()){
			return;
		}
		
		GObject object = program.getElementAt(event.getX(), event.getY());
		if(object != null && object instanceof Cell){
			Cell cell = (Cell)object;
			if(cell.getType() == CellType.EMPTY){
				((Cell)object).setType(playerType);
				checkWin();
				if(!isEnded()){
					computerPlayer.makeTurn();
					checkWin();
				}
			}
		}
	}
	
	private void checkWin(){
		winSequence = board.searchWinSequence();
		if(winSequence != null){
			drawWinLine();
		}
		else{
			if(board.isAllFilled()){
				winSequence = new WinSequence(null, null, null, CellType.EMPTY);
			}
		}
	}
	
	private void drawWinLine(){
		drawLineBetweenCells(winSequence.getFirst(), winSequence.getThird());
	}
	
	private void drawLineBetweenCells(Cell first, Cell second){
		double lineHeight = board.getCellSize() / 5;
		double firstX = first.getX() + board.getCellSize() / 2;
		double firstY = first.getY() + board.getCellSize() / 2;
		double secondX = second.getX() + board.getCellSize() / 2;
		double secondY = second.getY() + board.getCellSize() / 2;
		double lineLength = Math.sqrt(Math.pow(secondX - firstX, 2) + Math.pow(secondY - firstY, 2));
		GPolygon line = new GPolygon(firstX, firstY);
		line.addVertex(0, -lineHeight / 2);
		line.addVertex(lineLength, -lineHeight / 2);
		line.addVertex(lineLength, lineHeight / 2);
		line.addVertex(0, lineHeight / 2);
		if(firstX == secondX && firstY != secondY){
			line.rotate(270);
		}
		else if(firstX != secondX && firstY != secondY){
			if(firstX > secondX){
				line.rotate(225);
			}
			else{
				line.rotate(315);
			}
		}
		line.setFilled(true);
		line.setFillColor(Color.RED);
		line.setColor(Color.RED);
		program.add(line);
	}
}
