import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.util.Arrays;
 
/**
 * A slot machine able to game and realise a lot of m+ethod.
 * 
 * @author ForeroJ - SalinasS 
 */
public class SlotMachine
{
    private boolean isVisible;
    private boolean ok;
    private int xPos;
    private int yPos;
    private List<Wheel> wheels;
    private Fondo rectMachine;
    private boolean enJuego;
    private int count;
    private List<String> commonSymbols;
    private List<String> symbolTypes = new ArrayList<>();
    
    /**
     * Create a new slot machine determining the position, starting with 3 wheels and with a black color.
     */
    public SlotMachine(){
        isVisible = false;
        ok = true;
        xPos = 160;
        yPos = 30;
        wheels = new ArrayList<>(0);
        rectMachine = new Fondo();
        enJuego=true;
        count = 0;
        commonSymbols = new ArrayList<>();
        commonSymbols.add("black");
        symbolTypes.add("Normal");
        
        rectMachine.changeSize(xPos,yPos);
        rectMachine.changeColor("black");
    }
    

    public SlotMachine(int n){
        isVisible = false;
        ok = true;
        xPos = 160;
        yPos = 30;
        wheels = new ArrayList<>();
        rectMachine = new Fondo();
        enJuego = true;
        count = 0;
        commonSymbols = new ArrayList<>();
    
        String[] validColors = {"red", "magenta", "yellow", "green", "black", "blue", "white"};
    
        for (int i = 0; i < n; i++) {
        int randomSymbols = (int) (Math.random() * validColors.length);
        commonSymbols.add(validColors[randomSymbols]);
        symbolTypes.add("Normal");
        }
    
        rectMachine.changeSize(xPos, yPos);
        rectMachine.changeColor("black");

        for (int i = 0; i < n; i++) {
            Wheel newWheel = new Wheel(commonSymbols, symbolTypes);
            newWheel.moveHorizontalWheel(i * 80);
            wheels.add(newWheel);
            yPos += 80;
        }
        rectMachine.changeSize(xPos, yPos);
    
        for (Wheel w : wheels) {
            if (!commonSymbols.isEmpty()) {
                int randomIndex = (int) (Math.random() * commonSymbols.size());
                String randomColor = commonSymbols.get(randomIndex);
                w.changeSpecificColor(randomColor);
                ok = true;
            }
        }
        updateLeftWheel();
    }
    /**
        Create another wheel at the last of list.
        @param pos is the position on the array of the wheel we want to adition.
    */
 
    public void addWheel(int pos) {
        if (wheels.size() < 18) {
            Wheel newWheel = new Wheel(commonSymbols, symbolTypes);
            int positionWheel;
            if (pos >= 1 && pos <= wheels.size() + 1) {
                positionWheel = pos - 1;
                
                wheels.add(positionWheel, newWheel);
                newWheel.moveHorizontalWheel(positionWheel * 80);
                
                for (int i = positionWheel + 1; i < wheels.size(); i++) {
                    wheels.get(i).moveHorizontalWheel(80);
                }
                
                if (isVisible) {
                    newWheel.showWheels();
                }
                makeBigRectMachine();
                updateLeftWheel();
                ok = true;
            } else {
                ok = false;
            }
        } else {
            ok = false;
        }
    }
    
    /**
     * Delete an specific wheel.
     * @param pos is the position on the array of the wheel we want to eliminate.
     */
    public void delWheel(int pos) {
        if(pos >= 1 && wheels.get(pos-1).canDeleteWheel()){
                wheels.get(pos - 1).makeInvisible();
                wheels.remove(pos - 1);
            
                for (int i = pos - 1; i < wheels.size(); i++) {
                    wheels.get(i).moveHorizontalWheel(-80); 
                }   
                ok = true;
                updateLeftWheel();
                makeSmallRectMachine();
            }else{
                ok = false;
            }
    }   
    
