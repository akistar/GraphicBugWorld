import javafx.scene.image.Image;
/**
 * Plant can grow and get smaller when it is eaten.
 * @author Dantong Huang
 */
public class Plant extends Entity{
	
	
	public Plant(double x, double y, Image i){
		double size = Math.random()*10+15;
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
		if(this.getScaleX()>0||this.getScaleY()>0) {
			this.setScaleX(this.getScaleX()-0.01);
			this.setScaleY(this.getScaleY()-0.01);
		}
		if(this.getScaleX() <= 0) {
			this.setVisible(false);
		}
	}
}
