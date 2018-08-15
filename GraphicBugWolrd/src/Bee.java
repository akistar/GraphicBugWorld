import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
/**
 * Bee can move randomly. When it move, it will lost its energy. When it is hungry, it will eat honey from the plant, it can get energy back.
 * @author Dantong Huang
 *
 */
public class Bee extends Bug{
	
	private double eatSpeed = 0.5;
	

	public Bee(double x, double y, Image i) {
		super(x, y, i);
		double energy = Math.random()*200+100; //initial the bee energy between 100 to 200
		super.setEnergy(energy);
	}
	
	/**
	 * bee can get energy from all the plants. Plant will not get small when bee is getting energy.
	 */
	public void tick(World world) {
		List<Entity> leftEntities = new ArrayList<Entity>(world.getEntities());
		leftEntities.remove(this);
		for(Entity e: leftEntities) {
			//when a bee is close to a plant and it is hungry, it will eat food.
			if(e instanceof Plant && this.getBoundsInParent().intersects(e.getBoundsInParent())&& super.isHungry()==true) {
				this.eat();
				//when it is energy is more than 200, it will stop eating food.
				if(super.getEnergy()>200) {
					super.setHungry(false);
				}
				return;
			}
		}
		//call the tick method to move randomly
		super.tick(world);
	}
	
   /**
    * when a bee is eating, it can stop and move around the plant
    */
	public void eat() {
         super.eat();
		//make it move around the plant
		this.setLayoutY(this.getLayoutY()-eatSpeed);
		this.setLayoutX(this.getLayoutX()-eatSpeed);
		eatSpeed = - eatSpeed;
	}

}
