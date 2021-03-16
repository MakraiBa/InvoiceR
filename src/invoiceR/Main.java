package invoiceR;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {
    Stage alertStage = new Stage();
    @FXML
    private Button startbutton;

    @FXML
    void openNextScene(ActionEvent event) throws IOException {
        Parent alert = FXMLLoader.load(getClass().getResource("scenes/productStage.fxml"));
        alertStage.setScene(new Scene(alert));
        alertStage.setResizable(false);
        alertStage.show();
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("scenes/mainscene.fxml"));
        primaryStage.setScene(new Scene(root));
        //primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
