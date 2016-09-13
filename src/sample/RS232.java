/**
 * Created by Babes on 2016-01-22.
 */
package sample;

import javafx.scene.control.Tab;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;


public class RS232 implements Runnable{

    //zmienna boolowska do debugowania wyswietalnie na cmd komunikacji
    private boolean DebugCMD = true;
    private boolean DebugCMDsend = false;
    // ***********************************************//
    public  boolean OtwarciePortu,UstawienieParametrowPortu;
    public boolean StatusPortu=false;
    public boolean WyjatekPortu=false;
    public String WyjatekPortuString;

    private boolean PortOtwarty=false;
    private String port = new String();
    private int baud;
    private static SerialPort serialPort;
    private int dlugoscRamki;
    private Buffor bufor=new Buffor();
    private Buffor bufor_zapis = new Buffor();
    private static Buffor buf_zapis = new Buffor();
    public boolean watek = true;

    private static int LicznikDanych=0;
    private static int MaxLicznikDanych=19;
    private long Timeout=0;
    private long MaxTimeOut=8;
    private static byte[] TablicaKomand = new byte[20];
    private boolean wyslane = false;
    private int index=0;
    private BuforStatycznyKomunikacji BuforStatyczny = new BuforStatycznyKomunikacji();
    private WykresyBufor WykBuf=new WykresyBufor();
    private WykSilnikiBuf WykS = new WykSilnikiBuf();

    private Logi Silnik1Log = new Logi();
    private Logi Silnik2Log = new Logi();
    private Logi Silnik3Log = new Logi();
    private Logi Silnik4Log = new Logi();
    private Logi OrientacjaLog = new Logi();
    private Logi AccLog = new Logi();
    private Logi MagnetLog = new Logi();
    private Logi BaroLogi = new Logi();
    private Logi AccGyroRawLogi = new Logi();


    private static boolean OdswiezanieDanych=true;
    private static boolean ZapisDanychDoDrona=false;
    public  boolean DaneZapisanoWDronie = false;

    private static boolean ZmienionoOdswiezanie=false;

    /*******************************************************************************************************************/

    public synchronized static void setZapisDanychDoDrona(Buffor bufo_zapis){
        OdswiezanieDanych=false;
        buf_zapis=bufo_zapis;
        OdswiezanieDanych=false;
        ZapisDanychDoDrona=true;

    }

    public synchronized static void ZapiszDaneWDronie(byte komenda,short []dane){
        Buffor tmp=new Buffor();
        tmp.setADDR((byte)0xAA);
        tmp.setCommandRead(komenda);
        tmp.setShortInFrame(dane);
        setZapisDanychDoDrona(tmp);

    }

    public synchronized void setOdswiezanieDanych(){
        this.setTablicaKomandFull();
        this.OdswiezanieDanych=true;
    }

    public synchronized void pausaOdswiezanieDanych(){
        this.OdswiezanieDanych=false;
    }


    private void OdebraneDane(){
       // System.out.println("Odebrano komende:"+bufor.getCOMAND()+ "\tpo czasie:"+(System.currentTimeMillis()-Timeout));
        this.Timeout=0;
       if(this.OdswiezanieDanych) {
           this.LicznikDanych++;
           if (this.LicznikDanych == this.MaxLicznikDanych) this.LicznikDanych = 0;
       }
        if(this.ZapisDanychDoDrona){
            this.setOdswiezanieDanych();
            this.ZapisDanychDoDrona=false;
            this.DaneZapisanoWDronie=true;
        }
        this.wyslane=false;
    }

    public void setMaxTimeOut(long Timeoutms){
        this.MaxTimeOut=Timeoutms;
    }

    public void setWatek(boolean watek){
        this.watek=watek;
    }

