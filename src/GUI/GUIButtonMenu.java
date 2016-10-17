package GUI;

import FrontEndInternalAPI.ButtonMenu;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by Delia on 10/15/2016.
 */
public class GUIButtonMenu implements ButtonMenu{
    private Pane window;
    private Paint border;
    private Rectangle backdrop;
    private OptionsPopup myOptions;
    private String overButton = "-fx-background-color: linear-gradient(#0079b3, #00110e);" +
            "-fx-background-radius: 20;" +
            "-fx-text-fill: white;";
    private String buttonFill = "-fx-background-color: linear-gradient(#00110e, #0079b3);" +
            "-fx-background-radius: 20;" +
            "-fx-text-fill: white;";

    public GUIButtonMenu(Pane p, Paint borderColor){
        this.window = p;
        this.border = borderColor;
        drawButtonMenu();
        addTextLabel();
        addButtons();
    }

    private void drawButtonMenu(){
        backdrop = new Rectangle(1580, 90, Color.WHITE);
        backdrop.setStroke(border);
        backdrop.setStrokeWidth(5);
        backdrop.setTranslateY(10);
        backdrop.setTranslateX(10);
        backdrop.opacityProperty().setValue(0.5);
//        backdrop.setOnMouseMoved(e -> handle(e));
        backdrop.setOnMouseEntered(e -> backdrop.opacityProperty().setValue(0.8));
        backdrop.setOnMouseExited(e -> backdrop.opacityProperty().setValue(0.5));
        window.getChildren().add(backdrop);
    }

    private void addTextLabel(){
        Text label = new Text("Options");
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        label.setOnMouseEntered(e -> backdrop.opacityProperty().setValue(0.8));
        label.setTranslateX(20);
        label.setTranslateY(30);
        window.getChildren().add(label);
    }

    public void addButtons(){ 
        Button play = newButton("PLAY", 30, 40);
        Button pause = newButton("PAUSE", 130, 40);
        Button stop = newButton("STOP", 240, 40);
        Button options = newButton("OPTIONS", 340, 50);
        options.setOnMouseClicked(e -> optionsHandler());
        Button help = newButton("HELP", 420, 50);
        window.getChildren().add(play);
        window.getChildren().add(pause);
        window.getChildren().add(stop);
        window.getChildren().add(options);
        window.getChildren().add(help);
        
    }

    @Override
    public Button newButton(String text, int x, int y) {
       
        ImageView newImage = loadImage(text);
        Button newButton;
        if(text.equals("PLAY")){
            newButton = new Button(text, newImage);
        }
        else if(text.equals("PAUSE")){
            newButton = new Button(text, newImage);  
        }
        else if(text.equals("STOP")){
            newButton = new Button(text, newImage);
        }
        else
        {
            newButton = new Button(text);
        }
        newButton.setStyle(overButton);
        newButton.setOnMouseEntered(e -> {
            newButton.setStyle(buttonFill);
            backdrop.opacityProperty().setValue(0.8);
        });
        newButton.setOnMouseExited(e -> newButton.setStyle(overButton));
        newButton.setTranslateX(x);
        newButton.setTranslateY(y);
        return newButton;
    }

    private ImageView loadImage (String text) {
        Image newImage = new Image(getClass().getClassLoader()
                                   .getResourceAsStream("images/play.png"));
        if(text.equals("PLAY")){
         newImage = new Image(getClass().getClassLoader()
                                     .getResourceAsStream("images/play.png"));
        }
        else if(text.equals("PAUSE")){
             newImage = new Image(getClass().getClassLoader()
                                       .getResourceAsStream("images/pause.png"));
        }
        else if(text.equals("STOP")){
             newImage = new Image(getClass().getClassLoader()
                                       .getResourceAsStream("images/stop.png"));
        }
        else{
        }
        ImageView imgV = new ImageView(newImage);
        imgV.setFitWidth(40);
        imgV.setFitHeight(40);
        return imgV;
    }

    public void setDefaults(Color paint, String background, String turtle, String language){

    }

    private void optionsHandler(){
        Stage s = new Stage();
        myOptions = new OptionsPopup(s);
        myOptions.initPopup();
    }

    public Rectangle getBackdrop(){
        return backdrop;
    }
}