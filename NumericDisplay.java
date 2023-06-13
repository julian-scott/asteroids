/**
 * NumericDisplay class displays the score and amount of lives left.
 * 
 * @author julianscott
 * @version 3 April 2023
 */
public class NumericDisplay implements Drawable {

    private String prefix;
    private int value;
    private Point location;

    /**
     * NumericDisplay constructor constructs the score board and lives left.
     * 
     * @param xPos The X position of the text.
     * @param yPos The Y position of the text.
     * @param prefix The prefix of the text.
     * @param value The value (lives, score) to print.
     */
    public NumericDisplay(int xPos, int yPos, String prefix, int value) {

        this.prefix = prefix;
        this.value = value;
        location = new Point(xPos, yPos);

    }

    public Point getLocation() {
        return location;
    }

    public int getValue() {
        return value;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public void draw() {
        StdDraw.textLeft(location.getX(), location.getY(), this.prefix + this.value);

    }
}