    @Override
    public void run() {

        serialPort=new SerialPort(port);
        OtwarciePortu=false;
        UstawienieParametrowPortu=false;



        try{
            OtwarciePortu=serialPort.openPort();
            UstawienieParametrowPortu= serialPort.setParams(baud, 8, 1, 0);
            this.StatusPortu=true;
            if(OtwarciePortu) {
                serialPort.setEventsMask(SerialPort.MASK_RXCHAR);
                serialPort.addEventListener(new SerialPortReader(bufor));
                serialPort.setDTR(false);
            }
        }
        catch (SerialPortException ex){
            System.out.println(ex.toString());
            WyjatekPortuString=new String(ex.toString());
            this.StatusPortu=true;
            this.WyjatekPortu=true;
        }
        PortOtwarty=OtwarciePortu?(UstawienieParametrowPortu?true:false):false;
        if(PortOtwarty) {
            while (watek) {
                // try {
                //  Thread.sleep(1);
                Komunikacja();
                // }
                //  catch (InterruptedException e){
                //      e.printStackTrace();
                //  }
            }

            try {
                serialPort.closePort();
            } catch (SerialPortException ex) {
                System.out.println(ex);
            }

            Silnik1Log.CloseLogFile();
            Silnik2Log.CloseLogFile();
            Silnik3Log.CloseLogFile();
            Silnik4Log.CloseLogFile();
            OrientacjaLog.CloseLogFile();
            AccLog.CloseLogFile();
            MagnetLog.CloseLogFile();
            BaroLogi.CloseLogFile();
            AccGyroRawLogi.CloseLogFile();

        }
    }

    public RS232(){}

    public RS232(Buffor buffor_odczyt){
        this.bufor=buffor_odczyt;
        this.setTablicaKomandFull();
        this.bufor_zapis.setADDR((byte)0xAA);

        Silnik1Log.StartZapis();

        Silnik1Log.CreateLogFile("Silnik1");
        Silnik2Log.CreateLogFile("Silnik2");
        Silnik3Log.CreateLogFile("Silnik3");
        Silnik4Log.CreateLogFile("Silnik4");
        OrientacjaLog.CreateLogFile("Orientacja");
        AccLog.CreateLogFile("Akcelerometr");
        MagnetLog.CreateLogFile("Magnetometr");
        BaroLogi.CreateLogFile("Barometr");
        AccGyroRawLogi.CreateLogFile("AccGyroRaw");

        Silnik1Log.WriteLineToLogFile("Skladowa H, Regulator Alfa, Regulator Beta, Uchyb Alfa, Uchyb Beta, PWM, Reg Alfa Rate, Reg Beta Rate, Uchyb Alfa Rate, Uchyb Beta Rate");
        Silnik2Log.WriteLineToLogFile("Skladowa H, Regulator Alfa, Regulator Beta, Uchyb Alfa, Uchyb Beta, PWM, Reg Alfa Rate, Reg Beta Rate, Uchyb Alfa Rate, Uchyb Beta Rate");
        Silnik3Log.WriteLineToLogFile("Skladowa H, Regulator Alfa, Regulator Beta, Uchyb Alfa, Uchyb Beta, PWM, Reg Alfa Rate, Reg Beta Rate, Uchyb Alfa Rate, Uchyb Beta Rate");
        Silnik4Log.WriteLineToLogFile("Skladowa H, Regulator Alfa, Regulator Beta, Uchyb Alfa, Uchyb Beta, PWM, Reg Alfa Rate, Reg Beta Rate, Uchyb Alfa Rate, Uchyb Beta Rate");
        OrientacjaLog.WriteLineToLogFile("Alfa, Beta, Gamma, Alfa Rate, Beta Rate, Gamma Rate");
        AccLog.WriteLineToLogFile("X, Y, Z, Dlugosc wektora");
        MagnetLog.WriteLineToLogFile("X, Y, Z");
        BaroLogi.WriteLineToLogFile("T, P, W");
        AccGyroRawLogi.WriteLineToLogFile("ACC X, ACC Y, ACC Z, GYRO X, GYRO Y, GYRO Z");
    }

