package PaooGame.Settings;

import PaooGame.SQLite.SQL;

/*! \public class GameSettings
    \brief Implementeaza notiunea de setari ale jocului
 */

public class GameSettings {

    private boolean musicON;
    private boolean soundON;


    /*! \fn public GameSettings()
        \brief Constructorul de initializare al clasei GameSettings, care initializeaza setarile
                cu ultimele setari stabilite in baza de date
*/
    public GameSettings(){
            musicON = SQL.getInstance().getMusicStatus() == 1;
            soundON = SQL.getInstance().getSoundStatus() == 1;

    }

    /*! \fn public boolean isMusicON()
            \brief  Functie ce returneaza un flag care stabileste daca muzica este activata sau nu.
*/
    public boolean isMusicON(){return  musicON;}

    /*! \fn public boolean isSoundON()
            \brief  Functie ce returneaza un flag care stabileste daca sunetele sunt activate sau nu.
*/
    public boolean isSoundON(){return soundON;}


    /// Getters & Setters pt atribute
    public void setMusic(boolean set){
        musicON=set;
    }

    public void setSound(boolean set){
        soundON=set;
    }


}
