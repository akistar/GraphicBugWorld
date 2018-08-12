
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class UserInterface extends Application{



	@Override
	public void start(Stage primaryStage) throws Exception {
		World world = new World(500, 500);


		Image beeImage= new Image(getClass().getResourceAsStream("bee.png"));

		Image daisyImage= new Image(getClass().getResourceAsStream("daisy.png"));

		Image stoneImage= new Image(getClass().getResourceAsStream("stone.png"));

		Image caterpillarImage= new Image(getClass().getResourceAsStream("caterpillar.png"));



		for(int j=0; j<1; j++) {
			Bee bee = new Bee(10,10,beeImage);
			Caterpillar caterpillar = new Caterpillar(20, 20, caterpillarImage);
			Plant plant = new Plant(30,30,daisyImage);
			Obstacle obstacle = new Obstacle(40,50,stoneImage);


			world.addEntityRandomly(bee);
			world.addEntityRandomly(caterpillar);
			world.addEntityRandomly(plant);
			world.addEntityRandomly(obstacle);
		}
//		for(int k=0; k<5; k++) {
//			Plant plant = new Plant(10,10,daisyImage);
//			Obstacle obstacle = new Obstacle(10,10,stoneImage);
//			world.addEntityRandomly(plant);
//			world.addEntityRandomly(obstacle); 
//		}
//		for(int k=0; k<15; k++) {
//			Obstacle obstacle = new Obstacle(10,10,stoneImage);
//			world.addEntityRandomly(obstacle);
//		}

		//		for(int i =0; i<11; i++) {
		//			Bug bug = new Bug(10, 10);
		//			world.addEntityRandomly(bug);
		//		}

		Scene scene = new Scene(world, world.getWidth(),world.getHeight(),Color.FORESTGREEN);

		Button playButton = new Button("Play");
		playButton.setLayoutX(100);

		Button stopButton = new Button("Stop");

		world.getChildren().addAll(playButton, stopButton);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Bug world");
		primaryStage.show();

		//		KeyFrame frame = new KeyFrame(Duration.millis(150), new EventHandler<ActionEvent>() {
		//			@Override
		//			public void handle(ActionEvent arg0) {
		//				world.tickBug();
		//			}
		//		});
		//
		//		
		//		Timeline timeline = new Timeline(frame);
		//		
		//	timeline.setCycleCount(javafx.animation.Animation.INDEFINITE);
		//	timeline.play();
		//		

		KeyFrame frame = new KeyFrame(Duration.millis(150), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				world.tickBug();

			}
		});


		Timeline timeline = new Timeline(frame);

		timeline.setCycleCount(javafx.animation.Animation.INDEFINITE);

		playButton.setOnAction((ActionEvent event)->{
			timeline.play();
		});
		stopButton.setOnAction((ActionEvent event)->{
			timeline.stop();
		});
	}



	public static void main(String[] args) {
		Application.launch(args);

	}

}
