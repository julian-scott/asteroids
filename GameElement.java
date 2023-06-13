/**
 * GameElement abstract class implementing the Updatable and Drawable interfaces.
 * 
 * @author julianscott
 * @version 3 April 2023
 */

public abstract class GameElement implements Updatable, Drawable {

    protected Pose pose;
    protected Vector2D velocity;
    protected double radius;
    protected boolean destroyed;

    /**
     * GameElement constructor contructs games and tracks everything about the game.
     * 
     * @param pose The X and Y position of the object.
     * @param velocity The velocity the object is going.
     * @param radius The radius of the object.
     */
    public GameElement(Pose pose, Vector2D velocity, double radius) {
        this.pose = pose;
        this.velocity = velocity;
        this.radius = radius;
        this.destroyed = false;

    }

    public Pose getPose() {
        return pose;
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public double getRadius() {
        return radius;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    /**
     * checkCollision method checks to see if there is a collision between objects.
     * 
     * @param other Another GameElement to check if there was a collision with.
     * @return boolean Returns true if collision, false if not.
     */
    public boolean checkCollision(GameElement other) {

        double diffX = this.pose.getX() - other.pose.getX();
        double diffY = this.pose.getY() - other.pose.getY();
        double distance = Math.sqrt(diffX * diffX + diffY * diffY);

        return distance < this.radius + other.radius;

    }

    /**
     * update method updates all the values and keeps track of the game.
     */
    public void update() {

        this.pose = pose.move(velocity);

        if (pose.getX() > GameDriver.SCREEN_WIDTH) {
            pose = pose.newX(0);
        } else if (pose.getX() < 0) {
            pose = pose.newX(GameDriver.SCREEN_WIDTH);
        }

        if (pose.getY() > GameDriver.SCREEN_HEIGHT) {
            pose = pose.newY(0);
        } else if (pose.getY() < 0) {
            pose = pose.newY(GameDriver.SCREEN_HEIGHT);
        }
    }

}
