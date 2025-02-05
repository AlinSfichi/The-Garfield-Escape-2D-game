package PaooGame.States;


import PaooGame.Graphics.Assets;
import PaooGame.Graphics.Text;
import PaooGame.RefLinks;
import PaooGame.UI.ClickListener;
import PaooGame.UI.UIImageButton;
import PaooGame.UI.UIManager;

import java.awt.*;

/*! \public class HelpState extends State
    \brief Implementeaza notiunea de help pentru joc (explica ce are de facut eroul pentru a castiga)
 */
public class HelpState extends State
{


    /*! \fn public HelpState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public HelpState(RefLinks refLink)
    {
        super(refLink);
        uiManager=new UIManager(refLink);
        refLink.GetMouseManager().setUIManager(uiManager);

        uiManager.addObject(new UIImageButton(220, 410, 192, 64, Assets.resume_btn, new ClickListener() {
            @Override
            public void onClick() {
                refLink.GetMouseManager().setUIManager(State.GetPreviousState().getUiManager());
                State.SetState(State.GetPreviousState());

            }
        }));
    }
    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a starii de help.
     */
    @Override
    public void Update()
    {
        uiManager.Update();
    }



    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta de help .

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Render(Graphics g) {
        g.setFont(new Font("Arial", Font.BOLD, 20));
        try{
            g.drawImage(Assets.menuBackground,0,0,1056,544,null);
            g.drawImage(Assets.lasagna,830,320,200,200,null);
        }
        catch (Exception e){
            System.out.println("Exception occured "+e);
        }
        finally {
            uiManager.Render(g);

            Text.drawString(g,"Help Garfield pick the fish before time runs out.",120,60,Color.WHITE);
            Text.drawString(g,"You need to know that when you reach the traps, your speed will decrease a lot.",120,90,Color.WHITE);
            Text.drawString(g,"If you collect a fish, your timmer will increase.",120,120,Color.WHITE);
            Text.drawString(g,"Be careful and avoid the White Dog, because it will kill your cat.  ",120,150,Color.WHITE);
            Text.drawString(g,"You can move through the maze using the arrow keys.  ",120,200,Color.WHITE);

            g.drawImage(Assets.ar_UP,240,240,null);
            g.drawImage(Assets.ar_DOWN,240,310,null);
            g.drawImage(Assets.ar_LEFT,170,310,null);
            g.drawImage(Assets.ar_RIGHT,310,310,null);

            Text.drawString(g,"BACK ->",120,450,Color.WHITE);
        }





    }
}
