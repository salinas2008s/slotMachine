import javax.swing.JOptionPane;
import java.util.List;
import java.util.ArrayList;

/**
 *  A frame of the wheels and the symbols we are going to use in the class SlotMachine
 *
 * @author ForeroJ - SamuelS
 */
public class Wheel{
    private int pos;
    public List<String> symbols;
    protected boolean isVisible;
    private int xPos;
    private int yPos;
    private String actualColor;
    private final Circle visibleSymbol;
    private final Rectangle wheelFrame;
    protected boolean locked=false;
    protected Wheel leftW;
    protected boolean canDeleteW=true;
    public List<String> symbolTypes;

    /**
     * Create a new wheel determining the position, creating a new objet of the class Rectangle and another of Circle.
     */
    public Wheel(List<String> sharedSymbols, List<String> sharedSymbolTypes) {
        pos = 0;
        xPos = 0;
        yPos = 0;
        symbols = sharedSymbols;
        symbolTypes = sharedSymbolTypes;
        isVisible = false;
        actualColor = "black";

        wheelFrame = new Rectangle();
        wheelFrame.moveHorizontal(20);
        wheelFrame.moveVertical(50);

        visibleSymbol = new Circle();
        visibleSymbol.moveHorizontal(20);
        visibleSymbol.moveVertical(50);
        visibleSymbol.changeColor("black");
    }

    /**
        Make this wheel visible, with an specific size.
    */
    public void showWheels(){
        isVisible = true;
        wheelFrame.changeSize(65,65);
        wheelFrame.makeVisible();

        if (!symbols.isEmpty()) {
        visibleSymbol.makeVisible();
        }
    }

    /**
     * Make this symbol visible, with color put by the user.
     * @param color the new color. Valid colors are "red", "magenta", "yellow", "green", "black", "blue", "white",
     * "magenta" and "black".
    */
    public void showSymbol(String color) {
        actualColor = color;
        visibleSymbol.changeColor(color);

        wheelFrame.makeVisible();
        visibleSymbol.makeVisible();
    }

    /**
     * Move the wheelframe and the symbol to a set distance
     * @param distance the desired distance in pixels
    */
    public void moveHorizontalWheel(int distance){
        wheelFrame.moveHorizontal(distance);
        visibleSymbol.moveHorizontal(distance);
    }

    /**
        Make the wheelframe and the symbol invisible.
    */
    public void makeInvisible(){
        isVisible = false;
        wheelFrame.makeInvisible();
        visibleSymbol.makeInvisible();
    }

    /**
        Make just the simbol invisible.
    */
    public void hideSymbol() {
        visibleSymbol.makeInvisible();
    }

    /**
     * Change the symbol color randomly choosing ONLY from the available colors in 'symbols'.
     */
    public void changeColorSymbol() {
        if (!locked && !symbols.isEmpty()) {
            pos = pos + 1;

            if (pos >= symbols.size()) {
                pos = 0;
            }
            String colornew = symbols.get(pos);
            actualColor = colornew;
            visibleSymbol.changeColor(actualColor);
            if (isVisible) {
                visibleSymbol.makeVisible();
            }
            applyHerency(pos);
        }
    }

    public String[] getSymbols() {
    return symbols.toArray(new String[0]);
    }

    /**
     * Change the symbol color in a specific way.
     * @param color the new color. Valid colors are "red", "magenta", "yellow", "green", "black", "blue", "white",
     * "magenta" and "black".
    */
    public void changeSpecificColor(String color) {
        if (!locked && symbols.contains(color)) {
            actualColor = color;
            visibleSymbol.changeColor(color);
            if (isVisible) {
            visibleSymbol.makeVisible();
            }

            int posColor = -1;
            for (int i = 0; i < symbols.size(); i++) {
                if (symbols.get(i).equals(color)) {
                    posColor = i;
                    break;
                }
            }
            applyHerency(posColor);
        }
    }

    /**
    * Return the color this wheel is currently showing.
    * @return the current color of the wheel.
    */
    public String getActualColor() {
        return actualColor;
    }

    //Ciclo 2:
    /**
    * Exchange the current color of this wheel with the current color of another wheel.
    * @param wheel2 the other wheel to swap colors with.
    */
    public void swapSymbols(Wheel wheel2) {
        if (!locked) {
            String colorDeWheel1 = actualColor;
            String colorDeWheel2 = wheel2.actualColor;

            actualColor = colorDeWheel2;
            wheel2.actualColor = colorDeWheel1;

            this.changeSpecificColor(actualColor);
            wheel2.changeSpecificColor(wheel2.actualColor);

            if (isVisible) {
                visibleSymbol.makeVisible();
            }
            if (wheel2.isVisible) {
                wheel2.visibleSymbol.makeVisible();
            }
        }
    }

    /**
    * Lock this wheel so its color stays fixed and does not change on future spins, symbol swaps, or specific color changes.
    */
    public void lock(){
        locked = true;
    }

    /**
    * Unlock this wheel so its color can change again on future spins,symbol swaps, or specific color changes.
    */
    public void unlock(){
        locked = false;
    }

    /**
     * Indicates whether this wheel is currently locked.
     * @return true if the wheel is locked, false otherwise.
     */
    public boolean isLocked(){
        return locked;
    }

    //Ciclo 4:

    public void putLeftWheel(Wheel wheel){
        leftW =  wheel;
    }

    public boolean canDeleteWheel(){
        return canDeleteW;
    }

    protected void applyHerency(int position) {
        if (position < 0 || position >= symbolTypes.size()) {
            return;
        }
        String type = symbolTypes.get(position);
        String color = symbols.get(position);
        Symbol effect;
        if (type.equals("Ephemeral")) {
            effect = new Ephemeral(color);
        } else if (type.equals("Shy")) {
            effect = new Shy(color);
        } else {
            effect = new Symbol(color);
        }
        effect.symbolWeWant(visibleSymbol);
    }
    
    protected void setFrameColor(String color) {
        wheelFrame.changeColor(color);
    }
    
    /**
     * Indicates whether the symbol currently shown by this wheel is visible.
     * @return true if the symbol circle is visible, false otherwise.
     */
    public boolean isSymbolVisible() {
        return visibleSymbol.isVisible();
    }
    
    /**
     * Return the diameter of the symbol currently shown by this wheel.
     * @return the diameter of the symbol circle in pixels.
     */
    public int getSymbolDiameter() {
        return visibleSymbol.getDiameter();
    }
}