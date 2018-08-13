import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
/**
 * Bee can move randomly. When it move, it will lost its energy. When it is hungry, it will eat honey from the plant, it can get energy back.
 * @author akistar
 *
 */
public class Bee extends Bug{
	
	private double speed =10;
	private double eatSpeed = 1;
	

	public Bee(double x, double y, Image i) {
		super(x, y, i);
		double energy = Math.random()*200+100; //initial the bee energy between 100 to 200
		super.setEnergy(energy);
	}
	
	public void tick(World world) {
		
		
		List<Entity> leftEntities = new ArrayList<Entity>(world.getEntities());
		leftEntities.remove(this);
		for(Entity e: leftEntities) {
			//when a bee is close to a plant and it is hungry, it will eat food.
			if(e instanceof Plant && this.getBoundsInParent().intersects(e.getBoundsInParent())&& super.isHungry()==true) {
				this.eat();
				//when it is energy is more than 150, it will stop eating food.
				if(super.getEnergy()>150) {
					super.setHungry(false);
				}
				return;
			}
		}
		double d = Math.random();
		if(d<0.25) {
			//north
			this.setLayoutY(this.getLayoutY()-speed);
			super.setEnergy(super.getEnergy()-1);


		}else if(d<0.5) {
			//south
			this.setLayoutY(this.getLayoutY()+speed);
			super.setEnergy(super.getEnergy()-1);


		}else if(d<0.75) {
			//east
			this.setLayoutX(this.getLayoutX()-speed);
			super.setEnergy(super.getEnergy()-1);


		}else if(d<1) {
			//west
			this.setLayoutX(this.getLayoutX()+speed);
			super.setEnergy(super.getEnergy()-1);


		}
	}
	

	public void eat() {
		super.setEnergy(super.getEnergy()+1);
		//make it move around the plant
		this.setLayoutY(this.getLayoutY()-eatSpeed);
		this.setLayoutX(this.getLayoutX()-eatSpeed);
		eatSpeed = - eatSpeed;
	}

}
