//// Import the necessary classes
//import javafx.application.Application;
//import javafx.application.Platform;
//import javafx.scene.Scene;
//import javafx.scene.layout.Pane;
//import javafx.scene.shape.Line;
//import javafx.scene.shape.Rectangle;
//import javafx.stage.Stage;
//
//// Define some constants
//public class StickHero extends Application {
//    public static final int WIDTH = 600; // The width of the window
//    public static final int HEIGHT = 400; // The height of the window
//    public static final int GROUND = 300; // The y-coordinate of the ground
//    public static final int GAP = 100; // The gap between the platforms
//    public static final int PLATFORM_WIDTH = 50; // The width of each platform
//    public static final int STICK_SPEED = 10; // The speed of the Stick growing and falling
//    public static final int HERO_SPEED = 5; // The speed of the hero moving
//
//    // Declare some variables
//    private Pane root; // The root node of the scene graph
//    private Rectangle hero; // The hero shape
//    private Rectangle platform1; // The first platform shape
//    private Rectangle platform2; // The second platform shape
//    private Line Stick; // The Stick shape
//    private boolean isGrowing; // Whether the Stick is growing or not
//    private boolean isFalling; // Whether the Stick is falling or not
//    private boolean isMoving; // Whether the hero is moving or not
//
//    @Override
//    public void start(Stage primaryStage) {
//        // Initialize the variables
//        root = new Pane();
//        hero = new Rectangle(10, 10, 10, 40);
//        platform1 = new Rectangle(0, GROUND, PLATFORM_WIDTH, HEIGHT - GROUND);
//        platform2 = new Rectangle(WIDTH - PLATFORM_WIDTH, GROUND, PLATFORM_WIDTH, HEIGHT - GROUND);
//        Stick = new Line(PLATFORM_WIDTH, GROUND, PLATFORM_WIDTH, GROUND);
//        isGrowing = false;
//        isFalling = false;
//        isMoving = false;
//
//        // Add the shapes to the root node
//        root.getChildren().addAll(hero, platform1, platform2, Stick);
//
//        // Create a scene and set it to the stage
//        Scene scene = new Scene(root, WIDTH, HEIGHT);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("Stick Hero");
//        primaryStage.show();
//
//        // Set the scene to handle key events
//        scene.setOnKeyPressed(e -> {
//            switch (e.getCode()) {
//                case SPACE: // If space is pressed, start or stop growing the Stick
//                    if (!isFalling && !isMoving) {
//                        isGrowing = !isGrowing;
//                    }
//                    break;
//                default:
//                    break;
//            }
//        });
//
//        // Create an animation timer to update the game logic
//        new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                update();
//            }
//        }.start();
//    }
//
//    // Update the game logic
//    public void update() {
//        if (isGrowing) { // If the Stick is growing, increase its length and move its end point up
//            Stick.setEndY(Stick.getEndY() - STICK_SPEED);
//        } else if (isFalling) { // If the Stick is falling, rotate it around its start point and check if it reaches the ground or the second platform
//            double angle = Math.toDegrees(Math.atan2(Stick.getStartY() - Stick.getEndY(), Stick.getEndX() - Stick.getStartX()));
//            angle += STICK_SPEED;
//            if (angle >= 90) { // If the angle reaches 90 degrees, stop falling and start moving the hero
//                angle = 90;
//                isFalling = false;
//                isMoving = true;
//            }
//            double length = Math.sqrt(Math.pow(Stick.getStartX() - Stick.getEndX(), 2) + Math.pow(Stick.getStartY() - Stick.getEndY(), 2));
//            double endX = Stick.getStartX() + length * Math.cos(Math.toRadians(angle));
//            double endY = Stick.getStartY() - length * Math.sin(Math.toRadians(angle));
//            Stick.setEndX(endX);
//            Stick.setEndY(endY);
//            if (endY >= GROUND) { // If the end point reaches the ground, stop falling and end the game
//                isFalling = false;
//                endGame();
//            } else if (endX >= platform2.getX() && endX <= platform2.getX() + PLATFORM_WIDTH) { // If the end point reaches the second platform, stop
//            }
//        }
//    }
//
//    // End the game
//    public void endGame() {
//        // Display a message to the user
//        System.out.println("Game over!");
//        // Stop the application
//        Platform.exit();
//    }
//
//    // Reset the game
//    public void resetGame() {
//        // Move the second platform to a random position
//        double x = Math.random() * (WIDTH - 2 * PLATFORM_WIDTH - GAP) + PLATFORM_WIDTH + GAP;
//        platform2.setX(x);
//        // Move the hero back to the first platform
//        hero.setX(10);
//        // Reset the Stick
//        Stick.setStartX(PLATFORM_WIDTH);
//        Stick.setStartY(GROUND);
//        Stick.setEndX(PLATFORM_WIDTH);
//        Stick.setEndY(GROUND);
//    }
//
//    // Launch the application
//    public static void Main(String[] args) {
//        launch(args);
//    }
//}