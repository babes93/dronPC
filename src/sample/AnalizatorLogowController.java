package sample;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.layout.Pane;


import java.io.File;
import java.util.Scanner;
import java.util.regex.Pattern;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 * Created by Patryk on 2016-07-12.
 */
public class AnalizatorLogowController implements Initializable {




    @FXML
    private ListView<String> ListViewZawartoscPliku;

    @FXML
    private ListView<String> ListViewWykres;

    @FXML
    private Button ButtonWybierzKatalog;

    @FXML
    private ComboBox<String> ComboBoxPliki;

    @FXML
    private Button ButtonUsun;

    @FXML
    private Button ButtonDodaj;

    @FXML
    private Pane PaneWykres;    //741x584

    @FXML
    private Slider SliderOd;

    @FXML
    private Slider SliderDo;

    @FXML
    private TextField SliderOdMinText;

    @FXML
    private TextField SliderOdMaxText;

    @FXML
    private TextField SliderDoMinText;

    @FXML
    private TextField SliderDoMaxText;

    @FXML
    private TextField ZakresOdText;

    @FXML
    private TextField ZakresDoText;

    @FXML
    private Button WyswietlZakresButton;

    @FXML
    private Button WybierzPlik;

    @FXML
    private Button ZmienSciezkeButton;

    @FXML
    private Button PlikiMatLabButton;

    @FXML
    private TextField SciezkaMatLabTextField;

    @FXML
    private Button PlikiSciLabButton;

    private NumberAxis osx = new NumberAxis();
    private NumberAxis osy = new NumberAxis();

    private final LineChart<Number,Number> Wykres = new LineChart<Number, Number>(osx,osy);


    private DirectoryChooser WyborKatalogu = new DirectoryChooser();
    private DirectoryChooser WyborKataloguMatLab = new DirectoryChooser();


    private File WybranyKatalog;
    private String SciezkaDoKatalogu = new String();

    private ObservableList<String> ListZawartoscPliku = FXCollections.observableArrayList();
    private ObservableList<String> ListWykres = FXCollections.observableArrayList();
    private ObservableList<String> ListaPliki = FXCollections.observableArrayList();

    private DaneWykres daneWykres = new DaneWykres();
    private LogiDane logiDane = new LogiDane();

    private String data = new String();

    private double MaxSliderDo;
    private double MaxSliderOd;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        PaneWykres.getChildren().add(Wykres);
        Wykres.setMinSize(740,583);
        Wykres.setMaxSize(741,584);
        Wykres.setAnimated(false);
        Wykres.setCreateSymbols(false);
        osx.setLabel("Samples");
        osx.setForceZeroInRange(false);
        osy.setForceZeroInRange(false);

        SliderDo.setMin(0);
        SliderDo.setMax(0);
        SliderOd.setMin(0);
        SliderOd.setMax(0);
        SliderDoMaxText.setEditable(false);
        SliderDoMinText.setEditable(false);
        SliderOdMinText.setEditable(false);
        SliderOdMaxText.setEditable(false);


        ComboBoxPliki.setItems(ListaPliki);

        ListViewWykres.setEditable(false);
        ListViewZawartoscPliku.setEditable(false);

        ListViewWykres.setItems(ListWykres);
        ListViewZawartoscPliku.setItems(ListZawartoscPliku);

