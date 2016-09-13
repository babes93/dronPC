package sample;

import javafx.scene.control.TextField;
/**
 * Created by Babes on 2016-02-02.
 */
public class SilnikParametry {
    public TextField SkladowaH;
    public TextField UchybAlfa;
    public TextField RegAlfa;
    public TextField UchybBeta;
    public TextField RegBeta;
    public TextField PWM;

    public SilnikParametry(TextField H,TextField uchybalfa,TextField regalfa,TextField uchybbeta,TextField regbeta,TextField pwm){
        this.SkladowaH=H;
        this.UchybAlfa = uchybalfa;
        this.RegAlfa=regalfa;
        this.UchybBeta=uchybbeta;
        this.RegBeta=regbeta;
        this.PWM=pwm;

        this.SkladowaH.setText("0");
        this.UchybAlfa.setText("0");
        this.UchybBeta.setText("0");
        this.RegBeta.setText("0");
        this.RegAlfa.setText("0");
        this.PWM.setText("0");

    }

    public void setSkladowaH(String skladowaH){
        this.SkladowaH.setText(skladowaH);
    }

    public void setUchybAlfa(String uchybAlfa){
        this.UchybAlfa.setText(uchybAlfa);
    }

    public void setRegAlfa(String regAlfa){
        this.RegAlfa.setText(regAlfa);
    }

    public void setUchybBeta(String uchybBeta){
        this.UchybBeta.setText(uchybBeta);
    }

    public void setRegBeta(String regBeta){
        this.RegBeta.setText(regBeta);
    }

    public void setPWM(String pwm){
        this.PWM.setText(pwm);
    }

}
