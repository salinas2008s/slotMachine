import java.util.List;

public class Lefty extends Wheel {

    public Lefty(List<String> sharedSymbols, List<String> sharedSymbolTypes) {
        super(sharedSymbols, sharedSymbolTypes);
        setFrameColor("green");
    }

    @Override
    public void changeColorSymbol() {
        if (!isLocked() && leftW != null) {
            changeSpecificColor(leftW.getActualColor());
        }
    }

    @Override
    public void changeSpecificColor(String color) {
        if (!isLocked() && leftW != null) {
            super.changeSpecificColor(leftW.getActualColor());
        } else {
            super.changeSpecificColor(color);
        }
    }
}