package PaooGame.States;


import PaooGame.Graphics.Assets;
import PaooGame.Graphics.Text;
import PaooGame.Level.Level;
import PaooGame.RefLinks;
import PaooGame.SQLite.SQL;
import PaooGame.UI.ClickListener;
import PaooGame.UI.UIImageButton;
import PaooGame.UI.UIManager;

import java.awt.*;

/*! \public class HighScoresState extends State
    \brief Implementeaza notiunea de game over.
 */
public class HighScoresState extends State
{


    /*! \fn public HighScoresState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public HighScoresState(RefLinks refLink)
    {
        super(refLink);
        uiManager=new UIManager(refLink);
        refLink.GetMouseManager().setUIManager(uiManager);

        uiManager.addObject(new UIImageButton(400, 400, 192, 64, Assets.exit_btn, new ClickListener() {
            @Override
            public void onClick() {
                refLink.GetMouseManager().setUIManager(refLink.GetGame().getMenuState().getUiManager());
                State.SetState(refLink.GetGame().getMenuState());

                /// atunci cand butonul "Exit" este apasat, va fi afisat meniul,


            }
        }));
    }
    /*! \fn public void Update()
        \brief Actualizeaza starea curenta de game over.
     */
    @Override
    public void Update()
    {
        uiManager.Update();
    }



    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a starii de game over.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Render(Graphics g) {

        try {
            g.drawImage(Assets.menuBackground,0,0,1056,544,null);
            g.drawImage(Assets.lasagna,830,320,200,200,null);
        }
        catch (Exception e){
            System.out.println("Exception occured "+e);
        }
        finally {
            g.setFont(new Font("Algerian", Font.BOLD, 100));
            Text.drawString(g,"HIGH  SCORES",200,100,Color.WHITE);

            g.setFont(new Font("Arial", Font.BOLD, 60));
            Text.drawString(g,"LEVEL 1: "+ SQL.getInstance().getHighScore(1),230,200,Color.WHITE);
            Text.drawString(g,"LEVEL 2: "+ SQL.getInstance().getHighScore(2),230,270,Color.WHITE);
            Text.drawString(g,"LEVEL 3: "+ SQL.getInstance().getHighScore(3),230,340,Color.WHITE);
            uiManager.Render(g);
        }



    }
}
