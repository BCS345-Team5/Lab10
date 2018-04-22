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
    private Label win;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Image robot = new Image("robot.jpg");
		//System.out.println("Robot Width: "+robot.getWidth());
		//System.out.println("Robot Height: "+robot.getHeight());
		Image maze= new Image("maze.png");
		
		win=new Label();
		win.setTranslateX(50);
		HBox h1=new HBox(win);
	        
	    imageView1 = new ImageView(robot);
	    imageView1.setX(15);
	    imageView1.setY(285);
	    
	    imageView2 = new ImageView(maze);
	    imageView2.setX(0);
	    imageView2.setY(25);
	    
	        
	    Group root = new Group(imageView2,imageView1,h1);

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
		//System.out.println("Robot X Pos: "+imageView1.getX());
		//System.out.println("Robot Y Pos: "+imageView1.getY());
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
        checkWin();
    }
	public void checkWin(){
		if(imageView1.getX()==575 && (imageView1.getY()==265 || imageView1.getY()==275)) {
			win.setText("You won! Press reset to try again.");
		}
    }
    
    
    public static void main(String[] args)
    {
        launch(args);
    }
}
