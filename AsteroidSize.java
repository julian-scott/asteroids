/**
 * AsteroidSize enumeration class sets asteroid sizes.
 * 
 * @author julianscott
 * @version 13 April 2023
 *
 */
public enum AsteroidSize {
    SMALL(10, 200), MEDIUM(20, 100), LARGE(30, 50);

    private int radius;
    private int points;

    /**
     * AsteroidSize constructor creates asteroids of different sizes.
     * 
     * @param radius The radius of the asteroid.
     * @param points The worth of the asteroid.
     */
    private AsteroidSize(int radius, int points) {
        this.radius = radius;
        this.points = points;
    }

    public int getRadius() {
        return this.radius;

    }

    public int getPoints() {
        return this.points;
    }

    /**
     * randomeSize method creates randomized size asteroids.
     * 
     * @return AsteroidSize Returns an asteroid of given size.
     */
    public static AsteroidSize randomSize() {
        AsteroidSize size;

        double next = GameDriver.GENERATOR.nextDouble();

        if (next < 0.25) {
            size = SMALL;
        } else if (next < 0.50) {
            size = MEDIUM;
        } else {
            size = LARGE;
        }

        return size;
    }

}
