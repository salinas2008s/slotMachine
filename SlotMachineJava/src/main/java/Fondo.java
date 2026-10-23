import java.util.*;

public class Fondo {
    private int LIGHT_SPACING = 25;
    private int LIGHT_DIAMETER = 8;
    private Rectangle body;
    private Rectangle marquee;
    private Rectangle leverStick;
    private Circle leverBall;
    private Rectangle topBar;
    private Rectangle topBar2;
    private List<Circle> lights;
    private int currentWidth;
    private boolean visible;

    public Fondo() {
        body = new Rectangle();
        body.changeColor("black");

        marquee = new Rectangle();
        marquee.changeColor("red");
        marquee.moveVertical(-40);
        marquee.changeSize(30, 40);

        leverStick = new Rectangle();
        leverStick.changeColor("black");
        leverStick.moveHorizontal(60);
        leverStick.changeSize(70, 10);

        leverBall = new Circle();
        leverBall.changeColor("red");
        leverBall.moveHorizontal(55);
        leverBall.moveVertical(-20);
        leverBall.changeSize(20);

        topBar = new Rectangle();
        topBar.changeColor("yellow");
        topBar.moveVertical(10);
        topBar.changeSize(5, 40);

        topBar2 = new Rectangle();
        topBar2.changeColor("yellow");
        topBar2.moveVertical(20);
        topBar2.changeSize(5, 40);

        lights = new ArrayList<>();
        currentWidth = 40;
        visible = false;

        updateLights(currentWidth);
    }

    private int desiredLightCount(int width) {
        int count = (width - 20) / LIGHT_SPACING + 1;
        if (count < 1) {
            count = 1;
        }
        return count;
    }

    private void updateLights(int width) {
        int desired = desiredLightCount(width);

        while (lights.size() < desired) {
            int i = lights.size();
            Circle light = new Circle();
            light.changeColor("yellow");
            light.changeSize(LIGHT_DIAMETER);
            int targetX = 10 + 15 + i * LIGHT_SPACING;
            light.moveHorizontal(targetX - 12);
            light.moveVertical(-32);
            if (visible) {
                light.makeVisible();
            }
            lights.add(light);
        }

        while (lights.size() > desired) {
            Circle last = lights.remove(lights.size() - 1);
            last.makeInvisible();
        }
    }

    public void makeVisible() {
        visible = true;
        body.makeVisible();
        marquee.makeVisible();
        leverStick.makeVisible();
        leverBall.makeVisible();
        topBar.makeVisible();
        topBar2.makeVisible();
        for (Circle light : lights) {
            light.makeVisible();
        }
    }

    public void makeInvisible() {
        visible = false;
        body.makeInvisible();
        marquee.makeInvisible();
        leverStick.makeInvisible();
        leverBall.makeInvisible();
        topBar.makeInvisible();
        topBar2.makeInvisible();
        for (Circle light : lights) {
            light.makeInvisible();
        }
    }

    public void changeSize(int newHeight, int newWidth) {
        body.changeSize(newHeight, newWidth);
        marquee.changeSize(30, newWidth);
        topBar.changeSize(5, newWidth);
        topBar2.changeSize(5, newWidth);

        int delta = newWidth - currentWidth;
        leverStick.moveHorizontal(delta);
        leverBall.moveHorizontal(delta);
        currentWidth = newWidth;

        updateLights(newWidth);
    }

    public void changeColor(String newColor) {
        body.changeColor(newColor);
    }
    
        public void pullLever() {
        if (!visible) {
            return;
        }
        leverBall.moveVertical(15);
        leverStick.moveVertical(15);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        leverBall.moveVertical(-15);
        leverStick.moveVertical(-15);
    }
}