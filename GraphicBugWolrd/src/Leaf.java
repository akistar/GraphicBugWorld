import javafx.scene.image.Image;
/**
 * leaf cannot grow. It can eaten by caterpillar.
 * @author akistar
 *
 */
public class Leaf extends Plant{

	public Leaf(double x, double y, Image i) {
		super(x,y,i);
	}
	/**
	 * override the tick method from the plant. do not do anything. because the leaf will not grow.
	 */
	public void tick(World world) {}
	
}
