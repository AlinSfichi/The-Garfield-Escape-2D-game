package PaooGame.Entities.Statics;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;

/*! \class Door extends StaticEntity
    \brief Implementeaza notiunea de linie de finish (sub forma une usi)
 */

public class Door extends StaticEntity{

    /*! \fn public Door(RefLinks refLink, float x, float y)
          \brief Constructorul cu parametri al clasei Door

           \param reflink Referinta catre un obiect "shortcut"
           \param x Pozitia pe axa X a entitatii statice
           \param y Pozitia pe axa Y a entitatii statice
*/
    public Door(RefLinks refLink, float x, float y) {
        super(refLink, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT,"Door");

        bounds.x=0;
        bounds.y=0;
        bounds.width=width;
        bounds.height=height;
    }

    /// actualizarea starii curente
    @Override
    public void Update() {

    }

    /*! \fn public void Draw(Graphics g)
        \brief Functia de desenare a starii curente.

        \param g Contextul grafic in care se realizeaza desenarea.
     */
    @Override
    public void Render(Graphics g) {
        g.drawImage(Assets.door,(int)x,(int)y,width,height,null);
    }


    /*! \fn public void die()
        \brief Defineste notiunea de distrugere a entitatii pt obiecte de tip linie de finish
*/
    @Override
    public void die()
    {

    }
}
