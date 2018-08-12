import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public class Caterpillar extends Bug{
	private double speed =1;
	private double energy = 40;

	public Caterpillar(double x, double y, Image i) {
		super(x, y, i);
	}
	
	public void tick(World world) {
		if(this.energy >=20) {
		List<Entity> leftEntities = new ArrayList<Entity>(world.getEntities());
		leftEntities.remove(this);
		for(Entity e: leftEntities) {
			if(e instanceof Obstacle && this.getBoundsInParent().intersects(e.getBoundsInParent())) {
				speed = -speed;
				break;
			}
		}
		
		this.setLayoutX(this.getLayoutX() +speed);
		this.energy--;
		}else {
			this.eat();
		}
		
		
		
	}
	
	
	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void eat() {
		this.energy++;
	}
}
