package PaooGame.Entities.Creatures;

import PaooGame.Entities.Entity;
import PaooGame.Graphics.Animations;
import PaooGame.Graphics.Assets;
import PaooGame.Graphics.FogOfWar;
import PaooGame.Inventory.Inventory;

import PaooGame.Items.Item;
import PaooGame.Level.Level;
import PaooGame.RefLinks;
import PaooGame.States.State;


import java.awt.*;
import java.awt.image.BufferedImage;


/*! \public class Hero extends Creature
    \brief Implementeaza notiunea de erou/player (caracterul controlat de jucator).

    Elementele suplimentare pe care le aduce fata de clasa de baza sunt:
        imaginea
        inventory
        animatiile
 */

public class Hero extends Creature{


    private BufferedImage image;    /*!< Referinta catre imaginea curenta a eroului.*/
    private Inventory inventory;    /*!< Referinta catre inventory-ul in care sunt depuse elementele colectate.*/


    private Animations aDown,aUp,aRight,aLeft;  /*!< Animatiile prezente la deplasarea eroului.*/

    private FogOfWar fogOfWar; /*!< Ceata prezenta in jurul eroului.*/
    private int [][] itemPosition; /*!< Referinta catre o matrice cu itemele ce au fost colectate de pe harta (folosita pentru  salva starea jocului in SQL).*/


    /*! \fn public Hero(RefLinks refLink, float x, float y)
               \brief Constructorul cu parametri al clasei Creature

                \param reflink Referinta catre un obiect "shortcut"
                \param x Pozitia pe axa X a imaginii eroului
                \param y Pozitia pe axa Y a imaginii eroului
   */
    public Hero(RefLinks refLink, float x, float y,boolean isL) {
        super(refLink, x, y,Creature.DEFAULT_CREATURE_WIDTH,Creature.DEFAULT_CREATURE_HEIGHT,"Hero");
        refLink.SetHero(this);
        bounds.x=4;
        bounds.y=8;
        bounds.width=16;
        bounds.height=16;

        health=3;

        aUp=new Animations(50,Assets.heroUp);
        aDown=new Animations(50,Assets.heroDown);
        aRight=new Animations(50,Assets.heroRight);
        aLeft=new Animations(50,Assets.heroLeft);


        image = aRight.getCurrentFrame();

        inventory=new Inventory(refLink);
        fogOfWar=new FogOfWar(refLink.GetWorld().getWidth(),refLink.GetWorld().getHeight(),(int)x,(int)y);
        refLink.SetFogOfWar(fogOfWar);

        if(isL)//in caz ca avem LOAD, incarcam in inventar itemele care au fost colectate anterior
        {
            for(int i=0;i<17;i++)
            {
                for (int j=0;j<33;j++)
                {
                    itemPosition=refLink.getSaveData().itemPosition;
                    if(itemPosition[i][j]==1) {
                        Item e=Item.fItem.createNew(i, j);
                        inventory.addItem(e);

                    }
                }
            }
        }
        else {
            itemPosition = new int[17][33];
        }
    }


    /// actualizarea pozitiei curente , precum si a animatiilor
    @Override
    public void Update() {
        GetInput();
        Move();

        fogOfWar.setPlayerPosition((int)x/32,(int)y/32);

        aDown.Update();
        aLeft.Update();
        aUp.Update();
        aRight.Update();

        if(getRefLink().GetKeyManager().left)
        {
            image = aLeft.getCurrentFrame();
        }
        if(getRefLink().GetKeyManager().right) {
            image = aRight.getCurrentFrame();
        }
        if(getRefLink().GetKeyManager().up) {
            image = aUp.getCurrentFrame();
        }
        if(getRefLink().GetKeyManager().down) {
            image = aDown.getCurrentFrame();
        }

        checkAttacks();

        inventory.Update();
    }



