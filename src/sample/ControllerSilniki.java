package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;

/**
 * Created by Babes on 2016-02-25.
 */
public class ControllerSilniki implements Initializable {

    private WykSilnikiBuf buf = new WykSilnikiBuf();

    @FXML
    private CheckBox S4_PWM;

    @FXML
    private CheckBox S3_rA;

    @FXML
    private CheckBox S3_rB;

    @FXML
    private Pane PaneS3;

    @FXML
    private Pane PaneS4;

    @FXML
    private Pane PaneS1;

    @FXML
    private Pane PaneS2;

    @FXML
    private CheckBox S4_eA;

    @FXML
    private CheckBox S4_eB;

    @FXML
    private CheckBox S2_eB;

    @FXML
    private CheckBox S2_eA;

    @FXML
    private CheckBox S1_rB;

    @FXML
    private CheckBox S1_rA;

    @FXML
    private CheckBox S1_H;

    @FXML
    private CheckBox S1_PWM;

    @FXML
    private CheckBox S4_rB;

    @FXML
    private CheckBox S2_rA;

    @FXML
    private CheckBox S4_rA;

    @FXML
    private CheckBox S2_PWM;

    @FXML
    private CheckBox S4_H;

    @FXML
    private CheckBox S2_H;

    @FXML
    private CheckBox S3_H;

    @FXML
    private CheckBox S3_eA;

    @FXML
    private CheckBox S1_eA;

    @FXML
    private CheckBox S3_eB;

    @FXML
    private CheckBox S1_eB;

    @FXML
    private CheckBox S2_rB;

    @FXML
    private CheckBox S3_PWM;

    @FXML
    private CheckBox S1_rAlfaRate;

    @FXML
    private CheckBox S1_rBetaRate;

    @FXML
    private CheckBox S2_rAlfaRate;

    @FXML
    private CheckBox S2_rBetaRate;

    @FXML
    private CheckBox S3_rAlfaRate;

    @FXML
    private CheckBox S3_rBetaRate;

    @FXML
    private CheckBox S4_rAlfaRate;

    @FXML
    private CheckBox S4_rBetaRate;

    @FXML
    private CheckBox S1_eAlfaRate;

    @FXML
    private CheckBox S1_eBetaRate;

    @FXML
    private CheckBox S2_eAlfaRate;

    @FXML
    private CheckBox S2_eBetaRate;

    @FXML
    private CheckBox S3_eAlfaRate;

    @FXML
    private CheckBox S3_eBetaRate;

    @FXML
    private CheckBox S4_eAlfaRate;

    @FXML
    private CheckBox S4_eBetaRate;


    private NumberAxis osys1=new NumberAxis();
    private NumberAxis osxs1=new NumberAxis();

    private NumberAxis osys2=new NumberAxis();
    private NumberAxis osxs2 = new NumberAxis();

    private NumberAxis osys3=new NumberAxis();
    private NumberAxis osxs3=new NumberAxis();

    private NumberAxis osys4 = new NumberAxis();
    private NumberAxis osxs4 = new NumberAxis();


    private final LineChart<Number,Number> Silnik1 = new LineChart<Number, Number>(osxs1,osys1);
    private final LineChart<Number,Number> Silnik2 = new LineChart<Number, Number>(osxs2,osys2);
    private final LineChart<Number,Number> Silnik3 = new LineChart<Number, Number>(osxs3,osys3);
    private final LineChart<Number,Number> Silnik4 = new LineChart<Number, Number>(osxs4,osys4);

    private SilnikDane DaneS1= new SilnikDane();
    private SilnikDane DaneS2 = new SilnikDane();
    private SilnikDane DaneS3 = new SilnikDane();
    private SilnikDane DaneS4 = new SilnikDane();



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Silnik1.setMaxSize(417,312);
        Silnik2.setMaxSize(417,312);
        Silnik3.setMaxSize(417,312);
        Silnik4.setMaxSize(417, 312);

        Silnik1.setAnimated(false);
        Silnik2.setAnimated(false);
        Silnik3.setAnimated(false);
        Silnik4.setAnimated(false);

        Silnik1.setTitle("Silnik nr. 1");
        Silnik2.setTitle("Silnik nr. 2");
        Silnik3.setTitle("Silnik nr. 3");
        Silnik4.setTitle("Silnik nr. 4");

