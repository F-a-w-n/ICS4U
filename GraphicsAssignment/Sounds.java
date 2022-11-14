//Sounds.java - original code by Fawn Barisic - stores and controls sound files because I'm cool, ignore that the name is plural It's very intentional

import javax.sound.sampled.*;
import java.io.*;

//stores and plays sounds
public class Sounds {
  //clip stores the actual sound to play
  Clip clip;
  //sound turns file to audio for clip
  AudioInputStream sound;
  //file (to everyone's astonishment) stores the file
  File file;
  //if the sound's playing is set to false, it cannot play. used to toggle off audio
  boolean playing = true;

  public Sounds(String filename) {
    try {
      //does essentially what I described above
      file = new File(filename); //opens filename from user
      sound = AudioSystem.getAudioInputStream(file); //reads file
      clip = AudioSystem.getClip(); //makes a nice lil audio clip of it so I can play/pause etc.
      clip.open(sound);
    } catch (Exception e) { //filesystem is garbage and I hate it even though its nice and useful and not that bad
      System.out.println("audio file not found");
    }
  }

  public void Play() {
    if (playing) { //if sound isnt toggled off
    try {
        //restarts sound 
        sound = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(sound);
    } catch (Exception e) {}
    //starts the sound
    clip.start(); 
    } 
  }

  public void togglePlay() { //just sets playing and starts/stops the audio depending on if it's playing
    playing = (playing == true)? false : true;
    if (playing == false) {
      this.stop();
    } else {
      this.Play();
    }
  }
 
  public void stop() {
    try {
      //just shuts every dang thing off
    sound.close();
    clip.close();
    clip.stop();
    } catch (IOException e) {}
  }
}
