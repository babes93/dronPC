package sample;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
 * Created by Patryk on 2016-07-21.
 */
public class PlikiXMLMetody  {
    private String FileName = new String();
    private PolaKonfiguracji polaKonfiguracji = new PolaKonfiguracji();

    public PlikiXMLMetody(){}

    public PlikiXMLMetody (String fileName, PolaKonfiguracji polaKonfiguracji){
        this.FileName=fileName;
        this.polaKonfiguracji=polaKonfiguracji;
    }

    public void setFileName(String fileName){
        this.FileName=fileName;
    }

    public String getFileName() {
        return FileName;
    }

    public void CreateXMLFile(){

        if(!(new File(FileName+".xml").exists())){
           // System.out.println("Tworzenie nowego pliku");
            try {

                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();


                //korzen
                Document doc = docBuilder.newDocument();
                Element rootElement = doc.createElement("Konfiguracja");
                doc.appendChild(rootElement);


                Element Polaczenie = doc.createElement("Polaczenie");
                rootElement.appendChild(Polaczenie);

                Element Port = doc.createElement("Port");
                Port.appendChild(doc.createTextNode("COM3"));
                Polaczenie.appendChild(Port);

                Element baudRate = doc.createElement("BaudRate");
                baudRate.appendChild(doc.createTextNode("460800"));
                Polaczenie.appendChild(baudRate);

                //regulatory PID        //**********************************************************************    pid
                Element Regulatory = doc.createElement("Regulatory");
                rootElement.appendChild(Regulatory);

                //skladowa H
                Element skladowaH = doc.createElement("Skladowa_H");
                skladowaH.appendChild(doc.createTextNode("300"));
                Regulatory.appendChild(skladowaH);

                //Regulator PID stabilize
                Element PIDStabilize = doc.createElement("PID_Stabilize");
                Regulatory.appendChild(PIDStabilize);

                //Regulator PID stabilize ALFA

                Element PIDStabilizeAlfa = doc.createElement("Regulator_Alfa");
                PIDStabilize.appendChild(PIDStabilizeAlfa);

                Element PIDStabilizeAlfa_P = doc.createElement("Czlon_P");
                PIDStabilizeAlfa_P.appendChild(doc.createTextNode("0"));
                PIDStabilizeAlfa.appendChild(PIDStabilizeAlfa_P);

                Element PIDStabilizeAlfa_I = doc.createElement("Czlon_I");
                PIDStabilizeAlfa_I.appendChild(doc.createTextNode("0"));
                PIDStabilizeAlfa.appendChild(PIDStabilizeAlfa_I);

                Element PIDStabilizeAlfa_D = doc.createElement("Czlon_D");
                PIDStabilizeAlfa_D.appendChild(doc.createTextNode("0"));
                PIDStabilizeAlfa.appendChild(PIDStabilizeAlfa_D);

                Element PIDStabilizeAlfa_Iup = doc.createElement("Ograniczenie_I_gorne");
                PIDStabilizeAlfa_Iup.appendChild(doc.createTextNode("2"));
                PIDStabilizeAlfa.appendChild(PIDStabilizeAlfa_Iup);

                Element PIDStabilizeAlfa_Idown = doc.createElement("Ograniczenie_I_dol");
                PIDStabilizeAlfa_Idown.appendChild(doc.createTextNode("-2"));
                PIDStabilizeAlfa.appendChild(PIDStabilizeAlfa_Idown);


                //Regulator PID beta
                Element PIDStabilizeBeta = doc.createElement("Regulator_Beta");
                PIDStabilize.appendChild(PIDStabilizeBeta);

                Element PIDStabilizeBeta_P = doc.createElement("Czlon_P");
                PIDStabilizeBeta_P.appendChild(doc.createTextNode("0"));
                PIDStabilizeBeta.appendChild(PIDStabilizeBeta_P);

                Element PIDStabilizeBeta_I = doc.createElement("Czlon_I");
                PIDStabilizeBeta_I.appendChild(doc.createTextNode("0"));
                PIDStabilizeBeta.appendChild(PIDStabilizeBeta_I);

                Element PIDStabilizeBeta_D = doc.createElement("Czlon_D");
                PIDStabilizeBeta_D.appendChild(doc.createTextNode("0"));
                PIDStabilizeBeta.appendChild(PIDStabilizeBeta_D);

                Element PIDStabilizeBeta_Iup = doc.createElement("Ograniczenie_I_gorne");
                PIDStabilizeBeta_Iup.appendChild(doc.createTextNode("2"));
                PIDStabilizeBeta.appendChild(PIDStabilizeBeta_Iup);

                Element PIDStabilizeBeta_Idown = doc.createElement("Ograniczenie_I_dol");
                PIDStabilizeBeta_Idown.appendChild(doc.createTextNode("-2"));
                PIDStabilizeBeta.appendChild(PIDStabilizeBeta_Idown);


                // pid rate

                Element PIDRate = doc.createElement("PID_Rate");
                Regulatory.appendChild(PIDRate);

                //Regulator PID rate ALFA

                Element PIDRateAlfa = doc.createElement("Regulator_Alfa");
                PIDRate.appendChild(PIDRateAlfa);

                Element PIDRateAlfa_P = doc.createElement("Czlon_P");
                PIDRateAlfa_P.appendChild(doc.createTextNode("0"));
                PIDRateAlfa.appendChild(PIDRateAlfa_P);

                Element PIDRateAlfa_I = doc.createElement("Czlon_I");
                PIDRateAlfa_I.appendChild(doc.createTextNode("0"));
                PIDRateAlfa.appendChild(PIDRateAlfa_I);

                Element PIDRateAlfa_D = doc.createElement("Czlon_D");
                PIDRateAlfa_D.appendChild(doc.createTextNode("0"));
                PIDRateAlfa.appendChild(PIDRateAlfa_D);

                Element PIDRateAlfa_Iup = doc.createElement("Ograniczenie_I_gorne");
                PIDRateAlfa_Iup.appendChild(doc.createTextNode("2"));
                PIDRateAlfa.appendChild(PIDRateAlfa_Iup);

                Element PIDRateAlfa_Idown = doc.createElement("Ograniczenie_I_dol");
                PIDRateAlfa_Idown.appendChild(doc.createTextNode("-2"));
                PIDRateAlfa.appendChild(PIDRateAlfa_Idown);


                //Regulator PID beta rate
                Element PIDRateBeta = doc.createElement("Regulator_Beta");
                PIDRate.appendChild(PIDRateBeta);

                Element PIDRateBeta_P = doc.createElement("Czlon_P");
                PIDRateBeta_P.appendChild(doc.createTextNode("0"));
                PIDRateBeta.appendChild(PIDRateBeta_P);

                Element PIDRateBeta_I = doc.createElement("Czlon_I");
                PIDRateBeta_I.appendChild(doc.createTextNode("0"));
                PIDRateBeta.appendChild(PIDRateBeta_I);

                Element PIDRateBeta_D = doc.createElement("Czlon_D");
                PIDRateBeta_D.appendChild(doc.createTextNode("0"));
                PIDRateBeta.appendChild(PIDRateBeta_D);

                Element PIDRateBeta_Iup = doc.createElement("Ograniczenie_I_gorne");
                PIDRateBeta_Iup.appendChild(doc.createTextNode("2"));
                PIDRateBeta.appendChild(PIDRateBeta_Iup);

                Element PIDRateBeta_Idown = doc.createElement("Ograniczenie_I_dol");
                PIDRateBeta_Idown.appendChild(doc.createTextNode("-2"));
                PIDRateBeta.appendChild(PIDRateBeta_Idown);

                //*************************************************************************************************     Filtry


                //filtry
                Element Filtry = doc.createElement("Filtry");
                rootElement.appendChild(Filtry);

                //srednie
                Element SredniaAcc = doc.createElement("Srednia_akcelerometr");
                SredniaAcc.appendChild(doc.createTextNode("10"));
                Filtry.appendChild(SredniaAcc);

                Element SredniaGyro = doc.createElement("Srednia_zyroskop");
                SredniaGyro.appendChild(doc.createTextNode("10"));
                Filtry.appendChild(SredniaGyro);

                Element SredniaMagnet = doc.createElement("Srednia_magnetometr");
                SredniaMagnet.appendChild(doc.createTextNode("10"));
                Filtry.appendChild(SredniaMagnet);

                Element SredniaBaro = doc.createElement("Srednia_barometr");
                SredniaBaro.appendChild(doc.createTextNode("10"));
                Filtry.appendChild(SredniaBaro);

                Element FiltrKomplementarny = doc.createElement("Filtr_Komplementarny");
                Filtry.appendChild(FiltrKomplementarny);

                Element b1_gora = doc.createElement("Parametr_b1");
                b1_gora.appendChild(doc.createTextNode("0.97"));
                FiltrKomplementarny.appendChild(b1_gora);

                Element b2_dol = doc.createElement("Parametr_b2");
                b2_dol.appendChild(doc.createTextNode("0.8"));
                FiltrKomplementarny.appendChild(b2_dol);

                Element g_prog_gora = doc.createElement("G_prog_gorny");
                g_prog_gora.appendChild(doc.createTextNode("1.1"));
                FiltrKomplementarny.appendChild(g_prog_gora);

                Element g_prog_dol = doc.createElement("G_prog_dolny");
                g_prog_dol.appendChild(doc.createTextNode("0.9"));
                FiltrKomplementarny.appendChild(g_prog_dol);
                //*********************************************************************************************Odswiezane dane

                Element OdswiezaneDane = doc.createElement("Odswiezane_Dane");
                rootElement.appendChild(OdswiezaneDane);

                Element OdswiezaneDaneSilniki = doc.createElement("Silniki_Regulatory");
                OdswiezaneDane.appendChild(OdswiezaneDaneSilniki);

                Element silnik1 = doc.createElement("Silnik_nr.1");
                silnik1.appendChild(doc.createTextNode("true"));
                OdswiezaneDaneSilniki.appendChild(silnik1);

                Element silnik2 = doc.createElement("Silnik_nr.2");
                silnik2.appendChild(doc.createTextNode("true"));
                OdswiezaneDaneSilniki.appendChild(silnik2);

                Element silnik3 = doc.createElement("Silnik_nr.3");
                silnik3.appendChild(doc.createTextNode("true"));
                OdswiezaneDaneSilniki.appendChild(silnik3);

                Element silnik4 = doc.createElement("Silnik_nr.4");
                silnik4.appendChild(doc.createTextNode("true"));
                OdswiezaneDaneSilniki.appendChild(silnik4);

                Element silnik1rate = doc.createElement("Regulator_Rate_Silnik_1");
                silnik1rate.appendChild(doc.createTextNode("true"));
                OdswiezaneDaneSilniki.appendChild(silnik1rate);

                Element silnik2rate = doc.createElement("Regulator_Rate_Silnik_2");
                silnik2rate.appendChild(doc.createTextNode("true"));
                OdswiezaneDaneSilniki.appendChild(silnik2rate);

                Element silnik3rate = doc.createElement("Regulator_Rate_Silnik_3");
                silnik3rate.appendChild(doc.createTextNode("true"));
                OdswiezaneDaneSilniki.appendChild(silnik3rate);

                Element silnik4rate = doc.createElement("Regulator_Rate_Silnik_4");
                silnik4rate.appendChild(doc.createTextNode("true"));
                OdswiezaneDaneSilniki.appendChild(silnik4rate);

                Element uchyb = doc.createElement("Uchyb");
                uchyb.appendChild(doc.createTextNode("true"));
                OdswiezaneDaneSilniki.appendChild(uchyb);

                Element uchybRate = doc.createElement("Uchyb_Rate");
                uchybRate.appendChild(doc.createTextNode("true"));
                OdswiezaneDaneSilniki.appendChild(uchybRate);

                Element PWM_Silnikow = doc.createElement("PWM");
                PWM_Silnikow.appendChild(doc.createTextNode("true"));
                OdswiezaneDaneSilniki.appendChild(PWM_Silnikow);


                //czujniki
                Element czujniki = doc.createElement("Czujniki");
                OdswiezaneDane.appendChild(czujniki);

                Element accskladowe = doc.createElement("Akcelerometr_skladowe");
                accskladowe.appendChild(doc.createTextNode("true"));
                czujniki.appendChild(accskladowe);

                Element accwypadkowy = doc.createElement("Akcelerometr_wypadkowy");
                accwypadkowy.appendChild(doc.createTextNode("true"));
                czujniki.appendChild(accwypadkowy);

                Element gyro = doc.createElement("Zyroskop");
                gyro.appendChild(doc.createTextNode("true"));
                czujniki.appendChild(gyro);

                Element magnet = doc.createElement("Magnetometr");
                magnet.appendChild(doc.createTextNode("true"));
                czujniki.appendChild(magnet);

                Element baro = doc.createElement("Barometr");
                baro.appendChild(doc.createTextNode("true"));
                czujniki.appendChild(baro);

                Element RawAccGyro = doc.createElement("Raw_Acc_Gyro");
                RawAccGyro.appendChild(doc.createTextNode("true"));
                czujniki.appendChild(RawAccGyro);

                // orientacja i przesuniecie

                Element OrientacjaPrzesuniecie = doc.createElement("Orientacja_Przesuniecie");
                OdswiezaneDane.appendChild(OrientacjaPrzesuniecie);

                Element Orientacja = doc.createElement("Orientacja_drona");
                Orientacja.appendChild(doc.createTextNode("true"));
                OrientacjaPrzesuniecie.appendChild(Orientacja);

                Element predkosciKatowe = doc.createElement("Predkosci_katowe");
                predkosciKatowe.appendChild(doc.createTextNode("true"));
                OrientacjaPrzesuniecie.appendChild(predkosciKatowe);


                //tworzenie pliku

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();

                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File(this.FileName + ".xml"));

                transformer.transform(source, result);

                //System.out.println("utworzono plik xml");


            } catch (ParserConfigurationException pce) {
                pce.printStackTrace();
            } catch (TransformerException tfe) {
                tfe.printStackTrace();
            }
        }
    }

    public void ReadXMLFile(){

        try{
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuiler = docFactory.newDocumentBuilder();
            Document doc = docBuiler.parse(FileName+".xml");

            polaKonfiguracji.PortCom = doc.getElementsByTagName("Port").item(0).getTextContent();
            polaKonfiguracji.BaudRate = doc.getElementsByTagName("BaudRate").item(0).getTextContent();

            polaKonfiguracji.SredniaAcc = doc.getElementsByTagName("Srednia_akcelerometr").item(0).getTextContent();
            polaKonfiguracji.SredniaGyro = doc.getElementsByTagName("Srednia_zyroskop").item(0).getTextContent();
            polaKonfiguracji.SredniaMagnet = doc.getElementsByTagName("Srednia_barometr").item(0).getTextContent();
            polaKonfiguracji.SredniaBaro = doc.getElementsByTagName("Srednia_barometr").item(0).getTextContent();
            polaKonfiguracji.Komplementarny_b1 = doc.getElementsByTagName("Parametr_b1").item(0).getTextContent();
            polaKonfiguracji.Komplementarny_b2 = doc.getElementsByTagName("Parametr_b2").item(0).getTextContent();
            polaKonfiguracji.Komplementarny_g1 = doc.getElementsByTagName("G_prog_gorny").item(0).getTextContent();
            polaKonfiguracji.Komplementarny_g2 = doc.getElementsByTagName("G_prog_dolny").item(0).getTextContent();
            polaKonfiguracji.PWM = doc.getElementsByTagName("PWM").item(0).getTextContent();

            polaKonfiguracji.S1 = doc.getElementsByTagName("Silnik_nr.1").item(0).getTextContent();
            polaKonfiguracji.S2 = doc.getElementsByTagName("Silnik_nr.2").item(0).getTextContent();
            polaKonfiguracji.S3 = doc.getElementsByTagName("Silnik_nr.3").item(0).getTextContent();
            polaKonfiguracji.S4 = doc.getElementsByTagName("Silnik_nr.4").item(0).getTextContent();

            polaKonfiguracji.S1_Rate = doc.getElementsByTagName("Regulator_Rate_Silnik_1").item(0).getTextContent();
            polaKonfiguracji.S2_Rate = doc.getElementsByTagName("Regulator_Rate_Silnik_2").item(0).getTextContent();
            polaKonfiguracji.S3_Rate = doc.getElementsByTagName("Regulator_Rate_Silnik_3").item(0).getTextContent();
            polaKonfiguracji.S4_Rate = doc.getElementsByTagName("Regulator_Rate_Silnik_4").item(0).getTextContent();

            polaKonfiguracji.uchybS = doc.getElementsByTagName("Uchyb").item(0).getTextContent();
            polaKonfiguracji.uchybRate = doc.getElementsByTagName("Uchyb_Rate").item(0).getTextContent();

            polaKonfiguracji.Acc = doc.getElementsByTagName("Akcelerometr_skladowe").item(0).getTextContent();
            polaKonfiguracji.AccW = doc.getElementsByTagName("Akcelerometr_wypadkowy").item(0).getTextContent();
            polaKonfiguracji.Gyro = doc.getElementsByTagName("Zyroskop").item(0).getTextContent();
            polaKonfiguracji.Magnet = doc.getElementsByTagName("Magnetometr").item(0).getTextContent();
            polaKonfiguracji.Baro = doc.getElementsByTagName("Barometr").item(0).getTextContent();
            polaKonfiguracji.AccGyroRaw = doc.getElementsByTagName("Raw_Acc_Gyro").item(0).getTextContent();

            polaKonfiguracji.Orientacja = doc.getElementsByTagName("Orientacja_drona").item(0).getTextContent();
            polaKonfiguracji.PredkoscKatowa = doc.getElementsByTagName("Predkosci_katowe").item(0).getTextContent();

            polaKonfiguracji.SkladowaH = doc.getElementsByTagName("Skladowa_H").item(0).getTextContent();



            polaKonfiguracji.RegSAlfa_P = doc.getElementsByTagName("Czlon_P").item(0).getTextContent();
            polaKonfiguracji.RegSBeta_P = doc.getElementsByTagName("Czlon_P").item(1).getTextContent();
            polaKonfiguracji.RegRAlfa_P = doc.getElementsByTagName("Czlon_P").item(2).getTextContent();
            polaKonfiguracji.RegRBeta_P = doc.getElementsByTagName("Czlon_P").item(3).getTextContent();

            polaKonfiguracji.RegSAlfa_I = doc.getElementsByTagName("Czlon_I").item(0).getTextContent();
            polaKonfiguracji.RegSBeta_I = doc.getElementsByTagName("Czlon_I").item(1).getTextContent();
            polaKonfiguracji.RegRAlfa_I = doc.getElementsByTagName("Czlon_I").item(2).getTextContent();
            polaKonfiguracji.RegRBeta_I = doc.getElementsByTagName("Czlon_I").item(3).getTextContent();

            polaKonfiguracji.RegSAlfa_D = doc.getElementsByTagName("Czlon_D").item(0).getTextContent();
            polaKonfiguracji.RegSBeta_D = doc.getElementsByTagName("Czlon_D").item(1).getTextContent();
            polaKonfiguracji.RegRAlfa_D = doc.getElementsByTagName("Czlon_D").item(2).getTextContent();
            polaKonfiguracji.RegRBeta_D = doc.getElementsByTagName("Czlon_D").item(3).getTextContent();

            polaKonfiguracji.RegSAlfa_Iup = doc.getElementsByTagName("Ograniczenie_I_gorne").item(0).getTextContent();
            polaKonfiguracji.RegSBeta_Iup = doc.getElementsByTagName("Ograniczenie_I_gorne").item(1).getTextContent();
            polaKonfiguracji.RegRAlfa_Iup = doc.getElementsByTagName("Ograniczenie_I_gorne").item(2).getTextContent();
            polaKonfiguracji.RegRBeta_Iup = doc.getElementsByTagName("Ograniczenie_I_gorne").item(3).getTextContent();

            polaKonfiguracji.RegSAlfa_Idown = doc.getElementsByTagName("Ograniczenie_I_dol").item(0).getTextContent();
            polaKonfiguracji.RegSBeta_Idown = doc.getElementsByTagName("Ograniczenie_I_dol").item(1).getTextContent();
            polaKonfiguracji.RegRAlfa_Idown = doc.getElementsByTagName("Ograniczenie_I_dol").item(2).getTextContent();
            polaKonfiguracji.RegRBeta_Idown = doc.getElementsByTagName("Ograniczenie_I_dol").item(3).getTextContent();


        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void WriteXMlFile(){


        try{

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuiler = docFactory.newDocumentBuilder();
            Document doc = docBuiler.parse(FileName+".xml");


            doc.getElementsByTagName("Port").item(0).setTextContent(polaKonfiguracji.PortCom);
            doc.getElementsByTagName("BaudRate").item(0).setTextContent(polaKonfiguracji.BaudRate);

            doc.getElementsByTagName("Srednia_akcelerometr").item(0).setTextContent(polaKonfiguracji.SredniaAcc);
            doc.getElementsByTagName("Srednia_zyroskop").item(0).setTextContent(polaKonfiguracji.SredniaGyro);
            doc.getElementsByTagName("Srednia_barometr").item(0).setTextContent(polaKonfiguracji.SredniaMagnet);
            doc.getElementsByTagName("Srednia_barometr").item(0).setTextContent(polaKonfiguracji.SredniaBaro);
            doc.getElementsByTagName("Parametr_b1").item(0).setTextContent(polaKonfiguracji.Komplementarny_b1);
            doc.getElementsByTagName("Parametr_b2").item(0).setTextContent(polaKonfiguracji.Komplementarny_b2);
            doc.getElementsByTagName("G_prog_gorny").item(0).setTextContent(polaKonfiguracji.Komplementarny_g1);
            doc.getElementsByTagName("G_prog_dolny").item(0).setTextContent(polaKonfiguracji.Komplementarny_g2);
            doc.getElementsByTagName("PWM").item(0).setTextContent(polaKonfiguracji.PWM);

            doc.getElementsByTagName("Silnik_nr.1").item(0).setTextContent(polaKonfiguracji.S1);
            doc.getElementsByTagName("Silnik_nr.2").item(0).setTextContent(polaKonfiguracji.S2);
            doc.getElementsByTagName("Silnik_nr.3").item(0).setTextContent(polaKonfiguracji.S3);
            doc.getElementsByTagName("Silnik_nr.4").item(0).setTextContent(polaKonfiguracji.S4);

            doc.getElementsByTagName("Regulator_Rate_Silnik_1").item(0).setTextContent(polaKonfiguracji.S1_Rate);
            doc.getElementsByTagName("Regulator_Rate_Silnik_2").item(0).setTextContent(polaKonfiguracji.S2_Rate);
            doc.getElementsByTagName("Regulator_Rate_Silnik_3").item(0).setTextContent(polaKonfiguracji.S3_Rate);
            doc.getElementsByTagName("Regulator_Rate_Silnik_4").item(0).setTextContent(polaKonfiguracji.S4_Rate);

            doc.getElementsByTagName("Uchyb").item(0).setTextContent(polaKonfiguracji.uchybS);
            doc.getElementsByTagName("Uchyb_Rate").item(0).setTextContent(polaKonfiguracji.uchybRate);

            doc.getElementsByTagName("Akcelerometr_skladowe").item(0).setTextContent(polaKonfiguracji.Acc);
            doc.getElementsByTagName("Akcelerometr_wypadkowy").item(0).setTextContent(polaKonfiguracji.AccW);
            doc.getElementsByTagName("Zyroskop").item(0).setTextContent(polaKonfiguracji.Gyro);
            doc.getElementsByTagName("Magnetometr").item(0).setTextContent(polaKonfiguracji.Magnet);
            doc.getElementsByTagName("Barometr").item(0).setTextContent(polaKonfiguracji.Baro);
            doc.getElementsByTagName("Raw_Acc_Gyro").item(0).setTextContent(polaKonfiguracji.AccGyroRaw);

            doc.getElementsByTagName("Orientacja_drona").item(0).setTextContent(polaKonfiguracji.Orientacja);
            doc.getElementsByTagName("Predkosci_katowe").item(0).setTextContent(polaKonfiguracji.PredkoscKatowa);

            doc.getElementsByTagName("Skladowa_H").item(0).setTextContent(polaKonfiguracji.SkladowaH);



            doc.getElementsByTagName("Czlon_P").item(0).setTextContent(polaKonfiguracji.RegSAlfa_P);
            doc.getElementsByTagName("Czlon_P").item(1).setTextContent(polaKonfiguracji.RegSBeta_P);
            doc.getElementsByTagName("Czlon_P").item(2).setTextContent(polaKonfiguracji.RegRAlfa_P);
            doc.getElementsByTagName("Czlon_P").item(3).setTextContent(polaKonfiguracji.RegRBeta_P);

            doc.getElementsByTagName("Czlon_I").item(0).setTextContent(polaKonfiguracji.RegSAlfa_I);
            doc.getElementsByTagName("Czlon_I").item(1).setTextContent(polaKonfiguracji.RegSBeta_I);
            doc.getElementsByTagName("Czlon_I").item(2).setTextContent(polaKonfiguracji.RegRAlfa_I);
            doc.getElementsByTagName("Czlon_I").item(3).setTextContent(polaKonfiguracji.RegRBeta_I);

            doc.getElementsByTagName("Czlon_D").item(0).setTextContent(polaKonfiguracji.RegSAlfa_D);
            doc.getElementsByTagName("Czlon_D").item(1).setTextContent(polaKonfiguracji.RegSBeta_D);
            doc.getElementsByTagName("Czlon_D").item(2).setTextContent(polaKonfiguracji.RegRAlfa_D);
            doc.getElementsByTagName("Czlon_D").item(3).setTextContent(polaKonfiguracji.RegRBeta_D);


            doc.getElementsByTagName("Ograniczenie_I_gorne").item(0).setTextContent(polaKonfiguracji.RegSAlfa_Iup);
            doc.getElementsByTagName("Ograniczenie_I_gorne").item(1).setTextContent(polaKonfiguracji.RegSBeta_Iup);
            doc.getElementsByTagName("Ograniczenie_I_gorne").item(2).setTextContent(polaKonfiguracji.RegRAlfa_Iup);
            doc.getElementsByTagName("Ograniczenie_I_gorne").item(3).setTextContent(polaKonfiguracji.RegRBeta_Iup);

            doc.getElementsByTagName("Ograniczenie_I_dol").item(0).setTextContent(polaKonfiguracji.RegSAlfa_Idown);
            doc.getElementsByTagName("Ograniczenie_I_dol").item(1).setTextContent(polaKonfiguracji.RegSBeta_Idown);
            doc.getElementsByTagName("Ograniczenie_I_dol").item(2).setTextContent(polaKonfiguracji.RegRAlfa_Idown);
            doc.getElementsByTagName("Ograniczenie_I_dol").item(3).setTextContent(polaKonfiguracji.RegRBeta_Idown);


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(FileName+".xml"));
            transformer.transform(source,result);

            //System.out.println("zapisano zmiany");

        }catch (ParserConfigurationException pce){
            pce.printStackTrace();
        }catch (TransformerException tfe){
            tfe.printStackTrace();
        }catch (SAXException saxe){
            saxe.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }


    }

}
