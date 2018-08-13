
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
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class UserInterface extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		World world = new World(500, 450);

		Pane pane = new Pane();
		pane.setPrefWidth(500);
		pane.setPrefHeight(500);

	    pane.setStyle("-fx-background-color: forestGreen;");

		pane.getChildren().add(world);
		Image beeImage= new Image(getClass().getResourceAsStream("bee.png"));

		Image daisyImage= new Image(getClass().getResourceAsStream("daisy.png"));

		Image stoneImage= new Image(getClass().getResourceAsStream("stone.png"));

		Image caterpillarImage= new Image(getClass().getResourceAsStream("caterpillar.png"));

		Image beetleImage= new Image(getClass().getResourceAsStream("beetle.png"));

		Image leafImage= new Image(getClass().getResourceAsStream("leaf.png"));

         
		

		for(int j=0; j<1; j++) {
			Bee bee = new Bee(10,10,beeImage);
			Caterpillar caterpillar = new Caterpillar(20, 20, caterpillarImage);
			Plant plant = new Plant(30,30,daisyImage);
			Obstacle obstacle = new Obstacle(40,50,stoneImage);
			Beetle beetle = new Beetle(40,50,beetleImage);
			Leaf leaf = new Leaf(40,50,leafImage);


			world.addEntityRandomly(leaf);

			world.addEntityRandomly(bee);
			world.addEntityRandomly(caterpillar);
			world.addEntityRandomly(plant);
			world.addEntityRandomly(obstacle);
			world.addEntityRandomly(beetle);

		}
		

//		for(int j=0; j<20; j++) {
//			
//			Leaf leaf = new Leaf(40,50,leafImage);
//
//
//			world.addEntityRandomly(leaf);
//
//		}


		Button playButton = new Button("Play");
		playButton.setLayoutX(100);

		Button stopButton = new Button("Stop");
	
		HBox buttons = new HBox();
		buttons.getChildren().addAll(playButton,stopButton);
		

		VBox root = new VBox();
		root.getChildren().addAll(pane, buttons);
		Scene scene = new Scene(root);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Bug world");
		primaryStage.show();
	

		KeyFrame frame = new KeyFrame(Duration.millis(150), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				world.tickBug();//
				world.removeDead();//
			}
		});

	

		Timeline timeline = new Timeline(frame);

		timeline.setCycleCount(javafx.animation.Animation.INDEFINITE);
        
		KeyFrame frame1 = new KeyFrame(Duration.millis(1500), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Bee bee = new Bee(10,10,beeImage);
				Caterpillar caterpillar = new Caterpillar(20, 20, caterpillarImage);
				//Plant plant = new Plant(30,30,daisyImage);
				//Obstacle obstacle = new Obstacle(40,50,stoneImage);
				Beetle beetle = new Beetle(40,50,beetleImage);
				Leaf leaf = new Leaf(40,50,leafImage);


				world.addEntityRandomly(leaf);

				world.addEntityRandomly(bee);
				world.addEntityRandomly(caterpillar);
				//world.addEntityRandomly(plant);
			//	world.addEntityRandomly(obstacle);
				world.addEntityRandomly(beetle);
			}
		});

	

		Timeline timeline2 = new Timeline(frame1);

		timeline2.setCycleCount(javafx.animation.Animation.INDEFINITE);
        
		
		playButton.setOnAction((ActionEvent event)->{
			timeline2.play();
			timeline.play();

		});
		stopButton.setOnAction((ActionEvent event)->{
			timeline.stop();
			timeline2.stop();

		});
	}



	public static void main(String[] args) {
		Application.launch(args);

	}

}
