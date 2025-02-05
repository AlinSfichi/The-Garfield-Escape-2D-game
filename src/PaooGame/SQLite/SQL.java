package PaooGame.SQLite;

import PaooGame.SaveData.SaveData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

/*! \public class SQL
    \brief Gestioneaza baza de date

    Aceasta clasa utilizeaza Singleton Pattern.
 */
public class SQL {

    private static SQL instance = null;
    private static Connection c = null;

    /*! \fn  private SQL()
        \brief Constructorul privat al clasei SQL ; creeaza conexiunea cu baza de date
                si creeaza tabelele pentru scoruri , respectiv pentru setari
    */
    private SQL() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:javaGame.db");
            c.setAutoCommit(false);

            Statement stmt = c.createStatement();


            String sql = "CREATE TABLE IF NOT EXISTS SCORING " +
                    "(ID TEXT PRIMARY KEY NOT NULL," +
                    " LEVEL INT NOT NULL, " +
                    " SCORE INT NOT NULL) ";
            stmt.executeUpdate(sql);


            sql = "CREATE TABLE IF NOT EXISTS SETTINGS " +
                    "(ID TEXT PRIMARY KEY NOT NULL," +
                    "AUDIO TEXT NOT NULL, " +
                    "ON_OF TEXT NOT NULL); ";
            stmt.executeUpdate(sql);



            sql = "CREATE TABLE IF NOT EXISTS SAVE " +
                    "(ID TEXT PRIMARY KEY NOT NULL," +
                    "LEVEL INT NOT NULL, " +
                    "POSX INT NOT NULL, " +
                    "POSY INT NOT NULL, " +
                    "ENEMYPOSX INT NOT NULL, " +
                    "ENEMYPOSY INT NOT NULL, " +
                    "COUNTER INT NOT NULL," +
                    "FISHPOSITION TEXT," +
                    "TRAPPOSITION TEXT," +
                    "ITEMPOSITION TEXT); ";
            stmt.executeUpdate(sql);

