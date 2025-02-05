package PaooGame.Items;

import PaooGame.RefLinks;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;


/*! \public class ItemManager
    \brief Gestioneaza toti itemii inca necolectati de pe harta
 */
public class ItemManager {

    private RefLinks refLink; /*!< O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.*/
    private ArrayList<Item> items; /*!< ArrayList cu itemi.*/


    /*! \fn public ItemManager(Reflinks reflink)
           \brief Constructorul cu parametri de initializare al clasei ItemManager

           \param reflink Referinta catre un obiect de tip shortcut.
  */
    public ItemManager(RefLinks refLink)
    {
        this.refLink=refLink;
        items=new ArrayList<Item>();
    }

    /*! \fn public void Update()
       \brief Actualizarea acelor itemi de care se ocupa managerul de itemi, incluzand stergerea acestora de pe harta
             atunci cand sunt colectati.
*/
    public void Update()
    {
        Iterator<Item> it= items.iterator();
        while(it.hasNext())
        {
            Item i=it.next();
            i.Update();


            /// daca item-ul a fost colectat, va fi eliminat de pe harta
            if(i.isPickedUp())
                it.remove();

        }
    }

    /*! \fn public void Render(Graphics g)
        \brief Desenarea tuturor itemilor

        \param g Contextul grafic in care se realizeaza desenarea.
*/
    public void Render(Graphics g)
    {
        for(Item i:items)
            i.Render(g);
    }


    /*! \fn public void addItem(Item i)
        \brief Adauga un nou item pe harta.

        \param i Item-ul care se adauga
*/
    public void addItem(Item i)
    {
        i.setRefLink(refLink);
        items.add(i);
    }


    /// Getters & Setters pt atribute
    public RefLinks getRefLink() {
        return refLink;
    }

    public void setRefLink(RefLinks refLink) {
        this.refLink = refLink;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
