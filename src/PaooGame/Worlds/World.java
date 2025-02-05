package PaooGame.Worlds;

import PaooGame.Entities.Creatures.Enemy;
import PaooGame.Entities.Creatures.Hero;
import PaooGame.Entities.EntityManager;
import PaooGame.Entities.Statics.Door;
import PaooGame.Entities.Statics.Fish;
import PaooGame.Entities.Statics.Trap;
import PaooGame.Items.ItemManager;
import PaooGame.Level.Level;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;
import PaooGame.Tiles.TileManager;

import java.awt.*;
import java.util.Random;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
public class World {
    private RefLinks refLink;   /*!< O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.*/
    private int width;          /*!< Latimea hartii in numar de dale.*/
    private int height;         /*!< Inaltimea hartii in numar de dale.*/
    private int [][] mapTiles;     /*!< Referinta catre o matrice cu codurile dalelor ce vor construi harta.*/
    private int [][] fishPosition; /*!< Referinta catre o matrice cu pestisorii ce nu au fost colectati de pe harta.*/
    private int [][] trapPosition; /*!< Referinta catre o matrice cu capcanele de pe harta.*/
    private final int spawnX=0;/*!< Pozitia de start a jucatorului pe axa X*/
    private final int spawnY=32;    /*!< Pozitia de start a jucatorului pe harta Y*/

    private int enemyX=300;/*!< Pozitia de start a inamicului pe axa X*/
    private int enemyY=300;    /*!< Pozitia de start a inamicului pe harta Y*/

    private TileManager tileManager; /*!< Referinta catre managerul de tileuri*/

    /// Entities
    private EntityManager entityManager; /*!< Referinta catre un manager de entitati*/
    private ItemManager itemManager; /*!< Referinta catre un manager de itemi*/


    public World(RefLinks refLink, boolean isL)
    {
        this.refLink = refLink;
        tileManager=new TileManager();

        width = 33;
        height = 17;

        refLink.SetWorld(this);
        itemManager=new ItemManager(refLink);
        entityManager=new EntityManager(refLink,new Hero(refLink,100,100,isL));
        if(isL)
        {
            enemyX=refLink.getSaveData().enemyPosX;
            enemyY=refLink.getSaveData().enemyPosY;
            entityManager.getHero().setX(refLink.getSaveData().posX);
            entityManager.getHero().setY(refLink.getSaveData().posY);
        }
        else {
            enemyX=300;
            enemyY=300;
            entityManager.getHero().setX(spawnX);
            entityManager.getHero().setY(spawnY);
        }



        entityManager.addEntity(new Door(refLink, (width-1)*32, (height-2)*32));

        switch (Level.getInstance().getLevelNr())
        {
            case 1 : {
                //Harta LEVEL 1
                try{
                    mapTiles =new int[height][width];

                    File fisier = new File("map1.txt");
                    FileReader fr = new FileReader(fisier);
                    BufferedReader br = new BufferedReader(fr);

                    String linie;
                    int row = 0;

                    // Citirea fiecărei linii și conversia într-o matrice de întregi
                    while ((linie = br.readLine()) != null) {
                        String[] elemente = linie.split(" ");
                        for (int col = 0; col < elemente.length; col++) {
                            mapTiles[row][col] = Integer.parseInt(elemente[col]);
                        }
                        row++;
                    }

                    br.close();
                    fr.close();
                }
                catch (Exception e)
                {
                    System.out.println(e.toString());
                    System.exit(1);
                }
                addFish(isL);
                break;
            }

            case 2: {
                try{
                    mapTiles =new int[height][width];

                    File fisier = new File("map2.txt");
                    FileReader fr = new FileReader(fisier);
                    BufferedReader br = new BufferedReader(fr);

                    String linie;
                    int row = 0;

                    // Citirea fiecărei linii și conversia într-o matrice de întregi
                    while ((linie = br.readLine()) != null) {
                        String[] elemente = linie.split(" ");
                        for (int col = 0; col < elemente.length; col++) {
                            mapTiles[row][col] = Integer.parseInt(elemente[col]);
                        }
                        row++;
                    }

                    br.close();
                    fr.close();
                }
                catch (Exception e)
                {
                    System.out.println(e.toString());
                    System.exit(1);
                }
                addFish(isL);
                addTrap(isL);
//                Enemy enemy=new Enemy(refLink, enemyX, enemyY);
//                entityManager.addEntity(enemy);
                break;
            }
            case 3: {
                //Harta LEVEL 3
                try{
                    mapTiles =new int[height][width];

                    File fisier = new File("map3.txt");
                    FileReader fr = new FileReader(fisier);
                    BufferedReader br = new BufferedReader(fr);

                    String linie;
                    int row = 0;

                    // Citirea fiecărei linii și conversia într-o matrice de întregi
                    while ((linie = br.readLine()) != null) {
                        String[] elemente = linie.split(" ");
                        for (int col = 0; col < elemente.length; col++) {
                            mapTiles[row][col] = Integer.parseInt(elemente[col]);
                        }
                        row++;
                    }

                    br.close();
                    fr.close();
                }
                catch (Exception e)
                {
                    System.out.println(e.toString());
                    System.exit(1);
                }
                addFish(isL);
                addTrap(isL);
                Enemy enemy=new Enemy(refLink, enemyX, enemyY);
                entityManager.addEntity(enemy);
                break;
            }
            default:{

            }
        }










        
    }

