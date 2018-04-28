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
import javafx.util.Duration;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;

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
	    
	    //Creating Translate Transition
	      TranslateTransition tt1 = new TranslateTransition();
	      tt1.setDuration(Duration.millis(1000));
	      tt1.setNode(imageView1);
	      tt1.setByX(28);
	      
	      TranslateTransition tt2 = new TranslateTransition();
	      tt2.setDuration(Duration.millis(1000));
	      tt2.setNode(imageView1);
	      tt2.setByY(-112);
	      
	      TranslateTransition tt3 = new TranslateTransition();
	      tt3.setDuration(Duration.millis(1000));
	      tt3.setNode(imageView1);
	      tt3.setByX(224);
	      
	      TranslateTransition tt4 = new TranslateTransition();
	      tt4.setDuration(Duration.millis(1000));
	      tt4.setNode(imageView1);
	      tt4.setByY(-56);
	      
	      TranslateTransition tt5 = new TranslateTransition();
	      tt5.setDuration(Duration.millis(1000));
	      tt5.setNode(imageView1);
	      tt5.setByX(56);
	      
	      TranslateTransition tt6 = new TranslateTransition();
	      tt6.setDuration(Duration.millis(1000));
	      tt6.setNode(imageView1);
	      tt6.setByY(224);
	      
	      TranslateTransition tt7 = new TranslateTransition();
	      tt7.setDuration(Duration.millis(1000));
	      tt7.setNode(imageView1);
	      tt7.setByX(56);
	      
	      TranslateTransition tt8 = new TranslateTransition();
	      tt8.setDuration(Duration.millis(1000));
	      tt8.setNode(imageView1);
	      tt8.setByY(-112);
	      
	      TranslateTransition tt9 = new TranslateTransition();
	      tt9.setDuration(Duration.millis(1000));
	      tt9.setNode(imageView1);
	      tt9.setByX(119);
	      
	      TranslateTransition tt10 = new TranslateTransition();
	      tt10.setDuration(Duration.millis(1000));
	      tt10.setNode(imageView1);
	      tt10.setByY(-112);
	      
	      TranslateTransition tt11 = new TranslateTransition();
	      tt11.setDuration(Duration.millis(1000));
	      tt11.setNode(imageView1);
	      tt11.setByX(56);
	      
	      TranslateTransition tt12 = new TranslateTransition();
	      tt12.setDuration(Duration.millis(1000));
	      tt12.setNode(imageView1);
	      tt12.setByY(154);
	      
	      TranslateTransition tt13 = new TranslateTransition();
	      tt13.setDuration(Duration.millis(1000));
	      tt13.setNode(imageView1);
	      tt13.setByX(20);
	      
	      SequentialTransition seqT = new SequentialTransition (tt1,tt2,tt3,tt4,tt5,tt6,tt7,tt8,tt9,tt10,tt11,tt12,tt13);
	      seqT.play();

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
		System.out.println("Robot X Pos: "+imageView1.getX());
		System.out.println("Robot Y Pos: "+imageView1.getY());
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
