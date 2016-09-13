package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by Patryk on 2016-07-11.
 */
public class OdswiezanieDanychController implements Initializable {


    @FXML
    private CheckBox CheckBoxOrientacja;

    @FXML
    private CheckBox CheckBoxGyro;

    @FXML
    private CheckBox CheckBoxRawy;

    @FXML
    private CheckBox CheckBoxS3Rate;

    @FXML
    private CheckBox CheckBoxOrientacjaRate;

    @FXML
    private CheckBox CheckBoxBaro;

    @FXML
    private CheckBox CheckBoxAkcelerometr;

    @FXML
    private CheckBox CheckBoxS1Rate;

    @FXML
    private CheckBox CheckBoxPWM;

    @FXML
    private CheckBox CheckBoxMagnet;

    @FXML
    private CheckBox CheckBoxSilnik2;

    @FXML
    private CheckBox CheckBoxSilnik1;

    @FXML
    private CheckBox CheckBoxSilnik4;

    @FXML
    private CheckBox CheckBoxSilnik3;

    @FXML
    private Button ButtonZmienOdswiezanie;

    @FXML
    private CheckBox CheckBoxUchyby;

    @FXML
    private CheckBox CheckBoxS4Rate;

    @FXML
    private CheckBox CheckBoxS2Rate;

    @FXML
    private CheckBox CheckBoxAkcelerometrW;

    @FXML
    private CheckBox CheckBoxUchybyRate;



    private int maxTabKomand =20;
    private byte []TablicaKomand = new byte[maxTabKomand];
    private RS232 DronPort = new RS232();

    private PolaKonfiguracji polaKonfiguracji = new PolaKonfiguracji();
    private PlikiXMLMetody plikiXMLTymczasowy = new PlikiXMLMetody("tmpConf",polaKonfiguracji);

