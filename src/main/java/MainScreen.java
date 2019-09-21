/*
 *  Gitas Fleet Tracking System Setup 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainScreen extends Application {

    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/ui.fxml"));
            Parent content = loader.load();
            primaryStage.setTitle("Gitas FTS Obarey Setup");
            primaryStage.getIcons().add(new Image(getClass().getResource("/img/gpts_setup_ico.png").toExternalForm()));
            primaryStage.setScene( new Scene(content, 600, 400 ));
            primaryStage.show();
        } catch( Exception e ){
            e.printStackTrace();
        }
    }
}
