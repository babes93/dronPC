package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Pane;
/**
 * Created by Babes on 2016-02-25.
 */
public class ControllerWykresy implements Initializable {

    private int samples=0;
    private WykresyBufor buf = new WykresyBufor();

    @FXML
    private CheckBox Y_raw_gyro;

    @FXML
    private CheckBox Z_raw_acc;

    @FXML
    private CheckBox X_gyro;

    @FXML
    private CheckBox Y_raw_acc;

    @FXML
    private CheckBox Y_acc;

    @FXML
    private CheckBox Z_gyro;

    @FXML
    private CheckBox Beta;

    @FXML
    private CheckBox X_raw_gyro;

    @FXML
    private CheckBox W_acc;

    @FXML
    private CheckBox Gamma;

    @FXML
    private CheckBox X_raw_acc;

    @FXML
    private CheckBox Alfa;

    @FXML
    private Pane WykresPane;

    @FXML
    private CheckBox Z_acc;

    @FXML
    private CheckBox Z_raw_gyro;

    @FXML
    private CheckBox X_acc;

    @FXML
    private CheckBox Y_gyro;

    @FXML
    private CheckBox MAGNET_X;

    @FXML
    private CheckBox MAGNET_Y;

    @FXML
    private CheckBox MAGNET_Z;

    @FXML
    private CheckBox BARO_T;

    @FXML
    private CheckBox BARO_P;

    @FXML
    private CheckBox BARO_W;

    @FXML
    private CheckBox CheckBoxAlfaRate;

    @FXML
    private CheckBox CheckBoxBetaRate;

    @FXML
    private CheckBox CheckBoxGammaRate;

    private NumberAxis osx=new NumberAxis();
    private NumberAxis osy = new NumberAxis();

    private final LineChart<Number,Number> Wykres = new LineChart<Number, Number>(osx,osy);

    private XYChart.Series<Number,Number> alfa = new XYChart.Series<Number,Number>();
    private XYChart.Series<Number,Number> beta = new XYChart.Series<Number,Number>();
    private XYChart.Series<Number,Number> gamma = new XYChart.Series<Number,Number>();

    private XYChart.Series<Number,Number> alfaRate = new XYChart.Series<Number,Number>();
    private XYChart.Series<Number,Number> betaRate = new XYChart.Series<Number,Number>();
    private XYChart.Series<Number,Number> gammaRate = new XYChart.Series<Number,Number>();

    private XYChart.Series<Number,Number> gyroX = new XYChart.Series<Number,Number>();
    private XYChart.Series<Number,Number> gyroY= new XYChart.Series<Number,Number>();
    private XYChart.Series<Number,Number> gyroZ = new XYChart.Series<Number,Number>();

    private XYChart.Series<Number,Number> gyroRawX = new XYChart.Series<Number,Number>();
    private XYChart.Series<Number,Number> gyroRawY = new XYChart.Series<Number,Number>();
    private XYChart.Series<Number,Number> gyroRawZ = new XYChart.Series<Number,Number>();

    private XYChart.Series<Number,Number> accX = new XYChart.Series<Number,Number>();
    private XYChart.Series<Number,Number> accY= new XYChart.Series<Number,Number>();
    private XYChart.Series<Number,Number> accZ = new XYChart.Series<Number,Number>();
    private XYChart.Series<Number,Number> accW = new XYChart.Series<Number,Number>();

    private XYChart.Series<Number,Number> accRawX = new XYChart.Series<Number,Number>();
    private XYChart.Series<Number,Number> accRawY = new XYChart.Series<Number,Number>();
    private XYChart.Series<Number,Number> accRawZ = new XYChart.Series<Number,Number>();

    private XYChart.Series<Number,Number> magnetX = new XYChart.Series<Number,Number>();
    private XYChart.Series<Number,Number> magnetY= new XYChart.Series<Number,Number>();
    private XYChart.Series<Number,Number> magnetZ = new XYChart.Series<Number,Number>();

