import acm.util.RandomGenerator;


public class RandomComputerPlayer implements IComputerPlayer{
	private Board board;
	private CellType type;
	private RandomGenerator rng = RandomGenerator.getInstance();
	
	public RandomComputerPlayer(Board board, CellType type){
		this.board = board;
		this.type = type;
	}
	
	public void makeTurn(){
		//1000 - maximum number of attempts
		for(int i = 0; i < 1000; i++){
			int row = rng.nextInt(0, 2);
			int col = rng.nextInt(0, 2);
			if(tryPickCell(board.getCell(row, col))){
				return;
			}
		}
	}
	
	private boolean tryPickCell(Cell cell){
		if(cell.getType() == CellType.EMPTY){
			cell.setType(type);
			return true;
		}
		return false;
	}
}
