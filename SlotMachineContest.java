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
        countable = new int[n][2];  //Here we create a matriz with n columns and 2 rows (one for the actual wheel and another for the number of steps)
        int before = slot.distinctSymbols();
        int after;

        for (int i = 1; i <= n; i++) { // Go through each wheel one by one, from 1 to n
            int count = 0;         
            
            for (int j = 0; j < n; j++) { //Try up to n spins for this wheel
                if (before != 1) {
                    slot.spin(i, 1);
                    after = slot.distinctSymbols();
                    
                    if (after > before) {
                        //if the color got worst, so we try to go back
                        slot.spin(i, -1);

                    } else {
                        // the color get better, we keep it
                        before = after;
                        count++;
                    }
                }
            }
            countable[i - 1][0] = i;
            countable[i - 1][1] = count;       
        }
        distinctS = slot.distinctSymbols();
        return countable;
    }

    /**
     * Simulate the game like a real player would see it: this time themachine is visible, so you can watch the wheels spinning until it wins the jackpot.
     * @param n the number of wheels of the machine.
     */
    public void simulate(int n) throws InterruptedException {
    ok=true;
    solve(n);
    ok=false;
    }   
}