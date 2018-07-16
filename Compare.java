/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.musicg.fingerprint.FingerprintSimilarity;
import com.musicg.wave.Wave;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author shehan shaman
 */
public class Compare {

    /**
     * @param args the command line arguments
     */
    float result=0,com = 0;
    public static String resultSong,resultPath;
    
    public void match(Map songs) throws FileNotFoundException{        
        
        InputStream fis1 = null,fis2=null;
        String filename1 = "songs/test0.wav";
        //String filename2 = "./test0.wav";
        
        fis1 = new FileInputStream(filename1);
        Wave wave1 = new Wave(fis1);
        
        Iterator<String> itr = songs.keySet().iterator();
        while (itr.hasNext()) {
            String s = itr.next();
            System.out.println(s);
            
       
            fis2 = new FileInputStream(s);
            Wave wave2 = new Wave(fis2);
            
            FingerprintSimilarity similarity;
            similarity = wave1.getFingerprintSimilarity(wave2);
            com = similarity.getSimilarity()*100;
            System.out.println(com);
            if(com>result && com>=10){
                result = com;
                resultSong = s;
            }
        }
        
        
        System.out.println(songs.get(resultSong));
        
    }
    
}