    /**
     * Add and specific symbol giving the color.
     * @param pos the position on the shared symbol list where the color will be inserted.
     * @param color the color of the new symbol
     */
    public void addSymbol(int pos, String color) {
        if (Arrays.asList("red", "magenta", "yellow", "green", "black", "blue", "white").contains(color)) {
            if (pos >= 1 && pos <= commonSymbols.size() + 1) {
                commonSymbols.add(pos - 1, color);
                ok = true;
            } else {
                ok = false;
            }
        } else {
            if (isVisible) {
                JOptionPane.showMessageDialog(null, "ONLY THESE COLORS TO THE SYMBOLS: red, black, green, magenta, yellow, blue and white");
            }
            ok = false;
        }   
    }
    
    /**
     * Delete all the symbol with an specific color.
     * @param color an determinate color that going to be eliminate.
     */
    public void delSymbol(String symbol) {
        while (commonSymbols.remove(symbol)) {
        }
        for (Wheel w : wheels) {
            if (symbol.equals(w.getActualColor())) {
                if (!commonSymbols.isEmpty()) {
                    w.changeSpecificColor(commonSymbols.get(0));
                } else {
                    w.hideSymbol();
                }
            }
        }
        ok = true;
    }
    /**
     * Change the symbol color in a specific position.
     * @param wheel the position of the wheel
     * @param symbol the color of the new symbol
     */
    public void placeSymbol(int wheel, String symbol) {
        if (Arrays.asList("red", "magenta", "yellow", "green", "black", "blue", "white").contains(symbol)) {
            if (wheel > 0 && wheel <= wheels.size()) {
                wheels.get(wheel - 1).changeSpecificColor(symbol);
                ok = true;
            } else {
                ok = false;
            }
        } else {
            ok = false;
        }
 
    }
    
    /**
    * Spin all the wheels of the machine, choosing a random color for each one.
    */
    public void spin(){
        for (Wheel w : wheels) {
            if (!commonSymbols.isEmpty()) {
                int randomIndex = (int) (Math.random() * commonSymbols.size());
                String randomColor = commonSymbols.get(randomIndex);
                w.changeSpecificColor(randomColor);
                ok = true;
                rectMachine.pullLever();
            }
        }
        
    }
 
    /**
     * Spin an specific wheels of the machine. The color is aleatory.
     * @param wheel the position of the wheel we want to spin.
     */
    public void spin(int wheel) {
        if (wheel > 0 && wheel <= wheels.size()) {
            wheels.get(wheel - 1).changeColorSymbol();
            ok=true;
            rectMachine.pullLever();
        } else{
            ok=false;
        }
 
    }
    
    /**
     * Return a list with all the actual colors of the slot machine.
     */
    public String[] symbols() {
        if (!wheels.isEmpty()) {
            return wheels.get(0).getSymbols();
        }
        return commonSymbols.toArray(new String[0]);
    }   
    
