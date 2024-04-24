import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class VolumeControl
{

    static String cases() {
        int files = ThreadLocalRandom.current().nextInt(1, 3);
        String input="";
        switch (files) {
            case 0:

            case 1:
                return "resources/1.wav";
            case 2:
                return "resources/2.wav";
           /*/ case 3:
                mPlayer = MediaPlayer.create(this, music_numbers[3]);
                mPlayer.start();
            case 4:
                mPlayer = MediaPlayer.create(this, music_numbers[4]);
                mPlayer.start();
            case 5:
                mPlayer = MediaPlayer.create(this, music_numbers[5]);
                mPlayer.start();
            case 6:
                mPlayer = MediaPlayer.create(this, music_numbers[6]);
                mPlayer.start();
            case 7:
                mPlayer = MediaPlayer.create(this, music_numbers[7]);
                mPlayer.start();
            case 8:
                mPlayer = MediaPlayer.create(this, music_numbers[8]);
                mPlayer.start();
            case 9:
                mPlayer = MediaPlayer.create(this, music_numbers[9]);
                mPlayer.start();
            case 10:
                mPlayer = MediaPlayer.create(this, music_numbers[10]);
                mPlayer.start();/*/

        }
        return input;
    }
    static AudioInputStream audioInputStream;

    static {
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(cases()).getAbsoluteFile());
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Clip clip;

    static {
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public VolumeControl() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
    }

    public static void soundtrack() {//jeden klip, póki co aby przetestować koncept
        try {
            clip.open(audioInputStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception ex) {
            System.out.println("\n\n Error with playing sound.");
            ex.printStackTrace();
        }
    }
    public static void volume(int a){
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        String b= "-"+a+"f";
        gainControl.setValue(Float.parseFloat(b));
    }

}
