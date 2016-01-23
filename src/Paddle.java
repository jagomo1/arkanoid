import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Paddle extends Sprite {
    private int offset = 10;

    public Paddle(){
        this.width = 175;
        this.height = 25;
        this.positionX = (Main.WINDOW_WIDTH - this.width)/2;
        this.positionY = Main.WINDOW_HEIGTH - this.height - offset;
        this.setBoundaries();
        this.init_velocity = 10;
        this.dx = 0;
        this.dy = 0;
    }

    public void move() {
        this.positionX = this.positionX + this.dx;
        this.setBoundaries();
    }

    public void draw(GraphicsContext gc){
        gc.setFill( Color.BLUE );
        gc.fillRect(this.left_boundary, this.upper_boundary, this.width, this.height);
    }
}
