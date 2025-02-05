package PaooGame.Audio;

import PaooGame.Settings.GameSettings;

import javax.sound.sampled.Clip;
/*! \class MusicClip extends AudioClip
    \brief Implementeaza notiunea de clip de tip musica de fundal
 */
public class MusicClip extends AudioClip{

    /*! \fn public MusicClip(Clip clip)
       \brief Constructorul de initializare al clasei.

       \param clip O referinta catre un obiect de tip Clip.
    */
    public MusicClip(Clip clip) {
        super(clip);
    }



    /*! \fn public void stop()
       \brief Opreste muzica de fundal.
    */
    public void stop(){
        if(clip.isRunning()){
            clip.stop();
        }

    }

    /*! \fn public void start()
        \brief Porneste muzica de fundal.
    */
    public void start(){
        if(!clip.isRunning()){
            clip.flush();
            clip.setMicrosecondPosition(0);
            clip.start();
        }

    }


    /*! \fn public void Update(GameSettings gameSettings)
        \brief Actualizeaza starile clipurilor.

        \param  gameSettings Setarile in functie de care se actualizeaza starile
    */
    @Override
    public void Update(GameSettings gameSettings) {
        if(gameSettings.isMusicON()){
                start();
        }
        else
        {
                stop();
        }
    }
}
