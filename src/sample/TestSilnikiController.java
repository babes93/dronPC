package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Created by Patryk on 2016-07-11.
 */
public class TestSilnikiController implements Initializable {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField PWM_S2_TEST;

    @FXML
    private TextField PWM_S4_TEST;

    @FXML
    private Button UstawPWMButton;

    @FXML
    private Button StopTestSilniki;

    @FXML
    private TextField PWM_S3_TEST;

    @FXML
    private TextField PWM_S1_TEST;

    private RS232 DronPort = new RS232();

    private Buffor buf_zapis = new Buffor();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        PWM_S1_TEST.setText("0");
        PWM_S2_TEST.setText("0");
        PWM_S3_TEST.setText("0");
        PWM_S4_TEST.setText("0");

        UstawPWMButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                short []tmp = new short[6];
                tmp[0]=Short.decode(PWM_S1_TEST.getText());
                tmp[1]=Short.decode(PWM_S2_TEST.getText());
                tmp[2]=Short.decode(PWM_S3_TEST.getText());
                tmp[3]=Short.decode(PWM_S4_TEST.getText());

                DronPort.ZapiszDaneWDronie(buf_zapis.WRITE_PWM_TEST,tmp);
            }
        });

        StopTestSilniki.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              //  start = 0;
                short []tmp = new short[6];
             //   KonsolaDane.add(CzasSystemowy.format(new Date()) + ">STOP AWARYJNY - wyzerowanie PWM i REG");
                DronPort.ZapiszDaneWDronie(buf_zapis.STOP_AWARYJNY,tmp);
                /*
                buf_zapis.ClearBufor();
                buf_zapis.setADDR((byte) 0xAA);
                buf_zapis.setCommandRead(buf_zapis.STOP_AWARYJNY);
                zapis2 = 1;
                start = 1;
                */
            }
        });
    }
}
