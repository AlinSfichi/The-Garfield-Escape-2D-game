package PaooGame.UI;

import PaooGame.RefLinks;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/*! \public class UIManager
    \brief Implementeaza notiunea de manager de obiecte la nivelul interfetei.

    Similar cu EntityManager.
 */

public class UIManager {

    private RefLinks refLink; /*!< O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.*/
    private ArrayList<UIObject> objects; /*!< ArrayList ce contine obiectele interfetei grafice.*/


    /*! \fn public UIManager(RefLinks refLink)
         \brief Constructorul de initializare al clasei.

         \param reflink Referinta catre un obiect de tip shortcut.
    */
    public UIManager(RefLinks refLink)
    {
        this.refLink=refLink;
        objects=new ArrayList<UIObject>();
    }


    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a obiectelor grafice.

   */
    public void Update()
    {
        for(UIObject o:objects)
            o.Update();

    }

    /*! \fn public void Render(Graphics g)
        \brief Deseneaza starea curenta a obiectelor grafice.

        \param g Contextul grafic in care se deseneaza.
   */
    public void Render(Graphics g)
    {
        for(UIObject o:objects)
            o.Render(g);
    }


    /*! \fn public void onMouseMove(MouseEvent e)
          \brief Verific daca mouse-ul este "deasupra" unui buton grafic.

          \param e Evenimentul ce genereaza miscarea mouse-ului.
  */
    public void onMouseMove(MouseEvent e)
    {
        for(UIObject o:objects)
            o.onMouseMove(e);
    }

    /*! \fn public void onMouseRelease(MouseEvent e)
         \brief Verific daca mouse-ul este "eliberat"

         \param e Evenimentul ce genereaza miscarea mouse-ului.
 */
    public void onMouseRelease(MouseEvent e)
    {
        for(UIObject o:objects)
            o.onMouseRelease(e);
    }


    /*! \fn public void addObject(UIObject o)
            \brief Adauga un obiect grafic nou.

            \param o Obiectul ce se adauga.
    */
    public void addObject(UIObject o)
    {

        objects.add(o);
    }


    /*! \fn public void removeObject(UIObject o)
            \brief Sterge un obiect existent.

            \param o Obiectul ce se sterge.
    */
    public void removeObject(UIObject o)
    {
        objects.remove(o);
    }


    ///Getters & setters pt atribute.
    public RefLinks getRefLink() {
        return refLink;
    }

    public void setRefLink(RefLinks refLink) {
        this.refLink = refLink;
    }

    public ArrayList<UIObject> getObjects() {
        return objects;
    }

    public void setObjects(ArrayList<UIObject> objects) {
        this.objects = objects;
    }
}