    /*! \fn  private void checkAttacks()
                   \brief Verifica coliziunile eroului cu entitatile si stabileste
                          ce trebuie modificat pe harta atunci cand eroul intalneste anumite entitati
       */
    private void checkAttacks(){
        /// cb -> dreptunghiul de coliziune al eroului
        Rectangle cb=getCollisionBounds(0,0);

        Rectangle ar=new Rectangle();
        int arSize=20;

        ar.width=arSize;
        ar.height=arSize;

        if(getRefLink().GetKeyManager().up){
            ar.x=cb.x+cb.width/2-arSize/2;
            ar.y=cb.y-arSize;
        }
        else if(getRefLink().GetKeyManager().down){
            ar.x=cb.x+cb.width/2-arSize/2;
            ar.y=cb.y+cb.height;
        }
        else if(getRefLink().GetKeyManager().left){
            ar.x=cb.x-arSize;
            ar.y=cb.y+cb.height/2-arSize/2;
        }
        else if(getRefLink().GetKeyManager().right){
            ar.x=cb.x+cb.width;
            ar.y=cb.y+cb.height/2-arSize/2;
        }
        else{
            return;
        }

        boolean isCollision=false;//presupunem initial ca nu sunt coliziuni cu items
        for(Entity e:refLink.GetWorld().getEntityManager().getEntities())
        {

            if(e.equals(this)) {
                continue;
            }


            if(e.getName().equals("Trap") && e.getCollisionBounds(0,0).intersects(ar)){
                isCollision=true;//setam flagul de coliziuni cu items pe true

            }


            if(e.getName().equals("Enemy") && e.getCollisionBounds(0,0).intersects(ar)){
                die();
            }


            if(e.getName().equals("Fish") && e.getCollisionBounds(0,0).intersects(ar))
            {
                refLink.GetWorld().removeFish((int)e.getY()/32,(int)e.getX()/32);
                refLink.GetWorld().getItemManager().addItem(Item.fItem.createNew((int)e.getX(),(int)e.getY()));//adaugam in Inventar
                itemPosition[(int)e.getY()/32][(int)e.getX()/32]=1;
                e.hurt(1);//Distrugem entity-ul de tip FISH
                refLink.GetGame().getPlayState().addCountdown(3);//adaugam +3 secunde la countdown pentru fiecare pestisor colectat
                return;
            }

            //conditii trecut nivel urmator cand are coliziune cu Door
            if(e.getName().equals("Door") && e.getCollisionBounds(0,0).intersects(ar) && inventory.getInventoryItems().get(0).getCount()>= Level.getInstance().getLevelNr()*4)
            {
                Level.getInstance().setChangeLevel(true);
            }
        }

        if(isCollision)//in caz ca avem coliziuni cu iteme(traps) micsoram viteza eroului (efectul panzei de paianjen)
        {
            speed=0.5f;
        }
        else
        {
            speed=DEFAULT_SPEED;
        }



    }


    /*! \fn  public void die()
                  \brief Defineste notiunea de "moarte"/distrugere a eroului
      */
    @Override
    public void die()
    {
        //AICI vom implementa ce se v-a intampla cand jucatorul moare
        refLink.GetMouseManager().setUIManager(refLink.GetGame().getGameOverState().getUiManager());
        State.SetState(refLink.GetGame().getGameOverState());

        Level.getInstance().setLevel(0);
        Level.getInstance().setChangeLevel(true);
    }


    /*! \fn  private void GetInput()
                      \brief Asteapta input de la tastatura pentru a actualiza pozitia eroului pe harta
          */
    private void GetInput() {
        xMove=0;
        yMove=0;

        if(refLink.GetKeyManager().up)
            yMove=-speed;
        if(refLink.GetKeyManager().down)
            yMove+=speed;
        if(refLink.GetKeyManager().left)
            xMove-=speed;
        if(refLink.GetKeyManager().right)
            xMove+=speed;

    }


    /*! \fn public void Render()
                      \brief Deseneaza starea curenta a eroului pe harta.

                      \param g Contextul grafic in care se realizeaza desenarea.
    */
    @Override
    public void Render(Graphics g) {
        g.drawImage(image,(int)x,(int)y,width,height,null);

        inventory.Render(g);
    }


    /// Getters & Setters pentru atribute
    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public String getItemPositionString() ///transformam matricea cu itemele colectate intr-un string pentru a putea fi stocat in baza de date
    {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < itemPosition.length; i++) {
            for (int j = 0; j < itemPosition[i].length; j++) {
                sb.append(itemPosition[i][j]);
                if (j < itemPosition[i].length - 1) {
                    sb.append(",");
                }
            }
            if (i < itemPosition.length - 1) {
                sb.append(";");
            }
        }
        return sb.toString();
    }
}
