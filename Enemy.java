
/**
 * Enemy class creates enemy objects.
 * 
 * @author julianscott
 * @version 13 April 2023
 *
 */
public abstract class Enemy extends GameElement {

    protected int points;

    /**
     * Enemy constructor creates the base for enemy objects.
     * 
     * @param speed The speed of the enemy.
     * @param radius The "hitbox" of the enemy.
     * @param points The points the enemy is worth.
     */
    public Enemy(double speed, double radius, int points) {
        super(new Pose(AsteroidsGame.randomXPosition(), AsteroidsGame.randomYPosition(), 0),
                new Vector2D(AsteroidsGame.randomHeading(), speed), radius);
        this.points = points;
    }

    public int getPoints() {
        return this.points;
    }

}
