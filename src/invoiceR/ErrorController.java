package invoiceR;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ErrorController {

    @FXML
    private Button emptyFieldButton;

    @FXML
    private Button dataBaseConnectionErrorButton;

    public void closeEmptyFieldError(javafx.event.ActionEvent actionEvent) {
        Stage stage = (Stage) emptyFieldButton.getScene().getWindow();
        stage.close();
    }

    public void closeDatabaseError(ActionEvent actionEvent) {
        Stage stage = (Stage) dataBaseConnectionErrorButton.getScene().getWindow();
        stage.close();
    }
}