            c.commit();
            stmt.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
            System.out.println("Baza de date creata/deschisa cu succes!");
    }

    /*! \fn public static SQL getInstance()
            \brief Returneaza instanta bazei de date daca aceasta exista. In caz contrar, creeaza o noua
                    instanta, pe care apoi o returneaza.
    */
    public static SQL getInstance() {
        if (instance == null)
            instance = new SQL();
        return instance;
    }

    /*! \fn public void closeConnection()
                \brief Inchide conexiunea cu baza de date.
        */
    public void closeConnection() {
        try {
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    /*! \fn public void addPlayStatus(int level, int counter)
                  \brief Adauga in tabela SAVE starea jocului (nivelul si counter-ul)

                  \param level Nivelul jocului.
                  \param counter Valoarea counter-ului.
    */
    public void addPlayStatus(int level,int posX,int posY,int enemyPosX,int enemyPosY, int counter, String fishPos, String trapPos, String itemPos) {
        Date date = new Date();

        try {
            String sql = "INSERT INTO SAVE (ID, LEVEL, POSX, POSY, ENEMYPOSX, ENEMYPOSY, COUNTER, FISHPOSITION, TRAPPOSITION, ITEMPOSITION) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setLong(1, date.getTime());
            pstmt.setInt(2, level);
            pstmt.setInt(3, posX);
            pstmt.setInt(4, posY);
            pstmt.setInt(5, enemyPosX);
            pstmt.setInt(6, enemyPosY);
            pstmt.setInt(7, counter);
            pstmt.setString(8, fishPos);
            pstmt.setString(9, trapPos);
            pstmt.setString(10, itemPos);
            pstmt.executeUpdate();
            c.commit();
            pstmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void addAudioStatus(String audioType, int status) {
        Date date = new Date();

        try {
            String sql = "INSERT INTO SETTINGS (ID, AUDIO, ON_OF) VALUES (?, ?, ?)";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setLong(1, date.getTime());
            pstmt.setString(2, audioType);
            pstmt.setInt(3, status);
            pstmt.executeUpdate();
            c.commit();
            pstmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    /*! \fn public SaveData getPlayStatus()
                 \brief Returneaza starea jocului
   */
    public SaveData getPlayStatus() {
        try {
            int level = -1, counter = -1,posX=-1,posY=-1,enemyPosX=-1,enemyPosY=-1;
            String fishPosition = "";
            String trapPosition = "";
            String itemPosition = "";

            String sql = "SELECT LEVEL, POSX, POSY, ENEMYPOSX, ENEMYPOSY, COUNTER, FISHPOSITION, TRAPPOSITION, ITEMPOSITION FROM SAVE WHERE ID = (SELECT MAX(ID) FROM SAVE)";
            PreparedStatement pstmt = c.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                level = rs.getInt("LEVEL");
                posX = rs.getInt("POSX");
                posY = rs.getInt("POSY");
                enemyPosX = rs.getInt("ENEMYPOSX");
                enemyPosY = rs.getInt("ENEMYPOSY");
                counter = rs.getInt("COUNTER");
                fishPosition = rs.getString("FISHPOSITION");
                trapPosition = rs.getString("TRAPPOSITION");
                itemPosition = rs.getString("ITEMPOSITION");
            }

            rs.close();
            pstmt.close();

            return new SaveData(level,posX,posY,enemyPosX,enemyPosY, counter, fishPosition, trapPosition, itemPosition);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return null;
    }

    public boolean isEmpty()
    {
        boolean ok=false;
        try {


            String sql = "SELECT 1 FROM SAVE LIMIT 1";
            PreparedStatement pstmt = c.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                ok=true;
            }

            rs.close();
            pstmt.close();

            return ok;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return ok;
    }

    public int getMusicStatus() {
        try {
            String sql = "SELECT ON_OF FROM SETTINGS WHERE ID = (SELECT MAX(ID) FROM SETTINGS WHERE AUDIO = 'music')";
            PreparedStatement pstmt = c.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            int status = -1;
            if (rs.next()) {
                status = rs.getInt("ON_OF");
            }

            rs.close();
            pstmt.close();

            return status;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return -1;
    }

    public int getSoundStatus() {
        try {
            String sql = "SELECT ON_OF FROM SETTINGS WHERE ID = (SELECT MAX(ID) FROM SETTINGS WHERE AUDIO = 'sound')";
            PreparedStatement pstmt = c.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            int status = -1;
            if (rs.next()) {
                status = rs.getInt("ON_OF");
            }

            rs.close();
            pstmt.close();

            return status;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return -1;
    }

    /*! \fn public void addScore(int level, int score)
          \brief  Insereaza in baza de date scorul pentru fiecare nivel.

          \param level Nivelul al carui scor se adauga in baza de date.
          \param score Scorul care se adauga in baza de date.
  */

    public void addScore(int level, int score) {
        Date date = new Date();

        try {
            String sql = "INSERT INTO SCORING (ID, LEVEL, SCORE) VALUES (?, ?, ?)";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setLong(1, date.getTime());
            pstmt.setInt(2, level);
            pstmt.setInt(3, score);
            pstmt.executeUpdate();
            c.commit();
            pstmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    /*! \fn public int getHighScore(int level)
          \brief  Interogheaza si returneaza din baza de date cel mai bun scor pentru nivelul dat ca parametru.

          \param level Nivelul pentru care se cere cel mai bun scor.
  */
    public int getHighScore(int level) {
        try {
            String sql = "SELECT MAX(SCORE) AS SCORE FROM SCORING WHERE LEVEL = ?";

            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setInt(1, level);
            ResultSet rs = pstmt.executeQuery();

            int highScore = -1;
            if (rs.next()) {
                highScore = rs.getInt("SCORE");
            }

            rs.close();
            pstmt.close();

            return highScore;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return -1;
    }

    /*! \fn public void deleteSCORES()
          \brief Sterge toate inregistrarile din tabela de scoruri.
  */
    public void deleteSCORES() {
        try {
            String sql = "DELETE FROM SCORING";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.executeUpdate();
            c.commit();
            pstmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    /*! \fn public void deleteAUDIO()
          \brief Sterge toate inregistrarile din tabela de setari audio.
  */
    public void deleteAUDIO() {
        try {
            String sql = "DELETE FROM SETTINGS";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.executeUpdate();
            c.commit();
            pstmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
