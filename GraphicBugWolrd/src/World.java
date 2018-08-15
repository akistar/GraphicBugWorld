import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
/**
 * World has a list to store all the entities. 
 * @author Dantong Huang
 *
 */
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
	/**
	 * To add the entity into the world randomly. Check whether the position will cover the exiting entities. If true, set another position 
	 * @param e
	 */
	public void addEntityRandomly(Entity e) {
		double x = Math.random()*(this.width -2*e.getFitWidth())+e.getFitWidth();
		double y = Math.random()*(this.height- 2*e.getFitHeight())+e.getFitHeight();
		e.setLayoutX(x);
		e.setLayoutY(y);

		while(!checkValid(e)) {
			x = Math.random()*(this.width -2*e.getFitWidth())+e.getFitWidth();
			y = Math.random()*(this.height- 2*e.getFitHeight())+e.getFitHeight();
			e.setLayoutX(x);
			e.setLayoutY(y);
		}
		this.entities.add(e);
		this.getChildren().add(e);

	}

	/**
	 * another method to add entity. for create baby entity. 
	 * @param e
	 */
	//to do
	public void addEntity(Entity e) {
		while(!checkValid(e)) {
			e.setLayoutX(e.getLayoutX()-1);
			e.setLayoutY(e.getLayoutY()-1);
		}
		this.entities.add(e);
		this.getChildren().add(e);
	}


	/**
	 * a method to check whether the position will cover the exiting bugs.
	 * @param e
	 * @return
	 */
	public boolean checkValid(Entity e) {
		for(Entity entity: this.entities) {
			if(entity.getBoundsInParent().intersects(e.getBoundsInParent())) {
				return false;
			}
		}
		return true;
	}
	/**
	 * do action on the bug.
	 */
	public void tickBug() {
		for(Entity e: entities) 
		{
			e.tick(this);
			//when an entity's energy is less than 100, it will search food.
			if(e.getEnergy()<100) {
				e.setHungry(true);
			}
			//if the energy is less than 0, means the entity is dead. And set it visible first. Then remove it from the entities. 
			if(e.getEnergy()<=0) {
				e.setVisible(false);
			}
			// if a bug meet the edge, change its direction
			else if(e instanceof Bee ) {
				if(e.getLayoutX()<=e.getFitWidth())
					e.setLayoutX(e.getLayoutX()+5);
				if(e.getLayoutX()>=width -e.getFitWidth())
					e.setLayoutX(e.getLayoutX()-5);
				if(e.getLayoutY()<=e.getFitHeight())
					e.setLayoutY(e.getLayoutY()+5);
				if(e.getLayoutY()>=height-e.getFitHeight())
					e.setLayoutY(e.getLayoutY()-5);
			}else if(e instanceof Caterpillar|| e instanceof Beetle) {
				if(e.getLayoutX()<=e.getFitWidth())
					e.setSpeed(-e.getSpeed());
				if(e.getLayoutX()>=width -e.getFitWidth())
					e.setSpeed(-e.getSpeed());
				if(e.getLayoutY()<=e.getFitHeight())
					e.setSpeed(-e.getSpeed());				
				if(e.getLayoutY()>=height-e.getFitWidth())
					e.setSpeed(-e.getSpeed());

			}}
	}

	
	public List<Entity> getEntities() {
		return entities;
	}
	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}


	/**
	 * when the entity is not visible, which mean it is dead. need to be removed from the world.
	 */
	public void removeDead() {
		int index = this.entities.size();
		for(int i=0; i<index;) {
			Entity e = this.entities.get(i);
			if(e.isVisible()==false) {
				this.entities.remove(e);
				this.getChildren().remove(e);
				index = this.entities.size();
			}
			i++;
		}
	}
	
	public void clearEntities() {
		for(int i=0; i<this.entities.size();i++) {
			this.entities.get(i).setVisible(false);
		}
	}

}
