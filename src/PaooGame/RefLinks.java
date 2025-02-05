package PaooGame;


import PaooGame.Audio.AudioPlayer;
import PaooGame.Entities.Creatures.Hero;
import PaooGame.Graphics.FogOfWar;
import PaooGame.Input.KeyManager;
import PaooGame.Input.MouseManager;
import PaooGame.SaveData.SaveData;
import PaooGame.Settings.GameSettings;
import PaooGame.Worlds.World;

/*! \public class RefLinks
    \brief Clasa ce retine o serie de referinte ale unor elemente pentru a fi usor accesibile.

    Altfel ar trebui ca functiile respective sa aiba o serie intreaga de parametri si ar ingreuna programarea.
 */
public class RefLinks
{
    private Game game;          /*!< Referinta catre obiectul Game.*/
    private World world;            /*!< Referinta catre harta curenta.*/

    protected static GameSettings gameSettings; /*!< Referinta catre setarile jocului.*/
    private Hero hero;
    private SaveData saveData;

    private FogOfWar fogOfWar;

    /*! \fn public RefLinks(Game game)
        \brief Constructorul de initializare al clasei.

        \param game Referinta catre obiectul game.
     */
    public RefLinks(Game game)
    {
        this.game = game;
    }

    /*! \fn public KeyManager GetKeyManager()
        \brief Returneaza referinta catre managerul evenimentelor de tastatura.
     */
    public KeyManager GetKeyManager()
    {
        return game.GetKeyManager();
    }

    /*! \fn public KeyManager GetMouseManager()
        \brief Returneaza referinta catre managerul evenimentelor de mouse.
     */
    public MouseManager GetMouseManager()
    {
        return game.GetMouseManager();
    }


    /*! \fn public Game GetGame()
        \brief Intoarce referinta catre obiectul Game.
     */
    public Game GetGame()
    {
        return game;
    }


    /*! \fn public void SetGame(Game game)
        \brief Seteaza referinta catre un obiect Game.

        \param game Referinta obiectului Game.
     */
    public void SetGame(Game game)
    {
        this.game = game;
    }


    /*! \fn public World GetWorld()
        \brief Intoarce referinta catre harta curenta.
     */
    public World GetWorld()
    {
        return world;
    }



    /*! \fn public void SetWorld(World world)
        \brief Seteaza referinta catre harta curenta.

        \param world Referinta catre harta curenta.
     */
    public void SetWorld(World world)
    {
        this.world = world;
    }

    /*! \fn public World GetWorld()
        \brief Intoarce referinta catre harta curenta.
     */
    public Hero GetHero()
    {
        return hero;
    }



    /*! \fn public void SetHero(Hero Hero)
        \brief Seteaza referinta catre harta curenta.

        \param Hero Referinta catre harta curenta.
     */
    public void SetHero(Hero hero)
    {
        this.hero = hero;
    }

    public void SetFogOfWar(FogOfWar fogOfWar)
    {
        this.fogOfWar=fogOfWar;
    }

    public FogOfWar GetFogOfWar()
    {
        return fogOfWar;
    }

    public void setSaveData(SaveData x)
    {
        saveData=x;
    }
    public SaveData getSaveData()
    {
        return saveData;
    }

    public void setGameSettings(GameSettings gS)
    {
        gameSettings=gS;
    }

    public GameSettings getGameSettings() {
        return gameSettings;
    }

}
