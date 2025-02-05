package PaooGame.Tiles;

public class TileManager
{
    private static final int NO_TILES   = 32;
    public static Tile[] tiles          = new Tile[NO_TILES];       /*!< Vector de referinte de tipuri de dale.*/

    /// De remarcat ca urmatoarele dale sunt statice si publice. Acest lucru imi permite sa le am incarcate
    /// o singura data in memorie
    public TileManager()
    {
        tiles[0] = new Floor1Tile(0);     /*!< Dala de tip podea1*/
        tiles[1] = new Floor2Tile(1);     /*!< Dala de tip podea2*/
        tiles[2] = new Floor3Tile(2);     /*!< Dala de tip podea3*/
        tiles[3] = new Wall1Tile(3);     /*!< Dala de tip perete1*/
        tiles[4] = new Wall2Tile(4);     /*!< Dala de tip perete2*/
        tiles[5] = new Wall3Tile(5);     /*!< Dala de tip perete3*/
        tiles[6] = new fog(6);   /*!< Dala de tip Ceata*/
    }

    public Tile getTile(int id)
    {
        return tiles[id];
    }
}
