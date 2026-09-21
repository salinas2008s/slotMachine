import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for validating the behavior of SlotMachineContest.
 * @author Samuel Salinas - Juan David Forero
 */
public class SlotMachineContestCTest {

    private SlotMachineContest slot;
    
    /**
    * Sets up the test fixture.
    * Called before every test case method.
    */
    @Before
    public void setUp() {
        slot = new SlotMachineContest();
    }
    
    //Grupo: GomezB-CarreroC   
    /**
     * Verifies that solve returns
     * the expected number of movements.
     */
    @Test
    public void accordingCcGbShouldtestSolveNumberOfMovements() throws InterruptedException
    {
        SlotMachineContest contest = new SlotMachineContest();

        int[][] solution = contest.solve(5);

        assertEquals(5, solution.length);
    }

    /**
     * Verifica que SlotMachine(n) crea exactamente n ruedas, cada una
     * con n símbolos montados
     */
    @Test
    public void accordingCjMcShouldCreateEqualWheelsAndSymbolsPerWheel() {
        SlotMachine sm = new SlotMachine(5);
        assertEquals(5, sm.configuration().length);
        assertEquals(25, sm.symbols().length);
    }
    
    
    /**
     * Verifica que solve(n) nunca propone más acciones que ruedas existen
     * en la máquina, y que cada acción referencia una rueda dentro de un
     * rango válido (1 a n).
     */
    
    @Test
    public void accordingCjMcShouldProposeAtMostOneActionPerValidWheel() throws InterruptedException {
        int n = 4;
        int[][] actions = slot.solve(n);
    
        assertTrue(actions.length <= n);
        for (int[] action : actions) {
            int wheel = action[0];
            assertTrue(wheel >= 1 && wheel <= n);
        }
    }
    
     //Test #1
    @Test
    public void accordingCaPpshouldReturnValidMovesStructure() throws InterruptedException {
        SlotMachineContest contest = new SlotMachineContest();
        int n = 3;
        int[][] moves = contest.solve(n);

        assertNotNull(moves);
        for (int[] move : moves) {
            assertEquals(2, move.length);
        }
    }

    //Test #2
    @Test
    public void accordingCaPpshouldRunSimulationWithoutErrors() throws InterruptedException {
        int n = 3;
        SlotMachineContest contest = new SlotMachineContest();
        contest.simulate(n);
    }
}
