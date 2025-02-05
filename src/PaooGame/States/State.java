package PaooGame.States;


import PaooGame.RefLinks;

import PaooGame.UI.UIManager;

import java.awt.*;


/*! \class State
    \brief Implementeaza notiunea abstracta de stare a jocului/programului.

    Un joc odata ce este lansat in executie nu trebuie "sa arunce jucatorul direct in lupta", este nevoie de
    un meniu care sa contine optiuni: New Game, Load Game, Settings, About etc. Toate aceste optiuni nu sunt altceva
    decat stari ale programului (jocului) ce trebuiesc incarcate si afisate functie de starea curenta.
 */
public abstract class State {

    private static State previousState  = null; /*!< Referinta catre starea anterioara a jocului.*/
    private static State currentState   = null; /*!< Referinta catre starea curenta a jocului: game, meniu, settings, about etc.*/
    protected RefLinks refLink; /*!< O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.*/
    protected UIManager uiManager; /*!< O referinta catre managerul interfetei grafice.*/

    protected  boolean notify; /*!< Flag ce anunta State-ul atunci cand trebuie afisat ceva pe ecran.*/



    /*! \fn  public State(RefLinks refLink)
       \brief Constructorul cu parametri al clasei State.

       \param reflink Referinta catre un obiect de tip shortcut ce contine o serie de referinte utile.
    */
    public State(RefLinks refLink){
        this.refLink=refLink;

    }


    /*! \fn public static void SetState(State state)
        \brief Seteaza starea curenta a jocului.

        \param state Noua stare a programului (jocului).
     */
    public static void SetState(State state){
        previousState = currentState;
        currentState=state;
    }

    public static State GetState(){
        return currentState;
    }
    public static State GetPreviousState(){return previousState;}

    ///Metoda abstracta destinata actualizarii starii curente
    public abstract void Update();

    ///Metoda abstracta destinata desenarii starii curente
    public abstract void Render(Graphics g);


    /*! \fn public void setNotify(boolean flag)
        \brief Notifica state-ul atunci cand trbuie afisat ceva pe ecran.

        \param flag Flag-ul pentru setarea atributului notify.
     */
    public void setNotify(boolean flag){
        notify=flag;
    }

    /// returneaza managerul UI
    public UIManager getUiManager() {
        return uiManager;
    }

    public void addCountdown(int value){
       //vom implementa metoda in clasa copil(playstate)
    }


}
