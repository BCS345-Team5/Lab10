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
    
    private ImageView imageView1;
    private ImageView imageView2;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Image alien = new Image("robot.jpg");
		Image maze= new Image("maze.png");
	        
	    imageView1 = new ImageView(alien);
	    imageView1.setX(0);
	    imageView1.setY(285);
	    
	    imageView2 = new ImageView(maze);
	    imageView2.setX(0);
	    imageView2.setY(25);
	    
	        
	    Group root = new Group(imageView2,imageView1);

	    Scene scene = new Scene(root, 600, 450, Color.WHITE);
	    scene.setOnKeyPressed(this::processKeyPress);
		
		Button resetPos = new Button("Reset");
		root.getChildren().add(resetPos);
		resetPos.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				imageView1.setX(0);
			    imageView1.setY(285);
			}
		});
		
		Stage pu= new Stage();
		pu.setTitle("MAZE");
		pu.setScene(scene);
		pu.show();
	}
	
	public void processKeyPress(KeyEvent event)
    {
        switch (event.getCode())
        {
        		//Add check at each direction to check if theres a wall where its trying to move
            case UP:
                imageView1.setY(imageView1.getY() - JUMP);
                break;
            case DOWN:
                imageView1.setY(imageView1.getY() + JUMP);
                break;
            case RIGHT:
                imageView1.setX(imageView1.getX() + JUMP);
                break;
            case LEFT:
                imageView1.setX(imageView1.getX() - JUMP);
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
