import java.io.IOException;
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainDriver extends Application{

	public final static int JUMP = 10;
    
    private ImageView imageView;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Image alien = new Image("robot.jpg");
	        
	    imageView = new ImageView(alien);
	    imageView.setX(20);
	    imageView.setY(20);
	        
	    Group root = new Group(imageView);

	    Scene scene = new Scene(root, 400, 200, Color.BLACK);
	    scene.setOnKeyPressed(this::processKeyPress);
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(5);
		
		Button resetPos = new Button("Reset");
		GridPane.setConstraints(resetPos, 2, 1);
		grid.getChildren().add(resetPos);
		resetPos.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				imageView.setX(20);
			    imageView.setY(20);
			}
		});
		
		Stage pu= new Stage();
		pu.setTitle("MAZE");
		Scene mainScene = new Scene(grid, 400, 300);
		pu.setScene(scene);
		pu.show();
	}
	
	public void processKeyPress(KeyEvent event)
    {
        switch (event.getCode())
        {
        		//Add check at each direction to check if theres a wall where its trying to move
            case UP:
                imageView.setY(imageView.getY() - JUMP);
                break;
            case DOWN:
                imageView.setY(imageView.getY() + JUMP);
                break;
            case RIGHT:
                imageView.setX(imageView.getX() + JUMP);
                break;
            case LEFT:
                imageView.setX(imageView.getX() - JUMP);
                break;
            default:
                break;  // do nothing if it's not an arrow key
        }
    }
    
    public static void main(String[] args)
    {
        launch(args);
    }
}
