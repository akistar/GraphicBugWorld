import javafx.scene.image.ImageView;

/**
 * The superClass of all the object in the bug world.
 * @author Dantong Huang
 *
 */
public abstract class Entity extends ImageView{

	public void tick(World world) {}
	public double getEnergy() {return 1;}
	public void setEnergy(double e) {}
	public boolean isHungry() {return false;}
	public void setHungry(boolean isHungry) {}
	public double getSpeed() {return 0;}
	public void setSpeed(double speed) {	}

}
