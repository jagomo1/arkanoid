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

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Arkanoid");

        Group root = new Group();
        Scene theScene = new Scene( root, WINDOW_WIDTH, WINDOW_HEIGTH, Color.LIGHTGREY );
        primaryStage.setScene( theScene );
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
        Mechanics mechanics = new Mechanics(ball, paddle);

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