        ButtonWybierzKatalog.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                logiDane.ClearAll();
                int tmp_ilosclini=0;
                String AktualnaSciezka = new String();
                File Aktualny = new File(".");
                try {
                    AktualnaSciezka = new java.io.File(".").getCanonicalPath();
                    Aktualny = new java.io.File(".").getCanonicalFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                WyborKatalogu.setInitialDirectory(Aktualny);

                WybranyKatalog = WyborKatalogu.showDialog(new Stage());
                if (WybranyKatalog == null) {
                    System.out.println("Nie Wybrano Katalogu");
                } else {

                    SciezkaDoKatalogu = WybranyKatalog.getAbsolutePath();
                    SciezkaMatLabTextField.setText(SciezkaDoKatalogu);



                   String[] dane1 = SciezkaDoKatalogu.split(Pattern.quote(File.separator));
                   // System.out.println(dane1.length);
                    data = dane1[dane1.length-1]+" "+dane1[dane1.length-2]+"/"+dane1[dane1.length-3]+"/"+dane1[dane1.length-4];
                    Wykres.setTitle("Dane z Logow: "+data);

                    File Pliki = new File(SciezkaDoKatalogu);
                    for (int i=0; i<Pliki.listFiles().length;i++){
                        if(Pliki.listFiles()[i].isFile()){
                                if(Pliki.listFiles()[i].getName().contains(".txt")) {
                                    ListaPliki.add(Pliki.listFiles()[i].getName().substring(0, Pliki.listFiles()[i].getName().indexOf(".txt")));
                                }
                            //wczytanie zawartosci pliku do bufora

                        }
                    }

                    //zaladowanie nazw plikow

                    for(int i=0;i<ListaPliki.size();i++)logiDane.addNewLogFile(ListaPliki.get(i));

                    //zaladowanie zmiennych do plikow
                    String WybranyPlikString = new String();
                    for(int i=0;i<logiDane.getPlikiSize();i++) {
                        try {
                            WybranyPlikString = new String(WybranyKatalog.getCanonicalPath() + "/" + logiDane.getFileNameByIndex(i)+ ".txt");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        File WybranyPlik = new File(WybranyPlikString);
                        Scanner OdczytPliku;
                        int ilosc_lini=0;
                        try {
                            OdczytPliku = new Scanner(WybranyPlik);
                            if (OdczytPliku.hasNextLine()) {
                                String linia = new String(OdczytPliku.nextLine());
                                linia =linia.replaceAll(" ","");
                                String [] parametry = linia.split(",");
                                for (int y=0;y<parametry.length;y++)logiDane.addNewParametrToFile(logiDane.getFileNameByIndex(i),parametry[y]);
                            }

                            while (OdczytPliku.hasNextLine()) {
                                ilosc_lini++;
                                String linia = new String(OdczytPliku.nextLine());
                                String []dane = linia.split(",");
                                if(logiDane.getParametrSizeInFile(logiDane.getFileNameByIndex(i))==dane.length){
                                    for(int z=0;z<dane.length;z++){
                                        String FileName = new String(logiDane.getFileNameByIndex(i));
                                        String ParametrName = new String(logiDane.getParametrNameInFileByIndex(FileName,z));
                                        logiDane.addNewDataToParametrFile(FileName,ParametrName,Double.valueOf(dane[z]));

                                    }
                                }

                            }

                            OdczytPliku.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                        if(ilosc_lini>=tmp_ilosclini)tmp_ilosclini=ilosc_lini;
                    }

                    ZakresOdText.setText("0");

                    SliderOdMinText.setText("0");
                    SliderDoMaxText.setText(Integer.toString(tmp_ilosclini-1));
                    SliderOdMaxText.setText(SliderDoMaxText.getText());
                    SliderDoMinText.setText(SliderDoMaxText.getText());
                    ZakresDoText.setText(SliderDoMaxText.getText());
                    SliderOd.setMax(tmp_ilosclini-1);
                    SliderDo.setMax(tmp_ilosclini-1);
                    SliderDo.setValue(tmp_ilosclini-1);
                    SliderOd.setValue(0);
                }


            }
        });

