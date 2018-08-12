import javafx.scene.image.Image;

public class Plant extends Entity{
	private int size = 20; 
	public Plant(double x, double y, Image i){

		this.setLayoutX(x);
		this.setLayoutY(y);
		this.setFitWidth(size);
		this.setFitHeight(size);
		this.setImage(i);
	}

	public void tick(World world) {
		if(this.getScaleX()<=2||this.getScaleY()<=2) {
			System.out.println(this.getScaleX());
			this.setScaleX(this.getScaleX()+0.01);
		    this.setScaleY(this.getScaleY()+0.01);
		    }
	}

	public void eaten() {
		if(this.getScaleX()>=0||this.getScaleY()>=0) {
			this.setScaleX(this.getScaleX()-0.01);
			this.setScaleY(this.getScaleY()-0.01);
		}
	}
}
