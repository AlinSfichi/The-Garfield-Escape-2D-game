package PaooGame.States;


import PaooGame.Graphics.Assets;
import PaooGame.Graphics.Text;
import PaooGame.RefLinks;
import PaooGame.UI.ClickListener;
import PaooGame.UI.UIImageButton;
import PaooGame.UI.UIManager;

import java.awt.*;

/*! \public class PauseState extends State
    \brief Implementeaza notiunea de pauza pentru joc.
 */
public class PauseState extends State
{
    /*! \fn public PauseState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public PauseState(RefLinks refLink)
    {
        super(refLink);
        uiManager=new UIManager(refLink);
        uiManager.addObject(new UIImageButton(350, 350, 192, 64, Assets.resume_btn, new ClickListener() {
            @Override
            public void onClick() {
                refLink.GetMouseManager().setUIManager(State.GetPreviousState().getUiManager());
                State.SetState(State.GetPreviousState());

            }
        }));
    }
    /*! \fn public void Update()
        \brief Actualizeaza starea curenta pentru Pause State.
     */
    @Override
    public void Update()
    {
        uiManager.Update();
    }



    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta .

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Render(Graphics g) {
        g.setFont(new Font("Arial", Font.BOLD, 60));
        try{
            g.drawImage(Assets.menuBackground,0,0,1056,544,null);
            g.drawImage(Assets.lasagna,830,320,200,200,null);
        }
        catch (Exception e){
            System.out.println("Exception occured "+e);
        }
        finally {
            uiManager.Render(g);
            Text.drawString(g,"YOUR GAME IS PAUSED",100,200,Color.WHITE);

            g.setFont(new Font("Arial", Font.BOLD, 40));
            Text.drawString(g,"Time remained: "+PlayState.countdown,100,300,Color.WHITE);
            Text.drawString(g,"RESUME  ->",100,400,Color.WHITE);
        }


    }
}
