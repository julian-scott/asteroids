/**
 * Star class draws Stars on the background.
 * 
 * @author julianscott
 * @version 3 April 2023
 *
 */
public class Star implements Drawable {

    public static final int STAR_RADIUS = 1;

    private Point location;

    /**
     * Star constructor creates stars that go on the background.
     * 
     * @param x The X position of the star.
     * @param y The Y position of the star.
     */
    public Star(double x, double y) {
        this.location = new Point(x, y);
    }

    public Point getLocation() {
        return this.location;
    }

    @Override
    public void draw() {
        double xPos = this.location.getX();
        double yPos = this.location.getY();
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.filledCircle(xPos, yPos, STAR_RADIUS);

    }
}