    private XYChart.Series<Number,Number> baroT = new XYChart.Series<Number,Number>();
    private XYChart.Series<Number,Number> baroP= new XYChart.Series<Number,Number>();
    private XYChart.Series<Number,Number> baroW = new XYChart.Series<Number,Number>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        Wykres.setMaxSize(510, 520);
        Wykres.setAnimated(false);
        osx.setLabel("Samples");
        Wykres.setCreateSymbols(false);
        osx.setForceZeroInRange(false);
        osy.setForceZeroInRange(false);
        WykresPane.getChildren().add(Wykres);

        alfa.setName("Alfa");
        beta.setName("Beta");
        gamma.setName("Gamma");

        alfaRate.setName("Alfa Rate");
        betaRate.setName("Beta Rate");
        gammaRate.setName("Gamma Rate");

        gyroRawX.setName("Gyro Raw X");
        gyroRawY.setName("Gyro Raw Y");
        gyroRawZ.setName("Gyro Raw Z");

        gyroX.setName("Gyro X");
        gyroY.setName("Gyro Y");
        gyroZ.setName("Gyro Z");

        accRawX.setName("Acc Raw X");
        accRawY.setName("Acc Raw Y");
        accRawZ.setName("Acc Raw Z");

        accW.setName("Acc Wypadkowy");
        accX.setName("Acc X");
        accY.setName("Acc Y");
        accZ.setName("Acc Z");

        magnetX.setName("Magnet X");
        magnetY.setName("Magnet Y");
        magnetZ.setName("Magnet Z");

        baroP.setName("Cisnienie");
        baroT.setName("Temperatura");
        baroW.setName("Wysokosc n.p.m.");

        for(int i=0; i<50;i++){
            alfa.getData().add(new XYChart.Data<Number,Number>(i,0));
            beta.getData().add(new XYChart.Data<Number,Number>(i,0));
            gamma.getData().add(new XYChart.Data<Number,Number>(i,0));

            alfaRate.getData().add(new XYChart.Data<Number,Number>(i,0));
            betaRate.getData().add(new XYChart.Data<Number,Number>(i,0));
            gammaRate.getData().add(new XYChart.Data<Number,Number>(i,0));

            gyroX.getData().add(new XYChart.Data<Number,Number>(i,0));
            gyroY.getData().add(new XYChart.Data<Number,Number>(i,0));
            gyroZ.getData().add(new XYChart.Data<Number,Number>(i,0));

            magnetX.getData().add(new XYChart.Data<Number,Number>(i,0));
            magnetY.getData().add(new XYChart.Data<Number,Number>(i,0));
            magnetZ.getData().add(new XYChart.Data<Number,Number>(i,0));

            baroP.getData().add(new XYChart.Data<Number,Number>(i,0));
            baroW.getData().add(new XYChart.Data<Number,Number>(i,0));
            baroT.getData().add(new XYChart.Data<Number,Number>(i,0));

            accX.getData().add(new XYChart.Data<Number,Number>(i,0));
            accY.getData().add(new XYChart.Data<Number,Number>(i,0));
            accZ.getData().add(new XYChart.Data<Number,Number>(i,0));
            accW.getData().add(new XYChart.Data<Number,Number>(i,0));

            gyroRawX.getData().add(new XYChart.Data<Number,Number>(i,0));
            gyroRawY.getData().add(new XYChart.Data<Number,Number>(i,0));
            gyroRawZ.getData().add(new XYChart.Data<Number,Number>(i,0));

            accRawX.getData().add(new XYChart.Data<Number,Number>(i,0));
            accRawY.getData().add(new XYChart.Data<Number,Number>(i,0));
            accRawZ.getData().add(new XYChart.Data<Number,Number>(i,0));
        }


