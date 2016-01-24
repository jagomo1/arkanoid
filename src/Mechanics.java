import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Mechanics {

    private int scoreValueSize = 100;
    private int playerLifes;
    public int getPlayerLifes() {
        return playerLifes;
    }
    public void setPlayerLifes(int playerLifes) {
        this.playerLifes = playerLifes;
    }

    private int playerScore;

    private Ball ball;
    private Paddle paddle;
    private Brick[][] bricks;

    public Mechanics(int playerLifes, Ball ball, Paddle paddle, Brick[][] bricks){
        this.playerLifes = playerLifes;
        this.playerScore = 0;
        this.ball = ball;
        this.paddle = paddle;
        this.bricks = bricks;
    }
    public void checkCollision(){
        //collision ball vs window boundaries
        if ( ball.getLeft_boundary() < 0 || ball.getRight_boundary() > Main.WINDOW_WIDTH){
            ball.setDx(-ball.getDx());
        }
        if ( ball.getUpper_boundary() < Main.SCORE_BAR_SIZE ){
            ball.setDy(-ball.getDy());
        }
        if (ball.getBottom_boundary() > Main.WINDOW_HEIGTH){
            ball.setStarted(0);
            this.playerLifes--;
        }

        //collision paddle vs window boundaries
        if (paddle.getLeft_boundary() <= 0) paddle.setPositionX(paddle.getWidth()/2);
        if (paddle.getRight_boundary() >= Main.WINDOW_WIDTH) paddle.setPositionX(Main.WINDOW_WIDTH - paddle.getWidth()/2);

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
                        this.playerScore += 1;
                        ball.setPositionY(bricks[i][j].getBottom_boundary() + ball.getRadius());
                        ball.setDy(-ball.getDy());
                    }
                    else if (ball.getRight_boundary() > bricks[i][j].getLeft_boundary()
                            && ball.getLeft_boundary() < bricks[i][j].getRight_boundary()
                            && ball.getBottom_boundary() >= bricks[i][j].getUpper_boundary()
                            && ball.getUpper_boundary() < bricks[i][j].getUpper_boundary()){
                        bricks[i][j].setLifes(bricks[i][j].getLifes()-1);
                        this.playerScore += 1;
                        ball.setPositionY(bricks[i][j].getUpper_boundary() - ball.getRadius());
                        ball.setDy(-ball.getDy());
                    }
                    else if (ball.getPositionY() > bricks[i][j].getUpper_boundary()
                            && ball.getPositionY() < bricks[i][j].getBottom_boundary()
                            && ball.getRight_boundary() >= bricks[i][j].getLeft_boundary()
                            && ball.getLeft_boundary() < bricks[i][j].getLeft_boundary()){
                        bricks[i][j].setLifes(bricks[i][j].getLifes()-1);
                        this.playerScore += 1;
                        ball.setPositionX(bricks[i][j].getLeft_boundary() - ball.getRadius());
                        ball.setDx(-ball.getDx());
                    }
                    else if (ball.getPositionY() > bricks[i][j].getUpper_boundary()
                            && ball.getPositionY() < bricks[i][j].getBottom_boundary()
                            && ball.getLeft_boundary() <= bricks[i][j].getRight_boundary()
                            && ball.getRight_boundary() > bricks[i][j].getRight_boundary()){
                        bricks[i][j].setLifes(bricks[i][j].getLifes()-1);
                        this.playerScore += 1;
                        ball.setPositionX(bricks[i][j].getRight_boundary() + ball.getRadius());
                        ball.setDx(-ball.getDx());
                    }
                }
            }
        }

    }

    public void drawBar(GraphicsContext gc){
        gc.setFill( Color.WHITE );
        gc.fillRect(0, Main.SCORE_BAR_SIZE, Main.WINDOW_WIDTH, 1);
        gc.setFont(Font.font(25));
        gc.fillText(Integer.toString(this.playerScore), Main.WINDOW_WIDTH - scoreValueSize, Main.SCORE_BAR_SIZE - 10);
        for (int i=0;i<this.playerLifes;i++){
            int scoreBallOffset = 10;
            gc.fillOval(Main.WINDOW_WIDTH - scoreValueSize - 2*scoreBallOffset - 2*ball.initRadius - i*(2*ball.initRadius + scoreBallOffset), Main.SCORE_BAR_SIZE - 10 - 2*ball.initRadius, 2*ball.initRadius, 2*ball.initRadius);
        }
    }
}