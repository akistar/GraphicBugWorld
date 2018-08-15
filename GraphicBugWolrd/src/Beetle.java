import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
/**
 * A beetle can move randomly and to one direction at the same time. It can eat Caterpillar.
 * @author Dantong Huang
 *
 */
public class Beetle extends Bug{
	
	public Beetle(double x, double y, Image i) {
		super(x, y, i);
		double energy = Math.random()*200+150; //initial the caterpillar energy between 150 to 200
		super.setEnergy(energy);
		double direction = Math.random();
		if(direction < 0.5) {
			super.setSpeed(-1);
		}else {
			super.setSpeed(1);
		}
	}
	

   
	public void tick(World world) {
		
		List<Entity> leftEntities = new ArrayList<Entity>(world.getEntities());
		leftEntities.remove(this);
		for(Entity e: leftEntities) {
			//when a beetle is hungry and happen to meet the caterpillar, it will eat the caterpillar
			if(e instanceof Caterpillar && this.getBoundsInParent().intersects(e.getBoundsInParent())&& super.getEnergy()<100) {
				super.setEnergy(super.getEnergy()+100);
				e.setVisible(false);
				return;
			}
			//when it meet beetle or Obstacle, it will change the move direction.
			if((e instanceof Beetle||e instanceof Obstacle) && this.getBoundsInParent().intersects(e.getBoundsInParent())) {
				super.setSpeed(-super.getSpeed());
			}
		}
		
		//make it move to one direction
		this.setLayoutX(this.getLayoutX()+super.getSpeed());
		this.setLayoutY(this.getLayoutY()+super.getSpeed());
		
		//call the tick method from the superClass again to make it also move randomly
		super.tick(world);
				
	}

}
