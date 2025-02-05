package PaooGame.Entities;

import PaooGame.Entities.Creatures.Hero;
import PaooGame.RefLinks;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/*! \class EntityManager
    \brief Implementeaza notiunea de manager de entitati in joc
 */

public class EntityManager {

    /// entitatile din joc se refera la erou, inamic, respectiv itemi ce trebuie colectati
    private RefLinks refLink;           /*!< O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.*/
    private Hero hero;                  /*!< O referinta catre o entitate de tip erou*/

    private ArrayList<Entity>entities;  /*!< ArrayList cu toate entitatile din joc*/




    /*! \fn EntityManager(RefLinks refLink, Hero hero)
                \brief Constructorul cu parametri al clasei EntityManager. Adauga eroul la lista de entitati.

                \param reflink Referinta catre un obiect "shortcut"
                \param hero Eroul (entitatea principala)
    */
    public EntityManager(RefLinks refLink,Hero hero){
        this.refLink=refLink;
        this.hero=hero;
        entities=new ArrayList<Entity>();
        addEntity(hero);

    }


    /*! \fn private Comparator<Entity> renderSorter
                \brief Se ocupa de ordinea desenarii entitatilor pe harta.
                Eroul si inamicul trebuie sa fie randati "deasupra" entitatilor statice (cascaval ,capcane)
    */
    private Comparator<Entity> renderSorter=new Comparator<Entity>() {
        @Override
        public int compare(Entity a, Entity b) {
            if(a.getName().equals("Hero") || a.getName().equals("Enemy") && (b.getName().equals("Fish")||b.getName().equals("Trap")||b.getName().equals("Door")))
                return 1;
            if((a.getName().equals("Fish") || a.getName().equals("Trap")) && b.getName().equals("Door"))
                return 1;
            return -1;
        }
    };


    /*! \fn public void Update()
             \brief Actualizarea starii curente
 */
    public void Update(){
        Iterator<Entity> it= entities.iterator();
        while(it.hasNext())
        {
            Entity e=it.next();
            e.Update();

            /// daca viata entitatii respective s-a terminat, aceasta va fi eliminata de pe harta
            if(!e.isActive())
                it.remove();
        }

        entities.sort(renderSorter);
    }

    /*! \fn public void Render(Graphics g)
          \brief Desenarea starii curente starii curente.

          \param g Contextl grafic in care se realizeaza desenarea.
*/
    public void Render(Graphics g){

        for(Entity e:entities){
            e.Render(g);
        }
    }



    /*! \fn public void addEntity(Entity e)
      \brief Adauga entitatea la lista entitatilor deja existente

      \param e Entitatea care se doreste a fi adaugata
*/
    public void addEntity(Entity e){
        entities.add(e);
    }


    ///Getters & Setters pentru atribute
    public RefLinks getRefLink() {
        return refLink;
    }

    public void setRefLink(RefLinks refLink) {
        this.refLink = refLink;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }
}
