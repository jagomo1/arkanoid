import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Brick extends Sprite {

    public static int BRICK_WIDTH = 100;
    public static int BRICK_HEIGHT = 25;
    private int lifes = 1;
    private int bonus = 0;

    public Brick(int x, int y, int lifes, int bonus){
        this.width = BRICK_WIDTH;
        this.height = BRICK_HEIGHT;
        this.positionX = x;
        this.positionY = y;
        this.lifes = lifes;
        this.bonus = bonus;
        this.setBoundaries();
    }

    public void draw(GraphicsContext gc){
        gc.setFill( Color.ORANGE );
        gc.fillRect(this.positionX, this.positionY, this.width, this.height);
    }

    protected void setBoundaries(){
        this.left_boundary = this.positionX;
        this.right_boundary = this.positionX + this.width;
        this.upper_boundary = this.positionY;
        this.bottom_boundary = this.positionY + this.height;
    }

    public int getLifes() {
        return lifes;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }
}
