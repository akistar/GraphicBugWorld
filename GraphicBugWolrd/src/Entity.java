import javafx.scene.image.ImageView;

public abstract class Entity extends ImageView{
public void tick(World world) {}
public double getEnergy() {return 1;}
public void setEnergy(double e) {}
public boolean isHungry() {return false;}
public void setHungry(boolean isHungry) {}
public double getSpeed() {
	return 0;
}


public void setSpeed(double speed) {
	
}
//for circle
//public double getLeft() {
//	return this.getX() - this.getRadius();
//}
//public double getRight() {
//	return this.getCenterX() + this.getRadius();
//}public double getTop() {
//	return this.getCenterY() - this.getRadius();
//}public double getBottom() {
//	return this.getCenterY() + this.getRadius();
//}
}
