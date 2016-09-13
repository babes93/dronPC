package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Babes on 2016-02-14.
 */
public class Logi {

    private SimpleDateFormat RokSystemowy = new SimpleDateFormat("yyyy");
    private SimpleDateFormat MiesiacSystemowy = new SimpleDateFormat("MM");
    private SimpleDateFormat DzienSystemowy = new SimpleDateFormat("dd");
    private static SimpleDateFormat CzasSystemowy = new SimpleDateFormat("kk_mm");

    private String Rok= RokSystemowy.format(new Date());
    private String Miesiac = MiesiacSystemowy.format(new Date());
    private String Dzien = DzienSystemowy.format(new Date());
    private PrintWriter LogZapis;

    private static boolean Zapis=false;

    private String logi = new String("Logi");
    private String Directory = new String(logi+"/"+Rok+"/"+Miesiac+"/"+Dzien+"/"+CzasSystemowy.format(new Date()));
    private File katalog;
    private File plik;

    public void StartZapis(){this.Zapis=true;}
    public void StopZapis(){this.Zapis=false;}

    public Logi(){
        katalog = new File(Directory);
        katalog.mkdirs();
    }

    public void CreateLogFile(String name){
        String plikpelny=new String(Directory + "/" + name +"_log"+".txt");
        if(Zapis) {
            plik = new File(plikpelny);
            try{
                LogZapis = new PrintWriter(plikpelny);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }

        }
    }



    public void WriteLineToLogFile(String line){
        if (Zapis) {
            LogZapis.println(line);
        }
    }

    public void WriteDataShort(int length,short []data){
        if (Zapis){
            for(int i=0;i<length;i++){
             LogZapis.print(Short.toString(data[i])+",");
            }
            LogZapis.print("\n");
        }
    }


    public void WriteDataFloat(int length,float []data){
        if(Zapis){
            for(int i=0;i<length;i++){
                LogZapis.print(Float.toString(data[i])+",");
            }
            LogZapis.print("\n");
        }


    }

    public void CloseLogFile(){
        if(Zapis){
            LogZapis.close();
        }
    }




}
