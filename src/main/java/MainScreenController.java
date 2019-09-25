/*
 *  Gitas Fleet Tracking System Setup 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
import interfaces.ActionCallback;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {

    @FXML
    private Button uiInstallBtn;
    @FXML
    private TextField uiStaticDirPrefixInput;
    @FXML
    private Button uiStartDiagBtn;
    @FXML
    private Button uiUninstallBtn;
    @FXML
    private Label uiDiagNotf;
    @FXML
    private Label uiInstallNotf;
    @FXML
    private Label uiUninstallNotf;

    public void initialize(URL location, ResourceBundle resources) {

        uiInstallBtn.setOnMouseClicked( ev -> {
            Setup setup = new Setup(uiStaticDirPrefixInput.getText());
            setup.action(new ActionCallback() {
                @Override
                public void success(String msg) {

                }

                @Override
                public void error(String msg) {

                }
            });
        });

    }

}
