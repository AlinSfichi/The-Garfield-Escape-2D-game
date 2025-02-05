package PaooGame.UI;

import java.awt.*;
import java.awt.event.MouseEvent;


/*! public abstract class UIObject
    \brief Abstractizeaza notiunea de obiect al interfetei grafice
 */
public abstract class UIObject {


    protected float x; /*!< Pozitia pe axa X a tablei de joc a imaginii obiectui interfetei grafice.*/
    protected float y;/*!< Pozitia pe axa Y a tablei de joc a imaginii obiectui interfetei grafice.*/
    protected int width; /*!< Latimea obiectui interfetei grafice.*/
    protected int height;/*!< Inaltimea obiectui interfetei grafice.*/
    protected Rectangle bounds; /*!< Dreptunghiul de coliziune al obiectului.*/
    protected boolean hovering; /*!< Flag ce retine daca mouse-ul "trece" sau nu deasupra obiectului interfetei grafice.*/

    /*! \fn  public UIObject(float x, float y, int width, int height)
          \brief Constructorul de initializare al clasei UIObject.

          \param x Pozitia pe axa X a tablei de joc pentru imaginea obiectului interfetei grafice.
          \param y Pozitia pe axa Y a tablei de joc pentru imaginea obiectului interfetei grafice.
          \param width Latimea obiectui interfetei grafice.
          \param height Inaltimea obiectui interfetei grafice.
  */
    public UIObject(float x, float y, int width, int height)
    {
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        bounds=new Rectangle((int)x,(int)y,width,height);
    }

    /// metoda abstracta pentru actualizarea starii curente a obiectelor interfetei grafice
    public abstract void Update();

    /// metoda abstracta pentru desenarea starii curente a obiectelor interfetei grafice
    public abstract void Render(Graphics g);

    /// metoda abstracta pentru definirea actiunii ce are loc la click-ul mouse-ului pe butonul respectiv
    public abstract void onClick();


    /*! \fn public void onMouseMove(MouseEvent e)
          \brief Verific daca mouse-ul este "deasupra" unui buton grafic.

          \param e Evenimentul ce genereaza miscarea mouse-ului.
  */
    public void onMouseMove(MouseEvent e)
    {

        if(bounds.contains(e.getX(),e.getY()))
            hovering=true;
        else
            hovering=false;

    }



    /*! \fn public void onMouseRelease(MouseEvent e)
          \brief Verific daca mouse-ul este "eliberat"

          \param e Evenimentul ce genereaza miscarea mouse-ului.
  */
    public void onMouseRelease(MouseEvent e)
    {
        /// atunci cand se "elibereaza" butonul mouse-ului , se va produce evenimentul dorit
        if(hovering)
            onClick();
    }



    /// Getters & setters pt atribute.s
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isHovering() {
        return hovering;
    }

    public void setHovering(boolean hovering) {
        this.hovering = hovering;
    }

}