    /*! \fn public Tile GetTile(int x, int y)
        \brief Intoarce o referinta catre dala aferenta codului din matrice de dale.

        In situatia in care dala nu este gasita datorita unei erori ce tine de cod dala, coordonate gresite etc se
        intoarce o dala predefinita (ex. grassTile, mountainTile)
     */
    public Tile GetTile(int x, int y){

        return tileManager.getTile(mapTiles[x][y]);

    }

    public boolean IsSolid(int x, int y)
    {
        return tileManager.getTile(mapTiles[x][y]).IsSolid();
    }


    /*! \fn public  void Update()
            \brief Actualizarea hartii in functie de evenimente (un obiect a fost colectat)
         */
    public void Update()
    {
        itemManager.Update();
        entityManager.Update();
    }


    /*! \fn public void Render(Graphics g)
         \brief Functia de desenare a hartii.

         \param g Contextl grafic in care se realizeaza desenarea.
      */
    public void Render(Graphics g){

        /// randare tiles
        for(int x=0;x<height;x++)
            for(int y=0;y<width;y++)
            {
                GetTile(x,y).Draw(g,x*Tile.TILE_WIDTH,y*Tile.TILE_HEIGHT);
                if(x>=1 &&x<height-1 && y>=1 &&y<width-1) {
                    if (!this.refLink.GetFogOfWar().isVisible(y, x)) {
                        tileManager.getTile(6).Draw(g, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT); //Randare CEATA

                        //g.drawImage(Assets.fish,(int)y*Tile.TILE_HEIGHT,(int)x*Tile.TILE_WIDTH,height,width,null);
                    }
                }
            }


        /// randare items
        itemManager.Render(g);
        ///randare entitati
        entityManager.Render(g);




    }

    public void addFish(boolean isLoad) ///generam pestisori random sau ii adaugam din baza de date
    {
        if(isLoad)
        {
            fishPosition = refLink.getSaveData().fishPosition;

            for(int i=0;i<height;i++) {
                for (int j = 0; j < width; j++)
                    if(fishPosition[i][j]==1)
                    {
                        entityManager.addEntity(new Fish(refLink, j * 32, i * 32));
                    }
            }

        }
        else {
            fishPosition = new int[height][width];
            /// adaugare pestisori (nr lor este in functie de nivelul la care s-a ajuns)
            Random random = new Random();
            for (int i = 0; i < Level.getInstance().getLevelNr()*5; i++) {
                int w = random.nextInt(height);
                int h = random.nextInt(width);
                Tile t = GetTile(w, h);

                /// PESTISORII va fi plasat doar pe zona in care eroul se poate deplasa
                if (!t.IsSolid()) {
                    fishPosition[w][h] = 1;
                    entityManager.addEntity(new Fish(refLink, h * 32, w * 32));
                } else {
                    i--;
                }
            }
        }

    }
    public void removeFish(int x,int y)
    {
        fishPosition[x][y]=0;
    }
    public String getfishPositionString() ///transformam matricea cu pozitiile pestisorilor intr-un string pentru a putea fi stocat in baza de date
    {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fishPosition.length; i++) {
            for (int j = 0; j < fishPosition[i].length; j++) {
                sb.append(fishPosition[i][j]);
                if (j < fishPosition[i].length - 1) {
                    sb.append(",");
                }
            }
            if (i < fishPosition.length - 1) {
                sb.append(";");
            }
        }
        return sb.toString();
    }

    public String gettrapPositionString() ///transformam matricea cu pozitiile capcanelor intr-un string pentru a putea fi stocat in baza de date
    {
        if(trapPosition==null)
        {
            trapPosition = new int[height][width];
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < trapPosition.length; i++) {
            for (int j = 0; j < trapPosition[i].length; j++) {
                sb.append(trapPosition[i][j]);
                if (j < trapPosition[i].length - 1) {
                    sb.append(",");
                }
            }
            if (i < trapPosition.length - 1) {
                sb.append(";");
            }
        }
        return sb.toString();
    }

    public void addTrap(boolean isLoad) ///generam capcane random sau le adaugam din baza de date
    {
        if(isLoad)
        {
            trapPosition = refLink.getSaveData().trapPosition;

            for(int i=0;i<height;i++) {
                for (int j = 0; j < width; j++)
                    if(trapPosition[i][j]==1)
                    {
                        entityManager.addEntity(new Trap(refLink, j * 32, i * 32));
                    }
            }

        }
        else {
            trapPosition = new int[height][width];
            /// adaugare CAPCANE (nr lor este in functie de nivelul la care s-a ajuns)
            Random random = new Random();
            for (int i = 0; i < Level.getInstance().getLevelNr()*4; i++) {
                int w = random.nextInt(height);
                int h = random.nextInt(width);
                Tile t = GetTile(w, h);

                /// CAPCANA va fi plasata doar pe zona in care eroul se poate deplasa
                if (!t.IsSolid()) {
                    trapPosition[w][h] = 1;
                    entityManager.addEntity(new Trap(refLink, h * 32, w * 32));
                } else {
                    i--;
                }
            }
        }

    }




    /// Getters & Setters pt atribute.
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public RefLinks getRefLink() {
        return refLink;
    }

    public void setRefLink(RefLinks refLink) {
        this.refLink = refLink;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public void setItemManager(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public int getEnemyX() {
        return enemyX;
    }

    public void setEnemyX(int x)
    {
        enemyX=x;
    }

    public int getEnemyY() {
        return enemyY;
    }

    public void setEnemyY(int y)
    {
        enemyY=y;
    }
}
