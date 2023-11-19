
public class WinSequence {
	private Cell first;
	private Cell second;
	private Cell third;
	private CellType winType;
	
	public WinSequence(Cell first, Cell second, Cell third, CellType winType) {
		super();
		this.first = first;
		this.second = second;
		this.third = third;
		this.winType = winType;
	}
	
	public Cell getFirst() {
		return first;
	}
	public Cell getSecond() {
		return second;
	}
	public Cell getThird() {
		return third;
	}
	public CellType getWinType() {
		return winType;
	}
}
