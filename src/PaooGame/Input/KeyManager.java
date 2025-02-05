package PaooGame.Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*! \public class KeyManager implements KeyListener
    \brief Gestioneaza intrarea (input-ul) de tastatura.

    Clasa citeste daca au fost apasata o tasta, stabiliteste ce tasta a fost actionata si seteaza corespunzator un flag.
    In program trebuie sa se tina cont de flagul aferent tastei de interes. Daca flagul respectiv este true inseamna
    ca tasta respectiva a fost apasata si false nu a fost apasata.
 */

public class KeyManager implements KeyListener {
    private boolean[] keys;
    public boolean up;      /*!< Flag pentru tasta "sus" apasata.*/
    public boolean down;    /*!< Flag pentru tasta "jos" apasata.*/
    public boolean left;    /*!< Flag pentru tasta "stanga" apasata.*/
    public boolean right;   /*!< Flag pentru tasta "dreapta" apasata.*/




    /*! \fn public KeyManager()
         \brief Constructorul de initializare al clasei KeyManager
*/
    public KeyManager()
    {
        keys=new boolean[256];

    }


    /*! \fn public void Update()
         \brief Actualizarea input-ului de la tastatura.
*/
    public void Update()
    {
        up    = keys[KeyEvent.VK_UP];
        down  = keys[KeyEvent.VK_DOWN];
        left  = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
    }



    /*! \fn public KeyPressed(KeyEvent e)
         \brief actualizeaza starea tastei care este apasata

         \param e Evenimentul care genereaza actualizarea starii tastei
*/
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()<0 || e.getKeyCode() >=keys.length)
            return;
        keys[e.getKeyCode()]=true;

    }


    /*! \fn public KeyReleased(KeyEvent e)
      \brief actualizeaza starea tastei care este eliberata

      \param e Evenimentul care genereaza actualizarea starii tastei
*/
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()<0 || e.getKeyCode() >=keys.length)
            return;
        keys[e.getKeyCode()]=false;

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }
}
