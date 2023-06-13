import java.util.ArrayList;
import java.util.Iterator;

/**
 * Main class for Asteroids.
 * 
 * @author CS159 Instructors
 * @version s23
 */
public class AsteroidsGame implements Playable {

    public static final int LIVES = 3;

    private ArrayList<Drawable> drawElements;
    private ArrayList<Updatable> updateElements;
    private ArrayList<GameElement> shipAndBullets;
    private ArrayList<Enemy> enemies;

    private NumericDisplay score;
    private NumericDisplay lives;
    private Ship ship;

    /**
     * Constructs all game elements.
     */
    public AsteroidsGame() {
        StdDraw.setTitle("Generic Space Rock Shooter");

        drawElements = new ArrayList<>();
        updateElements = new ArrayList<>();
        shipAndBullets = new ArrayList<>();
        enemies = new ArrayList<>();

        // add background elements
        final int TOP = GameDriver.SCREEN_HEIGHT;
        score = new NumericDisplay(10, TOP - 20, "Score: ", 0);
        lives = new NumericDisplay(10, TOP - 40, "Lives: ", LIVES);
        drawElements.add(score);
        drawElements.add(lives);

        newShip();
    }

    /**
     * Starts a new game with stars and enemies.
     */
    public void startGame() {
        newStars();
        newEnemies();

    }

    /**
     * Creates and adds 100 stars with random locations.
     */
    private void newStars() {
        Star newStar;
        for (int i = 0; i < 100; i++) {
            newStar = new Star(AsteroidsGame.randomXPosition(), AsteroidsGame.randomYPosition());
            drawElements.add(newStar);

        }
    }

    /**
     * addEnemy method adds the given enemies to all appropriate ArrayLists.
     * 
     * @param enemy The given enemy.
     */
    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
        drawElements.add(enemy);
        updateElements.add(enemy);
    }

    /**
     * Creates 10 new asteroids.
     */
    private void newEnemies() {
        for (int i = 0; i < 10; i++) {
            AsteroidSize size = AsteroidSize.randomSize();
            Asteroid asteroid = new Asteroid(size);
            addEnemy(asteroid);
        }

    }

    /**
     * Creates a singular ship.
     */
    private void newShip() {
        ship = new Ship();
        drawElements.add(ship);
        updateElements.add(ship);
        shipAndBullets.add(ship);

    }

    /**
     * Handle keyboard input from the game and move the ship and shoot bullets if the corresponding keys are pressed.
     */
    private void handleKeyboardInput() {

        if (GameDriver.spacePressed()) {
            Bullet bullet = new Bullet(ship.getPose());
            shipAndBullets.add(bullet);
            drawElements.add(bullet);
            updateElements.add(bullet);

        }

        if (GameDriver.leftPressed()) {
            ship.turnLeft();
        }

        if (GameDriver.rightPressed()) {
            ship.turnRight();
        }

        if (GameDriver.upPressed()) {
            ship.thrust();
        }
    }

    @Override
    public void update() {

        // freeze simulation if game is over
        if (lives.getValue() <= 0) {
            return;

        }

        // update everything (including newest bullet)
        handleKeyboardInput();
        for (Updatable item : updateElements) {
            item.update();
        }

        handleCollisions();
        removeDestroyedBullets();
        removeDestroyedEnemies();

        // creates a new ship
        if (ship.isDestroyed()) {
            lives.setValue(lives.getValue() - 1);
            if (lives.getValue() > 0) {
                newShip();
            }
        }

        // adds random saucers
        if (GameDriver.GENERATOR.nextDouble() < Saucer.SPAWN_PROB) {
            Saucer saucer = new Saucer();
            drawElements.add(saucer);
            updateElements.add(saucer);
            enemies.add(saucer);
        }

        // create new asteroids
        if (enemies.isEmpty()) {
            newEnemies();
        }
    }

    @Override
    public void draw() {
        StdDraw.setPenColor(StdDraw.WHITE);
        for (Drawable cur : drawElements) {
            cur.draw();

        }

    }

    /**
     * Handles in game collisions, set destroyed to true.
     */
    private void handleCollisions() {
        for (GameElement cur : shipAndBullets) {
            for (Enemy enemy : enemies) {
                if (cur.checkCollision(enemy)) {
                    cur.setDestroyed(true);
                    enemy.setDestroyed(true);
                }
            }
        }
    }

    /**
     * Remove destroyed ship/bullets.
     */
    private void removeDestroyedBullets() {
        Iterator<GameElement> it = shipAndBullets.iterator();
        while (it.hasNext()) {
            GameElement sorb = it.next();
            if (sorb.isDestroyed()) {
                it.remove();
                drawElements.remove(sorb);
                updateElements.remove(sorb);
            }

        }
    }

    /**
     * Removes destroyed enemies.
     */
    private void removeDestroyedEnemies() {
        Iterator<Enemy> it = enemies.iterator();
        while (it.hasNext()) {
            Enemy enemy = it.next();
            if (enemy.isDestroyed()) {
                it.remove();
                drawElements.remove(enemy);
                updateElements.remove(enemy);
                score.setValue(enemy.getPoints() + score.getValue());
            }
        }
    }

    /**
     * Generates a random X position on the screen.
     * 
     * @return the x position
     */
    protected static double randomXPosition() {
        return GameDriver.GENERATOR.nextDouble() * GameDriver.SCREEN_WIDTH;
    }

    /**
     * Generates a random Y position on the screen.
     * 
     * @return the y position
     */
    protected static double randomYPosition() {
        return GameDriver.GENERATOR.nextDouble() * GameDriver.SCREEN_HEIGHT;
    }

    /**
     * Generates a random heading from the interval [0, 2*PI).
     * 
     * @return the heading
     */
    protected static double randomHeading() {
        return GameDriver.GENERATOR.nextDouble() * 2 * Math.PI;
    }

}
