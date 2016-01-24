import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ball extends Sprite {
    private int started;
    private Paddle paddle;
    private int radius;
    public int initRadius = 12;

    public Ball(Paddle paddle){
        this.paddle = paddle;
        this.started = 0;
        this.radius = initRadius;
        this.width = 2 * this.radius;
        this.height = 2 * this.radius;
        this.positionX = this.paddle.getPositionX();
        this.positionY = this.paddle.getUpper_boundary() - this.radius;
        this.setBoundaries();
        this.init_velocity = 5;
        this.dx = this.init_velocity;
        this.dy = -this.init_velocity;
        this.color = Color.WHITE;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void move() {
        if(this.started == 1){
            this.positionX = this.positionX + this.dx;
            this.positionY = this.positionY + this.dy;
        }
        else{
            this.positionX = this.paddle.getPositionX();
            this.positionY = this.paddle.getUpper_boundary() - this.radius;
        }
        this.setBoundaries();
    }

    public void draw(GraphicsContext gc){
        gc.setFill( this.color );
        gc.fillOval(this.left_boundary, this.upper_boundary, this.width, this.height);
    }

    public int getStarted() {
        return started;
    }

    public void setStarted(int started) {
        this.started = started;
        this.dx = this.init_velocity;
        this.dy = -this.init_velocity;
    }
//    public void clearBall(GraphicsContext gc){
//        gc.setFill( Color.WHITE );
//        gc.fillOval(this.positionX - (this.radius+0.7), this.positionY - (this.radius+0.7), 2*(this.radius+0.7), 2*(this.radius+0.7));
//    }

}
