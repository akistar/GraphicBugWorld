import javafx.scene.image.Image;
/**
 * Bug can move and eat.
 * @author Dantong Huang
 *
 */
public class Bug extends Entity{
	private double speed =10;
	private double energy = 150;
    private boolean isHungry = false;

	public Bug(double x, double y, Image i){
		this.setLayoutX(x);
		this.setLayoutY(y);
		this.setImage(i);
		this.setFitWidth(20);// to set the image's width
		this.setFitHeight(20);//to set the image's height
	}

	//make randomly movement
	public void tick(World world) {
		double d = Math.random();
		if(d<0.25) {
			//north
			this.setLayoutY(this.getLayoutY()-speed);
		}else if(d<0.5) {
			//south
			this.setLayoutY(this.getLayoutY()+speed);
		}else if(d<0.75) {
			//east
			this.setLayoutX(this.getLayoutX()-speed);
		}else if(d<1) {
			//west
			this.setLayoutX(this.getLayoutX()+speed);
		}
		this.energy = this.energy-0.5;
	
	}
	
    
	// when a bug is eating, it can get energy back.
	public void eat() {
		this.energy++;
	}
	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getEnergy() {
		return energy;
	}

	public void setEnergy(double energy) {
		this.energy = energy;
	}
	
	public boolean isHungry() {
		return isHungry;
	}

	public void setHungry(boolean isHungry) {
		this.isHungry = isHungry;
	}

}
