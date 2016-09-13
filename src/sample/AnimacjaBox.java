package sample;

import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
/**
 * Created by Babes on 2015-12-24.
 */
public class AnimacjaBox {

    private double alfa,beta,gamma;

    private Box box;

    private Rotate rx;
    private Rotate ry;
    private Rotate rz;


    public void UpDateBox(){
         rx.setAngle(alfa);
         ry.setAngle(beta);
         rz.setAngle(gamma);
    }

    public AnimacjaBox(Box box){
       this.box=box;

        this.alfa=0;
        this.beta=0;
        this.gamma=0;

        this.rx=new Rotate(0,0,0,0,Rotate.X_AXIS);
        this.ry= new Rotate(0,0,0,0,Rotate.Y_AXIS);
        this.rz = new Rotate(0,0,0,0,Rotate.Z_AXIS);

        this.UpDateBox();

        box.getTransforms().addAll(rx,ry,rz);
   }

    public void setAlfa(double alfa) {
        this.alfa = alfa;
    }

    public void setBeta(double beta) {
        this.beta = beta;
    }

    public void setGamma(double gamma) {
        this.gamma = gamma;
    }

    public void setAllAngle(double alfa,double beta,double gamma){
        this.alfa=alfa;
        this.beta=beta;
        this.gamma=gamma;
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

}
