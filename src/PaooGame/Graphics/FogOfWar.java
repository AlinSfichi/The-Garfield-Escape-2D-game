package PaooGame.Graphics;

public class FogOfWar {
    private boolean[][] visible; // matricea ce păstreză vizibilitatea fiecărui tile
    private int width, height; // dimensiunea hărții
    private int playerX, playerY; // poziția jucătorului- eroului

    public FogOfWar(int width, int height, int playerX, int playerY) {
        this.width = width;
        this.height = height;
        this.playerX = playerX;
        this.playerY = playerY;
        visible = new boolean[width][height];
        updateVisibility();
    }

    // reactualizarea zonei vizibile raportată la noua poziție a jucătorului
    private void updateVisibility() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (distance(playerX, playerY, x, y) <= 5) {
                    visible[x][y] = true;
                } else {
                    visible[x][y] = false;
                }
            }
        }
    }

    // metodă de calculare a zonei vizibile. De exemplu distanța euclidiană
    private double distance(int x1, int y1, int x2, int y2) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        return Math.sqrt(dx * dx + dy * dy);
    }

    // la modificarea poziției jucătorului se reactualizează zona vizibilă
    public void setPlayerPosition(int x, int y) {
        playerX = x;
        playerY = y;
        updateVisibility();
    }

    // verifică dacă un tile este sau nu în zona vizibilă
    public boolean isVisible(int x, int y) {
        return visible[x][y];
    }
}
