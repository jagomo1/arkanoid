public class Mechanics {

    private Ball ball;
    private Paddle paddle;
    private Brick[][] bricks;

    public Mechanics(Ball ball, Paddle paddle, Brick[][] bricks){
        this.ball = ball;
        this.paddle = paddle;
        this.bricks = bricks;
    }
    public void checkCollision(){
        //collision ball vs window boundaries
        if ( ball.getLeft_boundary() < 0 || ball.getRight_boundary() > Main.WINDOW_WIDTH){
            ball.setDx(-ball.getDx());
        }
        if ( ball.getUpper_boundary() < 0 ){//|| ball.getBottom_boundary() > Main.WINDOW_HEIGTH){
            ball.setDy(-ball.getDy());
        }

        //collision paddle vs window boundaries
        if (paddle.getLeft_boundary() < 0) paddle.setPositionX(paddle.getWidth()/2);
        if (paddle.getRight_boundary() > Main.WINDOW_WIDTH) paddle.setPositionX(Main.WINDOW_WIDTH - paddle.getWidth()/2);

        //collision ball vs paddle
        if ((ball.getRight_boundary() > paddle.getLeft_boundary())
                && (ball.getLeft_boundary() < paddle.getRight_boundary())
                && (ball.getBottom_boundary() >= paddle.getUpper_boundary())
                && (ball.getPositionY() < paddle.getUpper_boundary())){
            ball.setPositionY(paddle.getUpper_boundary() - ball.getRadius());
            ball.setDy(-ball.getDy());
        }
        else if ((ball.getPositionY() > paddle.getUpper_boundary())
                && (ball.getRight_boundary() >= paddle.getLeft_boundary())
                && (ball.getLeft_boundary() < paddle.getLeft_boundary())){
            ball.setPositionX(paddle.getLeft_boundary() - ball.getRadius());
            ball.setDx(-ball.getDx());
        }
        else if ((ball.getPositionY() > paddle.getUpper_boundary())
                && (ball.getLeft_boundary() <= paddle.getRight_boundary())
                && (ball.getRight_boundary() > paddle.getRight_boundary())){
            ball.setPositionX(paddle.getRight_boundary() + ball.getRadius());
            ball.setDx(-ball.getDx());
        }

        //collision ball vs bricks
        for (int i=0;i<Main.BRICKS_ROWS;i++){
            for (int j=0;j<Main.BRICKS_COLS;j++){
                if (bricks[i][j].getLifes() > 0){
                    if (ball.getRight_boundary() > bricks[i][j].getLeft_boundary()
                            && ball.getLeft_boundary() < bricks[i][j].getRight_boundary()
                            && ball.getUpper_boundary() <= bricks[i][j].getBottom_boundary()
                            && ball.getBottom_boundary() > bricks[i][j].getBottom_boundary()){
                        bricks[i][j].setLifes(bricks[i][j].getLifes()-1);
                        ball.setPositionY(bricks[i][j].getBottom_boundary() + ball.getRadius());
                        ball.setDy(-ball.getDy());
                    }
                    else if (ball.getRight_boundary() > bricks[i][j].getLeft_boundary()
                            && ball.getLeft_boundary() < bricks[i][j].getRight_boundary()
                            && ball.getBottom_boundary() >= bricks[i][j].getUpper_boundary()
                            && ball.getUpper_boundary() < bricks[i][j].getUpper_boundary()){
                        bricks[i][j].setLifes(bricks[i][j].getLifes()-1);
                        ball.setPositionY(bricks[i][j].getUpper_boundary() - ball.getRadius());
                        ball.setDy(-ball.getDy());
                    }
                    else if (ball.getPositionY() > bricks[i][j].getUpper_boundary()
                            && ball.getPositionY() < bricks[i][j].getBottom_boundary()
                            && ball.getRight_boundary() >= bricks[i][j].getLeft_boundary()
                            && ball.getLeft_boundary() < bricks[i][j].getLeft_boundary()){
                        bricks[i][j].setLifes(bricks[i][j].getLifes()-1);
                        ball.setPositionX(bricks[i][j].getLeft_boundary() - ball.getRadius());
                        ball.setDx(-ball.getDx());
                    }
                    else if (ball.getPositionY() > bricks[i][j].getUpper_boundary()
                            && ball.getPositionY() < bricks[i][j].getBottom_boundary()
                            && ball.getLeft_boundary() <= bricks[i][j].getRight_boundary()
                            && ball.getRight_boundary() > bricks[i][j].getRight_boundary()){
                        bricks[i][j].setLifes(bricks[i][j].getLifes()-1);
                        ball.setPositionX(bricks[i][j].getRight_boundary() + ball.getRadius());
                        ball.setDx(-ball.getDx());
                    }
                }
            }
        }

    }
}