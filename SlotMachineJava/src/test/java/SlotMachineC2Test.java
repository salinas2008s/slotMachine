import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for validating the behavior, methods, and error handling of the swap method in SlotMachine.
 * @author Samuel Salinas - Juan David Forero
 * @version 1.0
 */
public class SlotMachineC2Test {
    
    private SlotMachine slotMachine;
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp(){
        slotMachine = new SlotMachine();
    }
    /**
     * Tests that a valid color, once added to the shared symbol list,
     */
    @Test
    public void shouldAddSymbol(){
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        
        slotMachine.addSymbol(1, "yellow");
        slotMachine.placeSymbol(1, "yellow");
        
        assertEquals("yellow", slotMachine.configuration()[0]);
        assertTrue(slotMachine.ok());
    }
    /**
     * Tests that a color outside the allowed set is rejected and marked
     */
    @Test
    public void shouldntAddColorInvalidColor(){
        slotMachine.addSymbol(1, "purple");
        
        assertFalse(slotMachine.ok());
    }
    /**
     * Tests that adding wheels changes the machine's configuration
     */
    @Test
    public void shouldAddWheel(){
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        
        String[] before = slotMachine.configuration();
        
        slotMachine.addWheel(2);
        slotMachine.addWheel(3);
        
        String[] after = slotMachine.configuration();
        
        assertNotEquals(before,after);
        assertTrue(slotMachine.ok());
    }
    /**
     * Tests that adding a wheel at an out-of-range position is rejected
     */
    @Test
    public void shouldntAddWheel(){
        String[] before = slotMachine.configuration();
        
        slotMachine.addWheel(0);  
        
        String[] after = slotMachine.configuration();
        
        assertArrayEquals(before, after);
        assertFalse(slotMachine.ok());
    }
    /**
     *  Tests that configuration() reports each wheel's current color
     */
    @Test
    public void shouldShowCorrectConfiguration(){

        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        
        slotMachine.addSymbol(1, "red");
        slotMachine.addSymbol(1, "green");
        slotMachine.placeSymbol(1, "red");
        slotMachine.placeSymbol(2, "green");
        slotMachine.placeSymbol(3, "black");
        
        String[] config = slotMachine.configuration();
        
        assertEquals("red", config[0]);
        assertEquals("green", config[1]);
        assertEquals("black", config[2]);
    }
    /**
     * Tests that a rejected operation leaves the machine's configuration
     */
    @Test
    public void shouldntConfigurationChangeWithInvalidPosition(){
        String[] antes = slotMachine.configuration();
        slotMachine.addWheel(-1);
        
        assertFalse(slotMachine.ok());              
        
        String[] despues = slotMachine.configuration(); 
        assertEquals(antes.length, despues.length);
    }
    /**
     * Tests that a wheel can be removed when the machine has more than
     */
    @Test
    public void shouldDeleteWheel(){
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        
        slotMachine.delWheel(2);
        String[] configuration = slotMachine.configuration();
        
        assertEquals(4, configuration.length);
        
    }
    /**
     * Tests that the machine never drops below 3 wheels, even after
     */
    @Test
    public void shouldntDeleteWheelSizeCantBeLessThan3(){
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        
        slotMachine.delWheel(1);
        slotMachine.delWheel(1);
        
        String[] configuration = slotMachine.configuration();
        assertEquals(3, configuration.length);
    }
    
    //No realizamos should ni shouldt porque el codigo contiene System.exit(0), lo que cierra el programa.
    
