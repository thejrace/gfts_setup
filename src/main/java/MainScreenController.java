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

    /**
     * Initialize UI actions
     *
     * @param location
     * @param resources
     */
    public void initialize(URL location, ResourceBundle resources) {
        // install action
        uiInstallBtn.setOnMouseClicked( ev -> {
            String staticPrefixDirValue = uiStaticDirPrefixInput.getText();
            if( !staticPrefixDirValue.equals("") ) {
                disableInputs();
                refreshNotfs();
                Setup setup = new Setup(staticPrefixDirValue);
                uiInstallNotf.textProperty().bind(setup.getStatusProp());
                setup.action(new ActionCallback() {
                    @Override
                    public void success(String msg) {
                        enableInputs();
                    }
                    @Override
                    public void error(String msg) {
                        enableInputs();
                    }
                });
            }

        });

        // uninstall action
        uiUninstallBtn.setOnMouseClicked( ev -> {
            String staticPrefixDirValue = uiStaticDirPrefixInput.getText();
            if( !staticPrefixDirValue.equals("") ){
                disableInputs();
                refreshNotfs();
                Uninstall uninstall = new Uninstall();
                uiUninstallNotf.textProperty().bind(uninstall.getStatusProp());
                uninstall.action(staticPrefixDirValue, new ActionCallback() {
                    @Override
                    public void success(String msg) {
                        enableInputs();
                    }
                    @Override
                    public void error(String msg) {
                        enableInputs();
                    }
                });
            }
        });

    }

    /**
     * Refresh notf labels
     */
    private void refreshNotfs(){
        uiUninstallNotf.textProperty().unbind();
        uiUninstallNotf.setText("");
        uiInstallNotf.textProperty().unbind();
        uiInstallNotf.setText("");
        uiDiagNotf.textProperty().unbind();
        uiDiagNotf.setText("");
    }

    /**
     * Disable all buttons
     */
    private void disableInputs(){
        uiInstallBtn.setDisable(true);
        uiStartDiagBtn.setDisable(true);
        uiUninstallBtn.setDisable(true);
        uiStaticDirPrefixInput.setDisable(true);
    }

    /**
     * Enable all buttons
     */
    private void enableInputs(){
        uiInstallBtn.setDisable(false);
        uiStartDiagBtn.setDisable(false);
        uiUninstallBtn.setDisable(false);
        uiStaticDirPrefixInput.setDisable(false);
    }

}
