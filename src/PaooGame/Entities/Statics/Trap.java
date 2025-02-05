package PaooGame.Entities.Statics;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;

/*! \public class Trap extends StaticEntity
    \brief Implementeaza notiunea de capcana pt erou
 */

public class Trap extends StaticEntity{

    /*! \fn public Trap(RefLinks refLink, float x, float y)
           \brief Constructorul cu parametri al clasei Trap

            \param reflink Referinta catre un obiect "shortcut"
            \param x Pozitia pe axa X a entitatii statice
            \param y Pozitia pe axa Y a entitatii statice
*/
    public Trap(RefLinks refLink, float x, float y) {
        super(refLink, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT,"Trap");

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
        g.drawImage(Assets.spiderweb,(int)x,(int)y,width,height,null);
        //g.drawRect((int)x,(int)y, bounds.width, bounds.height); //Desenare dreptunghi DEBUG
    }


    /*! \fn public void die()
   \brief Defineste notiunea de distrugere a entitatii pt obiecte de tip capcana
*/
    @Override
    public void die()
    {
    }
}
