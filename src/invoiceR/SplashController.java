package invoiceR;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SplashController implements Initializable {
    Connect loadData= new Connect();

    @FXML
    private AnchorPane loadingPane;

    @FXML
    private Label loadingLabel;

    @FXML
    private Button closeSplashSceneButton;

    @FXML
    void closeSplashScreen(ActionEvent event) {
        Stage stage = (Stage) closeSplashSceneButton.getScene().getWindow();
        System.exit(0);
    }

    class ShowSplashScreen extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(600);
                Platform.runLater(() -> {
                    Stage stage = new Stage();
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("scenes/mainStage.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(SplashController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setMaximized(true);
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.setMaximized(true);
                    stage.show();
                    loadingPane.getScene().getWindow().hide();
                });
            } catch (InterruptedException ex) {
                Logger.getLogger(SplashController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData.getProducts();
        new ShowSplashScreen().start();
        FadeTransition ft = new FadeTransition(Duration.millis(800), loadingLabel);
        ft.setFromValue(1.0);
        ft.setToValue(0.3);
        ft.setCycleCount(40);
        ft.setAutoReverse(true);
        ft.play();
    }
}