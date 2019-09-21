/*
 *  Gitas Fleet Tracking System Setup 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
import javafx.application.Application;
import javafx.stage.Stage;
import org.json.JSONObject;
import utils.Common;

import java.io.File;

public class Main extends Application {

    public void start(Stage primaryStage) throws Exception {

        try {
            String configFile = getParameters().getRaw().get(0);

            SharedConfig.DATA = new JSONObject(Common.readJSONFile(new File(configFile)));
        } catch( IndexOutOfBoundsException e ){
            System.out.println("Setup config missing!");
            return;
        }

        MainScreen mainScreen = new MainScreen();
        mainScreen.start(new Stage());

    }

    public static void main(String[] args) {
        launch(args);
    }

}