        Timeline wyk = new Timeline(new KeyFrame(javafx.util.Duration.millis(100), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(buf.ore){
                    samples++;
                    alfa.getData().add(new XYChart.Data<Number,Number>(samples,buf.getAlfa()));
                    beta.getData().add(new XYChart.Data<Number,Number>(samples,buf.getBeta()));
                    gamma.getData().add(new XYChart.Data<Number,Number>(samples,buf.getGamma()));

                    alfa.getData().remove(0);
                    beta.getData().remove(0);
                    gamma.getData().remove(0);

                    buf.ore=false;
                }

                if(buf.raws){
                    accRawX.getData().add(new XYChart.Data<Number,Number>(samples,buf.getAccRawX()));
                    accRawY.getData().add(new XYChart.Data<Number,Number>(samples,buf.getAccRawY()));
                    accRawZ.getData().add(new XYChart.Data<Number,Number>(samples,buf.getAccRawZ()));

                    gyroRawX.getData().add(new XYChart.Data<Number,Number>(samples,buf.getGyroRawX()));
                    gyroRawY.getData().add(new XYChart.Data<Number,Number>(samples,buf.getGyroRawY()));
                    gyroRawZ.getData().add(new XYChart.Data<Number,Number>(samples,buf.getGyroRawZ()));

                    accRawX.getData().remove(0);
                    accRawY.getData().remove(0);
                    accRawZ.getData().remove(0);

                    gyroRawX.getData().remove(0);
                    gyroRawY.getData().remove(0);
                    gyroRawZ.getData().remove(0);

                    buf.raws=false;

                }

                if(buf.accs){
                    accX.getData().add(new XYChart.Data<Number,Number>(samples,buf.getAccX()));
                    accY.getData().add(new XYChart.Data<Number,Number>(samples,buf.getAccY()));
                    accZ.getData().add(new XYChart.Data<Number,Number>(samples,buf.getAccZ()));
                    accW.getData().add(new XYChart.Data<Number, Number>(samples, buf.getAccW()));

                    accX.getData().remove(0);
                    accY.getData().remove(0);
                    accZ.getData().remove(0);
                    accW.getData().remove(0);

                    buf.accs=false;

                }

                if(buf.gyros){
                    gyroX.getData().add(new XYChart.Data<Number,Number>(samples,buf.getGyroX()));
                   gyroY.getData().add(new XYChart.Data<Number,Number>(samples,buf.getGyroY()));
                    gyroZ.getData().add(new XYChart.Data<Number,Number>(samples,buf.getGyroZ()));

                    gyroX.getData().remove(0);
                    gyroY.getData().remove(0);
                    gyroZ.getData().remove(0);

                    buf.gyros=false;


                }

                if(buf.magnet){
                    magnetX.getData().add(new XYChart.Data<Number,Number>(samples,buf.MAGNET_X));
                    magnetY.getData().add(new XYChart.Data<Number,Number>(samples,buf.MAGNET_Y));
                    magnetZ.getData().add(new XYChart.Data<Number,Number>(samples,buf.MAGNET_Z));

                    magnetX.getData().remove(0);
                    magnetY.getData().remove(0);
                    magnetZ.getData().remove(0);

                    buf.magnet=false;
                }

                if(buf.baro){
                    baroP.getData().add(new XYChart.Data<Number,Number>(samples,buf.BARO_P));
                    baroT.getData().add(new XYChart.Data<Number,Number>(samples,buf.BARO_T));
                    baroW.getData().add(new XYChart.Data<Number,Number>(samples,buf.BARO_W));

                    baroP.getData().remove(0);
                    baroT.getData().remove(0);
                    baroW.getData().remove(0);


                    buf.baro=false;
                }

                if(buf.EulerRate){
                    alfaRate.getData().add(new XYChart.Data<Number,Number>(samples,buf.AlfaRate));
                    betaRate.getData().add(new XYChart.Data<Number,Number>(samples,buf.BetaRate));
                    gammaRate.getData().add(new XYChart.Data<Number,Number>(samples,buf.GammaRate));

                    alfaRate.getData().remove(0);
                    betaRate.getData().remove(0);
                    gammaRate.getData().remove(0);

                    buf.EulerRate=false;

                }


            }
        }));

        wyk.setCycleCount(Timeline.INDEFINITE);
        wyk.play();

        MAGNET_X.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (MAGNET_X.isSelected()) Wykres.getData().add(magnetX);
                else Wykres.getData().remove(magnetX);
            }
        });

        MAGNET_Y.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(MAGNET_Y.isSelected())Wykres.getData().add(magnetY);
                else Wykres.getData().remove(magnetY);
            }
        });

        MAGNET_Z.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(MAGNET_Z.isSelected())Wykres.getData().add(magnetZ);
                else Wykres.getData().remove(magnetZ);

            }
        });

        BARO_P.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(BARO_P.isSelected())Wykres.getData().add(baroP);
                else Wykres.getData().remove(baroP);
            }
        });

        BARO_T.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(BARO_T.isSelected())Wykres.getData().add(baroT);
                else Wykres.getData().remove(baroT);
            }
        });

        BARO_W.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(BARO_W.isSelected())Wykres.getData().add(baroW);
                else Wykres.getData().remove(baroW);
            }
        });

        Alfa.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(Alfa.isSelected()){
                    Wykres.getData().add(alfa);
                }else {
                    Wykres.getData().remove(alfa);
                }
            }
        });

        Beta.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(Beta.isSelected()){
                    Wykres.getData().add(beta);
                }else {
                    Wykres.getData().remove(beta);
                }
            }
        });

        Gamma.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(Gamma.isSelected()){
                    Wykres.getData().add(gamma);
                }else {
                    Wykres.getData().remove(gamma);
                }
            }
        });

        X_gyro.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(X_gyro.isSelected()){
                    Wykres.getData().add(gyroX);
                }else {
                    Wykres.getData().remove(gyroX);
                }
            }
        });

        Y_gyro.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(Y_gyro.isSelected()){
                    Wykres.getData().add(gyroY);
                }else{
                    Wykres.getData().remove(gyroY);
                }
            }
        });

        Z_gyro.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(Z_gyro.isSelected()){
                    Wykres.getData().add(gyroZ);
                }else{
                    Wykres.getData().remove(gyroZ);
                }
            }
        });

        X_acc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(X_acc.isSelected()){
                    Wykres.getData().add(accX);
                }else {
                    Wykres.getData().remove(accX);
                }
            }
        });

        Y_acc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(Y_acc.isSelected()){
                    Wykres.getData().add(accY);
                }else {
                    Wykres.getData().remove(accY);
                }
            }
        });

        Z_acc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(Z_acc.isSelected()){
                    Wykres.getData().add(accZ);
                }else {
                    Wykres.getData().remove(accZ);
                }
            }
        });

        W_acc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(W_acc.isSelected()){
                    Wykres.getData().add(accW);
                }else {
                    Wykres.getData().remove(accW);
                }
            }
        });

        X_raw_gyro.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(X_raw_gyro.isSelected()){
                    Wykres.getData().add(gyroRawX);
                }else Wykres.getData().remove(gyroRawX);
            }
        });

        Y_raw_gyro.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(Y_raw_gyro.isSelected())Wykres.getData().add(gyroRawY);
                else Wykres.getData().remove(gyroRawY);
            }
        });

        Z_raw_gyro.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(Z_raw_gyro.isSelected())Wykres.getData().add(gyroRawZ);
                else Wykres.getData().remove(gyroRawZ);
            }
        });

        X_raw_acc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(X_raw_acc.isSelected())Wykres.getData().add(accRawX);
                else Wykres.getData().remove(accRawX);
            }
        });

        Y_raw_acc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(Y_raw_acc.isSelected())Wykres.getData().add(accRawY);
                else Wykres.getData().remove(accRawY);
            }
        });

        Z_raw_acc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(Z_raw_acc.isSelected())Wykres.getData().add(accRawZ);
                else Wykres.getData().remove(accRawZ);
            }
        });

        CheckBoxAlfaRate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(CheckBoxAlfaRate.isSelected())Wykres.getData().add(alfaRate);
                else Wykres.getData().remove(alfaRate);
            }
        });

        CheckBoxBetaRate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(CheckBoxBetaRate.isSelected())Wykres.getData().add(betaRate);
                else Wykres.getData().remove(betaRate);
            }
        });

        CheckBoxGammaRate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(CheckBoxGammaRate.isSelected())Wykres.getData().add(gammaRate);
                else Wykres.getData().remove(gammaRate);
            }
        });

    }
}
