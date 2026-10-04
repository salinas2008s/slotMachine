import java.util.List;

public class Skipper extends Wheel {

    public Skipper(List<String> sharedSymbols, List<String> sharedSymbolTypes) {
        super(sharedSymbols, sharedSymbolTypes);
        setFrameColor("yellow");
    }

    
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