import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ball extends Sprite {

    private int radius;

    public Ball(){
        this.radius = 12;
        this.width = 2 * this.radius;
        this.height = 2 * this.radius;
        this.positionX = 100;
        this.positionY = 100;
        setBoundaries();
        this.init_velocity = 5;
        this.dx = this.init_velocity;
        this.dy = this.init_velocity;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void move() {
        this.positionX = this.positionX + this.dx;
        this.positionY = this.positionY + this.dy;
        setBoundaries();
    }

    public void draw(GraphicsContext gc){
        gc.setFill( Color.RED );
        gc.fillOval(this.left_boundary, this.upper_boundary, this.width, this.height);
    }

//    public void clearBall(GraphicsContext gc){
//        gc.setFill( Color.WHITE );
//        gc.fillOval(this.positionX - (this.radius+0.7), this.positionY - (this.radius+0.7), 2*(this.radius+0.7), 2*(this.radius+0.7));
//    }

}
