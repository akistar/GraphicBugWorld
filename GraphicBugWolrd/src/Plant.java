import javafx.scene.image.Image;

public class Plant extends Entity{
	private int size = 20; 
	
	
	public Plant(double x, double y, Image i){
		//display its position in the scene
		this.setLayoutX(x);
		this.setLayoutY(y);
		//initial its size
		this.setFitWidth(size);
		this.setFitHeight(size);
		//set the image
		this.setImage(i);
	}
    
	/**
	 * plant can grow each turn. getScaleX and getScaleY default value is 0
	 */
	public void tick(World world) {
		if(this.getScaleX()<=2.2||this.getScaleY()<=2.2) {
			this.setScaleX(this.getScaleX()+0.01);
		    this.setScaleY(this.getScaleY()+0.01);
		    }
	}
	
    /**
     * when a plant is eaten, it will become smaller and smaller.
     */
	public void eaten() {
		if(this.getScaleX()>=0||this.getScaleY()>=0) {
			this.setScaleX(this.getScaleX()-0.01);
			this.setScaleY(this.getScaleY()-0.01);
		}
	}
}
