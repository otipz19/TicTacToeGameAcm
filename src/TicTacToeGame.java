import java.awt.Color;
import java.awt.event.*;

import acm.graphics.*;
import acm.program.*;

public class TicTacToeGame extends GraphicsProgram{
	private static final int SCREEN_WIDTH = 600;
	private static final int SCREEN_HEIGHT = 700;
	
	private static final double BOARD_SIZE = 500;
	private static final double BOARD_HORIZONTAL_OFFSET = 50;
	private static final double BOARD_VERTICAL_OFFSET = 50;
	private static final double CELL_GAP = 25;
	
	private static final double BUTTON_SIZE = 75;
	private static final double BUTTON_HORIZONTAL_OFFSET = 50;
	private static final double BUTTON_VERTICAL_OFFSET = 25;
	
	private GameScene gameScene;
	private GCompound crossButton;
	private GCompound zeroButton;
	
	public void init(){
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		addMouseListeners();
		initGui();
	}
	
	public void mouseClicked(MouseEvent event){
		GObject object = getElementAt(event.getX(), event.getY());
		if(object != null){
			if(object == crossButton){
				initNewRound(CellType.CROSS);
			}
			else if(object == zeroButton){
				initNewRound(CellType.ZERO);
			}
			else if(gameScene != null){
				gameScene.handleClick(event);
				if(gameScene.isEnded()){
					if(gameScene.isPlayerWon()){
						createWinLabel("Player Won!");
					}
					else if(gameScene.isComputerWon()){
						createWinLabel("Computer Won!");
					}
					else{
						createWinLabel("Draw...");
					}
				}
			}
		}
	}
	
	private void initGui(){
		removeAll();
		drawBackground();
		createCrossButton();
		createZeroButton();
	}
	
	private void drawBackground() {
		GRect background = new GRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		Color color = new Color(0xD3D3D3);
		background.setFillColor(color);
		background.setColor(color);
		background.setFilled(true);
		add(background);
	}
	
	private void createZeroButton() {
		zeroButton = new GCompound();
		Cell btnCell = new Cell(SCREEN_WIDTH - BUTTON_SIZE - BUTTON_HORIZONTAL_OFFSET, SCREEN_HEIGHT - BUTTON_SIZE - BUTTON_VERTICAL_OFFSET, BUTTON_SIZE, BUTTON_SIZE);
		btnCell.setType(CellType.ZERO);
		zeroButton.add(btnCell);
		add(zeroButton);
	}

	private void createCrossButton() {
		crossButton = new GCompound();
		Cell btnCell = new Cell(BUTTON_HORIZONTAL_OFFSET, SCREEN_HEIGHT - BUTTON_SIZE - BUTTON_VERTICAL_OFFSET, BUTTON_SIZE, BUTTON_SIZE);
		btnCell.setType(CellType.CROSS);
		crossButton.add(btnCell);
		add(crossButton);
	}
	
	private void createWinLabel(String text){
		GLabel winLabel = new GLabel(text);
		winLabel.setFont("comicsans-36");
		winLabel.setColor(Color.RED);
		double x = (SCREEN_WIDTH - winLabel.getWidth()) / 2;
		double y = SCREEN_HEIGHT - BUTTON_VERTICAL_OFFSET;
		winLabel.setLocation(x, y);
		add(winLabel);
	}

	private void initNewRound(CellType playerType){
		initGui();
		Board board = new Board(this, BOARD_SIZE, BOARD_HORIZONTAL_OFFSET, BOARD_VERTICAL_OFFSET, CELL_GAP);
		CellType computerType = playerType == CellType.CROSS ? CellType.ZERO : CellType.CROSS;
		IComputerPlayer computerPlayer = new RandomComputerPlayer(board, computerType);
		gameScene = new GameScene(this, board, playerType, computerType, computerPlayer);
	}
}
