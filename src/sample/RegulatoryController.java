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
 * Created by Patryk on 2016-07-09.
 */
public class RegulatoryController implements Initializable {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField regAlfaIKat;

    @FXML
    private TextField regAlfaIRate;

    @FXML
    private Button StopAwaryjnyReg;

    @FXML
    private TextField SkladowaHText;

    @FXML
    private TextField regAlfaDRate;

    @FXML
    private TextField regBetaIRate;

    @FXML
    private Button setRegBetaRate;

    @FXML
    private TextField regAlfaIdownKat;

    @FXML
    private TextField regBetaIKat;

    @FXML
    private TextField regBetaIupRate;

    @FXML
    private TextField regAlfaIupKat;

    @FXML
    private Button setRegAlfaKat;

    @FXML
    private TextField regBetaIdownRate;

    @FXML
    private TextField regBetaIupKat;

    @FXML
    private Button StartRegButton;

    @FXML
    private TextField regAlfaPRate;

    @FXML
    private TextField regBetaPRate;

    @FXML
    private Button setRegBetaKat;

    @FXML
    private TextField regAlfaIdownRate;

    @FXML
    private TextField regBetaIdownKat;

    @FXML
    private Button setHbutton;

    @FXML
    private Button setRegAlfaRate;

    @FXML
    private TextField regAlfaDKat;

    @FXML
    private TextField regBetaPKat;

    @FXML
    private TextField regAlfaPKat;

    @FXML
    private TextField regBetaDKat;

    @FXML
    private TextField regAlfaIupRate;

    @FXML
    private TextField regBetaDRate;

    private PolaKonfiguracji polaKonfiguracji;
    private PlikiXMLMetody plikTymczasowyXML;

    private Buffor buf_zapis =new Buffor();
    private RS232 DronPort = new RS232();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        plikTymczasowyXML = new PlikiXMLMetody("tmpConf",polaKonfiguracji);

        SkladowaHText.setText(polaKonfiguracji.SkladowaH);

        regAlfaPKat.setText(polaKonfiguracji.RegSAlfa_P);
        regAlfaIKat.setText(polaKonfiguracji.RegSAlfa_I);
        regAlfaDKat.setText(polaKonfiguracji.RegSAlfa_D);
        regAlfaIupKat.setText(polaKonfiguracji.RegSAlfa_Iup);
        regAlfaIdownKat.setText(polaKonfiguracji.RegSAlfa_Idown);

        regBetaPKat.setText(polaKonfiguracji.RegSBeta_P);
        regBetaIKat.setText(polaKonfiguracji.RegSBeta_I);
        regBetaDKat.setText(polaKonfiguracji.RegSBeta_D);
        regBetaIupKat.setText(polaKonfiguracji.RegSBeta_Iup);
        regBetaIdownKat.setText(polaKonfiguracji.RegSBeta_Idown);

        regAlfaPRate.setText(polaKonfiguracji.RegRAlfa_P);
        regAlfaIRate.setText(polaKonfiguracji.RegRAlfa_I);
        regAlfaDRate.setText(polaKonfiguracji.RegRAlfa_D);
        regAlfaIupRate.setText(polaKonfiguracji.RegRAlfa_Iup);
        regAlfaIdownRate.setText(polaKonfiguracji.RegRAlfa_Idown);

        regBetaPRate.setText(polaKonfiguracji.RegRBeta_P);
        regBetaIRate.setText(polaKonfiguracji.RegRBeta_I);
        regBetaDRate.setText(polaKonfiguracji.RegRBeta_D);
        regBetaIupRate.setText(polaKonfiguracji.RegRBeta_Iup);
        regBetaIdownRate.setText(polaKonfiguracji.RegRBeta_Idown);

        setHbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               // KonsolaDane.add(CzasSystemowy.format(new Date()) + ">Wysłano składowa H");
                short[] tmp = new short[6];
                String s = SkladowaHText.getText();
                tmp[0] = Short.decode(s);
                tmp[1] = 1;

                DronPort.ZapiszDaneWDronie(buf_zapis.WRITE_H,tmp);

