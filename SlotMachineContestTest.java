import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for validating the behavior of SlotMachineContest.
 * The machines created by solve have random colors, so the tests check
 * properties that must hold for any random result.
 * @author Samuel Salinas - Juan David Forero
 */
public class SlotMachineContestTest {

    private SlotMachineContest slot;

    /**
    * Sets up the test fixture.
    * Called before every test case method.
    */
    @Before
    public void setUp() {
        slot = new SlotMachineContest();
    }

    /**
     * Tests that solve returns one row of steps per wheel, and that every row
     * holds a single value: the steps of that wheel.
     */
    @Test
    public void shouldReturnOneRowPerWheel() throws InterruptedException {
        int n = 3;
        int[][] result = slot.solve(n);

        assertNotNull(result);
        assertEquals(n, result.length);
        for (int[] row : result) {
            assertEquals(1, row.length);
        }
    }

    /**
    * Tests that the step counter of every wheel stays correct, no less than 0 and no more than
    * the number of wheels, and that the total of steps never exceeds n * n.
    */
    @Test
    public void shouldStepBeCorrect() throws InterruptedException {
        int n = 4;
        int[][] result = slot.solve(n);

        int total = 0;
        for (int[] pasos : result) {
            assertTrue(pasos[0] >= 0);
            assertTrue(pasos[0] <= n);
            total += pasos[0];
        }
        assertTrue(total <= n * n);
    }

    /**
    * Tests that solve keeps returning the right amount of rows for several different machine sizes,
    * not just one lucky case, and that the steps of every wheel stay valid in all of them.
    */
    @Test
    public void shouldWorkforEveryWhee() throws InterruptedException {
        int[] sizes = {6, 3, 4, 1, 10};

        for (int n : sizes) {
            int[][] result = slot.solve(n);
            assertEquals(n, result.length);
            for (int[] row : result) {
                assertTrue(row[0] >= 0 && row[0] <= n);
            }
        }
    }
    
    /**
    * Tests that n cant be a negative number, and that the contest is still usable
    * after the failure: a later solve with a valid size works normally.
    */
    @Test
    public void shouldntSolveNegativeWheels() throws InterruptedException {
        int n = -1;
        boolean ok = true;
 
        try {
            slot.solve(n);
        } catch (NegativeArraySizeException e) {
            ok = false;
        }
 
        assertFalse(ok);
        assertEquals(3, slot.solve(3).length);
    }
}