    private void initCheckBox(){

        if(polaKonfiguracji.Orientacja.equals("true"))CheckBoxOrientacja.setSelected(true);
        if(polaKonfiguracji.Acc.equals("true"))CheckBoxAkcelerometr.setSelected(true);
        if(polaKonfiguracji.AccW.equals("true"))CheckBoxAkcelerometrW.setSelected(true);
        if(polaKonfiguracji.PWM.equals("true"))CheckBoxPWM.setSelected(true);
        if(polaKonfiguracji.uchybS.equals("true"))CheckBoxUchyby.setSelected(true);
        if(polaKonfiguracji.S1.equals("true"))CheckBoxSilnik1.setSelected(true);
        if(polaKonfiguracji.S2.equals("true"))CheckBoxSilnik2.setSelected(true);
        if(polaKonfiguracji.S3.equals("true"))CheckBoxSilnik3.setSelected(true);
        if(polaKonfiguracji.S4.equals("true"))CheckBoxSilnik4.setSelected(true);
        if(polaKonfiguracji.AccGyroRaw.equals("true"))CheckBoxRawy.setSelected(true);
        if(polaKonfiguracji.Gyro.equals("true"))CheckBoxGyro.setSelected(true);
        if(polaKonfiguracji.Magnet.equals("true"))CheckBoxMagnet.setSelected(true);
        if(polaKonfiguracji.Baro.equals("true"))CheckBoxBaro.setSelected(true);
        if(polaKonfiguracji.S1_Rate.equals("true"))CheckBoxS1Rate.setSelected(true);
        if(polaKonfiguracji.S2_Rate.equals("true"))CheckBoxS2Rate.setSelected(true);
        if(polaKonfiguracji.S3_Rate.equals("true"))CheckBoxS3Rate.setSelected(true);
        if(polaKonfiguracji.S4_Rate.equals("true"))CheckBoxS4Rate.setSelected(true);
        if(polaKonfiguracji.PredkoscKatowa.equals("true"))CheckBoxOrientacjaRate.setSelected(true);
        if(polaKonfiguracji.uchybRate.equals("true"))CheckBoxUchybyRate.setSelected(true);







        if(polaKonfiguracji.Orientacja.equals("true"))TablicaKomand[0] = Buffor.READ_ANGLE;
        if(polaKonfiguracji.Acc.equals("true"));TablicaKomand[1] = Buffor.READ_ACC;
        if(polaKonfiguracji.AccW.equals("true"));TablicaKomand[2] = Buffor.READ_ACC_W;
        if(polaKonfiguracji.PWM.equals("true"))TablicaKomand[3] = Buffor.READ_PWM;
        if(polaKonfiguracji.uchybS.equals("true")) TablicaKomand[4] = Buffor.READ_UCHYB;
        if(polaKonfiguracji.S1.equals("true")) TablicaKomand[5] = Buffor.READ_S1;
        if(polaKonfiguracji.S2.equals("true")) TablicaKomand[6] = Buffor.READ_S2;
        if(polaKonfiguracji.S3.equals("true")) TablicaKomand[7] = Buffor.READ_S3;
        if(polaKonfiguracji.S4.equals("true")) TablicaKomand[8] = Buffor.READ_S4;
        if(polaKonfiguracji.AccGyroRaw.equals("true")) TablicaKomand[9] = Buffor.LOGI_ACC_GYRO;
        if(polaKonfiguracji.Gyro.equals("true")) TablicaKomand[10] = Buffor.READ_GYRO_FILTR;
        if(polaKonfiguracji.Magnet.equals("true")) TablicaKomand[11] = Buffor.READ_MAGNET_S;
        if(polaKonfiguracji.Baro.equals("true")) TablicaKomand[12] = Buffor.READ_BARO;
        if(polaKonfiguracji.S1_Rate.equals("true")) TablicaKomand[13] = Buffor.READ_S1_REG_RATE;
        if(polaKonfiguracji.S2_Rate.equals("true")) TablicaKomand[14] = Buffor.READ_S2_REG_RATE;
        if(polaKonfiguracji.S3_Rate.equals("true")) TablicaKomand[15] = Buffor.READ_S3_REG_RATE;
        if(polaKonfiguracji.S4_Rate.equals("true")) TablicaKomand[16] = Buffor.READ_S4_REG_RATE;
        if(polaKonfiguracji.PredkoscKatowa.equals("true")) TablicaKomand[17] = Buffor.READ_ORIENTACJA_RATE;
        if(polaKonfiguracji.uchybRate.equals("true")) TablicaKomand[18] = Buffor.READ_UCHYB_RATE;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            initCheckBox();



        CheckBoxOrientacja.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (CheckBoxOrientacja.isSelected()) {
                    TablicaKomand[0] = Buffor.READ_ANGLE;
                    polaKonfiguracji.Orientacja = "true";
                }
                else {
                    polaKonfiguracji.Orientacja = "false";
                    TablicaKomand[0]=0;
                }
            }
        });

        CheckBoxAkcelerometr.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(CheckBoxAkcelerometr.isSelected()){
                    polaKonfiguracji.Acc="true";
                    TablicaKomand[1]=Buffor.READ_ACC;
                }
                else {
                    polaKonfiguracji.Acc="false";
                    TablicaKomand[1]=0;
                }
            }
        });

        CheckBoxAkcelerometrW.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(CheckBoxAkcelerometrW.isSelected()){
                    polaKonfiguracji.AccW = "true";
                    TablicaKomand[2]=Buffor.READ_ACC_W;
                }
                else{
                    polaKonfiguracji.AccW = "false";
                    TablicaKomand[2]=0;
                }
            }
        });

        CheckBoxPWM.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(CheckBoxPWM.isSelected()){
                    polaKonfiguracji.PWM="true";
                    TablicaKomand[3]=Buffor.READ_PWM;
                }
                else {
                    polaKonfiguracji.PWM = "false";
                    TablicaKomand[3]=0;
                }
            }
        });

        CheckBoxUchyby.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(CheckBoxUchyby.isSelected()){
                    polaKonfiguracji.uchybS = "true";
                    TablicaKomand[4]=Buffor.READ_UCHYB;
                }
                else {
                    polaKonfiguracji.uchybS = "false";
                    TablicaKomand[4]=0;
                }
            }
        });

        CheckBoxSilnik1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(CheckBoxSilnik1.isSelected()){
                    polaKonfiguracji.S1 = "true";
                    TablicaKomand[5]=Buffor.READ_S1;
                }
                else {
                    polaKonfiguracji.S1="false";
                    TablicaKomand[5]=0;
                }
            }
        });

        CheckBoxSilnik2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(CheckBoxSilnik2.isSelected()){
                    polaKonfiguracji.S2="true";
                    TablicaKomand[6]=Buffor.READ_S2;
                }
                else {
                    polaKonfiguracji.S2="false";
                    TablicaKomand[6]=0;
                }
            }
        });

        CheckBoxSilnik3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(CheckBoxSilnik3.isSelected()){
                    polaKonfiguracji.S3 = "true";
                    TablicaKomand[7]=Buffor.READ_S3;
                }
                else {
                    polaKonfiguracji.S3 = "false";
                    TablicaKomand[7]=0;
                }
            }
        });

        CheckBoxSilnik4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(CheckBoxSilnik4.isSelected()){
                    polaKonfiguracji.S4 = "true";
                    TablicaKomand[8]=Buffor.READ_S4;
                }
                else{
                    polaKonfiguracji.S4 = "false";
                    TablicaKomand[8]=0;
                }
            }
        });

        CheckBoxRawy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(CheckBoxRawy.isSelected()){
                    polaKonfiguracji.AccGyroRaw = "true";
                    TablicaKomand[9]=Buffor.LOGI_ACC_GYRO;
                }
                else {
                    polaKonfiguracji.AccGyroRaw = "false";
                    TablicaKomand[9]=0;
                }
            }
        });

        CheckBoxGyro.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(CheckBoxGyro.isSelected()){
                    polaKonfiguracji.Gyro="true";
                    TablicaKomand[10]=Buffor.READ_GYRO_FILTR;
                }
                else{
                    polaKonfiguracji.Gyro="false";
                    TablicaKomand[10]=0;
                }
            }
        });

        CheckBoxMagnet.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(CheckBoxMagnet.isSelected()){
                    polaKonfiguracji.Magnet = "true";
                    TablicaKomand[11]=Buffor.READ_MAGNET_S;
                }
                else{
                    polaKonfiguracji.Magnet = "false";
                    TablicaKomand[11]=0;
                }
            }
        });

        CheckBoxBaro.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(CheckBoxBaro.isSelected()){
                    polaKonfiguracji.Baro = "true";
                    TablicaKomand[12]=Buffor.READ_BARO;
                }
                else {
                    polaKonfiguracji.Baro = "false";
                    TablicaKomand[12]=0;
                }
            }
        });

        CheckBoxS1Rate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(CheckBoxS1Rate.isSelected()){
                    polaKonfiguracji.S1_Rate = "true";
                    TablicaKomand[13]=Buffor.READ_S1_REG_RATE;
                }
                else{
                    polaKonfiguracji.S1_Rate = "false";
                    TablicaKomand[13]=0;
                }
            }
        });

        CheckBoxS2Rate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(CheckBoxS2Rate.isSelected()){
                    polaKonfiguracji.S2_Rate = "true";
                    TablicaKomand[14]=Buffor.READ_S2_REG_RATE;
                }
                else {
                    polaKonfiguracji.S2_Rate="false";
                    TablicaKomand[14]=0;
                }
            }
        });

        CheckBoxS3Rate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(CheckBoxS3Rate.isSelected()){
                    polaKonfiguracji.S3_Rate = "true";
                    TablicaKomand[15]=Buffor.READ_S3_REG_RATE;
                }
                else {
                    polaKonfiguracji.S3_Rate = "false";
                    TablicaKomand[15]=0;
                }
            }
        });

        CheckBoxS4Rate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(CheckBoxS4Rate.isSelected()){
                    polaKonfiguracji.S4_Rate = "true";
                    TablicaKomand[16]=Buffor.READ_S4_REG_RATE;
                }
                else {
                    polaKonfiguracji.S4_Rate = "false";
                    TablicaKomand[16]=0;
                }
            }
        });

        CheckBoxOrientacjaRate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(CheckBoxOrientacjaRate.isSelected()){
                    polaKonfiguracji.PredkoscKatowa = "true";
                    TablicaKomand[17]=Buffor.READ_ORIENTACJA_RATE;
                }
                else {
                    polaKonfiguracji.PredkoscKatowa = "false";
                    TablicaKomand[17]=0;
                }
            }
        });

        CheckBoxUchybyRate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(CheckBoxUchybyRate.isSelected()){
                    polaKonfiguracji.uchybRate = "true";
                    TablicaKomand[18]=Buffor.READ_UCHYB_RATE;
                }
                else{
                    polaKonfiguracji.uchybRate = "false";
                    TablicaKomand[18]=0;
                }
            }
        });

        ButtonZmienOdswiezanie.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                int length=0;
                byte []tmp = new byte [maxTabKomand];

                for(int i=0;i<maxTabKomand;i++){
                    if(TablicaKomand[i]!=0){
                        tmp[length++]=TablicaKomand[i];
                    }
                }

                DronPort.ZmienOdswiezaneDane(length,tmp);
                plikiXMLTymczasowy.WriteXMlFile();
               // KonsolaDane.add(CzasSystemowy.format(new Date()) + ">Zmieniono Odswieazne Dane");

            }
        });

    }

}
