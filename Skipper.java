import java.util.List;

/**
 * A wheel that advances four symbols each time it spins,
 * pausing briefly between each step.
 *
 * @author ForeroJ - SalinasS
 */
public class Skipper extends Wheel {

    /**
     * Create a new skipper wheel with a yellow frame.
     * @param sharedSymbols the list of colors shared by all the wheels of the machine.
     * @param sharedSymbolTypes the list of symbol types shared by all the wheels of the machine.
     */
    public Skipper(List<String> sharedSymbols, List<String> sharedSymbolTypes) {
        super(sharedSymbols, sharedSymbolTypes);
        setFrameColor("yellow");
    }

    /**
     * Advance four symbols, waiting 300 milliseconds after each step.
     * If the thread is interrupted during a pause, the interruption is preserved.
     */
    @Override
    public void changeColorSymbol() {
        for (int i = 0; i < 4; i++) {
            super.changeColorSymbol();
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}