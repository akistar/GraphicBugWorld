import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;

public class World extends Group {
	private int width;
	private int height;
	private List<Entity> entities = new ArrayList<Entity>();

	public World(int w, int h) {
		this.width = w;
		this.height = h;
		this.getChildren().addAll(entities);
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public void addEntityRandomly(Entity e) {
		double x = Math.random()*this.width;
		double y = Math.random()*this.height;
		e.setLayoutX(x);
		e.setLayoutY(y);

		while(!checkValid(e)) {
			System.out.println("same");
			x = Math.random()*this.width;
			y = Math.random()*this.height;
			e.setLayoutX(x);
			e.setLayoutY(y);
		}
		this.entities.add(e);
		this.getChildren().add(e);

	}



	public boolean checkValid(Entity e) {
		for(Entity entity: this.entities) {
			if(entity.getBoundsInParent().intersects(e.getBoundsInParent())) {
				return false;
			}
		}
		return true;
	}

	public void tickBug() {
		for(Entity e: entities) {
			e.tick(this);
			if(e.getEnergy()<0) {
				System.out.println("move energy "+e.getEnergy());
				this.entities.remove(e);
			}
			if(e instanceof Bee) {
				System.out.println("energy "+e.getEnergy());

				if(e.getLayoutX()<=10)
					e.setLayoutX(e.getLayoutX()+5);
				if(e.getLayoutX()>=width -10)
					e.setLayoutX(e.getLayoutX()-5);
				if(e.getLayoutY()<=10)
					e.setLayoutY(e.getLayoutY()+5);
				if(e.getLayoutY()>=height-10)
					e.setLayoutY(e.getLayoutY()-5);
			}
			else if(e instanceof Caterpillar) {
				Caterpillar c = (Caterpillar) e;
				if(e.getLayoutX()<=10)
					c.setSpeed(-c.getSpeed());
				if(e.getLayoutX()>=width -10)
					c.setSpeed(-c.getSpeed());

			}}
	}
	public List<Entity> getEntities() {
		return entities;
	}
	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}

	//public void remove


}
