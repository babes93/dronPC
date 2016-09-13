package sample;

import com.sun.org.apache.bcel.internal.generic.FLOAD;
import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import javafx.fxml.FXML;
import javafx.scene.input.TouchEvent;
import javafx.scene.shape.Box;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.Date;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.CategoryAxis;
import javafx.stage.Stage;


public class Controller implements Initializable {
    private int maxTabKomand =20;
    private byte []TablicaKomand = new byte[maxTabKomand];

    private Thread PortWatek;

    private long start_data, stop_data;
    SimpleDateFormat CzasSystemowy = new SimpleDateFormat("kk:mm:ss");
    private WykresyBufor WykBuf = new WykresyBufor();
    private WykSilnikiBuf WykS = new WykSilnikiBuf();

    private double MaxPWM = 10000;


    private File LogAccGyro;
    private PrintWriter ZapisAccGyroLog;
    private int startlog = 0;

    ObservableList<String> KonsolaDane = FXCollections.observableArrayList();
    private Buffor buffor_zapis = new Buffor();
    private Buffor buffor_odczyt = new Buffor();
    private Buffor buf_zapis = new Buffor();
    private RS232 DronPort = new RS232(buffor_odczyt);
    private AnimacjaBox DronAnimacja;
    private int start = 0;
    private int zapis2 = 0;
    private int licznik_odswiezania;
    private int timeout = 0;
    private byte[] tablica_komand = new byte[20];
    private SilnikParametry Silnik1, Silnik2, Silnik3, Silnik4;
    private int RODZAJ_LOGI = 0;

    private BuforStatycznyKomunikacji BuforStatyczny = new BuforStatycznyKomunikacji();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField PortTextField;

    @FXML
    private Button RozlaczButton;

    @FXML
    private ListView<String> KonsolaLista;

    @FXML
    private Button WyslijButton;

    @FXML
    private Button PolaczButton;

    @FXML
    private TextField WyslijTextField;

    @FXML
    private TextField BaudTextField;

    @FXML
    private ImageView ImageDron;

    @FXML
    private TextField AlfaTextField;

    @FXML
    private TextField BetaTextField;

    @FXML
    private TextField GammaTextField;

    @FXML
    private Box DronProstopadloscian;


    @FXML
    private TextField AkcelerometrWypadkowyText;

    @FXML
    private TextField AccYText;


    @FXML
    private TextField AccXText;


    @FXML
    private TextField AccZText;

    @FXML
    private TextField PWM_S1_TEXT;

    @FXML
    private TextField PWM_S2_TEXT;

    @FXML
    private TextField PWM_S3_TEXT;

    @FXML
    private TextField PWM_S4_TEXT;

    @FXML
    private Button PrawoButton;

    @FXML
    private Button DolButton;

    @FXML
    private Button SkretLewoButton;

    @FXML
    private Button LewoButton;

    @FXML
    private Button GoraButton;

    @FXML
    private Button PrzodButton;

    @FXML
    private Button TylButton;

    @FXML
    private Button SkretPrawoButton;

    @FXML
    private TextField S1_H;

    @FXML
    private TextField S2_H;

    @FXML
    private TextField S3_H;

    @FXML
    private TextField S4_H;

    @FXML
    private TextField E_ALFA_S1;

    @FXML
    private TextField E_ALFA_S2;

    @FXML
    private TextField E_ALFA_S3;

    @FXML
    private TextField E_ALFA_S4;

    @FXML
    private TextField REG_ALFA_S1;

    @FXML
    private TextField REG_ALFA_S2;

    @FXML
    private TextField REG_ALFA_S3;

    @FXML
    private TextField REG_ALFA_S4;

    @FXML
    private TextField E_BETA_S1;

    @FXML
    private TextField E_BETA_S2;

    @FXML
    private TextField E_BETA_S3;

    @FXML
    private TextField E_BETA_S4;

