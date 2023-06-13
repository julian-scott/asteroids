/**
 * Asteroid class creates asteroids.
 * 
 * @author julianscott
 * @version 13 April 2023
 */
public class Asteroid extends Enemy {

    public static final int ASTEROID_SPEED = 1;

    /**
     * Asteroid constructor creates asteroids.
     * 
     * @param size An AsteroidSize object.
     */
    public Asteroid(AsteroidSize size) {
        super(ASTEROID_SPEED, size.getRadius(), size.getPoints());
    }

    @Override
    public void draw() {
        StdDraw.circle(pose.getX(), pose.getY(), radius);

    }

}