                polaKonfiguracji.SkladowaH = SkladowaHText.getText();
                plikTymczasowyXML.WriteXMlFile();
            }
        });

        setRegAlfaKat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                short[] tmp = new short[6];
                tmp[0] = Short.decode(regAlfaPKat.getText());
                tmp[1] = Short.decode(regAlfaIKat.getText());
                tmp[2] = Short.decode(regAlfaIupKat.getText());
                tmp[3] = Short.decode(regAlfaIdownKat.getText());
                tmp[4] = Short.decode(regAlfaDKat.getText());

                // KonsolaDane.add(CzasSystemowy.format(new Date()) + ">Wysłano parametry Regulatora Beta");

                DronPort.ZapiszDaneWDronie(buf_zapis.WRITE_REG_ALFA1,tmp);

                polaKonfiguracji.RegSAlfa_P=regAlfaPKat.getText();
                polaKonfiguracji.RegSAlfa_I = regAlfaIKat.getText();
                polaKonfiguracji.RegSAlfa_D = regAlfaDKat.getText();
                polaKonfiguracji.RegSAlfa_Iup=regAlfaIupKat.getText();
                polaKonfiguracji.RegSAlfa_Idown=regAlfaIdownKat.getText();
                plikTymczasowyXML.WriteXMlFile();
            }
        });

        setRegBetaKat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                short[] tmp = new short[6];
                tmp[0] = Short.decode(regBetaPKat.getText());
                tmp[1] = Short.decode(regBetaIKat.getText());
                tmp[2] = Short.decode(regBetaIupKat.getText());
                tmp[3] = Short.decode(regBetaIdownKat.getText());
                tmp[4] = Short.decode(regBetaDKat.getText());

               // KonsolaDane.add(CzasSystemowy.format(new Date()) + ">Wysłano parametry Regulatora Beta");

                DronPort.ZapiszDaneWDronie(buf_zapis.WRITE_REG_BETA1,tmp);
                polaKonfiguracji.RegSBeta_P=regBetaPKat.getText();
                polaKonfiguracji.RegSBeta_I = regBetaIKat.getText();
                polaKonfiguracji.RegSBeta_D = regBetaDKat.getText();
                polaKonfiguracji.RegSBeta_Iup=regBetaIupKat.getText();
                polaKonfiguracji.RegSBeta_Idown=regBetaIdownKat.getText();
                plikTymczasowyXML.WriteXMlFile();
            }
        });

        setRegAlfaRate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                short[] tmp = new short[6];
                tmp[0] = Short.decode(regAlfaPRate.getText());
                tmp[1] = Short.decode(regAlfaIRate.getText());
                tmp[2] = Short.decode(regAlfaIupRate.getText());
                tmp[3] = Short.decode(regAlfaIdownRate.getText());
                tmp[4] = Short.decode(regAlfaDRate.getText());

                DronPort.ZapiszDaneWDronie(buf_zapis.WRITE_REG_ALFA_RATE,tmp);

                polaKonfiguracji.RegRAlfa_P=regAlfaPRate.getText();
                polaKonfiguracji.RegRAlfa_I = regAlfaIRate.getText();
                polaKonfiguracji.RegRAlfa_D = regAlfaDRate.getText();
                polaKonfiguracji.RegRAlfa_Iup=regAlfaIupRate.getText();
                polaKonfiguracji.RegRAlfa_Idown=regAlfaIdownRate.getText();
                plikTymczasowyXML.WriteXMlFile();

            }
        });

        setRegBetaRate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                short[] tmp = new short[6];
                tmp[0] = Short.decode(regBetaPRate.getText());
                tmp[1] = Short.decode(regBetaIRate.getText());
                tmp[2] = Short.decode(regBetaIupRate.getText());
                tmp[3] = Short.decode(regBetaIdownRate.getText());
                tmp[4] = Short.decode(regBetaDRate.getText());

                DronPort.ZapiszDaneWDronie(buf_zapis.WRITE_REG_BETA_RATE,tmp);

                polaKonfiguracji.RegRBeta_P=regBetaPRate.getText();
                polaKonfiguracji.RegRBeta_I = regBetaIRate.getText();
                polaKonfiguracji.RegRBeta_D = regBetaDRate.getText();
                polaKonfiguracji.RegRBeta_Iup=regBetaIupRate.getText();
                polaKonfiguracji.RegRBeta_Idown=regBetaIdownRate.getText();
                plikTymczasowyXML.WriteXMlFile();
            }
        });

        StartRegButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               // KonsolaDane.add(CzasSystemowy.format(new Date()) + ">Uruchomienie Regulatorow");
                short[] tmp = new short[6];
                tmp[0] = 1;

                DronPort.ZapiszDaneWDronie(buf_zapis.WRITE_CMD,tmp);
            }
        });

        StopAwaryjnyReg.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                short []tmp = new short[6];
               // KonsolaDane.add(CzasSystemowy.format(new Date()) + ">STOP AWARYJNY - wyzerowanie PWM i REG");
                DronPort.ZapiszDaneWDronie(buf_zapis.STOP_AWARYJNY,tmp);
            }
        });

    }
}
