import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public class Beetle extends Bug{
	private double speed =10;
	private double eatSpeed = 5;
	public Beetle(double x, double y, Image i) {
		super(x, y, i);
		super.setEnergy(100);
	}
	
	Image beetleImage= new Image(getClass().getResourceAsStream("beetle.png"));


	public void tick(World world) {
		
		List<Entity> leftEntities = new ArrayList<Entity>(world.getEntities());
		leftEntities.remove(this);
		for(Entity e: leftEntities) {
			if(e instanceof Caterpillar && this.getBoundsInParent().intersects(e.getBoundsInParent())&& super.getEnergy()<20) {
				System.out.println("Beetle eating bug");
				super.setEnergy(super.getEnergy()+20);
				e.setVisible(false);
				return;
			}
//			if(e instanceof Beetle && this.getBoundsInParent().intersects(e.getBoundsInParent()) && super.getEnergy()>10 && e.getEnergy()>10) {
//				System.out.println("new bettle");
//				super.setEnergy(super.getEnergy()-10);
//				e.setEnergy(e.getEnergy()-10);
//				Beetle newBettle = new Beetle(e.getLayoutX()-30, e.getLayoutY()-30, beetleImage);
//				world.addEntity(newBettle);
//				return;
//			}
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
	
	
//	public void eat() {
//		super.setEnergy(super.getEnergy()+1);
//		this.setLayoutY(this.getLayoutY()-eatSpeed);
//		this.setLayoutX(this.getLayoutX()-eatSpeed);
//		eatSpeed = - eatSpeed;
//	}
}
