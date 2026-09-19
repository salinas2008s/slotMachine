/**
 * This class try to win the SlotMachine game by itself, spinning the
 * wheels until all of them show the same symbol.
 * 
 * @author ForeroJ - SalinasS
 */
public class SlotMachineContest {
    private SlotMachine slot;
    private int[][] countable;
    private int distinctS;
    private boolean ok = false;

    
     /**
     * Try to solve the game: create a new machine and spin the wheels one by one until it wins (or almost). This method is used just for test the algorithm, so the machine stays invisible.
     * @param n the number of wheels of the machine.
     * @return an array with how many steps each wheel needed.
     */
    public int[][] solve(int n) throws InterruptedException {
        slot = new SlotMachine(n);
        if (!ok){
            slot.makeInvisible();
        } else{
            slot.makeVisible();
        }
        distinctS = slot.distinctSymbols();
        countable = new int[n][1];
        int actual = slot.distinctSymbols();
        int despues;

        for (int i = 1; i <= n; i++) {       
            int count = 0;         
            
            for (int j = 0; j < n; j++) {
                if (actual != 1) {
                    slot.spin(i, 1);
                    despues = slot.distinctSymbols();
                    
                    if (despues > actual) {
                        slot.spin(i, -1);

                    } else {
                        actual = despues;
                        count++;
                    }
                }
            }
            countable[i - 1][0] = count;       
        }
        return countable;
    }

    /**
     Simulate the game like a real player would see it: this time the machine is visible, so you can watch the wheels spinning until it wins the jackpot.
     * @param n the number of wheels of the machine.
     */
    public void simulate(int n) throws InterruptedException {
    ok=true;
    solve(n);
    ok=false;
    }   
}