    @FXML
    private TextField REG_BETA_S1;

    @FXML
    private TextField REG_BETA_S2;

    @FXML
    private TextField REG_BETA_S3;

    @FXML
    private TextField REG_BETA_S4;

    @FXML
    private TextField MAGNET_X;

    @FXML
    private TextField MAGNET_Y;

    @FXML
    private TextField MAGNET_Z;

    @FXML
    private TextField BARO_T;

    @FXML
    private TextField BARO_W;

    @FXML
    private TextField BARO_P;

    @FXML
    private ProgressBar S1_prog;

    @FXML
    private ProgressBar S2_prog;

    @FXML
    private ProgressBar S3_prog;

    @FXML
    private ProgressBar S4_prog;

    @FXML
    private ProgressIndicator S1_progind;

    @FXML
    private ProgressIndicator S2_progind;

    @FXML
    private ProgressIndicator S3_progind;

    @FXML
    private ProgressIndicator S4_progind;

    @FXML
    private Button StopButton;

    @FXML
    private MenuItem MenuUstawieniaRegulatory;

    @FXML
    private MenuItem MenuWykresySilniki;

    @FXML
    private MenuItem MenuWykresyDane;

    @FXML
    private MenuItem MenuPomocInformacje;

    @FXML
    private MenuItem MenuUstawieniaOdswiezaneDane;

    @FXML
    private MenuItem MenuUstawieniaFiltry;

    @FXML
    private MenuItem MenuUstawieniaTestSilniki;

    @FXML
    private MenuItem MenuWykresyAnalizatorLogow;

    @FXML
    private Button ZapiszConfButton;

    @FXML
    private Button WczytajConfButton;

    private PolaKonfiguracji polaKonfiguracji = new PolaKonfiguracji();
    private PlikiXMLMetody plikKonfiguracja,plikTymczasowyKonfiguracja;

