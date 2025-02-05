package PaooGame.Inventory;

import PaooGame.Graphics.Assets;
import PaooGame.Graphics.Text;
import PaooGame.Items.Item;

import PaooGame.Level.Level;
import PaooGame.RefLinks;

import java.awt.*;
import java.util.ArrayList;


/*! \public class Inventory
    \brief Gestioneaza toti itemii colectati de catre erou pe parcursul jocului
 */
public class Inventory {

    private RefLinks refLink; /*!< O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.*/
    private ArrayList<Item> inventoryItems; /*!< ArrayList cu toti itemii colectati*/


    /*! \fn public Inventory(RefLinks refLink)
         \brief Constructorul cu parametri de initializare al clasei Inventory

         \param reflink Referinta catre un obiect shortcut, ce contine o serie de refinte utile.
*/
    public Inventory(RefLinks refLink)
    {
        this.refLink=refLink;
        inventoryItems=new ArrayList<Item>();

        addItem(Item.fItem.createNew(0));
    }

    /*! \fn public void  Update()
         \brief Actualizarea starii curente a inventory-ului
*/
    public void Update(){
    }

    /*! \fn public void Render(Graphics g)
         \brief Desenarea obiectelor din inventory

         \param g Contextul grafic in care se realizeaza desenarea.
*/
    public void Render(Graphics g)
    {

        for(Item i:inventoryItems){
            g.drawImage(Assets.fish,0,0,null);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            Text.drawString(g," : "+Integer.toString(i.getCount())+ "/ "+ Level.getInstance().getLevelNr()*4,30,23,Color.WHITE);
        }

    }


    /*! \fn public void addItem(Item item)
     \brief Adauga un item in inventory.

     \param item Item-ul ce va fi adaugat.
*/
    public void addItem(Item item){
        for(Item i:inventoryItems){
            if(i.getId()==item.getId()){
                i.setCount(i.getCount()+item.getCount());
                return;
            }
        }
        inventoryItems.add(item);
    }



    /// Getters & Setters pentru atribute
    public RefLinks getRefLink() {
        return refLink;
    }

    public void setRefLink(RefLinks refLink) {
        this.refLink = refLink;
    }

    public ArrayList<Item> getInventoryItems() {
        return inventoryItems;
    }

    public void setInventoryItems(ArrayList<Item> inventoryItems) {
        this.inventoryItems = inventoryItems;
    }
}
