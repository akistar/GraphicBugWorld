import javafx.scene.image.Image;

public class Bug extends Entity{
	//	private double x;
	//	private double y;
	private double speed =10;
	private double energy = 40;
	public Bug(double x, double y, Image i){
		// for circle
		//		this.setCenterX(x);
		//		this.setCenterY(y);
		//		this.setRadius(10);
		this.setLayoutX(x);
		this.setLayoutY(y);
		this.setImage(i);
		this.setFitWidth(10);
		this.setFitHeight(10);
	}

	//make randomly movement
	public void tick(World world) {
		//for circle
		//		double d = Math.random();
		//		if(d<0.25) {
		//			//north
		//			this.setCenterY(this.getCenterY()-speed);
		//		}else if(d<0.5) {
		//			//south
		//			this.setCenterY(this.getCenterY()+speed);
		//		}else if(d<0.75) {
		//			//east
		//			this.setCenterX(this.getCenterX()-speed);
		//		}else if(d<1) {
		//			//west
		//			this.setCenterX(this.getCenterX()+speed);
		//		}
		//		this.setFitWidth(this.getFitWidth()+10);
		//		this.setFitHeight(this.getFitHeight()+10);



		double d = Math.random();
		if(d<0.25) {
			//north
			this.setLayoutY(this.getLayoutY()-speed);
		}else if(d<0.5) {
			//south
			this.setLayoutY(this.getLayoutY()+speed);
		}else if(d<0.75) {
			//east
			this.setLayoutX(this.getLayoutX()-speed);
		}else if(d<1) {
			//west
			this.setLayoutX(this.getLayoutX()+speed);
		}
	}


	public void move() {
		double d = Math.random();

		if(d<0.25) {
			//north
			this.setTranslateY(this.getTranslateY()-speed);
		}else if(d<0.5) {
			//south
			this.setTranslateY(this.getTranslateY()+speed);

		}else if(d<0.75) {
			//east
			this.setTranslateX(this.getTranslateX()-speed);

		}else if(d<1) {
			//west
			this.setTranslateX(this.getTranslateX()+speed);
		}
	}

	public double getEnergy() {
		return energy;
	}

	public void setEnergy(double energy) {
		this.energy = energy;
	}




}
