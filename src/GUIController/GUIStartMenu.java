package GUIController;

import FrontEndExternalAPI.StartMenu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GUIStartMenu implements StartMenu {

    private static final int START_MENU_WIDTH = 700;
    private static final int START_MENU_HEIGHT = 600;
    private static final int DROP_DOWN_X_VALUE = 400;
    private Stage stage, stageNew;
    private ColorPicker penColor, colorB;
    private Spinner<ImageView> backgroundSpinner, turtleSpinner;
    private ComboBox backgroundBox, turtleBox;
    private Pane startWindow;
    private static final LinearGradient textAndBoxGradient = new LinearGradient(0d, 1d, 1d, 0d, true,
            CycleMethod.NO_CYCLE,
            new Stop[]{
                    new Stop(0, Color.WHITE),
                    new Stop(0.15, Color.HONEYDEW),
                    new Stop(0.3, Color.LIGHTBLUE),
                    new Stop(0.45, Color.SLATEBLUE),
                    new Stop(0.6, Color.LIGHTBLUE),
                    new Stop(0.75, Color.HONEYDEW),
                    new Stop(1, Color.WHITE)
            });
    private ObservableList<String> backgroundOptions =
            FXCollections.observableArrayList(
                    "Circuits",
                    "Floating Cubes",
                    "Nebula",
                    "Metal Sheets",
                    "Spinning Screens"
            );
    private ObservableList<String> turtleOptions =
            FXCollections.observableArrayList(
                    "Drake",
                    "Heart"
            );
    private String overButton = "-fx-background-color: linear-gradient(#0079b3, #00110e);" +
            "-fx-background-radius: 20;" +
            "-fx-font: 35 arial;" +
            "-fx-text-fill: white;";
    private String buttonFill = "-fx-background-color: linear-gradient(#00110e, #0079b3);" +
            "-fx-background-radius: 20;" +
            "-fx-font: 35 arial;" +
            "-fx-text-fill: white;";

    public GUIStartMenu(Stage s) {
        stage = s;
    }

    public Parent setUpWindow(){
        startWindow = new Pane();
        startWindow.setPrefSize(START_MENU_WIDTH, START_MENU_HEIGHT);
        Image background = new Image(getClass().getClassLoader()
                .getResourceAsStream("background.jpg"));
        ImageView backgroundImageMainScreen = new ImageView(background);
        backgroundImageMainScreen.setFitWidth(START_MENU_WIDTH + 50);
        backgroundImageMainScreen.setFitHeight(START_MENU_HEIGHT);
        startWindow.getChildren().add(backgroundImageMainScreen);

        BigNameText title = new BigNameText("Welcome to \n\tSLogo");
        title.setTranslateX(125);
        title.setTranslateY(125);
        startWindow.getChildren().add(title);
        selectPenColor();
        selectBackgroundImage();
        selectTurtleImage();
        addLaunchButton();

        return startWindow;
    }

    @Override
    public void setParameters() {

    }

    private void selectPenColor(){
        penColor = generateColorPicker(Color.CORAL, DROP_DOWN_X_VALUE, 300);
        Label penLabel = generateLabel("Choose pen color", 125, 300);
        startWindow.getChildren().add(penColor);
        startWindow.getChildren().add(penLabel);
    }

    private Label generateLabel(String text, int x, int y){
        Label penLabel = new Label(text);
        penLabel.setTranslateX(x);
        penLabel.setTranslateY(y);
        penLabel.setTextFill(Color.WHITE);
        penLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        return penLabel;
    }

    private ColorPicker generateColorPicker(Color defaultColor, int x, int y){
        penColor = new ColorPicker();
        penColor.setTranslateX(x);
        penColor.setTranslateY(y);
        penColor.setValue(defaultColor);
        return penColor;
    }

    private void selectBackgroundImage(){
        System.setProperty("glass.accessible.force", "false");
        backgroundBox = new ComboBox(backgroundOptions);
        backgroundBox.setTranslateX(DROP_DOWN_X_VALUE);
        backgroundBox.setTranslateY(350);
        Label backgroundLabel = generateLabel("Select background image", 125, 350);
        startWindow.getChildren().add(backgroundLabel);
        startWindow.getChildren().add(backgroundBox);
    }

    private void selectTurtleImage(){
        System.setProperty("glass.accessible.force", "false");
        turtleBox = new ComboBox(turtleOptions);
        turtleBox.setTranslateX(DROP_DOWN_X_VALUE);
        turtleBox.setTranslateY(400);
        Label turtleLabel = generateLabel("Select turtle image", 125, 400);
        startWindow.getChildren().add(turtleLabel);
        startWindow.getChildren().add(turtleBox);

    }

    private void addLaunchButton(){
        Button newButton = new Button("Launch SLogo");
        newButton.setStyle(buttonFill);
        newButton.setOnMouseEntered(e -> newButton.setStyle(buttonFill));
        newButton.setOnMouseExited(e -> newButton.setStyle(overButton));
        newButton.setTranslateX(300);
        newButton.setTranslateY(450);
        startWindow.getChildren().add(newButton);
    }

    @Override
    public void initIDE() {

    }

    private static class BigNameText extends StackPane {
        /**
         * @param Name
         */
        public BigNameText(String Name) {
            Text titleText = new Text(Name);
            titleText.setFont(Font.font("Verdana", FontWeight.BOLD, 60));
            titleText.setFill(textAndBoxGradient);
            getChildren().add(titleText);
        }
    }
}