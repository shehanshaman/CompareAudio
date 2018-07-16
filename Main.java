/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author shehan shaman
 */


public class Main implements ActionListener{
    static final long RECORD_TIME = 12000; // 0.2 minute
    public static JButton button;
    public static JLabel frameResult;
    public static JavaSoundRecorder recorder = null;
    public static Thread stopper;
    
    public static void main(String [] arg) throws FileNotFoundException{
        
        //Create and set up the window.
        JFrame frame = new JFrame("Compare Songs");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        recorder = new JavaSoundRecorder();
        
        //Create and set up the content pane.
        JComponent newContentPane = recorder;
        
        // newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
        frame.getContentPane().setBackground(Color.getHSBColor(0.169f, 0.02f, 0.89f));
        //Display the window.
        frame.setSize(680, 400);
        frame.setLayout(null);
        
        button = new JButton("compare");
        button.setBounds(250,300,170,30);
        button.setName("compare");
        frame.add(button); 
        button.addActionListener(new Main());
        
        //Show above search results details
        frameResult = new JLabel("Click on Compare button",JLabel.CENTER);        
        frameResult.setBorder(BorderFactory.createLineBorder(Color.black));
        frameResult.setBounds(10,50,640,165 );
        frame.add(frameResult);
        
        frame.setVisible(true);

// creates a new thread that waits for a specified

// of time before stopping

while(true){
    stopper = new Thread(new Runnable() {

public void run() {

    try {
        Thread.sleep(RECORD_TIME);
    } catch (InterruptedException ex) {
        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }

recorder.finish();

}

});

}



}

    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         Compare.resultSong = null;
        
        JButton buttonclicked = (JButton)e.getSource();
        
        if(buttonclicked.getName().equals("compare")){
            
            //frameResult.setText("listening");
            //Item button click
            stopper.start();
            recorder.start();
            
            
            //frameResult.setText("comparing");

     Map songs = new HashMap();
     songs.put("songs/despacito.wav","Luis Fonsi - Despacito ft. Daddy Yankee"); 
     songs.put("songs/duburu.wav","Duburu Lamissi");
     songs.put("songs/Hanthane.wav","Hanthane Kandhu");
     songs.put("songs/kar.wav","Kar Gayi Chull");   
     songs.put("songs/shape.wav","Shape of You"); 
        
             try {
                 new Compare().match(songs);
                 if(Compare.resultSong==null){
                     frameResult.setText("NOT FOUND");
                 }else{
                     frameResult.setText((String) songs.get(Compare.resultSong));
                 }
                     
                 
             } catch (FileNotFoundException ex) {
                 Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
             }
            
            if(Compare.resultSong!=null){
                String gongFile = Compare.resultSong;
                InputStream in = null;
             try {
                 in = new FileInputStream(gongFile);
             } catch (FileNotFoundException ex) {
                 Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
             }

                 // create an audiostream from the inputstream
                 AudioStream audioStream = null;
                    try {
                     audioStream = new AudioStream(in);
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }

                // play the audio clip with the audioplayer class
                AudioPlayer.player.start(audioStream);
                try {
                    TimeUnit.SECONDS.sleep(20);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                AudioPlayer.player.stop(audioStream);
            }
            
        }
    }
    
       

    }

