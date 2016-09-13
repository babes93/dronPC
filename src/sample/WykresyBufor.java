package sample;

/**
 * Created by Babes on 2016-02-25.
 */
public  class WykresyBufor {

    private static double alfa,beta,gamma;
    private static Raw gyroRaw = new Raw();
    private static Filtracja gyro = new Filtracja();
    private static Raw accRaw = new Raw();
    private static Filtracja acc = new Filtracja();
    public static final double acck=0.000061;
    public static final double gyrok=0.0076;

    public static boolean ore=false;
    public static boolean accs=false;

    public static boolean magnet=false;
    public static boolean baro=false;

    public static double MAGNET_X,MAGNET_Y,MAGNET_Z;
    public static double BARO_P,BARO_T,BARO_W;
    public static double AlfaRate,BetaRate,GammaRate;
    public static boolean EulerRate = false;

    public static boolean gyros=false;
    public static boolean raws=false;

    public void setEulerRate(double alfaRate,double betaRate, double gammaRate){
        this.AlfaRate=alfaRate;
        this.BetaRate=betaRate;
        this.GammaRate=gammaRate;
    }

    public void setAlfa(double alfa){
        this.alfa=alfa;
    }

    public void setBeta(double beta) {
        this.beta = beta;
    }



    public void setGamma(double gamma) {
        this.gamma = gamma;
    }

    public void setGyroRawX(double X){
        this.gyroRaw.X=X;
    }

    public void setGyroRawY(double Y){
        this.gyroRaw.Y=Y;
    }

    public void setGyroRawZ(double Z){
        this.gyroRaw.Z=Z;
    }

    public void setGyroX(double X){
        this.gyro.X=X;
    }

    public void setGyroY(double Y){
        this.gyro.Y=Y;
    }

    public void setGyro(double Z){
        this.gyro.Z=Z;
    }

    public void setAccRawX(double X){

        this.accRaw.X=X;
    }

    public void setAccRawY(double Y){

        this.accRaw.Y=Y;
    }

    public  void setAccRawZ(double Z){
        this.accRaw.Z=Z;
    }

    public  void setAccX(double X){
        this.acc.X=X;
    }
    public void setAccY(double Y){
        this.acc.Y=Y;
    }
    public void setAccZ(double Z){
        this.acc.Z=Z;
    }
    public void setAccW(double W){
        this.acc.W=W;
    }

    public double getAlfa() {
        return alfa;
    }

    public double getBeta() {
        return beta;
    }

    public double getGamma() {
        return gamma;
    }

    public double getGyroRawX(){return gyroRaw.X;}
    public double getGyroRawY(){return gyroRaw.Y;}
    public double getGyroRawZ(){return gyroRaw.Z;}

    public double getGyroX(){return gyro.X;}
    public double getGyroY(){return gyro.Y;}
    public double getGyroZ(){return gyro.Z;}

    public double getAccRawX(){return accRaw.X;}
    public double getAccRawY(){return accRaw.Y;}
    public double getAccRawZ(){return accRaw.Z;}

    public double getAccX(){return acc.X;}
    public double getAccY(){return acc.Y;}
    public double getAccZ(){return acc.Z;}
    public double getAccW(){return acc.W;}

    public static class Raw{
        public double X,Y,Z;
    }
    public static class Filtracja{
        public double X,Y,Z,W;
    }

}
