import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ball extends Sprite {

    private int radius;
    private int dx;
    private int dy;

    public Ball(){
        this.radius = 12;
        this.positionX = 100;
        this.positionY = 100;
        this.dx = 5;
        this.dy = 5;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getDx() { return dx; }

    public void setDx(int dx) {  this.dx = dx; }

    public int getDy() { return dy; }

    public void setDy(int dy) { this.dy = dy; }

    public void move() {
        this.positionX = this.positionX + this.dx;
        this.positionY = this.positionY + this.dy;
    }

    public void drawBall(GraphicsContext gc){
        gc.setFill( Color.RED );
        gc.fillOval(this.positionX - this.radius, this.positionY - this.radius, 2*this.radius, 2*this.radius);
    }

//    public void clearBall(GraphicsContext gc){
//        gc.setFill( Color.WHITE );
//        gc.fillOval(this.positionX - (this.radius+0.7), this.positionY - (this.radius+0.7), 2*(this.radius+0.7), 2*(this.radius+0.7));
//    }

}
