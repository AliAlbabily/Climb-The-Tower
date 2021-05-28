package view;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;

public class BackgroundMusic {

    private Clip clip;

   public void playMusic(String musicLocation){

       try{

            File musicPath = new File(musicLocation);

            if(musicPath.exists()){
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicPath);

                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();

            }

            else{
                System.out.println("Can't find file");
            }
       }catch (Exception ex){
           ex.printStackTrace();
       }
   }

   public void stopMusic(){
       clip.stop();
   }
}
