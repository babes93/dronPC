package sample;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Created by Babes on 2016-01-23.
 */
public class Buffor {

    private byte[] frame = new byte[16];
    private byte ADDR;
    private byte MaskaZapis = (byte)0x80;
    private byte COMAND;
    private int x,y,z;
    public boolean Addr_ok,Data_ready;

    public final static byte READ_ANGLE =0x01;
    public final static byte READ_ACC = 0x02;
    public final static byte READ_ACC_W=0x03;
    public final static byte READ_PWM = 0x07;
    public final static byte READ_UCHYB = 0x08;
    public final static byte READ_S1 =0x09;
    public final static byte READ_S2 = 0x0A;
    public final static byte READ_S3 = 0x0B;
    public final static byte READ_S4 = 0x0C;
    public final static byte STOP_AWARYJNY = (byte)0x0D;
    public final static byte READ_KONFIGURACJA_1 = (byte)0x0E;
    public final static byte READ_KONGIGURACJA_2 = (byte)0x0F;
    public final static byte READ_GYRO_FILTR = (byte)0x11;
    public final static byte READ_UCHYB_LOG = (byte)0x10;
    public final static byte READ_MAGNET_S = (byte)0x12;
    public final static byte READ_BARO = (byte)0x13;
    public final static byte READ_S1_REG_RATE = (byte)0x14;
    public final static byte READ_S2_REG_RATE = (byte) 0x15;
    public final static byte READ_S3_REG_RATE = (byte) 0x16;
    public final static byte READ_S4_REG_RATE = (byte) 0x17;
    public final static byte READ_ORIENTACJA_RATE = (byte) 0x18;
    public final static byte READ_UCHYB_RATE = (byte) 0x19;


    public final static byte WRITE_PWM_TEST = (byte)0x81;
    public final static byte WRITE_MAX_PWM = (byte)0x82;
    public final static byte CMD_STOP = (byte)0x83;
    public final static byte CMD_GORA = (byte)0x84;
    public final static byte CMD_DOL = (byte)0x85;
    public final static byte WRITE_ANGLE = (byte)0x86;
    public final static byte WRITE_H = (byte)0x87;
    public final static byte WRITE_CMD = (byte)0x88;
    public final static byte WRITE_REG_ALFA1 = (byte)0x89;
    public final static byte WRITE_REG_BETA1 = (byte)0x8A;
    public final static byte LOGI_ACC_GYRO = (byte)0x8B;
    public final static byte WRITE_KONFIGURACJA_1 = (byte)0x8C;
    public final static byte WRITE_KONFIGURACJA_2 = (byte)0x8D;
    public final static byte WRITE_REG_ALFA_RATE = (byte)0x8E;
    public final static byte WRITE_REG_BETA_RATE = (byte)0x8F;


    private final byte[] FloatDataCommand={READ_ANGLE,READ_ACC,READ_ACC_W};



    public byte getCOMAND(){
        return this.frame[1];
    }

    public void setADDR(byte ADDR_ID){
        this.ADDR=ADDR_ID;
        this.frame[0]=this.ADDR;
        this.frame[15]=this.ADDR;
        this.frame[14]=(byte)0xAA;



    }

    public void setCommandWrite(byte command){
        this.COMAND|=((command) | ((byte)MaskaZapis));
        this.frame[1]=this.COMAND;
    }

    public void setCommandRead(byte command){
        this.COMAND=command;
        this.frame[1]=this.COMAND;
    }

    public void setDataFrame(byte[] data,int length){
        if(length>12)length=12;
        for(int i=0;i<length;i++){
            this.frame[i+2]=data[i];
        }
    }

    public byte[] getFrame(){
        return this.frame;
    }

    public void ClearDataFrame(){
        for(int i=2;i<14;i++)this.frame[i]=0;
    }

    public void ClearBufor(){for(int i=0;i<16;i++)this.frame[i]=0;}

    public float[] getFloatFromFrame(){
        float [] tmp = new float[3];
        byte[] by=new byte[4];
        for(int i=0;i<4;i++)by[i]=this.frame[i+2];
        tmp[0]= ByteBuffer.wrap(by).order(ByteOrder.LITTLE_ENDIAN).getFloat();
        for(int i=0;i<4;i++)by[i]=this.frame[i+6];
        tmp[1]= ByteBuffer.wrap(by).order(ByteOrder.LITTLE_ENDIAN).getFloat();
        for(int i=0;i<4;i++)by[i]=this.frame[i+10];
        tmp[2]= ByteBuffer.wrap(by).order(ByteOrder.LITTLE_ENDIAN).getFloat();

        this.Data_ready=false;
        this.COMAND=0;

        return tmp;

    }

    public short[] getShortFromFrame(){
        short [] tmp = new short[6];
        byte[] by=new byte[2];
        for(int i=0;i<6;i++){

            for(int x=0;x<2;x++)by[x]=this.frame[x+2+2*i];
           tmp[i]=ByteBuffer.wrap(by).order(ByteOrder.LITTLE_ENDIAN).getShort();

        }

        this.Data_ready=false;
        this.COMAND=0;
        return tmp;

    }

    public void setShortInFrame(short[] data){
        for(int i=0;i<6;i++){
            this.frame[2*i+2]=(byte)(data[i]&0xFF);
            this.frame[2*i+3]=(byte)((data[i]>>8)&0xFF);
        }
    }

    public void setFloatInFrame(float[] data){
        byte [] tmp = new byte[4];
        for(int i=0;i<3;i++){
            tmp=ByteBuffer.allocate(4).putFloat(data[i]).array();
            for(int x=0;x<4;x++)this.frame[4*i+2+x]=tmp[x];
        }

    }

    public synchronized void writeFrame(byte [] data){
        for (int i=0;i<16;i++){
            this.frame[i]=data[i];
        }
        if(this.frame[0]==this.frame[15]){
            Addr_ok=true;
            Data_ready=true;
            this.COMAND=this.frame[1];
        }
        else {
            Data_ready=false;
            Addr_ok=false;
        }

    }



}
