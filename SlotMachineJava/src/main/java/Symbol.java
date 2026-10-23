/**
 * A symbol that can be shown by a wheel. This is the normal symbol: it only has a color
 * and does not modify the circle that represents it. Other types of symbols extend this class to add their own effect.
 *
 * @author ForeroJ - SalinasS
 */
public class Symbol {
    protected String color;

    /**
     * Create a new symbol with the given color.
     * @param color the color of the symbol.
     */
    public Symbol(String color) {
        this.color = color;
    }

    /**
     * Return the color of this symbol.
     * @return the color of the symbol.
     */
    public String getColor() {
        return color;
    }

    /**
     * Apply the effect of this symbol to the circle shown on the wheel.
     * A normal symbol has no effect; subclasses override this method to change the
     * size or the visibility of the circle.
     * @param circle the circle that represents the symbol on the wheel.
     */
    public void symbolWeWant(Circle circle) {
    }
}