import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public class Bee extends Bug{
	
	private double speed =10;
	private double eatSpeed = 5;
	private double energy = 40;// less than 20 will eat food;

	public Bee(double x, double y, Image i) {
		super(x, y, i);
	}
	
	public void tick(World world) {
		
		List<Entity> leftEntities = new ArrayList<Entity>(world.getEntities());
		leftEntities.remove(this);
		for(Entity e: leftEntities) {
			if(e instanceof Plant && this.getBoundsInParent().intersects(e.getBoundsInParent())&& energy<20) {
				this.eat();
				return;
			}
		}
		double d = Math.random();
		if(d<0.25) {
			//north
			System.out.println("moving");

			this.setLayoutY(this.getLayoutY()-speed);
			this.energy--;

		}else if(d<0.5) {
			//south
			System.out.println("moving");

			this.setLayoutY(this.getLayoutY()+speed);
			this.energy--;

		}else if(d<0.75) {
			//east
			System.out.println("moving");

			this.setLayoutX(this.getLayoutX()-speed);
			this.energy--;

		}else if(d<1) {
			//west
			System.out.println("moving");

			this.setLayoutX(this.getLayoutX()+speed);
			this.energy--;

		}
	}
	
	
	public void eat() {
		this.energy++;
		this.setLayoutY(this.getLayoutY()-eatSpeed);
		this.setLayoutX(this.getLayoutX()-eatSpeed);
		eatSpeed = - eatSpeed;
	}

}
