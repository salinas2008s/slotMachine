import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for validating the types of wheels (Normal, Lefty, Rebel, Skipper)
 * and the types of symbols (Normal, Ephemeral, Shy) of the SlotMachine.
 * @author Samuel Salinas - Juan David Forero
 * @version 1.0
 */
public class SlotMachineC4Test {

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
     * Tests that a normal wheel can be added to the machine.
     */
    @Test
    public void shouldAddNormalWheel() {
        slotMachine.addWheel("normal", 1);
        assertTrue(slotMachine.ok());
        assertEquals(1, slotMachine.configuration().length);
    }

    /**
     * Tests that a normal wheel can be deleted from the machine.
     */
    @Test
    public void shouldDeleteNormalWheel() {
        slotMachine.addWheel("normal", 1);
        slotMachine.delWheel(1);
        assertTrue(slotMachine.ok());
        assertEquals(0, slotMachine.configuration().length);
    }

    /**
     * Tests that a wheel of an unknown type is not added.
     */
    @Test
    public void shouldntAddWheelOfInvalidType() {
        slotMachine.addWheel("Foo", 1);
        assertFalse(slotMachine.ok());
        assertEquals(0, slotMachine.configuration().length);
    }

    /**
     * Tests that a lefty wheel copies the color of the wheel on its left when it spins.
     */
    @Test
    public void shouldLefty() {
        slotMachine.addSymbol(1, "green");
        slotMachine.addWheel("normal", 1);
        slotMachine.addWheel("lefty", 2);
        assertEquals("black", slotMachine.configuration()[1]);
        slotMachine.placeSymbol(1, "green");
        slotMachine.spin(2);
        assertEquals("green", slotMachine.configuration()[1]);
        assertTrue(slotMachine.ok());
    }

    /**
     * Tests that a lefty wheel without a wheel on its left keeps its color when it spins.
     */
    @Test
    public void shouldntLeftyChange() {
        slotMachine.addSymbol(1, "green");
        slotMachine.addWheel("lefty", 1);
        String before = slotMachine.configuration()[0];
        slotMachine.spin(1);
        assertEquals(before, slotMachine.configuration()[0]);
    }

    /**
     * Tests that a rebel wheel does not allow to be locked, so it still changes when it spins.
     */
    @Test
    public void shouldntRebelAllowLock() {
        slotMachine.addSymbol(1, "green");
        slotMachine.addWheel("rebel", 1);
        slotMachine.lock(1);
        String before = slotMachine.configuration()[0];
        slotMachine.spin(1);
        assertNotEquals(before, slotMachine.configuration()[0]);
    }

    /**
     * Tests that a rebel wheel does not allow to swap its color with another wheel.
     */
    @Test
    public void shouldntRebelAllowSwap() {
        slotMachine.addSymbol(1, "green");
        slotMachine.addWheel("normal", 1);
        slotMachine.placeSymbol(1, "green");
        slotMachine.addWheel("rebel", 2);
        String normalBefore = slotMachine.configuration()[0];
        String rebelBefore = slotMachine.configuration()[1];
        slotMachine.swap(1, 2);
        assertFalse(slotMachine.ok());
        assertEquals(normalBefore, slotMachine.configuration()[0]);
        assertEquals(rebelBefore, slotMachine.configuration()[1]);
    }

    /**
     * Tests that a rebel wheel does not allow to be deleted.
     */
    @Test
    public void shouldntRebelAllowDelete() {
        slotMachine.addWheel("rebel", 1);
        slotMachine.delWheel(1);
        assertFalse(slotMachine.ok());
        assertEquals(1, slotMachine.configuration().length);
    }

    /**
     * Tests that a skipper wheel advances four symbols on each spin.
     */
    @Test
    public void shouldSkipperSpin() {
        slotMachine.addSymbol(1, "red");
        slotMachine.addSymbol(1, "green");
        slotMachine.addSymbol(1, "blue");
        slotMachine.addSymbol(1, "yellow");
        slotMachine.addWheel("skipper", 1);
        assertEquals("black", slotMachine.configuration()[0]);
        slotMachine.spin(1);
        assertEquals("red", slotMachine.configuration()[0]);
        assertTrue(slotMachine.ok());
    }

