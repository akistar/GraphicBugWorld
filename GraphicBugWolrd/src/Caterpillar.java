import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
/**
 * a caterpillar will move straight. When it is hungry and meet a leave, it will stop and eat until it is full of energy.
 * @author akistar
 *
 */
public class Caterpillar extends Bug{


	public Caterpillar(double x, double y, Image i) {
		super(x, y, i);
		double energy = Math.random()*200+180; //initial the caterpillar energy between 180 to 200
		super.setEnergy(energy);
		double direction = Math.random();
		if(direction < 0.5) {
			super.setSpeed(-1);
		}else {
			super.setSpeed(1);
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
				super.setSpeed(-super.getSpeed());
				break;
			}
			//meet the leaf
			if(e instanceof Leaf && this.getBoundsInParent().intersects(e.getBoundsInParent())&& super.isHungry()==true) {
				super.eat();
				Leaf leaf = (Leaf) e;
				//leaf can be eaten
				leaf.eaten();
				if(super.getEnergy()>180) {
					super.setHungry(false);
				}
				return;
			}
		}

		if(!super.isHungry()) {
			this.moveOneDirection();
		}else {
			//if it is hungry, it will search food and move to the food.
			List<Entity> foodsNearby = this.getFoodsNearby(world);
			if(foodsNearby.size()>0) {
				Entity food = foodsNearby.get(0);
				double xPos = this.getLayoutX() - food.getLayoutX();// get the X direction
				double yPos = this.getLayoutY() - food.getLayoutY();// get the Y direction
				if(xPos >= 0) {this.setLayoutX(this.getLayoutX()-super.getSpeed());}//left
				if(xPos < 0) {this.setLayoutX(this.getLayoutX()+super.getSpeed());}//right
				if(yPos >= 0) {this.setLayoutY(this.getLayoutY()-super.getSpeed());}//up
				if(yPos < 0) {this.setLayoutY(this.getLayoutY()+super.getSpeed());}//down
			}else {
				this.moveOneDirection();
			}
		}

		super.setEnergy(super.getEnergy()-1);

	}

	/**
	 * a method to let the caterpillar move in one direction
	 */
	public void moveOneDirection() {
		this.setLayoutX(this.getLayoutX()+super.getSpeed());
	}


	/**
	 * get the leaf nearby 
	 * @param world
	 * @return
	 */
	public List<Entity> getFoodsNearby(World world) {
		// When the distance between the food and caterpillar is less than 80, it is the food nearby.
		List<Entity> foodsNearby = new ArrayList<Entity>();

		for(Entity e: world.getEntities()) {
			if(e instanceof Leaf) {
				double distance = Math.sqrt((e.getLayoutX() - this.getLayoutX())*(e.getLayoutX() - this.getLayoutX()) + (e.getLayoutY() - this.getLayoutY())*(e.getLayoutY() - this.getLayoutY()));
				if(distance <=80) {
					foodsNearby.add(e);
				}
			}
		}

		return foodsNearby;
	}

}
