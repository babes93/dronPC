package sample;

import javafx.fxml.Initializable;



import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;


/**
 * Created by Patryk on 2016-07-11.
 */
public class InformacjeController implements Initializable {

    @FXML
    private TextArea InfoText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        InfoText.setEditable(false);
        InfoText.appendText("\nInfo\n");
    }
}
