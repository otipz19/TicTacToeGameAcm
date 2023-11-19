import acm.program.GraphicsProgram;


public class Board {
	private GraphicsProgram program;
	
	private double x;
	private double y;
	private double cellGap;
	private double cellSize;
	
	//[col][row] - leftUp - left column, upper row
	private Cell leftUp;
	private Cell midUp;
	private Cell rightUp;
	private Cell leftMid;
	private Cell midMid;
	private Cell rightMid;
	private Cell leftDown;
	private Cell midDown;
	private Cell rightDown;
	
	public Board(GraphicsProgram program, double boardSize, double x, double y, double cellGap){
		this.program = program;
		this.x = x;
		this.y = y;
		this.cellGap = cellGap;
		cellSize = (boardSize - 2 * cellGap) / 3;
		createCells();
	}
	
	public Cell getCell(int row, int col){
		switch(row){
			case 0:
				switch(col){
				case 0:
					return leftUp;
				case 1:
					return midUp;
				case 2:
					return rightUp;
				}
			case 1:
				switch(col){
				case 0:
					return leftMid;
				case 1:
					return midMid;
				case 2:
					return rightMid;
				}
			case 2:
				switch(col){
				case 0:
					return leftDown;
				case 1:
					return midDown;
				case 2:
					return rightDown;
				}
		}
		return null;
	}
	
	public double getCellSize(){
		return cellSize;
	}
	
	public boolean isAllFilled(){
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				if(getCell(i, j).getType() == CellType.EMPTY){
					return false;
				}
			}
		}
		return true;
	}
	
	public WinSequence searchWinSequence(){
		WinSequence seq;
		for(int i = 0; i < 3; i++){
			seq = checkSequenceFilled(getCell(i, 0), getCell(i, 1), getCell(i, 2));
			if(seq != null){
				return seq;
			}
			seq = checkSequenceFilled(getCell(0, i), getCell(1, i), getCell(2, i));
			if(seq != null){
				return seq;
			}
		}
		seq = checkSequenceFilled(getCell(0, 0), getCell(1, 1), getCell(2, 2));
		if(seq != null){
			return seq;
		}
		return checkSequenceFilled(getCell(0, 2), getCell(1, 1), getCell(2, 0));
	}
	
	private WinSequence checkSequenceFilled(Cell first, Cell second, Cell third){
		if(first.getType() != CellType.EMPTY && first.getType() == second.getType() && first.getType() == third.getType()){
			return new WinSequence(first, second, third, first.getType());
		}
		return null;
	}
	
	private void createCells(){
		double firstColX = x;
		double secondColX = x + (cellSize + cellGap);
		double thirdColX = x + 2 * (cellSize + cellGap);
		double firstRowY = y;
		double secondRowY = y + (cellSize + cellGap);
		double thirdRowY = y + 2 * (cellSize + cellGap);
		
		leftUp = new Cell(firstColX, firstRowY, cellSize, cellSize);
		midUp = new Cell(secondColX, firstRowY, cellSize, cellSize);
		rightUp = new Cell(thirdColX, firstRowY, cellSize, cellSize);
		
		leftMid = new Cell(firstColX, secondRowY, cellSize, cellSize);
		midMid = new Cell(secondColX, secondRowY, cellSize, cellSize);
		rightMid = new Cell(thirdColX, secondRowY, cellSize, cellSize);
		
		leftDown = new Cell(firstColX, thirdRowY, cellSize, cellSize);
		midDown = new Cell(secondColX, thirdRowY, cellSize, cellSize);
		rightDown = new Cell(thirdColX, thirdRowY, cellSize, cellSize);
		
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				program.add(getCell(i, j));
			}
		}
	}
}
