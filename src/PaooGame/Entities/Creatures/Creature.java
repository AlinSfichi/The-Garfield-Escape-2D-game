package PaooGame.Entities.Creatures;

import PaooGame.Entities.Entity;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;
import PaooGame.Worlds.World;

/*! \class Creature extends Entity
    \brief Defineste notiunea abstracta de creatura/individ/fiinta din joc.

    Notiunea este definita doar de  viteza de deplasare si distanta cu care trebuie sa se
    miste/deplaseze in urma calculelor.
 */

public abstract class Creature extends Entity {


    protected float speed;/*!< Viteza unui caracter.*/
    protected float xMove;/*!< Distanta cu care trebuie sa se miste eroul pe axa X.*/
    protected float yMove;/*!< Distanta cu care trebuie sa se miste eroul pe axa Y.*/


    public static final float DEFAULT_SPEED         = 4.0f; /*!< Viteza implicita a unu caracter.*/
    public static final int DEFAULT_CREATURE_WIDTH  = 32;   /*!< Latimea implicita a imaginii caracterului.*/
    public static final int DEFAULT_CREATURE_HEIGHT = 32;   /*!< Inaltimea implicita a imaginii caracterului.*/



    /*! \fn public Creature(RefLinks refLink, float x, float y, int width, int height,String name)
               \brief Constructorul cu parametri al clasei Creature

                \param reflink Referinta catre un obiect "shortcut"
                \param x Pozitia pe axa X a imaginii creaturii
                \param y Pozitia pe axa Y a imaginii creaturii
                \param width Latimea imaginii creaturii
                \param height Inaltimea imaginii creaturii
                \param name Numele creaturii
   */
    public Creature(RefLinks refLink, float x, float y, int width, int height,String name) {
        super(refLink, x, y,width,height,name);
        speed=DEFAULT_SPEED;
        xMove=0;
        yMove=0;
    }

    /*! \fn public void Move()
               \brief Implementeaza miscarea caracterului pe harta
   */
    public void Move()
    {
        MoveX();
        MoveY();
    }

    /*! \fn public void MoveX()
                   \brief Implementeaza miscarea caracterului pe axa X
       */
    public void MoveX(){
        if(xMove>0){

            // coliziuni dreapta
            int posx=(int)((x+xMove+bounds.x+bounds.width)/ Tile.TILE_WIDTH);
            //coliziuni colt dreapta sus si jos ale dreptunghiului de coliziune
            if(!collisionWithTile(posx,(int)(y+bounds.y)/Tile.TILE_HEIGHT) && !collisionWithTile(posx,(int)(y+ bounds.y+bounds.height)/Tile.TILE_HEIGHT))
            {
                x+=xMove;
            }

        }else if (xMove<0){
            int posx=(int)(x+xMove+bounds.x)/ Tile.TILE_WIDTH;
            //coliziuni colt stanga sus si jos ale dreptunghiului de coliziune
            if(!collisionWithTile(posx,(int)(y+bounds.y)/Tile.TILE_HEIGHT) && !collisionWithTile(posx,(int)(y+ bounds.y+bounds.height)/Tile.TILE_HEIGHT))
            {
                x+=xMove;
            }
        }

    }


    /*! \fn public void MoveY()
               \brief Implementeaza miscarea caracterului pe axa Y
   */
    public void MoveY(){
        if(yMove<0){
            int posy=(int)(y+yMove+ bounds.y)/Tile.TILE_HEIGHT;

            //coliziuni colt stanga si dreapta sus ale dreptunghiului de coliziune
            if(!collisionWithTile((int)(x+ bounds.x)/Tile.TILE_WIDTH,posy) &&
                    !collisionWithTile((int)(x+ bounds.x+ bounds.width)/Tile.TILE_WIDTH,posy))
            {
                y += yMove;
            }
        }
        else if (yMove>0){
            int posy=(int)(y+yMove+ bounds.y+bounds.height)/Tile.TILE_HEIGHT;

            //coliziuni colt stanga si dreapta jos ale dreptunghiului de coliziune
            if(!collisionWithTile((int)(x+ bounds.x)/Tile.TILE_WIDTH,posy) &&
                    !collisionWithTile((int)(x+ bounds.x+ bounds.width)/Tile.TILE_WIDTH,posy))
            {
                y+=yMove;
            }
        }
    }


    /*! \fn protected boolean collisionWithTile(int x,int y)
               \brief Semnaleaza coliziunea cu zidurile labirintului

               \param x Pozitia pe axa X a pt acel tile cu care se verifica coliziunea
               \param y Pozitia pe axa Y a pt acel tile cu care se verifica coliziunea
   */
    protected boolean collisionWithTile(int x,int y){
        if(x==-1||x==33) //verific sa nu fie posibila iesirea din harta pe orizontala
        {
            return true;
        }

        return getRefLink().GetWorld().IsSolid(y,x);
    }

    /// Getters & Setters pentru atribute
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }

}
