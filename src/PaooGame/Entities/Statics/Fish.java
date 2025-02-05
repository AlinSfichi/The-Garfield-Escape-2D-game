package PaooGame.Entities.Statics;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;

/*! \class Cheese extends StaticEntity
    \brief Implementeaza notiunea de item ce trebuie colectat de catre erou
 */

public class Fish extends StaticEntity{

    /*! \fn public Cheese(RefLinks refLink, float x, float y)
           \brief Constructorul cu parametri al clasei Cheese

            \param reflink Referinta catre un obiect "shortcut"
            \param x Pozitia pe axa X a entitatii statice
            \param y Pozitia pe axa Y a entitatii statice
*/
    public Fish(RefLinks refLink, float x, float y) {
        super(refLink, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT,"Fish");

        bounds.x=0;
        bounds.y=0;
        bounds.width=width;
        bounds.height=height;
    }


    /*! \fn public Update()
       \brief Actualizarea starii curente
*/
    @Override
    public void Update() {}


    /*! \fn public Update()
   \brief Desenarea starii curente
*/
    @Override
    public void Render(Graphics g) {
        g.drawImage(Assets.fish,(int)x,(int)y,width,height,null);
    }


    /*! \fn public void die()
   \brief Defineste notiunea de distrugere a entitatii pt obiecte de tip cascaval(itemi ce trebuie colectati)
*/
    @Override
    public void die()
    {
        /// va fi creat un item peste care la randul lui va disparea de pe harta
       //refLink.GetWorld().getItemManager().addItem(Item.fItem.createNew((int)x,(int)y));
    }
}
