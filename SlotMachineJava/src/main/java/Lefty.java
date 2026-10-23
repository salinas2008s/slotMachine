import java.util.List;

/**
 * A wheel that copies the color of the wheel on its left when it spins.
 * If there is no wheel on its left, it behaves like a normal wheel when a specific color
 * is set, and it does not change when it spins.
 *
 * @author ForeroJ - SalinasS
 */
public class Lefty extends Wheel {

    /**
     * Create a new lefty wheel with a green frame.
     * @param sharedSymbols the list of colors shared by all the wheels of the machine.
     * @param sharedSymbolTypes the list of symbol types shared by all the wheels of the machine.
     */
    public Lefty(List<String> sharedSymbols, List<String> sharedSymbolTypes) {
        super(sharedSymbols, sharedSymbolTypes);
        setFrameColor("green");
    }

    /**
     * Copy the current color of the wheel on the left.
     * Nothing happens if this wheel is locked or if there is no wheel on its left.
     */
    @Override
    public void changeColorSymbol() {
        if (!isLocked() && leftW != null) {
            changeSpecificColor(leftW.getActualColor());
        }
    }

    /**
     * Change the color of this wheel. If there is a wheel on the left and this wheel is not
     * locked, the given color is ignored and the color of the left wheel is copied instead.
     * @param color the new color, used only when there is no wheel on the left.
     */
    @Override
    public void changeSpecificColor(String color) {
        if (!isLocked() && leftW != null) {
            super.changeSpecificColor(leftW.getActualColor());
        } else {
            super.changeSpecificColor(color);
        }
    }
}