        Silnik1.setCreateSymbols(false);
        Silnik2.setCreateSymbols(false);
        Silnik3.setCreateSymbols(false);
        Silnik4.setCreateSymbols(false);

        osxs1.setLabel("Samples");
        osxs2.setLabel("Samples");
        osxs3.setLabel("Samples");
        osxs4.setLabel("Samples");

        osxs1.setForceZeroInRange(false);
        osxs2.setForceZeroInRange(false);
        osxs3.setForceZeroInRange(false);
        osxs4.setForceZeroInRange(false);

        osys1.setForceZeroInRange(false);
        osys2.setForceZeroInRange(false);
        osys3.setForceZeroInRange(false);
        osys4.setForceZeroInRange(false);

        PaneS1.getChildren().add(Silnik1);
        PaneS2.getChildren().add(Silnik2);
        PaneS3.getChildren().add(Silnik3);
        PaneS4.getChildren().add(Silnik4);

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        Timeline wyk = new Timeline(new KeyFrame(javafx.util.Duration.millis(100), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if(buf.s1){
                    DaneS1.setH(buf.H_S1);
                    DaneS1.setPWM(buf.PWM_S1);
                    DaneS1.seteAlfa(buf.eAlfa_S1);
                    DaneS1.seteBeta(buf.eBeta_S1);
                    DaneS1.setRegAlfa(buf.rAlfa_S1);
                    DaneS1.setRegBeta(buf.rBeta_S1);
                    DaneS1.setUchybRate(buf.eAlfaRate_S1,buf.eBetaRate_S1);
                    DaneS1.countSample();
                    buf.s1=false;
                }

                if(buf.s2){
                    DaneS2.setH(buf.H_S2);
                    DaneS2.setPWM(buf.PWM_S2);
                    DaneS2.seteAlfa(buf.eAlfa_S2);
                    DaneS2.seteBeta(buf.eBeta_S2);
                    DaneS2.setRegAlfa(buf.rAlfa_S2);
                    DaneS2.setRegBeta(buf.rBeta_S2);
                    DaneS2.setUchybRate(buf.eAlfaRate_S2,buf.eBetaRate_S2);
                    DaneS2.countSample();
                    buf.s2=false;

                }

                if(buf.s3){
                    DaneS3.setH(buf.H_S3);
                    DaneS3.setPWM(buf.PWM_S3);
                    DaneS3.seteAlfa(buf.eAlfa_S3);
                    DaneS3.seteBeta(buf.eBeta_S3);
                    DaneS3.setRegAlfa(buf.rAlfa_S3);
                    DaneS3.setRegBeta(buf.rBeta_S3);
                    DaneS3.setUchybRate(buf.eAlfaRate_S3,buf.eBetaRate_S3);
                    DaneS3.countSample();
                    buf.s3=false;
                }

                if(buf.s4){
                    DaneS4.setH(buf.H_S4);
                    DaneS4.setPWM(buf.PWM_S4);
                    DaneS4.seteAlfa(buf.eAlfa_S4);
                    DaneS4.seteBeta(buf.eBeta_S4);
                    DaneS4.setRegAlfa(buf.rAlfa_S4);
                    DaneS4.setRegBeta(buf.rBeta_S4);
                    DaneS4.setUchybRate(buf.eAlfaRate_S4,buf.eBetaRate_S4);
                    DaneS4.countSample();
                    buf.s4=false;

                }

                if(buf.s1Rate){
                    DaneS1.setRegAlfaRate(buf.rAlfaRate_S1);
                    DaneS1.setRegBetaRate(buf.rBetaRate_S1);
                    buf.s1Rate=false;
                }

                if(buf.s2Rate){
                    DaneS2.setRegAlfaRate(buf.rAlfaRate_S2);
                    DaneS2.setRegBetaRate(buf.rBetaRate_S2);
                    buf.s2Rate=false;
                }

                if(buf.s3Rate){
                    DaneS3.setRegAlfaRate(buf.rAlfaRate_S3);
                    DaneS3.setRegBetaRate(buf.rBetaRate_S3);
                    buf.s3Rate=false;
                }

                if(buf.s4Rate){
                    DaneS4.setRegAlfaRate(buf.rAlfaRate_S4);
                    DaneS4.setRegBetaRate(buf.rBetaRate_S4);
                    buf.s4Rate=false;
                }

            }
        }));

                wyk.setCycleCount(Timeline.INDEFINITE);
                wyk.play();

        //////////////////////////////////////////////////////////////////////////////////////////

        S1_H.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (S1_H.isSelected()) Silnik1.getData().add(DaneS1.H);
                else Silnik1.getData().remove(DaneS1.H);
            }
        });

        S1_PWM.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (S1_PWM.isSelected()) Silnik1.getData().add(DaneS1.PWM);
                else Silnik1.getData().remove(DaneS1.PWM);
            }
        });

        S1_eA.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (S1_eA.isSelected()) Silnik1.getData().add(DaneS1.eAlfa);
                else Silnik1.getData().remove(DaneS1.eAlfa);
            }
        });

        S1_eB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (S1_eB.isSelected()) Silnik1.getData().add(DaneS1.eBeta);
                else Silnik1.getData().remove(DaneS1.eBeta);
            }
        });

        S1_rA.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (S1_rA.isSelected()) Silnik1.getData().add(DaneS1.RegAlfa);
                else Silnik1.getData().remove(DaneS1.RegAlfa);
            }
        });

        S1_rB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (S1_rB.isSelected()) Silnik1.getData().add(DaneS1.RegBeta);
                else Silnik1.getData().remove(DaneS1.RegBeta);
            }
        });

        S1_eAlfaRate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(S1_eAlfaRate.isSelected())Silnik1.getData().add(DaneS1.eAlfaRate);
                else Silnik1.getData().remove(DaneS1.eAlfaRate);
            }
        });

        S1_eBetaRate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(S1_eBetaRate.isSelected()) Silnik1.getData().add(DaneS1.eBetaRate);
                else Silnik1.getData().remove(DaneS1.eBetaRate);
            }
        });

        /////////////////////////////////////////////////////////////////////////////////////////////

        S2_H.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (S2_H.isSelected()) Silnik2.getData().add(DaneS2.H);
                else Silnik2.getData().remove(DaneS2.H);
            }
        });

        S2_PWM.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(S2_PWM.isSelected())Silnik2.getData().add(DaneS2.PWM);
                else Silnik2.getData().remove(DaneS2.PWM);
            }
        });

        S2_eA.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(S2_eA.isSelected())Silnik2.getData().add(DaneS2.eAlfa);
                else Silnik2.getData().remove(DaneS2.eAlfa);
            }
        });

        S2_eB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(S2_eB.isSelected())Silnik2.getData().add(DaneS2.eBeta);
                else Silnik2.getData().remove(DaneS2.eBeta);
            }
        });

        S2_rA.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(S2_rA.isSelected())Silnik2.getData().add(DaneS2.RegAlfa);
                else Silnik2.getData().remove(DaneS2.RegAlfa);
            }
        });

        S2_rB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(S2_rB.isSelected())Silnik2.getData().add(DaneS2.RegBeta);
                else Silnik2.getData().remove(DaneS2.RegBeta);
            }
        });

        S2_eAlfaRate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(S2_eAlfaRate.isSelected())Silnik2.getData().add(DaneS2.eAlfaRate);
                else Silnik2.getData().remove(DaneS2.eAlfaRate);
            }
        });

        S2_eBetaRate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(S2_eBetaRate.isSelected()) Silnik2.getData().add(DaneS2.eBetaRate);
                else Silnik2.getData().remove(DaneS2.eBetaRate);
            }
        });


        ///////////////////////////////////////////////////////////////////////////////////////////////

        S3_H.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (S3_H.isSelected()) Silnik3.getData().add(DaneS3.H);
                else Silnik3.getData().remove(DaneS3.H);
            }
        });

        S3_PWM.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(S3_PWM.isSelected())Silnik3.getData().add(DaneS3.PWM);
                else Silnik3.getData().remove(DaneS3.PWM);
            }
        });

        S3_eA.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(S3_eA.isSelected())Silnik3.getData().add(DaneS3.eAlfa);
                else Silnik3.getData().remove(DaneS3.eAlfa);
            }
        });

        S3_eB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(S3_eB.isSelected())Silnik3.getData().add(DaneS3.eBeta);
                else Silnik3.getData().remove(DaneS3.eBeta);
            }
        });

        S3_rA.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(S3_rA.isSelected())Silnik3.getData().add(DaneS3.RegAlfa);
                else Silnik3.getData().remove(DaneS3.RegAlfa);
            }
        });

        S3_rB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(S3_rB.isSelected())Silnik3.getData().add(DaneS3.RegBeta);
                else Silnik3.getData().remove(DaneS3.RegBeta);
            }
        });


        S3_eAlfaRate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(S3_eAlfaRate.isSelected())Silnik3.getData().add(DaneS3.eAlfaRate);
                else Silnik3.getData().remove(DaneS3.eAlfaRate);
            }
        });

        S3_eBetaRate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(S3_eBetaRate.isSelected())Silnik3.getData().add(DaneS3.eBetaRate);
                else Silnik3.getData().remove(DaneS3.eBetaRate);
            }
        });


        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////


        S4_H.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (S4_H.isSelected()) Silnik4.getData().add(DaneS4.H);
                else Silnik4.getData().remove(DaneS4.H);
            }
        });

        S4_PWM.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(S4_PWM.isSelected())Silnik4.getData().add(DaneS4.PWM);
                else Silnik4.getData().remove(DaneS4.PWM);
            }
        });

        S4_eA.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(S4_eA.isSelected())Silnik4.getData().add(DaneS4.eAlfa);
                else Silnik4.getData().remove(DaneS4.eAlfa);
            }
        });

        S4_eB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(S4_eB.isSelected())Silnik4.getData().add(DaneS4.eBeta);
                else Silnik4.getData().remove(DaneS4.eBeta);
            }
        });

        S4_rA.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(S4_rA.isSelected())Silnik4.getData().add(DaneS4.RegAlfa);
                else Silnik4.getData().remove(DaneS4.RegAlfa);
            }
        });

        S4_rB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(S4_rB.isSelected())Silnik4.getData().add(DaneS4.RegBeta);
                else Silnik4.getData().remove(DaneS4.RegBeta);
            }
        });


        S4_eAlfaRate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(S4_eAlfaRate.isSelected())Silnik4.getData().add(DaneS4.eAlfaRate) ;
                else Silnik4.getData().remove(DaneS4.eAlfaRate);
            }
        });

        S4_eBetaRate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(S4_eBetaRate.isSelected()) Silnik4.getData().add(DaneS4.eBetaRate);
                else  Silnik4.getData().remove(DaneS4.eBetaRate);
            }
        });


        /***************************************************************************************************/

        S1_rAlfaRate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(S1_rAlfaRate.isSelected())Silnik1.getData().add(DaneS1.RegAlfaRate);
                else Silnik1.getData().remove(DaneS1.RegAlfaRate);
            }
        });

        S1_rBetaRate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(S1_rBetaRate.isSelected())Silnik1.getData().add(DaneS1.RegBetaRate);
                else Silnik1.getData().remove(DaneS1.RegBetaRate);
            }
        });

        S2_rAlfaRate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(S2_rAlfaRate.isSelected())Silnik2.getData().add(DaneS2.RegAlfaRate);
                else Silnik2.getData().remove(DaneS2.RegAlfaRate);
            }
        });

        S2_rBetaRate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(S2_rBetaRate.isSelected())Silnik2.getData().add(DaneS2.RegBetaRate);
                else Silnik2.getData().remove(DaneS2.RegBetaRate);
            }
        });

        S3_rAlfaRate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(S3_rAlfaRate.isSelected())Silnik3.getData().add(DaneS3.RegAlfaRate);
                else Silnik3.getData().remove(DaneS3.RegAlfaRate);
            }
        });

        S3_rBetaRate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(S3_rBetaRate.isSelected())Silnik3.getData().add(DaneS3.RegBetaRate);
                else Silnik3.getData().remove(DaneS3.RegBetaRate);
            }
        });

        S4_rAlfaRate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(S4_rAlfaRate.isSelected())Silnik4.getData().add(DaneS4.RegAlfaRate);
                else Silnik4.getData().remove(DaneS4.RegAlfaRate);
            }
        });

        S4_rBetaRate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(S4_rBetaRate.isSelected())Silnik4.getData().add(DaneS4.RegBetaRate);
                else Silnik4.getData().remove(DaneS4.RegBetaRate);
            }
        });


    }



    public class SilnikDane{
        public int sample=0;
        public XYChart.Series<Number,Number> PWM = new XYChart.Series<Number,Number>();
        public XYChart.Series<Number,Number> H = new XYChart.Series<Number,Number>();
        public XYChart.Series<Number,Number> eAlfa = new XYChart.Series<Number,Number>();
        public XYChart.Series<Number,Number> eBeta = new XYChart.Series<Number,Number>();
        public XYChart.Series<Number,Number> RegAlfa = new XYChart.Series<Number,Number>();
        public XYChart.Series<Number,Number> RegBeta= new XYChart.Series<Number,Number>();
        public XYChart.Series<Number,Number> RegAlfaRate = new XYChart.Series<Number,Number>();
        public XYChart.Series<Number,Number> RegBetaRate  = new XYChart.Series<Number,Number>();
        public XYChart.Series<Number,Number> eAlfaRate = new XYChart.Series<>();
        public XYChart.Series<Number,Number> eBetaRate = new XYChart.Series<>();


        public SilnikDane(){
            for(int i =0;i<50;i++){
                PWM.getData().add(new XYChart.Data<Number,Number>(i,0));
                H.getData().add(new XYChart.Data<Number,Number>(i,0));
                eAlfa.getData().add(new XYChart.Data<Number,Number>(i,0));
                eBeta.getData().add(new XYChart.Data<Number,Number>(i,0));
                RegAlfa.getData().add(new XYChart.Data<Number,Number>(i,0));
                RegBeta.getData().add(new XYChart.Data<Number,Number>(i,0));
                RegAlfaRate.getData().add(new XYChart.Data<Number,Number>(i,0));
                RegBetaRate.getData().add(new XYChart.Data<Number,Number>(i,0));
                eAlfaRate.getData().add(new XYChart.Data<Number,Number>(i,0));
                eBetaRate.getData().add(new XYChart.Data<Number,Number>(i,0));
            }

            PWM.setName("PWM");
            H.setName("H");
            eAlfa.setName("uchyb Alfa");
            eBeta.setName("uchyb Beta");
            RegAlfa.setName("Reg Alfa");
            RegBeta.setName("Reg Beta");
            RegAlfaRate.setName("Reg Alfa Rate");
            RegBetaRate.setName("Reg Beta Rate");
            eAlfaRate.setName("uchyb Alfa Rate");
            eBetaRate.setName("uchyb Beta Rate");

        }

        public void setUchybRate(double alfa, double beta){
            eAlfaRate.getData().add(new XYChart.Data<Number,Number>(sample,alfa));
            eBetaRate.getData().add(new XYChart.Data<Number,Number>(sample,beta));
            eAlfaRate.getData().remove(0);
            eBetaRate.getData().remove(0);
        }

        public void setH(double Hx){
            H.getData().add(new XYChart.Data<Number,Number>(sample,Hx));
            H.getData().remove(0);
        }

        public void setPWM(double PWMx){
            PWM.getData().add(new XYChart.Data<Number,Number>(sample,PWMx));
            PWM.getData().remove(0);
        }

        public void seteAlfa(double uchybaAlfa){
            eAlfa.getData().add(new XYChart.Data<Number,Number>(sample,uchybaAlfa));
            eAlfa.getData().remove(0);
        }

        public void seteBeta(double uchybBeta){
            eBeta.getData().add(new XYChart.Data<Number,Number>(sample,uchybBeta));
            eBeta.getData().remove(0);
        }

        public void setRegAlfa(double regalfa){
            RegAlfa.getData().add(new XYChart.Data<Number,Number>(sample,regalfa));
            RegAlfa.getData().remove(0);
        }

        public void setRegBeta(double regbeta){
            RegBeta.getData().add(new XYChart.Data<Number,Number>(sample,regbeta));
            RegBeta.getData().remove(0);
        }

        public void setRegAlfaRate(double rAlfaRate){
            RegAlfaRate.getData().add(new XYChart.Data<Number,Number>(sample,rAlfaRate));
            RegAlfaRate.getData().remove(0);
        }

        public void setRegBetaRate(double rBetaRate){
            RegBetaRate.getData().add(new XYChart.Data<Number,Number>(sample,rBetaRate));
            RegBetaRate.getData().remove(0);
        }

        public void countSample(){
            sample++;
        }


    }



}
