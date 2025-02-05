package PaooGame.Entities.Statics;

import PaooGame.Entities.Entity;
import PaooGame.RefLinks;


/*! \public abstract class StaticEntity extends Entity
    \brief Implementeaza notiunea de entitate statica( cascaval, capcana).
 */

public abstract class StaticEntity extends Entity {


    /*! \fn public StaticEntity(RefLinks refLink, float x, float y, int width, int height,String name)
               \brief Constructorul cu parametri al clasei StaticEntity

                \param reflink Referinta catre un obiect "shortcut"
                \param x Pozitia pe axa X a imaginii entitatii
                \param y Pozitia pe axa Y a imaginii entitatii
                \param width Latimea imaginii entitatii
                \param height Inaltimea imaginii entitatii
                \param name Denumirea entitatii statice
   */
    public StaticEntity(RefLinks refLink, float x, float y, int width, int height,String name) {
        super(refLink, x, y, width, height,name);

    }
}
