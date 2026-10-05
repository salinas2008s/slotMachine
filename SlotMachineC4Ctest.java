import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for validating the types of wheels (Normal, Lefty, Rebel, Skipper)
 * and the types of symbols (Normal, Ephemeral, Shy) of the SlotMachine.
 */
public class SlotMachineC4Ctest
{
 
    private SlotMachine slotMachine;

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp() {
        slotMachine = new SlotMachine();
    }  

    /**
     * Verifica que se puedan agregar simbolos de los tres tipos.
     */
    @Test
    public void shouldAddSymbolsOfEveryType(){
        slotMachine.addSymbol("normal", 1, "red");
        slotMachine.addSymbol("ephemeral", 2, "blue");
        slotMachine.addSymbol("shy", 3, "green");
        assertTrue(slotMachine.ok());
        assertEquals(3, slotMachine.symbols().length);
    }

    /**
     * Verifica que la rueda rebel no se deje bloquear, intercambiar
     * ni eliminar, y que la maquina quede igual despues de intentarlo.
     */
    @Test
    public void shouldRebelWheelRefuseLockSwapAndDelete(){
        slotMachine.addSymbol(1, "red");
        slotMachine.addSymbol(2, "blue");
        slotMachine.addWheel("rebel", 1);
        slotMachine.addWheel(2);
        slotMachine.placeSymbol(1, "red");
        slotMachine.placeSymbol(2, "blue");
        slotMachine.lock(1);
        assertFalse(slotMachine.ok());
        slotMachine.swap(1, 2);
        assertFalse(slotMachine.ok());
        slotMachine.delWheel(1);
        assertFalse(slotMachine.ok());
        assertEquals(2, slotMachine.configuration().length);
        assertEquals("red", slotMachine.configuration()[0]);
        assertEquals("blue", slotMachine.configuration()[1]);
    }

    /**
     * Verifica que la maquina funcione con los tres tipos de rueda y los
     * tres tipos de simbolo a la vez: todos los giros son exitosos y la
     * lefty copia siempre a la rueda de su izquierda.
     */
    @Test
    public void shouldSpinMachineWithEveryWheelAndSymbolType(){
        slotMachine.addSymbol("normal", 1, "red");
        slotMachine.addSymbol("ephemeral", 2, "blue");
        slotMachine.addSymbol("shy", 3, "green");
        slotMachine.addWheel("normal", 1);
        slotMachine.addWheel("lefty", 2);
        slotMachine.addWheel("rebel", 3);
        for (int i = 0; i < 10; i++){
            slotMachine.spin();
            assertTrue(slotMachine.ok());
            String[] config = slotMachine.configuration();
            assertEquals(config[0], config[1]);
        }
    }

    /**
         * GRUPO 6
         * CañonA - PaezP
        */
        @Test
        public void acordingCaPpShouldNotSwapRebelWheel() {
            SlotMachine machine = new SlotMachine();
        
            machine.addWheel("normal", 1);
            machine.addWheel("rebel", 2);
        
            machine.swap(1, 2);
        
            assertFalse(machine.ok());
        }

    
    @Test
    public void acordingCaPpShouldNotDeleteRebelWheelbutShouldDeleteNormalWheel() {
        SlotMachine machine = new SlotMachine();
    
        machine.addWheel("normal", 1);
        machine.addWheel("rebel", 2);
    
        machine.delWheel(2);
    
        assertFalse(machine.ok());
        assertEquals(2, machine.configuration().length);
        
        machine.delWheel(1);
        assertTrue(machine.ok());
        assertEquals(1, machine.configuration().length);
    }
}