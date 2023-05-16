package com.vvit.stickynotz;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class SticckyNote {

    String getStick(Context context){
        File file = new File(context.getFilesDir().getPath()+"/gfg.txt");
        StringBuffer text = new StringBuffer();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line= br.readLine())!=null){
                text.append(line);
                text.append("\n");
            }
            br.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return text.toString();
    }

    void setStick(String textToBeSaved, Context context){
        String text = textToBeSaved;
        FileOutputStream fos = null;
        try {
            fos = context.getApplicationContext().openFileOutput("gfg.txt",Context.MODE_PRIVATE);
            fos.write(text.getBytes());
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (fos!=null){
                try {
                    fos.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
