package PaooGame.Timer;

import PaooGame.Graphics.Assets;

import java.awt.*;

/*! \public class Timer
    \brief Implementeaza notiunea de timer pentru joc.

 */
public class Timer {
    long delay;  /*!< Intarzierea fata de momentul curent de timp.*/


    /*! \fn  public Timer(long delay)
           \brief Constructorul de initializare al clasei Timer.

           \param delay Intarzierea stabilita pentru timer.
   */
    public Timer(long delay){
        SetDelay(delay);
    }


    /*! \fn  public void SetDelay(long delay)
           \brief Actualizeaza intarzierea fata de momentu actual.

           \param delay Intarzierea stabilita pentru timer.
   */
    public void SetDelay(long delay){
        this.delay=System.currentTimeMillis()+delay;
    }


    /*! \fn   public boolean TimePassed()
          \brief Ofera informatii despre trecerea timpului.
  */
    public boolean TimePassed(){
        return(this.delay-System.currentTimeMillis()<=0);
    }


    /*! \fn   public void Render(Graphics g)
          \brief Deseneaza starea curenta a timer-ului.

          \param g Contextul grafic in care se deseneaza.
  */
    public void Render(Graphics g){
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawImage(Assets.timer,220,0,32,32,null);
    }
}
