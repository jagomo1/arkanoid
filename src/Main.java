import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import java.util.ArrayList;


public class Main extends Application {

    public static int WINDOW_WIDTH = 1280;
    public static int WINDOW_HEIGTH = 900;
    public static int WINDOW_OFFSET = 25;
    public static int BRICKS_COLS = 10;
    public static int BRICKS_ROWS = 10;
    public static int BRICKS_OFFSET_X = (WINDOW_WIDTH - 2*WINDOW_OFFSET - BRICKS_COLS*Brick.BRICK_WIDTH) / (BRICKS_COLS-1);
    public static int BRICKS_OFFSET_Y = (WINDOW_HEIGTH/2 - WINDOW_OFFSET- BRICKS_ROWS*Brick.BRICK_HEIGHT) / (BRICKS_ROWS-1);



    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Arkanoid");

        Group root = new Group();
        Scene theScene = new Scene( root, WINDOW_WIDTH, WINDOW_HEIGTH, Color.BLACK );
        primaryStage.setScene( theScene );
        primaryStage.setResizable(false);
        Canvas canvas = new Canvas( theScene.getWidth(), theScene.getHeight());
        root.getChildren().add( canvas );
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Key actions
        ArrayList<String> input = new ArrayList<>();
        theScene.setOnKeyPressed(event -> {
            String code = event.getCode().toString();
            if (!input.contains(code)) input.add(code);
        });
        theScene.setOnKeyReleased(event -> {
            String code = event.getCode().toString();
            if (input.contains(code)) input.remove(code);
        });

        //initializing objects
        Ball ball = new Ball();
        Paddle paddle = new Paddle();
        Brick[][] bricks = new Brick[BRICKS_ROWS][BRICKS_COLS];
        for (int i=0;i<BRICKS_ROWS;i++){
            for (int j = 0; j< BRICKS_COLS; j++){
                bricks[i][j] = new Brick(WINDOW_OFFSET + j*(Brick.BRICK_WIDTH+BRICKS_OFFSET_X), WINDOW_OFFSET + i*(Brick.BRICK_HEIGHT+BRICKS_OFFSET_Y), 1, 0);
            }
        }


        Mechanics mechanics = new Mechanics(ball, paddle, bricks);

        //time loop
        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount( Timeline.INDEFINITE );
        KeyFrame kf = new KeyFrame(
            Duration.seconds(0.017),                // 60 FPS
                event -> {
                    // key actions
                    if (input.contains("LEFT")) paddle.setDx(-paddle.getInit_velocity());
                    else if (input.contains("RIGHT")) paddle.setDx(paddle.getInit_velocity());
                    else paddle.setDx(0);

                    //moving objects
                    ball.move();
                    paddle.move();
                    mechanics.checkCollision();

                    //drawing objects
                    gc.clearRect(0, 0, canvas.getWidth(),canvas.getHeight());
                    ball.draw(gc);
                    paddle.draw(gc);
                    for (int i=0;i<BRICKS_ROWS;i++){
                        for (int j = 0; j< BRICKS_COLS; j++){
                            if (bricks[i][j].getLifes() > 0) {
                                bricks[i][j].draw(gc);
                            }
                        }
                    }
                }
            );

        gameLoop.getKeyFrames().add( kf );
        gameLoop.play();

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
