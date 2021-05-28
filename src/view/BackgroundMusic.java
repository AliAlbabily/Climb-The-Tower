package view;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.io.File;

/**
 * @author Hanis Saley
 * @version 1.0
 * This class contains music player.
 */

public class BackgroundMusic {

    private Clip clip;
    private long clipTimePosition;

   public void playMusic(String musicLocation){

       try{

            File musicPath = new File(musicLocation);

            if(musicPath.exists()){
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicPath);

                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-20.0f);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }

            else{
                System.out.println("Can't find file");
            }
       }catch (Exception ex){
           ex.printStackTrace();
       }
   }

   public void loopAfterPause(){
       clip.start();
       clip.loop(Clip.LOOP_CONTINUOUSLY);
   }

   public void pauseMusic(){
       clipTimePosition = clip.getMicrosecondPosition();
       clip.stop();

   }

   public void resumeMusic(){
       clip.setMicrosecondPosition(clipTimePosition);
       clip.start();
       clip.loop(Clip.LOOP_CONTINUOUSLY);
   }

   public void stopMusic(){
       clip.stop();
   }
}
