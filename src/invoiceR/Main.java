package invoiceR;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class Main extends Application {

    @FXML
    private Button startbutton;

    @FXML
    void openNextScene(ActionEvent event) throws IOException {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("scenes/splashStage.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        Image icon=new Image(getClass().getResourceAsStream("images/invoice.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
