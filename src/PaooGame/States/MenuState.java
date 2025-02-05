package PaooGame.States;


import PaooGame.Graphics.Assets;
import PaooGame.Graphics.Text;
import PaooGame.Level.Level;
import PaooGame.RefLinks;
import PaooGame.SQLite.SQL;
import PaooGame.UI.ClickListener;
import PaooGame.UI.UIImageButton;
import PaooGame.UI.UIManager;
import PaooGame.Game;

import java.awt.*;

/*! \public class MenuState extends State
    \brief Implementeaza notiunea de menu pentru joc.
 */
public class MenuState extends State
{

    /*! \fn public MenuState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */

    public MenuState(RefLinks refLink)
    {
        super(refLink);
        uiManager=new UIManager(refLink);
        refLink.GetMouseManager().setUIManager(uiManager);


        uiManager.addObject(new UIImageButton(450, 160, 192, 64, Assets.play_btn, new ClickListener() {
            @Override
            public void onClick() {
                refLink.GetMouseManager().setUIManager(refLink.GetGame().getPlayState().getUiManager());
                State.SetState(refLink.GetGame().getPlayState());
                //SQL.getInstance().deleteSCORES();

            }
        }));

        uiManager.addObject(new UIImageButton(450, 230, 192, 64, Assets.load_btn, new ClickListener() {
            @Override
            public void onClick() {
                if(!SQL.getInstance().isEmpty()) {
                    refLink.GetGame().setPlayState(true);
                }
                else
                {
                    refLink.GetGame().setPlayState(false);
                    System.out.println("Nu exista nicio salvare anterioara, creem un Nivel NOU!");
                }
                refLink.GetMouseManager().setUIManager(refLink.GetGame().getPlayState().getUiManager());
                State.SetState(refLink.GetGame().getPlayState());

            }
        }));
        uiManager.addObject(new UIImageButton(450, 300, 192, 64, Assets.scores_btn, new ClickListener() {
            @Override
            public void onClick() {
                refLink.GetMouseManager().setUIManager(refLink.GetGame().getHighScoresState().getUiManager());
                State.SetState(refLink.GetGame().getHighScoresState());

            }
        }));
        uiManager.addObject(new UIImageButton(450, 370, 192, 64, Assets.help_btn, new ClickListener() {
            @Override
            public void onClick() {
                refLink.GetMouseManager().setUIManager(refLink.GetGame().getHelpState().getUiManager());
                State.SetState(refLink.GetGame().getHelpState());

            }
        }));

        uiManager.addObject(new UIImageButton(450, 440, 192, 64, Assets.settings_btn, new ClickListener() {
            @Override
            public void onClick() {
                refLink.GetMouseManager().setUIManager(refLink.GetGame().getSettingsState().getUiManager());
                State.SetState(refLink.GetGame().getSettingsState());

            }
        }));





    }


    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a meniului.
     */
    @Override
    public void Update()
    {
        uiManager.Update();
    }



    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a meniului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Render(Graphics g) {
        try{
            g.drawImage(Assets.menuBackground,0,0,1056,544,null);

        }
        catch (Exception m)
        {
            System.out.println("Exception occured "+m);
        }
        finally {

            g.setFont(new Font("Algerian", Font.PLAIN, 80));
            Text.drawString(g,"The Garfield Escape ",100,85,Color.BLUE);


            uiManager.Render(g);
        }


    }
}
