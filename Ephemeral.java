/**
 * A symbol that shrinks every time it is selected on a wheel,
 * until it becomes a dot.
 *
 * @author ForeroJ - SalinasS
 */
public class Ephemeral extends Symbol {
 
    /**
     * Create a new ephemeral symbol with the given color.
     * @param color the color of the symbol.
     */
    public Ephemeral(String color) {
        super(color);
    }
 
    /**
     * Decrease the diameter of the given circle by 10 pixels.
     * The diameter never goes below 2 pixels, which is the size of a dot.
     * @param circle the circle that represents the symbol on the wheel.
     */
    @Override
    public void symbolWeWant(Circle circle) {
        int tamanoActual = circle.getDiameter();
        int nuevoTamano = tamanoActual - 10;
        if (nuevoTamano < 2) {
            nuevoTamano = 2;
        }
        circle.changeSize(nuevoTamano);
    }
}