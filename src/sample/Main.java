/* NOT COMPLETED/WIP
Bugs, issues and information:
1) Some parts of the code were adapted with help from online sources, other students and past projects.
Alterations were made to these parts to show knowledge of how the code works and proficiency.
2) Unable to fully implement Union find correctly. Smaller sections of pixels are still seen as whole groups.
3) Implementation of an intensity slider was not completed
4) Testing the code was unable to be completed, however, attempts were made to change this.
5) The application works mostly as intended. Some issues are still persistent and these issues are noted in comments.
*/



package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * @author Mathew Peder
 *
 * Applied Computing - 20073231
 */

public class Main extends Application
{
    private Stage primaryStage;

    //Sets the stage and title of the app
    @Override
    public void start(Stage primaryStage) throws IOException
    {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Sheep Recognition App");
        showMainView();
    }

    //Loads the FXML file and creates a new scene in the stage. Also loads initial scenebuilder as a new window
    private void showMainView() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("sample.fxml"));
        BorderPane mainLayout = loader.load();
        Scene scene;
        scene = new Scene( mainLayout );
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}






