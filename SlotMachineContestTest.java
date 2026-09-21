import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for validating the behavior of SlotMachineContest.
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
     * Tests that solve returns one row of steps per wheel.
     */
    @Test
    public void shouldReturnOneRowPerWheel() throws InterruptedException {
        int n = 3;
        int[][] result = slot.solve(n);

        assertEquals(n, result.length);
    }

    /**
    * Tests that the step counter of every wheel stays correct, no less than 0 and no more that the total of steps.
    */
    @Test
    public void shouldStepBeCorrect() throws InterruptedException {
        int n = 4;
        int[][] result = slot.solve(n);

        for (int[] pasos : result) {
            assertTrue(pasos[0] >= 0);
            assertTrue(pasos[0] <= n);
        }
    }

    /**
    * Tests that solve keeps returning the right amount of rows for several different machine sizes, not just one lucky case.
    */
    @Test
    public void shouldWorkforEveryWhee() throws InterruptedException {
        int[] sizes = {6, 3, 4};

        for (int n : sizes) {
            int[][] result = slot.solve(n);
            assertEquals(n, result.length);
        }
    }
    
    /**
    * Tests that n cant be a negative number.
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
    }
}