    /**
     * Give an integer of  the number of  distinct colors.
     */
    public int distinctSymbols() {
        String[] lista = configuration();
        List<String> unicos = new ArrayList<>();
        
        for (String symbol : lista) {
            if (!unicos.contains(symbol)) {
                unicos.add(symbol);
                ok = true;
            }
        }
        
        
        return unicos.size();
    }
    /**
     * Return an array with the current color shown by each wheel of the machine, in the same order as the wheels are arranged.
     */
    public String[] configuration() {
            String[] config = new String[wheels.size()];
            
            for (int i = 0; i < wheels.size(); i++) {
                config[i] = wheels.get(i).getActualColor();
            }
            
            return config;
    }    
    /**
    * Return true if the user win the game making a jackpot.
    */
    public boolean isJackpot() {
            if (distinctSymbols() == 1) {
            rectMachine.changeColor("green");
            return true;
        } else {
            return false;
        }
    }  
    /**
     * Makethe slot machine visible.
     */
    public void makeVisible(){
        JOptionPane.showMessageDialog(null, "ONLY THESE COLORS TO THE SIMBOLS: red, magenta, yellow, green, black, blue, white");
        isVisible = true;
        if (!wheels.isEmpty()) {
            rectMachine.makeVisible();
        }
        for (Wheel w : wheels) {
            w.showWheels(); 
        }
        ok = true;
    }
    /**
     * Makethe slot machine invisible.
     */
    public void makeInvisible(){
        isVisible = false;
        rectMachine.makeInvisible();
        for(Wheel w:wheels){
            w.makeInvisible();
        }
    }
    /**
     * Closeinmediately the game. 
     */
    public void exit() {
        makeInvisible();
        for (Wheel w : wheels) {
            w.makeInvisible();
        }
        enJuego = false;
        ok=true;
        System.exit(0); //We searched in the AI about one function that close immdiately the canvas, and show this line.
    }
    /**
     * Indicates whether the last operation was successfully performed
     */
    public boolean ok() {
        return ok;
    }
    /**
     * Make the frame of the slot machine like an big rectangle, if the user add another wheel the machine frame is bigger.
     */
    private void makeBigRectMachine(){
        xPos += 0;
        yPos += 80;
        rectMachine.changeSize(xPos, yPos);
        if (isVisible) {
            rectMachine.makeVisible();
            for (Wheel w : wheels) {
                w.showWheels(); 
            }
        }
    }
    /**
     * Make the frame of the slot machine like an small rectangle, if the user delete another wheel the machine frame is smaller.
     */
    private void makeSmallRectMachine(){
        xPos+=0;
        yPos-=80;
        rectMachine.changeSize(xPos,yPos);
        if (isVisible) {
            rectMachine.makeVisible();
            for (Wheel w : wheels) {
                w.showWheels(); 
            }
        }    
    }
    
    
    // CICLO 2:
    /**
    * Exchange the current colors between two wheels of the machine.
    * @param wheel1 the position of the first wheel to swap.
    * @param wheel2 the position of the second wheel to swap.
    */
    public void swap(int wheel1, int wheel2) {
    if (wheel1 > 0 && wheel1 <= wheels.size() && wheel2 > 0 && wheel2 <= wheels.size()) {
        Wheel variable1 = wheels.get(wheel1 - 1);
        Wheel variable2 = wheels.get(wheel2 - 1);
        
        if (variable1.isLocked() || variable2.isLocked()) {
            ok = false;
        } else {
            variable1.swapSymbols(variable2);
            ok = true;
        }
    } else {
        ok = false;
    }
    }
    /**
    * Lock a specific wheel so it stops changing color on future spins.
    * @param pos the position of the wheel to lock.
    */
    public void lock(int pos) {
        if (pos > 0 && pos <= wheels.size()) {
            wheels.get(pos - 1).lock();
            ok = true;
        } else {
            ok = false;
        }
    }
    /**
    *Unlock a specific wheel so it can change color again on future spins.
    *@param pos the position of the wheel to unlock.
    */
    public void unlock(int pos) {
        if (pos > 0 && pos <= wheels.size()) {
            wheels.get(pos - 1).unlock();
            ok = true;
        } else {
            ok = false;
        }
    }
    /**
     * Spin a specific wheel a given number of steps, pausing briefly between
     * @param wheel the position of the wheel to spin.
     * @param step the number of times the wheel's color should change.
     * @throws InterruptedException if the pause between steps is interrupted.
     */
    public void spin(int wheel, int step) throws InterruptedException {
        if (wheel > 0 && wheel <= wheels.size()) {
            for (int i = 0; i < step; i++) {
                wheels.get(wheel - 1).changeColorSymbol();
                if (isVisible) {
                    Thread.sleep(400);
                }
                rectMachine.pullLever();
            } 
            isJackpot();
        }else{
            ok=false;
        }
    }
    /**
     * Spin every wheel of the machine, setting each one to the color given at the matching position in setSymbols. 
     * @param setSymbols an array with one color per wheel, in wheel order.
     */
    public void spin(String[] setSymbols) {
        for(int i=0; i<wheels.size(); i++){
            if (Arrays.asList("red", "magenta", "yellow", "green", "black", "blue", "white").contains(setSymbols[i])) {
                wheels.get(i).changeSpecificColor(setSymbols[i]);
                rectMachine.pullLever();
            } else {
            ok = false;
            } 
        }
    }
    
    
    //Ciclo 4: 
    