    /**
     * Tests that a skipper wheel does not fail when there are no symbols to show.
     */
    @Test
    public void shouldntSkipperMove() {
        slotMachine.delSymbol("black");
        slotMachine.addWheel("skipper", 1);
        slotMachine.spin(1);
        assertEquals(1, slotMachine.configuration().length);
    }

    /**
     * Tests that a normal symbol can be added to the shared symbols.
     */
    @Test
    public void shouldAddNormalSymbol() {
        slotMachine.addWheel(1);
        slotMachine.addSymbol("normal", 1, "green");
        assertTrue(slotMachine.ok());
        assertEquals("green", slotMachine.symbols()[0]);
    }

    /**
     * Tests that an ephemeral symbol can be added to the shared symbols.
     */
    @Test
    public void shouldAddEphemeralSymbol() {
        slotMachine.addSymbol("ephemeral", 1, "green");
        assertTrue(slotMachine.ok());
    }

    /**
     * Tests that a shy symbol can be added to the shared symbols.
     */
    @Test
    public void shouldAddShySymbol() {
        slotMachine.addSymbol("shy", 1, "green");
        assertTrue(slotMachine.ok());
    }

    /**
     * Tests that a symbol of an unknown type is not added.
     */
    @Test
    public void shouldntAddSymbolType() {
        slotMachine.addSymbol("Foo", 1, "green");
        assertFalse(slotMachine.ok());
    }

    /**
     * Tests that a symbol with a color that is not allowed is not added.
     */
    @Test
    public void shouldntAddSymboldColor() {
        slotMachine.addSymbol("shy", 1, "pink");
        assertFalse(slotMachine.ok());
    }

    /**
     * Tests that a symbol is not added in a position outside the symbol list.
     */
    @Test
    public void shouldntAddSymbolPosition() {
        slotMachine.addSymbol("ephemeral", 10, "green");
        assertFalse(slotMachine.ok());
    }

    /**
     * Tests that a shy symbol does not toggle its visibility on a locked wheel.
     */
    @Test
    public void shouldntShyLocked() {
        slotMachine.addSymbol("shy", 1, "green");
        slotMachine.addWheel(1);
        slotMachine.placeSymbol(1, "green");
        slotMachine.lock(1);
        boolean before = slotMachine.isSymbolVisible(1);

        slotMachine.spin(1);
        slotMachine.spin(1);
        assertEquals(before, slotMachine.isSymbolVisible(1));
        assertEquals("green", slotMachine.configuration()[0]);
    }

    /**
     * Tests that an ephemeral symbol does not shrink on a locked wheel.
     */
    @Test
    public void shouldntEphemeralLocked() {
        slotMachine.addSymbol("ephemeral", 1, "green");
        slotMachine.addWheel(1);
        slotMachine.placeSymbol(1, "green");
        slotMachine.lock(1);
        int before = slotMachine.symbolSize(1);

        slotMachine.spin(1);
        slotMachine.spin(1);
        assertEquals(before, slotMachine.symbolSize(1));
    }

    /**
     * Tests that a normal symbol does not change the size nor the visibility of the wheel's symbol.
     */
    @Test
    public void shouldNormalSymbolNotChangeSize() {
        slotMachine.addSymbol("normal", 1, "green");
        slotMachine.addWheel(1);
        int size = slotMachine.symbolSize(1);
        boolean visible = slotMachine.isSymbolVisible(1);

        slotMachine.placeSymbol(1, "green");
        slotMachine.spin(1);
        slotMachine.spin(1);
        assertEquals(size, slotMachine.symbolSize(1));
        assertEquals(visible, slotMachine.isSymbolVisible(1));
    }

    /**
     * Tests that a normal symbol cannot be placed on a wheel that does not exist.
     */
    @Test
    public void shouldntPlaceNormalSymbol() {
        slotMachine.addSymbol("normal", 1, "green");
        slotMachine.addWheel(1);
        slotMachine.placeSymbol(5, "green");
        assertFalse(slotMachine.ok());
        assertEquals("black", slotMachine.configuration()[0]);
    }

}