    /**
    * Tests that a locked wheel keeps its color unchanged even when the whole machine is spun.
    */  
    @Test
    public void shouldLockWheel(){
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        slotMachine.addSymbol(1,"red");
        slotMachine.addSymbol(1,"red");
        String[] before = slotMachine.configuration();
        slotMachine.lock(1);
        slotMachine.spin();
        
        String[] after = slotMachine.configuration();
        assertEquals(before[0], after[0]);
        assertTrue(slotMachine.ok());
    }
    /**
     * Tests that locking an out-of-range position is rejected and that the target wheel remains unlocked and spins normally.
     */
    @Test
    public void shouldNotLockInvalidPosition(){
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        
        slotMachine.addSymbol(1, "red");
        slotMachine.addSymbol(1, "green");
        slotMachine.placeSymbol(1, "red");
        
        slotMachine.lock(0);            
        assertFalse(slotMachine.ok());
        
        String[] newColors = {"green", "green", "green"};
        slotMachine.spin(newColors);
        
        assertEquals("green", slotMachine.configuration()[0]);
    }
    
    
    //No hacemos las pruebas de makeVisible porque estás pruebas son en modo invisible, por lo que al ejecutarlas se muestra y no debería.
    /**
     * Tests that a valid color can be placed directly on a specific wheel position and is reflected in the configuration.
     */
    @Test 
    public void shouldShowCorrectPlaceSymbol(){
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        
        slotMachine.addSymbol(1, "red");
        slotMachine.placeSymbol(3, "red");
        
        assertEquals("red", slotMachine.configuration()[2]);
    }
    /**
     *  Tests that placing an invalid color is rejected and leaves the machine's configuration exactly as it was before.
     */
    @Test
    public void shouldNotPlaceSymbolColorInvalid(){
        String[] before = slotMachine.configuration();
        slotMachine.placeSymbol(1, "Rainbow");
        
        assertFalse(slotMachine.ok());
        String[] after = slotMachine.configuration();
        assertArrayEquals(before, after);
    }
    /**
     * Tests that spinning with an explicit array of valid colors sets each wheel to the exact color specified, in order.
     */
    @Test
    public void shouldSpinSetSymbol(){
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        
        slotMachine.addSymbol(1, "red");
        
        String[] colores = {"red", "black", "red"};
        slotMachine.spin(colores);
        
        String[] config = slotMachine.configuration();
        assertEquals("red", config[0]);
        assertEquals("black", config[1]);
        assertEquals("red", config[2]);
        assertTrue(slotMachine.ok());
    }
    /**
     * Tests that an invalid color inside the array is rejected, and that a wheel unaffected by the invalid entry keeps its previous color.
     */
    @Test
    public void shouldNotSpinSetSymbolInvalidColor(){
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        
        slotMachine.addSymbol(1, "red");
        slotMachine.placeSymbol(2, "black");
        
        String[] antes = slotMachine.configuration();
        String[] colores = {"red", "purple", "red"}; 
        slotMachine.spin(colores);
        
        assertFalse(slotMachine.ok());
        String[] despues = slotMachine.configuration();
        assertEquals(antes[1], despues[1]); 
    }
    /**
     * Tests that swapping with an out-of-range position is rejected,leaving both wheels' colors unchanged.
     */
    @Test
    public void shouldntSwapSymbolstInvlidPosition(){
        slotMachine.placeSymbol(1, "red");
        slotMachine.placeSymbol(2, "green");
        
        String[] before = slotMachine.configuration();
        slotMachine.swap(0, 2);
 
        assertFalse(slotMachine.ok());        
        String[] after = slotMachine.configuration();  
        assertArrayEquals(before, after);
    }
    /**
     * Tests that spinning the whole machine assigns a color from the shared symbol list to every wheel.
     */
    @Test
    public void shouldSpin(){
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        
        slotMachine.addSymbol(1, "green");
        slotMachine.delSymbol("black");
        slotMachine.spin();
        String[] config = slotMachine.configuration();
        assertEquals("green", config[0]);
        assertEquals("green", config[1]);
        assertEquals("green", config[2]);
        assertTrue(slotMachine.ok());
    }
    /**
     * Tests that deleting a symbol removes it from the shared list and forces any wheel showing that color to switch to a remaining valid color.
     */
    @Test
    public void ShouldDelSysmbol(){
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        
        slotMachine.addWheel(2);
        slotMachine.addSymbol(1, "red");    
        slotMachine.placeSymbol(2, "red");   
    
        String[] before = slotMachine.configuration();
        assertEquals("red", before[1]);
    
        slotMachine.delSymbol("red");        
    
        String[] after = slotMachine.configuration();
        assertNotEquals(before[1], after[1]);
        assertEquals("black", after[1]);
        assertTrue(slotMachine.ok());
    }
    /**
     * Tests that swap correctly exchanges the colors between two valid wheels.
     */
    @Test
    public void shouldSwapSymbols(){
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        
        slotMachine.addSymbol(1, "red");
        slotMachine.addSymbol(1, "green");
    
        slotMachine.placeSymbol(1, "red");
        slotMachine.placeSymbol(2, "green");
        
        slotMachine.swap(1, 2);
        
        String[] config = slotMachine.configuration();
        assertEquals("green", config[0]);
        assertEquals("red", config[1]);
        assertTrue(slotMachine.ok());
    }
    /**
     * Tests that swap correctly exchanges the colors between two valid wheels.
     */
    @Test
    public void shouldSwap(){
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        
        slotMachine.addSymbol(1, "green");
        slotMachine.addSymbol(1, "red");
        slotMachine.placeSymbol(1, "red");
        slotMachine.placeSymbol(2, "green");
        
        slotMachine.swap(1, 2);
        
        String[] config = slotMachine.configuration();
        assertEquals("green", config[0]);
        assertEquals("red", config[1]);
        assertTrue(slotMachine.ok());
    }
    /**
     * Tests that unlocking a previously locked wheel restores its ability to spin without errors.
     */
    @Test
    public void shouldUnlock(){
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        
        slotMachine.addSymbol(1,"green");
        slotMachine.addSymbol(2,"red");
        slotMachine.addWheel(2);
        slotMachine.addWheel(3);
        slotMachine.lock(1);
        slotMachine.unlock(1);
        slotMachine.spin();
        assertTrue(slotMachine.ok());
    }
    /**
     * Tests that unlocking an out-of-range position is rejected and  marked as not ok.
     */
    @Test
    public void shouldntUnlock(){
        slotMachine.addSymbol(1,"green");
        slotMachine.addSymbol(2,"red");
        slotMachine.addWheel(2);
        slotMachine.addWheel(3);
        slotMachine.lock(1);
        slotMachine.unlock(0); 
        assertFalse(slotMachine.ok()); 
    }
    /**
     * Tests that spinning a single valid wheel changes the machine's overall configuration and reports success.
     */
    @Test
    public void shouldSpinWheel(){
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        
        
        slotMachine.addSymbol(1,"green");
        slotMachine.addSymbol(2,"red");
        slotMachine.addWheel(2);
        slotMachine.addWheel(3);
        String[] before =  slotMachine.configuration();
        slotMachine.spin(2);
        
        String[] after =  slotMachine.configuration();
        assertTrue(slotMachine.ok());
        assertNotEquals(before,after);
        
        
        
    }
    /**
     * Tests that spinning an out-of-range wheel position is rejected,leaving the machine's configuration unchanged.
     */
    @Test
    public void shouldntSpinWheel(){
        
        slotMachine.addSymbol(1,"green");
        slotMachine.addSymbol(2,"red");
        slotMachine.addWheel(2);
        slotMachine.addWheel(3);
        String[] before =  slotMachine.configuration();
        slotMachine.spin(0);
        
        String[] after =  slotMachine.configuration();
        assertFalse(slotMachine.ok());
        assertEquals(before,after);
        
        
    }
    /**
     * Tests that spinning a single wheel over several steps changes its  color by the end of the sequence.
     */
    @Test
    public void shouldSpinSymbolsSteep()throws InterruptedException {
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        
        slotMachine.addSymbol(1,"green");
        slotMachine.addSymbol(2,"red");
        slotMachine.addWheel(2);
        slotMachine.addWheel(3);
        String[] before =  slotMachine.configuration();
        slotMachine.spin(2, 3);
        String[] after = slotMachine.configuration();
        
        assertNotEquals(before[1], after[1]); 
        assertTrue(slotMachine.ok());
        
    }
    /**
     * Tests that a locked wheel does not change color even when spun through multiple steps.
     */
    @Test
    public void shouldntSpinSymbolsSteep() throws InterruptedException {
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        
        slotMachine.addSymbol(1, "green");
        slotMachine.addSymbol(2, "red");
        slotMachine.addWheel(2);
        slotMachine.addWheel(3);
        
        slotMachine.lock(2);     
        String[] before = slotMachine.configuration();
        slotMachine.spin(2, 3);
        String[] after = slotMachine.configuration();
        
        assertEquals(before[1], after[1]); 
        assertTrue(slotMachine.ok());
    }
    
