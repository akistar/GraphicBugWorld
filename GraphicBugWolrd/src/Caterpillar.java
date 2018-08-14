import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
/**
 * a caterpillar will move straight. When it is hungry and meet a leave, it will stop and eat until it is full of energy.
 * @author akistar
 *
 */
public class Caterpillar extends Bug{
	
	private double speed;

	public Caterpillar(double x, double y, Image i) {
		super(x, y, i);
		double energy = Math.random()*200+100; //initial the caterpillar energy between 100 to 200
		super.setEnergy(energy);
		double direction = Math.random();
		if(direction < 0.5) {
			speed = -1;
		}else {
			speed = 1;
		}
	}

	/**
	 * define the caterpillar action in the bug world. It just move one direction. 
	 * when it meet the obstacle, it will change it direction.
	 * when it meet a leaf and it is hungry, it will stop and eat the leaf until it is not hungry.
	 */
	public void tick(World world) {

		List<Entity> leftEntities = new ArrayList<Entity>(world.getEntities());
		leftEntities.remove(this);
		for(Entity e: leftEntities) {
			//meet the obstacle
			if(e instanceof Obstacle && this.getBoundsInParent().intersects(e.getBoundsInParent())) {
				speed = -speed;
				break;
			}
			//meet the leaf
			if(e instanceof Leaf && this.getBoundsInParent().intersects(e.getBoundsInParent())&& super.isHungry()==true) {
				this.eat();
				Leaf leaf = (Leaf) e;
				//leaf can be eaten
				leaf.eaten();
				if(super.getEnergy()>180) {
					super.setHungry(false);
				}
				return;
			}
		}
		
		//movement
      	this.setLayoutX(this.getLayoutX() +speed);
      	super.setEnergy(super.getEnergy()-1);

	}


	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	
    /**
     * when it is eating, it can get energy back.
     */
	public void eat() {
		super.setEnergy(super.getEnergy()+1);
	}
}
