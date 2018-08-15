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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
/**
 * This is the user interface of the project. It will create a stage for the bug world, which has two parts. 
 * The left part is to control the bug world. It can add specific number entities into the bug world. It has play, stop and clear buttons.
 * The right part is to show the bug
 * @author Dantong Huang
 *
 */
public class PaneController extends Application {
	
	private World world = new World(600, 600);
	private Image beeImage= new Image(getClass().getResourceAsStream("bee.png"));
	private Image daisyImage= new Image(getClass().getResourceAsStream("daisy.png"));
	private Image stoneImage= new Image(getClass().getResourceAsStream("stone.png"));
	private Image caterpillarImage= new Image(getClass().getResourceAsStream("caterpillar.png"));
	private Image beetleImage= new Image(getClass().getResourceAsStream("beetle.png"));
	private Image leafImage= new Image(getClass().getResourceAsStream("leaf.png"));
	
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
	
	public ImageView resizeImage(Image image) {
		ImageView img = new ImageView(image);
		img.setFitHeight(20);
		img.setFitWidth(20);
		return img;
	}
	
	public StackPane createPane() {
		VBox vbox = new VBox();
		Label title = new Label("Create BugWorld");
		Label bee = new Label("", resizeImage(beeImage));
		TextField beeNum = new TextField();
		beeNum.setFocusTraversable(true);
		Label daisy = new Label("",resizeImage(daisyImage));
		TextField daisyNum = new TextField();
		Label stone = new Label("",resizeImage(stoneImage));
		TextField stoneNum = new TextField();
		Label caterpillar = new Label("",resizeImage(caterpillarImage));
		TextField caterpillarNum = new TextField();
		Label beetle = new Label("",resizeImage(beetleImage));
		TextField beetleNum = new TextField();
		Label leaf = new Label("",resizeImage(leafImage));
		TextField leafNum = new TextField();
		submitText(beeNum,"bee",daisyNum);
		submitText(daisyNum,"daisy",stoneNum);
		submitText(stoneNum,"stone",caterpillarNum);
		submitText(caterpillarNum,"caterpillar",beetleNum);
		submitText(beetleNum,"beetle",leafNum);
		submitText(leafNum,"leaf",beeNum);

		vbox.getChildren().addAll(title, bee, beeNum, daisy, daisyNum,stone,stoneNum,caterpillar, caterpillarNum,beetle, beetleNum,leaf, leafNum);
		StackPane pane = new StackPane();
		pane.setPrefWidth(150);
		pane.setPrefHeight(100);
		pane.getChildren().add(vbox);
		pane.setAlignment(Pos.CENTER);
		vbox.setAlignment(Pos.CENTER);
		return pane;
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
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		Pane bugWorld = new Pane();// to present the world
		bugWorld.setPrefWidth(600);
		bugWorld.setPrefHeight(600);
		bugWorld.setStyle("-fx-background-color: forestGreen;");
		bugWorld.getChildren().add(world);//add world into the bugWorld
		
		Button playButton = new Button("Play");
		Button stopButton = new Button("Stop");
		Button clearButton = new Button("Clear");

		HBox buttons = new HBox();
		buttons.getChildren().addAll(playButton, stopButton, clearButton);
	    buttons.setSpacing(20);
	    buttons.setAlignment(Pos.CENTER);
		VBox controller = new VBox();
		controller.setSpacing(300);
		StackPane createPane = createPane();
		controller.getChildren().addAll(createPane,buttons);// add createPane, stop button, play button
		
		HBox root = new HBox();
	    root.getChildren().addAll(controller,bugWorld);//add buttons and pane into root
	    
	    Scene scene = new Scene(root);
	    primaryStage.setScene(scene);
		primaryStage.setTitle("Bug world");
		primaryStage.show();
		
		
		
		//Animation section
		KeyFrame frame = new KeyFrame(Duration.millis(150), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				world.tickBug();//
				world.removeDead();//
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
		
		clearButton.setOnAction((ActionEvent event) ->{
			world.clearEntities();
		});
	}

	public static void main(String[] args) {
		Application.launch(args);

	}
}