    /**
     *  Tests that symbols() reflects every color added to the shared symbol list, in the order they were inserted.
     */
    @Test
    public void shouldSymbolsAfterAdd(){
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        
        slotMachine.addSymbol(1, "green");
        slotMachine.addSymbol(2, "red");
        
        String[] after = slotMachine.symbols();
        
        assertEquals(3, after.length);
        assertEquals("green", after[0]);
        assertEquals("red", after[1]);
        assertEquals("black", after[2]);
        assertTrue(slotMachine.ok());
    }
    /**
     * Tests that attempting to add an invalid color is rejected and marked as not ok.
     */
    @Test
    public void shouldntSymbols(){
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        
        slotMachine.addSymbol(1, "purple"); 
        String[] result = slotMachine.symbols();     
        assertFalse(slotMachine.ok());        
    }
    
    // makeinvisible estás pruebas son en modo invisible, por lo que al ejecutarlas se muestra y no debería.
    
    /**
     * Tests that ok() returns true after a successful operation.
     */
    @Test
    public void shouldOk(){
        slotMachine.addSymbol(1, "green");
        assertTrue(slotMachine.ok());
    }
    /**
     *  Tests operation has failed.
     */
    @Test
    public void shouldNoOk(){
        slotMachine.addSymbol(1, "purple");
        assertFalse(slotMachine.ok());
    }
    /**
     * Test that lets you know if a Spin is valid.
     */
    public void shouldSpinRandom(){
        slotMachine.addSymbol(1, "green");
        slotMachine.addSymbol(2, "red");
        String [] before = slotMachine.configuration();
        slotMachine.spin();
        String [] after = slotMachine.configuration();
        assertNotEquals(before,after);
    }
    /**
     * Test that lets you know if a Spin is invalid.
     */
    public void shouldntSpinRandom(){
        slotMachine.addSymbol(1, "black");
        slotMachine.addSymbol(2, "black");
        String [] before = slotMachine.configuration();
        slotMachine.spin();
        String [] after = slotMachine.configuration();
        assertEquals(before,after);
    }
        /**
     * Tests that distinctSymbols correctly counts different colors across wheels.
     */
    @Test
    public void shouldCountDistinctSymbols(){
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        
        slotMachine.addSymbol(1, "red");
        slotMachine.addSymbol(1, "green");
        
        slotMachine.placeSymbol(1, "red");
        slotMachine.placeSymbol(2, "green");
        slotMachine.placeSymbol(3, "black");
        
        int distinct = slotMachine.distinctSymbols();
        
        assertEquals(3, distinct);
        assertTrue(slotMachine.ok());
    }
    /**
    * Tests that distinctSymbols does not count repeated colors multiple times.
    */
    @Test
    public void shouldNotCountRepeatedColorsSeparately(){
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        
        slotMachine.addSymbol(1, "red");
        
        slotMachine.placeSymbol(1, "red");
        slotMachine.placeSymbol(2, "red");
        slotMachine.placeSymbol(3, "red");
        
        int distinct = slotMachine.distinctSymbols();
        
        assertEquals(1, distinct);
    }
    /**
    * Tests that isJackpot returns true when all wheels show the same color.
    */
    @Test
    public void shouldIsJackpot(){
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        slotMachine.addWheel(1);
        
        slotMachine.addSymbol(1, "red");
        slotMachine.placeSymbol(1, "red");
        slotMachine.placeSymbol(2, "red");
        slotMachine.placeSymbol(3, "red");
        assertTrue(slotMachine.isJackpot());
    }
    
    /**
    * Tests that isJackpot returns false when wheels show different colors.
    */
    @Test
    public void shouldntIsJackpot(){
        slotMachine.addSymbol(1, "red");
        slotMachine.addSymbol(1, "green");
        slotMachine.placeSymbol(1, "red");
        slotMachine.placeSymbol(2, "green");
        slotMachine.placeSymbol(3, "black");
        assertFalse(slotMachine.isJackpot());
    }

}