package PaooGame.UI;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \ public class UIImageButton extends UIObject
    \brief Implementeaza notiunea de obiect caracterizat printr-o imagine
 */
public class UIImageButton extends UIObject{

    private BufferedImage[] image;  /*!< Referinta catre imaginea aferenta butonului.*/
    public ClickListener clicker;   /*!< Obiect de tip ClickListener ce defineste tipul de eveniment la apasarea butonului mouse-ului.*/


    /*! \fn public UIImageButton(float x, float y, int width, int height, BufferedImage[] image,ClickListener clicker)
          \brief Constructorul de initializare al clasei.

          \param x Pozitia pe axa X a tablei de joc pentru imaginea obiectului interfetei grafice.
          \param y Pozitia pe axa Y a tablei de joc pentru imaginea obiectului interfetei grafice.
          \param width Latimea obiectui interfetei grafice.
          \param height Inaltimea obiectui interfetei grafice.
          \param image Imaginea obiectului.
          \param clicker Tipul de eveniment la apasarea buton-ului mouse-ului.
  */
    public UIImageButton(float x, float y, int width, int height, BufferedImage[] image,ClickListener clicker) {
        super(x, y, width, height);
        this.image=image;
        this.clicker=clicker;
    }

    /*! \fn public void Update()
         \brief Actualizarea starii curente.
    */
    @Override
    public void Update() {

    }


    /*! \fn public void Render(Graphics g)
         \brief Desenarea starii curente.

         \param g Contextul grafic in care se realizeaza desenarea.
    */
    @Override
    public void Render(Graphics g) {

        if(!isHovering())
            g.drawImage(image[0],(int)x,(int)y,width,height,null);
        else
            g.drawImage(image[1],(int)x,(int)y,width,height,null);
    }

    /*! \fn public void onClick()
         \brief Se executa evenimentul la apasarea butonului mouse-ului.
    */
    @Override
    public void onClick() {
        clicker.onClick();
    }
}