        ButtonDodaj.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if(ListViewZawartoscPliku.getSelectionModel().getSelectedItem()!=null) {
                    String zaznaczenie = new String(ListViewZawartoscPliku.getSelectionModel().getSelectedItem());
                    daneWykres.addNewDaneWykres(zaznaczenie);
                    //zaladowanie dnaych do daneWykres (bufor)
                    String[] tmp = zaznaczenie.split(":");
                    int DataSize = logiDane.getDataSizeInFileInParametr(tmp[0],tmp[1]);
                    for(int i=(int)SliderOd.getValue();i<(int)SliderDo.getValue();i++){
                        if(DataSize>i) {
                            Number y = logiDane.getDataByIndex(tmp[0], tmp[1], i);
                            daneWykres.addDaneWykres(zaznaczenie, i, y);
                        }
                    }

                    Wykres.getData().add(daneWykres.getDaneWykres(zaznaczenie));

                    ListWykres.add(zaznaczenie);
                    ListZawartoscPliku.remove(zaznaczenie);
                }

            }
        });

        ButtonUsun.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if(ListViewWykres.getSelectionModel().getSelectedItem()!=null){
                    String zaznaczenie = ListViewWykres.getSelectionModel().getSelectedItem();
                    String[] tmp = zaznaczenie.split(":");


                    for(int i=0;i<logiDane.getParametrSizeInFile(ComboBoxPliki.getValue());i++) {
                        if(logiDane.getParametrNameInFileByIndex(ComboBoxPliki.getValue(),i).equals(tmp[1])){
                            if(ComboBoxPliki.getValue().equals(tmp[0])) {
                                ListZawartoscPliku.add(zaznaczenie);
                            }
                        }
                    }

                    Wykres.getData().remove(daneWykres.getDaneWykres(zaznaczenie));
                    daneWykres.removeWykres(zaznaczenie);
                    ListWykres.remove(ListViewWykres.getSelectionModel().getSelectedItem());

                }
            }
        });

        ComboBoxPliki.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String WybranyPlik = new String(ComboBoxPliki.getValue());
                ListZawartoscPliku.clear();


                for(int i=0;i<logiDane.getParametrSizeInFile(WybranyPlik);i++){

                    ListZawartoscPliku.add(WybranyPlik+":"+logiDane.getParametrNameInFileByIndex(WybranyPlik,i));
                }

                for (int x=0;x<ListWykres.size();x++){
                    for(int i=0;i<logiDane.getParametrSizeInFile(WybranyPlik);i++) {
                        if (ListWykres.get(x).equals(WybranyPlik + ":"+logiDane.getParametrNameInFileByIndex(WybranyPlik,i))){
                            ListZawartoscPliku.remove(WybranyPlik+":"+logiDane.getParametrNameInFileByIndex(WybranyPlik,i));
                        }
                    }
                }


            }
        });

        SliderOd.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                SliderDo.setMin(SliderOd.getValue());
                SliderOdMaxText.setText(Integer.toString((int)SliderOd.getValue()));
                SliderDoMinText.setText(Integer.toString((int)SliderOd.getValue()));

                for(int i=0;i<ListWykres.size();i++){
                    //String[] tmp=ListWykres.get(i).split(":");
                    Wykres.getData().remove(daneWykres.getDaneWykres(ListWykres.get(i)));
                }

                daneWykres.clearDaneWykres();


                for(int i=0;i<ListWykres.size();i++){
                    String[] tmp = ListWykres.get(i).split(":");
                    int DataSize = logiDane.getDataSizeInFileInParametr(tmp[0],tmp[1]);
                    for (int x=(int)SliderOd.getValue();x<(int)SliderDo.getValue();x++){
                        if(x<DataSize){
                            Number y = logiDane.getDataByIndex(tmp[0], tmp[1], x);
                            daneWykres.addDaneWykres(ListWykres.get(i), x, y);
                        }
                    }


                }


                for(int i=0;i<ListWykres.size();i++){
                  //  String[] tmp = ListWykres.get(i).split(":");
                    Wykres.getData().add(daneWykres.getDaneWykres(ListWykres.get(i)));
                }

            }
        });

        SliderDo.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                SliderOd.setMax(SliderDo.getValue());
                SliderDoMaxText.setText(Integer.toString((int)SliderDo.getValue()));

                for(int i=0;i<ListWykres.size();i++){
                    //String[] tmp=ListWykres.get(i).split(":");
                    Wykres.getData().remove(daneWykres.getDaneWykres(ListWykres.get(i)));
                }

                daneWykres.clearDaneWykres();


                for(int i=0;i<ListWykres.size();i++){
                    String[] tmp = ListWykres.get(i).split(":");
                    int DataSize = logiDane.getDataSizeInFileInParametr(tmp[0],tmp[1]);
                    for (int x=(int)SliderOd.getValue();x<(int)SliderDo.getValue();x++){
                        if(x<DataSize){
                            Number y = logiDane.getDataByIndex(tmp[0], tmp[1], x);
                            daneWykres.addDaneWykres(ListWykres.get(i), x, y);
                        }
                    }


                }


                for(int i=0;i<ListWykres.size();i++){
                    //  String[] tmp = ListWykres.get(i).split(":");
                    Wykres.getData().add(daneWykres.getDaneWykres(ListWykres.get(i)));
                }


            }
        });

        WyswietlZakresButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                SliderOd.setValue(Double.valueOf(ZakresOdText.getText()));
                SliderDo.setValue(Double.valueOf(ZakresDoText.getText()));


                for(int i=0;i<ListWykres.size();i++){
                    //String[] tmp=ListWykres.get(i).split(":");
                    Wykres.getData().remove(daneWykres.getDaneWykres(ListWykres.get(i)));
                }
                daneWykres.clearDaneWykres();

                for(int i=0;i<ListWykres.size();i++){
                    String[] tmp = ListWykres.get(i).split(":");
                    int DataSize = logiDane.getDataSizeInFileInParametr(tmp[0],tmp[1]);
                    for (int x=Integer.valueOf(ZakresOdText.getText());x<Integer.valueOf(ZakresDoText.getText());x++){
                        if(x<DataSize){
                            Number y = logiDane.getDataByIndex(tmp[0], tmp[1], x);
                            daneWykres.addDaneWykres(ListWykres.get(i), x, y);
                        }
                    }
                }

                for(int i=0;i<ListWykres.size();i++){
                    //  String[] tmp = ListWykres.get(i).split(":");
                    Wykres.getData().add(daneWykres.getDaneWykres(ListWykres.get(i)));
                }
            }
        });

        ZmienSciezkeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                File WybranyKatalogMatLab;
                String AktualnaSciezka = new String();
                File Aktualny = new File(".");
                try {
                    AktualnaSciezka = new java.io.File(".").getCanonicalPath();
                    Aktualny = new java.io.File(".").getCanonicalFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                WyborKataloguMatLab.setInitialDirectory(Aktualny);

                WybranyKatalogMatLab = WyborKataloguMatLab.showDialog(new Stage());
                if (WybranyKatalog == null) {
                    System.out.println("Nie Wybrano Katalogu");
                } else {
                    try {
                        SciezkaMatLabTextField.setText(WybranyKatalogMatLab.getCanonicalPath());
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }

            }
        });

        PlikiMatLabButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               PlikiMatLab plikiMatLab = new PlikiMatLab(SciezkaMatLabTextField.getText(),logiDane);

                plikiMatLab.ZapisDanychLog(data);
                plikiMatLab.closeZapis();
            }
        });

        PlikiSciLabButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                PlikiSciLab plikiSciLab = new PlikiSciLab(SciezkaMatLabTextField.getText(),logiDane);
                plikiSciLab.ZapisDanychLog(data);
                plikiSciLab.closeZapis();

            }
        });


    }

    /***********************************************************************************************************************/

    private class PlikiMatLab{

        private String Sciezka = new String();
        private File PlikMatLab;
        private File PlikMatLabDane;
        private LogiDane logiDane;

        private PrintWriter ZapisPlikMatLab;
        private PrintWriter ZapisPlikMatLabDane;




        public PlikiMatLab(String sciezka,LogiDane log){
            this.logiDane=log;
            this.Sciezka=sciezka.replaceAll(Pattern.quote(File.separator),"/");
            this.PlikMatLab = new File(this.Sciezka+"/LogiMatLab.m");
            this.PlikMatLabDane = new File(this.Sciezka+"/LogiMatLabDane.m");
            try{
                this.ZapisPlikMatLab = new PrintWriter(this.PlikMatLab);
                this.ZapisPlikMatLabDane = new PrintWriter(this.PlikMatLabDane);
            }catch (IOException e){
                e.printStackTrace();
            }



        }


        private void ZapisDanychLog(String data){
            this.ZapisPlikMatLab.print("%%\n% Plik wczytujacy dane z pliku LogiMatLabDane \n% data zbierania logow: "+data+"\n\n%% \n% Opis zmiennych:");


            for(int i=0;i<logiDane.getPlikiSize();i++){
                String FileName = new String(logiDane.getFileNameByIndex(i));
                for(int x=0;x<logiDane.getParametrSizeInFile(FileName);x++){
                    String ParametrName = new String(logiDane.getParametrNameInFileByIndex(FileName,x));
                    this.ZapisPlikMatLabDane.println("\n% dane z pliku: "+FileName+" Parametr: "+ParametrName);
                    String[] param = FileName.split("_");
                    this.ZapisPlikMatLabDane.print(param[0]+"_"+ParametrName+" = [");
                    for(int z=0;z<logiDane.getDataSizeInFileInParametr(FileName,ParametrName);z++){
                            String dana = logiDane.getDataByIndex(FileName,ParametrName,z).toString();
                           // dana = dana.replace(".",",");

                        //    if(z%10==0)this.ZapisPlikMatLabDane.print("\n");
                           this.ZapisPlikMatLabDane.print(" ");
                             this.ZapisPlikMatLabDane.print(dana);
                    }
                    this.ZapisPlikMatLabDane.print(" ];\n");
                    this.ZapisPlikMatLabDane.print(param[0]+"_"+ParametrName+"_Size = ");
                    this.ZapisPlikMatLabDane.print(logiDane.getDataSizeInFileInParametr(FileName,ParametrName)+";\t%Rozmiar wektora\n");

                     this.ZapisPlikMatLab.print("\n% "+param[0]+"_"+ParametrName+" - wektor zaiwrajacy parametru :"+ParametrName+" z pliku: "+FileName+".txt");
                    this.ZapisPlikMatLab.print("\n% "+param[0]+"_"+ParametrName+"_Size - stala zawierajaca rozmiar powyzszego wektora");
                }
            }
            this.ZapisPlikMatLab.print("\n\n\n% Opcja czyszczaca workspace mozna zakomentowac \n clear all;");
            this.ZapisPlikMatLab.print("\n\n %%\n % Wczytanie danych z pliku z logami\n LogiMatLabDane;\n\n%% \n% Wlasne operacje ponizej:\n\n");
        }

        public void closeZapis(){
            this.ZapisPlikMatLab.close();
            this.ZapisPlikMatLabDane.close();
        }

    }

    private class PlikiSciLab{

        private String Sciezka = new String();
        private File PlikMatLab;
        private File PlikMatLabDane;
        private LogiDane logiDane;

        private PrintWriter ZapisPlikMatLab;
        private PrintWriter ZapisPlikMatLabDane;




        public PlikiSciLab(String sciezka,LogiDane log){
            this.logiDane=log;
            this.Sciezka=sciezka.replaceAll(Pattern.quote(File.separator),"/");
            this.PlikMatLab = new File(this.Sciezka+"/LogiSciLab.sce");
            this.PlikMatLabDane = new File(this.Sciezka+"/LogiSciLabDane.sce");
            try{
                this.ZapisPlikMatLab = new PrintWriter(this.PlikMatLab);
                this.ZapisPlikMatLabDane = new PrintWriter(this.PlikMatLabDane);
            }catch (IOException e){
                e.printStackTrace();
            }



        }


        private void ZapisDanychLog(String data){
            this.ZapisPlikMatLab.print("//\n// Plik wczytujacy dane z pliku LogiSciLabbDane \n// data zbierania logow: "+data+"\n\n// \n// Opis zmiennych:");


            for(int i=0;i<logiDane.getPlikiSize();i++){
                String FileName = new String(logiDane.getFileNameByIndex(i));
                for(int x=0;x<logiDane.getParametrSizeInFile(FileName);x++){
                    String ParametrName = new String(logiDane.getParametrNameInFileByIndex(FileName,x));
                    this.ZapisPlikMatLabDane.println("\n// dane z pliku: "+FileName+" Parametr: "+ParametrName);
                    String[] param = FileName.split("_");
                    this.ZapisPlikMatLabDane.print(param[0]+"_"+ParametrName+" = [");
                    for(int z=0;z<logiDane.getDataSizeInFileInParametr(FileName,ParametrName);z++){
                        String dana = logiDane.getDataByIndex(FileName,ParametrName,z).toString();
                        // dana = dana.replace(".",",");

                        //    if(z%10==0)this.ZapisPlikMatLabDane.print("\n");
                        this.ZapisPlikMatLabDane.print(" ");
                        this.ZapisPlikMatLabDane.print(dana);
                    }
                    this.ZapisPlikMatLabDane.print(" ];\n");
                    this.ZapisPlikMatLabDane.print(param[0]+"_"+ParametrName+"_Size = ");
                    this.ZapisPlikMatLabDane.print(logiDane.getDataSizeInFileInParametr(FileName,ParametrName)+";\t//Rozmiar wektora\n");

                    this.ZapisPlikMatLab.print("\n// "+param[0]+"_"+ParametrName+" - wektor zaiwrajacy parametru :"+ParametrName+" z pliku: "+FileName+".txt");
                    this.ZapisPlikMatLab.print("\n// "+param[0]+"_"+ParametrName+"_Size - stala zawierajaca rozmiar powyzszego wektora");
                }
            }
            this.ZapisPlikMatLab.print("\n\n\n// Opcja czyszczaca workspace mozna zakomentowac \n clear all;");
            this.ZapisPlikMatLab.print("\n\n //\n // Wczytanie danych z pliku z logami\n exec('LogiSciLabDane.sce');\n\n// \n// Wlasne operacje ponizej:\n\n");
        }

        public void closeZapis(){
            this.ZapisPlikMatLab.close();
            this.ZapisPlikMatLabDane.close();
        }

    }

    /**************************************************************************************************************************/

    private class DaneWykres{
        public ObservableList<SerieDanych> ListaDanych = FXCollections.observableArrayList();

        public void addNewDaneWykres(String nazwa){
            ListaDanych.add(new SerieDanych(nazwa));
        }

        public void removeWykres(String nazwa){
            int tmp=-1;
            for(int i=0;i<ListaDanych.size();i++){
                if(ListaDanych.get(i).Nazwa.equals(nazwa))tmp=i;
            }
            ListaDanych.remove(tmp);
        }

        public XYChart.Series<Number,Number> getDaneWykres(String nazwa){
            int tmp=-1;
            for(int i=0;i<ListaDanych.size();i++){
                if(ListaDanych.get(i).Nazwa.equals(nazwa))tmp=i;
            }
            return ListaDanych.get(tmp).SeriaDanych;
        }

        public void addDaneWykres(String nazwa,Number x, Number y){
            int tmp=-1;
            for(int i=0;i<ListaDanych.size();i++){
                if(ListaDanych.get(i).Nazwa.equals(nazwa))tmp=i;
            }
            ListaDanych.get(tmp).addData(x,y);
        }

        public void removeDaneWykres(String nazwa, int od, int do1){
            int tmp=-1;
            for(int i=0;i<ListaDanych.size();i++){
                if(ListaDanych.get(i).Nazwa.equals(nazwa))tmp=i;
            }

            ListaDanych.get(tmp).removeData(od,do1);
        }

        public void clearDaneWykres(){
            for(int i=0;i<ListaDanych.size();i++){
                ListaDanych.get(i).SeriaDanych.getData().clear();
            }
        }

    }

    private class SerieDanych{

        public String Nazwa = new String();
        public XYChart.Series<Number, Number> SeriaDanych = new XYChart.Series<Number,Number>();
        public int length=0;

        public SerieDanych(String Nazwa){
            this.Nazwa=Nazwa;
            this.SeriaDanych.setName(Nazwa);
        }

        public void addData(Number x, Number y){
            this.SeriaDanych.getData().add(new XYChart.Data<Number,Number>(x,y));
            this.length=this.SeriaDanych.getData().size();
        }

        public void removeData(int od, int do1){
            this.SeriaDanych.getData().remove(od,do1);
            this.length=this.SeriaDanych.getData().size();
        }

    }

    /**************************************************************************************************************************/

    private class LogiDane{
        public ObservableList<Pliki> Log = FXCollections.observableArrayList();

        public void addNewLogFile(String NazwaPliku){
            this.Log.add(new Pliki(NazwaPliku));
        }

        public void ClearAll(){

            for(int i=0;i<Log.size();i++){
                this.Log.get(i).ClearAll();
            }
            this.Log.clear();

        }

        public void addNewParametrToFile(String NazwaPliku,String ParametrName){
            int tmp =-1;
            for (int i=0;i<this.getPlikiSize();i++){
                if(Log.get(i).getName().equals(NazwaPliku))tmp=i;
            }
            if(tmp>=0) {
                this.Log.get(tmp).addNewParametr(ParametrName);
            }
        }

        public void addNewDataToParametrFile(String NazwaPliku,String ParametrName, Number data){
            int tmp =-1;
            for (int i=0;i<Log.size();i++){
                if(Log.get(i).getName().equals(NazwaPliku))tmp=i;
            }
            if(tmp>=0){
                this.Log.get(tmp).setParametrData(ParametrName,data);
            }
        }

        public boolean CheckExistData(String NazwaPliku, String ParametrName, int index){
            int tmp =-1;
            for (int i=0;i<Log.size();i++){
                if(Log.get(i).getName().equals(NazwaPliku))tmp=i;
            }
            if(tmp>=0) return this.Log.get(tmp).checkDataExist(ParametrName,index);
            else return false;
        }

        public Number getDataByIndex(String NazwaPliku, String ParametrName, int index){

            int tmp =-1;
            for (int i=0;i<Log.size();i++){
                if(Log.get(i).getName().equals(NazwaPliku))tmp=i;
            }
         return this.Log.get(tmp).getDataByIndex(ParametrName,index);
        }

        public int getPlikiSize(){
            return this.Log.size();
        }

        public String getFileNameByIndex(int index){
            if(index<=this.getPlikiSize())return this.Log.get(index).getName();
            else return null;
        }

        public int getParametrSizeInFile(String NazwaPliku){

            int tmp =-1;
            for (int i=0;i<Log.size();i++){
                if(Log.get(i).getName().equals(NazwaPliku))tmp=i;
            }
            return this.Log.get(tmp).getParametrySize();
        }

        public String getParametrNameInFileByIndex(String NazwaPliku, int index){
            int tmp =-1;
            for (int i=0;i<Log.size();i++){
                if(Log.get(i).getName().equals(NazwaPliku))tmp=i;
            }

            if(index<=this.Log.get(tmp).getParametrySize())return this.Log.get(tmp).Parametr.get(index).getParametr();
            else return null;
        }

        public int getDataSizeInFileInParametr(String NazwaPliku, String NazwaParametru){
            int tmp =-1;
            for (int i=0;i<Log.size();i++){
                if(Log.get(i).getName().equals(NazwaPliku))tmp=i;
            }
            return this.Log.get(tmp).getParametrDataSize(NazwaParametru);
        }



    }
    private class Pliki {

        private String Name = new String();

        public ObservableList<Parametry> Parametr = FXCollections.observableArrayList();


        public void ClearAll(){

            for(int i=0;i<Parametr.size();i++){
                Parametr.get(i).ClearBuf();
            }
            this.Parametr.clear();
        }


        public int getParametrIndexByName(String ParametrName){
            int tmp=-1;
            for(int i=0;i<this.Parametr.size();i++){
                if(this.Parametr.get(i).getParametr().equals(ParametrName))tmp=i;
            }
            return tmp;
        }


        public Pliki(String PlikName){
            this.Name = PlikName;
        }

        public int getParametrDataSize(String ParametrName){
            int tmp=-1;
            for(int i=0;i<this.Parametr.size();i++){
                if(this.Parametr.get(i).getParametr().equals(ParametrName))tmp=i;
            }
          if(tmp>=0) {
            return this.Parametr.get(tmp).Dane.size();
          }else return -1;

        }

        public int getParametrySize(){
            return this.Parametr.size();
        }

        public String getParametrNameByIndex(int index){
            if(index <= this.Parametr.size())return this.Parametr.get(index).getParametr();
            else return null;
        }

        public void addNewParametr(String ParametrName){
            this.Parametr.add(new Parametry(ParametrName));
        }

        public void setParametrData(String ParametrName, Number data){
            int tmp=-1;
            for(int i=0;i<this.Parametr.size();i++){
                if(this.Parametr.get(i).getParametr().equals(ParametrName))tmp=i;
            }
            if(tmp>=0) {
                this.Parametr.get(tmp).Dane.add(data);
            }
        }

        public boolean checkDataExist(String ParametrName, int index){
            int tmp=-1;
            for(int i=0;i<this.Parametr.size();i++){
                if(this.Parametr.get(i).getParametr().equals(ParametrName))tmp=i;
            }
            if(tmp>=0) {
            if(this.Parametr.get(tmp).length>=index)return true;
                else return false;
            }else return false;
        }

        public Number getDataByIndex(String ParametrName, int index){
            int tmp=-1;
            for(int i=0;i<this.Parametr.size();i++){
                if(this.Parametr.get(i).getParametr().equals(ParametrName))tmp=i;
            }
            return this.Parametr.get(tmp).Dane.get(index);
        }

        public void setName(String name){
            this.Name=name;
        }

        public String getName(){
            return this.Name;
        }

        public boolean egualsName(String name){
            if(this.Name.equals(name))return true;
            else return false;
        }

    }
    private class Parametry{
        private String Parametr = new String();
        public ObservableList<Number> Dane = FXCollections.observableArrayList();
       public int length=0;

        public void ClearBuf(){
            this.Dane.clear();
        }

        public Parametry(String name){
            this.Parametr=name;
        }

        public boolean checkDaneByParametr(String parametr){
            if(this.Parametr.equals(parametr))return true;
            else return false;
        }

        public Number getDaneByIndex(int index){
            return this.Dane.get(index);
        }

        public void setDane(Number number){
            this.length++;
            this.Dane.add(number);
        }

        public void setParametr(String parametr){
            this.Parametr=parametr;
        }

        public String getParametr(){
            return this.Parametr;
        }
    }

}
