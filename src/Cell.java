import java.awt.*;
import acm.graphics.*;
import acm.util.RandomGenerator;

public class Cell extends GCompound{
	private static final String ZERO_IMAGE_PATH = "..\\images\\zero.png";
	private static final String CROSS_IMAGE_PATH = "..\\images\\cross.png"; 
	
	private CellType type = CellType.EMPTY;
	private GRect background;
	private GImage image;
	
	public Cell(double x, double y, double width, double height){
		this(x, y, width, height, Color.WHITE);
	}
	
	public Cell(double x, double y, double width, double height, Color color){
		setLocation(x, y);
		background = new GRect(0, 0, width, height);
		background.setFilled(true);
		background.setFillColor(color);
		add(background);
	}
	
	public CellType getType(){
		return type;
	}
	
	public void setType(CellType type){
		if(this.type == CellType.EMPTY){
			this.type = type;
			image = new GImage(type == CellType.ZERO ? ZERO_IMAGE_PATH : CROSS_IMAGE_PATH);
			image.setSize(background.getWidth(), background.getHeight());
			add(image);
		}
	}
}
