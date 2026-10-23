import java.util.List;

/**
 * A wheel that rebels against the rules of the machine: it cannot be locked,
 * swapped or deleted.
 *
 * @author ForeroJ - SalinasS
 */
public class Rebel extends Wheel {

    /**
     * Create a new rebel wheel with a red frame.
     * @param sharedSymbols the list of colors shared by all the wheels of the machine.
     * @param sharedSymbolTypes the list of symbol types shared by all the wheels of the machine.
     */
    public Rebel(List<String> sharedSymbols, List<String> sharedSymbolTypes) {
        super(sharedSymbols, sharedSymbolTypes);
        setFrameColor("red");
    }

    /**
     * Do nothing: a rebel wheel refuses to be locked.
     */
    @Override
    public void lock() {
    }

    /**
     * Indicates that this wheel is protected against swaps.
     * @return always true, so the machine refuses to swap it.
     */
    @Override
    public boolean isLocked() {
        return true;
    }

    /**
     * Indicates that this wheel cannot be deleted.
     * @return always false.
     */
    @Override
    public boolean canDeleteWheel() {
        return false;
    }
}