    private void OdswiezaneDane(){

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

        DronPort.ZmienOdswiezaneDane(maxTabKomand,TablicaKomand);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        plikKonfiguracja = new PlikiXMLMetody("Conf",polaKonfiguracji);
        plikTymczasowyKonfiguracja = new PlikiXMLMetody("tmpConf",polaKonfiguracji);

        plikTymczasowyKonfiguracja.CreateXMLFile();
        plikKonfiguracja.CreateXMLFile();

        plikTymczasowyKonfiguracja.ReadXMLFile();

        this.OdswiezaneDane();

        AlfaTextField.setText("0");
        BetaTextField.setText("0");
        GammaTextField.setText("0");

        AccXText.setText("0");
        AccYText.setText("0");
        AccZText.setText("0");
        AkcelerometrWypadkowyText.setText("0");

        Silnik1 = new SilnikParametry(S1_H, E_ALFA_S1, REG_ALFA_S1, E_BETA_S1, REG_BETA_S1, PWM_S1_TEXT);
        Silnik2 = new SilnikParametry(S2_H, E_ALFA_S2, REG_ALFA_S2, E_BETA_S2, REG_BETA_S2, PWM_S2_TEXT);
        Silnik3 = new SilnikParametry(S3_H, E_ALFA_S3, REG_ALFA_S3, E_BETA_S3, REG_BETA_S3, PWM_S3_TEXT);
        Silnik4 = new SilnikParametry(S4_H, E_ALFA_S4, REG_ALFA_S4, E_BETA_S4, REG_BETA_S4, PWM_S4_TEXT);

        PortTextField.setText(polaKonfiguracji.PortCom);
        BaudTextField.setText(polaKonfiguracji.BaudRate);
        DronAnimacja = new AnimacjaBox(DronProstopadloscian);

        Timeline odswiezanie = new Timeline(new KeyFrame(javafx.util.Duration.millis(100), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if(DronPort.StatusPortu){
                    KonsolaDane.add(CzasSystemowy.format(new Date()) + ">Otworzono port: "+DronPort.OtwarciePortu);
                    KonsolaDane.add(CzasSystemowy.format(new Date()) + ">Ustawiono parametry portu: "+DronPort.OtwarciePortu);
                    KonsolaDane.add(CzasSystemowy.format(new Date()) + ">Polaczono z portem: "+(DronPort.OtwarciePortu?(DronPort.UstawienieParametrowPortu?true:false):false));
                    DronPort.StatusPortu=false;
                    if(DronPort.WyjatekPortu)KonsolaDane.add(CzasSystemowy.format(new Date()) + ">Wyjatek Portu: "+DronPort.WyjatekPortuString);
                    DronPort.WyjatekPortu=false;
                }

                if(DronPort.DaneZapisanoWDronie){
                    KonsolaDane.add(CzasSystemowy.format(new Date()) + ">Dane zapisano w dronie");
                    DronPort.DaneZapisanoWDronie=false;
                }

               // System.out.println("A:"+BuforStatyczny.AlfaEuler+"\tB:"+BuforStatyczny.BetaEuler);
                DronAnimacja.setAlfa(BuforStatyczny.BetaEuler);
                DronAnimacja.setGamma(BuforStatyczny.AlfaEuler);
                DronAnimacja.UpDateBox();

                AlfaTextField.setText(Float.toString(BuforStatyczny.AlfaEuler));
                BetaTextField.setText(Float.toString(BuforStatyczny.BetaEuler));
                GammaTextField.setText(Float.toString(BuforStatyczny.GammaEuler));

                AccXText.setText(Float.toString(BuforStatyczny.AccX));
                AccYText.setText(Float.toString(BuforStatyczny.AccY));
                AccZText.setText(Float.toString(BuforStatyczny.AccZ));
                AkcelerometrWypadkowyText.setText(Float.toString(BuforStatyczny.AccW));

                MAGNET_X.setText(Float.toString(BuforStatyczny.MagnetX));
                MAGNET_Y.setText(Float.toString(BuforStatyczny.MagnetY));
                MAGNET_Z.setText(Float.toString(BuforStatyczny.MagnetZ));

                BARO_P.setText(Float.toString(BuforStatyczny.BaroP));
                BARO_T.setText(Float.toString(BuforStatyczny.BaroT));
                BARO_W.setText(Float.toString(BuforStatyczny.BaroW));

                Silnik1.setPWM(Short.toString(BuforStatyczny.S1_PWM));
                Silnik2.setPWM(Short.toString(BuforStatyczny.S2_PWM));
                Silnik3.setPWM(Short.toString(BuforStatyczny.S3_PWM));
                Silnik4.setPWM(Short.toString(BuforStatyczny.S4_PWM));

                S1_prog.setProgress(((double) BuforStatyczny.S1_PWM) / MaxPWM);
                S2_prog.setProgress(((double) BuforStatyczny.S2_PWM) / MaxPWM);
                S3_prog.setProgress((double) BuforStatyczny.S3_PWM / MaxPWM);
                S4_prog.setProgress((double) BuforStatyczny.S4_PWM / MaxPWM);

                S1_progind.setProgress((double) BuforStatyczny.S1_PWM / MaxPWM);
                S2_progind.setProgress((double) BuforStatyczny.S2_PWM / MaxPWM);
                S3_progind.setProgress((double) BuforStatyczny.S3_PWM / MaxPWM);
                S4_progind.setProgress((double) BuforStatyczny.S4_PWM / MaxPWM);

                Silnik1.setUchybAlfa(Float.toString(BuforStatyczny.UchybAlfa));
                Silnik2.setUchybAlfa(Float.toString((-1) * BuforStatyczny.UchybAlfa));
                Silnik3.setUchybAlfa(Float.toString(BuforStatyczny.UchybAlfa));
                Silnik4.setUchybAlfa(Float.toString((-1) * BuforStatyczny.UchybAlfa));

                Silnik1.setUchybBeta(Float.toString(BuforStatyczny.UchybBeta));
                Silnik2.setUchybBeta(Float.toString(BuforStatyczny.UchybBeta));
                Silnik3.setUchybBeta(Float.toString((-1) * BuforStatyczny.UchybBeta));
                Silnik4.setUchybBeta(Float.toString((-1) * BuforStatyczny.UchybBeta));

                Silnik1.setSkladowaH(Float.toString(BuforStatyczny.S1_H));
                Silnik1.setRegAlfa(Float.toString(BuforStatyczny.S1_RegAlfa));
                Silnik1.setRegBeta(Float.toString(BuforStatyczny.S1_RegBeta));

                Silnik2.setSkladowaH(Float.toString(BuforStatyczny.S2_H));
                Silnik2.setRegAlfa(Float.toString(BuforStatyczny.S2_RegAlfa));
                Silnik2.setRegBeta(Float.toString(BuforStatyczny.S2_RegBeta));

                Silnik3.setSkladowaH(Float.toString(BuforStatyczny.S3_H));
                Silnik3.setRegAlfa(Float.toString(BuforStatyczny.S3_RegAlfa));
                Silnik3.setRegBeta(Float.toString(BuforStatyczny.S3_RegBeta));

                Silnik4.setSkladowaH(Float.toString(BuforStatyczny.S4_H));
                Silnik4.setRegAlfa(Float.toString(BuforStatyczny.S4_RegAlfa));
                Silnik4.setRegBeta(Float.toString(BuforStatyczny.S4_RegBeta));



                /*


                            OrientacjaLog.WriteDataFloat(3, tmpf);





                            AccLog.WriteDataFloat(4, tmpl);



                            Silnik1Log.WriteDataFloat(6, tmpl);



                            Silnik2Log.WriteDataFloat(6, tmpl);


                            Silnik3Log.WriteDataFloat(6, tmpl);



                            Silnik4Log.WriteDataFloat(6, tmpl);



                            tmpf=buffor_odczyt.getFloatFromFrame();







                        case (byte) 0x8B://surowe dane acc gyro
                            tmps = buffor_odczyt.getShortFromFrame();
                            // ZapisAccGyroLog
                            //System.out.println(Short.toString(tmps[0])+","+Short.toString(tmps[1])+","+Short.toString(tmps[2])+","+Short.toString(tmps[3])+","+Short.toString(tmps[4])+","+Short.toString(tmps[5]));

                            timeout = 0;

                            break;
                        case (byte) 0x8C:
                            tmpf[0] = Float.valueOf(AccProgGText.getText());
                            tmpf[1] = Float.valueOf(AccProgDText.getText());
                            //buf_zapis.setADDR((byte)0xAA);
                            buf_zapis.setFloatInFrame(tmpf);
                            buf_zapis.setCommandRead(buf_zapis.WRITE_KONFIGURACJA_2);
                            zapis2 = 1;
                            start = 1;
                        case (byte) 0x8D:
                            start = 0;
                            zapis2 = 0;
                            start = 1;
                            break;

                        default:
                            break;


                */

            }
        }));

        odswiezanie.setCycleCount(Timeline.INDEFINITE);
        odswiezanie.play();

        KonsolaLista.setItems(KonsolaDane);

        PolaczButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DronPort.setPort(PortTextField.getText());
                DronPort.setBaud(Integer.parseInt(BaudTextField.getText()));
                DronPort.setDlugoscRamki(16);
                KonsolaDane.add(CzasSystemowy.format(new Date()) + ">Laczenie z Portem:" + PortTextField.getText());
            //    KonsolaDane.add(CzasSystemowy.format(new Date()) + ">Polaczono: " + DronPort.Polacz());
                PortWatek = new Thread(DronPort);
                DronPort.setWatek(true);
                PortWatek.start();

                polaKonfiguracji.PortCom = PortTextField.getText();
                polaKonfiguracji.BaudRate = BaudTextField.getText();

                plikTymczasowyKonfiguracja.WriteXMlFile();
            }
        });

        RozlaczButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                start = 0;
                DronPort.setWatek(false);

                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }

                DronPort.Rozlacz();
                if(PortWatek.isAlive()){
                    DronPort.setWatek(false);
                    try {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }

                }
                DronPort.Rozlacz();



                KonsolaDane.add(CzasSystemowy.format(new Date()) + ">Rozlaczono z portem: "+PortTextField.getText());

            }
        });

        WyslijButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //WyslijTextField
                short[] tmp = new short[6];
                tmp[0] = Short.decode(WyslijTextField.getText());
                KonsolaDane.add(CzasSystemowy.format(new Date()) + ">Wysylanie komendy:"+WyslijTextField.getText());
                DronPort.ZapiszDaneWDronie(buf_zapis.WRITE_CMD,tmp);
                /*
                buf_zapis.setShortInFrame(tmp);

                buf_zapis.setCommandRead(buf_zapis.WRITE_CMD);
                buf_zapis.setADDR((byte) 0xAA);
                zapis2 = 1;
                start = 1;
                */
            }
        });


        GoraButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                short []tmps= new short[6];

                DronPort.ZapiszDaneWDronie(buf_zapis.CMD_GORA,tmps);
                KonsolaDane.add(CzasSystemowy.format(new Date()) + ">Wyslanie komendy: gora");
                /*
                start = 0;
                buf_zapis.ClearBufor();
                buf_zapis.setCommandRead(buf_zapis.CMD_GORA);
                buf_zapis.setADDR((byte) 0xAA);
                zapis2 = 1;
                start = 1;
                */

            }
        });

        DolButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                short []tmps= new short[6];

                DronPort.ZapiszDaneWDronie(buf_zapis.CMD_DOL,tmps);
                KonsolaDane.add(CzasSystemowy.format(new Date()) + ">Wyslanie komendy: dol");
                /*
                start = 0;
                buf_zapis.ClearBufor();
                buf_zapis.setCommandRead(buf_zapis.CMD_DOL);
                buf_zapis.setADDR((byte) 0xAA);
                zapis2 = 1;
                start = 1;
                */
            }
        });

        StopButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                short []tmps= new short[6];

                DronPort.ZapiszDaneWDronie(buf_zapis.CMD_STOP,tmps);
                KonsolaDane.add(CzasSystemowy.format(new Date()) + ">Wyslanie komendy: STOP");
                /*
                start = 0;
                buf_zapis.ClearBufor();
                buf_zapis.setCommandRead(buf_zapis.CMD_STOP);
                buf_zapis.setADDR((byte) 0xAA);
                zapis2 = 1;
                start = 1;
                */
            }
        });

        ZapiszConfButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                plikKonfiguracja.WriteXMlFile();
                KonsolaDane.add(CzasSystemowy.format(new Date()) + ">Zapisano konfiguracje do pliku: Conf.xml");
            }
        });

        WczytajConfButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                plikKonfiguracja.ReadXMLFile();
                KonsolaDane.add(CzasSystemowy.format(new Date()) + ">Wczytano konfiguracje z pliku: Conf.xml");

            }
        });


        /********************************               MENU BAR OBS�UGA ****************************************/

        MenuUstawieniaRegulatory.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                javafx.application.Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Regulatory.fxml"));
                            Parent root1 = (Parent) fxmlLoader.load();
                            Stage stage = new Stage();
                            stage.setTitle("Ustawienia Regulatory");
                            stage.setResizable(false);
                            stage.setScene(new Scene(root1));
                            stage.getScene().getStylesheets().add("vip.css");
                            stage.show();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        KonsolaDane.add(CzasSystemowy.format(new Date()) + ">Otworzono okno z ustawieniami regulatorow");

                    }
                });
            }
        });

        MenuWykresySilniki.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                javafx.application.Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("wykSilniki.fxml"));
                            Parent root1 = (Parent) fxmlLoader.load();
                            Stage stage = new Stage();
                            stage.setTitle("Wykresy");
                            stage.setResizable(false);
                            stage.setScene(new Scene(root1));
                            stage.getScene().getStylesheets().add("vip.css");
                            stage.show();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        KonsolaDane.add(CzasSystemowy.format(new Date()) + ">Otworzono wykresy silnikow");
                    }
                });
            }
        });

        MenuWykresyDane.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                javafx.application.Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("wykresy.fxml"));
                            Parent root1 = (Parent) fxmlLoader.load();
                            Stage stage = new Stage();
                            stage.setTitle("Wykresy");
                            stage.setResizable(false);
                            stage.setScene(new Scene(root1));
                            stage.getScene().getStylesheets().add("vip.css");
                            stage.show();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        KonsolaDane.add(CzasSystemowy.format(new Date()) + ">Otworzono wykrsy z danymi");

                    }
                });
            }
        });

        MenuPomocInformacje.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                javafx.application.Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Informacje.fxml"));
                            Parent root1 = (Parent) fxmlLoader.load();
                            Stage stage = new Stage();
                            stage.setTitle("Informacje");
                            stage.setResizable(false);
                            stage.setScene(new Scene(root1));
                            stage.getScene().getStylesheets().add("vip.css");
                            stage.show();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        KonsolaDane.add(CzasSystemowy.format(new Date()) + ">Otworzono okno z Informacjami");

                    }
                });
            }
        });

        MenuUstawieniaOdswiezaneDane.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                javafx.application.Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OdswiezaneDane.fxml"));
                            Parent root1 = (Parent) fxmlLoader.load();
                            Stage stage = new Stage();
                            stage.setTitle("Odswiezane Dane");
                            stage.setResizable(false);
                            stage.setScene(new Scene(root1));
                            stage.getScene().getStylesheets().add("vip.css");
                            stage.show();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        KonsolaDane.add(CzasSystemowy.format(new Date()) + ">Otworzono okno z Odswiezanymi Danymi");

                    }
                });
            }
        });

        MenuUstawieniaFiltry.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                javafx.application.Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Filtry.fxml"));
                            Parent root1 = (Parent) fxmlLoader.load();
                            Stage stage = new Stage();
                            stage.setTitle("Filtry Ustawienia");
                            stage.setResizable(false);
                            stage.setScene(new Scene(root1));
                            stage.getScene().getStylesheets().add("vip.css");
                            stage.show();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        KonsolaDane.add(CzasSystemowy.format(new Date()) + ">Otworzono okno z Ustawieniami Filtrow");

                    }
                });

            }
        });

        MenuUstawieniaTestSilniki.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                javafx.application.Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TestSilniki.fxml"));
                            Parent root1 = (Parent) fxmlLoader.load();
                            Stage stage = new Stage();
                            stage.setTitle("Test Silniki");
                            stage.setResizable(false);
                            stage.setScene(new Scene(root1));
                            stage.getScene().getStylesheets().add("vip.css");
                            stage.show();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        KonsolaDane.add(CzasSystemowy.format(new Date()) + ">Otworzono okno z Test Silniki");

                    }
                });
            }
        });

        MenuWykresyAnalizatorLogow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                javafx.application.Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AnalizatorLogow.fxml"));
                            Parent root1 = (Parent) fxmlLoader.load();
                            Stage stage = new Stage();
                            stage.setTitle("Analizator Logow");
                            stage.setResizable(false);
                            stage.setScene(new Scene(root1));
                            stage.getScene().getStylesheets().add("vip.css");
                            stage.show();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        KonsolaDane.add(CzasSystemowy.format(new Date()) + ">Otworzono okno z Analizator Logow");

                    }
                });
            }
        });

    }
}