    public synchronized static void ZmienOdswiezaneDane(int length,byte []data){

        OdswiezanieDanych = false;
        try {
            Thread.sleep(500);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        for(int i=0;i<length;i++){
            TablicaKomand[i]=data[i];
        }
        LicznikDanych=0;
        MaxLicznikDanych=length;
        OdswiezanieDanych=true;
        ZmienionoOdswiezanie=true;
    }

    private void setTablicaKomandFull(){
        if(this.ZmienionoOdswiezanie){

        }
        else {
            TablicaKomand[0] = bufor.READ_ANGLE;
            TablicaKomand[1] = bufor.READ_ACC;
            TablicaKomand[2] = bufor.READ_ACC_W;
            TablicaKomand[3] = bufor.READ_PWM;
            TablicaKomand[4] = bufor.READ_UCHYB;
            TablicaKomand[5] = bufor.READ_S1;
            TablicaKomand[6] = bufor.READ_S2;
            TablicaKomand[7] = bufor.READ_S3;
            TablicaKomand[8] = bufor.READ_S4;
            TablicaKomand[9] = bufor.LOGI_ACC_GYRO;
            TablicaKomand[10] = bufor.READ_GYRO_FILTR;
            TablicaKomand[11] = bufor.READ_MAGNET_S;
            TablicaKomand[12] = bufor.READ_BARO;
            TablicaKomand[13] = bufor.READ_S1_REG_RATE;
            TablicaKomand[14] = bufor.READ_S2_REG_RATE;
            TablicaKomand[15] = bufor.READ_S3_REG_RATE;
            TablicaKomand[16] = bufor.READ_S4_REG_RATE;
            TablicaKomand[17] = bufor.READ_ORIENTACJA_RATE;
            TablicaKomand[18] = bufor.READ_UCHYB_RATE;
        }
    }

    private void Komunikacja() {

        if (bufor.Data_ready) {
            float[] tmpl = new float[6];
            float[] tmpf = new float[3];
            float[] tmpf3 = new float[10];
            short[] tmps = new short[6];
            switch (bufor.getCOMAND()) {
                case 0x01://katy alfa beta gamma
                    this.OdebraneDane();
                    tmpf = bufor.getFloatFromFrame();
                    BuforStatyczny.setKatyEulera(tmpf[0],tmpf[1],tmpf[2]);
                    tmpf3[0]=tmpf[0];
                    tmpf3[1]=tmpf[1];
                    tmpf3[2]=tmpf[2];
                    tmpf3[3]=BuforStatyczny.AlfaRate;
                    tmpf3[4]=BuforStatyczny.BetaRate;
                    tmpf3[5]=BuforStatyczny.GammaRate;
                    OrientacjaLog.WriteDataFloat(6,tmpf3);
                    WykBuf.setAlfa(tmpf3[0]);
                    WykBuf.setBeta(tmpf3[1]);
                    WykBuf.setGamma(tmpf3[2]);
                    WykBuf.ore = true;
                   if(DebugCMD)System.out.println("Odebrano katy Eulera:\tAlfa:"+BuforStatyczny.AlfaEuler+"\tBeta:"+BuforStatyczny.BetaEuler+"\tGamma:"+BuforStatyczny.GammaEuler);

                    break;
                case 0x02: //odczyt akcelerometru X Y Z
                    this.OdebraneDane();
                    tmpf = bufor.getFloatFromFrame();
                    BuforStatyczny.setAccXYZ(tmpf[0],tmpf[1],tmpf[2]);



                    WykBuf.setAccX(tmpf[0]);
                    WykBuf.setAccY(tmpf[1]);
                    WykBuf.setAccZ(tmpf[2]);
                    if(DebugCMD)System.out.println("Odebrano ACC:\tX:"+BuforStatyczny.AccX+"\tY:"+BuforStatyczny.AccY+"\tZ:"+BuforStatyczny.AccZ);

                    break;
                case 0x03://odczyt wypadkowej akcelerometr wypadkowy
                    this.OdebraneDane();
                    tmpf = bufor.getFloatFromFrame();
                    BuforStatyczny.setAccW(tmpf[0]);
                    float []tmpf2 = new float[4];
                    tmpf2[0]=BuforStatyczny.AccX;
                    tmpf2[1]=BuforStatyczny.AccY;
                    tmpf2[2]=BuforStatyczny.AccZ;
                    tmpf2[3]=tmpf[0];
                    AccLog.WriteDataFloat(4,tmpf2);

                    WykBuf.setAccW(tmpf[0]);
                    WykBuf.accs = true;
                    if(DebugCMD)System.out.println("Odebrano Acc Wypadkowy:\tW:"+BuforStatyczny.AccW);

                    break;
                case 0x07: //odczyt pwm S1 S2 S3 S4
                    this.OdebraneDane();
                    tmps = bufor.getShortFromFrame();

                    for (int i = 0; i < 4; i++) {
                        if (tmps[i] >= 8500) tmps[i] -= 8500;
                    }

                    BuforStatyczny.setPWM(tmps[0],tmps[1],tmps[2],tmps[3]);
                    if(DebugCMD)System.out.println("Odebrano PWM:\tS1:"+BuforStatyczny.S1_PWM+"\tS2:"+BuforStatyczny.S2_PWM+"\tS3:"+BuforStatyczny.S3_PWM+"\tS4:"+BuforStatyczny.S4_PWM);


                    break;

                case 0x08:  //Uchyb alfa i beta dla s1 s2 s3 s4
                    this.OdebraneDane();
                    tmpf = bufor.getFloatFromFrame();
                    BuforStatyczny.setUchyb(tmpf[0],tmpf[1]);
                    if(DebugCMD)System.out.println("Odebrano uchyb:\tAlfa:"+BuforStatyczny.UchybAlfa+"\tBeta:"+BuforStatyczny.UchybBeta);

                    break;
                case 0x09:  //skladowa Silnik1  H RegAlfa RegBeta
                    this.OdebraneDane();
                    tmpf = bufor.getFloatFromFrame();
                    BuforStatyczny.setS1Reg(tmpf[0],tmpf[1],tmpf[2]);

                    WykS.H_S1=tmpf[0];
                    WykS.rAlfa_S1=tmpf[1];
                    WykS.rBeta_S1=tmpf[2];
                    WykS.eAlfa_S1=BuforStatyczny.UchybAlfa;
                    WykS.eBeta_S1=BuforStatyczny.UchybBeta;
                    WykS.PWM_S1=BuforStatyczny.S1_PWM;
                    WykS.s1=true;

                    tmpf3[0]=tmpf[0];
                    tmpf3[1]=tmpf[1];
                    tmpf3[2]=tmpf[2];
                    tmpf3[3]=BuforStatyczny.UchybAlfa;
                    tmpf3[4]=BuforStatyczny.UchybBeta;
                    tmpf3[5]=BuforStatyczny.S1_PWM;
                    tmpf3[6]=BuforStatyczny.S1_RegAlfa_Rate;
                    tmpf3[7]=BuforStatyczny.S1_RegBeta_Rate;
                    tmpf3[8]=BuforStatyczny.AlfaUchybRate;
                    tmpf3[9]=BuforStatyczny.BetaUchybRate;

                    Silnik1Log.WriteDataFloat(10,tmpf3);

                    if(DebugCMD)System.out.println("Odebrano S1:\tH:"+BuforStatyczny.S1_H+"\tRegAlfa:"+BuforStatyczny.S1_RegAlfa+"\tRegBeta:"+BuforStatyczny.S1_RegBeta);

                    break;
                case 0x0A:  //skladowa Silnik2 H RegAlfa RegBeta
                    this.OdebraneDane();
                    tmpf = bufor.getFloatFromFrame();
                    BuforStatyczny.setS2Reg(tmpf[0],tmpf[1],tmpf[2]);
                    WykS.H_S2=tmpf[0];
                    WykS.rAlfa_S2=tmpf[1];
                    WykS.rBeta_S2=tmpf[2];
                    WykS.eAlfa_S2=-1*BuforStatyczny.UchybAlfa;
                    WykS.eBeta_S2=BuforStatyczny.UchybBeta;
                    WykS.PWM_S2=BuforStatyczny.S2_PWM;
                    WykS.s2=true;

                    tmpf3[0]=tmpf[0];
                    tmpf3[1]=tmpf[1];
                    tmpf3[2]=tmpf[2];
                    tmpf3[3]=-1*BuforStatyczny.UchybAlfa;
                    tmpf3[4]=BuforStatyczny.UchybBeta;
                    tmpf3[5]=BuforStatyczny.S2_PWM;
                    tmpf3[6]=BuforStatyczny.S2_RegAlfa_Rate;
                    tmpf3[7]=BuforStatyczny.S2_RegBeta_Rate;
                    tmpf3[8]=BuforStatyczny.AlfaUchybRate;
                    tmpf3[9]=BuforStatyczny.BetaUchybRate;
                    Silnik2Log.WriteDataFloat(10,tmpf3);

                    if(DebugCMD)System.out.println("Odebrano S2:\tH:"+BuforStatyczny.S2_H+"\tRegAlfa:"+BuforStatyczny.S2_RegAlfa+"\tRegBeta:"+BuforStatyczny.S2_RegBeta);

                    break;
                case 0x0B: //skladowa Silnik3 H RegAlfa RegBeta
                    this.OdebraneDane();
                    tmpf = bufor.getFloatFromFrame();
                    BuforStatyczny.setS3Reg(tmpf[0],tmpf[1],tmpf[2]);
                    WykS.H_S3=tmpf[0];
                    WykS.rAlfa_S3=tmpf[1];
                    WykS.rBeta_S3=tmpf[2];
                    WykS.eAlfa_S3=BuforStatyczny.UchybAlfa;
                    WykS.eBeta_S3=-1*BuforStatyczny.UchybBeta;
                    WykS.PWM_S3=BuforStatyczny.S3_PWM;
                    WykS.s3=true;

                    tmpf3[0]=tmpf[0];
                    tmpf3[1]=tmpf[1];
                    tmpf3[2]=tmpf[2];
                    tmpf3[3]=BuforStatyczny.UchybAlfa;
                    tmpf3[4]=-1*BuforStatyczny.UchybBeta;
                    tmpf3[5]=BuforStatyczny.S3_PWM;
                    tmpf3[6]=BuforStatyczny.S3_RegAlfa_Rate;
                    tmpf3[7]=BuforStatyczny.S3_RegBeta_Rate;
                    tmpf3[8]=BuforStatyczny.AlfaUchybRate;
                    tmpf3[9]=BuforStatyczny.BetaUchybRate;

                    Silnik3Log.WriteDataFloat(10,tmpf3);

                    if(DebugCMD)System.out.println("Odebrano S3:\tH:"+BuforStatyczny.S3_H+"\tRegAlfa:"+BuforStatyczny.S3_RegAlfa+"\tRegBeta:"+BuforStatyczny.S3_RegBeta);

                    break;

                case 0x0C: //skladowa silnik4 H RegAlfa RegBeta
                    this.OdebraneDane();
                    tmpf = bufor.getFloatFromFrame();
                    BuforStatyczny.setS4Reg(tmpf[0],tmpf[1],tmpf[2]);
                    WykS.H_S4=tmpf[0];
                    WykS.rAlfa_S4=tmpf[1];
                    WykS.rBeta_S4=tmpf[2];
                    WykS.eAlfa_S4=-1*BuforStatyczny.UchybAlfa;
                    WykS.eBeta_S4=-1*BuforStatyczny.UchybBeta;
                    WykS.PWM_S4=BuforStatyczny.S4_PWM;
                    WykS.s4=true;

                    tmpf3[0]=tmpf[0];
                    tmpf3[1]=tmpf[1];
                    tmpf3[2]=tmpf[2];
                    tmpf3[3]=-1*BuforStatyczny.UchybAlfa;
                    tmpf3[4]=-1*BuforStatyczny.UchybBeta;
                    tmpf3[5]=BuforStatyczny.S4_PWM;
                    tmpf3[6]=BuforStatyczny.S4_RegAlfa_Rate;
                    tmpf3[7]=BuforStatyczny.S4_RegBeta_Rate;
                    tmpf3[8]=BuforStatyczny.AlfaUchybRate;
                    tmpf3[9]=BuforStatyczny.BetaUchybRate;
                    Silnik4Log.WriteDataFloat(10,tmpf3);

                    if(DebugCMD)System.out.println("Odebrano S4:\tH:"+BuforStatyczny.S4_H+"\tRegAlfa:"+BuforStatyczny.S4_RegAlfa+"\tRegBeta:"+BuforStatyczny.S4_RegBeta);

                    break;

                case 0x0E://odczyt srednich kroczacych Srednia Acc Gyro Magner Baro Komplementacrny b1 b2
                    this.OdebraneDane();
                    tmps = bufor.getShortFromFrame();
                    BuforStatyczny.setSrednie(tmps[0],tmps[1],tmps[2],tmps[3]);

                    //   buf_zapis.setCommandRead(buf_zapis.READ_KONGIGURACJA_2);
                    //   zapis2 = 1;
                    //   start = 1;

                    break;

                case 0x0F://odczyt srednich kroczacych konfiguracja prog acc gora i dol
                    this.OdebraneDane();
                    tmpf = bufor.getFloatFromFrame();
                    //   start = 0;
                    //    zapis2 = 0;
                    //     start = 1;


                    break;

                case (byte) 0x10: //zapis uchyb
                    this.OdebraneDane();
                    tmpf = bufor.getFloatFromFrame();


                    break;

                case (byte) 0x11:     // gyro x y z
                    this.OdebraneDane();
                    tmpf = bufor.getFloatFromFrame();
                    BuforStatyczny.setGyroXYZ(tmpf[0],tmpf[1],tmpf[2]);
                    WykBuf.setGyroX(tmpf[0]);
                    WykBuf.setGyroY(tmpf[1]);
                    WykBuf.setGyro(tmpf[2]);
                    WykBuf.gyros=true;
                    if(DebugCMD)System.out.println("Odebrano Gyro:\tX:"+BuforStatyczny.GyroX+"\tY:"+BuforStatyczny.GyroY+"\tZ:"+BuforStatyczny.GyroZ);

                    break;

                case (byte) 0x12: //magnet x y z
                    this.OdebraneDane();
                    tmpf = bufor.getFloatFromFrame();
                    BuforStatyczny.setMagnetXYZ(tmpf[0],tmpf[1],tmpf[2]);

                    WykBuf.MAGNET_X=tmpf[0];
                    WykBuf.MAGNET_Y=tmpf[1];
                    WykBuf.MAGNET_Z=tmpf[2];
                    WykBuf.magnet=true;

                    MagnetLog.WriteDataFloat(3,tmpf);

                    if(DebugCMD)System.out.println("Odebrano Magnet:\tX:"+BuforStatyczny.MagnetX+"\tY:"+BuforStatyczny.MagnetY+"\tZ:"+BuforStatyczny.MagnetZ);
                    break;
                case (byte) 0x13: //baro t p w
                    this.OdebraneDane();
                    tmpf = bufor.getFloatFromFrame();
                    BuforStatyczny.setBaro(tmpf[0],tmpf[1],tmpf[2]);
                    WykBuf.BARO_T=tmpf[0];
                    WykBuf.BARO_P=tmpf[1];
                    WykBuf.BARO_W=tmpf[2];

                    BaroLogi.WriteDataFloat(3,tmpf);

                    WykBuf.baro=true;
                    if(DebugCMD)System.out.println("Odebrano Baro:\tT:"+BuforStatyczny.BaroT+"\tP:"+BuforStatyczny.BaroP+"\tW:"+BuforStatyczny.BaroW);

                    break;

                case (byte) 0x14: //Silnik 1 Reg Rate
                    this.OdebraneDane();
                    tmpf = bufor.getFloatFromFrame();
                    BuforStatyczny.setS1RegRate(tmpf[0],tmpf[1]);
                    WykSilnikiBuf.rAlfaRate_S1=tmpf[0];
                    WykSilnikiBuf.rBetaRate_S1=tmpf[1];

                    WykSilnikiBuf.s1Rate=true;
                    if(DebugCMD)System.out.println("Odebrano S1 Rate:\tAlfa Rate:"+BuforStatyczny.S1_RegAlfa_Rate+"\tBeta Rate:"+BuforStatyczny.S1_RegBeta_Rate);
                    break;

                case (byte) 0x15: //Silnik 2 Reg Rate
                    this.OdebraneDane();
                    tmpf = bufor.getFloatFromFrame();
                    BuforStatyczny.setS2RegRate(tmpf[0],tmpf[1]);
                    WykSilnikiBuf.rAlfaRate_S2=tmpf[0];
                    WykSilnikiBuf.rBetaRate_S2=tmpf[1];

                    WykSilnikiBuf.s2Rate=true;
                    if(DebugCMD)System.out.println("Odebrano S2 Rate:\tAlfa Rate:"+BuforStatyczny.S2_RegAlfa_Rate+"\tBeta Rate:"+BuforStatyczny.S2_RegBeta_Rate);
                    break;

                case (byte) 0x16: //Silnik 3 Reg Rate
                    this.OdebraneDane();
                    tmpf = bufor.getFloatFromFrame();
                    BuforStatyczny.setS3RegRate(tmpf[0],tmpf[1]);
                    WykSilnikiBuf.rAlfaRate_S3=tmpf[0];
                    WykSilnikiBuf.rBetaRate_S3=tmpf[1];

                    WykSilnikiBuf.s3Rate=true;
                    if(DebugCMD)System.out.println("Odebrano S3 Rate:\tAlfa Rate:"+BuforStatyczny.S3_RegAlfa_Rate+"\tBeta Rate:"+BuforStatyczny.S3_RegBeta_Rate);
                    break;

                case (byte) 0x17: //Silnik 4 Reg Rate
                    this.OdebraneDane();
                    tmpf = bufor.getFloatFromFrame();
                    BuforStatyczny.setS4RegRate(tmpf[0],tmpf[1]);
                    WykSilnikiBuf.rAlfaRate_S4=tmpf[0];
                    WykSilnikiBuf.rBetaRate_S4=tmpf[1];

                    WykSilnikiBuf.s4Rate=true;
                    if(DebugCMD)System.out.println("Odebrano S4 Rate:\tAlfa Rate:"+BuforStatyczny.S4_RegAlfa_Rate+"\tBeta Rate:"+BuforStatyczny.S4_RegBeta_Rate);
                    break;

                case (byte) 0x18: //Orientacja Rate
                    this.OdebraneDane();
                    tmpf = bufor.getFloatFromFrame();
                    BuforStatyczny.setEulerRate(tmpf[0],tmpf[1],tmpf[2]);
                    WykBuf.setEulerRate(tmpf[0],tmpf[1],tmpf[2]);
                    WykBuf.EulerRate=true;
                    if(DebugCMD)System.out.println("Odebrano Euler Rate: \tAlfa:"+BuforStatyczny.AlfaRate+"\tBeta:"+BuforStatyczny.BetaRate+"\tGamma:"+BuforStatyczny.GammaRate);
                    break;

                case (byte) 0x19:
                    this.OdebraneDane();
                    tmpf=bufor.getFloatFromFrame();
                    BuforStatyczny.setUchybRate(tmpf[0],tmpf[1]);

                    WykSilnikiBuf.eAlfaRate_S1=tmpf[0];
                    WykSilnikiBuf.eAlfaRate_S2=tmpf[0];
                    WykSilnikiBuf.eAlfaRate_S3=tmpf[0];
                    WykSilnikiBuf.eAlfaRate_S4=tmpf[0];

                    WykSilnikiBuf.eBetaRate_S1=tmpf[1];
                    WykSilnikiBuf.eBetaRate_S2=tmpf[1];
                    WykSilnikiBuf.eBetaRate_S3=tmpf[1];
                    WykSilnikiBuf.eBetaRate_S4=tmpf[1];

                    WykSilnikiBuf.uchyb_rate=true;
                    if(DebugCMD)System.out.println("Odebrano uchyby Rate:\tAlfaRate: "+tmpf[0]+"\tBetaRate:"+tmpf[1]);
                    break;

                case (byte) 0x81:
                    this.OdebraneDane();
                    //   start = 0;
                    //    zapis2 = 0;
                    //    start = 1;


                    break;
                case (byte) 0x82:
                    this.OdebraneDane();
                    //   start = 0;
                    //   zapis2 = 0;
                    //   start = 1;


                    break;
                case (byte) 0x83: //komedna stop
                    this.OdebraneDane();
                    //    start = 0;
                    //    zapis2 = 0;
                    //    start = 1;


                    break;
                case (byte) 0x84: //komenda gora
                    this.OdebraneDane();
                    //    start = 0;
                    //   zapis2 = 0;
                    //  start = 1;


                    break;
                case (byte) 0x85://komenda dol
                    this.OdebraneDane();
                    //   start = 0;
                    //   zapis2 = 0;
                    //   start = 1;


                    break;

                case (byte) 0x87://ustawienie stalej H
                    this.OdebraneDane();
                    //   start = 0;
                    //   zapis2 = 0;
                    //   start = 1;


                    break;

                case (byte) 0x88: //regulator/pwm
                    this.OdebraneDane();
                    //   start = 0;
                    //   zapis2 = 0;
                    //   start = 1;


                    break;

                case (byte) 0x89: //zapis danych reg alfa
                    this.OdebraneDane();
                    //   start = 0;
                    //   zapis2 = 0;
                    //   start = 1;


                    break;
                case (byte) 0x8A://zapis danych reg beta
                    this.OdebraneDane();
                    //   start = 0;
                    //   zapis2 = 0;
                    //   start = 1;


                    break;
                case (byte) 0x0D:
                    this.OdebraneDane();
                    //   start = 0;
                    //   zapis2 = 0;
                    //   start = 1;



                    break;

                case (byte) 0x8B://surowe dane acc x y z gyro x y z
                    this.OdebraneDane();
                    tmps = bufor.getShortFromFrame();
                    // ZapisAccGyroLog
                    //System.out.println(Short.toString(tmps[0])+","+Short.toString(tmps[1])+","+Short.toString(tmps[2])+","+Short.toString(tmps[3])+","+Short.toString(tmps[4])+","+Short.toString(tmps[5]));

                    WykBuf.setAccRawX(((double) tmps[0]) * WykBuf.acck);
                    WykBuf.setAccRawY(((double) tmps[1]) * WykBuf.acck);
                    WykBuf.setAccRawZ(((double) tmps[2]) * WykBuf.acck);

                    WykBuf.setGyroRawX(((double) tmps[3]) * WykBuf.gyrok);
                    WykBuf.setGyroRawY(((double) tmps[4]) * WykBuf.gyrok);
                    WykBuf.setGyroRawZ(((double) tmps[5]) * WykBuf.gyrok);
                    WykBuf.raws = true;

                    AccGyroRawLogi.WriteDataShort(6,tmps);

                    break;
                case (byte) 0x8C: //zapis progow acc gora i dol
                    this.OdebraneDane();
                    break;
                case (byte) 0x8D:
                    this.OdebraneDane();
                    //  start = 0;
                    //    zapis2 = 0;
                    //    start = 1;
                    break;
                case (byte) 0x8E: // zapis reg alfa pid rate
                    this.OdebraneDane();
                    break;
                case (byte) 0x8F: // zapis reg beta pid rate
                    this.OdebraneDane();
                    break;

                default:
                    break;


            }
        }


            //Wysylanie danych
            if (!this.wyslane) {
                //wyslij dane
                if(this.OdswiezanieDanych)bufor_zapis.setCommandRead(TablicaKomand[this.LicznikDanych]);

                if (this.PortOtwarty) {
                    try {
                        serialPort.readBytes();
                    } catch (SerialPortException ex) {
                        ex.printStackTrace();
                    }
                }
                if(this.OdswiezanieDanych)this.sendFrame(bufor_zapis.getFrame());
                if(this.ZapisDanychDoDrona){
                    this.sendFrame(buf_zapis.getFrame());
                    if(DebugCMDsend)System.out.println("Wysylanie komendy:"+buf_zapis.getCOMAND());
                }
                if (DebugCMD) System.out.println("Wyslanie komendy:" + TablicaKomand[this.LicznikDanych]);

                Timeout = System.currentTimeMillis();

                this.wyslane = true;
            }

            if ((System.currentTimeMillis() - this.Timeout) >= this.MaxTimeOut) {
                wyslane = false;
                if(this.OdswiezanieDanych) {
                    this.LicznikDanych++;
                    if (this.LicznikDanych >= this.MaxLicznikDanych) this.LicznikDanych = 0;
                }
                if (DebugCMD) System.out.println("Wystapil Timeout:" + (System.currentTimeMillis() - Timeout));
            }

    }

    public void setDlugoscRamki(int dlugosc_ramki){
        this.dlugoscRamki=dlugosc_ramki;
    }

    public void Polacz(){
        serialPort=new SerialPort(port);
        boolean status1,status2;
        status1=false;
        status2=false;
        try{

            status1= serialPort.openPort();
            status2= serialPort.setParams(baud, 8, 1, 0);
            serialPort.setEventsMask(SerialPort.MASK_RXCHAR);
            serialPort.addEventListener(new SerialPortReader(bufor));
            serialPort.setDTR(false);

        }
        catch (SerialPortException ex){
            System.out.println(ex);
        }
      //  if(status1==status2==true) return true;
      //  else return false;

    }
    public void Rozlacz(){
        if(serialPort.isOpened()) {
            try {
                serialPort.closePort();
            } catch (SerialPortException ex) {
                System.out.println(ex);
            }
        }
        this.PortOtwarty=false;
    }

    public void setPort(String port){
        this.port=port;
    }
    public void setBaud(int baud){
        this.baud=baud;
    }
    public void sendByte(byte b){
        try {
            serialPort.writeByte(b);
        } catch (SerialPortException ex){
            System.out.println(ex);
        }
    }
    public void sendFrame(byte[] frame){
        if(this.PortOtwarty) {
            try {
                serialPort.writeBytes(frame);
            } catch (SerialPortException ex) {
                System.out.println(ex);
            }
        }
    }


    static class SerialPortReader implements SerialPortEventListener{


        private byte []buf1 = new byte[16];
       private int ramka=16;
        private Buffor buf;
        private int i=0;

        public SerialPortReader(Buffor buffor){
            this.buf=buffor;
        }



        public void serialEvent(SerialPortEvent event){
            if(event.isRXCHAR()){

                if(event.getEventValue()>=16) {
                    try {
                        buf1=serialPort.readBytes(ramka);
                        buf.writeFrame(buf1);
                        if(!buf.Addr_ok){
                            serialPort.readBytes();
                        }
                    } catch (SerialPortException ex) {
                        System.out.println(ex);
                    }
                }

            }
        }


    }



}
