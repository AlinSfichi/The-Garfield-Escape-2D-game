package PaooGame.Audio;

import PaooGame.Settings.GameSettings;

import javax.sound.sampled.Clip;

/*! \class  SoundClip extends AudioClip
    \brief Implementeaza notiunea de clip de tip sunet in joc
 */
public class SoundClip extends AudioClip{


    /*! \fn public SoundClip(Clip clip)
       \brief Constructorul de initializare al clasei.

       \param clip O referinta catre un obiect de tip Clip.
    */
    public SoundClip(Clip clip) {
        super(clip);

    }


    /*! \fn public void start()
        \brief Activeaza sunetele in joc.
    */
    public void start(){
        if(!clip.isRunning()){
            clip.flush();
            clip.setMicrosecondPosition(0);
            clip.start();
        }
    }

    /*! \fn public void stop()
       \brief Opreste muzica de fundal.
    */
    public void stop() {
        if(clip.isRunning()){
            clip.stop();
        }
    }

    /*! \fn public void Update(GameSettings gameSettings)
            \brief Actualizeaza starile clipurilor.

            \param  gameSettings Setarile in functie de care se actualizeaza starile
        */
    @Override
    public void Update(GameSettings gameSettings) {
        if(gameSettings.isSoundON()){
                start();
        }
        else
        {
            stop();
        }
    }
}
