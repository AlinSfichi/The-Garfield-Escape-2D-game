package PaooGame.Graphics;

import java.awt.*;

/*! \public class Text
    \brief Clasa este folosita pentru afisarea unui sir de caractere la o anumita pozitie in fereastra grafica

 */
public class Text {

    /*! \fn public static void drawString(Graphics g,String text,int xPos,int yPos,Color c)
         \brief Deseneaza text in contextul grafic.

         \param g Contextul grafic in care se realizeaza desenarea.
         \param text Textul care se doreste a fi desenat.
         \param xPos Pozitia pe axa X la care se incepe scrierea textului
         \param yPos Pozitia pe axa Yla care se incepe scrierea textului
         \param c Culoarea textului
*/
    public static void drawString(Graphics g,String text,int xPos,int yPos,Color c){
        g.setColor(c);
        int x=xPos;
        int y=yPos;


        g.drawString(text,x,y);
    }
}
