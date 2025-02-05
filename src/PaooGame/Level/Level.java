package PaooGame.Level;

import PaooGame.Graphics.Text;

import java.awt.*;


/*! \public class Level
    \brief Clasa defineste notiunea de nivel in joc.

    Aceasta clasa utilizeaza Singleton Pattern in ideea ca exista o singura instanta
    a nivelului ; nu pot exista mai multe nivele care sa fie jucate simultan
 */

public class Level {


    private static Level instance=null; /*!< Instanta a nivelului.*/
    private static int levelNr; /*!< Numarul nivelului la care s-a ajuns.*/
    private static boolean changeLevel; /*!< Flag pentru schimbarea nivelului jocului.*/



    /*! \fn private Level()
         \brief Constructorul privat al clasei Level.
*/
    private Level()
    {
        levelNr=1;
        changeLevel=false;
    }


    /*! \fn public static Level getInstance()
            \brief Returneaza instanta nivelului daca aceasta exista. In caz contrar, creeaza o noua
                    instanta, pe care apoi o returneaza.
    */
    public static Level getInstance(){
        if(instance==null)
            instance=new Level();
        return instance;
    }

    /*! \fn public void Render(Graphics g)
        \brief Desenarea numarului nivelului in contextul grafic.

        \param g Contextul grafic in care se realizeaza desenarea.
*/
    public void Render(Graphics g){
        /// se afiseaza nr nivelului actual
        g.setFont(new Font("Arial", Font.BOLD, 20));
        Text.drawString(g,"LEVEL "+Integer.toString(Level.getInstance().getLevelNr()),115,23,Color.WHITE);
    }

    /// Getters & Setters pt atribute.
    public void setLevel(int level)
    {
            levelNr = level;
    }

    public void setChangeLevel(boolean change)
    {
        changeLevel=change;
    }

    public int getLevelNr(){return levelNr;}

    public boolean isChangeLevel()
    {

        return changeLevel==true;
    }

    public void incLevel(){
        this.setLevel(levelNr+1);}

}
