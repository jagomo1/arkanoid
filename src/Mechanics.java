public class Mechanics {

    private Ball ball;
    private Paddle paddle;
    private Brick[][] bricks;

    public Mechanics(Ball ball){//Paddle paddle, Brick[][] bricks){
        this.ball = ball;
        //this.paddle = paddle;
        //this.bricks = bricks;
    }
    public void checkCollision(){
        if ( ball.getPositionX() - ball.getRadius() < 0 ||
                ball.getPositionX() + ball.getRadius() > Main.WINDOW_WIDTH){
            ball.setDx(-ball.getDx());
        }
        if ( ball.getPositionY() - ball.getRadius() < 0 ||
                ball.getPositionY() + ball.getRadius() > Main.WINDOW_HEIGTH){
            ball.setDy(-ball.getDy());
        }
    }
}