    public void updateLeftWheel(){
        Wheel leftw;
        for (int i=0; i<wheels.size(); i++){
            if(i>0){
                leftw = wheels.get(i-1);
            } else{
                leftw = null;
            }
            wheels.get(i).putLeftWheel(leftw);
        }
    }
    
    public void addWheel(String type, int pos) {
        if (type.equals("lefty") || type.equals("rebel") || type.equals("normal") || type.equals("skipper")) {
    
            if (wheels.size() < 18) {
                Wheel newWheel;
                if (type.equals("lefty")) {
                    newWheel = new Lefty(commonSymbols, symbolTypes);
                } else if (type.equals("rebel")) {
                    newWheel = new Rebel(commonSymbols, symbolTypes);
                } else if (type.equals("skipper")) {
                    newWheel = new Skipper(commonSymbols, symbolTypes);
                } else {
                    newWheel = new Wheel(commonSymbols, symbolTypes);
                }
    
                int positionWheel;
                if (pos >= 1 && pos <= wheels.size() + 1) {
                    positionWheel = pos - 1;
    
                    wheels.add(positionWheel, newWheel);
                    newWheel.moveHorizontalWheel(positionWheel * 80);
    
                    for (int i = positionWheel + 1; i < wheels.size(); i++) {
                        wheels.get(i).moveHorizontalWheel(80);
                    }
    
                    if (isVisible) {
                        newWheel.showWheels();
                    }
                    makeBigRectMachine();
                    updateLeftWheel(); 
                    newWheel.changeColorSymbol();
                    ok = true;
                } else {
                    ok = false;
                }
            } else {
                ok = false;
            }
    
        } else {
            ok = false; 
        }
    }
    
    public void addSymbol(String type, int pos, String color) {
        if (type.equals("normal") || type.equals("ephemeral") || type.equals("shy")) {
            if (Arrays.asList("red", "magenta", "yellow", "green", "black", "blue", "white").contains(color)) {
                if (pos >= 1 && pos <= commonSymbols.size() + 1) {
                    commonSymbols.add(pos - 1, color);
                    symbolTypes.add(pos - 1, type);
                    ok = true;
                } else {
                    ok = false;
                }
            } else {
                if (isVisible) {
                    JOptionPane.showMessageDialog(null, "ONLY THESE COLORS TO THE SYMBOLS: red, black, green, magenta, yellow, blue and white");
                }
                ok = false;
            }
        } else {
            ok = false;
        }
    }
    /**
     * Indicates whether the symbol shown by a specific wheel is visible.
     * @param wheel the position of the wheel to check.
     * @return true if the symbol is visible, false if it is hidden or the wheel does not exist.
     */
    public boolean isSymbolVisible(int wheel) {
        if (wheel > 0 && wheel <= wheels.size()) {
            return wheels.get(wheel - 1).isSymbolVisible();
        }
        return false;
    }

    /**
     * Return the diameter of the symbol shown by a specific wheel.
     * @param wheel the position of the wheel to check.
     * @return the diameter in pixels, or -1 if the wheel does not exist.
     */
    public int symbolSize(int wheel) {
        if (wheel > 0 && wheel <= wheels.size()) {
            return wheels.get(wheel - 1).getSymbolDiameter();
        }
        return -1;
    }
}