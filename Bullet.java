/**
 * Bullet class creates bullet objects.
 * 
 * @author julianscott
 * @version 8 April 2023
 */
public class Bullet extends GameElement {

    public static final double BULLET_RADIUS = 1.5;
    public static final int BULLET_SPEED = 20;
    public static final int BULLET_DURATION = 20;
    private int counter;

    /**
     * Bullet constructor implements the GameElement constructor and creates bullet objects.
     * 
     * @param pose The position of the bullet.
     */
    public Bullet(Pose pose) {
        super(pose, new Vector2D(pose.getHeading(), BULLET_SPEED), BULLET_RADIUS);
        this.counter = 0;
    }
    /*
     * public Bullet(Pose pose) { this.pose = pose; this. }
     */

    @Override
    public void draw() {

        /*
         * // new code for RAINBOWS!! int upperbound = 255; int randNum1 = GameDriver.GENERATOR.nextInt(upperbound); int
         * randNum2 = GameDriver.GENERATOR.nextInt(upperbound); int randNum3 = GameDriver.GENERATOR.nextInt(upperbound);
         * 
         * StdDraw.setPenColor(randNum1, randNum2, randNum3); // end of new code for rainbows
         */

        StdDraw.filledCircle(pose.getX(), pose.getY(), BULLET_RADIUS);

    }

    /**
     * update method updates all the bullets.
     */
    public void update() {
        super.update();
        if (counter >= BULLET_DURATION) {
            destroyed = true;
        }
        this.counter++;
    }

}
