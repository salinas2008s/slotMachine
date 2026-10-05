/**
 * A symbol that toggles between visible and invisible
 * every time it is selected on a wheel.
 *
 * @author ForeroJ - SalinasS
 */
public class Shy extends Symbol {

    /**
     * Create a new shy symbol with the given color.
     * @param color the color of the symbol.
     */
    public Shy(String color) {
        super(color);
    }

    /**
     * Toggle the visibility of the given circle: if it is visible it becomes invisible,
     * and if it is invisible it becomes visible.
     * @param circle the circle that represents the symbol on the wheel.
     */
    @Override
    public void symbolWeWant(Circle circle) {
        if (circle.isVisible()) {
            circle.makeInvisible();
        } else {
            circle.makeVisible();
        }
    }
}