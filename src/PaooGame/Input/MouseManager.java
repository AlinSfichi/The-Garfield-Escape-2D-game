package PaooGame.Input;

import PaooGame.UI.UIManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/*! \public class MouseManager implements MouseListener si MouseMotionListener
    \brief Gestioneaza intrarea (input-ul) de la mouse.
 */

public class MouseManager implements MouseListener, MouseMotionListener {

    private boolean leftPressed;    /*!< Flag pentru click stanga.*/
    private boolean  rightPressed;  /*!< Flag pentru click dreapta.*/
    private int mouseX;             /*!< Pozitia mouse-ului pe axa X.*/
    private int mouseY;             /*!< Pozitia mouse-ului pe axa Y.*/
    private UIManager uiManager;    /*!< Referinta catre un obiect de tip User Interface Manager.*/



    /*! \fn public MouseManager()
         \brief Constructorul de initializare al clasei MouseManager
*/
    public MouseManager(){

    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /*! \fn public MousePressed(MouseEvent e)
        \brief actualizeaza starea butoanelor mouse-ului care sunt apasate.

        \param e Evenimentul care genereaza actualizarea starii mouse-ului
*/
    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1) {
            leftPressed = true;
        }
        else if(e.getButton()==MouseEvent.BUTTON3)
            rightPressed=true;
    }


    /*! \fn public MouseReleased(MouseEvent e)
    \brief actualizeaza starea butoanelor mouse-ului care sunt eliberate.

    \param e Evenimentul care genereaza actualizarea starii mouse-ului.
*/
    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1)
            leftPressed=false;
        else if(e.getButton()==MouseEvent.BUTTON3)
            rightPressed=false;

        if(uiManager!=null) {
            uiManager.onMouseRelease(e);
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }


    /*! \fn public void mouseMoved(MouseEvent e)
            \brief Defineste actiunile ce au loc la miscarea mouse-ului.

            \param e Evenimentul care genereaza actiunile ce au loc.
*/
    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX=e.getX();
        mouseY=e.getY();

        if(uiManager!=null)
            uiManager.onMouseMove(e);
    }


    /// Getters & Setters pt atribute
    public boolean isLeftPressed(){
        return leftPressed;
    }

    public boolean isRightPressed(){
        return rightPressed;
    }

    public int getMouseX()
    {
        return mouseX;
    }

    public int getMouseY()
    {
        return mouseY;
    }


    public void setUIManager(UIManager uiManager)
    {
        this.uiManager=uiManager;
    }
}
