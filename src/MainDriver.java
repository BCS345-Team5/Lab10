import java.io.IOException;
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainDriver extends Application{

	public final static int JUMP = 7;
    
    private ImageView imageView1;
    private ImageView imageView2;
    private Label win;
    private Group root;
    private Image maze;
    PixelReader reader;
    
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Image robot = new Image("robot.jpg");
		//System.out.println("Robot Width: "+robot.getWidth());
		//System.out.println("Robot Height: "+robot.getHeight());
		maze= new Image("maze.png");
		
		reader = maze.getPixelReader();
		
		win=new Label();
		win.setTranslateX(50);
		HBox h1=new HBox(win);
	        
	    imageView1 = new ImageView(robot);
	    imageView1.setX(15);
	    imageView1.setY(285);
	    
	    imageView2 = new ImageView(maze);
	    imageView2.setX(0);
	    imageView2.setY(25);
	    
	        
	    root = new Group(imageView2,imageView1,h1);

	    Scene scene = new Scene(root, 600, 450, Color.WHITE);
	    scene.setOnKeyPressed(this::processKeyPress);
		
		Button resetPos = new Button("Reset");
		root.getChildren().add(resetPos);
		resetPos.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				imageView1.setX(15);
			    imageView1.setY(285);
			    win.setText("");
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
            	//if((reader.getColor((int)imageView1.getX()+25,(int)imageView1.getY()-30).toString()==Color.web("0xffffffff").toString())&&(reader.getColor((int)imageView1.getX()+50,(int)imageView1.getY()-30).toString()==Color.web("0xffffffff").toString())) {
            	if(true) {
            		imageView1.setY(imageView1.getY() - JUMP);
            		System.out.println("Inside the if statement");
            	}
            	else {
            		System.out.println("Robot X Pos: "+imageView1.getX());
            		System.out.println("Robot Y Pos: "+imageView1.getY());
            		System.out.println("Outside the if statement\nColor: "+Color.web(""+reader.getColor((int)imageView1.getX()+25,(int)imageView1.getY()-30)));
            	}
            		//imageView1.setY(imageView1.getY() - JUMP);
                break;
            case DOWN:
                imageView1.setY(imageView1.getY() + JUMP);
                break;
            case RIGHT:   
            	if(reader.getColor((int)imageView1.getX()+30,(int)imageView1.getY()).equals(Color.WHITE) && 
            			reader.getColor((int)imageView1.getX()+30,(int)imageView1.getY()-25).equals(Color.WHITE)){
                imageView1.setX(imageView1.getX() + JUMP);
            	//System.out.println((reader.getColor((int)imageView1.getX()+27,(int)imageView1.getY()).toString()));
            	}
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
