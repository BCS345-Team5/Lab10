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
    String pointWhite,pointBlue;
    
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Image robot = new Image("robot.jpg");
		//System.out.println("Robot Width: "+robot.getWidth());
		//System.out.println("Robot Height: "+robot.getHeight());
		maze= new Image("maze.png");
		
		reader = maze.getPixelReader();
		
		pointWhite = (""+reader.getColor(10,10));
		pointBlue = (""+reader.getColor(20,50));
		
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
		String colorUP1=(""+Color.web(""+reader.getColor((int)imageView1.getX()+20,(int)imageView1.getY()-30)));
		String colorUP2=(""+Color.web(""+reader.getColor((int)imageView1.getX()+5,(int)imageView1.getY()-30)));
		String colorDOWN1=(""+Color.web(""+reader.getColor((int)imageView1.getX()+20,(int)imageView1.getY()+5)));
		String colorDOWN2=(""+Color.web(""+reader.getColor((int)imageView1.getX()+5,(int)imageView1.getY()+5)));
		
        switch (event.getCode())
        {
        		//Add check at each direction to check if theres a wall where its trying to move
            case UP:       
            	if(pointWhite.equals(colorUP1)&& pointWhite.equals(colorUP2)) {
            	//if (true) {
            		imageView1.setY(imageView1.getY() - JUMP);
            		//System.out.println(Color.web(""+reader.getColor(10,10)));
            		//System.out.println("Inside the if statement\nColor: "+Color.web(""+reader.getColor((int)imageView1.getX()+25,(int)imageView1.getY()-30)));

            	}
            	else {
            		//System.out.println("Color1: "+pointWhite);
            		//System.out.println("Color2: "+colorUP1);
            		//System.out.println("Robot X Pos: "+imageView1.getX());
            		//System.out.println("Robot Y Pos: "+imageView1.getY());
            		//System.out.println("Outside the if statement\nColor: "+Color.web(""+reader.getColor((int)imageView1.getX()+25,(int)imageView1.getY()-30)));
            	}
            		//imageView1.setY(imageView1.getY() - JUMP);
                break;
            case DOWN:
            	if(pointWhite.equals(colorDOWN1)&& pointWhite.equals(colorDOWN2)) {
            		imageView1.setY(imageView1.getY() + JUMP);
            	}
            	else {
            		//System.out.println("Outside the if statement\nColor: "+Color.web(""+reader.getColor((int)imageView1.getX()+15,(int)imageView1.getY()-30)));
               	}
                break;
            case RIGHT:
            	if(reader.getColor((int)imageView1.getX()+30,(int)imageView1.getY()).equals(Color.WHITE) && 
            			reader.getColor((int)imageView1.getX()+30,(int)imageView1.getY()-25).equals(Color.WHITE) &&
            				imageView1.getX()<570) {
            	//if(true) {
            		imageView1.setX(imageView1.getX() + JUMP);
            	}
            	else {
            		//System.out.println("Outside the if statement\nColor: "+Color.web(""+reader.getColor((int)imageView1.getX()+15,(int)imageView1.getY()-30)));
               	}
                break;
            case LEFT:
            	if(reader.getColor((int)imageView1.getX()-5,(int)imageView1.getY()).equals(Color.WHITE) && 
            			reader.getColor((int)imageView1.getX()-5,(int)imageView1.getY()-25).equals(Color.WHITE) &&
            				imageView1.getX()>15) {
            	//if(true) {
                	imageView1.setX(imageView1.getX() - JUMP);
            	}
                else {
            		//System.out.println("Outside the if statement\nColor: "+Color.web(""+reader.getColor((int)imageView1.getX()+15,(int)imageView1.getY()-30)));
               	}
                break;
            default:
                break;  // do nothing if it's not an arrow key
        }
        checkWin();
    }
	public void checkWin(){
		if(imageView1.getX()>=568 && (imageView1.getY()>=264 && imageView1.getY()<=278)) {
			win.setText("You won! Press reset to try again.");
		}
    }
    
    
    public static void main(String[] args)
    {
        launch(args);
    }
}
