import java.util.List;

public class Rebel extends Wheel{

    public Rebel(List<String> sharedSymbols, List<String> sharedSymbolTypes) {
        super(sharedSymbols, sharedSymbolTypes);
        setFrameColor("red");
    }

    @Override
    public void lock(){
    }

    @Override
    public boolean isLocked(){
        return true;
    }

    @Override
    public boolean canDeleteWheel(){
        return false;
    }
}