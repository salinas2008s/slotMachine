import static org.junit.Assert.*;
import org.junit.Test;
 
/**
 * Tests of the shapes used to draw the slot machine: Circle and Rectangle.
 *
 * @author ForeroJ - SalinasS
 */
public class SlotMachineShapesTest {
 
    /**
     * Tests that a new circle is invisible and has its default diameter.
     */
    @Test
    public void shouldCreateInvisibleCircleWithDefaultDiameter() {
        Circle circle = new Circle();
        assertFalse(circle.isVisible());
        assertEquals(60, circle.getDiameter());
    }
 
    /**
     * Tests that a circle changes its visibility.
     */
    @Test
    public void shouldCircleChangeVisibility() {
        Circle circle = new Circle();
        circle.makeVisible();
        assertTrue(circle.isVisible());
        circle.makeInvisible();
        assertFalse(circle.isVisible());
    }
 
    /**
     * Tests that a circle changes its diameter when the new one is valid.
     */
    @Test
    public void shouldCircleChangeDiameter() {
        Circle circle = new Circle();
        circle.changeSize(20);
        assertEquals(20, circle.getDiameter());
    }
 
    /**
     * Tests that a circle ignores a negative diameter.
     */
    @Test
    public void shouldntCircleChangeToNegativeDiameter() {
        Circle circle = new Circle();
        circle.changeSize(-5);
        assertEquals(60, circle.getDiameter());
    }
 
    /**
     * Tests that an invisible circle can be moved and painted without errors.
     */
    @Test
    public void shouldMoveAndPaintInvisibleCircle() {
        Circle circle = new Circle();
        circle.moveRight();
        circle.moveLeft();
        circle.moveUp();
        circle.moveDown();
        circle.moveHorizontal(10);
        circle.moveVertical(-10);
        circle.slowMoveHorizontal(3);
        circle.slowMoveHorizontal(-3);
        circle.slowMoveVertical(3);
        circle.slowMoveVertical(-3);
        circle.changeColor("red");
        assertFalse(circle.isVisible());
    }
 
    /**
     * Tests that a visible circle can be moved, resized and painted.
     */
    @Test
    public void shouldMoveAndPaintVisibleCircle() {
        Circle circle = new Circle();
        circle.makeVisible();
        circle.moveRight();
        circle.moveLeft();
        circle.moveUp();
        circle.moveDown();
        circle.moveHorizontal(5);
        circle.moveVertical(5);
        circle.slowMoveHorizontal(3);
        circle.slowMoveHorizontal(-3);
        circle.slowMoveVertical(3);
        circle.slowMoveVertical(-3);
        circle.changeSize(30);
        circle.changeColor("green");
        assertTrue(circle.isVisible());
        assertEquals(30, circle.getDiameter());
        circle.makeInvisible();
    }
 
    /**
     * Tests that an invisible rectangle can be moved and painted without errors.
     */
    @Test
    public void shouldMoveAndPaintInvisibleRectangle() {
        Rectangle rectangle = new Rectangle();
        rectangle.moveRight();
        rectangle.moveLeft();
        rectangle.moveUp();
        rectangle.moveDown();
        rectangle.moveHorizontal(10);
        rectangle.moveVertical(-10);
        rectangle.slowMoveHorizontal(3);
        rectangle.slowMoveHorizontal(-3);
        rectangle.slowMoveVertical(3);
        rectangle.slowMoveVertical(-3);
        rectangle.changeSize(40, 50);
        rectangle.changeColor("blue");
        rectangle.makeInvisible();
    }
 
    /**
     * Tests that a visible rectangle can be moved, resized and painted.
     */
    @Test
    public void shouldMoveAndPaintVisibleRectangle() {
        Rectangle rectangle = new Rectangle();
        rectangle.makeVisible();
        rectangle.moveRight();
        rectangle.moveLeft();
        rectangle.moveUp();
        rectangle.moveDown();
        rectangle.moveHorizontal(5);
        rectangle.moveVertical(5);
        rectangle.slowMoveHorizontal(3);
        rectangle.slowMoveHorizontal(-3);
        rectangle.slowMoveVertical(3);
        rectangle.slowMoveVertical(-3);
        rectangle.changeSize(40, 50);
        rectangle.changeColor("yellow");
        rectangle.makeInvisible();
    }
}
 