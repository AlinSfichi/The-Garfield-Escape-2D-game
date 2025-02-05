package PaooGame.Entities;

import PaooGame.RefLinks;

import java.awt.*;


/*! \class Entity
    \brief Implementeaza notiunea abstracta de entitate in joc, "element cu care se poate intercationa"
 */
public abstract class Entity {
        ///coordonatele x si y sunt de tip float pentru a se elimina erorile de rotunjire
        ///ce pot sa apara in urma calculelor, urmand a se converti la intreg doar in momentul desenarii.
    protected float x;      /*!< Pozitia pe axa X a "tablei" de joc a imaginii entitatii.*/
    protected float y;      /*!< Pozitia pe axa Y a "tablei" de joc a imaginii entitatii.*/
    protected int width;    /*!< Latimea imaginii entitatii.*/
    protected int height;   /*!< Inaltimea imaginii entitatii.*/

    private final String name;    /*!< Numele entitatii (de exemplu : "Cheese"/"Trap" etc). */

    public static final int DEFAULT_HEALTH  = 1;   /*!< Valoarea implicita a vietii unei entitati.*/
    protected Rectangle bounds; /*!< Dreptunghiul de coliziune.*/


    protected RefLinks refLink; /*!< O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.*/

    protected int health;   /*!< "Viata" entitatii in joc*/
    protected boolean active=true; /*!< Flag pentru mentinerea entitatii pe tabla de joc*/




    /*! \fn Entity(RefLinks refLink, float x, float y, int width, int height, String name)
                \brief Constructorul cu parametri al clasei Entity

                \param reflink Referinta catre un obiect "shortcut"
                \param x Pozitia pe axa X a imaginii entitatii
                \param y Pozitia pe axa Y a imaginii entitatii
                \param width Latimea imaginii entitatii
                \param height Inaltimea imaginii entitatii
                \param name Denumirea entitatii
    */
    public Entity(RefLinks refLink, float x, float y, int width, int height, String name)
    {
        this.refLink=refLink;
        this.x=x;
        this.y=y;

        this.width=width;
        this.height=height;

        this.name=name;
        health=DEFAULT_HEALTH  ;

        bounds=new Rectangle(0,0,width,height);
    }

        ///Metoda abstracta destinata actualizarii starii curente
    public abstract void Update();

        ///Metoda abstracta destinata desenarii starii curente
    public abstract void Render(Graphics g);


    /*! \fn boolean getCollisionBounds(float xOffset,float yOffset)
               \brief Returneaza un dreptunghi ce "acopera" suprafata entitatii

               \param xOffset Offset-ul pe axa X la care verific coliziunea
               \param yOffset Offset-ul pe axa Y la care verific coliziunea
   */
    public Rectangle getCollisionBounds(float xOffset,float yOffset)
    {
        return new Rectangle((int)(x+bounds.x+xOffset),(int)(y+bounds.y+yOffset),bounds.width,bounds.height);
    }


        ///Metoda abstracta destinata implementarii distrugerii unei entitati
    public abstract void die();



    /*! \fn public void hurt(int amount)
               \brief Distrugerea treptata a entitatii

               \param amount Cantitatea care trebuie scazuta din viata entitatii atunci cand aceasta este distrusa treptat
   */
    public void hurt(int amount)
    {
        health-=amount;
        if(health<=0.5){
            active=false;
            die();
        }
    }


    /// Getters & Setters pentru atribute
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

    public RefLinks getRefLink() {
        return refLink;
    }

    public void setRefLink(RefLinks refLink) {
        this.refLink = refLink;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

}
