/**
 * Ship class extends GameElement class and creates ship objects.
 * 
 * @author julianscott
 * @version 7 April 2023
 * 
 *
 */
public class Ship extends GameElement {

    public static final int SHIP_WIDTH = 10;
    public static final int SHIP_HEIGHT = 20;
    public static final double SHIP_TURN = 0.1;
    public static final double SHIP_MOVE = 0.1;
    public static final double FRICTION = 0.02;

    /**
     * Ship constructor implements the GameElement constructor.
     * 
     */
    public Ship() {
        super(new Pose(240, 240, (Math.PI / 2.0)), new Vector2D(Math.PI / 2.0, 0),
                Math.max(SHIP_WIDTH, SHIP_HEIGHT) / 2.0);

        // TODO Auto-generated constructor stub
    }

    @Override
    public void draw() {
        GameUtils.drawPoseAsTriangle(this.pose, SHIP_WIDTH, SHIP_HEIGHT);

    }

    /**
     * turnLeft method turns the ship to the left.
     */
    public void turnLeft() {
        double newHeading = this.pose.getHeading() + SHIP_TURN;

        Pose newLHeading = new Pose(this.pose.getX(), this.pose.getY(), newHeading);
        this.pose = newLHeading;
        // super.update();
    }

    /**
     * turnRight method turns the ship to the right.
     */
    public void turnRight() {
        double newHeading = this.pose.getHeading() - SHIP_TURN;

        Pose newRHeading = new Pose(this.pose.getX(), this.pose.getY(), newHeading);
        this.pose = newRHeading;

    }

    /**
     * thrust method moves the ship in a certain heading.
     */
    public void thrust() {
        // Vector2D newMag = new Vector2D(this.pose.getHeading(), SHIP_MOVE);
        this.velocity = velocity.add(new Vector2D(this.pose.getHeading(), SHIP_MOVE)); // this.velocity is a Vector2D

    }

    /**
     * update method updates the ship position and velocity.
     */
    public void update() {

        super.update();
        this.velocity = this.velocity.newMagnitude(Math.max(0, this.velocity.getMagnitude() - FRICTION));

    }

}
