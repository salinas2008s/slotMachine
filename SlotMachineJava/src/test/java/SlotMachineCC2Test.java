import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for validating the behavior, methods, and error handling of the swap method in SlotMachine.
 * @author DOPO 
 * @version 1.0
 */
public class SlotMachineCC2Test {
    
    private SlotMachine slotMachine;

    //Prueba Grupo 2: Ibarra y Poveda
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
     * Verifies that attempting to swap two wheels fails when one of
     * them is locked, setting the machine status to not ok.
     */
    @Test
    public void shouldNotSwap() {
        // Add some symbols
        slotMachine.addSymbol(1, "red");
        slotMachine.addSymbol(1, "blue");
       
        // Add some wheels
        slotMachine.addWheel(1);
        slotMachine.addWheel(2);
        slotMachine.addWheel(3);
       
        // Lock the first one
        slotMachine.lock(1);
       
        // Try to swap the 1st and the 3rd
        slotMachine.swap(1, 3);
   
        // Check that the action wasn't succesful due to the wheel to spin is locked
        assertFalse(slotMachine.ok());
    }
    
    //Prueba G01 : BarraganA-GuerreroB
    /** Prueba que una rueda existente pueda ser bloqueada correctamente.
        * La maquina debe permitir bloquear una rueda que existe.
        * La operacion debe realizarse correctamente y ok() debe retornar true.
    */
            
    @Test
    public void accordingBaGqShouldLockWheel() {
        SlotMachine maquinaTraga = new SlotMachine();
        maquinaTraga.addWheel(1);
        maquinaTraga.lock(1);
        assertTrue(maquinaTraga.ok());
    }
    
    //Grupo Coronado - Horta:
    /**
    * A single wheel should never trigger a jackpot even if it already has a symbol placed
    */
     
    @Test
    public void accordingCgHnIsJackpotShouldBeFalseWithOnlyOneWheelEvenIfSymbolIsSet()
    {
        slotMachine.addWheel(1);
        slotMachine.addSymbol(1, "red");
        slotMachine.placeSymbol(1, "red");
    
        assertFalse(slotMachine.isJackpot());
    }
    
    /* Adding a color that already exists on a wheel should fail */
     
    @Test 
    public void accordingCgHnAddSymbolShouldFailWhenColorAlreadyExists() {
        slotMachine.addWheel(1);
        slotMachine.addSymbol(1, "red");
        slotMachine.addSymbol(1, "red");
        assertFalse(slotMachine.ok());
    }
    
    //G03: CanchonL-PaezC
    @Test
    public void accordingClPcShouldKeepDistinctSymbolCountAfterSwap(){
        slotMachine.addWheel(1);
        slotMachine.addWheel(2);
        slotMachine.addSymbol(1, "red");
        slotMachine.addSymbol(2, "blue");
        slotMachine.placeSymbol(1, "red");
        slotMachine.placeSymbol(2, "blue");

        int before = slotMachine.distinctSymbols();
        slotMachine.swap(1, 2);
        int after = slotMachine.distinctSymbols();

        assertTrue(slotMachine.ok());
        assertEquals(   before, after);
    }
    
        // EJEMPLO 2 
    // "que NO deberia hacer": dejar una configuracion dada (spin con
    // arreglo) no deberia declarar jackpot si los simbolos asignados no
    // coinciden entre si.
    
    @Test
    public void accordingClPcShouldNotReportJackpotWhenSetConfigurationDiffers(){
        slotMachine.addWheel(1);
        slotMachine.addWheel(2);
        slotMachine.addSymbol(1, "red");
        slotMachine.addSymbol(2, "green");

        slotMachine.spin(new String[]{"red", "green", "red", "green","green"});

        assertTrue(slotMachine.ok());
        assertFalse("simbolos distintos en las ruedas no es jackpot", slotMachine.isJackpot());
    }
    
    //Grupo 07 - Juan C-Santiago R
    /** Prueba que se puedan intercambiar dos ruedas */
    @Test
    public void swapShouldExchangeWheels(){
        String[] before = slotMachine.configuration();
        slotMachine.swap(1, 3);
        String[] after = slotMachine.configuration();
        assertEquals(before[0], after[2]);
        assertEquals(before[2], after[0]);
    } 
    /** Una rueda fijada no debe girar*/
    @Test
    public void lockedWheelShouldNotSpin()
    { 
            String[] before = slotMachine.configuration();
            slotMachine.lock(1);
            slotMachine.spin(1);
            String[] after = slotMachine.configuration();
            assertArrayEquals(before, after);
    }
    
    //G06: CañonA - PaezP

    //Prueba #1

    /**
     * Verifica el intercambio entre ruedas bloqueadas y desbloqueadas,
     * y la rotación de una rueda por pasos.
     */
    @Test
    public void accordingCaPpShouldNotSwapWhenWheelIsLockedAndWorkWhenUnlocked(){
        slotMachine.addWheel(1);
        slotMachine.addWheel(2);
        slotMachine.addWheel(3);

        slotMachine.addSymbol(1, "red");
        slotMachine.addSymbol(1, "blue");

        slotMachine.addSymbol(2, "green");
        slotMachine.addSymbol(2, "yellow");

        slotMachine.addSymbol(3, "magenta");
        slotMachine.addSymbol(3, "black");

        slotMachine.spin(new String[]{"blue", "yellow", "magenta"});
        assertEquals("blue", slotMachine.configuration()[0]);

        slotMachine.lock(2);
        slotMachine.swap(1, 2);
        
        assertFalse(slotMachine.ok());
        assertEquals("yellow", slotMachine.configuration()[1]);

        slotMachine.unlock(2);
        slotMachine.swap(1, 2);
        
        assertTrue(slotMachine.ok());
        assertEquals("yellow", slotMachine.configuration()[0]);
        assertEquals("blue", slotMachine.configuration()[1]);

        try
        {
            slotMachine.spin(1,3);
        }
        catch (InterruptedException ie)
        {
            ie.printStackTrace();
        }
        assertTrue(slotMachine.ok());
        assertNotNull(slotMachine.configuration());
        assertEquals(3, slotMachine.configuration().length);
    }
        
    //G02: Carvajal- Largo:
    /**
     * Verifies that spinning a wheel advances it to the next symbol
     * in the machine's symbol list.
     */
    @Test
    public void accordingCxLxShouldAdvanceToNextSymbol() {
        slotMachine.addSymbol(1, "red");
        slotMachine.addSymbol(2, "green");
        slotMachine.addWheel(1);
        slotMachine.placeSymbol(1, "red");
        slotMachine.spin(1);
        assertEquals("green", slotMachine.configuration()[0]);  
        assertTrue(slotMachine.ok());
    }
    
    /**
     * Verifies that deleting a symbol that is not in the machine fails
     * and leaves the symbol list unchanged.
     */
    @Test
    public void accordingCxLxShouldNotDeleteMissingSymbol() {
        slotMachine.addSymbol(1, "red");
        slotMachine.delSymbol("green");
        assertFalse(slotMachine.ok());                          
        assertEquals(1, slotMachine.symbols().length);           
    }
}