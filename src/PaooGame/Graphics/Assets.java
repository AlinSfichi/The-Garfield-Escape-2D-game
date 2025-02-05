package PaooGame.Graphics;

import java.awt.image.BufferedImage;

/*! \class public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */
public class Assets
{
        /// Referinte catre elementele grafice (dale) utilizate in joc.
    public static BufferedImage wall1;
    public static BufferedImage wall2;
    public static BufferedImage wall3;
    public static BufferedImage floor1;
    public static BufferedImage floor2;
    public static BufferedImage floor3;


    public static BufferedImage fish;
    public static BufferedImage spiderweb;
    public static BufferedImage door;

    public static BufferedImage fog;

    public static BufferedImage[] heroLeft;
    public static BufferedImage[] heroRight;
    public static BufferedImage[] heroUp;
    public static BufferedImage[] heroDown;


    public static BufferedImage[] dogLeft;
    public static BufferedImage[] dogRight;
    public static BufferedImage[] dogUp;
    public static BufferedImage[] dogDown;



    public static BufferedImage[] play_btn;
    public static BufferedImage[] load_btn;
    public static BufferedImage[] scores_btn;
    public static BufferedImage[] exit_btn;
    public static BufferedImage[] help_btn;
    public static BufferedImage[] pause_btn;
    public static BufferedImage[] resume_btn;
    public static BufferedImage[] settings_btn;
    public static BufferedImage[] musicOn_btn;
    public static BufferedImage[] musicOff_btn;

    public static BufferedImage menuBackground;
    public static BufferedImage lasagna;

    public static BufferedImage ar_UP;
    public static BufferedImage ar_DOWN;
    public static BufferedImage ar_RIGHT;
    public static BufferedImage ar_LEFT;

    public static BufferedImage timer;
    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */
    public static void Init()
    {


        fish=ImageLoader.LoadImage("/textures/fish.png");
        fog=ImageLoader.LoadImage("/textures/fog1.png");
        spiderweb=ImageLoader.LoadImage("/textures/spiderweb.png");
        door=ImageLoader.LoadImage("/textures/door.png");
        menuBackground=ImageLoader.LoadImage("/textures/menuBackground.jpg");
        lasagna=ImageLoader.LoadImage("/textures/lasagna.png");
        timer=ImageLoader.LoadImage("/textures/hourglass.png");

        play_btn=new BufferedImage[2];
        play_btn[0]=ImageLoader.LoadImage("/textures/controls/play.png");
        play_btn[1]=ImageLoader.LoadImage("/textures/controls/play2.png");
        
        load_btn=new BufferedImage[2];
        load_btn[0]=ImageLoader.LoadImage("/textures/controls/load.png");
        load_btn[1]=ImageLoader.LoadImage("/textures/controls/load2.png");

        scores_btn=new BufferedImage[2];
        scores_btn[0]=ImageLoader.LoadImage("/textures/controls/scores.png");
        scores_btn[1]=ImageLoader.LoadImage("/textures/controls/scores2.png");

        exit_btn=new BufferedImage[2];
        exit_btn[0]=ImageLoader.LoadImage("/textures/controls/exit.png");
        exit_btn[1]=ImageLoader.LoadImage("/textures/controls/exit2.png");

        help_btn=new BufferedImage[2];
        help_btn[0]=ImageLoader.LoadImage("/textures/controls/help.png");
        help_btn[1]=ImageLoader.LoadImage("/textures/controls/help2.png");
        
        pause_btn=new BufferedImage[2];
        pause_btn[0]=ImageLoader.LoadImage("/textures/controls/pause.png");
        pause_btn[1]=ImageLoader.LoadImage("/textures/controls/pause2.png");

        resume_btn=new BufferedImage[2];
        resume_btn[0]=ImageLoader.LoadImage("/textures/controls/resume.png");
        resume_btn[1]=ImageLoader.LoadImage("/textures/controls/resume2.png");

        settings_btn=new BufferedImage[2];
        settings_btn[0]=ImageLoader.LoadImage("/textures/controls/settings.png");
        settings_btn[1]=ImageLoader.LoadImage("/textures/controls/settings2.png");
        
        musicOn_btn=new BufferedImage[2];
        musicOn_btn[0]=ImageLoader.LoadImage("/textures/controls/musicOn.png");
        musicOn_btn[1]=ImageLoader.LoadImage("/textures/controls/musicOn2.png");

        musicOff_btn=new BufferedImage[2];
        musicOff_btn[0]=ImageLoader.LoadImage("/textures/controls/musicOff.png");
        musicOff_btn[1]=ImageLoader.LoadImage("/textures/controls/musicOff2.png");

        ar_UP=ImageLoader.LoadImage("/textures/controls/KeyboardButtonsDir_up.png");
        ar_DOWN=ImageLoader.LoadImage("/textures/controls/KeyboardButtonsDir_down.png");
        ar_LEFT=ImageLoader.LoadImage("/textures/controls/KeyboardButtonsDir_left.png");
        ar_RIGHT=ImageLoader.LoadImage("/textures/controls/KeyboardButtonsDir_right.png");

        /// Se creaza temporar un obiect SpriteSheet initializat prin intermediul clasei ImageLoader
        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/wall_floor.png"));

        SpriteSheet sheet2 = new SpriteSheet(ImageLoader.LoadImage("/textures/cats_2.png"));

        SpriteSheet sheet3 = new SpriteSheet(ImageLoader.LoadImage("/textures/dogs.png"));

        /// Se obtin subimaginile corespunzatoare elementelor necesare.
        wall1=sheet.crop(0,0);
        wall2=sheet.crop(1,0);
        wall3=sheet.crop(2,0);
        floor1=sheet.crop(0,1);
        floor2=sheet.crop(1,1);
        floor3=sheet.crop(2,1);



        heroUp=new BufferedImage[3];
        heroDown=new BufferedImage[3];
        heroRight=new BufferedImage[3];
        heroLeft=new BufferedImage[3];
        


        heroLeft[0] = sheet2.crop(3, 1);
        heroLeft[1] = sheet2.crop(4, 1);
        heroLeft[2] = sheet2.crop(5, 1);


        heroRight[0] = sheet2.crop(3, 2);
        heroRight[1] = sheet2.crop(4, 2);
        heroRight[2] = sheet2.crop(5, 2);

        heroUp[0] = sheet2.crop(3, 3);
        heroUp[1] = sheet2.crop(4, 3);
        heroUp[2] = sheet2.crop(5, 3);

        heroDown[0] = sheet2.crop(3, 0);
        heroDown[1] = sheet2.crop(4, 0);
        heroDown[2] = sheet2.crop(5, 0);


        dogUp=new BufferedImage[3];
        dogDown=new BufferedImage[3];
        dogRight=new BufferedImage[3];
        dogLeft=new BufferedImage[3];

        dogLeft[0] = sheet3.crop(3, 1);
        dogLeft[1] = sheet3.crop(4, 1);
        dogLeft[2] = sheet3.crop(5, 1);


        dogRight[0] = sheet3.crop(3, 2);
        dogRight[1] = sheet3.crop(4, 2);
        dogRight[2] = sheet3.crop(5, 2);

        dogUp[0] = sheet3.crop(3, 3);
        dogUp[1] = sheet3.crop(4, 3);
        dogUp[2] = sheet3.crop(5, 3);

        dogDown[0] = sheet3.crop(3, 0);
        dogDown[1] = sheet3.crop(4, 0);
        dogDown[2] = sheet3.crop(5, 0);
    }
}
