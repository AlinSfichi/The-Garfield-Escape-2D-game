package PaooGame.Graphics;

import java.awt.image.BufferedImage;
/*! \class Animations
    \brief Clasa defineste animatiile din deplasararea caracterelor

 */
public class Animations {

    private int speed;
    private int i;
    private long lastTime;
    private long timer;
    private BufferedImage[] frames;


    /*! \fn public Animations(int speed,BufferedImage[] images)
           \brief Constructorul cu parametri al clasei Animations

            \param speed Viteza de modificare a animatiilor
            \param images imaginile specifice animatiei
*/
    public Animations(int speed,BufferedImage[] images)
    {
        this.speed=speed;
        this.frames=images;
        i=0;
        timer=0;
        lastTime=System.currentTimeMillis();

    }


    /*! \fn public void Update()
          \brief Actualizarea animatiei curente.
*/
    public void Update(){
        timer+=(System.currentTimeMillis()-lastTime);
        lastTime=System.currentTimeMillis();

        if(timer>=speed){
            i++;
            timer=0;

            if(i>=frames.length){
                i=0;
            }
        }
    }

    /*! \fn public BufferedImage getCurrentFrame()
          \brief Returneaza animatia curenta.
*/
    public BufferedImage getCurrentFrame(){
        return frames[i];
    }
}
