package PaooGame.States;

import PaooGame.Graphics.Assets;
import PaooGame.Graphics.Text;
import PaooGame.Level.Level;
import PaooGame.RefLinks;
//import PaooGame.SQLite.SQL;
import PaooGame.SQLite.SQL;
import PaooGame.SaveData.SaveData;
import PaooGame.Timer.Timer;
import PaooGame.UI.ClickListener;
import PaooGame.UI.UIImageButton;
import PaooGame.UI.UIManager;
import PaooGame.Worlds.World;

import java.awt.*;

/*! \public class PlayState extends State
    \brief Implementeaza/controleaza jocul.
 */

public class PlayState extends State{

    SaveData saveData;
    private World world; /*!< O referinta catre harta jocului.*/
    public static final int DEFAULT_COUNTDOWN=60;
    public static int countdown=DEFAULT_COUNTDOWN; /*!< Numarul de secunde alocate pt fiecare nivel.*/
    private Timer timer;            /*!< Referinta catre un obiect de tip timer ce contorizeaza scurgerea timpului pt fiecare nivel.*/
    Level lvl=Level.getInstance();  /*!< Referinta catre un obiect de tip nivel.*/

    boolean isLoad;


    /*! \fn public PlayState(RefLinks refLink)
        \brief Constructorul de initializare al clasei

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public PlayState(RefLinks refLink,boolean isL){

        super(refLink);
        isLoad=isL;
        if(isLoad)
        {
            //System.out.println("LOAD!!");

            saveData=SQL.getInstance().getPlayStatus();
            refLink.setSaveData(saveData);


            countdown=refLink.getSaveData().counter;
            lvl.setLevel(refLink.getSaveData().level-1);
            lvl.setChangeLevel(true);
        }
        timer=new Timer(1000);
        world=new World(refLink,isLoad);
        refLink.SetWorld(world);
        uiManager=new UIManager(refLink);
        refLink.GetMouseManager().setUIManager(uiManager);

        uiManager.addObject(new UIImageButton(300, 0, 96, 32, Assets.pause_btn, new ClickListener() {
            @Override
            public void onClick() {
                refLink.GetMouseManager().setUIManager(refLink.GetGame().getPauseState().getUiManager());
                State.SetState(refLink.GetGame().getPauseState());
            }
        }));


        uiManager.addObject(new UIImageButton(500, 0, 96, 32, Assets.exit_btn, new ClickListener() {
            @Override
            public void onClick() {
                SQL.getInstance().addPlayStatus(lvl.getLevelNr(),(int)refLink.GetHero().getX(),(int)refLink.GetHero().getY(),refLink.GetWorld().getEnemyX(),refLink.GetWorld().getEnemyY(),countdown,refLink.GetWorld().getfishPositionString(),refLink.GetWorld().gettrapPositionString(),refLink.GetHero().getItemPositionString());
                refLink.GetMouseManager().setUIManager(refLink.GetGame().getMenuState().getUiManager());
                State.SetState(refLink.GetGame().getMenuState());

                /// atunci cand butonul "Exit" este apasat, va fi afisat meniul,
                /// dupa care o eventuala apasare a butonului  "Start" va relua
                /// jocul de la primul nivel
                //level=0;
                //changeLevel=true;
                lvl.setLevel(0);
                lvl.setChangeLevel(true);

            }
        }));


        uiManager.addObject(new UIImageButton(700, 0, 96, 32, Assets.help_btn, new ClickListener() {
            @Override
            public void onClick() {
                refLink.GetMouseManager().setUIManager(refLink.GetGame().getHelpState().getUiManager());
                State.SetState(refLink.GetGame().getHelpState());

            }
        }));
        uiManager.addObject(new UIImageButton(900, 0, 96, 32, Assets.settings_btn, new ClickListener() {
            @Override
            public void onClick() {
                refLink.GetMouseManager().setUIManager(refLink.GetGame().getSettingsState().getUiManager());
                State.SetState(refLink.GetGame().getSettingsState());

            }
        }));

    }


    /*! \fn public void SetWorld()
        \brief Reseteaza harta (stabileste o noua harta) si o ataseaza jocului prin scurtatura reflink.
     */
    public void SetWorld()
    {
        world=new World(refLink,isLoad);
        refLink.SetWorld(world);
    }


    /*! \fn public void Update()
       \brief Actualizeaza starea curenta a jocului.
    */
    @Override
    public void Update() {
        uiManager.Update();
        verify();
        world.Update();

        if(timer.TimePassed()){
            countdown--;
            timer.SetDelay(1000);
        }

        if(countdown==0)
        {
            refLink.GetMouseManager().setUIManager(refLink.GetGame().getGameOverState().getUiManager());
            lvl.setLevel(0);
            lvl.setChangeLevel(true);
            State.SetState(refLink.GetGame().getGameOverState());
        }

    }


    /// functie ce verifica trecerea la un alt nivel

    /*! \fn private void verify()
         \brief Se verifica daca jocul s-a terminat sau ,in caz contrar, se trece la urmatorul nivel.
   */
    private void verify()
    {
        if (lvl.isChangeLevel() ) {
            if(lvl.getLevelNr()!=0){
                SQL.getInstance().addScore(Level.getInstance().getLevelNr(), countdown);
            }

            lvl.incLevel();
            if (lvl.getLevelNr() < 4) {
                SetWorld();

                if(!isLoad) {
                    countdown = 60;
                }
                else
                {
                    isLoad=false;
                }


                lvl.setChangeLevel(false);
            } else {
                /// jocul a fost finalizat cu succes si se revine in MenuState pentru a se relua, la cerere
//                refLink.GetMouseManager().setUIManager(refLink.GetGame().getMenuState().getUiManager());
                lvl.setLevel(0);
                refLink.GetMouseManager().setUIManager(refLink.GetGame().getGameWinState().uiManager);
                State.SetState(refLink.GetGame().getGameWinState());
//                State.SetState(refLink.GetGame().getMenuState());

            }
        }
    }



    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a jocului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Render(Graphics g) {


        world.Render(g);
        uiManager.Render(g);

        lvl.Render(g);
        timer.Render(g);


        Text.drawString(g,""+countdown,255,23,Color.WHITE);
    }



    /// Getters & Setters pt atribute
    public void setCountdown(int value){
        countdown=value;
    }
    public void addCountdown(int value){
        countdown+=value;
    }

    public int getCountdown(){
        return countdown;
    }


}
