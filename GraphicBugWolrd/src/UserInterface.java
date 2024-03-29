
import javafx.animation.KeyFrame;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class UserInterface extends Application{
	
	World world = new World(600, 600);
	Image beeImage= new Image(getClass().getResourceAsStream("bee.png"));
	Image daisyImage= new Image(getClass().getResourceAsStream("daisy.png"));
	Image stoneImage= new Image(getClass().getResourceAsStream("stone.png"));
	Image caterpillarImage= new Image(getClass().getResourceAsStream("caterpillar.png"));
	Image beetleImage= new Image(getClass().getResourceAsStream("beetle.png"));
	Image leafImage= new Image(getClass().getResourceAsStream("leaf.png"));

	public void addBee(int n) {
        for(int i=0; i<n; i++) {
			Bee bee = new Bee(10,10,beeImage);
			world.addEntityRandomly(bee);
        }
	}
	
	public void addDaisy(int n) {
		 for(int i=0; i<n; i++) {
				Plant plant = new Plant(30,30,daisyImage);
				world.addEntityRandomly(plant);
	        }
	}
	
	public void addStone(int n) {
		 for(int i=0; i<n; i++) {
				Obstacle obstacle = new Obstacle(40,50,stoneImage);
				world.addEntityRandomly(obstacle);
	        }
	}
	
	public void addCaterpillar(int n) {
        for(int i=0; i<n; i++) {
			Caterpillar caterpillar = new Caterpillar(20, 20, caterpillarImage);
			world.addEntityRandomly(caterpillar);
        }
	}
	
	public void addBeetle(int n) {
		 for(int i=0; i<n; i++) {
				Beetle beetle = new Beetle(40,50,beetleImage);
				world.addEntityRandomly(beetle);
	        }
	}
	public void addLeaf(int n) {
		 for(int i=0; i<n; i++) {
				Leaf leaf = new Leaf(40,50,leafImage);
				world.addEntityRandomly(leaf);
	        }
	}
	
	public void submitText(TextField text, String entity, TextField nextText) {
		text.setOnKeyPressed(new EventHandler<KeyEvent>()
	    {
	        @Override
	        public void handle(KeyEvent ke)
	        {
	            if (ke.getCode().equals(KeyCode.ENTER))
	            {
					int num = Integer.parseInt(text.getText().replaceAll("\\s",""));
					if(entity.equals("bee")) {
						addBee(num);
						System.out.println("true");
						nextText.requestFocus();
					}
					if(entity.equals("daisy")) {
						addDaisy(num);
						nextText.requestFocus();
					}
					if(entity.equals("stone")) {
						addStone(num);
						nextText.requestFocus();
					}
					if(entity.equals("caterpillar")) {
						addCaterpillar(num);
						nextText.requestFocus();
					}
					if(entity.equals("beetle")) {
						addBeetle(num);
						nextText.requestFocus();
					}
					if(entity.equals("leaf")) {
						addLeaf(num);
						nextText.requestFocus();

					}
					
	            }
	        }
	    });
	}
	
	
	public StackPane createPane() {
		VBox vbox = new VBox();
		Label title = new Label("Create your own bug world");
		Label bee = new Label("Bee");
		TextField beeNum = new TextField();
		beeNum.setFocusTraversable(true);
		Label daisy = new Label("Daisy");
		TextField daisyNum = new TextField();
		Label stone = new Label("Stone");
		TextField stoneNum = new TextField();
		Label caterpillar = new Label("Caterpillar");
		TextField caterpillarNum = new TextField();
		Label beetle = new Label("Beetle");
		TextField beetleNum = new TextField();
		Label leaf = new Label("Leaf");
		TextField leafNum = new TextField();
		submitText(beeNum,"bee",daisyNum);
		submitText(daisyNum,"daisy",stoneNum);
		submitText(stoneNum,"stone",caterpillarNum);
		submitText(caterpillarNum,"caterpillar",beetleNum);
		submitText(beetleNum,"beetle",leafNum);
		submitText(leafNum,"leaf",beeNum);

		vbox.getChildren().addAll(title, bee, beeNum, daisy, daisyNum,stone,stoneNum,caterpillar, caterpillarNum,beetle, beetleNum,leaf, leafNum);
		StackPane pane = new StackPane();
		pane.setPrefWidth(600);
		pane.setPrefHeight(600);
		pane.getChildren().add(vbox);
		pane.setAlignment(Pos.CENTER);
		vbox.setAlignment(Pos.CENTER);
		return pane;
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Pane pane = new Pane();
		pane.setPrefWidth(600);
		pane.setPrefHeight(600);

		

	    pane.setStyle("-fx-background-color: forestGreen;");

		pane.getChildren().add(world);
		
		



		Button playButton = new Button("Play");
		playButton.setLayoutX(100);

		Button stopButton = new Button("Stop");
	
		HBox buttons = new HBox();
		buttons.getChildren().addAll(playButton,stopButton);
		

		VBox root = new VBox();
		root.getChildren().addAll(pane, buttons);
		Scene scene = new Scene(root);
		
		Button createButton = new Button("Create");
		
		createButton.setOnAction((ActionEvent event) ->{
			primaryStage.setScene(scene);
		});
		
		VBox createRoot = new VBox();
        
        Pane createPane = this.createPane();
		
        createRoot.getChildren().addAll(createPane, createButton);
		
        Scene createScene = new Scene(createRoot);
        primaryStage.setScene(createScene);
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
        
		KeyFrame frame1 = new KeyFrame(Duration.millis(2000), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				addBee(1);
				addCaterpillar(2);
				addBeetle(1);
				addLeaf(2);
				addDaisy(2);
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
