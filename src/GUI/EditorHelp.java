package GUI;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by Delia on 10/22/2016.
 */
public class EditorHelp extends HelpMenu {
    private String instructions = "Start by clicking on the text field and type an instruction \nin the editor. "+
        "\nPress the Run button to start the execution.";

    /**
     *
     * @param s
     */
    public EditorHelp(Stage s) {
        super(s);
    }

    /**
     *
     */
    public void addNodes(){
        getWindow().getChildren().add(addRectangle());
        getWindow().getChildren().add(addTip(instructions, 110, 110));
    }

    /**
     *
     * @param text
     * @param x
     * @param y
     * @return
     */
    public Text addTip(String text, int x, int y){
        Text instructionText = new Text(text);
        instructionText.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        instructionText.setFill(Color.WHITE);
        instructionText.setTranslateX(x);
        instructionText.setTranslateY(y);
        return instructionText;
    }
}
