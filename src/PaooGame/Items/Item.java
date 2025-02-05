package PaooGame.Items;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \public class Item
    \brief Clasa defineste notiunea de obiect ce poate fi colectat si depus in inventory
 */
public class Item {


    public static Item[] items=new Item[256];/*!< Un vector de items ce retine cate o singura instanta pt fiecare item. */
    public static Item fItem=new Item(Assets.fish,"Fish",0); /*!< Item de tip peste. */


    public static final int ITEM_WIDTH=32; /*!< Latimea implicita a imaginii item-ului.*/
    public static final int ITEM_HEIGHT=32;/*!< Inaltimea implicita a imaginii item-ului.*/



    protected RefLinks refLink;/*!< O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.*/
    protected BufferedImage texture;/*!< Referinta catre imaginea item-ului.*/
    protected String name;/*!< Denumirea data item-ului.*/
    protected final int id;/*!< Id-ul asociat fiecarui item.*/

    protected Rectangle bounds; /*!< Dreptunghiul de coliziune.*/

    protected int x; /*!< Pozitia pe axa X a "tablei" de joc a imaginii item-ului.*/
    protected int y; /*!< Pozitia pe axa Y a "tablei" de joc a imaginii item-ului.*/
    protected int count; /*!< Numarul de itemi de un anumit tip ce sunt colectati.*/
    protected boolean pickedUp=false; /*!< Flag ce retine daca un item a fost sau nu colectat .*/



    /*! \fn public Item(BufferedImage texture,String name,int id)
         \brief Constructorul cu parametri de initializare al clasei Item

         \param texture Imaginea asociata item-ului
         \param name Denumirea item-ului
         \param id Id-ul asociat fiecarui item.
*/
    public Item(BufferedImage texture,String name,int id)
    {
        this.texture=texture;
        this.name=name;
        this.id=id;
        count=1;

        bounds= new Rectangle(x,y,ITEM_WIDTH,ITEM_HEIGHT);
        items[id]=this;
    }


    /*! \fn public void Update()
           \brief Actualizarea starii item-ului (trecerea lui in inventory atunci cand este colectat de catre erou)
  */
    public void Update(){
        /// atunci cand eroul atinge item-ul respectiv, acesta va disparea de pe harta
        if(refLink.GetWorld().getEntityManager().getHero().getCollisionBounds(0f,0f).intersects(bounds)){
            pickedUp=true;
            refLink.GetWorld().getEntityManager().getHero().getInventory().addItem(this);
        }
    }

    /*! \fn public void Render(Graphics g)
        \brief Desenarea item-ului

        \param g Contextul grafic in care se realizeaza desenarea.
*/
    public void Render(Graphics g)
    {
        if(refLink==null)
            return;
        Render(g,x,y);
    }


    /*! \fn public void Render(Graphics g,int x,int y)
        \brief Desenarea item-ului

        \param g Contextul grafic in care se realizeaza desenarea.
        \param x Pozitia pe axa X a locului unde se deseneaza.
         \param x Pozitia pe axa Ya locului unde se deseneaza.
    */
    public void Render(Graphics g,int x,int y)
    {
        g.drawImage(texture,x,y,ITEM_WIDTH,ITEM_HEIGHT,null);
    }


    /*! \fn public Item createNew(int x,int y)
       \brief Creeaza un nou item la pozitia specificata.

       \param x Pozitia pe axa X a locului unde se doreste inserarea item-ului.
       \param x Pozitia pe axa Y a locului unde se doreste inserarea item-ului.
  */
    public Item createNew(int x,int y)
    {
        Item i=new Item(texture,name,id);
        i.setPosition(x,y);
        return i;
    }

    /*! \fn public Item createNew(int count)
       \brief Creeaza un nou item si ii stabileste capacitatea.

       \param count Capacitatea item-ului.
  */
    public Item createNew(int count)
    {
        Item i=new Item(texture,name,id);
        i.setPickedUp(true);
        i.setCount(count);
        return i;
    }


    /*! \fn public  void setPosition(int x ,int y)
      \brief Seteaza pozitia item-ului pe harta.

      \param x Pozitia pe axa X.
      \param y Pozitia pe axa Y.
 */
    public void setPosition(int x ,int y)
    {
        this.x=x;
        this.y=y;
        bounds.x=x;
        bounds.y=y;
    }


    /// Getters & setters pt atribute
    public RefLinks getRefLink() {
        return refLink;
    }

    public void setRefLink(RefLinks refLink) {
        this.refLink = refLink;
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isPickedUp() {
        return pickedUp;
    }

    public void setPickedUp(boolean pickedUp) {
        this.pickedUp = pickedUp;
    }

}
