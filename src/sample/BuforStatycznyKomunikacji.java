package sample;

/**
 * Created by Patryk on 2016-06-20.
 */
public class BuforStatycznyKomunikacji {
    public static float AlfaEuler;
    public static float BetaEuler;
    public static float GammaEuler;
    public static float AccX;
    public static float AccY;
    public static float AccZ;
    public static float AccW;
    public static short S1_PWM;
    public static short S2_PWM;
    public static short S3_PWM;
    public static short S4_PWM;
    public static float UchybAlfa;
    public static float UchybBeta;
    public static float S1_H;
    public static float S1_RegAlfa;
    public static float S1_RegBeta;
    public static float S2_H;
    public static float S2_RegAlfa;
    public static float S2_RegBeta;
    public static float S3_H;
    public static float S3_RegAlfa;
    public static float S3_RegBeta;
    public static float S4_H;
    public static float S4_RegAlfa;
    public static float S4_RegBeta;
    public static short SredniaAcc;
    public static short SredniaGyro;
    public static short SredniaMagnet;
    public static short SredniaBaro;
    public static float GyroX;
    public static float GyroY;
    public static float GyroZ;
    public static float MagnetX;
    public static float MagnetY;
    public static float MagnetZ;
    public static float BaroT;
    public static float BaroP;
    public static float BaroW;
    public static short AccRawX;
    public static short AccRawY;
    public static short AccRawZ;
    public static short GyroRawX;
    public static short GyroRawY;
    public static short GyroRawZ;
    public static float S1_RegAlfa_Rate;
    public static float S1_RegBeta_Rate;
    public static float S2_RegAlfa_Rate;
    public static float S2_RegBeta_Rate;
    public static float S3_RegAlfa_Rate;
    public static float S3_RegBeta_Rate;
    public static float S4_RegAlfa_Rate;
    public static float S4_RegBeta_Rate;
    public static float AlfaRate;
    public static float BetaRate;
    public static float GammaRate;
    public static float AlfaUchybRate;
    public static float BetaUchybRate;


    public synchronized void setUchybRate(float alfa, float beta){
        this.AlfaUchybRate=alfa;
        this.BetaUchybRate=beta;
    }

    public synchronized void setEulerRate(float alfarate, float betarate, float gammarate){
        this.AlfaRate=alfarate;
        this.BetaRate=betarate;
        this.GammaRate=gammarate;
    }



    public synchronized void setS1RegRate(float alfa_rate, float beta_rate){
        this.S1_RegAlfa_Rate=alfa_rate;
        this.S1_RegBeta_Rate=beta_rate;
    }

    public synchronized void setS2RegRate(float alfa_rate, float beta_rate){
        this.S2_RegAlfa_Rate=alfa_rate;
        this.S2_RegBeta_Rate=beta_rate;
    }

    public synchronized void setS3RegRate(float alfa_rate, float beta_rate){
        this.S3_RegAlfa_Rate=alfa_rate;
        this.S3_RegBeta_Rate=beta_rate;
    }

    public synchronized void setS4RegRate(float alfa_rate, float beta_rate){
        this.S4_RegAlfa_Rate=alfa_rate;
        this.S4_RegBeta_Rate=beta_rate;
    }

    public synchronized void setKatyEulera(float Alfa,float Beta, float Gamma){
        this.AlfaEuler=Alfa;
        this.BetaEuler=Beta;
        this.GammaEuler=Gamma;
    }

    public synchronized void setAccXYZ(float X,float Y, float Z){
        this.AccX=X;
        this.AccY=Y;
        this.AccZ=Z;
    }

    public synchronized void setAccW(float W){
        this.AccW=W;
    }

    public synchronized void setPWM(short S1,short S2, short S3, short S4){
        this.S1_PWM=S1;
        this.S2_PWM=S2;
        this.S3_PWM=S3;
        this.S4_PWM=S4;
    }

    public synchronized void setUchyb(float Alfa,float Beta){
        this.UchybAlfa=Alfa;
        this.UchybBeta=Beta;
    }

    public synchronized void setS1Reg(float H, float RegAlfa, float RegBeta){
        this.S1_H=H;
        this.S1_RegAlfa=RegAlfa;
        this.S1_RegBeta=RegBeta;
    }

    public synchronized void setS2Reg(float H, float RegAlfa, float RegBeta){
        this.S2_H=H;
        this.S2_RegAlfa=RegAlfa;
        this.S2_RegBeta=RegBeta;
    }

    public synchronized void setS3Reg(float H, float RegAlfa, float RegBeta){
        this.S3_H=H;
        this.S3_RegAlfa=RegAlfa;
        this.S3_RegBeta=RegBeta;
    }

    public synchronized void  setS4Reg(float H, float RegAlfa, float RegBeta){
        this.S4_H=H;
        this.S4_RegAlfa=RegAlfa;
        this.S4_RegBeta=RegBeta;
    }

    public synchronized void setSrednie(short Acc, short Gyro, short Magnet, short Baro){
        this.SredniaAcc=Acc;
        this.SredniaGyro=Gyro;
        this.SredniaMagnet=Magnet;
        this.SredniaBaro=Baro;
    }

    public synchronized void setGyroXYZ(float X,float Y, float Z){
        this.GyroX=X;
        this.GyroY=Y;
        this.GyroZ=Z;
    }

    public synchronized void setMagnetXYZ(float X, float Y, float Z){
        this.MagnetX=X;
        this.MagnetY=Y;
        this.MagnetZ=Z;
    }

    public synchronized void setBaro(float T, float P,float W){
        this.BaroT=T;
        this.BaroP=P;
        this.BaroW=W;
    }

    public synchronized void setAccGyroRaw(short AccX,short AccY, short AccZ, short GyroX, short GyroY, short GyroZ){
        this.AccRawX=AccX;
        this.AccRawY=AccY;
        this.AccRawZ=AccZ;
        this.GyroRawX=GyroX;
        this.GyroRawY=GyroY;
        this.GyroRawZ=GyroZ;
    }




}
