package PaooGame.States;

import PaooGame.RefLinks;


//Folosim Sablonul FACTORY
/*
  Metodă statică pentru crearea obiectelor de tip State.
  name Numele stării care trebuie creată.
  refLink Referință către obiectul RefLinks.
  isLoad Parametru specific pentru PlayState.
  O instanță a clasei corespunzătoare stării dorite.
 */
public class StatesFactory {
    public static State createState(StatesNames name, RefLinks refLink,boolean isLoad)
    {
        // Folosim un switch pentru a determina ce tip de stare să creăm
        switch (name)
        {
            case GameOverState:
                return new GameOverState(refLink);
            case GameWinState:
                return new GameWinState(refLink);
            case HelpState:
            return new HelpState(refLink);
            case HighScoresState:
                return new HighScoresState(refLink);
            case MenuState:
                return new MenuState(refLink);
            case PauseState:
                return new PauseState(refLink);
            case PlayState:
                return new PlayState(refLink,isLoad);
            case SettingsState:
                return new SettingsState(refLink);
            default:
                // Aruncăm o excepție dacă numele stării nu este recunoscut
                throw new IllegalArgumentException("Stare necunoscuta: "+name);
        }
    }

}
