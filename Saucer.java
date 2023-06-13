/**
 * Saucer class creates enemy saucers.
 * 
 * @author julianscott
 * @version 13 April 2023
 */
public class Saucer extends Enemy {

    public static final int HALF_WIDTH = 10;
    public static final int HALF_HEIGHT = 5;
    public static final int SAUCER_SPEED = 2;
    public static final int SAUCER_POINTS = 400;
    public static final double SPAWN_PROB = 0.002;
    public static final double TURN_PROB = 0.05;

    /**
     * Saucer constructor creates saucers.
     */
    public Saucer() {
        super(SAUCER_SPEED, Math.max(HALF_WIDTH, HALF_HEIGHT), SAUCER_POINTS);

    }

    @Override
    public void update() {
        super.update();

        if (GameDriver.GENERATOR.nextDouble() < TURN_PROB) {
            velocity = velocity.newHeading(AsteroidsGame.randomHeading());
        }
        this.pose = pose.move(velocity);

        if (pose.getX() > GameDriver.SCREEN_WIDTH || pose.getX() < 0 || pose.getY() > GameDriver.SCREEN_HEIGHT
                || pose.getY() < 0) {
            destroyed = true;
            points = 0;
        }

    }

    @Override
    public void draw() {
        StdDraw.rectangle(pose.getX(), pose.getY(), HALF_WIDTH, HALF_HEIGHT);

    }

}
