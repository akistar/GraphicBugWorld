import javafx.scene.image.Image;

public class Obstacle extends Entity{
	private int size = 20; 
	
	public Obstacle(double x, double y, Image i){

		this.setLayoutX(x);
		this.setLayoutY(y);
		this.setFitWidth(size);
		this.setFitHeight(size);
		this.setImage(i);
	}
}
