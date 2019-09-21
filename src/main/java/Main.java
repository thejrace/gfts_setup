/*
 *  Gitas Fleet Tracking System Setup 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage primaryStage) throws Exception {

        MainScreen mainScreen = new MainScreen();
        mainScreen.start(new Stage());

    }

    public static void main(String[] args) {
        launch(args);
    }

}
