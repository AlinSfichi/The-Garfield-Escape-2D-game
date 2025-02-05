package PaooGame.SaveData;

public class SaveData {
    public int level;
    public int posX;
    public int posY;
    public int enemyPosX;
    public int enemyPosY;
    public int counter;

    public int[][] fishPosition;
    public int[][] trapPosition;

    public int[][] itemPosition;

    public SaveData(int level,int posX,int posY,int enemyPosX,int enemyPosY,int counter,String fishPos,String trapPos,String itemPos)
    {
        this.level=level;
        this.counter=counter;
        this.posX=posX;
        this.posY=posY;
        this.enemyPosX=enemyPosX;
        this.enemyPosY=enemyPosY;

        //convertim string-ul din baza de date intr-o matrice
        String[] rows = fishPos.split(";");
        fishPosition = new int[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            String[] values = rows[i].split(",");
            fishPosition[i] = new int[values.length];
            for (int j = 0; j < values.length; j++) {
                fishPosition[i][j] = Integer.parseInt(values[j]);
            }
        }

        //convertim string-ul din baza de date intr-o matrice
        String[] rows1 = itemPos.split(";");
        itemPosition = new int[rows1.length][];
        for (int i = 0; i < rows1.length; i++) {
            String[] values1 = rows1[i].split(",");
            itemPosition[i] = new int[values1.length];
            for (int j = 0; j < values1.length; j++) {
                itemPosition[i][j] = Integer.parseInt(values1[j]);
            }
        }

        //convertim string-ul din baza de date intr-o matrice
        String[] rows2 = trapPos.split(";");
        trapPosition = new int[rows2.length][];
        for (int i = 0; i < rows2.length; i++) {
            String[] values2 = rows2[i].split(",");
            trapPosition[i] = new int[values2.length];
            for (int j = 0; j < values2.length; j++) {
                trapPosition[i][j] = Integer.parseInt(values2[j]);
            }
        }
    }
}
