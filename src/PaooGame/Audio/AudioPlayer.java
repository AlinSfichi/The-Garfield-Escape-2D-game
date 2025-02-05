package PaooGame.Audio;

import PaooGame.Exceptions.NullContentException;
import PaooGame.Settings.GameSettings;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/*! \class AudioPlayer
    \brief Implementeaza notiunea de player audio , care controleaza clipurile audio
 */
public class AudioPlayer {
    private static AudioPlayer instance=null; /*!< Instanta a nivelului.*/
    private List<AudioClip> audioClipList;   /*!< Lista de clipuri audio utilizate in joc.*/




    /*! \fn public AudioPlayer()
        \brief Constructorul de initializare al clasei.
     */
    private AudioPlayer(){
        audioClipList=new ArrayList<>();
    }

    public static AudioPlayer getInstance(){
        if(instance==null)
            instance=new AudioPlayer();
        return instance;
    }


    /*! \fn public void playMusic(String fileName)
        \brief Implementeaza notiunea de play pentru un obiect de tip MusicCLip

        \param  fileName Numele fisierului ce contine clipul de tip muzica de fundal.
     */
    public void playMusic(String fileName) throws NullContentException{
        final Clip clip=getClip(fileName);
        if(clip==null){
            throw new NullContentException("Null content file.");
        }
        else {
            MusicClip musicClip=new MusicClip(clip);
            audioClipList.add(musicClip);
        }

    }


    /*! \fn public void playSound(String fileName)
        \brief Implementeaza notiunea de play pentru un obiect de tip SoundCLip

        \param  fileName Numele fisierului ce contine clipul de tip sunet.
    */
    public void playSound(String fileName) throws NullContentException {
        final Clip clip=getClip(fileName);
        /// in cazul in care continutul fisierului este nul, se arunca o exceptie
        if(clip==null){
            throw new NullContentException("Null content file.");
        }
        else{
            SoundClip soundClip=new SoundClip(clip);
            audioClipList.add(soundClip);
        }

    }


    /*! \fn private Clip getClip(String fileName)
        \brief Functie folosita pt a extrage fisierul audio de la un path dat

        \param  fileName Numele fisierului ce contine clipul
    */

    private Clip getClip(String fileName){
        final URL soundFile=AudioPlayer.class.getResource("/sounds/"+fileName);
        try(AudioInputStream audioInputStream= AudioSystem.getAudioInputStream(soundFile)){
            final Clip clip=AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.setMicrosecondPosition(0);

            return clip;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
        return null;
    }

    /*! \fn public void Update(GameSettings gameSettings)
        \brief Actualizeaza starile clipurilor.

        \param  gameSettings Setarile in functie de care se actualizeaza starile
    */
    public void Update(GameSettings gameSettings){
        audioClipList.forEach(audioClip -> audioClip.Update(gameSettings));
    }
}
