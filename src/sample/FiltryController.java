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
public class FiltryController implements Initializable {


    @FXML
    private TextField SredniaAcc;

    @FXML
    private TextField SredniaBaro;

    @FXML
    private TextField SredniaGyro;

    @FXML
    private Button ZapiszKonfiguracjaButton;

    @FXML
    private TextField AccProgGText;

    @FXML
    private TextField Komplementarny_b2;

    @FXML
    private TextField Komplementarny_b1;

    @FXML
    private TextField AccProgDText;

    @FXML
    private TextField SredniaMagnet;

    @FXML
    private Button WczytajKonfiguracjaButton;

    private Buffor buf_zapis = new Buffor();
    private RS232 DronPort = new RS232();

    private PolaKonfiguracji polaKonfiguracji = new PolaKonfiguracji();
    private PlikiXMLMetody plikiXMLTymaczoswe = new PlikiXMLMetody("tmpConf",polaKonfiguracji);

    @Override
    public void initialize(URL location, ResourceBundle resources) {



        AccProgDText.setText(polaKonfiguracji.Komplementarny_g2);
        AccProgGText.setText(polaKonfiguracji.Komplementarny_g1);
        Komplementarny_b1.setText(polaKonfiguracji.Komplementarny_b1);
        Komplementarny_b2.setText(polaKonfiguracji.Komplementarny_b2);
        SredniaAcc.setText(polaKonfiguracji.SredniaAcc);
        SredniaGyro.setText(polaKonfiguracji.SredniaGyro);
        SredniaMagnet.setText(polaKonfiguracji.SredniaMagnet);
        SredniaBaro.setText(polaKonfiguracji.SredniaBaro);

        WczytajKonfiguracjaButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                buf_zapis.ClearBufor();
                short []tmp = new short[6];
                //  KonsolaDane.add(CzasSystemowy.format(new Date()) + ">Wczytywanie Konfiguracji Filtrow");
                DronPort.ZapiszDaneWDronie(buf_zapis.READ_KONFIGURACJA_1,tmp);
                /*
                buf_zapis.setADDR((byte) 0xAA);
                buf_zapis.setCommandRead(buf_zapis.READ_KONFIGURACJA_1);
                zapis2 = 1;
                start = 1;
                */
            }
        });

        ZapiszKonfiguracjaButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean status = false;
                short[] tmps = new short[6];
                buf_zapis.ClearBufor();
                tmps[0] = Short.decode(SredniaAcc.getText());
                tmps[1] = Short.decode(SredniaGyro.getText());
                tmps[2] = Short.decode(SredniaMagnet.getText());
                tmps[3] = Short.decode(SredniaBaro.getText());
                tmps[4] = (short) (Float.valueOf(Komplementarny_b1.getText()) * 100.0);
                tmps[5] = (short) (Float.valueOf(Komplementarny_b2.getText()) * 100.0);

                for (int i = 0; i < 4; i++) {
                    if (tmps[i] <= 75 || tmps[i] >= 1) status = true;
                    else {
                        status = false;
                      //  KonsolaDane.add(CzasSystemowy.format(new Date()) + ">Wartosc sredniej musi byc w zakresie 1-75");
                    }
                }
                for (int i = 4; i < 6; i++) {
                    if (tmps[i] < 100 || tmps[i] > 0) status = true;
                    else {
                        status = false;
                      //  KonsolaDane.add(CzasSystemowy.format(new Date()) + ">Wartosc parametru b miedzy 0.01 a 0.99");
                    }

                }
                if (status) {
                   // KonsolaDane.add(CzasSystemowy.format(new Date()) + ">Zapisiywanie Konfiguracji Filtrow");
                    DronPort.ZapiszDaneWDronie(buf_zapis.WRITE_KONFIGURACJA_1,tmps);

                    polaKonfiguracji.Komplementarny_b1 = Komplementarny_b1.getText();
                    polaKonfiguracji.Komplementarny_b2 = Komplementarny_b2.getText();
                    polaKonfiguracji.Komplementarny_g1 = AccProgGText.getText();
                    polaKonfiguracji.Komplementarny_g2 = AccProgDText.getText();
                    polaKonfiguracji.SredniaAcc = SredniaAcc.getText();
                    polaKonfiguracji.SredniaGyro = SredniaGyro.getText();
                    polaKonfiguracji.SredniaMagnet = SredniaMagnet.getText();
                    polaKonfiguracji.SredniaBaro = SredniaBaro.getText();
                    plikiXMLTymaczoswe.WriteXMlFile();

                    /*
                    buf_zapis.setADDR((byte) 0xAA);
                    buf_zapis.setCommandRead(buf_zapis.WRITE_KONFIGURACJA_1);
                    buf_zapis.setShortInFrame(tmps);
                    zapis2 = 1;
                    start = 1;
                    */
                }
            }
        });


    }
}
