package PaooGame.Audio;

import PaooGame.Settings.GameSettings;

import javax.sound.sampled.Clip;


/*! \class AudioClip
    \brief Implementeaza notiunea abstracta de clip audio
 */
public abstract class AudioClip {

    protected final Clip clip;   /*!< Referinta catre un obiect de tip Clip ce va fi elementul central in ceea ce priveste sunetele.*/


    /*! \fn public AudioClip(Clip clip)
        \brief Constructorul de initializare al clasei.

        \param clip O referinta catre un obiect de tip Clip.
     */
    public AudioClip(Clip clip){
        this.clip=clip;
    }


    ///Metoda abstracta destinata actualizarii volumului clipului in functie de setari
    public abstract void Update(GameSettings gameSettings);



}


